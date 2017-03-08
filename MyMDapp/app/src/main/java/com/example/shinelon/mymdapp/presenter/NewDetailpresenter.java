package com.example.shinelon.mymdapp.presenter;

import android.content.Context;

import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.modle.http.NewDetailService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.HomeFrg;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class NewDetailpresenter extends BasePresenter<HomeFrg> {

    private NewDetailService newDetailService;
    public NewDetailpresenter(Context context) {
        newDetailService = RetrofitUtils.createApi(context, NewDetailService.class);
    }
    public void loadNewsList(int id) {

        Subscription subscription = newDetailService.getNewDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewDetailBean newDetailBean) {
                        mMvpView.loadNewDetail(newDetailBean);

                    }
                });

    }

    @Override
    public void attachView(HomeFrg mvpView) {
        super.attachView(mvpView);
    }
}
