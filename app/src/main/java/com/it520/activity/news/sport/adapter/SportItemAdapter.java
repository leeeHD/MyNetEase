package com.it520.activity.news.sport.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.news.adapter.BaseNewsAdapter;
import com.it520.activity.news.sport.bean.SportDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by jackytan on 2016/5/16.
 */
public class SportItemAdapter extends BaseNewsAdapter<SportDetail> {

    private Context context = null;
    private DisplayImageOptions options;

    public SportItemAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public SportDetail getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);

        if (convertView == null) {
            if (type == 1) {
                convertView = LayoutInflater.from(context).inflate(R.layout.news_detail_item_more_layout, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.img2 = (ImageView) convertView.findViewById(R.id.img2);
                holder.img3 = (ImageView) convertView.findViewById(R.id.img3);
                holder.news_title = (TextView) convertView.findViewById(R.id.news_title);
                holder.news_from = (TextView) convertView.findViewById(R.id.news_from);
                holder.numbers = (TextView) convertView.findViewById(R.id.numbers);
                holder.special_topic = (TextView) convertView.findViewById(R.id.special_topic);
                convertView.setTag(holder);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.news_detail_item_layout, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.news_title = (TextView) convertView.findViewById(R.id.news_title);
                holder.news_from = (TextView) convertView.findViewById(R.id.news_from);
                holder.numbers = (TextView) convertView.findViewById(R.id.numbers);
                holder.special_topic = (TextView) convertView.findViewById(R.id.special_topic);
                convertView.setTag(holder);
            }


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        if (type == 1) {
            holder.img2.setFocusable(false);
            holder.img3.setFocusable(false);

        } else {
            holder.img.setFocusable(false);
        }

        SportDetail entity = getItem(position);
        if (entity != null) {
            if (type == 1) {
                ImageLoader.getInstance().displayImage(entity.getImgsrc(), holder.img, options);
                ImageLoader.getInstance().displayImage(entity.getImgextra().get(0).getImgsrc(), holder.img2, options);
                ImageLoader.getInstance().displayImage(entity.getImgextra().get(1).getImgsrc(), holder.img3, options);

            } else {
                ImageLoader.getInstance().displayImage(entity.getImgsrc(), holder.img, options);
            }
            holder.news_title.setText(entity.getTitle());
            holder.news_from.setText(entity.getSource());
            if (TextUtils.isEmpty(entity.getSpecialID())) {
                holder.numbers.setVisibility(View.VISIBLE);
                holder.numbers.setText(entity.getReplyCount() + "跟帖");
                holder.special_topic.setVisibility(View.GONE);
            } else {
                holder.numbers.setVisibility(View.GONE);
                holder.special_topic.setVisibility(View.VISIBLE);
            }

        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas.get(position).isMulti()) {
            return 1;
        }
        return 0;
    }

    public void updateList(List<SportDetail> newData) {
        mDatas.addAll(newData);
        notifyDataSetChanged();
    }

    class ViewHolder {
        public ImageView img;
        public ImageView img2;
        public ImageView img3;
        public TextView news_title;
        public TextView news_from;
        public TextView numbers;
        public TextView special_topic;
    }

}
