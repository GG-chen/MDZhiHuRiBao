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

import java.util.Map;

public class HomeActivity extends BaseActivity  {
    private Map<String, Fragment> mFragmentMap = new ArrayMap<>();
    public Toolbar mActionBarToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ThemeLinearLayout mSwitchTheme;
    private LinearLayout mCustomLayout;
    private int mCurrent = 0;
    private int mOld;
    private TextView mTitle;
    private ImageView mThemeLoge;

    @Override
    protected void initActivity() {
        setContentView(R.layout.activity_home);
        /*fragments.add(new HomeFragment());
        fragments.add(new WelfareFragment());
        fragments.add(new JuheFragment());
        fragments.add(new TuringFragment());
        fragments.add(new AboutFragment());*/
        mFragmentMap.put("0", createFragment(0));
        mFragmentMap.put("1",createFragment(1));
        mFragmentMap.put("2", createFragment(2));
        mFragmentMap.put("3", createFragment(3));
        mFragmentMap.put("4", createFragment(4));
    }

    @Override
    protected void initView() {
        setupToolbar();
        setupDrawer();
        selectItem(mCurrent);
    }

    public void setupDrawer() {
        mCustomLayout = (LinearLayout) findViewById(R.id.navigation_header_container);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mActionBarToolbar,R.string.drawer_open, R.string.drawer_close) {
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
        mSwitchTheme = (ThemeLinearLayout) findViewById(R.id.switch_theme);
        mThemeLoge = (ImageView) findViewById(R.id.theme_loge);
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
                /*mFragmentMap.remove(mCurrent + "");
                Fragment fragment = createFragment(mCurrent);
                mFragmentMap.put("" + mCurrent, fragment);
                log("切换主题");
                selectItem(mCurrent);*/
                recreate();
            }
        });
    }

    private Fragment createFragment(int current) {
        Fragment fragment = null;
        switch (current) {
            case 0:
                fragment = new HomeFragment();
            break;
            case 1:
                fragment = new WelfareFragment();
            break;
            case 2:
                fragment = new JuheFragment();
            break;
            case 3:
                fragment = new TuringFragment();
            break;
            case 4:
                fragment = new AboutFragment();
            break;
            default:
                break;
        }
        return fragment;
    }

    public void selectItem(int i) {
        Fragment fragment = null;
        this.mCurrent = i;
        switch (i) {
            case 0:
                //首页
                fragment = getFragment(i);
                break;
            case 1:
                //福利
                fragment = getFragment(i);
                break;
            case 2:
                //每日新闻
                fragment = getFragment(i);
                break;
            case 3:
                //图灵机器人
                fragment = getFragment(i);
                break;
            case 4:
                //关于
                fragment = getFragment(i);
                break;
            default:
                break;

        }
        if (fragment != null) {
            //FragmentManager fragmentManager = getSupportFragmentManager();
           // fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                trx.hide(mFragmentMap.get(mOld + ""));
            if (!fragment.isAdded()) {
                trx.add(R.id.content, fragment);
            }
            trx.show(fragment).commit();
            mOld = mCurrent;
            closeDrawer();
            log("创建Fragment成功！！");
        } else {
            log("创建Fragment失败！！");
        }

    }

    private Fragment getFragment(int i) {
        Fragment fragment;
        if (i == 1 || i == 2 || i == 3) {
            mActionBarToolbar.setBackgroundResource(R.color.colorPrimary);
        } else {
            mActionBarToolbar.setBackgroundColor(Color.TRANSPARENT);
        }
        fragment = mFragmentMap.get(i + "");
        return fragment;
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
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.tl_custom);
            //可以根据这个设置title
            mTitle = (TextView) findViewById(R.id.toolbar_title);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
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
