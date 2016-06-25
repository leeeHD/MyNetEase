package com.it520.activity.yxxbase;

import android.view.View;

/**
 * Created by Yu xiangxin on 2016/6/5 - 11:07.
 *
 * @desc ${TODD}
 */
public abstract class BaseHolder<T> {

    public interface onOtherClick {
        void OtherClick(Object obj);
    }

    public onOtherClick mOnOtherClick;

    public void setOnOtherClick(onOtherClick onOtherClick) {
        mOnOtherClick = onOtherClick;
    }

    public View mHolderView;


    public BaseHolder() {

        mHolderView = initHolderView();

        mHolderView.setTag(this);
    }

    //绑定数据 刷新
    public void setDateWithRefreshHolderView(int position, T data) {
        bindData(position, data);
    }

    public abstract View initHolderView();

    protected abstract void bindData(int position, T data);


}
