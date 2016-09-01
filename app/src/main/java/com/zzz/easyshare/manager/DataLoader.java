package com.zzz.easyshare.manager;

import java.util.Map;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 15:09
 */
public class DataLoader {

    private static final String TAG = "DataLoader";

    public static final DataLoader mInstance = new DataLoader();

    /**
     * 无参构造
     */
    private DataLoader() {}

    /**
     * 获取实例对象。
     * @return 返回一个 DataLoader 的实例对象。
     */
    public static DataLoader getInstance() {
        return mInstance;
    }

    /**
     * 加载数据。
     * @param clazz 要获取的bean对象的字节码对象。
     * @param path 要访问的Url路径。
     */
    public <T> T getBean(final Class<T> clazz, String...path) {
        return getBean(clazz, null, path);
    }

    /**
     * 加载数据。
     * @param clazz 要获取的bean对象的字节码对象。
     * @param path 要访问的Url路径。
     */
    public <T> T getBean(final Class<T> clazz, Map<String, String> map, String...path) {
        return HttpLoader.getInstance().getBean(clazz, map, path);
    }
}
