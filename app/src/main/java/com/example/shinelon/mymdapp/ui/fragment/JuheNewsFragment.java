package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.adapter.JuheTypeAdapter;
import com.example.shinelon.mymdapp.modle.bean.JuheBean;
import com.example.shinelon.mymdapp.presenter.JuhePresenter;
import com.example.shinelon.mymdapp.ui.activity.JuheDetailActivity;
import com.example.shinelon.mymdapp.ui.view.WrapRecyclerView;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/3/10.
 */
public class JuheNewsFragment extends BaseFragment implements JuheFrg, JuheTypeAdapter.OnViewItemClickListener {
    private JuheTypeAdapter mNewsAdapter;
    private String mType = null;
    @InjectView(R.id.juhe_list_view)
    public WrapRecyclerView mRecyclerView;
    @InjectView(R.id.juhe_swipe_layout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private JuhePresenter mPresenter;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mNewsAdapter.notifyDataSetChanged();
        }
    };

    public static JuheNewsFragment newInstance(String type) {
        Bundle args = new Bundle();
        JuheNewsFragment fragment = new JuheNewsFragment();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mType = args.getString("TYPE");
        }
    }
    @Override
    protected void initFragment() {
        initData();
        initView();
    }

    private void initData() {
        Log.d("JuheNewsFragment", "initData: ");
        mNewsAdapter = new JuheTypeAdapter();
        mNewsAdapter.setOnItemClickListener(this);
        mPresenter = new JuhePresenter(mContext);
        mPresenter.attachView(this);
        mPresenter.loadData(mType);
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mNewsAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(mType);
            }
        });
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.juhe_list_frg;
    }

    @Override
    public void loadData(JuheBean bean) {
        if (bean != null) {
            Log.d("JuheNewsFragment", "loadData: ");
            mNewsAdapter.setData(bean.getResult().getData());
            mSwipeRefreshLayout.setRefreshing(false);
            mHandler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        JuheBean.JuheResult.JuHeItem item = mNewsAdapter.getmItems().get(position);
            Intent intent = new Intent(mContext, JuheDetailActivity.class);
            Log.d("onItemClick", "onItemClick: " + item.getTitle());
            intent.putExtra("juheUrl", item.getUrl());
            intent.putExtra("juheTitle", item.getTitle());
            intent.putExtra("juheImage", item.getThumbnail_pic_s());
            startActivity(intent);
    }
}
