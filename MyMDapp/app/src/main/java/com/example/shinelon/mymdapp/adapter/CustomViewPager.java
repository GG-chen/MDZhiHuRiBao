package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.ui.activity.NewDetailActivity;
import com.example.shinelon.mymdapp.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/3/7.
 */

public class CustomViewPager extends PagerAdapter {
    public static List<NewsListBean.TopStoried> list = new ArrayList<>();
    private Context context;

    public CustomViewPager(Context context, List<NewsListBean.TopStoried> list) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % HomeFragment.VIEWPAGER_COUNT;
        if (position >= HomeFragment.VIEWPAGER_COUNT || position < 0) {
            position = 0;
        }
        Log.d("CustomViewPager", "instantiateItem: position: " + position);
        Log.d("CustomViewPager", "instantiateItem: list.size: " + list.size());
        View item = View.inflate(context, R.layout.header_item, null);

        ImageView imageView = (ImageView) item.findViewById(R.id.header_img);
        TextView title = (TextView) item.findViewById(R.id.header_text);
        try {

            ImageUtils.getInstance().setImage(imageView, list.get(position).getImages());
            title.setText(list.get(position).getTitle());
        } catch (Exception e) {
            e.toString();
        }
        final int finalPosition = position;
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewDetailActivity.class);
                intent.putExtra("newId", list.get(finalPosition).getId());
                context.startActivity(intent);
            }
        });
        container.addView(item);
        return item;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void addList(List<NewsListBean.TopStoried> item) {
        Log.d("CustomViewPager", "addList: " + item.size());
        if (list != null) {
            list.clear();
            this.list = item;
            Log.d("CustomViewPager", "addList: " + list.size());
            list.notify();
        }
        //notifyDataSetChanged();
    }

}
