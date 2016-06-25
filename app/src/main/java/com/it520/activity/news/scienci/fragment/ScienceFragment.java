package com.it520.activity.news.scienci.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.main.fragment.YxxBaseFragment;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.scienci.adapter.NewsDetailAdapter;
import com.it520.activity.news.scienci.bean.Science;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.share.ShareInfo;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.ImageUtile;
import com.it520.activity.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by Yu xiangxin on 2016/6/19 - 14:44.
 *
 * @desc ${TODD}
 */
public class ScienceFragment extends YxxBaseFragment implements XListView.IXListViewListener,
        AbsListView.OnScrollListener, ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener {
    private static final java.lang.String TAG = "ScienceFragment";
    private static final long INDICATTOR_TIME = 3000;
    private int mPageIndex = 0;
    private View mParaentView;
    private XListView mListview;
    private List<Science.ScienceItem.ScienceItemAds> mScienceItemAdses;//轮播广告的数据集
    private View mAdsBannerLayout;
    private LinearLayout mAdsDotsContent;
    private TextView mAdsBannerTitle;
    private ViewPager mAdsViewPager;
    private AdsAdapter mAdsAdapter;
    private List<Science.ScienceItem> mDatas = new ArrayList<>();
    private NewsDetailAdapter mNewsDetailAdapter;

    public final int INIT_DATA = 0;
    public final int REFRESH_DATA = 1;
    public final int LOAD_MORE_DATA = 3;
    public final int REQUEST_ERROR = -1;
    //private IndicatorTask mIndicatorTask;

    //
    @Override
    protected void handleMsg(Message msg) {
        switch (msg.what) {
            case LOAD_MORE_DATA:
                mListview.stopLoadMore();
            case INIT_DATA:
            case REFRESH_DATA:
                handLoadData(msg.what, (Science) msg.obj);
                mPtrFrame.refreshComplete();
                break;
            case REQUEST_ERROR:
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                mPtrFrame.refreshComplete();
                mListview.stopLoadMore();
                break;

        }
    }


    void handLoadData(int action, Science obj) {
        if (obj != null && obj.getT1348649580692().size() != 0) {
            //广告轮播图片
            Science.ScienceItem remove = obj.getT1348649580692().remove(0);
            mScienceItemAdses = remove.getAds();
            Science.ScienceItem.ScienceItemAds scienceItemAds = new Science.ScienceItem.ScienceItemAds();
            scienceItemAds.setImgsrc(remove.getImgsrc());
            scienceItemAds.setDocid(remove.getDocid());
            scienceItemAds.setSubtitle(remove.getTitle());
            scienceItemAds.setTitle(remove.getLtitle());
            scienceItemAds.setUrl(remove.getUrl());

            mScienceItemAdses.add(scienceItemAds);
            if (action == REFRESH_DATA) {
                mDatas.clear();
            }
            mDatas.addAll(obj.getT1348649580692());

            if (mScienceItemAdses != null) {
                mAdsBannerTitle.setText(mScienceItemAdses.get(0).getTitle());
                initAdsIndicator(mAdsDotsContent, mScienceItemAdses.size());
                mListview.removeHeaderView(mAdsBannerLayout);
                mListview.addHeaderView(mAdsBannerLayout);
                mAdsViewPager.setCurrentItem(mScienceItemAdses.size() * 10000);

                mAdsAdapter.notifyDataSetChanged();
            }
            // TODO: 2016/6/19 填充主数据
            mNewsDetailAdapter.reSetDataWithRefresh(mDatas);

        }
    }


    private void initAdsIndicator(LinearLayout parent, int size) {
        parent.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView image = new ImageView(getContext());
            image.setBackgroundResource(R.drawable.gray_dot);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.setMargins(10, 0, 0, 0);
            image.setLayoutParams(p);
            parent.addView(image);
        }
        changeDotsStatus(0);
    }

    private void changeDotsStatus(int position) {
        for (int i = 0; i < mAdsDotsContent.getChildCount(); i++) {
            ImageView tmp = (ImageView) mAdsDotsContent.getChildAt(i);
            if (position == i) {
                tmp.setBackgroundResource(R.drawable.white_dot);
            } else {
                tmp.setBackgroundResource(R.drawable.gray_dot);
            }
        }
    }


    @Override
    protected View initParentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParaentView = inflater.inflate(R.layout.fragment_news_hot, null, false);

        initView();

        return mParaentView;
    }

    private void initView() {
        mListview = (XListView) mParaentView.findViewById(R.id.listview);
        mListview.setPullRefreshEnable(false);//禁用下拉刷新,已经在别的地方实现
        mListview.setPullLoadEnable(true);//启用上拉加载更多
        mListview.setXListViewListener(this);//设置加载数据回掉
        mListview.setOnScrollListener(this);//设置滑动监听
        mListview.setOnItemClickListener(this);
        mNewsDetailAdapter = new NewsDetailAdapter(null);
        mListview.setAdapter(mNewsDetailAdapter);

        mAdsBannerLayout = mInflater.inflate(R.layout.frag_news_science_layout, null);
        mAdsDotsContent = (LinearLayout) mAdsBannerLayout.findViewById(R.id.dots);
        mAdsBannerTitle = (TextView) mAdsBannerLayout.findViewById(R.id.banner_title);
        mAdsViewPager = (ViewPager) mAdsBannerLayout.findViewById(R.id.science_vp);
        mAdsAdapter = new AdsAdapter();
        mAdsViewPager.setAdapter(mAdsAdapter);
        mAdsViewPager.addOnPageChangeListener(this);


        //mAdsViewPager.setOnTouchListener(this);

        //mIndicatorTask = new IndicatorTask();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadData(INIT_DATA);
    }


    private void loadData(final int action) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Contance.getNewsScience(mPageIndex);
                mHttpUtil.doGet(url, new HttpResponse<Science>(Science.class) {
                    @Override
                    public void onSuccess(Science obj) {
                        mHandler.obtainMessage(action, obj).sendToTarget();
                        mPageIndex += Contance.DEFAULT_PAGE_SIZE;
                    }

                    @Override
                    public void onError(String error) {
                        mHandler.sendEmptyMessage(REQUEST_ERROR);
                        LogUtils.v(TAG, "数据加载失败" + error);
                    }
                });
            }
        }).start();
    }


    @Override
    protected PtrClassicFrameLayout initParaentView() {
        return (PtrClassicFrameLayout) mParaentView.findViewById(R.id.ptr_frame);
    }

    @Override
    protected void onRefreshData(PtrFrameLayout frame) {
        mPageIndex = 0;
        loadData(REFRESH_DATA);
        LogUtils.v(TAG, "" + "下拉刷新数据");
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {
        loadData(LOAD_MORE_DATA);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                int lastVisiblePosition = mListview.getLastVisiblePosition();
                int size = mDatas.size() + mListview.getFooterViewsCount() + mListview.getHeaderViewsCount();
                if (lastVisiblePosition + 1 >= size) {
                    loadData(LOAD_MORE_DATA);
                }
                break;

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        position = position % mScienceItemAdses.size();
        mAdsBannerTitle.setText(mScienceItemAdses.get(position).getTitle());
        changeDotsStatus(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mDatas != null && mDatas.get((int) id) != null) {
            Science.ScienceItem item = mDatas.get((int) id);
            item.getDocid();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.DOC_ID, item.getDocid());
            //把要分享的信息封装在 ShareInfo中,传递给 DetailActivity
            ShareInfo shareInfo = new ShareInfo();
            shareInfo.setImgUrl(item.getImgsrc());
            shareInfo.setText(item.getDigest());
            shareInfo.setTitle(item.getTitle());
            shareInfo.setComment("");//我的评论
            shareInfo.setUrl(item.getUrl());
            intent.putExtra(DetailActivity.EXTRA_SHAREINFO, shareInfo);

            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);

        }
    }

