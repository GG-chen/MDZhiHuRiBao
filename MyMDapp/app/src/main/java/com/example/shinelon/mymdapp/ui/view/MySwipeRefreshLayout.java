package com.example.shinelon.mymdapp.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Shinelon on 2017/2/2.
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    /*@Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d("Layout", "onScrollChanged!! ");
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollChangeListener != null) {
            scrollChangeListener.setSrollChanged(l, t, oldl, oldt);
        }
    }
    private ScrollChangeListener scrollChangeListener;
    public void setScrollChangeListener(ScrollChangeListener scrollChangeListener) {
        this.scrollChangeListener = scrollChangeListener;
    }

    public interface ScrollChangeListener {
        void setSrollChanged(int l, int t, int oldl, int oldt);
    }*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
