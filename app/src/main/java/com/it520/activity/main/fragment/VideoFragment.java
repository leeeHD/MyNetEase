package com.it520.activity.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.it520.activity.R;
import com.it520.activity.main.wight.SmartTabLayout;
import com.it520.activity.video.gossip.fragment.GossipFragment;
import com.it520.activity.video.quadratic.fragment.QuadraticFragment;

import java.util.ArrayList;

/**
 * Created by kay on 16/5/12.
 */
public class VideoFragment extends Fragment {
    SmartTabLayout viewpagerTab;
    ViewPager pager;
    ArrayList<FragmentInfo> info = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, null);
        String[] videoTitles = getResources().getStringArray(R.array.video_titles);
        FrameLayout frame = (FrameLayout) view.findViewById(R.id.tab);
        frame.addView(LayoutInflater.from(getContext()).inflate(R.layout.include_tabs, frame, false));
        viewpagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagerTab);
        pager = (ViewPager) view.findViewById(R.id.video_viewpager);
        for(int i = 0; i < videoTitles.length; i++) {
            switch (i) {
                case 0 :
                    info.add(new FragmentInfo(videoTitles[i], GossipFragment.class.getName()));
                    break;
                case 1:
                    info.add(new FragmentInfo(videoTitles[i], QuadraticFragment.class.getName()));
                    break;
            }
        }
        MyAdapter adapter = new MyAdapter(getContext(),getFragmentManager(), info);
        pager.setAdapter(adapter);
        //pager.setOffscreenPageLimit(4);
        viewpagerTab.setViewPager(pager);
        return view;
    }
    class MyAdapter extends FragmentPagerAdapter {
        ArrayList<FragmentInfo> info;
        Context context;

        public MyAdapter(Context context, FragmentManager fm, ArrayList<FragmentInfo> info) {
            super(fm);
            this.info = info;
            this.context = context;
        }
        public Fragment getItem(int position) {
            FragmentInfo fragmentInfo = info.get(position);
            return Fragment.instantiate(context, fragmentInfo.getFragment());
        }
        public int getCount() {
            return info.size();
        }
        public CharSequence getPageTitle(int position) {
            FragmentInfo fragmentInfo = info.get(position);
            return fragmentInfo.getTitle();
        }
    }
}
