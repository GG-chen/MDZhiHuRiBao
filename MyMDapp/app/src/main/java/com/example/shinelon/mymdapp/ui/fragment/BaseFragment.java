package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;

import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2017/1/31.
 */

public abstract class BaseFragment extends Fragment {
    protected static String TAG_LOG = null;
    protected Context context;
    protected LinearLayoutManager layoutManager;
    protected View view;
    protected MyApplication my;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getCurrentLayoutId() != 0) {
            Log.d(TAG_LOG, "找到Fragment！ ");
            return inflater.inflate(getCurrentLayoutId(), null);
        } else {
            Log.d(TAG_LOG, "没有找到Fragment！ ");
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this,view);
        my = (MyApplication) context.getApplicationContext();
        initFragment();
    }

    protected abstract void initFragment();
    protected abstract  int getCurrentLayoutId();

    @Override
    public void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
    }
}
