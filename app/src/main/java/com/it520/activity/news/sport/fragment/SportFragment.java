package com.it520.activity.news.sport.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.news.adapter.BannerGalleryAdapter;
import com.it520.activity.news.bean.NewsAds;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.sport.adapter.SportItemAdapter;
import com.it520.activity.news.sport.bean.Sport;
import com.it520.activity.news.sport.bean.SportDetail;
import com.it520.activity.news.widget.BannerGallery;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * @author moy
 * @time 2016/6/19  14:50
 * @desc 体育
 */

public class SportFragment extends Fragment implements XListView.IXListViewListener {
    LayoutInflater mInflater;
    PtrClassicFrameLayout mPtrFrame;
    XListView mListView;
    private View mBannerLayout;
    private BannerGallery mBannerGallery;
    private BannerGalleryAdapter mBannerGalleryAdapter;

    SportItemAdapter mNewsItemAdapter;

    List<NewsAds> mBannerDataList;
    List<SportDetail> mNewsDetailList;

    public int startIndex = 0;
    public int endIndex = 0;
    public final int PAGESIZE = 20;
    public int index = 0;


    public static final int INIT_LIST_DATE = 1;
    public static final int UPDATE_LIST_DATE = 2;
    public boolean isGetingData = false;
    public LinearLayout dots;
    private TextView banner_title;
    ArrayList<ImageView> dotsImageViews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_news_sport, null, false);
        initView(view);
        initEvent();
        getIndexDate(true);
        return view;

    }

    private void initView(View view) {

        mListView = (XListView) view.findViewById(R.id.news_sport_listview);
        //上方广告头条
        mBannerLayout = this.mInflater.inflate(R.layout.news_banner_layout, null);
        dots = (LinearLayout) mBannerLayout.findViewById(R.id.dots);
        banner_title = (TextView) mBannerLayout.findViewById(R.id.banner_title);
        mBannerGallery = (BannerGallery) mBannerLayout.findViewById(R.id.news_hot_banner_gallery);
        mBannerGallery.setFocusable(true);
        mBannerGallery.setUnselectedAlpha(100);

        mBannerGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int realPosition = position%mBannerDataList.size();
                changeDotsStatus(realPosition);
                banner_title.setText(mBannerDataList.get(realPosition).getTitle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //把广告头条加入
        mListView.addHeaderView(mBannerLayout);
        //整个外围视图
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.sport_ptr_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //拿数据,开始时
                getIndexDate(true);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
        mListView.setXListViewListener(this);
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);

    }

    private void initEvent() {

    }

    private void setBannerData() {
        // Banner
        if (mBannerDataList != null) {
            if (mBannerGalleryAdapter == null) {
                mBannerGalleryAdapter = new BannerGalleryAdapter(getActivity());
                mBannerGalleryAdapter.setData(mBannerDataList);
                mBannerGallery.setAdapter(mBannerGalleryAdapter);
                int current = Integer.MAX_VALUE/2;
                mBannerGallery.setSelection(current - (current%mBannerDataList.size()));
                addDotsImage(mBannerDataList.size());
            } else {
                mBannerGalleryAdapter.setData(mBannerDataList);
                mBannerGalleryAdapter.notifyDataSetChanged();
                int current = Integer.MAX_VALUE/2;
                mBannerGallery.setSelection(current - (current % mBannerDataList.size()));
            }


        }
    }

    private void addDotsImage(int size){
        for(int i =0 ;i<size;i++){
            ImageView image = new ImageView(getContext());
            image.setBackgroundResource(R.drawable.gray_dot);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            p.setMargins(10,0,0,0);
            image.setLayoutParams(p);
            dotsImageViews.add(image);
            dots.addView(image);
        }
        changeDotsStatus(0);

    }
    private void changeDotsStatus(int position){

        for(int i = 0 ;i<dotsImageViews.size();i++){
            ImageView tmp = dotsImageViews.get(i);
            if(position==i){
                tmp.setBackgroundResource(R.drawable.white_dot);

            }else{
                tmp.setBackgroundResource(R.drawable.gray_dot);
            }
        }
    }
    private void setDetailDate() {
        if (mNewsDetailList != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new SportItemAdapter(getActivity());
                mNewsItemAdapter.setData(mNewsDetailList);
                mListView.setAdapter(mNewsItemAdapter);
                //设置点击监听
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SportDetail sportDetail = mNewsItemAdapter.getItem(position-2);
                        Intent intent = new Intent();
                        String docID = sportDetail.getDocid();
                        intent.setClass(getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.DOC_ID,docID);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);

                    }
                });
            } else {
                mNewsItemAdapter.setData(mNewsDetailList);
                mNewsItemAdapter.notifyDataSetChanged();
                mPtrFrame.refreshComplete();
                mListView.stopLoadMore();
            }
        }
    }

    private void update(List<SportDetail> tmp) {
        if (tmp != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new SportItemAdapter(getActivity());
                mNewsItemAdapter.setData(tmp);
                mListView.setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.updateList(tmp);

            }
        }
    }

    public void getIndexDate(final boolean isResh) {
        if(isGetingData){
            return;
        }
        if (isResh) {
            index = 0;
        }
        culIndex();
        new Thread(new Runnable() {
            @Override
            public void run() {
                isGetingData = true;
                HttpUtil httputil = HttpUtil.getInstance();
                String url = Contance.getSportUrl(startIndex, endIndex);
                httputil.doGet(url, new HttpResponse<Sport>(Sport.class) {
                    @Override
                    public void onSuccess(Sport sport) {

                        if (sport != null && sport.getT1348649079062() != null && sport.getT1348649079062().size() > 0) {
                            mBannerDataList = sport.getT1348649079062().get(0).getAds();// 广告位数据

                        }
                        if (sport != null && sport.getT1348649079062() != null) {
                            if (isResh) {
                                List<SportDetail> date = sport.getT1348649079062();
                                date.remove(0);
                                mNewsDetailList = date;
                                // 获取数据完毕，发送消息更新UI
                                Message message = handler.obtainMessage(INIT_LIST_DATE);
                                handler.sendMessage(message);
                            } else {
                                List<SportDetail> data = sport.getT1348649079062();
                                Message message = handler.obtainMessage(UPDATE_LIST_DATE);
                                message.obj = data;
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


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_LIST_DATE:
                    setBannerData();
                    setDetailDate();
                    break;
                case UPDATE_LIST_DATE:
                    List<SportDetail> data = (List<SportDetail>) msg.obj;
                    update(data);
                    break;
            }

        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBannerGallery.setOnItemSelectedListener(null);
        mBannerGallery.destroy();
    }

    public void culIndex() {
        if (index == 0) {
            endIndex = startIndex + PAGESIZE;
        } else {
            startIndex = endIndex + 1;
            endIndex = startIndex + PAGESIZE;
        }
    }





    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {
        startIndex = endIndex;
        endIndex=endIndex+20;
        new Thread() {
            public void run() {
                HttpUtil httputil = HttpUtil.getInstance();
                String url = Contance.getSportUrl(startIndex, endIndex);
                httputil.doGet(url, new HttpResponse<Sport>(Sport.class) {
                    @Override
                    public void onSuccess(Sport sport) {
                        if (sport != null && sport.getT1348649079062() != null) {
                            if (mNewsDetailList!=null) {
                                List<SportDetail> date = sport.getT1348649079062();
                                mNewsDetailList.addAll(date);
                                // 获取数据完毕，发送消息更新UI
                                Message message = handler.obtainMessage(INIT_LIST_DATE);
                                handler.sendMessage(message);

                            }

                        }
                    }

                    @Override
                    public void onError(String error) {
                        isGetingData = false;
                    }
                });
            }
        }.start();
    }
}
