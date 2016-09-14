package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.MessageAdapter;
import com.zzz.easyshare.bean.FuliBean;
import com.zzz.easyshare.manager.DataLoader;
import com.zzz.easyshare.ui.activity.WebDetailActivity;
import com.zzz.easyshare.utils.ZImageLoader;
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
public class MessagePager extends BasePager {

    @Bind(R.id.rv_pager_message)
    RecyclerView       mRecyclerView;
    @Bind(R.id.srl_message_refresh)
    SwipeRefreshLayout mRefreshLayout;
    //测试数据
    private List<FuliBean.ResultsBean> mList = new ArrayList<>();
    int page = 0;
    private View mView;

    private MessageAdapter          mMessageAdapter;
    private ImageView               mIvMessageHead;
    private View                    mHeadView;
    private LoadMoreWrapper<Object> mLoadMoreWrapper;
    private HeaderAndFooterWrapper  mHeaderAndFooterWrapper;

    /**
     * 构造方法
     *
     * @param context
     */
    public MessagePager(Activity context) {
        super(context);
        setNoPagerState(false);
    }

    @Override
    protected View OnCreateView() {
        mView = View.inflate(getParentActivity(), R.layout.pager_message, null);
        mHeadView = View.inflate(getParentActivity(), R.layout.item_message_head, null);
        initHeadView();
        return mView;
    }

    @Override
    protected Object OnLoadNetData() {
        FuliBean bean = getNetData();
        if (bean != null) {
            List<FuliBean.ResultsBean> list = bean.getResults();
            mList.addAll(list);
        }
        return mList;
    }

    @Override
    protected void onUiRefresh(Object o) {
        if (mMessageAdapter == null) {
            //创建适配器
            mMessageAdapter = new MessageAdapter(getParentActivity(), R.layout.item_message, mList);
            //设置布局管理器
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getParentActivity()));
            //添加分割线
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getParentActivity(), 1));
            //设置swipe颜色
            mRefreshLayout.setColorSchemeColors(Color.parseColor("#40a953"));
            //添加加载更多布局
            mLoadMoreWrapper = new LoadMoreWrapper<>(mMessageAdapter);
            mLoadMoreWrapper.setLoadMoreView(R.layout.item_message_foot);
            //            //添加头布局
            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mLoadMoreWrapper);
            mHeaderAndFooterWrapper.addHeaderView(mHeadView);
        }
        initListener();
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        //mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    //监听
    private void initListener() {
        //头布局点击监听
        mIvMessageHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://2.taobao.com/";
                Intent intent = new Intent(getParentActivity(), WebDetailActivity.class);
                intent.putExtra("address", url);
                getParentActivity().startActivity(intent);
            }
        });
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
        mMessageAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                mRefreshLayout.setRefreshing(false);
            }
        });

    }

    //头布局初始化
    private void initHeadView() {
        mIvMessageHead = (ImageView) mHeadView.findViewById(R.id.iv_message_head);

        ZImageLoader.setImg(getParentActivity(), "http://img1.imgtn.bdimg.com/it/u=1581831496,1600408569&fm=21&gp=0.jpg", mIvMessageHead);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mHeaderAndFooterWrapper.notifyDataSetChanged();
                    if (mRefreshLayout.isRefreshing()) {
                        mRefreshLayout.setRefreshing(false);
                    }
                    ZSnack.showSnackShort(mRefreshLayout, "已更新");
                    break;
                case 1:
                    if (mRefreshLayout.isRefreshing()) {
                        mRefreshLayout.setRefreshing(false);
                    }
                    ZSnack.showSnackShort(mRefreshLayout, "已经是最新数据了");
                    break;
                case 2:
                    ZSnack.showSnackShort(mRefreshLayout, "已无更多数据");
                    break;
            }
        }
    };

    private FuliBean getNetData() {
        return DataLoader.getInstance().getBean(FuliBean.class, "福利", "10", page + "");
    }
}
