package com.it520.activity.news.phone.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.it520.activity.R;
import com.it520.activity.news.phone.adapter.BannerGalleryAdapter;
import com.it520.activity.news.phone.adapter.NewsItemAdapter;
import com.it520.activity.news.phone.bean.LYBAds;
import com.it520.activity.news.phone.bean.Phone;
import com.it520.activity.news.phone.bean.PhoneAds;
import com.it520.activity.news.phone.bean.PhoneDetail;
import com.it520.activity.news.widget.BannerGallery;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiyubin on 2016/6/19/019.
 */
public class PhoneFragment extends Fragment implements AbsListView.OnScrollListener {

    private static final int UPDATE_ADS_DATE = 0x00102;
    LayoutInflater mInflater;
    private ListView mPhoneListView;
    PtrClassicFrameLayout mPtrFrame;
    private View mBannerLayout;

    private NewsItemAdapter mNewsItemAdapter;
    List<PhoneDetail> mNewsDetailList;//新闻的数据

    private BannerGallery mAdsGallery;//广告布局的容器
    private BannerGalleryAdapter mAdsAdapter;//广告数据的adapter
    List<LYBAds> mAdsDatas = new ArrayList<>();//广告的数据

    public int startIndex = 0;
    public int endIndex = 0;
    public final int PAGESIZE = 20;
    public int index = 0;

    boolean isScrlooToButtom = false;

