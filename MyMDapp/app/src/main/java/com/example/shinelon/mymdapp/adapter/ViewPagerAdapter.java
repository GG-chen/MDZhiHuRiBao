package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;


import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.ui.activity.NewDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/2/1.
 */

public class ViewPagerAdapter extends PagerAdapter {

    public ArrayList<NewsListBean.TopStoried> list = new ArrayList<>();
    private List<ImageView> views = new ArrayList<>();
    private Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
        Log.d("PagerAdapter", "structure ");
    }

    @Override
    public int getCount() {
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
        position %= list.size();
        if (position < 0) {
            position = list.size() + position;
        }
        ImageView imageView = views.get(position);
        ImageUtils.getInstance().setImage(imageView, list.get(position).getImages());
        Log.d("instantiateItem", "instantiateItem: " + position);
        ViewParent viewParent = imageView.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(views.get(position));
    }

    public void addItem(List<NewsListBean.TopStoried> topStorieds) {
        if (topStorieds != null) {
            views.clear();
            list.clear();
            Log.d("addItem", "addItem: topStorieds " + topStorieds.size());
            list.addAll(topStorieds);
            for (int i = 0; i < topStorieds.size(); i++) {
                final NewsListBean.TopStoried topStoried = topStorieds.get(i);
                ImageView imageView = new ImageView(context);
                imageView.setBackgroundResource(R.drawable.ic_new);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewDetailActivity.class);
                        intent.putExtra("newId", topStoried.getId());
                        context.startActivity(intent);
                    }
                });
                views.add(imageView);
            }


        }

    }


}
