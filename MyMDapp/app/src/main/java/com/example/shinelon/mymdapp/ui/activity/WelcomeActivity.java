package com.example.shinelon.mymdapp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.WelcomeBean;
import com.example.shinelon.mymdapp.modle.http.WelcomeService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.utils.MyUtils;


import rx.Observer;
import rx.schedulers.Schedulers;


/**
 * Created by Shinelon on 2017/1/31.
 */
/*
* message.what 处理一下
* */

public class WelcomeActivity extends BaseActivity {
    private ImageView imageView;
    private TextView text;
    private WelcomeBean welcomeBean;
    private WelcomeService welcomeService;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    welcomeBean = (WelcomeBean) msg.obj;
                    Log.d("main", welcomeBean.getImg());
                    boolean isSuccess = imageUtils.setImage(imageView, welcomeBean.getImg());
                    if (!isSuccess) {
                        showToast("读取图片失败！！");
                        log("读取图片失败！！");
                    }

                    text.setText(welcomeBean.getText());
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    handler.sendMessageDelayed(message,1000);
                    break;
                case 2:
                    intent2(HomeActivity.class);
                    finish();
                    break;


            }

        }
    };


    @Override
    protected void initActivity() {
        super.initActivity();
        setContentView(R.layout.splash_layout);
    }

    @Override
    protected void initData() {
        super.initData();
        welcomeService = RetrofitUtils.createApi(this,WelcomeService.class);
        getData();
    }

    protected void initView() {
        super.initView();
        imageView = (ImageView) findViewById(R.id.welcome_view);
        text = (TextView) findViewById(R.id.writer);
    }

    public void getData() {
        Log.d("main", "getDate!!");
        if (MyUtils.isNetworkAvailable(context)) {
            welcomeService.getWelcomeImg(getScreenSize())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Observer<WelcomeBean>() {
                        @Override
                        public void onCompleted() {
                            Log.d("main", "onCompleted!!");

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("main", e.toString());

                        }

                        @Override
                        public void onNext(WelcomeBean welcomeBean) {
                            Log.d("main", "next!!");
                            MyApplication my = (MyApplication) getApplication();
                            my.getDbService().saveWelcome(welcomeBean);
                            sendHandler(welcomeBean);
                        }

                    });
        } else {
            MyApplication my = (MyApplication) getApplication();
            WelcomeBean welcomeBean = my.getDbService().queryWelcome();
            if (welcomeBean != null) {
                sendHandler(welcomeBean);
            }
        }

        handler.sendEmptyMessageDelayed(2, 5000);
    }

    private void sendHandler(WelcomeBean welcomeBean) {
        Message message = handler.obtainMessage();
        message.what = 1;
        message.obj = welcomeBean;
        handler.sendMessage(message);
    }

}
