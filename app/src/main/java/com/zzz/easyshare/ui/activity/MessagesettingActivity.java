package com.zzz.easyshare.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.zzz.easyshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagesettingActivity extends BaseActivity {

    @Bind(R.id.switch_messagesetting_system)
    Switch mSwitchMessagesettingSystem;
    @Bind(R.id.switch_messagesetting_order)
    Switch mSwitchMessagesettingOrder;
    @Bind(R.id.switch_messagesetting_chat)
    Switch mSwitchMessagesettingChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagesetting);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setTitle("message setting");
        }
    }

    @OnClick({R.id.switch_messagesetting_system, R.id.switch_messagesetting_order, R.id.switch_messagesetting_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_messagesetting_system:
                break;
            case R.id.switch_messagesetting_order:
                break;
            case R.id.switch_messagesetting_chat:
                break;
        }
    }
}
