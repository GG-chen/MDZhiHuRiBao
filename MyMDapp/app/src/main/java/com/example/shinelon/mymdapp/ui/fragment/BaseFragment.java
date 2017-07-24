package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shinelon.mymdapp.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2017/1/31.
 */

public abstract class BaseFragment extends Fragment {
    protected static String TAG_LOG = null;
    protected Context mContext;
    protected View mView;
    protected MyApplication mApplication;
    public Boolean isAdd = false;


    @Override
    public void onAttach(Context context) {
        log("onAttach");
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        log("onCreate");
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log("onCreateView");
        if (getCurrentLayoutId() != 0) {
            log("找到Fragment！ ");
            return inflater.inflate(getCurrentLayoutId(), null);
        } else {
            log("没有找到Fragment！ ");
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        log("onViewCreated");
        this.mView = view;
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this,view);
        mApplication = (MyApplication) mContext.getApplicationContext();
        initFragment();
    }

    protected abstract void initFragment();
    protected abstract  int getCurrentLayoutId();

    @Override
    public void onDestroy() {
        ButterKnife.reset(this);
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        log("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log("onDetach");
    }

    protected void log(String msg) {
        Log.d(TAG_LOG, msg);
    }
}
