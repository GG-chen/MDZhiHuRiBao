package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.TuringBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Shinelon on 2017/3/6.
 */
public interface TuringService {
    @GET("api")
    Observable<TuringBean> getData(@Query("key") String key, @Query("info") String info);

}