/*

    //ViewPager轮播图触摸暂停播放任务
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIndicatorTask.stop();
                break;
            case MotionEvent.ACTION_UP:
                mIndicatorTask.start();
                break;
            case MotionEvent.ACTION_MOVE:
                mIndicatorTask.stop();
                break;
        }
        return false;
    }

    private boolean mIndTaskIsRun = false;

    */

    /**
     * 轮播图指示器任务
     *//*

    class IndicatorTask implements Runnable {

        public void start() {
            mIndTaskIsRun = true;
            mHandler.postDelayed(this, INDICATTOR_TIME);
        }

        public void stop() {
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (mAdsViewPager != null && mScienceItemAdses != null && mScienceItemAdses.size() != 0)
                mAdsViewPager.setCurrentItem(mAdsViewPager.getCurrentItem() + 1);
            start();
        }
    }
*/


    class AdsAdapter extends PagerAdapter {

        public RelativeLayout.LayoutParams params = null;

        public AdsAdapter() {
            params = new RelativeLayout.LayoutParams(-1, -1);
        }

        @Override
        public int getCount() {
            return mScienceItemAdses != null ? Integer.MAX_VALUE : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public ImageView getItem(ViewGroup container, int position) {
            position = position % mScienceItemAdses.size();
            String imgUrl = mScienceItemAdses.get(position).getImgsrc();

            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageUtile.LoadImage(imgUrl, imageView);
            return imageView;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView item = getItem(container, position);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
