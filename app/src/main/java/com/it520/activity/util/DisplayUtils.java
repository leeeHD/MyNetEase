package com.it520.activity.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Yu xiangxin on 2016/5/28 - 13:02.
 *
 * @desc ${TODD}
 */
public class DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * （DisplayMetrics类中属性density）
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * （DisplayMetrics类中属性density）
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * （DisplayMetrics类中属性scaledDensity）
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static SpannableString setTextSpan(String text, int dp, int startIndex, int endIndent) {
        SpannableString span = new SpannableString(text);
        span.setSpan(new AbsoluteSizeSpan(DisplayUtils.dip2px(UIUtils.getContext(), dp)), startIndex, endIndent,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    public static SpannableString setTextSpanColor(String text, int color, int startIndex, int endIndent) {
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        ss.setSpan(span, startIndex, endIndent, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }


}
