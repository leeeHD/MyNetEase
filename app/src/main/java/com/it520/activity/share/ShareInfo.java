package com.it520.activity.share;

import java.io.Serializable;

/**
 * Created by Yu xiangxin on 2016/6/22 - 1:22.
 *
 * @desc ${TODD}
 */
public class ShareInfo implements Serializable {

    public static final int MAX_LENGTH = 70;//= 140 / 2

    /**
     * 必给字段
     */
    private String imgUrl;//预览图片 小尺寸
    private String url;//链接
    private String text;// 主题,
    private String title; // 标题

    /**
     * 以下字段仅当需要是赋值,否则可以不提供
     */
    private String site = "小马哥新闻"; // site是分享此内容的网站名称，仅在QQ空间使用
    private String comment;//是我对这条分享的评论，仅在人人网和QQ空间使用
    private String address;//邮件地址 和 信息 需要作为联系人的地址



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShareInfo{" +
                "site='" + site + '\'' +
                ", url='" + url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", comment='" + comment + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title != null ? title.length() > MAX_LENGTH ? title.substring(0, MAX_LENGTH) + "..." : title : "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text != null ? text.length() > MAX_LENGTH ? text.substring(0, MAX_LENGTH) + "..." : text : "";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
