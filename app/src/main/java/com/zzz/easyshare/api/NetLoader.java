package com.zzz.easyshare.api;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @创建者 zlf
 * @创建时间 2016/8/31 15:11
 */
public interface NetLoader {

    @GET("{path}")
    Call<ResponseBody> getData(@Path("path") String path);

    @GET("{path}")
    Call<ResponseBody> getData(@Path("path") String path, @QueryMap Map<String, String> map);

    @GET("{path1}/{path2}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2);

    @GET("{path1}/{path2}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2
            , @QueryMap Map<String, String> map);

    @GET("{path1}/{path2}/{path3}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2,
                               @Path("path3") String path3);

    @GET("{path1}/{path2}/{path3}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2,
                               @Path("path3") String path3, @QueryMap Map<String, String> map);

    @GET("{path1}/{path2}/{path3}/{path4}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2,
                               @Path("path3") String path3, @Path("path4") String path4);

    @GET("{path1}/{path2}/{path3}/{path4}")
    Call<ResponseBody> getData(@Path("path1") String path1, @Path("path2") String path2,
                               @Path("path3") String path3, @Path("path4") String path4,
                               @QueryMap Map<String, String> map);
}