package com.example.shinelon.mymdapp.modle.http.utils;

import android.content.Context;
import android.util.Log;


import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.utils.MyUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Shinelon on 2017/1/31.
 */

public class RetrofitUtils {
    public static Retrofit retrofit;
    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "cache");
    private static final Interceptor INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response originalResponse = chain.proceed(request);
            //int maxAge = 60; // 在线缓存在1分钟内可读取
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(10, TimeUnit.DAYS).build();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();


        }
    };
    private static final Interceptor INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!MyUtils.isNetworkAvailable(MyApplication.getContext())) {
                CacheControl cacheControl = new CacheControl.Builder()
                        //如果使用频繁就多加一天
                        .maxStale(1, TimeUnit.DAYS).build();
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
                Log.d("RetrofitUtils", "intercept: 现在没有网络");
            }
            return chain.proceed(request);

        }
    };
    private static int cacheSzie = 20 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDirectory, cacheSzie);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(INTERCEPTOR)
            .addInterceptor(INTERCEPTOR_OFFLINE)
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .cache(cache)
            .build();


    public static <T> T createApi(Context context, Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl("http://news-at.zhihu.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                    retrofit = builder.build();
                }
            }
        }
        return retrofit.create(clazz);
    }


}
