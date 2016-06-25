package com.it520.activity.news.scienci.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.news.scienci.bean.Science;
import com.it520.activity.util.ImageUtile;
import com.it520.activity.util.UIUtils;
import com.it520.activity.yxxbase.BaseHolder;

/**
 * Created by Yu xiangxin on 2016/6/19 - 19:53.
 *
 * @desc ${TODD}
 */
public class ScienceHolder extends BaseHolder<Science.ScienceItem> {
    private ImageView img;
    private TextView newsTitle;
    private TextView newsFrom;
    private TextView specialTopic;
    private TextView numbers;

    @Override
    public View initHolderView() {
        return LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.news_detail_item_layout, null);
    }

    @Override
    protected void bindData(int position, Science.ScienceItem data) {
        initView();
        ImageUtile.LoadImage(data.getImgsrc(), img);
        newsTitle.setText(data.getTitle());
        newsFrom.setText(data.getSource());
        numbers.setText(data.getReplyCount() + "跟帖");
    }


    private void initView() {
        img = (ImageView) mHolderView.findViewById(R.id.img);
        newsTitle = (TextView) mHolderView.findViewById(R.id.news_title);
        newsFrom = (TextView) mHolderView.findViewById(R.id.news_from);
        specialTopic = (TextView) mHolderView.findViewById(R.id.special_topic);
        numbers = (TextView) mHolderView.findViewById(R.id.numbers);
    }

}
