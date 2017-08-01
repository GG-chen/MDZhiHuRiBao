package com.example.shinelon.mymdapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.shinelon.mymdapp.ui.fragment.JuheNewsFragment;

/**
 * Created by Shinelon on 2017/3/10.
 */
public class JuheNewsPagerAdapter extends FragmentPagerAdapter {
    private final String[] mTitles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private final String[] mTitles_en ={"top", "shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};

    public JuheNewsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        Log.d("JuheNewsPagerAdapter", "JuheNewsPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("JuheNewsPagerAdapter", "getItem！ " + mTitles[position]);
        return JuheNewsFragment.newInstance(mTitles_en[position]);
    }

    @Override
    public int getCount() {
        Log.d("JuheNewsPagerAdapter", "getCount" +  mTitles.length);
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
