package com.zzz.easyshare.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.zzz.easyshare.R;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 17:34
 */
public class LocalImageHolderView implements Holder<Integer>{

    private ImageView mImageView;
    private Context mContext;

    @Override
    public View createView(Context context) {
        this.mContext = context;
        mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        Glide.with(mContext).load(data).placeholder(Color.TRANSPARENT)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(mImageView);
    }
}
