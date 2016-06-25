package com.it520.activity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

/**
 * Created by kay on 16/5/11.
 */
public class ImageUtile {


    public String getMd5Name(String url) {
        return Md5Helper.toMD5(url);
    }

    public File getImage(String imagename) {

        File mediaImage = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root);
            if (!myDir.exists())
                return null;

            mediaImage = new File(myDir.getPath() + "/" + Contance.FLODERNAME + imagename);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mediaImage;
    }

    public Bitmap getImageBitmap(String photoPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
        return bitmap;
    }

    public boolean checkifImageExists(String imagename) {
        Bitmap b = null;
        File file = getImage("/" + imagename + ".jpg");
        String path = file.getAbsolutePath();

        if (path != null)
            b = BitmapFactory.decodeFile(path);

        if (b == null || b.equals("")) {
            return false;
        }
        return true;
    }

    private static DisplayImageOptions options = null;

    public static DisplayImageOptions initOptions(Context context) {
        if (options == null) {
            synchronized (ImageUtile.class) {
                if (options == null) {
                    options = new DisplayImageOptions.Builder()
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .bitmapConfig(Bitmap.Config.RGB_565)
                            .build();
                }
            }
        }
        return options;
    }


    public static void LoadImage(String url, ImageView target) {
        if (TextUtils.isEmpty(url) || target == null)
            return;
        initOptions(UIUtils.getContext());
       ImageLoader.getInstance().displayImage(url, target, options);
    }
}























