package com.it520.activity.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.main.fragment.NewsFragment;
import com.it520.activity.main.fragment.ReadingFragment;
import com.it520.activity.main.fragment.TopicFragment;
import com.it520.activity.main.fragment.VideoFragment;
import com.it520.activity.main.wight.FragmentTabHost;
import com.it520.activity.mine.MyFrgment;

import java.util.ArrayList;

/**
 * Created by kay on 16/5/12.
 */
public class IndexActivity extends FragmentActivity implements TabHost.OnTabChangeListener {
    private FragmentTabHost tabHost;

    private long exitTime = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        tabHost = (FragmentTabHost) super.findViewById(R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();
    }


    private void initTab() {
        String[] titles = getResources().getStringArray(R.array.titles);
        Class[] fragments = {NewsFragment.class, ReadingFragment.class, VideoFragment.class, TopicFragment.class, MyFrgment.class};
        int [] images = {R.drawable.news_selector,R.drawable.reading_selector,R.drawable.video_selector,R.drawable.topic_selector,R.drawable.mine_selector};
        ArrayList<Tab> tabInfors = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Tab tmp = new Tab(titles[i],fragments[i]);
            tabInfors.add(tmp);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(titles[i]).setIndicator(getTabView(i,titles,images));
            tabHost.addTab(tabSpec, fragments[i], null);
            tabHost.setTag(i);
        }

    }

    private View getTabView(int idx,String [] title,int[] image) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_title, null);
        ((TextView) view.findViewById(R.id.tvTab)).setText(title[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.tvTab)).setTextColor(Color.RED);

        }
        ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(image[idx]);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        changeTab();
    }


    private void changeTab(){
        TabWidget tabw=tabHost.getTabWidget();
        for(int i=0;i<tabw.getChildCount();i++){
            View view=tabw.getChildAt(i);
            ImageView iv=(ImageView)view.findViewById(R.id.ivImg);
            if(i==tabHost.getCurrentTab()){
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.RED);
                iv.setSelected(true);
            }else{
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.gray));
                iv.setSelected(false);
            }

        }
    }

    @Override
    public void onBackPressed() {
        if((System.currentTimeMillis()-exitTime) > 2000){
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
