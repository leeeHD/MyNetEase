package com.it520.activity.news.finance.Cons;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class Constance {

    public static final String FINANCE_URL="http://c.m.163.com/nc/article/list/T1348648756099/0-20.html";

    public static final String DETAIL_START="http://c.m.163.com/nc/article/%S/full.html";

    public static  String getDetailUrl(String docid){
        
        return  DETAIL_START.replaceAll("%S",docid);
    }
}
