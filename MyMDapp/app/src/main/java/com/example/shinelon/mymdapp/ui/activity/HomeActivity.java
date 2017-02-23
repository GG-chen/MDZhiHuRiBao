package com.example.shinelon.mymdapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.ui.fragment.BoredFragment;
import com.example.shinelon.mymdapp.ui.fragment.CompanyFragment;
import com.example.shinelon.mymdapp.ui.fragment.HomeFragment;
import com.example.shinelon.mymdapp.ui.fragment.MovieFragment;
import com.example.shinelon.mymdapp.ui.fragment.PsychologyFragment;

import static com.example.shinelon.mymdapp.R.id.home;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public Toolbar actionBarToolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RadioGroup group;
    private int current;

    @Override
    protected void initActivity() {
        super.initActivity();
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        super.initView();
        setupToolbar();
        setupDrawer();
        group = (RadioGroup) findViewById(R.id.button_group);
        group.setOnCheckedChangeListener(this);
        selectItem(0);
    }

    public void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBarToolbar,R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        //mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void selectItem(int i) {
        Fragment fragment = null;
        this.current = i;
        switch (i) {
            case 0:
                //首页
                fragment = new HomeFragment();
                break;
            case 1:
                //日常心理学
                fragment = new PsychologyFragment();
                break;
            case 2:
                //电影日报
                fragment = new MovieFragment();
                break;
            case 3:
                //不许无聊
                fragment = new BoredFragment();
                break;
            case 4:
                //大公司日报
                fragment = new CompanyFragment();
                break;
            default:
                break;

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
            closeDrawer();
            log("创建Fragment成功！！");
        } else {
            log("创建Fragment失败！！");
        }

    }

    @Override
    protected void initData() {
        super.initData();
        log("现在时间" + getCurrentData());

    }

    public void setupToolbar() {
        final ActionBar bar = getActionBarToolbar();
        bar.setTitle("");
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);
    }



    protected ActionBar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.tl_custom);
            if (actionBarToolbar != null) {
                setSupportActionBar(actionBarToolbar);
            }
        }
        return getSupportActionBar();
    }
    protected void closeDrawer() {
        if (drawerLayout == null)
            return;
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.home:
                selectItem(0);
                break;
            case R.id.psycology:
                selectItem(1);
                break;
            case R.id.moive:
                selectItem(2);
                break;
            case R.id.bored:
                selectItem(3);
                break;
            case R.id.company:
                selectItem(4);
                break;
        }
    }
}
