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
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/2/1.
 */

public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RefreshRecyclerAdapter.MyViewHolder> implements View.OnClickListener {
    private static final int NOMAL_ITEM = 1;
    private static final int LODING_ITEM = 0;
    private boolean isLoading = false;
    public List<NewsListBean.Stories> mList = new ArrayList<>();
    public Context mContext;

    public RefreshRecyclerAdapter(Context context) {
        this.mContext = context;
        Log.d("Adapter", "structure ");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NOMAL_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false);
                MyViewHolder holder = new MyViewHolder(view);
                view.setOnClickListener(this);
                return holder;
            case LODING_ITEM:
                View lodingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_layout, parent, false);
                MyViewHolder lodingHolder = new MyViewHolder(lodingView);
                return  lodingHolder;
            }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == NOMAL_ITEM) {

            onBindNomalItem(holder, position);
        } else {
            onBindLoadingItem(holder);
        }
    }

    private void onBindLoadingItem(MyViewHolder holder) {
        holder.layout.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void onBindNomalItem(MyViewHolder holder, int position) {
        holder.layout.setTag(mList.get(position).getId());
        NewsListBean.Stories item = mList.get(position);
        holder.title.setText(item.getTitle());
        holder.beforeData.setVisibility(View.GONE);
        if (item.getDate() != null) {

            holder.beforeData.setVisibility(View.VISIBLE);
            holder.beforeData.setText(item.getDate());
        }
        ImageUtils.getInstance().setImage(holder.imageView, item.getImages()[0]);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int)v.getTag());
        }

    }

    public void addItem(List<NewsListBean.Stories> stories) {
        if (stories != null) {
            Log.d("addItem", "addItem: " + stories.size());
            mList.addAll(stories);
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

    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount() && getItemCount() > 0) {
            return NOMAL_ITEM;
        }
        return LODING_ITEM;
    }
    public void startLoding() {
        if (isLoading) {
            return;
        }
        isLoading = true;
    }
    public void finishLoding() {
        if (!isLoading) {
            return;
        }
        isLoading = false;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);

    }
}
