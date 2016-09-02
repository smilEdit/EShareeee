package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.ui.activity.WebDetailActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:53
 */
public class MessagePager extends BasePager {
    @Bind(R.id.iv_pager_topic)
    ImageView mIvPagerTopic;
    @Bind(R.id.lv_pager_message)
    ListView  mLvPagerMessage;
    private View mView;

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
        return mView;
    }

    @Override
    protected Object OnLoadNetData() {
        return "";
    }


    @OnClick({R.id.iv_pager_topic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pager_topic:
                String url = "";
                toDetailWeb(url);
                break;
        }
    }

    private void toDetailWeb(String url) {
        Intent intent = new Intent();
        intent.putExtra("www.baidu.com", "");
        getParentActivity().startActivity(new Intent(getParentActivity(),WebDetailActivity.class));
    }
}
