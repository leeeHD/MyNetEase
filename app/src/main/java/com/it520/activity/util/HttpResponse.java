package com.it520.activity.util;

/**
 * Created by kay on 16/5/10.
 */
public abstract class HttpResponse<T> {
    Class<T> tClass;

    public HttpResponse(Class<T> cls) {
        tClass = cls;
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String error);

    public void parseContent(String back) {
        if (tClass == Boolean.class) {
            onSuccess((T) Boolean.TRUE);
            return;
        }
        if (tClass == String.class) {
            onSuccess((T) back);
            return;
        }
        T object = JsonUtil.parse(back, tClass);
        if (object != null) {
            onSuccess(object);
        } else {
            String failMsg = "解析出错";
            onError(failMsg);
        }
    }
}

