package com.example.shinelon.mymdapp.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.adapter.RefreshRecyclerAdapter;
import com.example.shinelon.mymdapp.adapter.ViewPagerAdapter;
import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.ui.activity.NewDetailActivity;
import com.example.shinelon.mymdapp.ui.view.WrapRecyclerView;
import com.example.shinelon.mymdapp.modle.bean.NewItemBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.presenter.HomePresenter;
import com.example.shinelon.mymdapp.ui.view.MySwipeRefreshLayout;
import com.example.shinelon.mymdapp.utils.MyUtils;


import java.util.ArrayList;

import butterknife.InjectView;


/**
 * Created by Shinelon on 2017/1/31.
 */
public class HomeFragment extends BaseFragment implements HomeFrg, RefreshRecyclerAdapter.OnRecyclerViewItemClickListener {
    private NewsListBean newsListBean;
    private HomePresenter homePresenter;
    private HomeActivity home;
    public ArrayList<View> views = new ArrayList<>();
    @InjectView(R.id.list_view)
    WrapRecyclerView recyclerView;
    @InjectView(R.id.swipe_layout)
    MySwipeRefreshLayout refreshLayout;
    ViewPager head_viewpager;
    public RefreshRecyclerAdapter adapter;
    public ViewPagerAdapter pagerAdapter;
    private int currentPager = 1000;
    private boolean isFirst = true;
    private String needLodeDate = null;
    private LinearLayoutManager manager;
    private boolean isLoading = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                    Log.d("ReflactText", "handleMessage: ");
                    if (isFirst) {
                        handler.sendEmptyMessageDelayed(2, 3000);
                        isFirst  = false;
                    }
                    break;
                case 2 :
                    pagerAdapter.notifyDataSetChanged();
                    head_viewpager.setCurrentItem(currentPager++);
                    handler.sendEmptyMessageDelayed(2, 3000);
                    break;
            }

        }
    };


    @Override
    protected void initFragment() {
        home = (HomeActivity) getActivity();
        /*home.setupToolbar(view);
        home.setupDrawer();*/
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
                    if (!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
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
        setupPager();
        loadNewData();
        setupSwipe();


    }
    private void loadNewData() {
        Log.d("TEST！！", "loadNewData: ");
        needLodeDate = home.getCurrentData();
        homePresenter.loadLastNewsList();
    }

    private void setupPager() {
        head_viewpager = new ViewPager(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        head_viewpager.setLayoutParams(params);
        pagerAdapter = new ViewPagerAdapter(getActivity());
        head_viewpager.setAdapter(pagerAdapter);
        recyclerView.addHeaderView(head_viewpager);
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
            views.clear();
            adapter.addItem(newsListBean.getStories());
            pagerAdapter.addItem(newsListBean.getTop_stories());
            handler.sendEmptyMessage(1);
            homePresenter.loadNewsList(needLodeDate);
            Log.d(TAG_LOG, "结束!!");
            notifyDataHasChanged();

        }


    }

    private void notifyDataHasChanged() {
        pagerAdapter.notifyDataSetChanged();
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
        //handler.sendEmptyMessage(1);
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
