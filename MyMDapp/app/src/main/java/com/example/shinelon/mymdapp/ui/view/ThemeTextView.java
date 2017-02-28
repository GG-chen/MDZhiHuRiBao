package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ThemeTextView extends TextView implements ThemeUIInterface{
    private int attr_drawable = -1;
    private int attr_texcolor = -1;
    public ThemeTextView(Context context) {
        super(context);
    }

    public ThemeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttribute(attrs);
        this.attr_texcolor = ViewAttributeUtil.getTextColorAttribute(attrs);
    }

    public ThemeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttribute(attrs);
        this.attr_texcolor = ViewAttributeUtil.getTextColorAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        if (attr_drawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId,attr_drawable);
        }
        if (attr_texcolor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId,attr_texcolor);
        }

    }
}
