package com.it520.activity.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BitmapUtil {
    public static Bitmap getRoundBitmap(Bitmap bitmap) {
        // 创建一个新的Bitmap和画板
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        // 创建画笔
        final Paint paint = new Paint();
        // 创建边界
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        // 画一个圆形
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 3, paint);

        // 设置遮罩
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // 切割图像
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    // 网络获取两张图片，合并到一个ImageView中
    // 使用时注意线程
    public static void overlay(Bitmap bmp1, Bitmap bmp2, final ImageView imageView) {
        // 1. 创建背景大小的bitmap
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        int w = canvas.getWidth() / 2;
        int h = canvas.getHeight();

        // 2. 拿到原来的Bitmap，作宽高的scale处理
        // Bitmap.createScaledBitmap(bmp1, w, h, false)
        canvas.drawBitmap(Bitmap.createScaledBitmap(bmp1, w, h, false), 0, 0, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(bmp2, w, h, false), w, 0, null);

        // 3. 设置图片到ImageView
        imageView.setImageBitmap(bmOverlay);
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
