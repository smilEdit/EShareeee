package com.zzz.easyshare.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.easyshare.R;
import com.zzz.easyshare.bean.FuliBean;
import com.zzz.easyshare.utils.ZImageLoader;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/9 16:11
 */
public class ExploreRvAdapter extends CommonAdapter<FuliBean.ResultsBean> {
    Context mContext;
//    List<String> mList;
    List<FuliBean.ResultsBean> mList;
    public ExploreRvAdapter(Context context, int layoutId, List<FuliBean.ResultsBean> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }
    @Override
    protected void convert(ViewHolder holder, FuliBean.ResultsBean bean, final int position) {
        holder.setText(R.id.tv_item_explore_title, mList.get(position).getDesc());
/*                .setText(R.id.tv_item_explore_address, mList.get(position).getCreatedAt())
                .setText(R.id.tv_item_explore_nickname,mList.get(position).getWho());*/
        ZImageLoader.setImg(mContext,mList.get(position).getUrl(),(ImageView)holder.getView(R.id.iv_item_explore_goods));
    }

}
