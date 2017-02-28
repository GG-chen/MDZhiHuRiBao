package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ThemeRadioGroup extends RadioGroup implements ThemeUIInterface {
    public ThemeRadioGroup(Context context) {
        super(context);
    }

    public ThemeRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {

    }
}
