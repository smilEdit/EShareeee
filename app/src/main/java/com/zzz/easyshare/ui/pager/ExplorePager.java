package com.zzz.easyshare.ui.pager;

import android.app.Activity;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.zzz.easyshare.R;
import com.zzz.easyshare.widget.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:53
 */
public class ExplorePager extends BasePager {
    private View             mView;
    private ConvenientBanner mConvenientBanner;

    /**
     * 构造方法
     *
     * @param context
     */
    public ExplorePager(Activity context) {
        super(context);
        setNoPagerState(false);
    }

    @Override
    protected View OnCreateView() {
        initView();
        return mView;
    }

    private void initView() {
        mView = View.inflate(getParentActivity(), R.layout.pager_explore, null);
        initConvenient();
    }

    private void initConvenient() {
        mConvenientBanner = (ConvenientBanner) mView.findViewById(R.id.convenient);
        List<String> localImages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            localImages.add("http://img3.imgtn.bdimg.com/it/u=2945870806,1886591151&fm=21&gp=0.jpg");
        }
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                .setPageIndicator(new int[]{R.drawable.head_vp_shape, R.drawable.head_vp_shape_wh})
                .setCanLoop(true);

        mConvenientBanner.startTurning(2000);
    }

    @Override
    protected Object OnLoadNetData() {
        return "";
    }
}
