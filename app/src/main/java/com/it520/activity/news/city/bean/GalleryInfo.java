package com.it520.activity.news.city.bean;

public class GalleryInfo {
    private NewsBean newsBean;
    private WhetherInfo whetherInfo;

    public NewsBean getNewsBean() {
        return newsBean;
    }

    public void setNewsBean(NewsBean newsBean) {
        this.newsBean = newsBean;
    }

    public WhetherInfo getWhetherInfo() {
        return whetherInfo;
    }

    public void setWhetherInfo(WhetherInfo whetherInfo) {
        this.whetherInfo = whetherInfo;
    }

    public class NewsBean {
        private String title;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "NewsBean{" +
                    "title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GalleryInfo{" +
                "newsBean=" + newsBean +
                ", whetherInfo=" + whetherInfo +
                '}';
    }
}
