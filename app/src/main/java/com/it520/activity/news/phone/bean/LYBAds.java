package com.it520.activity.news.phone.bean;

/**
 * Created by laiyubin on 2016/6/21/021.
 */
public class LYBAds {
    private String title;
    private String docId;
    private String imgUrl;
    private String link_url;

    @Override
    public String toString() {
        return "LYBAds{" +
                "title='" + title + '\'' +
                ", docId='" + docId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", link_url='" + link_url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
}
