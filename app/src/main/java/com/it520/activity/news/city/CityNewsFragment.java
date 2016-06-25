package com.it520.activity.news.city;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.city.adapter.CityNewsAdapter;
import com.it520.activity.news.city.bean.ItemInfo;
import com.it520.activity.news.city.bean.WhetherInfo;
import com.it520.activity.news.city.service.CityApi;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CityNewsFragment extends Fragment {
    private static final String TAG = "CityNewsFragment";
    private LayoutInflater mInflater;
    private CityNewsAdapter mCityNewsAdapter;
    public RecyclerView mRecyclerView;
    private Context mContext;
    private WhetherInfo mWhetherInfos;
    private PtrClassicFrameLayout mPtrFrame;
    private Subscription mSubscribe;
    private LinearLayoutManager mLayoutManager;

    // 上拉加载更多参数
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int start = 0;
    private final static int DATA_COUNT_ONE_PAGE = 20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContext = getActivity();
        View view = mInflater.inflate(R.layout.fragment_news_beijing, null, false);
        initView(view);
        loadData(Constants.CITY_NEWS_URL);
        return view;
    }

    private void initView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_beijing);
        mCityNewsAdapter = new CityNewsAdapter(getActivity());
        mRecyclerView.setAdapter(mCityNewsAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // RecyclerView添加点击事件
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // 跳转到天气页面
                        if(position == 0 && mCityNewsAdapter.isWhetherPageShowing) {
                            Intent intent = new Intent(getActivity(), WhetherActivity.class);
                            intent.putExtra("whether", mWhetherInfos);
                            startActivity(intent);
                        }
                        else {
                            ItemInfo itemInfo = mCityNewsAdapter.mItemInfos.get(position);
                            String docid = itemInfo.getDocid();

                            Intent intent = new Intent(getActivity(), DetailActivity.class);
                            intent.putExtra(DetailActivity.DOC_ID, docid);
                            startActivity(intent);
                        }

                    }
                })
        );

        // RecyclerView的滚动监听
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // 往上滑
                if(dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ((visibleItemCount + pastVisiblesItems + 1) >= totalItemCount)
                        {
                            loading = false;
                            Log.d(TAG, "Last Item Wow !");
                            start += DATA_COUNT_ONE_PAGE;
                            int end = start + DATA_COUNT_ONE_PAGE;
                            String newUrl = Constants.getCityNewsUrl(start, end);
                            Log.d(TAG, "new url -> " + newUrl);
                            loadData(newUrl);
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });

        mPtrFrame = (PtrClassicFrameLayout) rootView.findViewById(R.id.city_ptr_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData(Constants.CITY_NEWS_URL);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
    }

    private void loadData(String url) {
        mSubscribe = getItemInfos(url)
                .subscribeOn(Schedulers.io()) // 另一个线程
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程更新UI
                .subscribe(new Observer<List<ItemInfo>>() {
                    @Override
                    public void onCompleted() {
                        mPtrFrame.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "加载失败",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,  e.getMessage());
                        mPtrFrame.refreshComplete();
                    }

                    @Override
                    public void onNext(List<ItemInfo> itemInfos) {
                        if(loading == false) {
                            mCityNewsAdapter.addToLast(itemInfos);
                            loading = true;
                        } else {
                            mCityNewsAdapter.setWhetherData(mWhetherInfos);
                            mCityNewsAdapter.addItemInfos(itemInfos);
                        }

                        mPtrFrame.refreshComplete();
                    }
                });
    }

    private Observable<List<ItemInfo>> getItemInfos(final String url) {
        return Observable.create(new Observable.OnSubscribe<List<ItemInfo>>() {
            @Override
            public void call(Subscriber<? super List<ItemInfo>> subscriber) {
                try {
                    List<ItemInfo> itemInfos = CityApi.getItemInfos(url);
                    mWhetherInfos = CityApi.getWhetherInfos(Constants.CITY_WETHER_URL);

                    subscriber.onNext(itemInfos);
                }
                catch (IOException | JSONException e) {
                    subscriber.onError(e);
                }

                if(!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSubscribe != null && !mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }
}
