package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.WelfareBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/3/4.
 */
public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.WelfareHolder>{
    private List<WelfareBean.WelfareItem> mList = new ArrayList<>();
    private Context mContext;
    private OnItemTouch mOnItemTouch;
    private Boolean isScroll = false;

    public WelfareAdapter(List<WelfareBean.WelfareItem> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public WelfareHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.welfare_item, parent, false);
        return new WelfareHolder(view);
    }

    @Override
    public void onBindViewHolder(WelfareHolder holder, final int position) {
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemTouch.onTouch(v, mList.get(position).getImageUrl(), mList.get(position).getDesc());
            }
        });
        if (!isScroll) {
            ImageUtils.getInstance().setImage(holder.imageView, mList.get(position).getImageUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class WelfareHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public WelfareHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.welfare_id);

        }
    }

    public void addItem(List<WelfareBean.WelfareItem> item) {
        if (item != null) {
           // mList.clear();
            mList.addAll(item);
        }
        //notifyDataSetChanged();
        Log.d("WelfareAdapter", "addItem: ");

    }

    public Boolean getScoll() {
        return isScroll;
    }

    public void setScoll(Boolean scroll) {
        if (scroll != isScroll)
        isScroll = scroll;
    }

    public void setOnTouchListener(OnItemTouch onTouchListener) {
        this.mOnItemTouch = onTouchListener;
    }
    public interface OnItemTouch{
        void onTouch(View view, String url, String name);
    }
}
