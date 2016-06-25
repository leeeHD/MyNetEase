package com.it520.activity.news.scienci.adapter;

import com.it520.activity.news.scienci.bean.Science;
import com.it520.activity.news.scienci.holder.ScienceHolder;
import com.it520.activity.yxxbase.BaseHolder;
import com.it520.activity.yxxbase.SuperAdapter;

import java.util.List;

/**
 * Created by Yu xiangxin on 2016/6/19 - 19:52.
 *
 * @desc ${TODD}
 */
public class NewsDetailAdapter extends SuperAdapter<Science.ScienceItem> {


    protected boolean stopRefress = false;

    public NewsDetailAdapter(List<Science.ScienceItem> datas) {
        super(datas);
    }

    @Override
    protected BaseHolder getSpaceHolder(int position) {
        return new ScienceHolder();
    }

}

