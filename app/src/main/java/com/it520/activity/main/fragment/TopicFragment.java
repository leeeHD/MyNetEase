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
import com.it520.activity.topic.SubjectFragment;

import java.util.ArrayList;

/**
 * Created by dong on 16/5/12.
 */
public class TopicFragment extends Fragment {
    SmartTabLayout viewpagerTab;
    ViewPager pager;
    ArrayList<FragmentInfo> info = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic, null);

        String[] topicTitles = getResources().getStringArray(R.array.topic_titles);
        FrameLayout frame = (FrameLayout) view.findViewById(R.id.topic_tab);
        frame.addView(LayoutInflater.from(getContext()).inflate(R.layout.include_topic_tabs, frame, false));
        viewpagerTab = (SmartTabLayout) view.findViewById(R.id.topic_viewpagerTab);
        pager = (ViewPager) view.findViewById(R.id.topic_viewpager);

        for (int i = 0; i < topicTitles.length; i++)
            switch (i) {
                case 0://话题
                    info.add(new FragmentInfo(topicTitles[i], SubjectFragment.class.getName()));
                    break;
                case 1://问题
                    info.add(new FragmentInfo(topicTitles[i], MainFragment.class.getName()));
                    break;
                case 2://关注
                    info.add(new FragmentInfo(topicTitles[i], MainFragment.class.getName()));
                    break;
            }
        Adapt adapt = new Adapt(getContext(), getFragmentManager(), info);
        pager.setAdapter(adapt);
        pager.setOffscreenPageLimit(4);
        viewpagerTab.setViewPager(pager);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            return Fragment.instantiate(context, fragmentInfo.getFragment());
        }

        @Override
        public int getCount() {
            return info.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            FragmentInfo fragmentInfo = info.get(position);
            return fragmentInfo.getTitle();
        }
    }
}
