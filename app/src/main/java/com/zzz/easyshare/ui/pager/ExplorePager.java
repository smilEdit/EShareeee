package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.ExploreRvAdapter;
import com.zzz.easyshare.bean.FuliBean;
import com.zzz.easyshare.manager.DataLoader;
import com.zzz.easyshare.utils.ZSnack;
import com.zzz.easyshare.utils.ZToast;
import com.zzz.easyshare.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:53
 */
public class ExplorePager extends BasePager {
    @Bind(R.id.spinner_explore_types)
    Spinner            mSpinnerExploreTypes;
    @Bind(R.id.spinner_explore_sorting)
    Spinner            mSpinnerExploreSorting;
    @Bind(R.id.rv_explore)
    RecyclerView       mRvExplore;
    @Bind(R.id.srl_explore_refresh)
    SwipeRefreshLayout mSrlExploreRefresh;

    private View mView;

    private List<FuliBean.ResultsBean> mList = new ArrayList<>();

    private ExploreRvAdapter        mExploreRvAdapter;
    private LoadMoreWrapper<Object> mLoadMoreWrapper;

    /**
     * 构造方法
     *
     * @param context
     */
    public ExplorePager(Activity context) {
        super(context);
        setNoPagerState(false);
    }

    @Override
    protected View OnCreateView() {
        initView();
        return mView;
    }

    private void initView() {
        mView = View.inflate(getParentActivity(), R.layout.pager_explore, null);
    }

    @Override
    protected void onUiRefresh(Object o) {
        if (mExploreRvAdapter == null) {

            mExploreRvAdapter = new ExploreRvAdapter(getParentActivity(), R.layout.item_explore, mList);

            //设置布局管理器
            mRvExplore.setLayoutManager(new LinearLayoutManager(getParentActivity()));
            //设置分割线
            mRvExplore.addItemDecoration(new DividerItemDecoration(getParentActivity(), 1));
            //加载Item动画
            //            mRvExplore.setItemAnimator();
            //添加尾部局
            mLoadMoreWrapper = new LoadMoreWrapper<>(mExploreRvAdapter);
            mLoadMoreWrapper.setLoadMoreView(R.layout.item_message_foot);
        }
        initListener();
        mRvExplore.setAdapter(mLoadMoreWrapper);
    }

    private void initListener() {
        //上滑加载更多监听
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {

            @Override
            public void onLoadMoreRequested() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        FuliBean fuliBean = getNetData();
                        if (fuliBean != null) {
                            mList.addAll(fuliBean.getResults());
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }).start();
            }
        });
        //条目点击监听
        mExploreRvAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ZToast.showShortToast(getParentActivity(), position + "");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        //下拉刷新监听
        mSrlExploreRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        FuliBean fuliBean = getNetData();
                        if (fuliBean != null) {
                            mList.addAll(0, fuliBean.getResults());
                            mHandler.sendEmptyMessage(0);
                        } else {
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                }).start();
                mSrlExploreRefresh.setRefreshing(false);
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mLoadMoreWrapper.notifyDataSetChanged();
                    ZSnack.showSnackShort(mRvExplore, "已更新");
                    break;
                case 1:
                    ZSnack.showSnackShort(mRvExplore, "已经是最新数据了~");
                    break;
                case 2:
                    ZSnack.showSnackShort(mRvExplore, "已无更多数据~");
                    break;
            }
        }
    };

    @Override
    protected Object OnLoadNetData() {
        FuliBean bean = getNetData();
        if (bean != null) {
            List<FuliBean.ResultsBean> list = bean.getResults();
            mList.addAll(list);
        }
        return mList;
    }

    int page = 1;

    private FuliBean getNetData() {
        return DataLoader.getInstance().getBean(FuliBean.class, "福利", "10", page + "");
    }
    /*    @OnClick({R.id.spinner_explore_types, R.id.spinner_explore_sorting})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.spinner_explore_types:
                    break;
                case R.id.spinner_explore_sorting:
                    break;
            }
        }*/
}
