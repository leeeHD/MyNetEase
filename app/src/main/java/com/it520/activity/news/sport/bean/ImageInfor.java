package com.it520.activity.news.sport.bean;

/**
 * @author moy
 * @time 2016/6/20  10:09
 * @desc 详情中的图片
 */

public class ImageInfor {
    private String alt;//图片的标题
    private String src;
    private String ref;//替代位置


    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "ImageInfor{" +
                "alt='" + alt + '\'' +
                ", src='" + src + '\'' +
                ", ref='" + ref + '\'' +
                '}';
    }
}
