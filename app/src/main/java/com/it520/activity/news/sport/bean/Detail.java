package com.it520.activity.news.sport.bean;

import java.util.List;

/**
 * @author moy
 * @time 2016/6/20  9:48
 * @desc 体育页面详情的bean
 */

public class Detail {
    private String body;
    private String source;
    private String title;
    private String ptime;
    private List<ImageInfor> img;
    private int replyCount;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<ImageInfor> getImg() {
        return img;
    }

    public void setImg(List<ImageInfor> img) {
        this.img = img;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "body='" + body + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", ptime='" + ptime + '\'' +
                ", img=" + img +
                ", replyCount=" + replyCount +
                '}';
    }
}
