package com.example.shinelon.mymdapp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    public static final int ENTER_TIME = 3000;
    public static final int LODE_SUCCESS = 1;
    public static final int LODE_ERROR = 2;
    private ImageView imageView;
    private TextView text;
    private WelcomeBean welcomeBean;
    private WelcomeService welcomeService;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LODE_SUCCESS:
                    welcomeBean = (WelcomeBean) msg.obj;
                    imageUtils.setImage(imageView, welcomeBean.getImg());
                    text.setText(welcomeBean.getText());
                    Message message = handler.obtainMessage();
                    message.what = LODE_ERROR;
                    handler.sendMessageDelayed(message,ENTER_TIME);
                    break;
                case LODE_ERROR:
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
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale);
        imageView.startAnimation(animation);

    }

    public void getData() {
        Log.d("main", "getDate!!");
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
                            handler.sendEmptyMessageDelayed(LODE_ERROR, ENTER_TIME);
                        }

                        @Override
                        public void onNext(WelcomeBean welcomeBean) {
                            Log.d("main", "next!!");
                            sendHandler(welcomeBean);
                        }

                    });

    }

    private void sendHandler(WelcomeBean welcomeBean) {
        Message message = handler.obtainMessage();
        message.what = LODE_SUCCESS;
        message.obj = welcomeBean;
        handler.sendMessage(message);
    }

}
