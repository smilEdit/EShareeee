package com.zzz.easyshare.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zzz.easyshare.R;
import com.zzz.easyshare.utils.ZImageLoader;

import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/9/9 16:11
 */
public class ExploreRvAdapter extends CommonAdapter<String> {
    Context mContext;
    List<String> mList;
    public ExploreRvAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_item_explore_title, "万能适配器哦");
        holder.setText(R.id.tv_item_explore_address, mList.get(position));
        ZImageLoader.setImg(mContext,"http://ww2.sinaimg.cn/large/610dc034jw1f7mixvc7emj20ku0dv74p.jpg",(ImageView)holder.getView(R.id.iv_item_explore_goods));
    }
}
