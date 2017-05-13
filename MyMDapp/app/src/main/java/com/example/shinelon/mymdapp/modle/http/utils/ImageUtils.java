package com.example.shinelon.mymdapp.modle.http.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shinelon.mymdapp.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Shinelon on 2017/1/31.
 */

public class ImageUtils {
    public static ImageUtils imageUtils;
    private Context context;

    public static ImageUtils getInstance() {
        if (imageUtils == null) {
            synchronized (ImageUtils.class) {
                if (imageUtils == null) {
                    imageUtils = new ImageUtils();
                }
            }
        }
        return imageUtils;

    }

    public void setContext(Context c) {
        this.context = c;
    }

    private ImageUtils() {
    }


    public Boolean setImage(ImageView v, String url) {
        if (context == null && url == null) {
            Log.d("ImageUtil" , "没有context或者url");
            return false;
        }
        Glide.with(context).load(url).error(R.drawable.error).diskCacheStrategy(DiskCacheStrategy.ALL).into(v);
        return true;

    }

    public Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        try {
            bitmap =  Glide.with(context).load(url).asBitmap().centerCrop().into(600,600).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
