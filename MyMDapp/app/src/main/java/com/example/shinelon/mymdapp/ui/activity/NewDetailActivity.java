package com.example.shinelon.mymdapp.ui.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.ui.fragment.NewDetailFragment;

/**
 * Created by Shinelon on 2017/2/3.
 */

public class NewDetailActivity extends BaseToolBarActivity{

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null ) {
            NewDetailFragment newDetailFragment = NewDetailFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_content, newDetailFragment).commit();
        }

    }

    @Override
    protected void initActivity() {
        setContentView(R.layout.detail_new_layout);
    }


    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
