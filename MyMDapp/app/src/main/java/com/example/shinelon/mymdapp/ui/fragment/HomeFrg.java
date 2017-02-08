package com.example.shinelon.mymdapp.ui.fragment;

import com.example.shinelon.mymdapp.modle.bean.NewDetailBean;
import com.example.shinelon.mymdapp.modle.bean.NewsListBean;

/**
 * Created by Shinelon on 2017/1/31.
 */

public interface HomeFrg {
    void refresh(NewsListBean data);
    void loadMore(NewsListBean data);
    void loadNewDetail(NewDetailBean data);

}
