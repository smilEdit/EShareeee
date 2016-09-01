package com.zzz.easyshare.ui.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zzz.easyshare.AppConfig;
import com.zzz.easyshare.R;
import com.zzz.easyshare.manager.DataLoader;
import com.zzz.easyshare.utils.ZToast;

import java.util.Map;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 15:04
 */
public class BaseActivity<T> extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "BaseActivity";
    private InputMethodManager mInputManager;
    public  ViewPager          mViewPager;

    /**
     * ActionBar的开关
     */
    private   ActionBarDrawerToggle drawToggle;
    protected ActionBar             mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.sActivities.add(this);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(true);
            mActionBar.setTitle(R.string.app_name);
        }
    }

    /**
     * 设置ViewPager。
     * @param viewPager 当前Activity的ViewPager对象。
     */
    protected void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (isShouldHideKeyBoard(view, ev)) {
            hideKeyBoard(view.getWindowToken());
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 获取InputMethodManager，隐藏软键盘。
     *
     * @param token 当前被选中的View的Token。
     */
    private void hideKeyBoard(IBinder token) {
        if (token != null) {
            mInputManager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v     当前被点击的View。
     * @param event 当前点击事件的MotionEvent。
     * @return 可以隐藏键盘返回true，否者返回false。
     */
    private boolean isShouldHideKeyBoard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 加载数据。
     * @param clazz 要获取的bean对象的字节码对象。
     * @param path 要访问的Url路径。
     */
    public void getBean(final Class<T> clazz, Map<String, String> map, String...path) {
        requestNetData(clazz, map, path);
    }

    /**
     * 加载数据。
     * @param clazz 要获取的bean对象的字节码对象。
     * @param path 要访问的Url路径。
     */
    public void getBean(final Class<T> clazz, String...path) {
        requestNetData(clazz, null, path);
    }

    /**
     * 获取网络数据。
     * @param clazz 要获取对象的class对象。
     * @param map 参数map集合。可以为null。
     * @param path 要获取的网络的子路径，如果有多个参数可以传入多个。不能出现 index/login 这样的格式。
     */
    private void requestNetData(final Class<T> clazz, final Map<String, String> map, final String[] path) {
        new AsyncTask<Object, Void, T>() {
            @Override
            protected T doInBackground(Object... params) {
                return DataLoader.getInstance().getBean(clazz, map, path);
            }

            @Override
            protected void onPostExecute(T aVoid) {
                onUiRefresh(aVoid);
            }
        }.execute();
    }

    /**
     * getBean 方法执行完毕成功获取数据后会执行此方法。
     * @param bean 获取到的bean对象。
     */
    protected void onUiRefresh(T bean) {}

    /**
     * 弹出一个时间较短的单例Toast。
     *
     * @param text 要弹出的文本。
     */
    protected void showShortToast(String text) {
        ZToast.showShortToast(this, text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppConfig.sActivities.remove(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
}
