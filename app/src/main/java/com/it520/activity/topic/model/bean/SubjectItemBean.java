package com.it520.activity.topic.model.bean;

import java.util.List;

public class SubjectItemBean {
    /**
     * createTime : 1461330247642
     * talkPicture : ["http://mobilepics.nosdn.127.net/netease_subject/867464022080170/1466311271125631858","http://mobilepics.nosdn.127.net/netease_subject/866722020039568/null","http://mobilepics.nosdn.127.net/netease_subject/868853025923205/1466330656859141660"]
     * alias : “铲屎的！快来伺候爷！”
     * talkCount : 367
     * classification : 生活
     * name : 铲屎官日志
     * subjectId : SJ05561786630961052762
     * state : 0
     * picurl : http://dingyue.nosdn.127.net/6wBBpM39zci9DkKAzOpJbd=OUvTnBrC5AK8Yq6fhO5fbw1461332308117.jpg
     * feature : 萌宠：0，宠物：0
     * type : 1
     * concernCount : 6121
     */
    private long createTime;
    private List<String> talkPicture;
    private String alias;
    private int talkCount;
    private String classification;
    private String name;
    private String subjectId;
    private int state;
    private String picurl;
    private String feature;
    private int type;
    private int concernCount;
    private List<TalkContentBean> talkContent;

    public List<TalkContentBean> getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(List<TalkContentBean> talkContent) {
        this.talkContent = talkContent;
    }

    public static class TalkContentBean {
        private String talkId;
        private String content;
        private String userHeadPicUrl;

        public String getTalkId() {
            return talkId;
        }

        public void setTalkId(String talkId) {
            this.talkId = talkId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUserHeadPicUrl() {
            return userHeadPicUrl;
        }

        public void setUserHeadPicUrl(String userHeadPicUrl) {
            this.userHeadPicUrl = userHeadPicUrl;
        }

        @Override
        public String toString() {
            return "TalkContentBean{" +
                    "talkId='" + talkId + '\'' +
                    ", content='" + content + '\'' +
                    ", userHeadPicUrl='" + userHeadPicUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SubjectItemBean{" +
                "createTime=" + createTime +
                ", talkPicture=" + talkPicture +
                ", alias='" + alias + '\'' +
                ", talkCount=" + talkCount +
                ", classification='" + classification + '\'' +
                ", name='" + name + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", state=" + state +
                ", picurl='" + picurl + '\'' +
                ", feature='" + feature + '\'' +
                ", type=" + type +
                ", concernCount=" + concernCount +
                ", talkContent=" + talkContent +
                '}';
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setTalkPicture(List<String> talkPicture) {
        this.talkPicture = talkPicture;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setTalkCount(int talkCount) {
        this.talkCount = talkCount;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setConcernCount(int concernCount) {
        this.concernCount = concernCount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public List<String> getTalkPicture() {
        return talkPicture;
    }

    public String getAlias() {
        return alias;
    }

    public int getTalkCount() {
        return talkCount;
    }

    public String getClassification() {
        return classification;
    }

    public String getName() {
        return name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getState() {
        return state;
    }

    public String getPicurl() {
        return picurl;
    }

    public String getFeature() {
        return feature;
    }

    public int getType() {
        return type;
    }

    public int getConcernCount() {
        return concernCount;
    }
}
