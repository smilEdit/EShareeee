package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.MessageAdapter;
import com.zzz.easyshare.ui.activity.WebDetailActivity;
import com.zzz.easyshare.utils.Utils;
import com.zzz.easyshare.utils.ZImageLoader;
import com.zzz.easyshare.utils.ZToast;
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
    private List<String> mList;
    private View mView;
    private MessageAdapter mMessageAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView mIvMessageHead;

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
        View headView = View.inflate(getParentActivity(), R.layout.item_message_head, null);
        mIvMessageHead = (ImageView) headView.findViewById(R.id.iv_message_head);
        mIvMessageHead.setOnClickListener(this);
        //创建适配器
        mMessageAdapter = new MessageAdapter(headView);
        //创建布局管理器
        mLayoutManager = new LinearLayoutManager(getParentActivity());
        //设置适配器
        mRecyclerView.setAdapter(mMessageAdapter);
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getParentActivity(), 1));
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#40a953"));
        initHeadView();
        initListener();
        if (mList == null) {
            mList = new ArrayList<>();
        }
        return mView;
    }

    //初始化刷新
    private void initListener() {

    }


    //头布局初始化
    private void initHeadView() {
        ZImageLoader.setImg(getParentActivity(), "http://img1.imgtn.bdimg.com/it/u=1581831496,1600408569&fm=21&gp=0.jpg", mIvMessageHead);
    }

    @Override
    protected Object OnLoadNetData() {

        return "";
    }

    @Override
    protected void onInitData() {
        mList.clear();
        mList.add("testtestestestestestestesttestestesttest");
        mList.add("testtestestestestestestesttestestesttest");

        if (mRefreshLayout.isRefreshing()) {
            Utils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    refreshSwipe(false);
                }
            });
        }
    }

    private void refreshSwipe(boolean flag) {
        mMessageAdapter.flush(mList,flag);
//        ZSnack.showSnackShort();
        ZToast.showShortToast(getParentActivity(),"刷新成功");
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onUiRefresh(Object o) {
        super.onUiRefresh(o);
    }


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
}
