package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.WelfareBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Shinelon on 2017/3/4.
 */

public interface WelfareService {
    //获取福利图片
    @GET("10/{count}")
    Observable<WelfareBean> loadWelfare(@Path("count") String count);


}
