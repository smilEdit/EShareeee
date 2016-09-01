package com.zzz.easyshare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzz.easyshare.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @创建者 zlf
 * @创建时间 2016/9/1 15:53
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEAD = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOT = 2;
    public static final int conout    = 1;
    private boolean mFlag;


    private List<String> mList = new ArrayList<>();
    //    private List<Message> list = new ArrayList<>();


    public MessageAdapter(List<String> list) {
        this.mList = list;
    }

    //item点击回调
    public interface OnItemClickListener {
        void OnItemClick(String str);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message, parent, false));
        } else if (viewType == TYPE_FOOT) {
            return new FootViewHoolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_foot, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            ((ItemViewHolder) holder).Title.setText("");
            ((ItemViewHolder) holder).Date.setText("");
            ((ItemViewHolder) holder).Details.setText("");
            if (mOnItemClickListener != null) {
                ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.OnItemClick("test String");
                    }
                });

            } else if (mList.size() >= 10) {
                FootViewHoolder footerViewHolder = (FootViewHoolder) holder;
                footerViewHolder.mTvMessageFoot.setText(R.string.loading);
                if (mOnUpPull != null) {
                    mOnUpPull.upPullLoad(mList.size());
                }
            }
        }
    }

    //上拉加载回调
    public interface OnUpPull {
        void upPullLoad(int size);
    }

    private OnUpPull mOnUpPull;

    public void setOnUpPull(OnUpPull onUpPull) {
        mOnUpPull = onUpPull;
    }

    @Override
    public int getItemCount() {
        //添加脚布局
        return mList.size() == 0 ? 0 : mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_item_message_title)
        TextView Title;
        @Bind(R.id.tv_item_message_date)
        TextView Date;
        @Bind(R.id.tv_item_message_details)
        TextView Details;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FootViewHoolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_message_foot)
        public TextView mTvMessageFoot;

        public FootViewHoolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
