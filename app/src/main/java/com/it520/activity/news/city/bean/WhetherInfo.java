package com.it520.activity.news.city.bean;

import java.io.Serializable;
import java.util.List;

public class WhetherInfo implements Serializable {
    private List<DayInfo> dayInfos;
    private String dt;
    private int rt_temperature;
    private Pm2d5 pm2d5;

    public Pm2d5 getPm2d5() {
        return pm2d5;
    }

    public void setPm2d5(Pm2d5 pm2d5) {
        this.pm2d5 = pm2d5;
    }

    public List<DayInfo> getDayInfos() {
        return dayInfos;
    }

    public void setDayInfos(List<DayInfo> dayInfos) {
        this.dayInfos = dayInfos;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getRt_temperature() {
        return rt_temperature;
    }

    public void setRt_temperature(int rt_temperature) {
        this.rt_temperature = rt_temperature;
    }

    public class DayInfo  implements Serializable {
        /**
         * wind : 微风
         * nongli : 农历五月十六日
         * date : 20日
         * climate : 雷阵雨
         * week : 星期一
         * temperature : 33°C/22°C
         */
        private String wind;
        private String nongli;
        private String date;
        private String climate;
        private String week;
        private String temperature;

        public void setWind(String wind) {
            this.wind = wind;
        }

        public void setNongli(String nongli) {
            this.nongli = nongli;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setClimate(String climate) {
            this.climate = climate;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWind() {
            return wind;
        }

        public String getNongli() {
            return nongli;
        }

        public String getDate() {
            return date;
        }

        public String getClimate() {
            return climate;
        }

        public String getWeek() {
            return week;
        }

        public String getTemperature() {
            return temperature;
        }
    }
    public class Pm2d5  implements Serializable {
        /**
         * nbg1 : http://img1.cache.netease.com/m/newsapp/weather/TianQi370/LeiZhenYu.jpg
         * nbg2 : http://img1.cache.netease.com/m/newsapp/weather/TianQi1008/LeiZhenYu.jpg
         * aqi : 121
         * pm2_5 : 82
         */
        private String nbg1;
        private String nbg2;
        private String aqi;
        private String pm2_5;

        public void setNbg1(String nbg1) {
            this.nbg1 = nbg1;
        }

        public void setNbg2(String nbg2) {
            this.nbg2 = nbg2;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getNbg1() {
            return nbg1;
        }

        public String getNbg2() {
            return nbg2;
        }

        public String getAqi() {
            return aqi;
        }

        public String getPm2_5() {
            return pm2_5;
        }
    }

    @Override
    public String toString() {
        return "WhetherInfo{" +
                "dayInfos=" + dayInfos +
                ", dt='" + dt + '\'' +
                ", rt_temperature=" + rt_temperature +
                ", pm2d5=" + pm2d5 +
                '}';
    }
}

