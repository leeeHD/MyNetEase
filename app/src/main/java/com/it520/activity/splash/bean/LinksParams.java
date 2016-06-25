package com.it520.activity.splash.bean;

import java.io.Serializable;

/**
 * Created by kay on 16/5/10.
 */
public class LinksParams implements Serializable {
    private String link_url;

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    @Override
    public String toString() {
        return "LinksParams{" +
                "link_url='" + link_url + '\'' +
                '}';
    }
}
