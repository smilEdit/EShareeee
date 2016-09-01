package com.zzz.easyshare.api;

import com.zzz.easyshare.bean.Data;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 12:11
 */
public interface DataApi {
    @Headers("Cache-Control: public, max-age=3600")
    @GET("data/{type}/{count}/{page}")
    Observable<Data> getNormal(@Path("type") String type,
                               @Path("count") int count,
                               @Path("page") int page);
}
