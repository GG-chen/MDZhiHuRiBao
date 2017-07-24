package com.example.shinelon.mymdapp.ui.activity;

import android.graphics.Bitmap;
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
    private String mUrl;
    private String mName;
    private ImageView mImageView;
    private ImageView mDownload;
    private BitmapSaveUtil mSaveUtil;

    @Override
    protected void initActivity() {
        setContentView(R.layout.detail_welfare_layout);
        mUrl = getIntent().getStringExtra("imageUrl");
        mName = getIntent().getStringExtra("imageName");
        mSaveUtil = BitmapSaveUtil.getSaveUtil(this);
    }

    @Override
    protected void initView() {
        mImageView = (ImageView) findViewById(R.id.detail_img);
        mDownload = (ImageView) findViewById(R.id.download);
        mDownload.setOnClickListener(this);
        if (mSaveUtil.isSave(mName)) {
            mDownload.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void initData() {
        if (mUrl != null) {
            ImageUtils.getInstance().setImage(mImageView, mUrl);
        }
    }

    @Override
    public void onClick(View v) {
        mImageView.setDrawingCacheEnabled(true);
        Bitmap obmp = Bitmap.createBitmap(mImageView.getDrawingCache());
        mImageView.setDrawingCacheEnabled(false);
        Boolean isSuccess = mSaveUtil.saveBitmap(obmp, mName);
        if (isSuccess) {
            Toast.makeText(this, "保存成功" + mSaveUtil.getmLocalFile(), Toast.LENGTH_SHORT).show();
            mDownload.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }

    }
}
