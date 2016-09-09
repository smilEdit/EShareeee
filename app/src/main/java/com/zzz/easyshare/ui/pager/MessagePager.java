package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.MessageAdapter;
import com.zzz.easyshare.ui.activity.WebDetailActivity;
import com.zzz.easyshare.utils.ZImageLoader;
import com.zzz.easyshare.utils.ZSnack;
import com.zzz.easyshare.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:53
 */
public class MessagePager extends BasePager implements View.OnClickListener {

    //    @Bind(R.id.rv_pager_message)
    //    RecyclerView       mRvPagerMessage;
    //    @Bind(R.id.srl_message_refresh)
    //    SwipeRefreshLayout mSrlMessageRefresh;
    //    @Bind(R.id.iv_message_head)
    //    ImageView          mIvMessageHead;

    //测试数据
    private List<String>        mList;
    private View                mView;
    private MessageAdapter      mMessageAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView        mRecyclerView;
    private SwipeRefreshLayout  mRefreshLayout;
    private ImageView           mIvMessageHead;
    private View                mHeadView;

    /**
     * 构造方法
     *
     * @param context
     */
    public MessagePager(Activity context) {
        super(context);
        setNoPagerState(true);
    }

    @Override
    protected View OnCreateView() {
        mView = View.inflate(getParentActivity(), R.layout.pager_message, null);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_pager_message);

        mRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.srl_message_refresh);
        mHeadView = View.inflate(getParentActivity(), R.layout.item_message_head, null);
        mIvMessageHead = (ImageView) mHeadView.findViewById(R.id.iv_message_head);
        mIvMessageHead.setOnClickListener(this);
        //创建布局管理器
        mLayoutManager = new LinearLayoutManager(getParentActivity());
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getParentActivity(), 1));

        mRefreshLayout.setColorSchemeColors(Color.parseColor("#40a953"));

        initHeadView();

        return mView;
    }

    //初始化刷新
    private void initListener() {
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downRefresh();
            }
        });

        //上拉加载更多
        mMessageAdapter.setOnFooterListener(new MessageAdapter.OnFooterListener() {
            @Override
            public void startListener() {
                upRefresh();
            }

            @Override
            public void endListener() {

            }

            @Override
            public void runing() {

            }
        });
    }

    //上拉加载更多
    private void upRefresh() {
        //重置网络请求参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (i++ % 5 != 0) {
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mList.add(getParentActivity().getString(R.string.justTest) + i + "++++");
                    mHandler.sendEmptyMessage(0);
                } else {
                    mHandler.sendEmptyMessage(2);
                }
            }
        }).start();
    }


    private int i = 0;

    //下拉刷新逻辑
    private void downRefresh() {
        //重置网络请求参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (i++ % 5 != 0) {
                    mList.add(0, getParentActivity().getString(R.string.justTest) + i);
                    mHandler.sendEmptyMessage(0);
                } else {
                    mHandler.sendEmptyMessage(1);
                }
                SystemClock.sleep(2000);
            }
        }).start();
    }


    //头布局初始化
    private void initHeadView() {
        ZImageLoader.setImg(getParentActivity(), "http://img1.imgtn.bdimg.com/it/u=1581831496,1600408569&fm=21&gp=0.jpg", mIvMessageHead);
    }

    @Override
    protected Object OnLoadNetData() {

        if (mList == null) {
            mList = new ArrayList<>();
        }
        for (int i = 0; i < 5; i++) {
            mList.add(getParentActivity().getString(R.string.justTest) + i);
        }
        return mList;
    }

    @Override
    protected void onUiRefresh(Object o) {
        if (mMessageAdapter == null) {
            //创建适配器
            mMessageAdapter = new MessageAdapter(mHeadView, mList);
            //设置适配器
            mRecyclerView.setAdapter(mMessageAdapter);

        } else {
            mMessageAdapter.notifyDataSetChanged();
        }
        initListener();
    }


    //头布局点击逻辑
    private void toDetailWeb(String url) {
        Intent intent = new Intent(getParentActivity(), WebDetailActivity.class);
        intent.putExtra("address", url);
        getParentActivity().startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        String url = "https://2.taobao.com/";
        toDetailWeb(url);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //判空
                    if (mMessageAdapter == null) {
                        mMessageAdapter = new MessageAdapter(mHeadView, mList);
                        mRecyclerView.setAdapter(mMessageAdapter);
                    } else {
                        mMessageAdapter.notifyDataSetChanged();
                        ZSnack.showSnackShort(mRefreshLayout, "已更新");
                    }
                    if (mRefreshLayout.isRefreshing()) {
                        mRefreshLayout.setRefreshing(false);
                    }
                    break;
                case 1:
                    if (mRefreshLayout.isRefreshing()) {
                        mRefreshLayout.setRefreshing(false);
                    }
                    ZSnack.showSnackShort(mRefreshLayout, "已经是最新数据了");
                    break;
                case 2:
                    ZSnack.showSnackShort(mRefreshLayout, "已无更多数据");
                    mMessageAdapter.isData(false);
                    break;
            }
        }
    };
}
