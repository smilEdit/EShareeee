package com.zzz.easyshare.manager;


import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.zzz.easyshare.AppConfig;
import com.zzz.easyshare.api.NetLoader;
import com.zzz.easyshare.api.URL;
import com.zzz.easyshare.utils.ZToast;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 15:10
 */
public class HttpLoader {

    private static final HttpLoader mInstance = new HttpLoader();
    private static final String TAG = "HttpLoader";
    private final NetLoader mNetLoader;

    public static HttpLoader getInstance() {
        return mInstance;
    }

    /**
     * 无参构造
     */
    private HttpLoader() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL.url)
                .client(client)
                .build();
        mNetLoader = retrofit.create(NetLoader.class);
    }

    /**
     * 获取网络数据
     */
    public <T> T getBean (final Class<T> clazz, Map<String, String> map, String...path){

        String name = Thread.currentThread().getName();

        if (TextUtils.equals("main", name)) {
            ZToast.showLongToast(AppConfig.sContext, "主线程获取网络数据~");
        }


        Call<ResponseBody> call = getResponseBodyCall(path, map);

        StringBuilder sb = new StringBuilder(URL.url);
        for (int i = 0; i < path.length; i++) {
            if (i != 0) {
                sb.append("/");
            }
            sb.append(path[i]);
        }
        final String url = sb.toString();
        Log.d("url",url);
        if (call != null) {
            try {
                Response<ResponseBody> execute = call.execute();
                int code = execute.code();
                if (code != 200) {
                    return null;
                }
                String result = execute.body().string();
                if (TextUtils.isEmpty(result)) {
                    result = CacheManager.getInstance().getCacheData(url);
                } else {
                    CacheManager.getInstance().saveCacheData(url, result);
                }
                if (result == null) {
                    return null;
                } else {
                    //// TODO: 2016/8/31
                    Gson gson = new Gson();

                    return gson.fromJson(result,clazz);
//                    return XmlUtils.toBean(clazz, result.getBytes());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Nullable
    private Call<ResponseBody> getResponseBodyCall(String[] path, Map<String, String> map) {
        Call<ResponseBody> call = null;
        switch (path.length) {
            case 1:
                if (map != null) {
                    call = mNetLoader.getData(path[0], map);
                } else {
                    call =  mNetLoader.getData(path[0]);
                }
                break;
            case 2:
                if (map != null) {
                    call = mNetLoader.getData(path[0], path[1], map);
                } else {
                    call = mNetLoader.getData(path[0], path[1]);
                }
                break;
            case 3:
                if (map != null) {
                    call = mNetLoader.getData(path[0], path[1], path[2], map);
                } else {
                    call = mNetLoader.getData(path[0], path[1], path[2]);
                }
                break;
            case 4:
                if (map != null) {
                    call = mNetLoader.getData(path[0], path[1], path[2], path[3], map);
                } else {
                    call = mNetLoader.getData(path[0], path[1], path[2], path[3]);
                }
                break;
            default:
                new Exception("HttpLoader getData(String...path)方法参数个数不能小于1个或大于4个。").printStackTrace();
        }
        return call;
    }
}