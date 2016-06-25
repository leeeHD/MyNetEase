package com.it520.activity.yxxbase;

import android.view.View;
import android.view.ViewGroup;

import com.it520.activity.news.adapter.BaseNewsAdapter;

import java.util.List;

/**
 * Created by Yu xiangxin on 2016/6/5 - 11:03.
 *
 * @desc ${TODD}
 */
public abstract class SuperAdapter<T> extends BaseNewsAdapter<T> {

    public interface onLoadMoreData {
        void loadMoreData(int position);
    }

    public onLoadMoreData mOnLoadMoreData;

    public void setOnLoadMoreData(onLoadMoreData monLoadMoreData) {
        this.mOnLoadMoreData = monLoadMoreData;
    }

    private static final String TAG = "SuperAdapter";

    public SuperAdapter(List<T> datas) {
        super(datas);
    }


    public void reSetDataWithRefresh(List<T> datas) {
        super.setData(datas);
        this.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView == null) {
            holder = getSpaceHolder(position);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.setDateWithRefreshHolderView(position, mDatas.get(position));

        if (mOnLoadMoreData != null ) {
            mOnLoadMoreData.loadMoreData(position);
        }

        return holder.mHolderView;
    }

    protected abstract BaseHolder getSpaceHolder(int position);

}
