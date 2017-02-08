package com.example.shinelon.mymdapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shinelon on 2017/2/6.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "database.db";
    private static final int version = 1;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS welcome (id integer primary key autoincrement, text varchar(60), img varchar(60))");
        db.execSQL("CREATE TABLE IF NOT EXISTS news (id integer primary key autoincrement, json varchar(4000))");
        db.execSQL("CREATE TABLE IF NOT EXISTS beforenews (id integer primary key autoincrement, json varchar(4000))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS welcome");
        db.execSQL("DROP TABLE IF EXISTS news");
        db.execSQL("DROP TABLE IF EXISTS beforenews");
        onCreate(db);
    }
}
