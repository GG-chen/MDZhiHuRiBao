package com.example.shinelon.mymdapp.modle.http;


import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.squareup.okhttp.ResponseBody;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Shinelon on 2017/1/31.
 */

public interface HomeService {
    //获取最新新闻
    @GET("4/news/latest")
    Observable<NewsListBean> getNewsList();
    //获取过往新闻
    @GET("4/news/before/{date}")
    Observable<NewsListBean>  getNewsList(@Path("date") String date);
}
