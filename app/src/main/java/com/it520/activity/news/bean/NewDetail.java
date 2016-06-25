package com.it520.activity.news.bean;

import java.util.List;

/**
 * Created by kay on 16/5/14.
 */
public class NewDetail {
    //第一条的广告
    List<NewsAds> ads;
    //标题
    String title;
    //图片（压缩后）
    String img;
    //详细id
    String docid;
    //回复数量
    int replyCount;
    //专题id
    String specialID;
    //新闻来源
    String source;
    String digest;

    public List<NewsAds> getAds() {
        return ads;
    }

    public void setAds(List<NewsAds> ads) {
        this.ads = ads;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "PhoneDetail{" +
                "ads=" + ads +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", docid='" + docid + '\'' +
                ", replyCount=" + replyCount +
                ", specialID='" + specialID + '\'' +
                ", source='" + source + '\'' +
                ", digest='" + digest + '\'' +
                '}';
    }
}
