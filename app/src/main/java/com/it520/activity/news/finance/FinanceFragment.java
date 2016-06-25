package com.it520.activity.news.finance;

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
import com.it520.activity.news.finance.Adapter.FinanceBannerAdapter;
import com.it520.activity.news.finance.Adapter.FinanceItemAdapter;
import com.it520.activity.news.finance.Cons.Constance;
import com.it520.activity.news.finance.bean.Finance;
import com.it520.activity.news.finance.bean.FinanceAds;
import com.it520.activity.news.finance.bean.FinanceDetail;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.widget.BannerGallery;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/19 0019.
 */
public class FinanceFragment extends Fragment implements AbsListView.OnScrollListener {
    LayoutInflater mInflater;
    PtrClassicFrameLayout mPtrFrame;
    ListView mListView;
    private View mBannerLayout;
    private BannerGallery mBannerGallery;
    private FinanceBannerAdapter mBannerGalleryAdapter;

    FinanceItemAdapter mFinanceItemAdapter;

    List<FinanceAds> mBannerDataList;
    List<FinanceDetail> mFinanceDetailList;

    public int startIndex = 0;
    public int endIndex = 0;
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
        View view = inflater.inflate(R.layout.fragment_news_finance, null, false);
        initView(view);
        getIndexDate(true);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.finance_listview);
        
        mBannerLayout = this.mInflater.inflate(R.layout.news_banner_layout, null);
        dots = (LinearLayout) mBannerLayout.findViewById(R.id.dots);
        banner_title = (TextView) mBannerLayout.findViewById(R.id.banner_title);
        mBannerGallery = (BannerGallery) mBannerLayout.findViewById(R.id.news_hot_banner_gallery);
        mBannerGallery.setFocusable(true);
        mBannerGallery.setUnselectedAlpha(100);
        mListView.addHeaderView(mBannerLayout);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FinanceDetail financeDetail = mFinanceItemAdapter.getItem(position-1);
                Log.i("-------------->",financeDetail.toString());
                Intent intent = new Intent();
                String docid = financeDetail.getDocid();
                intent.setClass(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.DOC_ID,docid);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in_animation,R.anim.activity_out_animation);
//                getActivity().finish();
            }
        });

        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_finance_frame);
//        Using an object to specify the last update time.
        mPtrFrame.setLastUpdateTimeRelateObject(this);  //下拉刷新
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

    private void setBannerData() {
        // Banner
        if(mBannerDataList!=null) {
            if(mBannerGalleryAdapter==null) {
                mBannerGalleryAdapter = new FinanceBannerAdapter(getActivity());
                mBannerGalleryAdapter.setData(mBannerDataList);
                mBannerGallery.setAdapter(mBannerGalleryAdapter);
                int current = Integer.MAX_VALUE/2;
                mBannerGallery.setSelection(current - (current%mBannerDataList.size()));
                addDotsImage(mBannerDataList.size());
            }else {
                mBannerGalleryAdapter.setData(mBannerDataList);
                mBannerGalleryAdapter.notifyDataSetChanged();
                int current = Integer.MAX_VALUE/2;
                mBannerGallery.setSelection(current - (current % mBannerDataList.size()));
            }
        }

    }
    private void addDotsImage(int size){
        for (int i =0 ;i<size;i++){
            ImageView image = new ImageView(getActivity());
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
        if (mFinanceDetailList != null) {
            if (mFinanceItemAdapter == null) {
                mFinanceItemAdapter = new FinanceItemAdapter(getActivity());
                mFinanceItemAdapter.setData(mFinanceDetailList);
                mListView.setAdapter(mFinanceItemAdapter);
            } else {
                mFinanceItemAdapter.setData(mFinanceDetailList);
                mFinanceItemAdapter.notifyDataSetChanged();
            }
            Log.i("tanyaping", "setDetailDate");
        }
    }
    private void update(List<FinanceDetail> tmp) {
        if(tmp!=null) {
            if(mFinanceItemAdapter==null) {
                mFinanceItemAdapter=new FinanceItemAdapter(getActivity());
                mFinanceItemAdapter.setData(tmp);
                mListView.setAdapter(mFinanceItemAdapter);
            }else {
                mFinanceItemAdapter.updateList(tmp);
            }
        }
    }

    private void getIndexDate(final boolean isResh) {
        if(isGetingData){
            return;
        }
        if (isResh) {
            index = 0;
        }
        // // TODO: 2016/5/16 网络请求架构在UI线程返回call back 的success和error
        new Thread(new Runnable() {
            @Override
            public void run() {
                isGetingData = true;
                HttpUtil httputil = HttpUtil.getInstance();
                httputil.doGet(Constance.FINANCE_URL, new HttpResponse<Finance>(Finance.class) {
                    @Override
                    public void onSuccess(Finance finance) {
                        if(finance!=null && finance.getT1348648756099()!=null && finance.getT1348648756099().size()>0) {
                            mBannerDataList = finance.getT1348648756099().get(0).getAds();
                        }
                        if(finance!=null && finance.getT1348648756099()!=null) {
                            if(isResh) {
                                List<FinanceDetail> data = finance.getT1348648756099();
                                data.remove(0);
                                mFinanceDetailList = data;
                                // 获取数据完毕，发送消息更新UI
                                Message message = handler.obtainMessage(INIT_LIST_DATE);
                                handler.sendMessage(message);
                            }
                        }else {
                            List<FinanceDetail> data = finance.getT1348648756099();
                            Message message = handler.obtainMessage(UPDATE_LIST_DATE);
                            message.obj = data;
                            handler.sendMessage(message);
                        }

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
                    Log.i("hked", "arr");
                    List<FinanceDetail> data = (List<FinanceDetail>) msg.obj;
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE && isScrlooToButtom) {
            getIndexDate(false);  //在底部的时候就不加载新数据了
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
