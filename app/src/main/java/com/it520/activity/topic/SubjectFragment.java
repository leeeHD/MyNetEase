package com.it520.activity.topic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.news.city.Constants;
import com.it520.activity.news.city.RecyclerItemClickListener;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.topic.model.TopicApi;
import com.it520.activity.topic.model.bean.SubjBean;
import com.it520.activity.topic.model.bean.SunjDetailBean;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SubjectFragment extends Fragment {
    private static final String TAG = "SubjectFragment";
    private PtrClassicFrameLayout mPtrFrame;
    private LayoutInflater mInflater;
    private SubjectAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private Subscription mSubscribe;
    private LinearLayoutManager mLayoutManager;

    // 上拉加载更多参数
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int start = 0;
    private final static int DATA_COUNT_ONE_PAGE = 20;
    private List<SubjBean> mSubjList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContext = getActivity();

        View view = mInflater.inflate(R.layout.fragment_topic_subject, container, false);
        initView(view);
        loadData(Constants.TOPIC_SUBJECT_DETAIL_URL);
        return view;
    }


    private void initView(View rootView) {
        setRecyclerView(rootView);

        // 上拉加载更多
        setSrcollListner(rootView);

        // item点击事件
        // RecyclerView添加点击事件
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // 跳转到详情页面
                        Toast.makeText(getActivity(), "item click position - " + position, Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private void setRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_subject);
        mAdapter = new SubjectAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    // 上拉加载实现
    private void setSrcollListner(View rootView) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            start += DATA_COUNT_ONE_PAGE;
                            int end = start + DATA_COUNT_ONE_PAGE;
                            String newUrl = Constants.getSubjectUrl(start, end);
                            Log.d(TAG, "topic new url -- " + newUrl);
                            loadData(newUrl);
                        }
                    }
                }
            }
        });

        mPtrFrame = (PtrClassicFrameLayout) rootView.findViewById(R.id.topic_suject_ptr_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData(Constants.TOPIC_SUBJECT_DETAIL_URL);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
    }

    // 获取数据
    void loadData(String url){
        mSubscribe = getItemInfos(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<SunjDetailBean>() {
                @Override
                public void onCompleted() {
                    mPtrFrame.refreshComplete();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(mContext, "加载失败",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    mPtrFrame.refreshComplete();
                }

                @Override
                public void onNext(SunjDetailBean infos) {
                    if(loading == false) {
                        // update new data
                        mAdapter.updateSubjItemInfos(infos.getData().getSubjectList());
                        loading = true;
                    } else {
                        // initial data
                        mAdapter.addSubjInfos(mSubjList);
                        Log.d(TAG, mSubjList.toString());
                        mAdapter.addSubjItemInfos(infos.getData().getSubjectList());
                    }

                    mPtrFrame.refreshComplete();
                }
            });
    }

    private Observable<SunjDetailBean> getItemInfos(final String url) {
        return Observable.create(new Observable.OnSubscribe<SunjDetailBean>() {
            @Override
            public void call(Subscriber<? super SunjDetailBean> subscriber) {
                try {
                    mSubjList = TopicApi.getListBean(Constants.TOPIC_SUBJECT_BAR_URL, SubjBean.class, "话题");
                    SunjDetailBean infos = TopicApi.parse(url, SunjDetailBean.class);
                    subscriber.onNext(infos);
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
