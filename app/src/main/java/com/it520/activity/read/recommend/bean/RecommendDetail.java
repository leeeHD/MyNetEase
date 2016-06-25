package com.it520.activity.read.recommend.bean;

import com.it520.activity.news.sport.bean.Imgextra;

import java.util.List;

/**
 * @author moy
 * @time 2016/6/20  18:00
 * @desc 推荐阅读的细节
 */

public class RecommendDetail {
    String title;
    String recReason;//可选
    String source;
    String img;
    List<Imgextra> imgnewextra;//多图模式
    //详细id
    String docid;
    List<String> unlikeReason;//不喜欢原因

    //判断是否是多图模式
    public boolean isMulti() {
        if (imgnewextra != null && imgnewextra.size() > 0) {
            return true;
        }
        return false;
    }
    //判断是否是本地新闻
    public boolean isLocal() {
        if (recReason != null) {
            return true;
        }
        return false;
    }

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

    public String getRecReason() {
        return recReason;
    }

    public void setRecReason(String recReason) {
        this.recReason = recReason;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public List<Imgextra> getImgnewextra() {
        return imgnewextra;
    }

    public void setImgnewextra(List<Imgextra> imgnewextra) {
        this.imgnewextra = imgnewextra;
    }

    public List<String> getUnlikeReason() {
        return unlikeReason;
    }

    public void setUnlikeReason(List<String> unlikeReason) {
        this.unlikeReason = unlikeReason;
    }

    @Override
    public String toString() {
        return "RecommendDetail{" +
                "title='" + title + '\'' +
                ", recReason='" + recReason + '\'' +
                ", source='" + source + '\'' +
                ", img='" + img + '\'' +
                ", imgnewextra=" + imgnewextra +
                ", docid='" + docid + '\'' +
                ", unlikeReason=" + unlikeReason +
                '}';
    }
}
