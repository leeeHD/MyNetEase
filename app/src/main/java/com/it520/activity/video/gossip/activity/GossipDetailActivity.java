package com.it520.activity.video.gossip.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.it520.activity.R;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.video.gossip.adapter.GossipDetailAdapter;
import com.it520.activity.video.gossip.bean.Gossip;
import com.it520.activity.video.gossip.bean.Recommend;

import java.util.List;

/**
 * CEO:mac on 2016/6/20 20:51.
 * FUNCTION:
 */
public class GossipDetailActivity extends Activity {
    public static final String GOSSIP = "gossip";
    private static final int INIT = 0;
    private static final int INIT_RECYCLERVIEW = 1;
    private RecyclerView mGossipDetailRcv;
    private RelativeLayout mGossipRl;
    private Gossip mGossip;
    private List<Gossip> mRecommend;
    private String mVid;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case INIT :
                    mGossipRl.setVisibility(View.INVISIBLE);
                    initRecyclerData(mRecommend);
                    getData(mVid);
                    break;
                case INIT_RECYCLERVIEW:
                    List<Gossip> recommend = (List<Gossip>) msg.obj;
                    initRecyclerData(recommend);
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gossip_detail);
        super.onCreate(savedInstanceState);
        mGossipRl = (RelativeLayout) findViewById(R.id.gossip_rl);
        mGossip = (Gossip) getIntent().getSerializableExtra(GOSSIP);
        int time = (int)(Math.random() * 3000);
        System.out.println("消耗的时间是:"+time);
        handler.sendEmptyMessageDelayed(INIT,time);
        mVid = mGossip.getVid();
    }
    //加载数据
    private void getData(String vid) {
        String gossipDetailUrl = Contance.getGossipDetailUrl(vid);
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.doGet(gossipDetailUrl, new HttpResponse<Recommend>(Recommend.class) {
            public void onSuccess(Recommend o) {
                mRecommend = o.getRecommend();
                Message msg = handler.obtainMessage(INIT_RECYCLERVIEW);
                msg.obj = mRecommend;
                handler.sendMessage(msg);
            }
            public void onError(String error) {

            }
        });
    }
    //初始化listview数据
    private void initRecyclerData(List<Gossip> recommend) {
        mGossipDetailRcv = (RecyclerView) findViewById(R.id.gossip_detail_rcv);
        GossipDetailAdapter gossipDetailAdapter = new GossipDetailAdapter(this,mGossip,recommend);
        mGossipDetailRcv.setAdapter(gossipDetailAdapter);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mGossipDetailRcv.setLayoutManager(linearLayoutManager);
        mGossipDetailRcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                linearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });
    }
}
