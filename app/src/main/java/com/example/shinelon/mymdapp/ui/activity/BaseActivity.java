package com.example.shinelon.mymdapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.utils.SPUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;


/**
 * Created by Shinelon on 2017/1/31.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG_LOG = null;
    protected Context mContext;
    protected ImageUtils mImageUtils;
    protected DisplayMetrics mDisplayMetrics;
    private Date mData;
    public SPUtil mSpUtil;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSpUtil = MyApplication.getmSpUtil();
        if (mSpUtil.get("theme", "DayTheme").equals("DayTheme")) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
        super.onCreate(savedInstanceState);
        Log.d("onCreate", " its me !!!");
        TAG_LOG = this.getClass().getName();
        mContext = this;
        mImageUtils = ImageUtils.getInstance();
        mImageUtils.setmContext(MyApplication.getContext());
        setContentView(getContentViewId());
        ButterKnife.inject(this);
        initActivity();
        initView();
        initData();

    }

    protected abstract int getContentViewId();

    protected void intent2(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void initData() {
    }

    protected void initView() {

    }

    protected void initActivity() {

    }

    public void log(String msj) {
        if (msj != null) {

            Log.d(TAG_LOG, msj);
        }
    }

    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = new Toast(this);
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
    protected String getScreenSize() {
       mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.widthPixels + "*" + mDisplayMetrics.heightPixels;
    }
    public String getCurrentData() {
        mData = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String nowTime = format.format(mData);
        return nowTime;

    }

    public String getFormatDate(String date) {
        Log.d("getFormatDate", "date " + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String lastDate = null;
        try {
            Date formatDate = format.parse(date);
            SimpleDateFormat format2E = new SimpleDateFormat("MM月dd日  E");
            lastDate = format2E.format(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastDate;
    }


}
