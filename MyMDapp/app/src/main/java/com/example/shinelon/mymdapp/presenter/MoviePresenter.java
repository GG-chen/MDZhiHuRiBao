package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.support.annotation.MainThread;

import com.example.shinelon.mymdapp.modle.bean.MovieBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.MovieService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.ui.fragment.MovieFrg;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/3/5.
 */

public class MoviePresenter extends BasePresenter<MovieFrg> {
    private Context context;
    private MovieService movieService;


    public MoviePresenter(Context context) {
        this.context = context;
        movieService = RetrofitUtils.movieService;
    }

    public void getMovie(String count) {
        Subscription subscription = movieService.loadWelfare(count)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Observer<MovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        mMvpView.lodeMovie(movieBean);

                    }
                });
    }

    @Override
    public void attachView(MovieFrg mvpView) {
        super.attachView(mvpView);
    }
}
