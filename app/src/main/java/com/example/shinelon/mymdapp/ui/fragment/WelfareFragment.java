package com.example.shinelon.mymdapp.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
    public RecyclerView mRecyclerView;
    private WelfareAdapter mAdapter;
    private WelfarePresenter mPresenter;
    private List<WelfareBean.WelfareItem> mWelfareBeen = new ArrayList<>();
    private StaggeredGridLayoutManager mManager;
    private boolean isLoding = true;
    private int mPage = 1;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void initFragment() {
        initview();
        initData();
    }

    private void initData() {
        mPresenter.loadWelfare(String.valueOf(mPage));
    }

    private void initview() {
        mPresenter = new WelfarePresenter(mContext);
        mPresenter.attachView(this);
        mAdapter = new WelfareAdapter(mWelfareBeen, mContext);
        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        mRecyclerView.addOnScrollListener(getOnBottomListener(mManager));
        mAdapter.setOnTouchListener(this);
    }
    OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                                mAdapter.getItemCount() - 8;
                if (isBottom) {
                    if (!isLoding) {
                        mPage += 1;
                        isLoding = true;
                        mPresenter.loadWelfare(String.valueOf(mPage));
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != 2) {
                    mAdapter.setScoll(false);
                } else {
                    mAdapter.setScoll(true);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        };
    }


    @Override
    protected int getCurrentLayoutId() {
        return R.layout.welfare_frag;
    }

    @Override
    public void loadMore(WelfareBean bean) {
        log("WelfareFragment   loadMore" + mPage);
        isLoding = false;
        if (bean != null) {
            mAdapter.addItem(bean.getResults());
            handler.sendEmptyMessage(0);
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
        Intent intent = new Intent(mContext, WelfareDetailActivity.class);
        intent.putExtra("imageUrl", url);
        intent.putExtra("imageName", name);
        ActivityCompat.startActivity(mContext, intent, options.toBundle());
    }
}
