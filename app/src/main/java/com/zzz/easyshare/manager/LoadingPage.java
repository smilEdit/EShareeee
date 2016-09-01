package com.zzz.easyshare.manager;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zzz.easyshare.R;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:36
 */
public abstract class LoadingPage<E> extends FrameLayout {

    private static final String TAG = "LoadingPage";
//    private static final Hashtable<String,Typeface> cache = new Hashtable<String, Typeface>();
//
//    public static Typeface get(Context c, String assetPath) {
//        synchronized (cache) {
//            if (!cache.containsKey(assetPath)) {
//                try {
//                    Typeface t = Typeface.createFromAsset(c.getAssets(),
//                            assetPath);
//                    cache.put(assetPath, t);
//                } catch (Exception e) {
//                    Log.e(TAG, "Could not get typeface '" + assetPath
//                            + "' because " + e.getMessage());
//                    return null;
//                }
//            }
//            return cache.get(assetPath);
//        }
//    }

    private PageState mCurrentState = PageState.STATE_LOADING; //表示界面当前的state，默认是加载中
    /**
     * 加载中的布局。
     */
    private View      mLoadingView;
    /**
     * 加载成功的布局。
     */
    private View      mSuccessView;
    /**
     * 加载失败的布局。
     */
    private View      mErrorView;
    private ImageView mIvLoadingImg;
    private boolean   mNoPagerState;
    private AsyncTask mAsyncTask;

    public LoadingPage(Context context) {
        this(context, false);
    }

    public LoadingPage(Context context, boolean noPagerState) {
        this(context, null);
        mNoPagerState = noPagerState;
        if (mNoPagerState) {
            showPage();
        }
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();

        initData();
    }

    public void initData() {
        //判断数据是否为空，并返回相应的状态。
        //根据状态加载相应的界面。
        mAsyncTask = new AsyncTask<Object, Void, E>() {
            @Override
            protected E doInBackground(Object... params) {
                E data = onLoadData();
                //判断数据是否为空，并返回相应的状态。
                mCurrentState = checkData(data);
                return data;
            }

            @Override
            protected void onPostExecute(E bean) {
                //根据状态加载相应的界面。
                showPage();
                onUiRefresh(bean);
            }
        }.execute();
    }
    /**
     * 加载数据
     * @return 返回的的具体数据类型和数据由子类定义。
     */
    protected abstract E onLoadData();

    private PageState checkData(Object data) {
        if (data == null) {
            return PageState.STATE_ERROR;
        } else {
            if (data instanceof List) {
                if (((List) data).size() < 1) {
                    return PageState.STATE_ERROR;
                }
            }
        }
        return PageState.STATE_SUCCESS;
    }

    /**
     * 初始化View
     */
    private void initView() {
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.page_loading, null);
            mIvLoadingImg = (ImageView) mLoadingView.findViewById(R.id.iv_loading);
            Glide.with(getContext()).load(R.mipmap.loading_gif).into(mIvLoadingImg);
        }
        addView(mLoadingView);
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);
            TextView tvTip = (TextView) mErrorView.findViewById(R.id.tv_tipTitle);
            TextView tvTip1 = (TextView) mErrorView.findViewById(R.id.tv_tipTitle1);
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/newSmallBalls.ttf");
            tvTip.setTypeface(typeface);
            tvTip1.setTypeface(typeface);
            //手动重试！
            mErrorView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentState = PageState.STATE_LOADING;
                    Glide.with(getContext()).load(R.mipmap.loading_gif).into(mIvLoadingImg);
                    showPage();
                    initData();
                }
            });
        }
        addView(mErrorView);
        if (mSuccessView == null) {
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new IllegalArgumentException("The method createSuccessView() can not return null!");
            } else {
                if (mSuccessView.getLayoutParams() == null) {
                    addView(mSuccessView);
                }
            }
        }
        showPage();
    }

    /**
     * 让子类自己实现加载成功的布局。
     * @return 返回一个布局。
     */
    protected abstract View createSuccessView();

    /**
     * 根据当前的页面状态显示对应的页面。
     */
    private void showPage() {
        mLoadingView.setVisibility(INVISIBLE);
        mErrorView.setVisibility(INVISIBLE);
        mSuccessView.setVisibility(INVISIBLE);
        //如果子类不想让父类管理页面状态就直接返回子类的布局。
        if (mNoPagerState) {
            mSuccessView.setVisibility(VISIBLE);
            Glide.clear(mIvLoadingImg);
            return;
        }

        switch (mCurrentState) {
            case STATE_LOADING: //加载中
                mLoadingView.setVisibility(VISIBLE);
                break;
            case STATE_ERROR:  //加载失败
                mErrorView.setVisibility(VISIBLE);
                Glide.clear(mIvLoadingImg);
                break;
            case STATE_SUCCESS:  //加载成功
                mSuccessView.setVisibility(VISIBLE);
                Glide.clear(mIvLoadingImg);
                break;
        }
    }

    /**
     * onLoadData()方法执行完毕后，此方法在主线程中执行。
     * @param bean onLoadData()方法返回的值。
     */
    protected abstract void onUiRefresh(E bean);

    public void killThreadPools(){
        // TODO: 2016/8/31 设置TAG 让AsyncTask 结束任务。
    };

    /**
     * 定义3种状态常量
     */
    enum PageState{
        STATE_LOADING,//加载中的状态
        STATE_ERROR,//加载失败的状态
        STATE_SUCCESS//加载成功的状态
    }
}
