package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.view.View;

import com.zzz.easyshare.manager.LoadingPage;

import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:36
 */
public abstract class BasePager<E> {
    protected static final String TAG = "BasePager";
    private Activity    mParentActivity;
    private View        mBaseView;
    private boolean     mIsLoaded;
    private LoadingPage mLoadingPage;
    private boolean     mNoPagerState;

    /**
     * 构造方法
     */
    public BasePager(Activity context) {
        mParentActivity = context;
        mBaseView = OnCreateView();
        ButterKnife.bind(this, mBaseView);
    }

    /**
     * 设置当前页面不受页面状态管理者LoadingPager的管理。此方法应当在onInitData()方法被执行前调用。最好是放在构造方法中执行。
     * @param noPagerState true表示不愿意被管理，false表示愿意被管理。
     */
    public void setNoPagerState(boolean noPagerState) {
        mNoPagerState = noPagerState;
    }

    /**
     * 获取页面布局。
     *
     * @return 返回页面布局。
     */
    public View getBaseView() {

        //如果 mLoadingPage 为空说明还没有调用过 initData方法。
        // 这种情况一般出现在直接把一个Pager创建出来然后添加到某个布局中，或是给Activity设置contentView的时候。
        if (mLoadingPage == null) {
            initData();
        }
        return mLoadingPage;
    }

    /**
     * 获取当前页面的父Activity。
     *
     * @return 返回一个Activity, 也等同于获取Context。
     */
    public Activity getParentActivity() {
        return mParentActivity;
    }

    /**
     * 判断当前页面是否已经被加载过一次。
     *
     * @return 如果已经被加载过返回true，否则返回false。
     */
    public boolean isLoaded() {
        return mIsLoaded;
    }

    /**
     * 加载数据。这个方法不应该被调用。当该页面被加载的时候回自动被调用。
     */
    public void initData() {
        if (mLoadingPage == null) {
            initLoadingPager();
        }
        mIsLoaded = true;
        onInitData();
    }

    /**
     * 初始化LoadingPager页面。
     */
    private void initLoadingPager() {
        mLoadingPage = new LoadingPage<E>(mParentActivity, mNoPagerState) {

            @Override
            protected View createSuccessView() {
                return mBaseView;
            }

            @Override
            protected E onLoadData() {
                return OnLoadNetData();
            }

            @Override
            protected void onUiRefresh(E o) {
                BasePager.this.onUiRefresh(o);
            }
        };
    }

    /**
     * 当加载布局的时候调用此方法。
     *
     * @return 返回当前页面的布局。
     */
    protected abstract View OnCreateView();

    /**
     * 当页面被加载到布局的时候被调用。
     */
    protected void onInitData() {}

    /**
     * 当前页面不是第一次加载的时候调用。
     */
    public void onRestart() {
    }

    /**
     * 获取网络数据的时候被调用。此方法在子线程中被调用。
     *
     * @return 将获取到的数据返回。如果返回null将不会加载正常页面，而是加载空的页面从而拥有更好的用户体验。
     */
    protected abstract E OnLoadNetData();

    /**
     * 当OnLoadNetData()方法执行完毕后被调用。用于更新UI操作。此方法是在主线程中执行。
     */
    protected void onUiRefresh(E e) {
    }

    /**
     * 当页面被销毁时调用。
     */
    public void onDestroy() {
        mIsLoaded = false;
//        mLoadingPage.killThreadPools();
    }

    /**
     * 获取标题名称。
     *
     * @return 返回当前页面的标题。用于设置Action的标题。
     */
    public String getTitleName() {
        return "我是BasePager";
    }

}
