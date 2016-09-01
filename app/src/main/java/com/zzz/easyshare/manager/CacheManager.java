package com.zzz.easyshare.manager;

import android.os.Environment;
import android.util.Log;

import com.zzz.easyshare.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 15:13
 */
public final class CacheManager {
    private static final String TAG = "CacheManager";
    /**
     * 初始化本类对象
     */
    private static CacheManager sCacheManager = new CacheManager();
    private String mCachePath;

    /**
     * 私有构造函数，防止其他类创建本类对象。
     */
    private CacheManager() {
        mCachePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + AppConfig.sContext.getPackageName() + File.separator + "cache";
        File cacheFile = new File(mCachePath);
        if (!cacheFile.exists()) {
            //不存在创建目录
            boolean mkdirs = cacheFile.mkdirs();//这里如果目录是一级的不需要,如果目录是多级的需要创建
        }
    }

    /**
     * 获取本类实例。
     */
    public static CacheManager getInstance() {
        return sCacheManager;
    }

    /**
     * 得到缓存的数据
     *
     * @return 返回缓存的网络数据
     */
    public String getCacheData(String url) {

        //得到文件
        try {
            File file = new File(mCachePath, md5FileName(url));
            FileInputStream fis = new FileInputStream(file);
            int len;
            //记录
            StringBuilder cacheData = new StringBuilder();
            for (byte[] buffer = new byte[1024]; (len = fis.read(buffer)) != -1; ) {
                cacheData.append(new String(buffer, 0, len));
            }
            fis.close();
            return cacheData.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存缓存的数据
     * 1. 缓存 的数据应该是唯一的(文件名)就用md5
     * http://www.baidu.com/ad
     */
    public void saveCacheData(String url, String jsonData) {

        //文件,我们应该先建立目录

        Log.i(TAG, "saveCacheData: 将数据缓存到本地");


        try {
            String fileName = md5FileName(url);
            //md5加密文件名
            File file = new File(mCachePath, fileName);
            //写入数据
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonData.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据URL地址计算出唯一的文件名称。
     *
     * @param url Url地址。
     * @return 返回一个文件名
     * @throws NoSuchAlgorithmException
     */
    private String md5FileName(String url) throws NoSuchAlgorithmException {
        StringBuilder fileName = new StringBuilder();
        MessageDigest digester = MessageDigest.getInstance("MD5");
        digester.update(url.getBytes());//得到加密的字符
        byte[] digest = digester.digest();//加密
        for (byte aDigest : digest) {
            String hexString = Integer.toHexString(aDigest & 0xff);
            fileName.append(hexString);
        }
        return fileName.toString();
    }
}
