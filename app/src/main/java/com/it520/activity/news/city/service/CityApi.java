package com.it520.activity.news.city.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it520.activity.news.city.bean.CityInfo;
import com.it520.activity.news.city.bean.ItemInfo;
import com.it520.activity.news.city.bean.WhetherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CityApi {
    private static final String TAG = "CityApi";

    public static List<ItemInfo> getItemInfos(String url) throws IOException {
        String json = getJsonString(url);

        String jsonFormatted = formatJson(json);
        Log.d(TAG, jsonFormatted);

        Gson gson = new Gson();
        Type type = new TypeToken<CityInfo<ItemInfo>>(){}.getType();
        CityInfo infos = gson.fromJson(jsonFormatted, type);
        List infos1 = infos.getInfos();

        return infos1;
    }

    private static String getJsonString(String url) throws IOException {
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

    public static WhetherInfo getWhetherInfos(String url) throws IOException, JSONException {
        String jsonString = getJsonString(url);
        Gson gson = new Gson();
        WhetherInfo whetherInfo = gson.fromJson(jsonString, WhetherInfo.class);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray =
                jsonObject.getJSONArray("北京|北京");
        List<WhetherInfo.DayInfo> dayInfos = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d(TAG, jsonArray.toString());
            String json = jsonArray.getString(i);
            dayInfos.add(gson.fromJson(json, WhetherInfo.DayInfo.class));
        }

        whetherInfo.setDayInfos(dayInfos);
        return whetherInfo;
    }

    public static String formatJson(String json) {
        String limiter = "\"";
        int end = json.indexOf(limiter, 2);
        String newJson = "{\"infos\"" + json.substring(end + 1);
        return newJson;
    }
}
