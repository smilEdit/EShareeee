package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.ui.activity.FeedbackActivity;
import com.zzz.easyshare.ui.activity.MessagesettingActivity;
import com.zzz.easyshare.ui.activity.MyorderActivity;
import com.zzz.easyshare.ui.activity.MyshareActivity;
import com.zzz.easyshare.ui.activity.PersonalInfoActivity;
import com.zzz.easyshare.utils.ZToast;

import butterknife.Bind;
import butterknife.OnClick;
import tyrantgit.explosionfield.ExplosionField;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:54
 */
public class PersonPager extends BasePager {

    @Bind(R.id.iv_person_avatar)
    ImageView mIvPersonAvatar;
    @Bind(R.id.tv_person_nickname)
    TextView  mTvPersonNickname;
    @Bind(R.id.tv_person_signature)
    TextView  mTvPersonSignature;
    @Bind(R.id.iv_person_share)
    ImageView mIvPersonShare;
    @Bind(R.id.iv_person_tags)
    ImageView mIvPersonTags;
    @Bind(R.id.iv_person_order)
    ImageView mIvPersonOrder;
    @Bind(R.id.iv_person_info)
    ImageView mIvPersonInfo;
    @Bind(R.id.iv_person_message_setting)
    ImageView mIvPersonMessageSetting;
    @Bind(R.id.iv_person_password_setting)
    ImageView mIvPersonPasswordSetting;
    @Bind(R.id.iv_person_eshare)
    ImageView mIvPersonEshare;
    @Bind(R.id.iv_person_feedback)
    ImageView mIvPersonFeedback;
    @Bind(R.id.iv_person_help)
    ImageView mIvPersonHelp;
    private View mView;
    private ExplosionField mExplosionField;

    /**
     * 构造方法
     *
     * @param context
     */
    public PersonPager(Activity context) {
        super(context);
        setNoPagerState(false);
    }

    @Override
    protected View OnCreateView() {
        mView = View.inflate(getParentActivity(), R.layout.pager_person, null);
        mExplosionField = ExplosionField.attach2Window(getParentActivity());
        return mView;
    }

    @Override
    protected Object OnLoadNetData() {
        return "";
    }

    @OnClick({R.id.iv_person_avatar, R.id.tv_person_nickname, R.id.tv_person_signature, R.id.iv_person_share, R.id.iv_person_tags, R.id.iv_person_order, R.id.iv_person_info, R.id.iv_person_message_setting, R.id.iv_person_password_setting, R.id.iv_person_eshare, R.id.iv_person_feedback, R.id.iv_person_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_person_avatar:
                changeAvatar();
                break;
            case R.id.tv_person_nickname:
                break;
            case R.id.tv_person_signature:
                break;
            case R.id.iv_person_share:
                mExplosionField.explode(view);
                toMyShare();
                break;
            case R.id.iv_person_tags:
                mExplosionField.explode(view);
                toMyTags();
                break;
            case R.id.iv_person_order:
                mExplosionField.explode(view);
                toMyOrder();
                break;
            case R.id.iv_person_info:
                mExplosionField.explode(view);
                toPersonalInfo();
                break;
            case R.id.iv_person_message_setting:
                mExplosionField.explode(view);
                toMesSetting();
                break;
            case R.id.iv_person_password_setting:
                mExplosionField.explode(view);
                toPswSetting();
                break;
            case R.id.iv_person_eshare:
                mExplosionField.explode(view);
                toAboutEShare();
                break;
            case R.id.iv_person_feedback:
                mExplosionField.explode(view);
                toFeedback();
                break;
            case R.id.iv_person_help:
                mExplosionField.explode(view);
                toHelp();
                break;
        }
    }

    private void toMesSetting() {
        Intent intent = new Intent(getParentActivity(), MessagesettingActivity.class);
        //        intent.putExtra();
        getParentActivity().startActivity(intent);
    }

    private void toPswSetting() {
        ZToast.showShortToast(getParentActivity(),"toPswSetting");

    }

    private void toAboutEShare() {
        ZToast.showShortToast(getParentActivity(),"toAboutEShare");

    }

    private void toFeedback() {
        getParentActivity().startActivity(new Intent(getParentActivity(), FeedbackActivity.class));
    }

    private void toHelp() {
        ZToast.showShortToast(getParentActivity(),"toHelp");

    }

    private void toPersonalInfo() {
        Intent intent = new Intent(getParentActivity(), PersonalInfoActivity.class);
        //        intent.putExtra();
        getParentActivity().startActivity(intent);

    }

    private void toMyOrder() {
        Intent intent = new Intent(getParentActivity(), MyorderActivity.class);
        //        intent.putExtra();
        getParentActivity().startActivity(intent);
    }

    private void toMyTags() {
        ZToast.showShortToast(getParentActivity(),"toMyTags");

    }

    private void toMyShare() {
        Intent intent = new Intent(getParentActivity(), MyshareActivity.class);
//        intent.putExtra();
        getParentActivity().startActivity(intent);
    }

    private void changeAvatar() {

        ZToast.showShortToast(getParentActivity(),"changeAvatar");
    }
}
