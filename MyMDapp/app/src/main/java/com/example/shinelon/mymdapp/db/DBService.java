package com.example.shinelon.mymdapp.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.bean.WelcomeBean;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Shinelon on 2017/2/6.
 */

public class DBService {
    private DBOpenHelper dbOpenHelper;

    public DBService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);

    }

    public void saveWelcome(WelcomeBean welcomeBean) {
        dbOpenHelper.getWritableDatabase().execSQL("insert into welcome (text, img) values (?, ?)", new Object[] {welcomeBean.getText(), welcomeBean.getImg()});

    }

    public void updateWelcome(WelcomeBean welcomeBean) {
        dbOpenHelper.getWritableDatabase().execSQL("update welcome set text=?, img=? where=?", new Object[] {welcomeBean.getText(), welcomeBean.getImg(), 1});

    }
    public WelcomeBean queryWelcome() {
        Cursor cursor = dbOpenHelper.getWritableDatabase().rawQuery("select text, img from welcome where id=?", new String[] {String.valueOf(1)});
        if (cursor.moveToNext()) {
            WelcomeBean welcomeBean = new WelcomeBean();
            welcomeBean.setText(cursor.getString(1));
            welcomeBean.setImg(cursor.getString(2));
            return  welcomeBean;
        }
        return null;
    }

    public void saveNews(String json) {
        Log.d("TEST！！", "saveNews: " + json);
        dbOpenHelper.getWritableDatabase().execSQL("insert into news (json) values (?)", new Object[] {json});

    }
    public void dropNews() {
        dbOpenHelper.getWritableDatabase().execSQL("delete from news");

    }
    public String queryNews() {
        Cursor cursor = dbOpenHelper.getWritableDatabase().rawQuery("select json from news where id=?", new String[] {String.valueOf(1)});
        if (cursor.moveToNext()) {
            String json = cursor.getString(0);
            Log.d("TEST！！", "queryNews: " + json);
            return json;
        }
        return null;
    }

    public void saveBeforeNews(String json) {
        Log.d("TEST！！", "saveBeforeNews: " + json);
        dbOpenHelper.getWritableDatabase().execSQL("insert into beforenews (json) values (?)", new Object[] {json});

    }

    public void dropBeforeNews() {
        dbOpenHelper.getWritableDatabase().execSQL("delete from beforenews");

    }
    public List<String> queryBeforeNews() {
        List<String> list = new ArrayList<>();
        Cursor cursor = dbOpenHelper.getWritableDatabase().rawQuery("select * from beforenews", null);
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (cursor.moveToNext()) {
                String json = cursor.getString(1);
                Log.d("TEST！！", "queryBeforeNews: " + json + cursor.getString(0));
                list.add(json);
            }
        }
        return list;
    }
}
