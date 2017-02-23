package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.HomeService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.ui.fragment.HomeFrg;
import com.example.shinelon.mymdapp.utils.MyUtils;
import com.google.gson.Gson;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class HomePresenter extends BasePretener<HomeFrg> {

    private final MyApplication my;
    private HomeService homeService;
    private Context context;
    public HomePresenter(Context context) {
        homeService = RetrofitUtils.createApi(context, HomeService.class);
        this.context = context;
        my = (MyApplication) context.getApplicationContext();
    }
    public void loadNewsList(final String date) {
            Subscription subscription = homeService.getNewsList(date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Observer<NewsListBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(NewsListBean newListBean) {
                            newListBean.getStories().get(0).setDate(((HomeActivity)context).getFormatDate(newListBean.getDate()));
                            mMvpView.loadMore(newListBean);
                        }
                    });

    }

    public void loadLastNewsList() {
            Log.d("TEST！！", "loadLastNewsList: ");
            Subscription subscription = homeService.getNewsList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Observer<NewsListBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(NewsListBean newsListBean) {
                            Log.d("HomePresenter！！", "loadLastNewsList: " + newsListBean.getStories().get(0).getImages()[0]);
                            mMvpView.refresh(newsListBean);
                        }
                    });

    }

    @Override
    public void attachView(HomeFrg mvpView) {
        super.attachView(mvpView);
    }
}
