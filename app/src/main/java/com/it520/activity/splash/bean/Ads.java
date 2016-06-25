package com.it520.activity.splash.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kay on 16/5/10.
 */
public class Ads implements Serializable {
    ArrayList<Advert>ads;
    String error;
    int next_req;
    int result;

    public ArrayList<Advert> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Advert> ads) {
        this.ads = ads;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "Ads{" +
                "ads=" + ads +
                ", error='" + error + '\'' +
                ", next_req=" + next_req +
                ", result=" + result +
                '}';
    }
}
