package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.MovieBean;
import com.example.shinelon.mymdapp.modle.bean.WelfareBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Shinelon on 2017/3/5.
 */

public interface MovieService {
    @GET("1/{count}")
    Observable<MovieBean> loadWelfare(@Path("count") String count);
}
