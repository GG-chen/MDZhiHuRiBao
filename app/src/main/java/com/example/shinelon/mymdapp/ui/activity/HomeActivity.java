package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.ui.fragment.JuheFragment;
import com.example.shinelon.mymdapp.ui.fragment.TuringFragment;
import com.example.shinelon.mymdapp.ui.fragment.AboutFragment;
import com.example.shinelon.mymdapp.ui.fragment.HomeFragment;
import com.example.shinelon.mymdapp.ui.fragment.WelfareFragment;
import com.example.shinelon.mymdapp.ui.view.ThemeLinearLayout;
import com.example.shinelon.mymdapp.utils.MyUtils;
import com.example.shinelon.mymdapp.utils.ThemeUIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

public class HomeActivity extends BaseActivity {
    private List<Fragment> mFragmentList = new ArrayList<>();
    @InjectView(R.id.tl_custom)
    Toolbar mActionBarToolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    @InjectView(R.id.switch_theme)
    ThemeLinearLayout mSwitchTheme;
    @InjectView(R.id.navigation_header_container)
    LinearLayout mCustomLayout;
    private int mCurrent = 0;
    @InjectView(R.id.theme_loge)
    ImageView mThemeLoge;

    @Override
    protected void initActivity() {

        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new WelfareFragment());
        mFragmentList.add(new JuheFragment());
        mFragmentList.add(new TuringFragment());
        mFragmentList.add(new AboutFragment());

    }

    @Override
    protected void initView() {
        setupToolbar();
        setupDrawer();
        selectItem(mCurrent);
    }

    public void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mActionBarToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                MyUtils.hideKeyboard((HomeActivity) mContext);
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        //mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (!mSpUtil.get("theme", "DayTheme").equals("DayTheme")) {
            mThemeLoge.setBackgroundResource(R.drawable.moon);
        }
        mSwitchTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSpUtil.get("theme", "DayTheme").equals("DayTheme")) {
                    mThemeLoge.setBackgroundResource(R.drawable.moon);
                    mSpUtil.put("theme", "NightTheme");
                    setTheme(R.style.NightTheme);
                } else {
                    mSpUtil.put("theme", "DayTheme");
                    setTheme(R.style.DayTheme);
                    mThemeLoge.setBackgroundResource(R.drawable.sun);
                }
                ThemeUIUtil.changeTheme(mCustomLayout, getTheme());
                recreate();
            }
        });
    }


    public void selectItem(int i) {

        if (mFragmentList.size() != 0) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragmentList.get(mCurrent));
            if (!mFragmentList.get(i).isAdded()) {
                trx.add(R.id.content, mFragmentList.get(i));
            }
            trx.show(mFragmentList.get(i)).commit();
            mCurrent = i;
            if (i == 1 || i == 2 || i == 3) {
                mActionBarToolbar.setBackgroundResource(R.color.colorPrimary);
            } else {
                mActionBarToolbar.setBackgroundColor(Color.TRANSPARENT);
            }
            closeDrawer();
        } else {
            showToast("请重启软件!");
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {
        log("现在时间" + getCurrentData());

    }

    public void setupToolbar() {
        final ActionBar bar = getmActionBarToolbar();
        bar.setTitle("");
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);
    }


    protected ActionBar getmActionBarToolbar() {
        if (mActionBarToolbar != null) {
            setSupportActionBar(mActionBarToolbar);
        }
        return getSupportActionBar();
    }
    protected void closeDrawer() {
        if (mDrawerLayout == null)
            return;
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


    public void onCheckedChanged(View view) {
        switch (view.getId()) {
            case R.id.home:
                selectItem(0);
                break;
            case R.id.welfare_frag:
                selectItem(1);
                break;
            case R.id.moive:
                selectItem(2);
                break;
            case R.id.turing:
                selectItem(3);
                break;
            case R.id.about:
                selectItem(4);
                break;
        }
    }

}
