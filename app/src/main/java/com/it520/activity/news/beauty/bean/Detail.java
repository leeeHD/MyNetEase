package com.it520.activity.news.beauty.bean;

import java.util.List;

/**
 * CEO:mac on 2016/6/19 20:56.
 * FUNCTION:
 */
public class Detail {
    private String body;
    private String title;
    private String ptime;
    private List<ImageInfo> img;
    private int replyCount;
    private String source;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public List<ImageInfo> getImg() {
        return img;
    }

    public void setImg(List<ImageInfo> img) {
        this.img = img;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "body='" + body + '\'' +
                ", title='" + title + '\'' +
                ", ptime='" + ptime + '\'' +
                ", img=" + img +
                ", replyCount=" + replyCount +
                ", source='" + source + '\'' +
                '}';
    }
}
