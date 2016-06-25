package com.it520.activity.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it520.activity.R;
import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;

/**
 * Created by kay on 16/5/12.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main,null,false);
        final PtrClassicFrameLayout ptrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame);
        ptrFrame.setLastUpdateTimeRelateObject(this);
        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
        return view;
    }
}
