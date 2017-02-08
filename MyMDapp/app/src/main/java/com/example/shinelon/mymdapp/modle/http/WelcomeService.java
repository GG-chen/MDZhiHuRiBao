package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.WelcomeBean;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Shinelon on 2017/1/31.
 */

public interface WelcomeService {
    @GET("4/start-image/{IMG_SIZE}")
    Observable<WelcomeBean> getWelcomeImg(@Path("IMG_SIZE") String size);
}
