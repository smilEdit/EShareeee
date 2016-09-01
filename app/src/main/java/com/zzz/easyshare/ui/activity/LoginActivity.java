package com.zzz.easyshare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.easyshare.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.iv_login_welcome)
    ImageView mIvLoginWelcome;
    @Bind(R.id.et_login_number)
    EditText  mEtLoginNumber;
    @Bind(R.id.et_login_password)
    EditText  mEtLoginPassword;
    @Bind(R.id.tv_login_forgotmm)
    TextView  mTvLoginForgotmm;
    @Bind(R.id.bt_login_login)
    Button    mBtLoginLogin;
    @Bind(R.id.iv_login_weichat)
    ImageView mIvLoginWeichat;
    @Bind(R.id.iv_login_qq)
    ImageView mIvLoginQq;
    @Bind(R.id.tv_login_tohome)
    TextView  mTvLoginTohome;
    @Bind(R.id.tv_login_regist)
    TextView  mTvLoginRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login_forgotmm, R.id.bt_login_login, R.id.iv_login_weichat, R.id.iv_login_qq,R.id.tv_login_regist,R.id.tv_login_tohome})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forgotmm:
                break;
            case R.id.bt_login_login:
                break;
            case R.id.iv_login_weichat:
                break;
            case R.id.iv_login_qq:
                break;
            case R.id.tv_login_tohome:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.tv_login_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
        }
    }
}
