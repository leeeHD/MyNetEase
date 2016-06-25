package com.it520.activity.util;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Yu xiangxin on 2016/5/25 - 17:22.
 *
 * @desc ${TODD}
 */
public class MyTempUtil {

    public static View getTextView(String showName) {
        TextView text = new TextView(UIUtils.getContext());//
        text.setText(showName);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        text.setLayoutParams(params);
        text.setTextSize(40);
        text.setTextColor(Color.BLUE);
        text.setGravity(Gravity.CENTER);
        return text;


    }

}
