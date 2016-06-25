package com.it520.activity.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.it520.activity.R;


/**
 * Created by Yu xiangxin on 2016/6/2 - 13:25.
 *
 * @desc ${TODD}
 */
public class ActivityUtils {

    public static void startActivity(final Context context, Class targetClz, final long delay, final boolean closeSelf) {
        final Intent intent = new Intent(context, targetClz);
        startActivity(context, intent, delay, closeSelf);
    }

    public static void startActivity(final Context context, Class targetClz, final long delay) {
        startActivity(context, targetClz, delay, false);
    }

    public static void startActivity(final Context context, Class targetClz) {
        startActivity(context, targetClz, 0, false);
    }

    public static void startActivity(final Context context, Class targetClz, final boolean closeSelf) {
        startActivity(context, targetClz, 0, closeSelf);
    }

    public static void startActivity(final Context context, final Intent target, final long delay, final boolean closeSelf) {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(delay);
                context.startActivity(target);
                ((Activity) context).overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);
                /************进入动画***********/
                if (closeSelf)
                    ((Activity) context).finish();
            }
        }.start();
    }
}
