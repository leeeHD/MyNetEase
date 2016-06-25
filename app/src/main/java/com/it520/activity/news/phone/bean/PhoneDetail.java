package com.it520.activity.news.phone.bean;

import java.util.List;

public class PhoneDetail {
    //第一条的广告
    List<PhoneAds> ads;

    private String alias;
    private String boardid;
    private String cid;
    private String digest;
    private String docid; //文章id
    private String ename;
    private int hasAD;
    private boolean hasCover;
    private int hasHead;
    private boolean hasIcon;
    private int hasImg;
    private String imgsrc; //图片id(未压缩)
    private String lmodify;
    private String ltitle;
    private int order;
    private String postid;
    private int priority;
    private String ptime;
    private int replyCount; //回复数
    private String source;  //资讯来源
    private String subtitle;
    private String template;
    private String title;   //标题
    private String tname;
    private String url;
    private String url_3w;
    private int votecount;

    public List<PhoneAds> getAds() {
        return ads;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    @Override
    public String toString() {
        return "PhoneDetail{" +
                "alias='" + alias + '\'' +
                ", boardid='" + boardid + '\'' +
                ", cid='" + cid + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", ename='" + ename + '\'' +
                ", hasAD=" + hasAD +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", hasIcon=" + hasIcon +
                ", hasImg=" + hasImg +
                ", imgsrc='" + imgsrc + '\'' +
                ", lmodify='" + lmodify + '\'' +
                ", ltitle='" + ltitle + '\'' +
                ", order=" + order +
                ", postid='" + postid + '\'' +
                ", priority=" + priority +
                ", ptime='" + ptime + '\'' +
                ", replyCount=" + replyCount +
                ", source='" + source + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", template='" + template + '\'' +
                ", title='" + title + '\'' +
                ", tname='" + tname + '\'' +
                ", url='" + url + '\'' +
                ", url_3w='" + url_3w + '\'' +
                ", votecount=" + votecount +
                '}';
    }
}
