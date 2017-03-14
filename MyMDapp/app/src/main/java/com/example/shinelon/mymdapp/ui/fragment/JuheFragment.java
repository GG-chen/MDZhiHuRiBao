package com.example.shinelon.mymdapp.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.adapter.JuheNewsPagerAdapter;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/3/10.
 */

public class JuheFragment extends BaseFragment {
    @InjectView(R.id.tabs)
    public PagerSlidingTabStrip pagerSlidingTabStrip;
    @InjectView(R.id.vp_view)
    public ViewPager viewPager;


    @Override
    protected void initFragment() {
        JuheNewsPagerAdapter adapter = new JuheNewsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.juhe_frg;
    }

}
