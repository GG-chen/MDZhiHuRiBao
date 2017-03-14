package com.example.shinelon.mymdapp.modle.http;

import com.example.shinelon.mymdapp.modle.bean.JuheBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Shinelon on 2017/3/10.
 */

public interface JuheService {
    @GET("index")
    Observable<JuheBean> loadData(@Query("type") String type, @Query("key") String key);

}
