package com.it520.activity.news.city;

public class Constants {
    private final static int ITEM_COUNT_ONE_PAGE = 20;
    private  static int start = 0;
    public final static String CITY_NEWS_URL_PREFIX = "http://c.3g.163.com/nc/article/local/5YyX5Lqs/";
    public final static String CITY_NEWS_URL = "http://c.3g.163.com/nc/article/local/5YyX5Lqs/0-20.html";
    public final static String CITY_WETHER_URL = "http://c.m.163.com/nc/weather/5YyX5LqsfOWMl%2BS6rA%3D%3D.html";
//    public final static String CITY_NEWS_URL = "http://c.m.163.com/nc/article/local/5bm%2F5bee/0-20.html";


    ////////////////话题
    public static final String TOPIC_SUBJECT_BAR_URL = "http://c.m.163.com/recommend/getChanRecomNews?channel=T1460094487214&size=5&passport=&devId=voR9hg%2Fh8H0Fwgd7q66gBw%3D%3D&lat=&lon=&version=10.0&net=wifi&ts=1466478464&sign=55FOQiSW8eIWuXdaVOiSAY%2FFIkEK9ZbMJbX2m13jN4148ErR02zJ6%2FKXOnxX046I&encryption=1&canal=baidu_news&mac=uLXqgjVw6PeYCHnCbhIPO1VYhcaEx9qKBfnL6TmfOng%3D";
    public static final String TOPIC_SUBJECT_DETAIL_URL = "http://topic.comment.163.com/topic/list/subject/0-10.html";
    public static final String TOPIC_SUBJECT_DETAIL_URL_PREFIX = "http://topic.comment.163.com/topic/list/subject/";

    public static String getCityNewsUrl(int start, int end) {
        StringBuilder sb = new StringBuilder(CITY_NEWS_URL_PREFIX);
        return sb.append(start).append("-").append(end).append(".html").toString();
    }

    public static String getSubjectUrl(int start, int end) {
        StringBuilder sb = new StringBuilder(TOPIC_SUBJECT_DETAIL_URL_PREFIX);
        return sb.append(start).append("-").append(end).append(".html").toString();
    }
}
