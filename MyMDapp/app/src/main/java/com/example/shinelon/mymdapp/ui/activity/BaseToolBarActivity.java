package com.example.shinelon.mymdapp.ui.activity;

import android.support.v7.widget.Toolbar;

/**
 * Created by Shinelon on 2017/2/3.
 */

public abstract class BaseToolBarActivity extends BaseActivity {

    public abstract boolean providesActivityToolbar();


    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
