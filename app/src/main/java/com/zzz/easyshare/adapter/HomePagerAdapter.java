package com.zzz.easyshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.easyshare.R;
import com.zzz.easyshare.bean.HomeTestData;
import com.zzz.easyshare.utils.ZImageLoader;
import com.zzz.easyshare.widget.GoodsImage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/8 15:32
 */
public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.HomePagerViewHolder> {

    public Context mContext;
    private List<HomeTestData> mList;
    private HomePagerViewHolder mHolder;

    public HomePagerAdapter(List<HomeTestData> list,Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public HomePagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recycler, parent, false);
        return new HomePagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomePagerViewHolder holder, int position) {
        this.mHolder = holder;
        holder.mTvItemHomeTitle.setText(mList.get(position).getTitle());
        ZImageLoader.setImg(mContext,mList.get(position).getImage(), holder.mIvItemHomeGoods);
//        Glide.with(mContext).load(mList.get(position).getImage()).into(holder.mIvItemHomeGoods);
//        Picasso.with(mContext).load(mList.get(position).getImage()).into(holder.mIvItemHomeGoods);
        ZImageLoader.setAvatar(mContext,"http://img3.imgtn.bdimg.com/it/u=1691364090,593751885&fm=11&gp=0.jpg", holder.mIvItemHomeUser);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public static class HomePagerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_item_home_goods)
        GoodsImage mIvItemHomeGoods;
        @Bind(R.id.tv_item_home_title)
        TextView   mTvItemHomeTitle;
        @Bind(R.id.tv_item_home_details)
        TextView   mTvItemHomeDetails;
        @Bind(R.id.iv_item_home_user)
        ImageView  mIvItemHomeUser;
        @Bind(R.id.tv_item_home_username)
        TextView   mTvItemHomeUsername;

        public HomePagerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
//    private class DriverViewTarget extends BitmapImageViewTarget {
//
//        private final GoodsImage mItemHomeGoods;
//
//        public DriverViewTarget(GoodsImage ivItemHomeGoods) {
//            super(ivItemHomeGoods);
//            mItemHomeGoods = ivItemHomeGoods;
//        }
//        @Override
//        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//            int viewWidth = mHolder.mIvItemHomeGoods.getWidth();
//            float scale = resource.getWidth() / (viewWidth * 1.0f);
//            int viewHeight = (int) (resource.getHeight() * scale);
//            mItemHomeGoods.setOriginal(viewWidth, viewHeight);
////            setCardViewLayoutParams(viewWidth, viewHeight);
////            resource.setWidth(viewWidth);
////            resource.setHeight(viewHeight);
//
//            super.onResourceReady(mItemHomeGoods, glideAnimation);
//        }
//    }
}
