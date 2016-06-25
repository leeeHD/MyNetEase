package com.it520.activity.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.it520.activity.splash.bean.Ads;
import com.it520.activity.splash.bean.Advert;
import com.it520.activity.util.Contance;
import com.it520.activity.util.ImageUtile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kay on 16/5/10.
 */
public class DownLoadImageService extends IntentService {
    public final static String ADS= "ads";
    Ads ads;

    public DownLoadImageService() {
        super("DownLoadImageService");
        this.ads = ads;
    }

    public DownLoadImageService(Ads ads) {
        super("DownLoadImageService");
        this.ads = ads;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ads = (Ads) intent.getSerializableExtra(ADS);
        Log.i("hked","start Service"+(ads==null));
        if (ads != null) {
            ImageUtile util = new ImageUtile();
            ArrayList<Advert> data = ads.getAds();
            if (data != null && data.size() == 0) {
                return;
            }
            for (Advert tmp : data) {
                List<String> images = tmp.getRes_url();
                if (images != null && !TextUtils.isEmpty(images.get(0))) {
                    String imgUrl = images.get(0);
                    String imageName = util.getMd5Name(imgUrl);
                    if (!util.checkifImageExists(imageName)) {
                        downLoadImage(imgUrl,imageName);
                    }
                }
            }

        }
    }

    private void downLoadImage(String imageUrl,String imageName) {
        URL url = null;
        try {
            url = new URL(imageUrl);
            URLConnection conn = url.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            saveToSdCard(bitmap, imageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveToSdCard(Bitmap bitmap, String filename) {

        String stored = null;

        File sdcard = Environment.getExternalStorageDirectory();

        File folder = new File(sdcard.getAbsoluteFile(), Contance.FLODERNAME);
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), filename + ".jpg");
        if (file.exists())
            return stored;

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            stored = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stored;
    }


}
