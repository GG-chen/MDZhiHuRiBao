package com.example.shinelon.mymdapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class SPUtil {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public SPUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);

    }
    public  void put(String theme, String data) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(theme, data);
        mEditor.commit();
    }

    public String get(String theme, String data) {
        String saveTheme = mSharedPreferences.getString(theme, data);
        return saveTheme;
    }
}
