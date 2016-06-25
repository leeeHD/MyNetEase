package com.it520.activity.util;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kay on 16/5/10.
 */
public class JsonUtil {
    public static Gson gson;

    public static <T> T parse(String response, Class<T> valueType) {
        if (TextUtils.isEmpty(response) || valueType == null) {
            return null;
        }
        try {
            if (gson == null) {
                gson = new Gson();
            }
            return gson.fromJson(response, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> parse(String response, Type listType) {
        if (TextUtils.isEmpty(response) || listType == null) {
            return null;
        }
        if (gson == null) {
            gson = new Gson();
        }
        try {
            return gson.fromJson(response, listType);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static Gson getGson(){
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
