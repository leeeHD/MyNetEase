package com.it520.activity.news.city.bean;

import java.util.List;

public class CityInfo<T> {
    private List<T> infos;

    public List<T> getInfos() {
        return infos;
    }

    public void setInfos(List<T> infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "infos=" + infos +
                '}';
    }
}
