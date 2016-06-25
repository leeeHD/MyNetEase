package com.it520.activity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.it520.activity.util.HttpUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kay on 16/5/9.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        mMainTid = android.os.Process.myTid();// T 线程
        mHandler = new Handler();


        HttpUtil util = HttpUtil.getInstance();

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

    private static Context mContext;
    private static int mMainTid;
    private static Handler mHandler;


    public static Context getContext() {
        return mContext;
    }

    public static int getMainId() {
        return mMainTid;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getProtocolCache() {
        return map;
    }

}
