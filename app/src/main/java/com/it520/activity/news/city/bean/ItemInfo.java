package com.it520.activity.news.city.bean;

import java.util.List;

public class ItemInfo {private String postid;
    private String logo;
    private int votecount;
    private int replyCount;
    private int hasImg;
    private String digest;
    private String url;
    private String docid;
    private String title;
    private int hasAD;
    private String source;
    private List<ImgextraBean> imgextra; // 多张图片的URL集合
    private int order;
    private int cityType;
    private String lmodify;
    private String subtitle;
    private String imgsrc;
    private String boardid;
    private List<?> ad;
    private List<WapPortalV2Bean> wap_portalV2;
    private String partner;
    private String ptime;

    // 专题模式
    private String skipID;
    private String skipType;
    private String specialID;

    // 直播
    private List<?> editor;
    private String TAGS;
    private int imgType;
    private int priority;
    private LiveInfoBean live_info;
    private String TAG;
    public static class LiveInfoBean {
        /**
         * mutilVideo : true
         * pano : false
         * end_time : 2016-06-19 18:30:00
         * user_count : 42189
         * start_time : 2016-06-19 08:00:00
         * roomId : 84007
         * type : 0
         * video : false
         */
        private boolean mutilVideo;
        private boolean pano;
        private String end_time;
        private int user_count;
        private String start_time;
        private int roomId;
        private int type;
        private boolean video;

        public void setMutilVideo(boolean mutilVideo) {
            this.mutilVideo = mutilVideo;
        }

        public void setPano(boolean pano) {
            this.pano = pano;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public void setUser_count(int user_count) {
            this.user_count = user_count;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public boolean isMutilVideo() {
            return mutilVideo;
        }

        public boolean isPano() {
            return pano;
        }

        public String getEnd_time() {
            return end_time;
        }

        public int getUser_count() {
            return user_count;
        }

        public String getStart_time() {
            return start_time;
        }

        public int getRoomId() {
            return roomId;
        }

        public int getType() {
            return type;
        }

        public boolean isVideo() {
            return video;
        }
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public List<?> getEditor() {
        return editor;
    }

    public void setEditor(List<?> editor) {
        this.editor = editor;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LiveInfoBean getLive_info() {
        return live_info;
    }

    public void setLive_info(LiveInfoBean live_info) {
        this.live_info = live_info;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setCityType(int cityType) {
        this.cityType = cityType;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public void setAd(List<?> ad) {
        this.ad = ad;
    }

    public void setWap_portalV2(List<WapPortalV2Bean> wap_portalV2) {
        this.wap_portalV2 = wap_portalV2;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getPostid() {
        return postid;
    }

    public String getLogo() {
        return logo;
    }

    public int getVotecount() {
        return votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public int getHasImg() {
        return hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public String getUrl() {
        return url;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public int getHasAD() {
        return hasAD;
    }

    public String getSource() {
        return source;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public int getOrder() {
        return order;
    }

    public int getCityType() {
        return cityType;
    }

    public String getLmodify() {
        return lmodify;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getBoardid() {
        return boardid;
    }

    public List<?> getAd() {
        return ad;
    }

    public List<WapPortalV2Bean> getWap_portalV2() {
        return wap_portalV2;
    }

    public String getPartner() {
        return partner;
    }

    public String getPtime() {
        return ptime;
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/c701056dc3504d919e0db7ac9f4794d820160619112822.jpeg
         */
        private String imgsrc;

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        @Override
        public String toString() {
            return "ImgextraBean{" +
                    "imgsrc='" + imgsrc + '\'' +
                    '}';
        }
    }

    public static class WapPortalV2Bean {
        @Override
        public String toString() {
            return "WapPortalV2Bean{" +
                    "wap_url='" + wap_url + '\'' +
                    ", wap_title='" + wap_title + '\'' +
                    ", wap_desc='" + wap_desc + '\'' +
                    ", wap_img='" + wap_img + '\'' +
                    '}';
        }

        /**
         * wap_url : http://m.bendi.163.com/bj/affair/index
         * wap_title : 有事
         * wap_desc : 有事
         * wap_img : http://img4.cache.netease.com/3g/2016/1/4/201601041702319401b.png
         */
        private String wap_url;
        private String wap_title;
        private String wap_desc;
        private String wap_img;

        public void setWap_url(String wap_url) {
            this.wap_url = wap_url;
        }

        public void setWap_title(String wap_title) {
            this.wap_title = wap_title;
        }

        public void setWap_desc(String wap_desc) {
            this.wap_desc = wap_desc;
        }

        public void setWap_img(String wap_img) {
            this.wap_img = wap_img;
        }

        public String getWap_url() {
            return wap_url;
        }

        public String getWap_title() {
            return wap_title;
        }

        public String getWap_desc() {
            return wap_desc;
        }

        public String getWap_img() {
            return wap_img;
        }
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "postid='" + postid + '\'' +
                ", logo='" + logo + '\'' +
                ", votecount=" + votecount +
                ", replyCount=" + replyCount +
                ", hasImg=" + hasImg +
                ", digest='" + digest + '\'' +
                ", url='" + url + '\'' +
                ", docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", hasAD=" + hasAD +
                ", source='" + source + '\'' +
                ", imgextra=" + imgextra +
                ", order=" + order +
                ", cityType=" + cityType +
                ", lmodify='" + lmodify + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", boardid='" + boardid + '\'' +
                ", ad=" + ad +
                ", wap_portalV2=" + wap_portalV2 +
                ", partner='" + partner + '\'' +
                ", ptime='" + ptime + '\'' +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", specialID='" + specialID + '\'' +
                ", editor=" + editor +
                ", TAGS='" + TAGS + '\'' +
                ", imgType=" + imgType +
                ", priority=" + priority +
                ", live_info=" + live_info +
                ", TAG='" + TAG + '\'' +
                '}';
    }
}
