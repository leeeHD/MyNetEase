package com.it520.activity.topic.model.bean;

public class SubjBean {

    /**
     * 首个栏目的数据
     */
    private String picUrl;
    private int focusNum;
    private String topicId;
    private String topicName;

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setFocusNum(int focusNum) {
        this.focusNum = focusNum;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public int getFocusNum() {
        return focusNum;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    @Override
    public String toString() {
        return "SubjBean{" +
                "picUrl='" + picUrl + '\'' +
                ", focusNum=" + focusNum +
                ", topicId='" + topicId + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
