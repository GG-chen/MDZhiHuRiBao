package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ThemeLinearLayout extends LinearLayout implements ThemeUIInterface{
    private int mAttrBackground = -1;

    public ThemeLinearLayout(Context context) {
        super(context);
    }

    public ThemeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAttrBackground = ViewAttributeUtil.getBackgroundAttribute(attrs);
    }

    public ThemeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mAttrBackground = ViewAttributeUtil.getBackgroundAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (mAttrBackground != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this,themeId, mAttrBackground);
        }
    }
}
