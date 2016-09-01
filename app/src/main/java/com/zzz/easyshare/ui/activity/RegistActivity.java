package com.zzz.easyshare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zzz.easyshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity {

    @Bind(R.id.iv_regist_welcome)
    ImageView mIvRegistWelcome;
    @Bind(R.id.tv_regist_number)
    EditText  mTvRegistNumber;
    @Bind(R.id.tv_regist_nickname)
    EditText  mTvRegistNickname;
    @Bind(R.id.tv_regist_code)
    EditText  mTvRegistCode;
    @Bind(R.id.bt_regist_get)
    Button    mBtRegistGet;
    @Bind(R.id.tv_regist_password)
    EditText  mTvRegistPassword;
    @Bind(R.id.bt_regist_regist)
    Button    mBtRegistRegist;
    @Bind(R.id.tv_regist_login)
    TextView  mTvRegistLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_regist_get, R.id.bt_regist_regist, R.id.tv_regist_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_regist_get:
                break;
            case R.id.bt_regist_regist:
                break;
            case R.id.tv_regist_login:
                startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                Toast.makeText(RegistActivity.this, "click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
