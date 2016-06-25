package com.it520.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.it520.activity.main.fragment.FragmentInfo;
import com.it520.activity.main.wight.SmartTabLayout;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    SmartTabLayout viewpagerTab;
    ViewPager pager;
    ArrayList<FragmentInfo> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frame = (FrameLayout) findViewById(R.id.tab);
        frame.addView(LayoutInflater.from(this).inflate(R.layout.include_tabs, frame, false));
        viewpagerTab = (SmartTabLayout) findViewById(R.id.viewpagerTab);
        pager = (ViewPager) findViewById(R.id.viewpager);
//        for (int i = 0; i < 10; i++) {
//            info.add(new FragmentInfo("info " + i, MainFragment.class.getName()));
//        }
        Adapt adapt = new Adapt(this,getSupportFragmentManager(),info);
        pager.setAdapter(adapt);
        viewpagerTab.setViewPager(pager);
    }


    class Adapt extends FragmentPagerAdapter {
        ArrayList<FragmentInfo> info;
        Context context;

        public Adapt(Context context, FragmentManager fm, ArrayList<FragmentInfo> info) {
            super(fm);
            this.info = info;
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            FragmentInfo fragmentInfo = info.get(position);
            return Fragment.instantiate(context,fragmentInfo.getFragment());
        }

        @Override
        public int getCount() {
            return info.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            FragmentInfo fragmentInfo = info.get(position);
            return fragmentInfo.getTitle() ;
        }
    }

}
