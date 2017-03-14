package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shinelon.mymdapp.modle.bean.WelfareBean;
import com.example.shinelon.mymdapp.modle.http.WelfareService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.WelfareFrg;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/3/4.
 */
public class WelfarePresenter extends BasePresenter<WelfareFrg> {
    private final WelfareService welfareService;
    private Context context;

    public WelfarePresenter(Context context) {
        this.context = context;
        //welfareService = RetrofitUtils.createWelfare(context, WelfareService.class);
        welfareService = RetrofitUtils.welfareService;
    }

    public void loadWelfare(String count) {
        Log.d("WelfareFragment", "loadWelfare");
        welfareService.loadWelfare(count)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Observer<WelfareBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("WelfareFragment", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WelfareFragment", "onError" + e.toString());
                    }

                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        Log.d("WelfareFragment", "onNext" + welfareBean.getResults().get(0).getDesc());
                      mMvpView.loadMore(welfareBean);
                    }
                });
    }

    @Override
    public void attachView(WelfareFrg mvpView) {
        super.attachView(mvpView);
    }
}
