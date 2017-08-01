package com.example.shinelon.mymdapp.presenter;

import com.example.shinelon.mymdapp.ui.activity.BaseActivity;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class BasePretener<T> implements Presenter<T> {
    protected T mMvpView;


    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }

}
