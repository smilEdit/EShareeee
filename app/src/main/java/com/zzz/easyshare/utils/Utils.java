package com.zzz.easyshare.utils;


import com.zzz.easyshare.AppConfig;

/**
 * @创建者 zlf
 * @创建时间 2016/8/19 15:31
 */
public class Utils {
    public static void runOnUIThread(Runnable runnable){
        AppConfig.sHandler.post(runnable);
    }
}
