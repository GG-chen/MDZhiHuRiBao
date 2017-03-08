package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shinelon.mymdapp.TuringParams;
import com.example.shinelon.mymdapp.modle.bean.TuringBean;
import com.example.shinelon.mymdapp.modle.bean.WelfareBean;
import com.example.shinelon.mymdapp.modle.http.TuringService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.TuringFrg;

import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/3/6.
 */

public class TuringPresenter extends BasePresenter<TuringFrg> {
    private Context context;
    private TuringService turingService;

    public TuringPresenter(Context context) {
        this.context = context;
        turingService = RetrofitUtils.turingService;
    }

    public void loadData(String text) {
        turingService.getData(TuringParams.TULING_KEY, text)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Observer<TuringBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TuringFragment", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(TuringBean bean) {
                        Log.d("TuringFragment", "onNext: " );
                        mMvpView.loadData(bean);
                    }
                });

    }

    @Override
    public void attachView(TuringFrg mvpView) {
        super.attachView(mvpView);
    }
}
