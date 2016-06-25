package com.it520.activity.news.phone.bean;

import java.util.List;

/**
 * Created by laiyubin on 2016/6/20/020.
 */
public class Testde {

    /**
     * result : 0
     * next_req : 600
     * error :
     * ads : [{"monitor":"","is_sens":0,"vdet":"","action_params":{"link_url":"http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]"},"monitorClickUrl":"","vdog":"","show_time":"3.5","category":"T1348649654285","id":"47565","flight_id":"762","loop_time":0,"res_url":["http://img1.126.net/channel14/16kaola_1080624_0619.jpg","","",""],"vdot":"","thirdplat":0,"main_title":"送你680元礼包买国外美妆","style":"3","monitorShowUrl":"","show_num":3,"ad_type":0,"vdeg":"","action":"1","ad_loc":6,"expired_time":1467388799000,"sub_title":"","location":"10","ratio":"100.0","content":"","video_url":""}]
     */

    private int result;
    private int next_req;
    private String error;
    /**
     * monitor :
     * is_sens : 0
     * vdet :
     * action_params : {"link_url":"http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]"}
     * monitorClickUrl :
     * vdog :
     * show_time : 3.5
     * category : T1348649654285
     * id : 47565
     * flight_id : 762
     * loop_time : 0
     * res_url : ["http://img1.126.net/channel14/16kaola_1080624_0619.jpg","","",""]
     * vdot :
     * thirdplat : 0
     * main_title : 送你680元礼包买国外美妆
     * style : 3
     * monitorShowUrl :
     * show_num : 3
     * ad_type : 0
     * vdeg :
     * action : 1
     * ad_loc : 6
     * expired_time : 1467388799000
     * sub_title :
     * location : 10
     * ratio : 100.0
     * content :
     * video_url :
     */

    private List<AdsBean> ads;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public static class AdsBean {
        private String monitor;
        private int is_sens;
        private String vdet;
        /**
         * link_url : http://rd.da.netease.com/redirect?t=FXbyKCJDPM&p=T1I7P2&proId=1922&target=http%3A%2F%2Fm.kaola.com%2Factivity%2Fh5%2F12371.shtml%3Fnoklappbanner%3D1%26tag%3D21be576e728b0d8dc2493b69d501f1b0&had=[netease||id]
         */

        private ActionParamsBean action_params;
        private String monitorClickUrl;
        private String vdog;
        private String show_time;
        private String category;
        private String id;
        private String flight_id;
        private int loop_time;
        private String vdot;
        private int thirdplat;
        private String main_title;
        private String style;
        private String monitorShowUrl;
        private int show_num;
        private int ad_type;
        private String vdeg;
        private String action;
        private int ad_loc;
        private long expired_time;
        private String sub_title;
        private String location;
        private String ratio;
        private String content;
        private String video_url;
        private List<String> res_url;

        public String getMonitor() {
            return monitor;
        }

        public void setMonitor(String monitor) {
            this.monitor = monitor;
        }

        public int getIs_sens() {
            return is_sens;
        }

        public void setIs_sens(int is_sens) {
            this.is_sens = is_sens;
        }

        public String getVdet() {
            return vdet;
        }

        public void setVdet(String vdet) {
            this.vdet = vdet;
        }

        public ActionParamsBean getAction_params() {
            return action_params;
        }

        public void setAction_params(ActionParamsBean action_params) {
            this.action_params = action_params;
        }

        public String getMonitorClickUrl() {
            return monitorClickUrl;
        }

        public void setMonitorClickUrl(String monitorClickUrl) {
            this.monitorClickUrl = monitorClickUrl;
        }

        public String getVdog() {
            return vdog;
        }

        public void setVdog(String vdog) {
            this.vdog = vdog;
        }

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFlight_id() {
            return flight_id;
        }

        public void setFlight_id(String flight_id) {
            this.flight_id = flight_id;
        }

        public int getLoop_time() {
            return loop_time;
        }

        public void setLoop_time(int loop_time) {
            this.loop_time = loop_time;
        }

        public String getVdot() {
            return vdot;
        }

        public void setVdot(String vdot) {
            this.vdot = vdot;
        }

        public int getThirdplat() {
            return thirdplat;
        }

        public void setThirdplat(int thirdplat) {
            this.thirdplat = thirdplat;
        }

        public String getMain_title() {
            return main_title;
        }

        public void setMain_title(String main_title) {
            this.main_title = main_title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getMonitorShowUrl() {
            return monitorShowUrl;
        }

        public void setMonitorShowUrl(String monitorShowUrl) {
            this.monitorShowUrl = monitorShowUrl;
        }

        public int getShow_num() {
            return show_num;
        }

        public void setShow_num(int show_num) {
            this.show_num = show_num;
        }

        public int getAd_type() {
            return ad_type;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public String getVdeg() {
            return vdeg;
        }

        public void setVdeg(String vdeg) {
            this.vdeg = vdeg;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getAd_loc() {
            return ad_loc;
        }

        public void setAd_loc(int ad_loc) {
            this.ad_loc = ad_loc;
        }

        public long getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(long expired_time) {
            this.expired_time = expired_time;
        }

        public String getSub_title() {
            return sub_title;
        }

        public void setSub_title(String sub_title) {
            this.sub_title = sub_title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public List<String> getRes_url() {
            return res_url;
        }

        public void setRes_url(List<String> res_url) {
            this.res_url = res_url;
        }

        public static class ActionParamsBean {
            private String link_url;

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }
        }
    }
}
