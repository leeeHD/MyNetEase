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
import com.it520.activity.read.mine.ReadFragment;
import com.it520.activity.read.recommend.fragment.RecomReadFramgent;

import java.util.ArrayList;


/**
 * @author moy
 * @time 2016/6/20  15:50
 * @desc 阅读
 */

public class ReadingFragment extends Fragment {

    private ViewPager pager;
    private SmartTabLayout viewpagerTab;
    ArrayList<FragmentInfo> info = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, null);
        String[] newTitles = getResources().getStringArray(R.array.read_titles);
        FrameLayout frame = (FrameLayout) view.findViewById(R.id.read_tab);
        //添加fragment?
        frame.addView(LayoutInflater.from(getContext()).inflate(R.layout.include_read_tabs, frame, false));
        viewpagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagerTab);
        pager = (ViewPager) view.findViewById(R.id.read_viewpager);

        for (int i = 0; i < newTitles.length; i++) {
            switch (i) {
                case 0://推荐阅读
                    info.add(new FragmentInfo(newTitles[i], RecomReadFramgent.class.getName()));
                    break;
                case 1://我的阅读
                    info.add(new FragmentInfo(newTitles[i], ReadFragment.class.getName()));

                    break;
            }
        }
        Adapt adapt = new Adapt(getContext(), getFragmentManager(), info);
        pager.setAdapter(adapt);
        pager.setOffscreenPageLimit(2);
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
