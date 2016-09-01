package com.zzz.easyshare.ui.activity;

import android.os.Bundle;

import com.zzz.easyshare.R;

public class ShareActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        init();
    }

    private void init() {
        initActionBar();
    }

    private void initActionBar() {
       mActionBar.setTitle("share");
    }

}
