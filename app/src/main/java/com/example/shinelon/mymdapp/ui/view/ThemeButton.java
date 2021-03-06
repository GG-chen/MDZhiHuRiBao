package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ThemeButton extends Button implements ThemeUIInterface {
    private int mAttrDrawable = -1;
    private int mAttrTexcolor = -1;
    public ThemeButton(Context context) {
        super(context);
    }

    public ThemeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mAttrDrawable = ViewAttributeUtil.getBackgroundAttribute(attrs);
        this.mAttrTexcolor = ViewAttributeUtil.getTextColorAttribute(attrs);
    }

    public ThemeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mAttrDrawable = ViewAttributeUtil.getBackgroundAttribute(attrs);
        this.mAttrTexcolor = ViewAttributeUtil.getTextColorAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (mAttrDrawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, mAttrDrawable);
        }
        if (mAttrTexcolor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId, mAttrTexcolor);
        }

    }
}
