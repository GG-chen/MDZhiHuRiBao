package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Shinelon on 2017/2/3.
 */

public interface NewDetailService {
    @GET("4/news/{id}")
    Observable<NewDetailBean> getNewDetail(@Path("id") int id);
}
