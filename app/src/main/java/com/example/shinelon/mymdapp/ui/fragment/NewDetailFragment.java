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
    private int mNewId = 0;
    private NewDetailpresenter mNewDetailpresenter;
    private NewDetailBean mBean;
    @InjectView(R.id.appbar)
    AppBarLayout mAppbar;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.author)
    TextView mAuthor;

    @InjectView(R.id.tv_content)
    TextView mTvContent;

    @InjectView(R.id.backdrop)
    ImageView mImageView;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    Handler mHandler = new Handler() {
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
            mNewId = getArguments().getInt("id");
        }
        if (!((BaseToolBarActivity) getActivity()).providesActivityToolbar()) {
            ((BaseToolBarActivity) getActivity()).setToolbar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
        loadData();
        initView();

    }

    private void initView() {
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                mToolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });
    }

    private void setupData(CharSequence body) {
        if (mBean != null) {
            mCollapsingToolbar.setTitle(mBean.getTitle());
            ImageUtils.getInstance().setImage(mImageView,  mBean.getImage());
            mTvContent.setText(body);
        }
    }

    private void loadData() {
        mNewDetailpresenter = new NewDetailpresenter(mContext);
        mNewDetailpresenter.attachView(this);
        mNewDetailpresenter.loadNewsList(mNewId);
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.detail_new_frg;
    }

    public static NewDetailFragment newInstance(Bundle args) {
        NewDetailFragment fragment = new NewDetailFragment();
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
        mBean = data;
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
                CharSequence test = Html.fromHtml(mBean.getBody(), imageGetter, null);
                msg.what = 1;
                msg.obj = test;
                mHandler.sendMessage(msg);
            }
        });
        t.start();
    }


}
