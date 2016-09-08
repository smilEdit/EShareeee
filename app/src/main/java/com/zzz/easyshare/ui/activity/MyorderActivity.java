package com.zzz.easyshare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zzz.easyshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyorderActivity extends AppCompatActivity {

    @Bind(R.id.tv_myorder_unpaid)
    TextView mTvMyorderUnpaid;
    @Bind(R.id.tv_myorder_paid)
    TextView mTvMyorderPaid;
    @Bind(R.id.tv_myorder_received)
    TextView mTvMyorderReceived;
    @Bind(R.id.tv_myorder_finished)
    TextView mTvMyorderFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("my order");
        }
    }

    @OnClick({R.id.tv_myorder_unpaid, R.id.tv_myorder_paid, R.id.tv_myorder_received, R.id.tv_myorder_finished})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_myorder_unpaid:
                break;
            case R.id.tv_myorder_paid:
                break;
            case R.id.tv_myorder_received:
                break;
            case R.id.tv_myorder_finished:
                break;
        }
    }
}
