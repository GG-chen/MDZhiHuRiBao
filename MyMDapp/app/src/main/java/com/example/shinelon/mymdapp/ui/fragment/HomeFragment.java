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
    public ArrayList<NewItemBean> itemBeans = new ArrayList<>();
    public ArrayList<View> views = new ArrayList<>();
    @InjectView(R.id.list_view)
    WrapRecyclerView recyclerView;
    @InjectView(R.id.swipe_layout)
    MySwipeRefreshLayout refreshLayout;
    //@InjectView(R.id.head_viewpager)
    ViewPager head_viewpager;
    public RefreshRecyclerAdapter adapter;
    public ViewPagerAdapter pagerAdapter;
    private int currentPager = 0;
    private boolean isFirst = true;
    private int beforeDateCount = 0;
    private RecyclerView.LayoutManager manager;
    private int scrollY = 0;
    private boolean isLoading = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1 :
                   // delectFoot();
                    Log.d("ReflactText", "handleMessage: ");
                    isLoading = false;
                    adapter.notifyDataSetChanged();
                    pagerAdapter.notifyDataSetChanged();
                    Log.d("Test", "run: !!");


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
        home.setupToolbar(view);
        home.setupDrawer();
        manager = new LinearLayoutManager(context) ;
        recyclerView.setLayoutManager(manager);
        homePresenter = new HomePresenter(context);
        homePresenter.attachView(this);
        adapter = new RefreshRecyclerAdapter(context,itemBeans);
        adapter.setOnItemClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollY = dy + scrollY;
                if (Math.abs(scrollY) < 300) {
                    float percentage = (float) Math.abs(scrollY) / (float) 300;
                    home.actionBarToolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
                    //Log.d("HomeFragment", " dy = " + scrollY);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //屏幕中最后一个可见子项的position
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                   // Log.d("Last", "item: " + lastVisibleItemPosition);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //Log.d("onScrollStateChanged", "onScrollStateChanged: " + newState);
                if (!recyclerView.canScrollVertically(1) && newState == 2 && !isFirst) {
                    Log.d("lastItemVisible", "load" );
                    isLoading = true;
                    //addFoot();
                    beforeDateCount ++;
                    synchronized (this) {
                        homePresenter.loadNewsList(String.valueOf(home.getBeforeDate(beforeDateCount, false)));
                    }

                }
            }
        });
        setupPager();
        loadNewData();
        setupSwipe();


    }

    private void addFoot() {
        recyclerView.addFootView(LayoutInflater.from(context).inflate(R.layout.foot_layout, null));

    }
    private void delectFoot() {
        recyclerView.mFootViews.clear();
    }

    private void loadNewData() {
        Log.d("TEST！！", "loadNewData: ");
        homePresenter.loadLastNewsList();
        homePresenter.loadNewsList(String.valueOf(home.getBeforeDate(beforeDateCount, false)));

    }

    private void setupPager() {
        head_viewpager = new ViewPager(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        head_viewpager.setLayoutParams(params);
        pagerAdapter = new ViewPagerAdapter(getActivity(), views);
        head_viewpager.setAdapter(pagerAdapter);
        head_viewpager.setCurrentItem(Integer.MAX_VALUE / 2);
        recyclerView.addHeaderView(head_viewpager);
        recyclerView.setAdapter(adapter);
    }


    private void setupSwipe() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG_LOG, "onRefresh!! ");
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
            itemBeans.clear();
           // topBeans.clear();
            views.clear();
            setStory(data);
            setTop_story(data);
            currentPager = 1;
            Log.d(TAG_LOG, "结束!!");
            handler.sendEmptyMessage(1);
            if (!MyUtils.isNetworkAvailable(context)) {

            }

        }


    }

    private void setStory(NewsListBean data) {
        for (int i = 0; i < data.getStories().size(); i++) {
            int id = data.getStories().get(i).getId();
            String title = data.getStories().get(i).getTitle();
            String images = (String) data.getStories().get(i).getImages().get(0);
            NewItemBean bean = new NewItemBean(id,title,images);
            itemBeans.add(bean);
            //Log.d(TAG_LOG, "get the story" + itemBeans.size());
        }
    }

    private void setTop_story(final NewsListBean data) {
                ImageUtils.getInstance().setContext(context);
                Log.d("TEST", "setTop_story!!!!!" + data.getTop_stories().size());
                for (int i = 0; i < data.getTop_stories().size(); i++) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    //imageView.setImageBitmap(ImageUtils.getInstance().getBitmap(data.getTop_stories().get(i).getImage()));
                    ImageUtils.getInstance().setImage(imageView, data.getTop_stories().get(i).getImage());
                    imageView.setTag(data.getTop_stories().get(i).getId());
                    views.add(imageView);
                    Log.d("TEST!!!!!!!!!!!!!!!!!!", "run" + views.size());

                }
                    pagerAdapter.notifyDataSetChanged();


                Log.d(TAG_LOG, "get the View = " + views.size());



    }
    private void setBeforeStory(NewsListBean data) {
        for (int i = 0; i < data.getStories().size(); i++) {
            int id = data.getStories().get(i).getId();
            String title = data.getStories().get(i).getTitle();
            String images = (String) data.getStories().get(i).getImages().get(0);
            NewItemBean bean = new NewItemBean(id,title,images);
            if (i == 0) {
                bean.setDate(home.getFormatDate(data.getDate()));
            }
            itemBeans.add(bean);
        }
        Log.d("ReflactText", "setBeforeStory: " + itemBeans.get(1).getTitle());
        handler.sendEmptyMessage(1);

    }

    @Override
    public void loadMore(NewsListBean data) {
        if (data != null) {
            Log.d("loadMore", "loadMore: " + data.getStories().size());
            setBeforeStory(data);
        }
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
