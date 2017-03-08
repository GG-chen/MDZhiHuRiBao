package com.example.shinelon.mymdapp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Shinelon on 2017/3/4.
 */

public class BitmapSaveUtil {
    private final String dir;
    public static BitmapSaveUtil saveUtil;
    private Context context;

    public BitmapSaveUtil(Context context) {
        this.context = context;
        dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mdapp/";
    }

    public static BitmapSaveUtil getSaveUtil(Context context) {
        if (saveUtil == null) {
            saveUtil = new BitmapSaveUtil(context);
        }
        return saveUtil;
    }

    public boolean saveBitmap(Bitmap bitmap, String name) {
        String state = Environment.getExternalStorageState();
            //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        try {
            File file = new File(dir + name + ".jpg");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isSave(String name) {
        File file = new File(dir + name + ".jpg");
        if (file.exists()) {
            return true;
        }
        return false;
    }

}
