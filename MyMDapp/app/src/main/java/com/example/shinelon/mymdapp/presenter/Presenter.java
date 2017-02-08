package com.example.shinelon.mymdapp.presenter;

/**
 * Created by Shinelon on 2017/1/31.
 */

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
