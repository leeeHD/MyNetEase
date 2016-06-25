package com.it520.activity.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it520.activity.news.widght.PtrClassicFrameLayout;
import com.it520.activity.news.widght.PtrDefaultHandler;
import com.it520.activity.news.widght.PtrFrameLayout;
import com.it520.activity.util.HttpUtil;

/**
 * Created by Yu xiangxin on 2016/6/19 - 15:38.
 *
 * @desc ${TODD}
 */
public abstract class YxxBaseFragment extends Fragment {

    private static final java.lang.String TAG = "YxxBaseFragment";
    protected HttpUtil mHttpUtil = null;
    protected PtrClassicFrameLayout mPtrFrame;
    public LayoutInflater mInflater;

    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            handleMsg(msg);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHttpUtil = HttpUtil.getInstance();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        return initParentView(inflater, container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPtrFragment();
    }

    protected void initPtrFragment() {
        mPtrFrame = initParaentView();

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onRefreshData(frame);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.disableWhenHorizontalMove(true);
    }

    /**
     * handler 数据处理
     */
    protected abstract void handleMsg(Message msg);

    /**
     * 找到 PtrClassicFrameLayout mPtrFrame
     */
    protected abstract PtrClassicFrameLayout initParaentView();

    /**
     * 加载视图 代替了onCreateView方法,
     */
    protected abstract View initParentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 刷新数据源  停止刷新动画 mPtrFrame.refreshComplete();
     */
    protected abstract void onRefreshData(PtrFrameLayout frame);


}
