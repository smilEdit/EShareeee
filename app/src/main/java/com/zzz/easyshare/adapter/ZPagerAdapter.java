package com.zzz.easyshare.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzz.easyshare.ui.pager.BasePager;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 16:34
 */
public class ZPagerAdapter extends PagerAdapter {
    private Context         mContext;
    private List<String>    mTitle;
    private List<BasePager> mPagerList;

    public ZPagerAdapter(Context mContext, List<String> mTitle, List<BasePager> mPagerList) {
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mPagerList = mPagerList;
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //获得当前fragment返回的view
        BasePager basePager = mPagerList.get(position);
        View view;
        if (basePager != null) {
            if (basePager.isLoaded()) {
                basePager.onRestart();
            } else {
                basePager.initData();
            }
            view = basePager.getBaseView();
            container.addView(view);
        } else {
            view = new TextView(mContext);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        BasePager basePager = mPagerList.get(position);
        if (basePager != null) {
            basePager.onDestroy();
        }
        container.removeView((View) object);
    }
}