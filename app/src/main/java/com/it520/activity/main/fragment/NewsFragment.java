package com.it520.activity.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.main.wight.SmartTabLayout;
import com.it520.activity.news.beauty.fragment.BeautyFragment;
import com.it520.activity.news.city.CityNewsFragment;
import com.it520.activity.news.finance.FinanceFragment;
import com.it520.activity.news.fragment.HotFragment;
import com.it520.activity.news.phone.fragment.PhoneFragment;
import com.it520.activity.news.scienci.fragment.ScienceFragment;
import com.it520.activity.news.sport.fragment.SportFragment;

import java.util.ArrayList;

//import com.it520.activity.news.sport.fragment.SportFragment;


/**
 * Created by kay on 16/5/12.
 */
public class NewsFragment extends Fragment {
    SmartTabLayout viewpagerTab;
    ViewPager pager;
    ArrayList<FragmentInfo> info = new ArrayList<>();
    private TextView live_entrance_tv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        String[] newTitles = getResources().getStringArray(R.array.news_titles);
        FrameLayout frame = (FrameLayout) view.findViewById(R.id.tab);
        frame.addView(LayoutInflater.from(getContext()).inflate(R.layout.include_tabs, frame, false));
        viewpagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagerTab);
        pager = (ViewPager) view.findViewById(R.id.viewpager);

        for (int i = 0; i < newTitles.length; i++) {
            switch (i) {
                case 0://头条
                    info.add(new FragmentInfo(newTitles[i], HotFragment.class.getName()));
                    break;
                case 1://娱乐

                    break;
                case 2://热点

                    break;
                case 3://体育
                   info.add(new FragmentInfo(newTitles[i], SportFragment.class.getName()));

                    break;
                case 4://本地

                    break;
                case 5://网易号

                    break;
                case 6://北京
                    info.add(new FragmentInfo(newTitles[i], CityNewsFragment.class.getName()));
                    break;
                case 7://财经
                    info.add(new FragmentInfo(newTitles[i], FinanceFragment.class.getName()));
                    break;
                
             case 8://科技
                    info.add(new FragmentInfo(newTitles[i], ScienceFragment.class.getName()));
                    break;
                case 9://汽车
                    info.add(new FragmentInfo(newTitles[i], MainFragment.class.getName()));
                    break;
                case 10://美女
                    info.add(new FragmentInfo(newTitles[i], BeautyFragment.class.getName()));
                    break;
                case 11://手机
                    info.add(new FragmentInfo(newTitles[i],PhoneFragment.class.getName()));
                    break;
            }
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


    class Adapt extends FragmentStatePagerAdapter {
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
