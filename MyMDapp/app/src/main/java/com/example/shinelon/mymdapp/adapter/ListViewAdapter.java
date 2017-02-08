package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.util.List;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class ListViewAdapter extends BaseAdapter {
    public List<NewItemBean> list;
    LayoutInflater layoutInflater;
    public Context context;

    public ListViewAdapter(List<NewItemBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.new_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.new_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.new_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).getTitle());
        ImageUtils.getInstance().setImage(viewHolder.imageView, list.get(position).getImages());

        return convertView;
    }
    public class ViewHolder {
        public ImageView imageView;
        public TextView title;

        public ViewHolder(TextView title, ImageView imageView) {
            this.title = title;
            this.imageView = imageView;
        }
    }
}
