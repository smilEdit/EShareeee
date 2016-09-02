package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.utils.ZToast;

import butterknife.Bind;
import butterknife.OnClick;

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
                toMyShare();
                break;
            case R.id.iv_person_tags:
                toMyTags();
                break;
            case R.id.iv_person_order:
                toMyOrder();
                break;
            case R.id.iv_person_info:
                toPersonalInfo();
                break;
            case R.id.iv_person_message_setting:
                toMesSetting();
                break;
            case R.id.iv_person_password_setting:
                toPswSetting();
                break;
            case R.id.iv_person_eshare:
                toAboutEShare();
                break;
            case R.id.iv_person_feedback:
                toFeedback();
                break;
            case R.id.iv_person_help:
                toHelp();
                break;
        }
    }

    private void toMesSetting() {
        ZToast.showShortToast(getParentActivity(),"toMesSetting");

    }

    private void toPswSetting() {
        ZToast.showShortToast(getParentActivity(),"toPswSetting");

    }

    private void toAboutEShare() {
        ZToast.showShortToast(getParentActivity(),"toAboutEShare");

    }

    private void toFeedback() {
        ZToast.showShortToast(getParentActivity(),"toFeedback");

    }

    private void toHelp() {
        ZToast.showShortToast(getParentActivity(),"toHelp");

    }

    private void toPersonalInfo() {
        ZToast.showShortToast(getParentActivity(),"toPersonalInfo");

    }

    private void toMyOrder() {
        ZToast.showShortToast(getParentActivity(),"toMyOrder");

    }

    private void toMyTags() {
        ZToast.showShortToast(getParentActivity(),"toMyTags");

    }

    private void toMyShare() {
        ZToast.showShortToast(getParentActivity(),"toMyShare");

    }

    private void changeAvatar() {
        ZToast.showShortToast(getParentActivity(),"changeAvatar");
    }
}
