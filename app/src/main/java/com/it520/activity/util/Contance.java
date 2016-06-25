package com.it520.activity.util;

/**
 * Created by kay on 16/5/10.
 */
public class Contance {
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String SPLASH_TITLE = "http://g1.163.com/madr?app=7A16FBB6&platform=android&category=STARTUP&location=1&timestamp=1462779408364&uid=OaBKRDb%2B9FBz%2FXnwAuMBWF38KIbARZdnRLDJ6Kkt9ZMAI3VEJ0RIR9SBSPvaUWjrFtfw1N%2BgxquT0B2pjMN5zsxz13RwOIZQqXxgjCY8cfS8XlZuu2bJj%2FoHqOuDmccGyNEtV%2FX%2FnBofofdcXyudJDmBnAUeMBtnIzHPha2wl%2FQnUPI4%2FNuAdXkYqX028puyLDhnigFtrX1oiC2F7UUuWhDLo0%2BE0gUyeyslVNqLqJCLQ0VeayQa%2BgbsGetk8JHQ";
    public static final String HOT_URL_START = "http://c.m.163.com/nc/article/headline/T1348647909107/";
    public static final String PHONE_URL_START = "http://c.3g.163.com/nc/article/list/T1348649654285/";
    public static final String PHONE_URL_END = ".html";
    //phone广告栏
    public static final String PHONE_ADS_URL="http://g1.163.com/madrs?app=7A16FBB6&platform=android&category=T1348649654285&location=10%2C11%2C20%2C21%2C22%2C23%2C24%2C25%2C26%2C27%2C28%2C29&timestamp=1466443424421&uid=EZ3bZWDXmKdsK4nvCc35n0JkyjzYeO0CqopHlF8QI8cjEBq6Mcfj%2Fres5X6yyZpuv%2FR80Uwp%2BLRk5r643RBYGTK%2FYg9XtGXQDkoNiD2p7f3tYf9OnRyAHhB4NhYLlDykkZI0Z6RoB9Qdl08ze8hLbzsMhiSapSf1%2BFuU3UntQnkBwEd1NrkWWCBu1t3ECC%2FPzgaAVMFV7PWvaRK9RRetvbshlxMh72hmI87R4SrCXxcXz9y3f%2B46HoCf2pYGi1Tx7Vo6GjHG3SUoq5%2FRFbIqSzl6BgyKUslHa9IKJAFU8TTJnLwTYhUMJ1J63OXg86nqxl9ZY9cEIfcXAltq%2FiXKECc2eEQlevdCe5hyfrr%2B8w5YBPT%2FKsLl9nCOa2V%2BFzfb";
    public static final String HOT_URL_END = ".html?from=toutiao&size=20&passport=&devId=bMo6EQztO2ZGFBurrbgcMQ%3D%3D&lat=YO6p1koFB04ckeiATuYaGw%3D%3D&lon=SQIt%2FB7%2BSqySYsiVHI%2FDiQ%3D%3D&version=7.0&net=wifi&ts=1463198253&sign=VHsiElahM1HTWFL0pnd52EoxE3w9piu1mp9jiCwGatd48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=goapk_news";
    public static final String BEAUTY_URL = "http://c.m.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&passport=&devId=F9sgZlyCkPIZPtpH2SlDiAaqAZ2m4UG8C21t0POXhziL0gfT2z2VxMz89jLOk3Ge&lat=Zj3k0ibWuI9XkAt3vYXx8Q%3D%3D&lon=lUPMr%2Bhk%2FAPnCx4EAktYmA%3D%3D&version=10.0&net=wifi&ts=1466644605&sign=FHd6%2ByTfHaRonyZG9uTMqHAPPFvvxZ7t63ZGB%2FrovrB48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=racUMC0A9havm%2BHe6jH3YAvVdjgSXYDtwEDZ03eH1l8%3D";
    //视频->八卦
    public static final String GOSSIP_URL = "http://c.3g.163.com/nc/video/Tlist/T1457069261743/0-10.html";
    //视频->八卦->详情
    public static final String GOSSIP_DETAIL_URL = "http://c.3g.163.com/nc/video/detail/%S.html";
    public static final String ENTERTAINMENT_URL = "http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html";
    public static final String DETAIL_START = "http://c.m.163.com/nc/article/%S/full.html";
    public final static String FLODERNAME = ".advertise";
    public final static String BEAUTY_DETAIL_URL = "http://c.3g.163.com/nc/article/%S/full.html";
    //推荐阅读
    public static final String READ_RECOMMEND_URL="http://c.3g.163.com/recommend/getSubDocPic?from=yuedu&size=20&passport=&devId=44t6%2B5mG3ACAOlQOCLuIHg%3D%3D&lat=&lon=&version=8.0&net=wifi&ts=1466405830&sign=yjeBSfUkOKthVl9lwmIjnX1IZX3qtbctm9oqkhD6ei148ErR02zJ6%2FKXOnxX046I&encryption=1&canal=baidu_news&mac=PgkupVHCdv2BHOSYI%2Bgr0Ye1A3IUPLLdoCiqBVf2Go0%3D";


    //新闻 -> 科技
    public static final String BASE_ULR_NEWS_SCIENCE = "http://c.m.163.com/nc/article/list/T1348649580692/";
    public static String getHotUrl(int start,int end) {
        return HOT_URL_START + start + "-" + end + HOT_URL_END;
    }
    //体育
    public static String getSportUrl(int start, int end) {
        return SPORT_URL_START + start + "-" + end + SPORT_URL_END;
    }
    public static final String SPORT_URL_START = "http://c.m.163.com/nc/article/list/T1348649079062/";
    public static final String SPORT_URL_END=".html";
    public static String getNewsScience(int startPage) {
        return BASE_ULR_NEWS_SCIENCE + startPage + "-" + DEFAULT_PAGE_SIZE + ".html";
    }
    //获取手机数据
    public static String getPhoneUrl(int start,int end) {
        return PHONE_URL_START+start+"-"+end+PHONE_URL_END;
    }
    public static String getDetailUrl(String docid) {
        return DETAIL_START.replaceAll("%S", docid);
    }
    public static String getBeautyDetailUrl(String docid){
        return BEAUTY_DETAIL_URL.replaceAll("%S",docid);
    }
    public static String getGossipDetailUrl(String vid){
        return GOSSIP_DETAIL_URL.replaceAll("%S",vid);
    }
}
