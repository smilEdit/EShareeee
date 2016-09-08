package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.HomePagerAdapter;
import com.zzz.easyshare.bean.HomeTestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:51
 */
public class HomePager extends BasePager {


    @Bind(R.id.tv_home_city)
    TextView             mTvHomeCity;
    @Bind(R.id.et_home_search)
    AutoCompleteTextView mEtHomeSearch;
    @Bind(R.id.srl_home_refresh)
    SwipeRefreshLayout   mSrlHomeRefresh;
    @Bind(R.id.rv_home_goods)
    RecyclerView mRvHomeGoods;

    private String TAG = "HomePager";
    private HomePagerAdapter mAdapter;
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
            mList.add(new HomeTestData(R.drawable.test555, "this is a test title1"));
            mList.add(new HomeTestData(R.drawable.test444, "this is a test title2"));
            mList.add(new HomeTestData(R.drawable.test333, "this is a test title3"));
            mList.add(new HomeTestData(R.drawable.test111, "this is a test title4"));
            mList.add(new HomeTestData(R.drawable.test222, "this is a test title5"));
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
            //设置布局管理器
            mRvHomeGoods.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            //设置Adapter
            mRvHomeGoods.setAdapter(mAdapter);
            //添加分割线

//            mRvHomeGoods.addItemDecoration(new DividerItemDecoration(getParentActivity(), 16));
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String getTitleName() {
        return "Home";
    }
}
