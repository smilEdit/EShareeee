package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.HomePagerAdapter;
import com.zzz.easyshare.bean.HomeTestData;
import com.zzz.easyshare.manager.DataLoader;
import com.zzz.easyshare.utils.ZSnack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:51
 */
public class HomePager extends BasePager implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.tv_home_city)
    TextView             mTvHomeCity;
    @Bind(R.id.et_home_search)
    AutoCompleteTextView mEtHomeSearch;
    @Bind(R.id.srl_home_refresh)
    SwipeRefreshLayout   mSrlHomeRefresh;
    @Bind(R.id.rv_home_goods)
    RecyclerView         mRvHomeGoods;

    private String TAG = "HomePager";
    private HomePagerAdapter   mAdapter;
    private List<HomeTestData> mList;

    /**
     * 构造方法
     *
     * @param context
     */
    public HomePager(Activity context) {
        super(context);
        setNoPagerState(true);
    }


    @Override
    protected View OnCreateView() {
        View view = View.inflate(getParentActivity(), R.layout.pager_home, null);
        return view;
    }

    @Override
    protected Object OnLoadNetData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(new HomeTestData("http://ww1.sinaimg.cn/large/7a8aed7bgw1ewgtp8kircj20ht0qodj0.jpg", "this is a test title1"));
            mList.add(new HomeTestData("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2243962948,2705986633&fm=116&gp=0.jpg", "this is a test title2"));
            mList.add(new HomeTestData("http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg", "this is a test title3"));
            mList.add(new HomeTestData("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=137160088,3671401105&fm=116&gp=0.jpg", "this is a test title4"));
            mList.add(new HomeTestData("http://ww1.sinaimg.cn/large/610dc034jw1f7lughzrjmj20u00k9jti.jpg", "this is a test title5"));
        }
        return mList;
    }


    @Override
    protected void onInitData() {

    }

    @Override
    protected void onUiRefresh(Object o) {
        if (mAdapter == null) {
            mAdapter = new HomePagerAdapter(mList, getParentActivity());
            final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRvHomeGoods.setOnScrollListener(new RecyclerView.OnScrollListener() {
                int[] lastVisibleItemPositions;

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPositions[0] + 1 == mAdapter.getItemCount()) {
                        upRefresh();
//                        mSrlHomeRefresh.setRefreshing(true);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                ZSnack.showSnackShort(mRvHomeGoods, "加载更多~");
//                                mSrlHomeRefresh.setRefreshing(false);
//                            }
//                        }, 1000);
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null);
                }
            });
            //设置布局管理器
            mRvHomeGoods.setLayoutManager(layoutManager);
            //设置默认的动画
            mRvHomeGoods.setItemAnimator(new DefaultItemAnimator());
            //设置Adapter
            mRvHomeGoods.setAdapter(mAdapter);
            //添加分割线
            //            mRvHomeGoods.addItemDecoration(new DividerItemDecoration(getParentActivity(), 16));
            mSrlHomeRefresh.setOnRefreshListener(this);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }




    @Override
    public String getTitleName() {
        return "Home";
    }

    @Override
    public void onRefresh() {
        downRefresh();
    }

    //上拉加载 获取更多数据
    private void upRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //                HomeTestData data = getNetData();
                HomeTestData data = new HomeTestData("http://ww4.sinaimg.cn/large/610dc034gw1ew5b4ri9mxj20ic0qoq4t.jpg", "this is a test 666");
                if (data != null) {
                    mList.add(data);
                    mHandler.sendEmptyMessage(0);
                } else {
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }

    //下拉刷新 获取最新数据
    private void downRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //                HomeTestData data = getNetData();
                HomeTestData data = new HomeTestData("http://ww4.sinaimg.cn/large/610dc034gw1ew5b4ri9mxj20ic0qoq4t.jpg", "this is a test 666");
                if (data != null) {
                    mList.add(0, data);
                    mHandler.sendEmptyMessage(0);
                } else {
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //以防万一 为了健壮
                    if (mAdapter == null) {
                        mAdapter = new HomePagerAdapter(mList, getParentActivity());
                        mRvHomeGoods.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                        ZSnack.showSnackShort(mRvHomeGoods, "咻咻咻咻咻咻~");
                    }
                    //为了健壮
                    if (mSrlHomeRefresh.isRefreshing()) {
                        mSrlHomeRefresh.setRefreshing(false);
                    }
                    break;
                case 1:
                    if (mSrlHomeRefresh.isRefreshing()) {
                        mSrlHomeRefresh.setRefreshing(false);
                    }
                    ZSnack.showSnackShort(mRvHomeGoods, "已经是最新数据了~");
                    break;
                case 2:
                    if (mSrlHomeRefresh.isRefreshing()) {
                        mSrlHomeRefresh.setRefreshing(false);
                    }
                    ZSnack.showSnackShort(mRvHomeGoods, "已无更多数据~");
                    break;
            }
        }
    };

    private HomeTestData getNetData() {
        return DataLoader.getInstance().getBean(HomeTestData.class, "", "", "");
    }
}
