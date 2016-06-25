package com.it520.activity.splash.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.it520.activity.R;
import com.it520.activity.main.IndexActivity;
import com.it520.activity.service.DownLoadImageService;
import com.it520.activity.splash.bean.Ads;
import com.it520.activity.splash.bean.Advert;
import com.it520.activity.splash.bean.LinksParams;
import com.it520.activity.splash.widght.RingTextView;
import com.it520.activity.splash.widght.RingTextViewOnClickListener;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.util.ImageUtile;
import com.it520.activity.util.JsonUtil;
import com.it520.activity.util.SharePreferenceUtil;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kay on 16/5/8.
 */
public class SplashActivity extends Activity {
    RingTextView ring;

    final String SPLASH_CONTENT = "splash_content";
    final String SPLASH_LASTTIME = "splash_last";
    final String SPLASH_TIME_OUT = "splash_time_out";
    final String SPLASH_PAGE_INDEX = "splash_page_index";
    MyHandler handler;
    final int TIME = 20;
    final static int UPADTE_RING = 101;
    final static int GOTOMAIN = 102;
    ImageView adv_img;
    boolean isWaiting =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ring = (RingTextView) findViewById(R.id.ring);
        ring.setVisibility(View.GONE);
        adv_img = (ImageView) findViewById(R.id.adv_img);

        handler = new MyHandler(this);
        // ring.setProgess(5,5);
        getData();
        getImage();
        ring.setClickListener(new RingTextViewOnClickListener() {
            @Override
            public void onClick(RingTextView text) {
                gotoMainActivity();
            }
        });


    }

    private void getData() {
        String content = SharePreferenceUtil.getString(this, SPLASH_CONTENT);
        if (content.equals("")) {
            getAds();
        } else {
            long lastTime = SharePreferenceUtil.getLong(SplashActivity.this, SPLASH_LASTTIME);
            int timeout = SharePreferenceUtil.getInt(SplashActivity.this, SPLASH_TIME_OUT);
            long nowTIME = new Date().getTime();
            if ((lastTime + timeout * 60 * 1000) < nowTIME) {
                getAds();
            }
        }

    }

    private void getImage() {
        String content = SharePreferenceUtil.getString(this, SPLASH_CONTENT);
        if (content.equals("")) {
            notShowImage();
        } else {
            Ads ads = JsonUtil.parse(content, Ads.class);
            showRingImage(ads);
        }

    }

    private void getAds() {
        HttpUtil.getInstance().doGet(Contance.SPLASH_TITLE, new HttpResponse<String>(String.class) {
            @Override
            public void onSuccess(String obj) {
                Ads ads = JsonUtil.parse(obj, Ads.class);
                if (ads != null) {
                    try {
                        SharePreferenceUtil.putString(SplashActivity.this, SPLASH_CONTENT, obj);
                        SharePreferenceUtil.putLong(SplashActivity.this, SPLASH_LASTTIME, new Date().getTime());
                        SharePreferenceUtil.putInt(SplashActivity.this, SPLASH_TIME_OUT, ads.getNext_req());

                        Intent intent = new Intent();
                        intent.setClass(SplashActivity.this, DownLoadImageService.class);
                        intent.putExtra(DownLoadImageService.ADS, ads);
                        startService(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onError(String out) {

            }
        });
    }


    private void updateRingimage(int now) {
        ring.setVisibility(View.VISIBLE);
        ring.setProgess(TIME, now);
        if (now == TIME) {
            gotoMainActivity();
        }
    }

    private void gotoWebActivity(LinksParams action_params) {
        Intent intent = new Intent();
        intent.setClass(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL_NAME, action_params);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);
        finish();
    }

    private void gotoMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this, IndexActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);
        finish();
    }

    private void showRingImage(Ads ads) {
        ring.setVisibility(View.VISIBLE);
        handler.post(ReshRing);
        int lastIndex = SharePreferenceUtil.getInt(this, SPLASH_PAGE_INDEX);
        Log.i("hked", "index = " + lastIndex);
        ArrayList<Advert> adverts = ads.getAds();
        int total = adverts.size();
        Log.i("hked", "total = " + total);

        Advert advert = adverts.get(lastIndex);
        if (lastIndex + 1 >= total) {
            lastIndex = 0;
        } else {
            lastIndex++;
        }
        List<String> urls = advert.getRes_url();

        if (!TextUtils.isEmpty(urls.get(0))) {
            ImageUtile util = new ImageUtile();
            String url = urls.get(0);
            final LinksParams action_params = advert.getAction_params();
            String imageName = util.getMd5Name(url);
            if (util.checkifImageExists(imageName)) {
                File file = util.getImage("/" + imageName + ".jpg");
                Bitmap bitmap = util.getImageBitmap(file.getAbsolutePath());
                adv_img.setImageBitmap(bitmap);
                adv_img.setTag(action_params);
                adv_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoWebActivity(action_params);
                    }
                });

                SharePreferenceUtil.putInt(this, SPLASH_PAGE_INDEX, lastIndex);

            }
        }
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(ReshRing);
        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(ReshRing);
        super.onDestroy();

    }

    private void notShowImage() {
        handler.postDelayed(gotoMain, 2000);
    }

    static class MyHandler extends Handler {
        WeakReference<SplashActivity> activity;
        int time = 0;

        public MyHandler(SplashActivity act) {
            this.activity = new WeakReference<SplashActivity>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity act = activity.get();
            switch (msg.what) {
                case UPADTE_RING:
                    if (time > act.TIME) {
                        removeCallbacks(act.ReshRing);
                    }
                    if (act != null) {
                        act.updateRingimage(time);
                    }
                    time++;
                    break;
                case GOTOMAIN:
                    if (act != null) {
                        act.gotoMainActivity();
                    }
                    break;
            }
        }
    }

    Runnable ReshRing = new Runnable() {
        @Override
        public void run() {

            handler.sendEmptyMessage(UPADTE_RING);
            handler.postDelayed(ReshRing, 250);
        }
    };

    Runnable gotoMain = new Runnable() {
        @Override
        public void run() {

                handler.sendEmptyMessage(GOTOMAIN);


        }
    };
}

