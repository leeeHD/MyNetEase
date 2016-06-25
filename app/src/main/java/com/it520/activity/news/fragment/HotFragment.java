package com.it520.activity.news.fragment;

import android.content.Intent;
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

import com.it520.activity.R;
import com.it520.activity.news.adapter.BannerGalleryAdapter;
import com.it520.activity.news.adapter.NewsItemAdapter;
import com.it520.activity.news.bean.Hot;
import com.it520.activity.news.bean.NewDetail;
import com.it520.activity.news.bean.NewsAds;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.widget.BannerGallery;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.share.ShareInfo;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kay on 16/5/14.
 */
public class HotFragment extends Fragment implements AbsListView.OnScrollListener {
    private static final java.lang.String TAG = "HotFragment";
    LayoutInflater mInflater;
    PtrClassicFrameLayout mPtrFrame;
    ListView mListView;
    private View mBannerLayout;
    private BannerGallery mBannerGallery;
    private BannerGalleryAdapter mBannerGalleryAdapter;

    NewsItemAdapter mNewsItemAdapter;

    List<NewsAds> mBannerDataList;
    List<NewDetail> mNewsDetailList;

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
    ArrayList<ImageView> dotsImageViews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_news_hot, null, false);
        initView(view);
        initEvent();
        getIndexDate(true);
        return view;

    }

    private void initView(View view) {

        mListView = (ListView) view.findViewById(R.id.listview);

        mBannerLayout = this.mInflater.inflate(R.layout.news_banner_layout, null);
        dots = (LinearLayout) mBannerLayout.findViewById(R.id.dots);
        banner_title = (TextView) mBannerLayout.findViewById(R.id.banner_title);
        mBannerGallery = (BannerGallery) mBannerLayout.findViewById(R.id.news_hot_banner_gallery);
        mBannerGallery.setFocusable(true);
        mBannerGallery.setUnselectedAlpha(100);

        mBannerGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int realPosition = position % mBannerDataList.size();
                changeDotsStatus(realPosition);
                banner_title.setText(mBannerDataList.get(realPosition).getTitle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //        mBannerGallery;
        //
        mListView.addHeaderView(mBannerLayout);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mNewsItemAdapter != null && mNewsItemAdapter.getCount() > (id + 1) && mNewsItemAdapter.getItem((int) id) != null) {
                    NewDetail item = mNewsItemAdapter.getItem((int) id);
                    item.getDocid();
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(DetailActivity.DOC_ID, item.getDocid());
                    //把要分享的信息封装在 ShareInfo中,传递给 DetailActivity
                    ShareInfo shareInfo = new ShareInfo();

                    shareInfo.setImgUrl(item.getImg());
                    shareInfo.setText(item.getDigest());
                    shareInfo.setTitle(item.getTitle());
                    shareInfo.setComment("");//我的评论
                    shareInfo.setUrl(Contance.getBeautyDetailUrl(item.getDocid()));
                    intent.putExtra(DetailActivity.EXTRA_SHAREINFO, shareInfo);
                    LogUtils.v(TAG, "分享的数据---->" + shareInfo);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);

                }

            }
        });
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getIndexDate(true);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
        mListView.setOnScrollListener(this);

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
                int current = Integer.MAX_VALUE / 2;
                mBannerGallery.setSelection(current - (current % mBannerDataList.size()));
                addDotsImage(mBannerDataList.size());
            } else {
                mBannerGalleryAdapter.setData(mBannerDataList);
                mBannerGalleryAdapter.notifyDataSetChanged();
                int current = Integer.MAX_VALUE / 2;
                mBannerGallery.setSelection(current - (current % mBannerDataList.size()));
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

    private void setDetailDate() {
        if (mNewsDetailList != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new NewsItemAdapter(getActivity());
                mNewsItemAdapter.setData(mNewsDetailList);
                mListView.setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.setData(mNewsDetailList);
                mNewsItemAdapter.notifyDataSetChanged();
            }
            Log.i("tanyaping", "setDetailDate");
        }
    }

    private void update(List<NewDetail> tmp) {
        if (tmp != null) {
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new NewsItemAdapter(getActivity());
                mNewsItemAdapter.setData(tmp);
                mListView.setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.updateList(tmp);

                mPtrFrame.refreshComplete();

            }

            Log.i("tanyaping", "update--->");
        }
    }

    public void getIndexDate(final boolean isResh) {
        if (isGetingData) {
            return;
        }
        if (isResh) {
            index = 0;
        }
        culIndex();
        // // TODO: 2016/5/16 网络请求架构在UI线程返回call back 的success和error
        new Thread(new Runnable() {
            @Override
            public void run() {
                isGetingData = true;
                HttpUtil httputil = HttpUtil.getInstance();
                String url = Contance.getHotUrl(startIndex, endIndex);
                Log.i("hked", "endIndex = " + endIndex);
                httputil.doGet(url, new HttpResponse<Hot>(Hot.class) {
                    @Override
                    public void onSuccess(Hot hot) {

                        if (hot != null && hot.getT1348647909107() != null && hot.getT1348647909107().size() > 0) {
                            mBannerDataList = hot.getT1348647909107().get(0).getAds();// 广告位数据

                        }
                        if (hot != null && hot.getT1348647909107() != null) {
                            if (isResh) {
                                List<NewDetail> date = hot.getT1348647909107();
                                date.remove(0);
                                mNewsDetailList = date;
                                // 获取数据完毕，发送消息更新UI
                                Message message = handler.obtainMessage(INIT_LIST_DATE);
                                handler.sendMessage(message);
                            } else {
                                List<NewDetail> data = hot.getT1348647909107();
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
            mPtrFrame.refreshComplete();
            switch (msg.what) {
                case INIT_LIST_DATE:
                    setBannerData();
                    setDetailDate();

                    break;
                case UPDATE_LIST_DATE:
                    Log.i("hked", "arr");
                    List<NewDetail> data = (List<NewDetail>) msg.obj;
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
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isScrlooToButtom) {
            Log.i("hked", "in");
            getIndexDate(false);
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