package com.zzz.easyshare.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.easyshare.R;
import com.zzz.easyshare.bean.FuliBean;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/14 10:46
 */
public class MessageAdapter extends CommonAdapter<FuliBean.ResultsBean> {
    Context                mContext;

    List<FuliBean.ResultsBean> mList;
    public MessageAdapter(Context context, int layoutId, List<FuliBean.ResultsBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }
    @Override
    protected void convert(ViewHolder holder, FuliBean.ResultsBean bean, final int position) {
        holder.setText(R.id.tv_item_message_title, mList.get(position-1).getDesc());
//                .setText(R.id.tv_item_message_date, mList.get(position).getWho());
    }
}
