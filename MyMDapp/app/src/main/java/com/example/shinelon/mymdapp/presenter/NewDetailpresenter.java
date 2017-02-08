package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.HomeService;
import com.example.shinelon.mymdapp.modle.http.NewDetailService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.HomeFrg;

import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class NewDetailpresenter extends BasePretener<HomeFrg> {

    private NewDetailService newDetailService;
    public NewDetailpresenter(Context context) {
        newDetailService = RetrofitUtils.createApi(context, NewDetailService.class);
    }
    public void loadNewsList(int id) {

        newDetailService.getNewDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<NewDetailBean>() {
                    @Override
                    public void onCompleted() {
                         //mMvpView.log("onCompleted!!");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        // mMvpView.log(throwable.getMessage());
                    }

                    @Override
                    public void onNext(NewDetailBean newDetailBean) {
                        Log.d("DetailNew", newDetailBean.getTitle());
                        mMvpView.loadNewDetail(newDetailBean);

                    }
                });

    }

    @Override
    public void attachView(HomeFrg mvpView) {
        super.attachView(mvpView);
    }
}