    public static final int INIT_LIST_DATE = 1;
    public static final int UPDATE_LIST_DATE = 2;
    public boolean isGetingData = false;
    public LinearLayout dots;
    private TextView banner_title;
    public static final int REFURESH_DATA = 0;
    public static final int LOAD_MORE_DATE = 1;
    public static final int INIT_ADS_DATA = 2;
    ArrayList<ImageView> dotsImageViews = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_news_phone, null, false);
        initView(view);
        getIndexDate(REFURESH_DATA);
        getAdsDate();
        return view;
    }


    private void initView(View view) {
        mPhoneListView = (ListView) view.findViewById(R.id.phone_Lv);
        mBannerLayout = this.mInflater.inflate(R.layout.news_banner_layout, null);
        dots = (LinearLayout) mBannerLayout.findViewById(R.id.dots);
        banner_title = (TextView) mBannerLayout.findViewById(R.id.banner_title);
        mAdsGallery = (BannerGallery) mBannerLayout.findViewById(R.id.news_hot_banner_gallery);
        mBannerLayout.setBackgroundColor(Color.GREEN);
        mAdsGallery.setFocusable(true);
        mAdsGallery.setUnselectedAlpha(100);
        mAdsGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int realPostion = position % mAdsDatas.size();
                changeDotsStatus(realPostion);
                //拿到导航标题
                banner_title.setText(mAdsDatas.get(position).getTitle());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mPhoneListView.addHeaderView(mBannerLayout);
        mPhoneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("lyb", "手机ListView~~position = " + position);
            }
        });

        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.phone_ptr_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getIndexDate(REFURESH_DATA);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
        mPhoneListView.setOnScrollListener(this);
    }

    private void setBannerData() {

        // Ads 广告
        if (mAdsDatas != null) {
            if (mAdsAdapter == null) {
                mAdsAdapter = new BannerGalleryAdapter(getActivity());
                mAdsAdapter.setData(mAdsDatas);
                mAdsGallery.setAdapter(mAdsAdapter);
                mAdsAdapter.notifyDataSetChanged();
                int current = Integer.MAX_VALUE / 2;
                mAdsGallery.setSelection(current - (current % mAdsDatas.size()));
                addDotsImage(mAdsDatas.size());
            } else {
                mAdsAdapter.setData(mAdsDatas);
                mAdsAdapter.notifyDataSetChanged();
                mAdsAdapter.notifyDataSetChanged();
                int current = Integer.MAX_VALUE / 2;
                mAdsGallery.setSelection(current - (current % mAdsDatas.size()));
            }
        }
    }

    private void addDotsImage(int size) {
        for (int i = 0; i < size; i++) {
            ImageView image = new ImageView(getContext());
            image.setBackgroundResource(R.drawable.gray_dot);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.setMargins(10, 0, 0, 0);
            image.setLayoutParams(p);
            dotsImageViews.add(image);
            dots.addView(image);
        }
        changeDotsStatus(0);

    }


    private void getIndexDate(final int isResh) {
        if (isGetingData) {
            return;
        }
        if (isResh == REFURESH_DATA) {
            index = 0;
        }
        culIndex();
        new Thread(new Runnable() {
            @Override
            public void run() {

                isGetingData = true;
                HttpUtil httputil = HttpUtil.getInstance();
                String url = null;
              //  if (isResh == REFURESH_DATA) {
                    url = Contance.getPhoneUrl(startIndex, endIndex);
               // }
                Log.i("lyb", "endIndex = " + endIndex);
                httputil.doGet(url, new HttpResponse<Phone>(Phone.class) {
                    @Override
                    public void onSuccess(Phone phone) {
               //if (phone != null && phone.getT1348649654285() != null && phone.getT1348649654285().size() > 0) {
               //mBannerDataList = phone.getT1348649654285().get(0).getAds();// 广告位数据
               //Log.v(getClass().getSimpleName(), "数据测试-1--->" + mBannerDataList);
               //}
                        if (phone != null && phone.getT1348649654285() != null) {
                            Log.v(getClass().getSimpleName(), "数据测试-2--->" + phone);

                            List<PhoneDetail> date = phone.getT1348649654285();
                            PhoneDetail remove = date.remove(0);
                            Log.v(getClass().getSimpleName(), "移出的数据---》" + remove);
                            LYBAds lybAds = new LYBAds();
                            lybAds.setTitle(remove.getTitle());
                            lybAds.setImgUrl(remove.getImgsrc());
                            lybAds.setDocId(remove.getDocid());
                            lybAds.setLink_url(remove.getUrl());
                            mAdsDatas.add(0, lybAds);
                            handler.sendEmptyMessage(UPDATE_ADS_DATE);


                            if (isResh == LOAD_MORE_DATE) {
                                List<PhoneDetail> data = phone.getT1348649654285();
                                Message message = handler.obtainMessage(UPDATE_LIST_DATE);
                                message.obj = data;
                                handler.sendMessage(message);
                            } else {

                                mNewsDetailList = date;
                                // 获取数据完毕，发送消息更新UI
                                Message message = handler.obtainMessage(INIT_LIST_DATE);
                                handler.sendMessage(message);
                            }

                        }


                        index++;
                        isGetingData = false;
                    }

                    @Override
                    public void onError(String error) {
                        isGetingData = false;
                    }
                });
            }
        }).start();
    }


    // TODO: 2016/6/20/020  解析广告
    private void getAdsDate() {
        Log.v(getClass().getSimpleName(), "" + "111111111111111");
        new Thread(new Runnable() {
            @Override
            public void run() {


                HttpUtil httputil = HttpUtil.getInstance();
                final String url = Contance.PHONE_ADS_URL;
                //new TypeToken<List<PhoneAdvert>>(){}.getType()
                // 泛型修改
                httputil.doGet(url, new HttpResponse<String>(String.class) {
                    @Override
                    public void onSuccess(String item) {
                        List<PhoneAds> parse = JsonUtil.parse(item, new TypeToken<List<PhoneAds>>() {
                        }.getType());
                        mAdsDatas.clear();
                        for (int i = 0; i < parse.size(); i++) {
                            LYBAds lbyAds = new LYBAds();
                            PhoneAds.AdsBean adsBean = parse.get(i).getAds().get(0);
                            lbyAds.setLink_url(adsBean.getAction_params().getLink_url());
                            lbyAds.setImgUrl(adsBean.getRes_url().get(0));
                            lbyAds.setTitle(adsBean.getMain_title());
                            mAdsDatas.add(lbyAds);
                        }
                        Log.v("请求得到的数据--->", "" + mAdsDatas);
                        handler.sendEmptyMessage(UPDATE_ADS_DATE);

                    }

                    @Override
                    public void onError(String error) {
                        Log.v("getAdsDate----> 数据解析失败", error);
                    }
                });
            }
        }).start();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_LIST_DATE:
                    setBannerData();
                    setDetailDate();
                    break;
                case UPDATE_LIST_DATE:
                    List<PhoneDetail> data = (List<PhoneDetail>) msg.obj;
                    update(data);
                    break;
                case UPDATE_ADS_DATE:
                    setBannerData();
                    Log.v(getClass().getSimpleName(), ""+"更新广告数据--》");
                    break;
            }

        }
    };

    private void update(List<PhoneDetail> tmp) {
        if (tmp != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new NewsItemAdapter(getActivity());
                mNewsItemAdapter.setData(tmp);
                mPhoneListView.setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.updateList(tmp);

            }
            Log.i("lyb", "setDetailDate");
        }
    }

    private void setDetailDate() {
        if (mNewsDetailList != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new NewsItemAdapter(getActivity());
                mNewsItemAdapter.setData(mNewsDetailList);
                mPhoneListView.setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.setData(mNewsDetailList);
                mNewsItemAdapter.notifyDataSetChanged();
            }
            Log.i("lyb", "setDetailDate");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdsGallery.setOnItemSelectedListener(null);
        mAdsGallery.destroy();
    }

    private void culIndex() {
        if (index == 0) {
            endIndex = PAGESIZE;
        } else {
            startIndex = PAGESIZE + startIndex;
            Log.v("lyb", "startIndex=" + startIndex);
            endIndex = startIndex + PAGESIZE;
            Log.v("lyb", "endIndex=" + endIndex);
        }
    }

    private void changeDotsStatus(int position) {
        for (int i = 0; i < dotsImageViews.size(); i++) {
            ImageView tmp = dotsImageViews.get(i);
            if (position == i) {
                tmp.setBackgroundResource(R.drawable.white_dot);

            } else {
                tmp.setBackgroundResource(R.drawable.gray_dot);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isScrlooToButtom) {
            Log.i("lyb", "in");
            getIndexDate(LOAD_MORE_DATE);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view.getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrlooToButtom = true;
        } else {
            isScrlooToButtom = false;
        }
    }
}
