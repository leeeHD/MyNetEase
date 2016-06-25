package com.it520.activity.yxxbase;

import android.view.LayoutInflater;
import android.view.View;

import com.it520.activity.R;
import com.it520.activity.util.UIUtils;

/**
 * Created by Yu xiangxin on 2016/6/19 - 22:04.
 *
 * @desc ${TODD}
 */
public class LoadMoreHolder extends BaseHolder {
    @Override
    public View initHolderView() {
        return LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_load_more, null);
    }

    @Override
    protected void bindData(int position, Object data) {

    }
}
