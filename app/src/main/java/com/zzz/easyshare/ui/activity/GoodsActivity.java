package com.zzz.easyshare.ui.activity;

import android.os.Bundle;

import com.zzz.easyshare.R;

public class GoodsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle("goods");
        }
    }
}
