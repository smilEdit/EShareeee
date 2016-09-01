package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.ZPagerAdapter;
import com.zzz.easyshare.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:51
 */
public class HomePager extends BasePager{

    PagerSlidingTabStrip slidingtab;
    private String TAG = "HomePager";
    private ViewPager mVp_Home;

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
        mVp_Home = (ViewPager) view.findViewById(R.id.vp_home_viewpager);
        slidingtab = (PagerSlidingTabStrip) view.findViewById(R.id.slidingtab);
        return view;
    }

    @Override
    protected Object OnLoadNetData() {
        return "";
    }

    private List<BasePager> basePagerList = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    @Override
    protected void onInitData() {
        basePagerList.clear();
        mTitle.clear();
        mTitle.add("tag1");
        mTitle.add("tag2");
        mTitle.add("tag3");
        mTitle.add("tag4");
        basePagerList.add(new TagPager(getParentActivity()));
        basePagerList.add(new TagPager(getParentActivity()));
        basePagerList.add(new TagPager(getParentActivity()));
        basePagerList.add(new TagPager(getParentActivity()));
        mVp_Home.setAdapter(new ZPagerAdapter(getParentActivity(),mTitle,basePagerList));
        slidingtab.setViewPager(mVp_Home);
    }

    @Override
    public String getTitleName() {
        return "Home";
    }
}
