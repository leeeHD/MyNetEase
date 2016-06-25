package com.it520.activity.news.scienci.bean;

import java.util.List;

/**
 * Created by Yu xiangxin on 2016/6/19 - 13:18.
 *
 * @desc ${TODD}
 */
public class Science {
    private List<ScienceItem> T1348649580692;


    public List<ScienceItem> getT1348649580692() {
        return T1348649580692;
    }

    public void setT1348649580692(List<ScienceItem> t1348649580692) {
        T1348649580692 = t1348649580692;
    }

    @Override
    public String toString() {
        return "Science{" +
                "T1348649580692=" + T1348649580692 +
                '}';
    }

    public static class ScienceItem {
        public static class ScienceItemAds {

            /**
             * docid : BPEQSMLI00097U7T
             * title : 苹果造车有两大必然性 但面临着七大挑战
             * tag : doc
             * subtitle :
             * imgsrc : http://cms-bucket.nosdn.127.net/e8a2b898ace8441f84b32d8b3179d52a20160613164730.jpeg
             * url : BPEQSMLI00097U7T
             */
            private String docid;
            private String title;
            private String tag;
            private String subtitle;
            private String imgsrc;
            private String url;

            @Override
            public String toString() {
                return "ScienceItemAds{" +
                        "docid='" + docid + '\'' +
                        ", title='" + title + '\'' +
                        ", tag='" + tag + '\'' +
                        ", subtitle='" + subtitle + '\'' +
                        ", imgsrc='" + imgsrc + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }

            public void setDocid(String docid) {
                this.docid = docid;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDocid() {
                return docid;
            }

            public String getTitle() {
                return title;
            }

            public String getTag() {
                return tag;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public String getUrl() {
                return url;
            }
        }

        private List<ScienceItemAds> ads;

        private String digest;
        private String docid;//文章id
        private String imgsrc;//图片
        private String lmodify;
        private String ltitle;//长标题
        private int priority;
        private String ptime;//
        private int replyCount;//回复数
        private String source;//来源
        private String title;//标题
        private String url;//移动风格
        private String url_3w;//桌面版
        private int votecount;

        public List<ScienceItemAds> getAds() {
            return ads;
        }

        public void setAds(List<ScienceItemAds> ads) {
            this.ads = ads;
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
            return "ScienceItem{" +
                    "ads=" + ads +
                    ", digest='" + digest + '\'' +
                    ", docid='" + docid + '\'' +
                    ", imgsrc='" + imgsrc + '\'' +
                    ", lmodify='" + lmodify + '\'' +
                    ", ltitle='" + ltitle + '\'' +
                    ", priority=" + priority +
                    ", ptime='" + ptime + '\'' +
                    ", replyCount=" + replyCount +
                    ", source='" + source + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", url_3w='" + url_3w + '\'' +
                    ", votecount=" + votecount +
                    '}';
        }
    }


}
