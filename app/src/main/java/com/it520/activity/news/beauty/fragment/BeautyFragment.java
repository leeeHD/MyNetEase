package com.it520.activity.news.beauty.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.it520.activity.R;
import com.it520.activity.news.beauty.activity.DetailActivity;
import com.it520.activity.news.beauty.adapter.BeautyAdapter;
import com.it520.activity.news.beauty.bean.Beauty;
import com.it520.activity.news.beauty.bean.Beautys;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * CEO:mac on 2016/6/19 13:59.
 * FUNCTION:
 */
public class BeautyFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final int INIT_LISTVIEW = 0;
    private ListView mBeautyLv;
    private BeautyHandler mHandler;
    private BeautyAdapter mAdapter;
    private SharedPreferences mSp;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty, null);
        initView(view);
        mSp = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return view;
    }
    /**
     * 初始化view
     * @param view
     */
    private void initView(View view) {
        //获取url
        String beautyUrl = Contance.BEAUTY_URL;
        //加载网络数据
        getData(beautyUrl);
        mHandler = new BeautyHandler(BeautyFragment.this);
        mBeautyLv = (ListView) view.findViewById(R.id.beauty_lv);
        mBeautyLv.setOnItemClickListener(this);





    }
    private void getData(final String beautyUrl) {
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.doGet(beautyUrl, new HttpResponse<Beautys>(Beautys.class) {
            public void onSuccess(Beautys o) {
                System.out.println("解析的数据"+o);
                //获取所有的解析数据
                Gson gson = new Gson();
                if(o==null) {
                    String json = mSp.getString("SAVEJSON", "");
                    System.out.println("------>"+json);
                    o = gson.fromJson(json, new TypeToken<Beautys>() {
                    }.getType());
               }else{
                    String jsonStr = gson.toJson(o);
                    SharedPreferences.Editor edit =  mSp.edit();
                    edit.putString("SAVEJSON",jsonStr);
                    edit.commit();
                }
                List<Beauty> beautys = o.getLists();
                Message msg = mHandler.obtainMessage(INIT_LISTVIEW);
                msg.obj = beautys;
                mHandler.sendMessage(msg);
            }
            public void onError(String error) {

            }
        });
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Beauty beauty = (Beauty) mAdapter.getItem(position);
        Intent intent = new Intent();
        String docid = beauty.getDocid();
        intent.setClass(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.DOC_ID,docid);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_in_animation,R.anim.activity_out_animation);
        getActivity().finish();
    }


    //弱引用,方便Handler回收
    class BeautyHandler extends Handler{
        WeakReference<BeautyFragment> weakReference;
        public BeautyHandler(BeautyFragment fragment){
            weakReference = new WeakReference<>(fragment);
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BeautyFragment beautyFragment = weakReference.get();
            if(beautyFragment==null) {
                return;
            }
            switch (msg.what) {
                case INIT_LISTVIEW:
                    List<Beauty> beauties = (List<Beauty>) msg.obj;
                    beautyFragment.initListViewData(beauties);
                    break;
            }
        }
    }
    private void initListViewData(List<Beauty> beauties) {
        if(mAdapter==null) {
            mAdapter = new BeautyAdapter(beauties,getContext());
            //设置数据
            mBeautyLv.setAdapter(mAdapter);
        }

    }
}
