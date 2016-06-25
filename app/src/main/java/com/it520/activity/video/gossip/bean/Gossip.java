package com.it520.activity.video.gossip.bean;

import java.io.Serializable;

/**
 * CEO:mac on 2016/6/20 16:59.
 * FUNCTION:
 */
public class Gossip implements Serializable{
    private String topicImg;
    private String title;
    private int playCount;
    private int length;
    private String topicName;
    private String cover;
    private String mp4_url;
    private int replyCount;
    private String vid;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public int getReplyCount() {
        return replyCount;
    }
    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Gossip{" +
                "topicImg='" + topicImg + '\'' +
                ", title='" + title + '\'' +
                ", playCount=" + playCount +
                ", length=" + length +
                ", topicName='" + topicName + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
