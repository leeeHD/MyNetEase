package com.it520.activity.news.sport.util;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * @author moy
 * @time 2016/6/20  9:43
 * @desc ${TODD}
 */

public class GsonUtil {
    public static Gson gson;

    /**
     * 解析
     *
     * @param response
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parse(String response, Class<T> valueType) {
        if (TextUtils.isEmpty(response) || valueType == null) {
            return null;
        }
        try {
            if (gson == null) {
                gson = new Gson();
            }
            System.out.println("response" + response);

            return gson.fromJson(response, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }

        return gson;
    }
    public static String toJson(Object o) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(o);
    }

}
