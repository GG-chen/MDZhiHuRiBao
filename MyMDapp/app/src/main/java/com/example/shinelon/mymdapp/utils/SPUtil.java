package com.example.shinelon.mymdapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class SPUtil {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public SPUtil(Context context) {
        sharedPreferences = context.getSharedPreferences("theme", Context.MODE_PRIVATE);

    }
    public  void put(String theme, String data) {
        editor = sharedPreferences.edit();
        editor.putString(theme, data);
        editor.commit();
    }

    public String get(String theme, String data) {
        String saveTheme = sharedPreferences.getString(theme, data);
        return saveTheme;
    }
}
