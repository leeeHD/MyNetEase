package com.it520.activity.news.finance.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class FinanceDetail {
    //第一条的广告
    List<FinanceAds> ads;
    //标题
    String title;
    //图片（压缩后）
    String imgsrc;
    //详细id
    String docid;
    //回复数量
    int replyCount;
    //专题id
    String specialID;
    //新闻来源
    String source;
    String digest;

    public List<FinanceAds> getAds() {
        return ads;
    }

    public void setAds(List<FinanceAds> ads) {
        this.ads = ads;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
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

    @Override
    public String toString() {
        return "FinanceDetail{" +
                "ads=" + ads +
                ", title='" + title + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", docid='" + docid + '\'' +
                ", replyCount=" + replyCount +
                ", specialID='" + specialID + '\'' +
                ", source='" + source + '\'' +
                ", digest='" + digest + '\'' +
                '}';
    }
}
