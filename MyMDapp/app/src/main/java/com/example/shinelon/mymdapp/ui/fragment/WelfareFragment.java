package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.adapter.WelfareAdapter;
import com.example.shinelon.mymdapp.modle.bean.WelfareBean;
import com.example.shinelon.mymdapp.presenter.WelfarePresenter;
import com.example.shinelon.mymdapp.ui.activity.WelfareDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/23.
 */

public class WelfareFragment extends BaseFragment implements WelfareFrg, WelfareAdapter.OnItemTouch {
    @InjectView(R.id.recycler)
    public RecyclerView recyclerView;
    private WelfareAdapter adapter;
    private WelfarePresenter presenter;
    private List<WelfareBean.WelfareItem> welfareBeen = new ArrayList<>();
    private StaggeredGridLayoutManager manager;
    private boolean loding = true;
    private int mPage = 1;


    @Override
    protected void initFragment() {
        initview();
        initData();
    }

    private void initData() {
        presenter.loadWelfare(String.valueOf(mPage));
    }

    private void initview() {
        presenter = new WelfarePresenter(context);
        presenter.attachView(this);
        adapter = new WelfareAdapter(welfareBeen, context);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 16;
                outRect.right = 16;
                outRect.bottom = 16;
                if(parent.getChildAdapterPosition(view)==0){
                    outRect.top=16;
                }
            }
        });
        recyclerView.addOnScrollListener(getOnBottomListener(manager));
        adapter.setOnTouchListener(this);
    }
    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                                adapter.getItemCount() - 6;
                if (isBottom) {
                    if (!loding) {
                        mPage += 1;
                        loding = true;
                        presenter.loadWelfare(String.valueOf(mPage));
                    }
                }
            }
        };
    }


    @Override
    protected int getCurrentLayoutId() {
        return R.layout.welfare_frag;
    }

    @Override
    public void loadMore(WelfareBean bean) {
        log("WelfareFragment   loadMore" + bean.getResults().get(2).getImageUrl());
        loding = false;
        if (bean != null) {
            adapter.addItem(bean.getResults());
        }
    }

    @Override
    public void onTouch(View view, String url, String name) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the top_new activity is animating from
                        (int)view.getWidth()/2, (int)view.getHeight()/2, //拉伸开始的坐标
                        0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        startNewAcitivity(options, url, name);
    }

    private void startNewAcitivity(ActivityOptionsCompat options, String url, String name) {
        Intent intent = new Intent(context, WelfareDetailActivity.class);
        intent.putExtra("imageUrl", url);
        intent.putExtra("imageName", name);
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
