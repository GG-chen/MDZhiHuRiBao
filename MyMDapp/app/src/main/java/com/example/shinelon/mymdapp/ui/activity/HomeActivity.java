package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
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

public class HomeActivity extends BaseActivity  {
    private List<Fragment> fragments = new ArrayList<>();
    private Map<String, Fragment> fragmentMap = new ArrayMap<>();
    public Toolbar actionBarToolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ThemeLinearLayout switch_theme;
    private LinearLayout custom_layout;
    private int current = 0;
    private int old;
    private TextView title;
    private ImageView theme_loge;

    @Override
    protected void initActivity() {
        setContentView(R.layout.activity_home);
        /*fragments.add(new HomeFragment());
        fragments.add(new WelfareFragment());
        fragments.add(new JuheFragment());
        fragments.add(new TuringFragment());
        fragments.add(new AboutFragment());*/
        fragmentMap.put("0", createFragment(0));
        fragmentMap.put("1",createFragment(1));
        fragmentMap.put("2", createFragment(2));
        fragmentMap.put("3", createFragment(3));
        fragmentMap.put("4", createFragment(4));
    }

    @Override
    protected void initView() {
        setupToolbar();
        setupDrawer();
        selectItem(current);
    }

    public void setupDrawer() {
        custom_layout = (LinearLayout) findViewById(R.id.navigation_header_container);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBarToolbar,R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                MyUtils.hideKeyboard((HomeActivity)context);
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        //mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        switch_theme = (ThemeLinearLayout) findViewById(R.id.switch_theme);
        theme_loge = (ImageView) findViewById(R.id.theme_loge);
        if (!spUtil.get("theme", "DayTheme").equals("DayTheme")) {
            theme_loge.setBackgroundResource(R.drawable.moon);
        }
        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spUtil.get("theme", "DayTheme").equals("DayTheme")) {
                    theme_loge.setBackgroundResource(R.drawable.moon);
                    spUtil.put("theme", "NightTheme");
                   setTheme(R.style.NightTheme);
                } else {
                    spUtil.put("theme", "DayTheme");
                    setTheme(R.style.DayTheme);
                    theme_loge.setBackgroundResource(R.drawable.sun);
                }
                ThemeUIUtil.changeTheme(custom_layout, getTheme());
                /*fragmentMap.remove(current + "");
                Fragment fragment = createFragment(current);
                fragmentMap.put("" + current, fragment);
                log("切换主题");
                selectItem(current);*/
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
        this.current = i;
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
                trx.hide(fragmentMap.get(old + ""));
            if (!fragment.isAdded()) {
                trx.add(R.id.content, fragment);
            }
            trx.show(fragment).commit();
            old = current;
            closeDrawer();
            log("创建Fragment成功！！");
        } else {
            log("创建Fragment失败！！");
        }

    }

    private Fragment getFragment(int i) {
        Fragment fragment;
        if (i == 1 || i == 2 || i == 3) {
            actionBarToolbar.setBackgroundResource(R.color.colorPrimary);
        } else {
            actionBarToolbar.setBackgroundColor(Color.TRANSPARENT);
        }
        fragment = fragmentMap.get(i + "");
        return fragment;
    }

    @Override
    protected void initData() {
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
            //可以根据这个设置title
            title = (TextView) findViewById(R.id.toolbar_title);
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
