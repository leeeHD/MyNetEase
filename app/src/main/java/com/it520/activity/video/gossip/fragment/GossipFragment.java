package com.it520.activity.video.gossip.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it520.activity.R;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.video.gossip.activity.GossipDetailActivity;
import com.it520.activity.video.gossip.adapter.GossipAdapter;
import com.it520.activity.video.gossip.bean.Gossip;
import com.it520.activity.video.gossip.bean.Gossips;
import com.it520.activity.video.gossip.listener.ItemClickListener;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * CEO:mac on 2016/6/20 15:14.
 * FUNCTION:
 */
public class GossipFragment extends Fragment implements ItemClickListener{
    private static final int INIT_GRIDVIEW = 0;
    private GossipHandler mHandler;
    private GossipAdapter mAdapter;
    private RecyclerView mGossipRcv;
    private List<Gossip> mGossips;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gossip, null);
        initView(view);
        return view;
    }
    private void initView(View view) {
        //获取url
        String gossipUrl = Contance.GOSSIP_URL;
        //加载网络数据
        getData(gossipUrl);
        mHandler = new GossipHandler(GossipFragment.this);
        mGossipRcv = (RecyclerView) view.findViewById(R.id.gossip_recycler_view);

    }
    private void getData(String gossipUrl) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.doGet(gossipUrl, new HttpResponse<Gossips>(Gossips.class) {
            public void onSuccess(Gossips o) {
                List<Gossip> gossips = o.getLists();
                Message msg = mHandler.obtainMessage(INIT_GRIDVIEW);
                msg.obj = gossips;
                mHandler.sendMessage(msg);
            }
            public void onError(String error) {

            }
        });
    }
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        Gossip gossip = mGossips.get(position);
        intent.setClass(getContext(), GossipDetailActivity.class);
        intent.putExtra(GossipDetailActivity.GOSSIP,gossip);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_in_animation,R.anim.activity_out_animation);
    }

    //弱引用,方便Handler回收
    class GossipHandler extends Handler {
        WeakReference<GossipFragment> weakReference;
        public GossipHandler(GossipFragment fragment){
            weakReference = new WeakReference<>(fragment);
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GossipFragment gossipFragment = weakReference.get();
            if(gossipFragment==null) {
                return;
            }
            switch (msg.what) {
                case INIT_GRIDVIEW:
                    mGossips = (List<Gossip>) msg.obj;
                    gossipFragment.initGridViewData(mGossips);
                    break;
            }
        }
    }
    private void initGridViewData(List<Gossip> gossips) {
        if(mAdapter==null) {
            mAdapter = new GossipAdapter(gossips, getContext());
        }
        mAdapter.setListener(this);
        mGossipRcv.setAdapter(mAdapter);
        mGossipRcv.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
}
