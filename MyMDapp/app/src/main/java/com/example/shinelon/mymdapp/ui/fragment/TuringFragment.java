package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.TuringParams;
import com.example.shinelon.mymdapp.adapter.TuringAdapter;
import com.example.shinelon.mymdapp.modle.bean.TuringBean;
import com.example.shinelon.mymdapp.presenter.TuringPresenter;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/23.
 */
public class TuringFragment extends BaseFragment implements TuringFrg, View.OnClickListener {
    private TuringPresenter presenter;
    @InjectView(R.id.turing_main)
    public ListView listView;
    @InjectView(R.id.send_msg)
    public ImageView send;
    @InjectView(R.id.edit_context)
    public EditText editText;
    private TuringAdapter adapter;
    private List<TuringBean> list = new ArrayList<>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
        }
    };
    private static boolean isFirst = true;

    @Override
    protected void initFragment() {
        initView();
        if (isFirst) {
            initData();
            isFirst = false;
        }
    }

    private void initData() {
        TuringBean bean = new TuringBean();
        bean.setText("你好呀  :)  ");
        bean.setType(TuringParams.TYPE_LEFT);
        bean.setCode(TuringParams.TulingCode.TEXT);
        list.add(bean);
    }


    private void initView() {
        presenter = new TuringPresenter(context);
        presenter.attachView(this);
        adapter = new TuringAdapter(list, context);
        listView.setAdapter(adapter);
        send.setOnClickListener(this);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyUtils.hideKeyboard((HomeActivity)context);
                return false;
            }
        });
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.turing_frag;
    }

    @Override
    public void loadData(TuringBean bean) {
        if (bean != null && bean.getCode() != TuringParams.TulingCode.ERROR_DATA_FORMAT
                && bean.getCode() != TuringParams.TulingCode.ERROR_TEXT_NULL
                && bean.getCode() != TuringParams.TulingCode.ERRORKEY_LEN
                && bean.getCode() != TuringParams.TulingCode.ERROR_SERVER_UPDATE
                && bean.getCode() != TuringParams.TulingCode.ERROR_KEY_ERROR
                && bean.getCode() != TuringParams.TulingCode.ERROR_OUT_OF_TIMES
                && bean.getCode() != TuringParams.TulingCode.ERROR_NOT_SUPPORT
                ) {
            list.add(bean);
            handler.sendEmptyMessage(0);
        } else {
            TuringBean errorBean = new TuringBean();
            errorBean.setText("抱歉，我生病了，不能陪小主聊天了...");
            errorBean.setType(TuringParams.TYPE_LEFT);
            list.add(errorBean);
            handler.sendEmptyMessage(0);

        }

    }

    @Override
    public void onClick(View v) {
        MyUtils.hideKeyboard((HomeActivity)context);
        switch (v.getId()) {
            case R.id.send_msg:
                String text = String.valueOf(editText.getText());
                text.trim();
                if (text != null && !"".equals(text)) {
                    TuringBean bean = new TuringBean();
                    bean.setText(text);
                    bean.setType(TuringParams.TYPE_RIGHT);
                    list.add(bean);
                    handler.sendEmptyMessage(0);
                    presenter.loadData(text);
                } else {
                    Toast.makeText(context, "不能为空！！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        editText.setText("");
    }
}
