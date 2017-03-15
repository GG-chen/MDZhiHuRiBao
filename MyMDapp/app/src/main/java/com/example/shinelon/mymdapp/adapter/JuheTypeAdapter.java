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
import com.example.shinelon.mymdapp.modle.bean.JuheBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shinelon on 2017/3/10.
 */

public class JuheTypeAdapter extends RecyclerView.Adapter<JuheTypeAdapter.MyViewHolder> implements View.OnClickListener {
    private static final int NOMAL_ITEM = 1;
    private static final int LODING_ITEM = 0;
    private Context context;
    private List<JuheBean.JuheResult.JuHeItem> items = new ArrayList<JuheBean.JuheResult.JuHeItem>();

    public JuheTypeAdapter() {
    }

    @Override
    public JuheTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        Log.d("JuheFragment", "onCreateViewHolder: " + viewType);
        switch (viewType) {
            case NOMAL_ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.juhe_item, parent, false);
                JuheTypeAdapter.MyViewHolder holder = new JuheTypeAdapter.MyViewHolder(view);
                view.setOnClickListener(this);
                return holder;
            case LODING_ITEM:
                View lodingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_nomore_layout, parent, false);
                JuheTypeAdapter.MyViewHolder lodingHolder = new JuheTypeAdapter.MyViewHolder(lodingView);
                return  lodingHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(JuheTypeAdapter.MyViewHolder holder, int position) {
        Log.d("JuheFragment", "onBindViewHolder:11111111 " + items.get(0).getCategory());
        int type = getItemViewType(position);
        if (type == NOMAL_ITEM) {
            onBindNomalItem(holder, position);
        } else {
            onBindLoadingItem(holder);
        }
    }

    private void onBindLoadingItem(JuheTypeAdapter.MyViewHolder holder) {
        holder.layout.setVisibility(View.VISIBLE);
    }

    private void onBindNomalItem(JuheTypeAdapter.MyViewHolder holder, int position) {
        holder.layout.setTag(position);
        holder.title.setText(items.get(position).getTitle());
        ImageUtils.getInstance().setImage(holder.imageView, items.get(position).getThumbnail_pic_s());

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getItemCount()-1 && getItemCount() > 0) {
            return NOMAL_ITEM;
        }
        return LODING_ITEM;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }

    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title;
        public View layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.juhe_new_image);
            title = (TextView) itemView.findViewById(R.id.juhe_new_title);
        }
    }
    public void setData(List<JuheBean.JuheResult.JuHeItem> items) {
        Log.d("JuheFragment", "setData:11111111 " + items.get(0).getCategory());
        this.items.clear();
        Log.d("JuheFragment", "setData: 22222222" + items.get(0).getCategory());
        this.items.addAll(items);
        Log.d("JuheFragment", "setData: 33333333333" + items.get(0).getCategory());
       // notifyDataSetChanged();

    }

    public void setOnItemClickListener(OnViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    private OnViewItemClickListener mOnItemClickListener = null;
    public  interface OnViewItemClickListener {
        void onItemClick(View view , int position);

    }

    public List<JuheBean.JuheResult.JuHeItem> getItems() {
        return items;
    }
}
