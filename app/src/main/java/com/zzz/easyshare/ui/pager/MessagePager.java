package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.view.View;

import com.zzz.easyshare.R;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:53
 */
public class MessagePager extends BasePager {
    private View mView;

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
        return mView;
    }

    @Override
    protected Object OnLoadNetData() {
        return null;
    }
}
