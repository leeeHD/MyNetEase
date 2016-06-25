package com.it520.activity.read.recommend.bean;

import java.util.List;

/**
 * @author moy
 * @time 2016/6/20  17:58
 * @desc 推荐阅读
 */

public class RecommendRead {
    private List< RecommendDetail> 推荐;

    public List<RecommendDetail> get推荐() {
        return 推荐;
    }

    public void set推荐(List<RecommendDetail> 推荐) {
        this.推荐 = 推荐;
    }
}
