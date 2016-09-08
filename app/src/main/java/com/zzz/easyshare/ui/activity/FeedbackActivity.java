package com.zzz.easyshare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zzz.easyshare.R;
import com.zzz.easyshare.utils.ZGetT;
import com.zzz.easyshare.utils.ZToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends AppCompatActivity {

    @Bind(R.id.et_feedback_suggest)
    EditText mEtFeedbackSuggest;
    @Bind(R.id.bt_feedback_confirm)
    Button   mBtFeedbackConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Feedback");
        }
    }

    @OnClick({R.id.bt_feedback_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_feedback_confirm:
                String suggest = ZGetT.t(mEtFeedbackSuggest);
                ZToast.showShortToast(FeedbackActivity.this,suggest);
                break;
        }
    }
}
