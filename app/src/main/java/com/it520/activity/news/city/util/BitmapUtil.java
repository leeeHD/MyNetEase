package com.it520.activity.news.city.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.it520.activity.news.city.bean.ItemInfo;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BitmapUtil {
    // RxJava实现
    public static void overlay(final List<ItemInfo.ImgextraBean> imageBean, final ImageView cityVpIv) {
        Observable
                .just(imageBean)
                .map(new Func1<List<ItemInfo.ImgextraBean>, Bitmap>() {
                    @Override
                    public Bitmap call(List<ItemInfo.ImgextraBean> imgextraBeen) {
                        // 1. 拿到两张图片的bitmap
                        Bitmap bmp1 = getBitmap(imageBean.get(0).getImgsrc());
                        Bitmap bmp2 = getBitmap(imageBean.get(1).getImgsrc());

                        // 2. 创建背景大小的bitmap
                        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
                        Canvas canvas = new Canvas(bmOverlay);
                        int w = canvas.getWidth() / 2;
                        int h = canvas.getHeight();

                        // 3. 拿到原来的Bitmap，作宽高的scale处理
                        // Bitmap.createScaledBitmap(bmp1, w, h, false)
                        canvas.drawBitmap(Bitmap.createScaledBitmap(bmp1, w, h, false), 0, 0, null);
                        canvas.drawBitmap(Bitmap.createScaledBitmap(bmp2, w, h, false), w, 0, null);

                        return bmOverlay;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 友情提示
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        // 4. 设置图片到ImageView
                        cityVpIv.setImageBitmap(bitmap);
                    }
                });

    }

    // 网络获取bitmap图片
    public static Bitmap getBitmap(String imgSrc) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(imgSrc).build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            Bitmap bitmap = BitmapFactory.decodeStream(body.byteStream());

            body.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
