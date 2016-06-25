package com.it520.activity.splash.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kay on 16/5/10.
 */
public class Advert implements Serializable {

    LinksParams action_params;
    List<String> res_url;

    public LinksParams getAction_params() {
        return action_params;
    }

    public void setAction_params(LinksParams action_params) {
        this.action_params = action_params;
    }

    public List<String> getRes_url() {
        return res_url;
    }

    public void setRes_url(List<String> res_url) {
        this.res_url = res_url;
    }


    @Override
    public String toString() {
        return "Advert{" +
                "action_params=" + action_params +
                ", res_url=" + res_url +
                ", res_url size =" + res_url.size() +
                '}';
    }
}
