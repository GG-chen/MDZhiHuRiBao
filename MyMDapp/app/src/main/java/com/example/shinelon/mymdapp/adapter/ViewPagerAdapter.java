package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;


import com.example.shinelon.mymdapp.ui.activity.NewDetailActivity;

import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/2/1.
 */

public class ViewPagerAdapter extends PagerAdapter {

    public ArrayList<View> views;
    private Context context;

    public ViewPagerAdapter(Context context, ArrayList<View> views) {
        this.views = views;
        this.context = context;
        Log.d("PagerAdapter", "structure ");
    }

    @Override
    public int getCount() {
        //Log.d("PagerAdapter", "count " + views.size());
        //return views.size();
        if (views.size() == 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {

            //Log.d("PagerAdapter", "instantiateItem!!");

        position %= views.size();
        if (position<0){
            position = views.size()+position;
        }
        final ImageView imageView = (ImageView) views.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewDetailActivity.class);
                intent.putExtra("newId", (Integer) imageView.getTag());
                context.startActivity(intent);
            }
        });
        ViewParent viewParent = imageView.getParent();
        if (viewParent!=null){
            ViewGroup parent = (ViewGroup)viewParent;
            parent.removeView(imageView);
        }

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(views.get(position));
    }


}
