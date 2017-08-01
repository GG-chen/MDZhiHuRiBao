package com.example.shinelon.mymdapp;

import android.app.Application;
import android.content.Context;

import com.example.shinelon.mymdapp.utils.SPUtil;

/**
 * Created by Shinelon on 2017/2/6.
 */

public class MyApplication extends Application {
    private static  MyApplication myApplication;
    private static SPUtil mSpUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication  = this;
        mSpUtil = new SPUtil(this);
    }

    public static Context getContext() {
        return myApplication;
    }

    public static SPUtil getmSpUtil() {
        return mSpUtil;
    }

}
