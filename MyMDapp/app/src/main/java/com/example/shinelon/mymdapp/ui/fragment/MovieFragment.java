package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.MovieBean;
import com.example.shinelon.mymdapp.presenter.MoviePresenter;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/23.
 */
public class MovieFragment extends BaseFragment implements MovieFrg{
    @InjectView(R.id.web_movie)
    public WebView webview;
    private MoviePresenter presenter;
    private String url;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            webview.loadUrl(url);
            Log.d("MovieFragment", "handleMessage: " + url);
        }
    };

    @Override
    protected void initFragment() {
        presenter = new MoviePresenter(context);
        presenter.attachView(this);
        presenter.getMovie("1");
        WebSettings setting = webview.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setDatabaseEnabled(true);
        setting.setGeolocationEnabled(true);
        String dir = context.getDir("database", Context.MODE_PRIVATE).getPath();
        setting.setDatabasePath(dir);
        setting.setGeolocationDatabasePath(dir);

        setting.setAppCacheEnabled(true);
        String cacheDir = context.getDir("cache", Context.MODE_PRIVATE).getPath();
        setting.setAppCachePath(cacheDir);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);
        setting.setAppCacheMaxSize(1024 * 1024 * 10);
        setting.setAllowFileAccess(true);

        setting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);

        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                return false;

            }
        });



    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.movie_frag;
    }

    @Override
    public void lodeMovie(MovieBean bean) {
        if (bean != null) {
             url = bean.getResults().get(0).getUrl();
            handler.sendEmptyMessage(1);
        } else {
            Toast.makeText(context, "数据获取失败！！", Toast.LENGTH_SHORT).show();
        }
    }
}
