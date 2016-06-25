package com.it520.activity.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;
import android.widget.LinearLayout;

import com.it520.activity.MyApplication;


/**
 * Created by Yu xiangxin on 2016/5/24 - 13:13.
 *
 * @desc ${TODD}
 */
public class UIUtils {
    public static Context getContext() {
        return MyApplication.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String[] getStringArr(int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getMainThreadId() {
        return MyApplication.getMainId();
    }


    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static Handler getHandler() {
        return MyApplication.getHandler();
    }


    public static void postTaskSafe(Runnable task) {
        int mainId = getMainThreadId();
        int currentId = Process.myTid();
        if (mainId == currentId) {
            task.run();
        } else {
            getHandler().post(task);
        }
    }

    public static LinearLayout.LayoutParams getLinearLayoutMathParentParams() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static LinearLayout.LayoutParams getLinearLayoutWrapContentParams() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }


}
