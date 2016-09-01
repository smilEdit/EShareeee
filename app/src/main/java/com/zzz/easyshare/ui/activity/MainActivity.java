package com.zzz.easyshare.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zzz.easyshare.R;
import com.zzz.easyshare.adapter.CommonPagerAdapter;
import com.zzz.easyshare.ui.pager.ExplorePager;
import com.zzz.easyshare.ui.pager.HomePager;
import com.zzz.easyshare.ui.pager.MessagePager;
import com.zzz.easyshare.ui.pager.PersonPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.vp_main)
    ViewPager    mVpMain;
    @Bind(R.id.tv_main_home)
    TextView     mTvMainHome;
    @Bind(R.id.ll_home)
    LinearLayout mLlHome;
    @Bind(R.id.tv_main_explore)
    TextView     mTvMainExplore;
    @Bind(R.id.ll_explore)
    LinearLayout mLlExplore;
    @Bind(R.id.iv_quickOption)
    ImageView    mIvQuickOption;
    @Bind(R.id.tv_main_message)
    TextView     mTvMainMessage;
    @Bind(R.id.ll_message)
    LinearLayout mLlMessage;
    @Bind(R.id.tv_main_person)
    TextView     mTvMainPerson;
    @Bind(R.id.ll_person)
    LinearLayout mLlPerson;

    private View     mCurrentView;
    private TextView mCurrentTextView;
    private int mCurrentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化参数
        init();

    }

    private void init() {
        initActionBar();
        selectPagers(0);  //更新底部工具栏的选中。
        setViewPager((ViewPager) findViewById(R.id.vp_main));
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        CommonPagerAdapter adapter = new CommonPagerAdapter(this);
        adapter.addPager(HomePager.class);
        adapter.addPager(ExplorePager.class);
        adapter.addPager(MessagePager.class);
        adapter.addPager(PersonPager.class);
        adapter.setInvokeArg(Activity.class);
        mViewPager.setAdapter(adapter);
    }


    @OnClick({R.id.ll_home, R.id.ll_explore, R.id.iv_quickOption, R.id.ll_message, R.id.ll_person})
    public void onClick(View view) {
        int index = -1;
        switch (view.getId()) {
            case R.id.ll_home:  //点击了《home》按钮
                index = 0;
                break;
            case R.id.ll_explore:  //点击了《explore》按钮
                index = 1;
                break;
            case R.id.iv_quickOption:  //点击了《分享》按钮
                //弹出一个dialog
                startActivity(new Intent(MainActivity.this,ShareActivity.class));
                break;
            case R.id.ll_message:   //点击了《message》按钮
                index = 2;
                break;
            case R.id.ll_person:   //点击了《person》按钮
                index = 3;
                break;
        }
        if (index != -1) {
            mViewPager.setCurrentItem(index, false);
        }
    }

    private void initActionBar() {
        getSupportActionBar().hide();
    }

    private void selectPagers(int index) {
        if (mCurrentIndex == index) {
            return;
        }
        mCurrentIndex = index;
        View v = null;
        TextView t = null;
        switch (index) {
            case 0:  //点击了《home》按钮
                v = mLlHome;
                t = mTvMainHome;
                break;
            case 1:  //点击了《explore》按钮
                v = mLlExplore;
                t = mTvMainExplore;
                break;
            case 2:   //点击了《message》按钮
                v = mLlMessage;
                t = mTvMainMessage;
                break;
            case 3:   //点击了《person》按钮
                v = mLlPerson;
                t = mTvMainPerson;
                break;
        }
        if (v != null) {
            v.setSelected(true);
            if (mCurrentView != null)
                mCurrentView.setSelected(false);
        }
        if (t != null) {
            t.setTextColor(getResources().getColor(R.color.md_green_600_color_code));
            if (mCurrentTextView != null)
                mCurrentTextView.setTextColor(Color.GRAY);
        }
        mCurrentView = v;
        mCurrentTextView = t;
    }

    private long firstTime = 0;

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime < 2000) {
            finish();
        } else {
            firstTime = secondTime;
            Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 当ViewPager的页面被改变时调用该方法。
     *
     * @param position 当前被选中的页面。
     */
    public void onPageSelected(int position) {
        selectPagers(position);  //更新底部工具栏的选中。
    }

}
