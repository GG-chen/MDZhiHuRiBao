package com.example.shinelon.mymdapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.TuringParams;
import com.example.shinelon.mymdapp.modle.bean.TuringBean;
import com.example.shinelon.mymdapp.ui.view.MyClickSpan;
import com.github.library.bubbleview.BubbleTextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shinelon on 2017/3/6.
 */
public class TuringAdapter extends BaseAdapter {
    private List<TuringBean> mList = new ArrayList<>();
    private Context mContext;

    public TuringAdapter(List<TuringBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getType() != TuringParams.TYPE_LEFT) {
            return TuringParams.TYPE_RIGHT;
        } else {
            return TuringParams.TYPE_LEFT;
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TuringHolder holder;
        if (convertView == null) {
            holder = new TuringHolder();
            convertView = getConverViewByType(position);
            holder.bubbleTextView = (BubbleTextView) convertView.findViewById(R.id.edit_context);
            holder.circleImageView = (CircleImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        } else {
            holder = (TuringHolder) convertView.getTag();
        }
        setData(position, holder);
        return convertView;
    }

    private void setData(int position, TuringHolder holder) {
        TuringBean bean = mList.get(position);
        if (bean.getType() == TuringParams.TYPE_RIGHT) {
            holder.bubbleTextView.setText(bean.getText());
            return;
        }
        switch (bean.getCode()) {
            //文字类
            case TuringParams.TulingCode.TEXT:
                holder.bubbleTextView.setText(bean.getText());
                break;
            //链接类
            case TuringParams.TulingCode.URL:
                holder.bubbleTextView.setText(bean.getText() + "\n");
                SpannableString spannableStringUrl = setUrlData(bean.getUrl());
                holder.bubbleTextView.append(spannableStringUrl);
                holder.bubbleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            //新闻类
            case TuringParams.TulingCode.NEWS:
                holder.bubbleTextView.setText(bean.getText() + "\n");
                for (int i = 0; i < bean.getList().size(); i++) {
                    holder.bubbleTextView.append(bean.getList().get(i).getArticle() + "\n");
                    SpannableString spannableStringNews = setUrlData(bean.getList().get(i).getDetailurl());
                    holder.bubbleTextView.append(spannableStringNews);
                    holder.bubbleTextView.append("\n\n");
                }
                holder.bubbleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            //列车类
            case TuringParams.TulingCode.TRAIN:
                holder.bubbleTextView.setText(bean.getText() + "\n");
                SpannableString spannableStringTrain = setUrlData(bean.getUrl());
                holder.bubbleTextView.append(spannableStringTrain);
                holder.bubbleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            //航班类
            case TuringParams.TulingCode.PLANE:
                holder.bubbleTextView.setText(bean.getText() + "\n");
                SpannableString spannableStringPlane = setUrlData(bean.getUrl());
                holder.bubbleTextView.append(spannableStringPlane);
                holder.bubbleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            //菜谱、视频、小说类
            case TuringParams.TulingCode.VIDEO:
                holder.bubbleTextView.setText(bean.getText() + "  " + bean.getList().get(0).getName()+ "\n");
                SpannableString spannableStringVideo = setUrlData(bean.getList().get(0).getDetailurl());
                holder.bubbleTextView.append(spannableStringVideo);
                holder.bubbleTextView.append("\n");
                holder.bubbleTextView.append(bean.getList().get(0).getInfo());
                holder.bubbleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                break;
            case TuringParams.TulingCode.APP:
            case TuringParams.TulingCode.HOTEL:
            case TuringParams.TulingCode.PLACE:
                holder.bubbleTextView.setText("小主， 对不起哦，我不太懂。。。");
                break;

        }
    }

    @NonNull
    private SpannableString setUrlData(String url) {
        SpannableString spannableString = new SpannableString(url);
        MyClickSpan myClickSpan = new MyClickSpan(url, mContext);
        spannableString.setSpan(myClickSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private View getConverViewByType(int position) {
        View convertView;
        if (mList.get(position).getType() == TuringParams.TYPE_LEFT) {
            convertView = View.inflate(mContext, R.layout.turing_left, null);
        } else {
            convertView = View.inflate(mContext, R.layout.turing_right, null);

        }
        return convertView;
    }

    public class TuringHolder {
        public CircleImageView circleImageView;
        public BubbleTextView bubbleTextView;

        public TuringHolder() {
        }
    }
}
