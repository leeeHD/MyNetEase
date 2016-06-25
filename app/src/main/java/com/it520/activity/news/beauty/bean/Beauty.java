package com.it520.activity.news.beauty.bean;

/**
 * CEO:mac on 2016/6/19 14:37.
 * FUNCTION:
 */
public class Beauty {
    private String title;
    private String imgsrc;
    private int replyCount;
    private String source;
    private int downTimes;
    private int upTimes;
    private String docid;
    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
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

    public int getDownTimes() {
        return downTimes;
    }

    public void setDownTimes(int downTimes) {
        this.downTimes = downTimes;
    }

    public int getUpTimes() {
        return upTimes;
    }

    public void setUpTimes(int upTimes) {
        this.upTimes = upTimes;
    }
    public String toString() {
        return "Beauty{" +
                "docid='" + docid + '\'' +
                ", upTimes=" + upTimes +
                ", downTimes=" + downTimes +
                ", source='" + source + '\'' +
                ", replyCount=" + replyCount +
                ", imgsrc='" + imgsrc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
