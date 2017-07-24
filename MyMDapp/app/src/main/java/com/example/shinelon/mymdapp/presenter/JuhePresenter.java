package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shinelon.mymdapp.JuheParams;
import com.example.shinelon.mymdapp.modle.bean.JuheBean;
import com.example.shinelon.mymdapp.modle.http.JuheService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.fragment.JuheFrg;

import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/3/10.
 */

public class JuhePresenter extends BasePresenter<JuheFrg> {
    private Context mContext;
    private JuheService mJuheService;

    public JuhePresenter(Context context) {
        this.mContext = context;
        mJuheService = RetrofitUtils.mJuheService;
    }

    public void loadData(final String type) {
        mJuheService.loadData(type, JuheParams.JUHE_APIKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Observer<JuheBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("JuhePresenter", "onError: " + e);
                    }

                    @Override
                    public void onNext(JuheBean bean) {
                        Log.d("JuhePresenter", "onNext: " );
                        mMvpView.loadData(bean);
                    }
                });

    }

    @Override
    public void attachView(JuheFrg mvpView) {
        super.attachView(mvpView);
    }
}
