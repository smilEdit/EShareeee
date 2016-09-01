package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.view.View;

import com.zzz.easyshare.R;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 16:38
 */
public class TagPager extends BasePager {
    /**
     * 构造方法
     *
     * @param context
     */
    public TagPager(Activity context) {
        super(context);
    }

    @Override
    protected View OnCreateView() {
        View view = View.inflate(getParentActivity().getApplicationContext(), R.layout.layout_test_tag, null);
        return view;
    }

    @Override
    protected Object OnLoadNetData() {
        return "";
    }
}
