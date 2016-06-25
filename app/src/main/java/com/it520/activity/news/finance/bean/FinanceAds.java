package com.it520.activity.news.finance.bean;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class FinanceAds {
    String imgsrc;
    String subtitle;
    String tag;
    String title;
    String url;

    public FinanceAds(String imgsrc, String subtitle, String tag, String title, String url) {
        this.imgsrc = imgsrc;
        this.subtitle = subtitle;
        this.tag = tag;
        this.title = title;
        this.url = url;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FinanceAds{" +
                "imgsrc='" + imgsrc + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
