package com.example.shinelon.mymdapp.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.presenter.NewDetailpresenter;
import com.example.shinelon.mymdapp.ui.activity.BaseToolBarActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class NewDetailFragment extends BaseFragment implements HomeFrg {
    private int newId = 0;
    private NewDetailpresenter newDetailpresenter;
    private NewDetailBean bean;
    @InjectView(R.id.appbar)
    AppBarLayout appbar;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.author)
    TextView author;

    @InjectView(R.id.tv_content)
    TextView tv_content;

    @InjectView(R.id.backdrop)
    ImageView imageView;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CharSequence body = (CharSequence) msg.obj;
            setupData(body);
        }
    };
    @Override
    protected void initFragment() {

        if (getArguments().getInt("id") != 0) {
            newId = getArguments().getInt("id");
        }
        if (!((BaseToolBarActivity) getActivity()).providesActivityToolbar()) {
            ((BaseToolBarActivity) getActivity()).setToolbar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
        Log.d("DetailNew", "initFrg");
        loadData();
        initView();

    }

    private void initView() {
        Log.d("DetailNew", "initView");
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                toolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });
    }

    private void setupData(CharSequence body) {
        Log.d("DetailNew", bean.getTitle());
        collapsingToolbar.setTitle(bean.getTitle());
        //author.setText(bean.getAuthor().getName());
        ImageUtils.getInstance().setImage(imageView,  bean.getImage());
        tv_content.setText(body);
        tv_content.setTextSize(20);
    }

    private void loadData() {
        newDetailpresenter = new NewDetailpresenter(context);
        newDetailpresenter.attachView(this);
        newDetailpresenter.loadNewsList(newId);
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.detail_new_frg;
    }

    public static NewDetailFragment newInstance(int id) {
        NewDetailFragment fragment = new NewDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void refresh(NewsListBean data) {

    }

    @Override
    public void loadMore(NewsListBean data) {

    }

    @Override
    public void loadNewDetail(NewDetailBean data) {
        Log.d("DetailNew", "loadNewDetail");
        bean = data;
        readHtml();

    }


    private void readHtml() {
        Thread t = new Thread(new Runnable() {
            Message msg = Message.obtain();

            @Override
            public void run() {
                /**
                 * 要实现图片的显示需要使用Html.fromHtml的一个重构方法：public static Spanned
                 * fromHtml (String source, Html.ImageGetterimageGetter,
                 * Html.TagHandler
                 * tagHandler)其中Html.ImageGetter是一个接口，我们要实现此接口，在它的getDrawable
                 * (String source)方法中返回图片的Drawable对象才可以。
                 */
                Html.ImageGetter imageGetter = new Html.ImageGetter() {

                    @Override
                    public Drawable getDrawable(String source) {
                        // TODO Auto-generated method stub
                        URL url;
                        Drawable drawable = null;
                        try {
                            url = new URL(source);
                            drawable = Drawable.createFromStream(
                                    url.openStream(), null);
                            drawable.setBounds(0, 0,
                                    drawable.getIntrinsicWidth(),
                                    drawable.getIntrinsicHeight());
                        } catch (MalformedURLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return drawable;
                    }
                };
                CharSequence test = Html.fromHtml(bean.getBody(), imageGetter, null);
                msg.what = 1;
                msg.obj = test;
                handler.sendMessage(msg);
            }
        });
        t.start();
    }


}
