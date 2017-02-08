package com.example.shinelon.mymdapp.modle.http.utils;

import android.content.Context;

import retrofit.GsonConverterFactory;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Shinelon on 2017/1/31.
 */

public class RetrofitUtils {
    public static Retrofit retrofit;

    public static <T> T createApi(Context context, Class<T> clazz) {
        ScalarsConverterFactory scalarsConverterFactory = ScalarsConverterFactory.create();
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl("http://news-at.zhihu.com/api/")
                            //.addConverterFactory(scalarsConverterFactory)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    retrofit = builder.build();
                }
            }
        }
        return retrofit.create(clazz);
    }



}
