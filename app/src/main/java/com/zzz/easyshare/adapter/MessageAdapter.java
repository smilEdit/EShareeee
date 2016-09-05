package com.zzz.easyshare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zzz.easyshare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/1 15:53
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    public static final int TYPE_HEAD = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOT = 2;
    public static final int counts    = 1;
    private boolean mFlag;
    private View    mHeadView;

    private List<String> mList = new ArrayList<>();
    //    private List<Message> list = new ArrayList<>();


    public MessageAdapter(View headView) {
        this.mHeadView = headView;
        mList.add("testtestest11111111111111111111111");
        mList.add("testtestest222222222222222222222222222222222222222222222222222222222222");
        mList.add("testtestest333333333333333333333333333333333333333333333333333333333333333333333333333");
    }

    public void flush(List<String> list, boolean isFlush) {
        if (isFlush) {
            mList.clear();
        }
        if (list != null) {
            mList.addAll(list);
        }
        super.notifyDataSetChanged();
    }

    //item点击回调
    public interface OnItemClickListener {
        void OnItemClick(String str);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    //脚布局点击回调
    private OnFooterListener mOnFooterListener;

    public void setOnFooterListener(OnFooterListener onFooterListener) {
        this.mOnFooterListener = onFooterListener;
    }

    public interface OnFooterListener {
        void startListener();

        void endListener();

        void runing();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new MyViewHolder(View.inflate(parent.getContext(),
                    R.layout.item_message, null));
        } else if (viewType == TYPE_FOOT) {
            return new MyViewHolder(View.inflate(parent.getContext(),
                    R.layout.item_message_foot, null));
        } else if (viewType == TYPE_HEAD) {
            return new MyViewHolder(mHeadView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            holder.Title.setText("title");
            holder.Date.setText("1993-07-19");
            holder.Details.setText(mList.get(position));
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //                        mOnItemClickListener.OnItemClick("test String");
                        Logger.d("setonClickListener");
                    }
                });

            } else if (getItemViewType(position) == TYPE_FOOT) {
                if (mFlag) {
                    holder.itemView.findViewById(R.id.skv_message_foot_load).setVisibility(View.GONE);
                    holder.itemView.findViewById(R.id.v_message_foot).setVisibility(View.GONE);
                    TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_message_foot_load);
                    tv.setText("没有更多数据！");
                    return;
                }
                if (mOnFooterListener != null) {
                    mOnFooterListener.startListener();
                }
            }

        }
    }


    @Override
    public int getItemCount() {
        return mList.size() + counts;
    }

    public void isData() {
        mFlag = mFlag;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position + counts == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Title;
        public TextView Date;
        public TextView Details;

//        @Bind(R.id.tv_item_message_title)
//        TextView Title;
//        @Bind(R.id.tv_item_message_date)
//        TextView Date;
//        @Bind(R.id.tv_item_message_details)
//        TextView Details;

        public MyViewHolder(View itemView) {
            super(itemView);
//          ButterKnife.bind(this, itemView);
            Title = (TextView) itemView.findViewById(R.id.tv_item_message_title);
            Date = (TextView) itemView.findViewById(R.id.tv_item_message_date);
            Details = (TextView) itemView.findViewById(R.id.tv_item_message_details);
        }
    }
}
