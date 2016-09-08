package com.zzz.easyshare.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.utils.ZToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 zlf
 * @创建时间 2016/9/8 10:06
 */
public class MyshareActivity extends BaseActivity {
    @Bind(R.id.bt_myshare_ongoing)
    Button   mBtMyshareOngoing;
    @Bind(R.id.bt_myshare_end)
    Button   mBtMyshareEnd;
    @Bind(R.id.lv_myshare_content)
    ListView mLvMyshareContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myshare);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle("My share");
        }
    }

    @OnClick({R.id.bt_myshare_ongoing, R.id.bt_myshare_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_myshare_ongoing:
                onGoing();
                break;
            case R.id.bt_myshare_end:
                onEnd();
                break;
        }
    }

    private void onGoing() {
        ZToast.showShortToast(MyshareActivity.this,"onGoing");
    }

    private void onEnd() {
        ZToast.showShortToast(MyshareActivity.this,"End");
    }
}
