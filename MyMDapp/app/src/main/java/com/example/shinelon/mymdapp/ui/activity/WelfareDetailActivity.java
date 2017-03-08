package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.modle.http.utils.ImageUtils;
import com.example.shinelon.mymdapp.utils.BitmapSaveUtil;

/**
 * Created by Shinelon on 2017/3/4.
 */
public class WelfareDetailActivity extends BaseActivity implements View.OnClickListener {
    private String url;
    private String name;
    private ImageView imageView;
    private ImageView download;
    private BitmapSaveUtil saveUtil;

    @Override
    protected void initActivity() {
        setContentView(R.layout.detail_welfare_layout);
        url = getIntent().getStringExtra("imageUrl");
        name = getIntent().getStringExtra("imageName");
        saveUtil = BitmapSaveUtil.getSaveUtil(this);
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.detail_img);
        download = (ImageView) findViewById(R.id.download);
        download.setOnClickListener(this);
        if (saveUtil.isSave(name)) {
            download.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void initData() {
        if (url != null) {
            ImageUtils.getInstance().setImage(imageView, url);
        }
    }

    @Override
    public void onClick(View v) {
        imageView.setDrawingCacheEnabled(true);
        Bitmap obmp = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);
        Boolean isSuccess = saveUtil.saveBitmap(obmp, name);
        if (isSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            download.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }

    }
}
