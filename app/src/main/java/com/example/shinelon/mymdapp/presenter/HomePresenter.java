package com.example.shinelon.mymdapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.shinelon.mymdapp.MyApplication;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.HomeService;
import com.example.shinelon.mymdapp.modle.http.utils.RetrofitUtils;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.ui.fragment.HomeFrg;

import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class HomePresenter extends BasePresenter<HomeFrg> {

    private final MyApplication mApplication;
    private HomeService mHomeService;
    private Context mContext;
    public HomePresenter(Context context) {
        mHomeService = RetrofitUtils.createApi(context, HomeService.class);
        this.mContext = context;
        mApplication = (MyApplication) context.getApplicationContext();
    }
    public void loadNewsList(final String date) {
            Subscription subscription = mHomeService.getNewsList(date)
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
                            newListBean.getStories().get(0).setDate(((HomeActivity) mContext).getFormatDate(newListBean.getDate()));
                            mMvpView.loadMore(newListBean);
                        }
                    });

    }

    public void loadLastNewsList() {
            Log.d("TEST！！", "loadLastNewsList: ");
            Subscription subscription = mHomeService.getNewsList()
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
