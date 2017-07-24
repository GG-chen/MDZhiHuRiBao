package com.example.shinelon.mymdapp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Shinelon on 2017/3/4.
 */

public class BitmapSaveUtil {
    private final String mDir;
    public static BitmapSaveUtil mSaveUtil;
    private Context mContext;
    private String mLocalFile;

    public BitmapSaveUtil(Context context) {
        this.mContext = context;
        mDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mdapp/";
    }

    public static BitmapSaveUtil getSaveUtil(Context context) {
        if (mSaveUtil == null) {
            mSaveUtil = new BitmapSaveUtil(context);
        }
        return mSaveUtil;
    }

    public boolean saveBitmap(Bitmap bitmap, String name) {
        String state = Environment.getExternalStorageState();
            //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        try {
            File f = new File(mDir);

            if(!f.exists()){

                f.mkdirs();

            }

            mLocalFile = f.getAbsolutePath()+"/"+ name + ".jpg";

            File file = new File(mLocalFile);

            try {

                if(!file.createNewFile()) {

                    System.out.println("File already exists");

                }

            } catch (IOException ex) {

                System.out.println(ex);

            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isSave(String name) {
        File file = new File(mDir + name + ".jpg");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public String getmLocalFile() {
        return mLocalFile;
    }
}
