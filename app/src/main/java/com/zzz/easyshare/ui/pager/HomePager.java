package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.HomePagerAdapter;
import com.zzz.easyshare.bean.FuliBean;
import com.zzz.easyshare.manager.DataLoader;
import com.zzz.easyshare.utils.ZSnack;
import com.zzz.easyshare.utils.ZToast;

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

    @Bind(R.id.rv_home_goods)
    SuperRecyclerView mRvHomeGoods;

    int page = 1;

    private String TAG = "HomePager";
    private HomePagerAdapter mAdapter;
    //    private List<HomeTestData> mList;

    private List<FuliBean.ResultsBean> mFuliList = new ArrayList<>();

    /**
     * 构造方法
     *
     * @param context
     */
    public HomePager(Activity context) {
        super(context);
        setNoPagerState(false);
    }


    @Override
    protected View OnCreateView() {
        View view = View.inflate(getParentActivity(), R.layout.pager_home, null);
        return view;
    }


    @Override
    protected Object OnLoadNetData() {
        FuliBean bean = DataLoader.getInstance().getBean(FuliBean.class, "福利", "10", page + "");
        if (bean != null) {
            List<FuliBean.ResultsBean> list = bean.getResults();
            mFuliList.addAll(list);
        }
        return mFuliList;
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onUiRefresh(Object o) {
        if (mAdapter == null) {
            mAdapter = new HomePagerAdapter(mFuliList, getParentActivity());

            //设置布局管理器
            mRvHomeGoods.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            //设置默认的动画
            //            mRvHomeGoods.setItemAnimator(new DefaultItemAnimator());
            //设置Adapter
            mRvHomeGoods.setAdapter(mAdapter);
            //添加分割线
            //            mRvHomeGoods.addItemDecoration(new DividerItemDecoration(getParentActivity(), 16));

        } else {
            mAdapter.notifyDataSetChanged();
        }
        mRvHomeGoods.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downRefresh();
            }
        });
        mRvHomeGoods.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                upRefresh();
            }
        }, 10);
        mRvHomeGoods.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        mAdapter.setOnItemClickListener(new HomePagerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, TextView title) {
                ZToast.showShortToast(getParentActivity(), title.getText().toString());
            }
        });

    }


    @Override
    public String getTitleName() {
        return "Home";
    }

    //上拉加载 获取更多数据
    private void upRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                page++;
                FuliBean fuliBean = getNetData();
                if (fuliBean != null) {
                    mFuliList.addAll(fuliBean.getResults());
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
                page = 1;
                FuliBean fuliBean = getNetData();
                if (fuliBean != null) {
                    mFuliList.addAll(0, fuliBean.getResults());
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
                        mAdapter = new HomePagerAdapter(mFuliList, getParentActivity());
                        mRvHomeGoods.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                        ZSnack.showSnackShort(mRvHomeGoods, "已更新");
                    }
                    break;
                case 1:
                    ZSnack.showSnackShort(mRvHomeGoods, "已经是最新数据了~");
                    break;
                case 2:
                    ZSnack.showSnackShort(mRvHomeGoods, "已无更多数据~");
                    break;
            }
        }
    };

    private FuliBean getNetData() {
        return DataLoader.getInstance().getBean(FuliBean.class, "福利", "10", page + "");
    }
}
