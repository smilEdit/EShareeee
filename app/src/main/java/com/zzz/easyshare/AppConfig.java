package com.zzz.easyshare;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.Logger;
import com.zzz.easyshare.api.URL;
import com.zzz.easyshare.utils.CacheInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @创建者 zzz
 */
public class AppConfig extends Application {

    public static Context  sContext;
    public static Handler  sHandler;
    public static Retrofit sRetrofit;

    public static ArrayList<Activity> sActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sHandler = new Handler();
        Logger.init("EShare");
        // LeakCanary.install(this);
        initRetrofit();
    }

    private void initRetrofit() {
        File cacheFile = new File(this.getCacheDir(), "CacheData");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        Interceptor interceptor = new CacheInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(interceptor)
                .build();

        sRetrofit = new Retrofit.Builder()
                .baseUrl(URL.url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
