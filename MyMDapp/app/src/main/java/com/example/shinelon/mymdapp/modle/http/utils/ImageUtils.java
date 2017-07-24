package com.example.shinelon.mymdapp.modle.http.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shinelon.mymdapp.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class ImageUtils {
    public static ImageUtils mImageUtils;
    private Context mContext;

    public static ImageUtils getInstance() {
        if (mImageUtils == null) {
            synchronized (ImageUtils.class) {
                if (mImageUtils == null) {
                    mImageUtils = new ImageUtils();
                }
            }
        }
        return mImageUtils;

    }

    public void setmContext(Context c) {
        this.mContext = c;
    }

    private ImageUtils() {
    }


    public Boolean setImage(ImageView v, String url) {
        if (mContext == null && url == null) {
            Log.d("ImageUtil" , "没有context或者url");
            return false;
        }
        try {
            Glide.with(mContext).load(url).error(R.drawable.error).diskCacheStrategy(DiskCacheStrategy.ALL).into(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        try {
            bitmap =  Glide.with(mContext).load(url).asBitmap().centerCrop().into(600,600).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
