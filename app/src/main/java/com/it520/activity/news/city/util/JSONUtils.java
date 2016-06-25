package com.it520.activity.news.city.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JSONUtils {
    public static <T> T parseInfoBean(String json, Class<?> type) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = (T) gson.fromJson(json, type);
            if(t == null) {
                throw new RuntimeException("parse json fail");
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }
}
