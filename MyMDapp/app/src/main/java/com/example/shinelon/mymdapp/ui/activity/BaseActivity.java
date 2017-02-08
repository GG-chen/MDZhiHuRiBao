package com.example.shinelon.mymdapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Shinelon on 2017/1/31.
 */

public class BaseActivity extends AppCompatActivity {
    protected static String TAG_LOG = null;
    protected Context context;
    protected ImageUtils imageUtils;
    protected DisplayMetrics displayMetrics;
    private Date data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getName();
        context = this;
        initActivity();
        initView();
        initData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        imageUtils = ImageUtils.getInstance();
        imageUtils.setContext(context);
    }

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
        if (msg != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
    protected String getScreenSize() {
       displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }
    public String getCurrentData() {
        data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String nowTime = format.format(data);
        return nowTime;

    }

    public String getBeforeDate(int i, boolean isWeek) {
        SimpleDateFormat format = null;
        if (isWeek) {
            format = new SimpleDateFormat("MM月dd日  E");
        } else {
           format = new SimpleDateFormat("yyyyMMdd");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DAY_OF_MONTH, -i);
        Date beforeDdate = calendar.getTime();
        String beforeTime = format.format(beforeDdate);
        return beforeTime;
    }
    public String getFormatDate(String date) {
        //String formatDate = time.substring(0, 3) + "-" + time.substring(4, 5) + "-" + time.substring(6, 7);
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
