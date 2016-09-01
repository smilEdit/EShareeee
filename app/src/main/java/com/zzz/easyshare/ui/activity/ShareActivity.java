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

import com.zzz.easyshare.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity {

    @Bind(R.id.et_share_title)
    EditText     mEtShareTitle;
    @Bind(R.id.et_share_description)
    EditText     mEtShareDescription;
    @Bind(R.id.iv_share_good1)
    ImageView    mIvShareGood1;
    @Bind(R.id.iv_sahre_good2)
    ImageView    mIvSahreGood2;
    @Bind(R.id.tl_share_images)
    LinearLayout mTlShareImages;
    @Bind(R.id.iv_share_location)
    ImageView    mIvShareLocation;
    @Bind(R.id.money)
    EditText     mMoney;
    @Bind(R.id.eT_share_type)
    EditText     mETShareType;
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
    }

    private void initActionBar() {
        mActionBar.setTitle("share");
    }

    @OnClick({R.id.iv_share_good1, R.id.iv_sahre_good2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share_good1:
                showSelectDialog();
                break;
            case R.id.iv_sahre_good2:
                break;
        }
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
}
