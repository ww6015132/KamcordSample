package com.example.weiwang.kamcordsample.Retrofit;

import com.example.weiwang.kamcordsample.Models.GroupModel;
import com.example.weiwang.kamcordsample.Models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface RetrofitInterface {

    @Headers({"Content-Type: application/json", "Accept: application/json", "device-token: abc123", "accept-language:en", "client-name:android"})
    @GET("v1/feed/set/featuredShots")
    Call<ResponseModel> getFeed(@Query("count") Integer count);

    @Headers({"Content-Type: application/json", "Accept: application/json", "device-token: abc123", "accept-language:en", "client-name:android"})
    @GET("v1/feed/{param}")
    Call<GroupModel> getPagenationFeed(@Path("param") String url, @Query("count") Integer count, @Query("page") String key);

}
