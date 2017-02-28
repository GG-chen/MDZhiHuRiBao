package com.example.shinelon.mymdapp.utils;


import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ThemeUIUtil {
    public static void changeTheme(View rootView, Resources.Theme theme) {
        if (rootView instanceof ThemeUIInterface) {
            ((ThemeUIInterface) rootView).setTheme(theme);
            if (rootView instanceof ViewGroup) {
                int count = ((ViewGroup) rootView).getChildCount();
                for (int i = 0; i < count; i++) {
                    changeTheme(((ViewGroup) rootView).getChildAt(i), theme);
                }
            }
        }
    }
}
