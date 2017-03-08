package com.example.shinelon.mymdapp.ui.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
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


import java.util.ArrayList;

import butterknife.InjectView;


/**
 * Created by Shinelon on 2017/1/31.
 */
public class HomeFragment extends BaseFragment implements HomeFrg, RefreshRecyclerAdapter.OnRecyclerViewItemClickListener {
    public static int VIEWPAGER_COUNT = 5;
    private NewsListBean newsListBean;
    private HomePresenter homePresenter;
    private HomeActivity home;
    private ArrayList<NewsListBean.TopStoried> list = new ArrayList<>();
    @InjectView(R.id.list_view)
    WrapRecyclerView recyclerView;
    @InjectView(R.id.swipe_layout)
    MySwipeRefreshLayout refreshLayout;
    public RefreshRecyclerAdapter adapter;
    private String needLodeDate = null;
    private LinearLayoutManager manager;
    private boolean isLoading = false;
    private CustomViewPager customViewPager;
    private SwipeViewPager swipeViewPager;


    @Override
    protected void initFragment() {
        initRecyclerView();
        setupPager();
        loadNewData();
        setupSwipe();


    }

    private void initRecyclerView() {
        home = (HomeActivity) getActivity();
        manager = new LinearLayoutManager(context) ;
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        homePresenter = new HomePresenter(context);
        homePresenter.attachView(this);
        adapter = new RefreshRecyclerAdapter(context);
        adapter.setOnItemClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisibleItems = manager.findFirstVisibleItemPosition();
                    if (!isLoading && (visibleItemCount + pastVisibleItems + 3) >= totalItemCount) {
                        isLoading = true;
                        Log.d("onScrolled", "start loading!!");
                        homePresenter.loadNewsList(needLodeDate);
                        adapter.startLoding();
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
        Log.d("TEST！！", "loadNewData: ");
        needLodeDate = home.getCurrentData();
        homePresenter.loadLastNewsList();
    }

    private void setupPager() {
         swipeViewPager = new SwipeViewPager(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        swipeViewPager.setLayoutParams(params);
        customViewPager = new CustomViewPager(context, list);
        //初始化 轮播图指示点
        swipeViewPager.updateIndicatorView(VIEWPAGER_COUNT);
        swipeViewPager.setAdapter(customViewPager);
       //广告图开启滚动功能
        swipeViewPager.startScorll();
        recyclerView.addHeaderView(swipeViewPager);
        recyclerView.setAdapter(adapter);
    }


    private void setupSwipe() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG_LOG, "onRefresh!! ");
                newsListBean = null;
                notifyDataHasChanged();
                loadNewData();
                refreshLayout.setRefreshing(false);
                Toast.makeText(context, "更新成功！！", Toast.LENGTH_SHORT).show();
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
            newsListBean = data;
            Log.d(TAG_LOG, "get the data!!" + data.getStories().get(0).getTitle());
            Log.d(TAG_LOG, "get the data!!" + data.getTop_stories().get(0).getTitle());
            adapter.addItem(newsListBean.getStories());
            list.addAll(newsListBean.getTop_stories());
            VIEWPAGER_COUNT = newsListBean.getTop_stories().size();
            homePresenter.loadNewsList(needLodeDate);
            Log.d(TAG_LOG, "结束!!");
            notifyDataHasChanged();

        }


    }

    private void notifyDataHasChanged() {
        customViewPager.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(NewsListBean data) {
        Log.d("loadMore", "loadMore: " + data.getStories().size());
        if (isLoading) {
            adapter.finishLoding();
            isLoading = false;
        }
        needLodeDate = data.getDate();
        adapter.addItem(data.getStories());
        notifyDataHasChanged();

    }

    @Override
    public void loadNewDetail(NewDetailBean data) {

    }

    @Override
    public void onItemClick(View view, int data) {
        Intent intent =  new Intent(context, NewDetailActivity.class);
        intent.putExtra("newId", data);
        startActivity(intent);
    }

}
