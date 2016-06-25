package com.it520.activity.read.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.read.recommend.adapter.RecommendAdapter;
import com.it520.activity.read.recommend.bean.RecommendDetail;
import com.it520.activity.read.recommend.bean.RecommendRead;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;


/**
 * @author moy
 * @time 2016/6/20  15:50
 * @desc 推荐阅读
 */

public class ReadFragment extends Fragment implements XListView.IXListViewListener {
    LayoutInflater mInflater;
    private XListView mListView;

    private RecommendAdapter mRecommendAdapter;
    private List<RecommendDetail> mData;
    private static final int LOADDATASHEAD = 1;
    private static final int LOADDATASEND = 2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_read_recom, null, false);
        initView(view);
        loadDatas(1);
        return view;
    }

    private void initView(View view) {
        mListView = (XListView) view.findViewById(R.id.read_recom_listview);
        //mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.recom_ptr_frame);
       /* mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //拿数据,开始时
                loadDatas();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);*/


        mRecommendAdapter = new RecommendAdapter(getActivity());
        mListView.setAdapter(mRecommendAdapter);
        mRecommendAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecommendDetail item = mRecommendAdapter.getItem(position - 1);
                Intent intent = new Intent();
                String docID = item.getDocid();
                intent.setClass(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.DOC_ID, docID);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in_animation, R.anim.activity_out_animation);
            }
        });
        mListView.setXListViewListener(this);
        //开启加载更多的监听
        mListView.setPullLoadEnable(true);

    }

    private void loadDatas(final int where) {
        new Thread() {
            public void run() {
                HttpUtil httputil = HttpUtil.getInstance();
                String url = Contance.READ_RECOMMEND_URL;
                httputil.doGet(url, new HttpResponse<RecommendRead>(RecommendRead.class) {
                    @Override
                    public void onSuccess(RecommendRead read) {
                        if (read != null && read.get推荐() != null && read.get推荐().size() > 0) {
                            mData = read.get推荐();
                            //设置数据
                            if (where == LOADDATASHEAD) {
                                handler.sendEmptyMessage(LOADDATASHEAD);
                            } else {
                                handler.sendEmptyMessage(LOADDATASEND);
                            }

                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(getActivity(), "加载数据失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();


    }

    //设置数据
    private void setDetailDate(int where) {
        if (mData != null) {
            if (mRecommendAdapter == null) {
                mRecommendAdapter = new RecommendAdapter(getActivity());
                mRecommendAdapter.setDatas(mData, where);
                mListView.setAdapter(mRecommendAdapter);
                mRecommendAdapter.notifyDataSetChanged();
            } else {
                mRecommendAdapter.setDatas(mData, where);
                mRecommendAdapter.notifyDataSetChanged();
                mListView.stopRefresh();
                //刷新时候应该被修改
                SimpleDateFormat fornatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                mListView.setRefreshTime(fornatter.format(new Date()));
            }
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADDATASHEAD:
                    setDetailDate(LOADDATASHEAD);
                    break;
                case LOADDATASEND:
                    setDetailDate(LOADDATASEND);
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        loadDatas(RecommendAdapter.LOADHEAD);
    }

    @Override
    public void onLoadMore() {
        loadDatas(RecommendAdapter.LOADEND);
    }
}
