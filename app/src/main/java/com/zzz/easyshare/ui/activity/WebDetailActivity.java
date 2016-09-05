package com.zzz.easyshare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zzz.easyshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebDetailActivity extends AppCompatActivity {

    @Bind(R.id.wv_webdetail_content)
    WebView mWvWebdetailContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webdetail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("address");
        // 设置WevView要显示的网页
//        mWvWebdetailContent.loadDataWithBaseURL(null, url, "text/html", "utf-8",
//                null);
        mWvWebdetailContent.loadUrl(url);
        //自适应屏幕缩放
        mWvWebdetailContent.getSettings().setUseWideViewPort(true);
        mWvWebdetailContent.getSettings().setLoadWithOverviewMode(true);
        //手势缩放
        mWvWebdetailContent.getSettings().setSupportZoom(true);
        mWvWebdetailContent.getSettings().setBuiltInZoomControls(true);
//        mWvWebdetailContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWvWebdetailContent.getSettings().setJavaScriptEnabled(true); //设置支持Javascript
        mWvWebdetailContent.requestFocus(); //触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        // mWvWebdetailContent.getSettings().setBuiltInZoomControls(true); //页面添加缩放按钮
        // mWvWebdetailContent.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);   //取消滚动条

        // 点击链接由自己处理，而不是新开Android的系统browser响应该链接。
        mWvWebdetailContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //设置点击网页里面的链接还是在当前的webview里跳转
                view.loadUrl(url);
                return true;
            }
        });
    }
}
