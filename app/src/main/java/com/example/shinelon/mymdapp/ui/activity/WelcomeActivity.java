package com.example.shinelon.mymdapp.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.WelcomeBean;
import com.example.shinelon.mymdapp.modle.http.WelcomeService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;


import butterknife.InjectView;
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
    public static final int ENTER_MAIN = 3;
    @InjectView(R.id.welcome_view)
     ImageView mImageView;
    private WelcomeBean mWelcomeBean;
    private WelcomeService mWelcomeService;



    @Override
    protected void initView() {
        mText = (TextView) findViewById(R.id.writer);
        wel_relative = (RelativeLayout) findViewById(R.id.wel_relative);
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation scaleAnimation = new AlphaAnimation(0.5f,1.0f);
        scaleAnimation.setDuration(1000);
        set.addAnimation(scaleAnimation);
        set.setFillAfter(true);
        wel_relative.startAnimation(set);


    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LODE_SUCCESS:
                    mWelcomeBean = (WelcomeBean) msg.obj;
                    mImageUtils.setImage(mImageView, mWelcomeBean.getCreatives().get(0).getUrl());
                    Message message = handler.obtainMessage();
                    message.what = ENTER_MAIN;
                    handler.sendMessageDelayed(message,ENTER_TIME);
                    break;
                case LODE_ERROR:
                    mImageView.setImageResource(R.drawable.welcome);
                    Message messageerror = handler.obtainMessage();
                    messageerror.what = ENTER_MAIN;
                    handler.sendMessageDelayed(messageerror,ENTER_TIME);
                    break;
                case ENTER_MAIN:
                    intent2(HomeActivity.class);
                    finish();
                    break;


            }

        }
    };
    private RelativeLayout wel_relative;


    private TextView mText;

    @Override
    protected void initActivity() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.splash_layout;
    }
    @Override
    protected void initData() {
        mWelcomeService = RetrofitUtils.createApi(this,WelcomeService.class);
        getData();
    }

    public void getData() {
        Log.d("WelcomeActivity", "getDate!!");
            mWelcomeService.getWelcomeImg(getScreenSize())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.immediate())
                    .subscribe(new Observer<WelcomeBean>() {
                        @Override
                        public void onCompleted() {
                            Log.d("WelcomeActivity", "onCompleted!!");

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("WelcomeActivity", e.toString());
                            handler.sendEmptyMessage(LODE_ERROR);
                        }

                        @Override
                        public void onNext(WelcomeBean welcomeBean) {
                            Log.d("WelcomeActivity", "next!!" + welcomeBean.getCreatives().get(0).getUrl());
                            Message message = handler.obtainMessage();
                            message.what = LODE_SUCCESS;
                            message.obj = welcomeBean;
                            handler.sendMessage(message);
                        }

                    });

    }


}
