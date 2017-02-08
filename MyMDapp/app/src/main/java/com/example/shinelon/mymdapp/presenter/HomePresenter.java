package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.HomeService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.HomeFrg;
import com.example.shinelon.mymdapp.utils.MyUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class HomePresenter extends BasePretener<HomeFrg> {

    private final MyApplication my;
    private HomeService homeService;
    private Context context;
    public HomePresenter(Context context) {
        homeService = RetrofitUtils.createApi(context, HomeService.class);
        this.context = context;
        my = (MyApplication) context.getApplicationContext();
    }
    public void loadNewsList(String date) {
        if (MyUtils.isNetworkAvailable(context)) {
            Call<ResponseBody > call = homeService.getNewsList(date);
            call.enqueue(new Callback<ResponseBody >() {
                @Override
                public void onResponse(Response<ResponseBody > response, Retrofit retrofit) {

                    String json = null;
                    try {
                        json = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    my.getDbService().saveBeforeNews(json);
                    mMvpView.loadMore(getJson2Object(json));

                }

                @Override
                public void onFailure(Throwable t) {

                }

            });
        } else {
            //TODO
        }


    }

    public void loadLastNewsList() {
        if (MyUtils.isNetworkAvailable(context)) {
            Log.d("TEST！！", "loadLastNewsList: ");
            Call<ResponseBody> call = homeService.getNewsList();
            call.enqueue(new Callback<ResponseBody >() {
                @Override
                public void onResponse(Response<ResponseBody > response, Retrofit retrofit) {
                    String json = null;
                    try {
                        json = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    my.getDbService().saveNews(json);
                    Log.d("TEST！！", "loadLastNewsList: " + json);
                    mMvpView.refresh(getJson2Object(json));
                    Log.d("TEST！！", "onResponse: ");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("TEST！！", "onFailure: " + t.toString());

                }
            });
        } else {
            Toast.makeText(context, "没有网络！！" , Toast.LENGTH_SHORT).show();
            loadNewsListFromDB();
        }


    }

    private NewsListBean getJson2Object(String json) {
        // Log.d("TEST！！", "getJson2Object: " + json);
        Gson gson = new Gson();
        NewsListBean bean = gson.fromJson(json, NewsListBean.class);
        Log.d("TEST！！", "getJson2Object: " + bean.getDate());
        return bean;
    }

    public void loadNewsListFromDB() {
        String lastNews = my.getDbService().queryNews();
        Log.d("数据库", "loadNewsListFromDB: " + lastNews);
        if (lastNews != null) {
            mMvpView.refresh(getJson2Object(lastNews));
        }
       loadBeforeNewsFromDB();

    }

    public void loadBeforeNewsFromDB() {
        List<String> beforeNews = my.getDbService().queryBeforeNews();
        if (beforeNews != null) {
            for (int i = 0; i < beforeNews.size(); i++) {
                //Log.d("数据库", "loadBeforeNewsListFromDB: " + beforeNews.get(i).toString());
                mMvpView.loadMore(getJson2Object(beforeNews.get(i).toString()));
            }
        }
    }


    private NewsListBean getJson2ObjectBefore(String json) {
        Gson gson = new Gson();
        NewsListBean bean = gson.fromJson(json, NewsListBean.class);
        return bean;
    }




    @Override
    public void attachView(HomeFrg mvpView) {
        super.attachView(mvpView);
    }
}
