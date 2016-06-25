package com.it520.activity.news.beauty.bean;

/**
 * CEO:mac on 2016/6/19 20:57.
 * FUNCTION:
 */
public class ImageInfo {
    private String ref;
    private String src;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    public String toString() {
        return "ImageInfo{" +
                "ref='" + ref + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
