package com.example.shinelon.mymdapp.modle.http;


import com.squareup.okhttp.ResponseBody;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Shinelon on 2017/1/31.
 */

public interface HomeService {
    //获取最新新闻
    @GET("4/news/latest")
    Call<ResponseBody> getNewsList();
    //获取过往新闻
    @GET("4/news/before/{date}")
    Call<ResponseBody>  getNewsList(@Path("date") String data);
}
