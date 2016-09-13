package com.zzz.easyshare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


    public MessageAdapter(View headView, List<String> list) {
        this.mHeadView = headView;
        this.mList = list;
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
        void OnItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    //脚布局回调
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            holder.Title.setText("title");
            holder.Date.setText("1993-07-19:::::::" + position);
            holder.Details.setText(mList.get(position - counts));
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.OnItemClick(position);

                    }
                });

            } else if (getItemViewType(position + 1) == TYPE_FOOT) {

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

    public void isData(boolean flag) {
        mFlag = flag;
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


        public MyViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.tv_item_message_title);
            Date = (TextView) itemView.findViewById(R.id.tv_item_message_date);
            Details = (TextView) itemView.findViewById(R.id.tv_item_message_details);
        }
    }
}
