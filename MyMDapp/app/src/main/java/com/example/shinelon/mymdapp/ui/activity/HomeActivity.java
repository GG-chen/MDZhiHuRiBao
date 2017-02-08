package com.example.shinelon.mymdapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.presenter.HomePresenter;
import com.example.shinelon.mymdapp.ui.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {
    private HomePresenter homePresenter;
    public Toolbar actionBarToolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int current;

    @Override
    protected void initActivity() {
        super.initActivity();
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        super.initView();
        selectItem(0);
        /*setupToolbar();
        setupDrawer();*/
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
        //homePresenter = new HomePresenter(this);
        log("现在时间" + getCurrentData());
       // homePresenter.loadNewsList(getCurrentData());

    }

    public void setupToolbar(View view) {
        final ActionBar bar = getActionBarToolbar(view);
        bar.setTitle("");
        bar.setHomeAsUpIndicator(R.drawable.ic_menu);
        bar.setDisplayHomeAsUpEnabled(true);
    }



    protected ActionBar getActionBarToolbar(View view) {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) view.findViewById(R.id.tl_custom);
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

}
