package com.it520.activity.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kay on 16/5/9.
 */
public class HttpUtil {
    private static HttpUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Context context;


    private HttpUtil(){
        mOkHttpClient =  new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        mDelivery = new Handler(Looper.myLooper());

    }


    public void doGet(String url, final HttpResponse myResponse){
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                if(myResponse!=null){

                    myResponse.onError("连接服务器失败");
                }
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if(myResponse!=null){
                        myResponse.onError("连接服务器失败");
                    }
                }else{
                    if(myResponse!=null){
                      String json = response.body().string();
                        Log.d("net-test", json);
                        if(TextUtils.isEmpty(json)){
                            return;
                        }
                        myResponse.parseContent(json);

                    }
                }
            }
        });
    }

    public static HttpUtil getInstance() {
        if (mInstance == null) {
                 synchronized (HttpUtil.class){
                     if(mInstance == null){
                         mInstance = new HttpUtil();
                     }
                 }
        }
        return mInstance;
    }
}
