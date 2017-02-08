package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.util.List;

/**
 * Created by Shinelon on 2017/2/1.
 */

public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RefreshRecyclerAdapter.MyViewHolder> implements View.OnClickListener {
    public List<NewItemBean> list;
    public Context context;

    public RefreshRecyclerAdapter(Context context, List<NewItemBean> bean) {
        this.context = context;
        this.list = bean;
        Log.d("Adapter", "structure ");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(this);
        //Log.d("Adapter", ":onCreateViewHolder2 ");
        Log.d("ReflactText", "onCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.layout.setTag(list.get(position).getId());
        NewItemBean item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.beforeData.setVisibility(View.GONE);
        if (list.get(position).getDate() != null) {

            holder.beforeData.setVisibility(View.VISIBLE);
            holder.beforeData.setText(list.get(position).getDate());
        }
        //Log.d("Adapter", "onBindViewHolder: " );
        ImageUtils.getInstance().setImage(holder.imageView, item.getImages());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }

    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title;
        public View layout;
        public TextView beforeData;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.new_image);
            title = (TextView) itemView.findViewById(R.id.new_title);
            beforeData = (TextView) itemView.findViewById(R.id.before_data);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);

    }
}
