package com.it520.activity.topic.model;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TopicApi {
    private static final String TAG = "TopicApi";

    public static <T> List<T> getListBean(String url, Class<?> tClass, String key) throws IOException, JSONException {
        String jsonString = getJsonString(url);
        Log.d(TAG, "话题 -- " + jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        Log.d(TAG, jsonArray.toString());
        List<T> list = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            String json = jsonArray.getString(i);
            T subjBean = (T) new Gson().fromJson(json, tClass);
            list.add(subjBean);
        }

        return list;
    }

    public static String getJsonString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response =  client.newCall(request).execute();
            return response.body().string();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T parse(String url, Class<T> valueType) throws IOException {
        String jsonString = getJsonString(url);
        if(jsonString == null) {
            return null;
        }

        return new Gson().fromJson(jsonString, valueType);
    }
}
