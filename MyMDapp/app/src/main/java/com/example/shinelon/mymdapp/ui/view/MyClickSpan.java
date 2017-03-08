package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Shinelon on 2017/3/7.
 */

public class MyClickSpan extends ClickableSpan {
    private String text;
    private Context context;


    public MyClickSpan(String text, Context context) {
        this.text = text;
        this.context = context;
    }

    @Override
    public void onClick(View widget) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_uri = Uri.parse(text);
        intent.setData(content_uri);
        //指定打开的浏览器  这里选择自带的浏览器
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        context.startActivity(intent);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.BLUE);
        ds.setUnderlineText(true);
    }
}
