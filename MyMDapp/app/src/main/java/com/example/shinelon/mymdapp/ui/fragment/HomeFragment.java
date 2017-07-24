package com.example.shinelon.mymdapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.slip.SwipeViewPager;
import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.adapter.CustomViewPager;
import com.example.shinelon.mymdapp.adapter.RefreshRecyclerAdapter;
import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.ui.activity.NewDetailActivity;
import com.example.shinelon.mymdapp.ui.view.WrapRecyclerView;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.presenter.HomePresenter;
import com.example.shinelon.mymdapp.ui.view.MySwipeRefreshLayout;
import com.example.shinelon.mymdapp.utils.MyUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * Created by Shinelon on 2017/1/31.
 */
public class HomeFragment extends BaseFragment implements HomeFrg, RefreshRecyclerAdapter.OnRecyclerViewItemClickListener {
    public static int VIEWPAGER_COUNT = 5;
    private NewsListBean mNewsListBean;
    private HomePresenter mHomePresenter;
    private HomeActivity mHome;
    private List<NewsListBean.TopStoried> mList = new ArrayList<>();
    @InjectView(R.id.list_view)
    WrapRecyclerView mRecyclerView;
    @InjectView(R.id.swipe_layout)
    MySwipeRefreshLayout mRefreshLayout;
    public RefreshRecyclerAdapter mAdapter;
    private String mNeedLodeDate = null;
    private LinearLayoutManager mManager;
    private boolean isLoading = false;
    private CustomViewPager mCustomViewPager;
    private SwipeViewPager mSwipeViewPager;


    @Override
    protected void initFragment() {
        initRecyclerView();
        setupSwipe();
        setupPager();
        loadNewData();


    }

    private void initRecyclerView() {
        mHome = (HomeActivity) getActivity();
        mHomePresenter = new HomePresenter(mContext);
        mHomePresenter.attachView(this);
        mManager = new LinearLayoutManager(mContext) ;
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RefreshRecyclerAdapter(mContext);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = mManager.getChildCount();
                    int totalItemCount = mManager.getItemCount();
                    int pastVisibleItems = mManager.findFirstVisibleItemPosition();
                    if (!isLoading && (visibleItemCount + pastVisibleItems + 3) >= totalItemCount) {
                        isLoading = true;
                        log("onScrolled");
                        mHomePresenter.loadNewsList(mNeedLodeDate);
                        mAdapter.startLoding();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    private void loadNewData() {
        log("loadNewData");
        mNeedLodeDate = mHome.getCurrentData();
        mHomePresenter.loadLastNewsList();
    }

    private void setupPager() {
         mSwipeViewPager = new SwipeViewPager(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyUtils.dp2px(mContext,(float)300));
        mSwipeViewPager.setLayoutParams(params);
        //初始化 轮播图指示点
        mSwipeViewPager.updateIndicatorView(VIEWPAGER_COUNT);
        mCustomViewPager = new CustomViewPager(mContext, mList);
        mSwipeViewPager.setAdapter(mCustomViewPager);
       //广告图开启滚动功能
        mSwipeViewPager.startScorll();
        mRecyclerView.addHeaderView(mSwipeViewPager);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupSwipe() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                log("onRefresh!! ");
                mNewsListBean = null;
                notifyDataHasChanged();
                loadNewData();
                mRefreshLayout.setRefreshing(false);
                Toast.makeText(mContext, "更新成功！！", Toast.LENGTH_SHORT).show();
            }


        });

    }


    @Override
    protected int getCurrentLayoutId() {
        return R.layout.fragment_layout;
    }



    @Override
    public void refresh(NewsListBean data) {

        if (data != null) {
            log("refresh!!");
            mList.clear();
            mNewsListBean = data;
            mAdapter.addItem(mNewsListBean.getStories());
            mList.addAll(mNewsListBean.getTop_stories());
            mCustomViewPager.addList(mNewsListBean.getTop_stories());
            VIEWPAGER_COUNT = mNewsListBean.getTop_stories().size();
            mHomePresenter.loadNewsList(mNeedLodeDate);
            notifyDataHasChanged();

        }


    }

    private void notifyDataHasChanged() {
        //mCustomViewPager.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(NewsListBean data) {
        Log.d("loadMore", "loadMore: " + data.getStories().size());
        if (isLoading) {
            mAdapter.finishLoding();
            isLoading = false;
        }
        mNeedLodeDate = data.getDate();
        mAdapter.addItem(data.getStories());
        notifyDataHasChanged();

    }

    @Override
    public void loadNewDetail(NewDetailBean data) {

    }

    @Override
    public void onItemClick(View view, int data) {
        Intent intent =  new Intent(mContext, NewDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", data);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

}
