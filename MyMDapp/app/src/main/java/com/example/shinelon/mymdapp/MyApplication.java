package com.example.shinelon.mymdapp;

import android.app.Application;
import android.content.Context;

import com.example.shinelon.mymdapp.db.DBService;

/**
 * Created by Shinelon on 2017/2/6.
 */

public class MyApplication extends Application {
    private DBService dbService;
    private static  MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication  = this;
        dbService = new DBService(getApplicationContext());
    }

    public DBService getDbService() {
        return dbService;
    }
    public static Context getContext() {
        return myApplication;
    }

}
