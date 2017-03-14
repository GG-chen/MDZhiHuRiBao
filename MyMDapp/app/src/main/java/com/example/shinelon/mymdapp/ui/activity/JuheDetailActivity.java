package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/3/11.
 */

public class JuheDetailActivity extends BaseToolBarActivity {
   // @InjectView(R.id.juhe_detail_wv)
    public WebView webView;
   /// @InjectView(R.id.juhe_appbar)
    AppBarLayout appbar;

    //@InjectView(R.id.juhe_toolbar)
    Toolbar toolbar;

   // @InjectView(R.id.juhe_author)
    TextView author;

   // @InjectView(R.id.backdrop)
    ImageView imageView;

   // @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    private String url;
    private String title;
    private String imageUrl;

    @Override
    protected void initActivity() {
        setContentView(R.layout.juhe_detail_layout);
        url = getIntent().getStringExtra("juheUrl");
        title = getIntent().getStringExtra("juheTitle");
        imageUrl = getIntent().getStringExtra("juheImage");
        initViews();
        if (!providesActivityToolbar()) {
            setToolbar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void initViews() {
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.juhe_collapsing_toolbar);
        webView = (WebView) findViewById(R.id.juhe_detail_wv);
        toolbar = (Toolbar) findViewById(R.id.juhe_toolbar);
        appbar = (AppBarLayout) findViewById(R.id.juhe_appbar);
        author = (TextView) findViewById(R.id.juhe_author);
        imageView = (ImageView) findViewById(R.id.juhe_backdrop);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                toolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });
    }

    @Override
    protected void initData() {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
        webView.setBackgroundColor(Color.parseColor("#000000")); // 设置背景色
        webView.getBackground().setAlpha(0);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        collapsingToolbar.setTitle(title);
        ImageUtils.getInstance().setImage(imageView,  imageUrl);
    }



    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
