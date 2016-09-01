package com.zzz.easyshare.adapter;

import android.app.Activity;
import android.view.View;

import com.zzz.easyshare.ui.pager.BasePager;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:32
 */
public class CommonPagerAdapter extends BaseViewPagerAdapter<BasePager> {
    public CommonPagerAdapter(Activity context) {
        super(context);
    }

    @Override
    protected void onDestroyItem(BasePager basePager, int position) {
        basePager.onDestroy();
    }

    /**
     * 当页面被加载的时候被调用。
     * @param basePager 当前页面的对象实例。
     * @param position 当前的页面索引。
     * @return 返回当前页面要加载的布局。
     */
    @Override
    protected View onCreateConvertView(BasePager basePager, int position) {
        if (basePager.isLoaded()) {
            basePager.onRestart();
        } else {
            basePager.initData();
        }
        return basePager.getBaseView();
    }
}
