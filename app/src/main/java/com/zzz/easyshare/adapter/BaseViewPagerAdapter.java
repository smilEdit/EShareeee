package com.zzz.easyshare.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzz.easyshare.excption.MethodNotImplementedException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 14:33
 */
public abstract class BaseViewPagerAdapter<P> extends PagerAdapter {
    private static final String TAG = "BaseViewPagerAdapter";
    protected final Activity mContext;
    /**
     * 当前适配器中的所有页面
     */
    private Map<Class<? extends P>, P> mPagerMap     = new HashMap<>();
    private List<Class<? extends P>>   mPagerClsList = new ArrayList<>();
    private Class<?> mInvokeArg[];

    public BaseViewPagerAdapter(Activity context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mPagerMap == null? 0 : mPagerMap.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mInvokeArg == null) {
            throw new MethodNotImplementedException("setInvokeArg(Class<?>... parameterTypes)方法未被调用！这个方法是用来设置Class对象创建实例时所需要的参数的。");
        }
        Class<? extends P> cls = mPagerClsList.get(position);
        P pager = getPager(cls);
        View view = getConvertView(pager, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        P p = getItemInstance(position);
        if (p != null) {
            BaseViewPagerAdapter.this.onDestroyItem(p, position);
        }
        container.removeView((View) object);
    }

    /**
     * 设置class对象构造方法所需要的参数。如果没有有参构造则不需要传参。但是该方法必须被调用。且是在setAdapter()方法执行前被调用。
     * @param parameterTypes class对象构造方法所需要的参数的class对象。
     */
    public void setInvokeArg(Class<?>... parameterTypes) {
        mInvokeArg = parameterTypes;
    }

    /**
     * 添加布局对象
     * @param cls 添加的布局对象的class对象。
     * @return 添加成功返回true,失败返回false。
     */
    public boolean addPager(Class<? extends P> cls) {
        int pagerSize = mPagerMap.size();
        int clsSize = mPagerClsList.size();

        if (!mPagerMap.containsKey(cls)) {
            mPagerMap.put(cls, null);
        }

        boolean addMapOk = mPagerMap.size() > pagerSize;
        if (!addMapOk) {
            return false;
        }
        if (!mPagerClsList.contains(cls)) {
            mPagerClsList.add(cls);
        }
        boolean addListOk = mPagerClsList.size() > clsSize;
        if (!addListOk) {
            mPagerMap.remove(cls);
            return false;
        }
        return true;
    }

    /**
     * 当一个Item被销毁时调用。通常在这个方法中做的事情是执行 container.removeView()，
     * 但是这个事情父类已经做过了。子类不需要关心。其实这个方法只是父类通知之类有一个条目被销毁了而已。
     * @param p 当前页面的对象。
     * @param position 当前页面的索引。
     */
    protected abstract void onDestroyItem(P p, int position);

    /**
     * 通过class对象获取一个页面实例。
     * @param cls 要获取实例的class对象。
     * @return 成功则返回一个对象。失败返回null。
     */
    protected P getItemInstance(Class<? extends P> cls) {
        return mPagerMap.get(cls);
    }

    /**
     * 通过class对象获取一个页面实例。
     * @param position 要获取实例的Item索引。
     * @return 成功则返回一个对象。失败返回null。
     */
    protected P getItemInstance(int position) {
        return getItemInstance(mPagerClsList.get(position));
    }

    /**
     * 获取当前页面的布局。
     * @param p 当前页面的对象。
     * @param position 当前页面的索引。
     * @return 返回当前页面的布局。
     */
    private View getConvertView(P p, int position) {
        //如果页面没有被加载过就加载此页面。否则就调用从新加载的方法。
        if (p == null) {
            new NullPointerException("BaseViewPagerAdapter: 页面创建失败~").printStackTrace();
            return new TextView(mContext);
        }
        return onCreateConvertView(p, position);
    }

    /**
     * 当页面被加载的时候被调用。
     * @param p 当前页面的对象实例。
     * @param position 当前的页面索引。
     * @return 返回当前页面要加载的布局。
     */
    protected abstract View onCreateConvertView(P p, int position);

    /**
     * 创建页面对象实例。
     * @param cls 要创建页面对象的class对象。
     * @return 创建成功返回一个对象，失败返回null。
     */
    private P getPager(Class<? extends P> cls) {
        P pager = mPagerMap.get(cls);
        if (pager != null) {
            return pager;
        }
        try {
            Constructor<? extends P> constructor = cls.getConstructor(mInvokeArg);
            P newPager = constructor.newInstance(mContext);
            mPagerMap.put(cls, newPager);
            return newPager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
