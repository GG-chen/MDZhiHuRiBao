package com.example.shinelon.mymdapp;

import android.app.Application;

import com.example.shinelon.mymdapp.db.DBService;

/**
 * Created by Shinelon on 2017/2/6.
 */

public class MyApplication extends Application {
    private DBService dbService;

    @Override
    public void onCreate() {
        super.onCreate();
        dbService = new DBService(getApplicationContext());
    }

    public DBService getDbService() {
        return dbService;
    }

}
