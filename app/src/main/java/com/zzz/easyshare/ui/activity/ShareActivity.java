package com.zzz.easyshare.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zzz.easyshare.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity {


    @Bind(R.id.et_share_title)
    EditText       mEtShareTitle;
    @Bind(R.id.et_share_description)
    EditText       mEtShareDescription;
    @Bind(R.id.iv_share_picture1)
    ImageView      mIvSharePicture1;
    @Bind(R.id.iv_share_clear_icon1)
    ImageView      mIvShareClearIcon1;
    @Bind(R.id.rl_share_good1)
    RelativeLayout mRlShareGood1;
    @Bind(R.id.iv_share_picture2)
    ImageView      mIvSharePicture2;
    @Bind(R.id.iv_share_clear_icon2)
    ImageView      mIvShareClearIcon2;
    @Bind(R.id.rl_share_good2)
    RelativeLayout mRlShareGood2;
    @Bind(R.id.rl_share_picture)
    LinearLayout   mRlSharePicture;
    @Bind(R.id.iv_share_location)
    ImageView      mIvShareLocation;
    @Bind(R.id.money)
    EditText       mMoney;
    @Bind(R.id.eT_share_type)
    EditText       mETShareType;
    private String mPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initActionBar();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        if (!TextUtils.isEmpty(path)) {
            //            ZImageLoader.setImg(ShareActivity.this,path,);
        }
    }

    private void initActionBar() {
        mActionBar.setTitle("share");

    }


    private void showSelectDialog() {
        new AlertDialog.Builder(this).setTitle("请选择图片来源").setItems(

                new String[]{"从相册选择", "从相机拍照"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        switch (which) {
                            case 0:
                                if (Build.VERSION.SDK_INT < 19) {
                                    intent = new Intent();
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "选择图片"), 0);
                                } else {
                                    intent = new Intent(Intent.ACTION_PICK,
                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "选择图片"),
                                            0);
                                }
                                break;
                            case 1:
                                mPath = "";
                                String savePath = "";
                                String storageState = Environment.getExternalStorageState();
                                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                                    savePath = Environment.getExternalStorageDirectory()
                                            .getAbsolutePath() + "/oschina/Camera/";
                                    File savedir = new File(savePath);
                                    if (!savedir.exists()) {
                                        savedir.mkdirs();
                                    }
                                }

                                if (TextUtils.isEmpty(savePath)) {
                                    showShortToast("无法保存照片，请检查是否插入SD卡");
                                    return;
                                }

                                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                                String fileName = timeStamp + ".jpg";
                                File out = new File(savePath, fileName);
                                Uri uri = Uri.fromFile(out);
                                mPath = savePath + fileName;
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intent, 1);
                                break;
                        }
                    }
                }).setNegativeButton(
                "取消", null).show();
    }

    @OnClick({R.id.iv_share_clear_icon1, R.id.rl_share_good1, R.id.iv_share_clear_icon2, R.id.iv_share_location, R.id.iv_share_picture1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share_clear_icon1:
                break;
            case R.id.rl_share_good1:
                break;
            case R.id.iv_share_clear_icon2:
                break;
            case R.id.iv_share_picture1:
                showSelectDialog();
                break;
            case R.id.iv_share_location:
                break;
        }
    }
}
