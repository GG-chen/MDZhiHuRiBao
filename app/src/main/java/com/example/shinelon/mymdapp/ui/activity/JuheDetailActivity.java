package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/3/11.
 */

public class JuheDetailActivity extends BaseToolBarActivity {
    @InjectView(R.id.juhe_detail_wv)
    WebView mWebView;
    @InjectView(R.id.juhe_appbar)
    AppBarLayout mAppbar;

    @InjectView(R.id.juhe_toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.juhe_author)
    TextView mAuthor;

    @InjectView(R.id.backdrop)
    ImageView mImageView;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    private String mUrl;
    private String mTitle;
    private String mImageUrl;

    @Override
    protected void initActivity() {
        mUrl = getIntent().getStringExtra("juheUrl");
        mTitle = getIntent().getStringExtra("juheTitle");
        mImageUrl = getIntent().getStringExtra("juheImage");
        initViews();
        if (!providesActivityToolbar()) {
            setToolbar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void initViews() {
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                mToolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });
    }

    @Override
    protected int getContentViewId() {
        return R.layout.juhe_detail_layout;
    }

    @Override
    protected void initData() {
        mWebView.loadUrl(mUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
        mWebView.setBackgroundColor(Color.parseColor("#000000")); // 设置背景色
        mWebView.getBackground().setAlpha(0);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mCollapsingToolbar.setTitle(mTitle);
        ImageUtils.getInstance().setImage(mImageView, mImageUrl);
    }


    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
