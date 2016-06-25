package com.it520.activity.news.sport.bean;

import com.it520.activity.news.bean.NewsAds;

import java.util.List;

/**
 * @author moy
 * @time 2016/6/19  16:02
 * @desc ${TODD}
 */

public class SportDetail {
    //第一条的广告
    List<NewsAds> ads;
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

    /* ******第二种 多图模式****** */
    List<Imgextra> imgextra;

    //判断是否是多图模式
    public boolean isMulti() {
        if (imgextra != null && imgextra.size() > 0) {
            return true;
        }
        return false;
    }

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

    public List<Imgextra> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<Imgextra> imgextra) {
        this.imgextra = imgextra;
    }

    @Override
    public String toString() {
        return "SportDetail{" +
                "ads=" + ads +
                ", title='" + title + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", docid='" + docid + '\'' +
                ", replyCount=" + replyCount +
                ", specialID='" + specialID + '\'' +
                ", source='" + source + '\'' +
                ", digest='" + digest + '\'' +
                ", imgextra=" + imgextra +
                '}';
    }
}
