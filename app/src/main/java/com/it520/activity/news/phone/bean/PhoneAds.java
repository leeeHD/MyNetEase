package com.it520.activity.news.phone.bean;

import java.util.List;

public class PhoneAds {

    /**
     * result : 0
     * error :
     * ads : [{"ad_loc":6,"vdog":"","location":"10","thirdplat":0,"expired_time":1467388799000,"action_params":{"link_url":"http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]"},"sub_title":"","loop_time":0,"vdot":"","id":"47565","style":"3","video_url":"","action":"1","flight_id":"762","show_time":"3.5","vdeg":"","show_num":3,"monitor":"","monitorClickUrl":"","vdet":"","content":"","is_sens":0,"category":"T1348649654285","ratio":"100.0","res_url":["http://img1.126.net/channel14/16kaola_1080624_0619.jpg","","",""],"monitorShowUrl":"","main_title":"送你680元礼包买国外美妆","ad_type":0}]
     * next_req : 600
     */
    private int result;
    private String error;
    private List<AdsBean> ads;
    private int next_req;

    @Override
    public String toString() {
        return "PhoneAds{" +
                "result=" + result +
                ", error='" + error + '\'' +
                ", ads=" + ads +
                ", next_req=" + next_req +
                '}';
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public int getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public int getNext_req() {
        return next_req;
    }

    public static class AdsBean {

        @Override
        public String toString() {
            return "AdsBean{" +
                    "ad_loc=" + ad_loc +
                    ", vdog='" + vdog + '\'' +
                    ", location='" + location + '\'' +
                    ", thirdplat=" + thirdplat +
                    ", expired_time=" + expired_time +
                    ", action_params=" + action_params +
                    ", sub_title='" + sub_title + '\'' +
                    ", loop_time=" + loop_time +
                    ", vdot='" + vdot + '\'' +
                    ", id='" + id + '\'' +
                    ", style='" + style + '\'' +
                    ", video_url='" + video_url + '\'' +
                    ", action='" + action + '\'' +
                    ", flight_id='" + flight_id + '\'' +
                    ", show_time='" + show_time + '\'' +
                    ", vdeg='" + vdeg + '\'' +
                    ", show_num=" + show_num +
                    ", monitor='" + monitor + '\'' +
                    ", monitorClickUrl='" + monitorClickUrl + '\'' +
                    ", vdet='" + vdet + '\'' +
                    ", content='" + content + '\'' +
                    ", is_sens=" + is_sens +
                    ", category='" + category + '\'' +
                    ", ratio='" + ratio + '\'' +
                    ", res_url=" + res_url +
                    ", monitorShowUrl='" + monitorShowUrl + '\'' +
                    ", main_title='" + main_title + '\'' +
                    ", ad_type=" + ad_type +
                    '}';
        }

        /**
         * ad_loc : 6
         * vdog :
         * location : 10
         * thirdplat : 0
         * expired_time : 1467388799000
         * action_params : {"link_url":"http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]"}
         * sub_title :
         * loop_time : 0
         * vdot :
         * id : 47565
         * style : 3
         * video_url :
         * action : 1
         * flight_id : 762
         * show_time : 3.5
         * vdeg :
         * show_num : 3
         * monitor :
         * monitorClickUrl :
         * vdet :
         * content :
         * is_sens : 0
         * category : T1348649654285
         * ratio : 100.0
         * res_url : ["http://img1.126.net/channel14/16kaola_1080624_0619.jpg","","",""]
         * monitorShowUrl :
         * main_title : 送你680元礼包买国外美妆
         * ad_type : 0
         */
        private int ad_loc;
        private String vdog;
        private String location;
        private int thirdplat;
        private long expired_time;
        private ActionParamsBean action_params;
        private String sub_title;
        private int loop_time;
        private String vdot;
        private String id;
        private String style;
        private String video_url;
        private String action;
        private String flight_id;
        private String show_time;
        private String vdeg;
        private int show_num;
        private String monitor;
        private String monitorClickUrl;
        private String vdet;
        private String content;
        private int is_sens;
        private String category;
        private String ratio;
        private List<String> res_url;
        private String monitorShowUrl;
        private String main_title;
        private int ad_type;

        public void setAd_loc(int ad_loc) {
            this.ad_loc = ad_loc;
        }

        public void setVdog(String vdog) {
            this.vdog = vdog;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setThirdplat(int thirdplat) {
            this.thirdplat = thirdplat;
        }

        public void setExpired_time(long expired_time) {
            this.expired_time = expired_time;
        }

        public void setAction_params(ActionParamsBean action_params) {
            this.action_params = action_params;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public void setLoop_time(int loop_time) {
            this.loop_time = loop_time;
        }

        public void setVdot(String vdot) {
            this.vdot = vdot;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public void setFlight_id(String flight_id) {
            this.flight_id = flight_id;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public void setVdeg(String vdeg) {
            this.vdeg = vdeg;
        }

        public void setShow_num(int show_num) {
            this.show_num = show_num;
        }

        public void setMonitor(String monitor) {
            this.monitor = monitor;
        }

        public void setMonitorClickUrl(String monitorClickUrl) {
            this.monitorClickUrl = monitorClickUrl;
        }

        public void setVdet(String vdet) {
            this.vdet = vdet;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIs_sens(int is_sens) {
            this.is_sens = is_sens;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public void setRes_url(List<String> res_url) {
            this.res_url = res_url;
        }

        public void setMonitorShowUrl(String monitorShowUrl) {
            this.monitorShowUrl = monitorShowUrl;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public int getAd_loc() {
            return ad_loc;
        }

        public String getVdog() {
            return vdog;
        }

        public String getLocation() {
            return location;
        }

        public int getThirdplat() {
            return thirdplat;
        }

        public long getExpired_time() {
            return expired_time;
        }

        public ActionParamsBean getAction_params() {
            return action_params;
        }

        public String getSub_title() {
            return sub_title;
        }

        public int getLoop_time() {
            return loop_time;
        }

        public String getVdot() {
            return vdot;
        }

        public String getId() {
            return id;
        }

        public String getStyle() {
            return style;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getAction() {
            return action;
        }

        public String getFlight_id() {
            return flight_id;
        }

        public String getShow_time() {
            return show_time;
        }

        public String getVdeg() {
            return vdeg;
        }

        public int getShow_num() {
            return show_num;
        }

        public String getMonitor() {
            return monitor;
        }

        public String getMonitorClickUrl() {
            return monitorClickUrl;
        }

        public String getVdet() {
            return vdet;
        }

        public String getContent() {
            return content;
        }

        public int getIs_sens() {
            return is_sens;
        }

        public String getCategory() {
            return category;
        }

        public String getRatio() {
            return ratio;
        }

        public List<String> getRes_url() {
            return res_url;
        }

        public String getMonitorShowUrl() {
            return monitorShowUrl;
        }

        public String getMain_title() {
            return main_title;
        }

        public int getAd_type() {
            return ad_type;
        }

        public static class ActionParamsBean {

            @Override
            public String toString() {
                return "ActionParamsBean{" +
                        "link_url='" + link_url + '\'' +
                        '}';
            }

            /**
             * link_url : http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]
             */
            private String link_url;

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getLink_url() {
                return link_url;
            }
        }
    }
}
