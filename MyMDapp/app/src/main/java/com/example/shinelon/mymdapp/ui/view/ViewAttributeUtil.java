package com.example.shinelon.mymdapp.ui.view;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.shinelon.mymdapp.ui.ThemeUIInterface;

/**
 * Created by Shinelon on 2017/2/28.
 */

public class ViewAttributeUtil {
    public static int getBackgroundAttribute(AttributeSet attributeSet) {
        return getAttributeValue(attributeSet, android.R.attr.background);
    }

    public static int getTextColorAttribute(AttributeSet attributeSet) {
        return getAttributeValue(attributeSet, android.R.attr.textColor);
    }

    private static int getAttributeValue(AttributeSet attributeSet, int background) {
        int value = -1;
        int count = attributeSet.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (attributeSet.getAttributeNameResource(i) == background) {
                String str = attributeSet.getAttributeValue(i);
                if (null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1, str.length())).intValue();
                    return value;
                }
            }
        }
        return value;
    }

    public static void applyBackgroundDrawable(ThemeUIInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != drawable) {
            (ci.getView()).setBackgroundDrawable(drawable);
        }
        ta.recycle();
    }
    public static void applyTextColor(ThemeUIInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getColor(0, 0);
        if (null != ci && ci instanceof TextView) {

            ((TextView)ci.getView()).setTextColor(resourceId);
        }
        ta.recycle();
    }
}
