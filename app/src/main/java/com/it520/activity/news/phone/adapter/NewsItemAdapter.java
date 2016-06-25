package com.it520.activity.news.phone.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.it520.activity.R;
import com.it520.activity.news.phone.bean.PhoneDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by jackytan on 2016/5/16.
 */
public class NewsItemAdapter extends BaseNewsAdapter<PhoneDetail> {

    private Context context = null;
    private DisplayImageOptions options;

    public NewsItemAdapter(Context context){
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
    public PhoneDetail getItem(int position) {
       return mDatas.get(position);
    }
    //item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_detail_item_layout, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.news_title= (TextView) convertView.findViewById(R.id.news_title);
            holder.news_from= (TextView) convertView.findViewById(R.id.news_from);
            holder.numbers = (TextView) convertView.findViewById(R.id.numbers);
            //holder.special_topic = (TextView) convertView.findViewById(R.id.special_topic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setFocusable(false);

        PhoneDetail entity = getItem(position);
        //item显示 图片,标题,跟帖
        if (entity != null) {
            ImageLoader.getInstance().displayImage(entity.getImgsrc(), holder.img, options);
            holder.news_title.setText(entity.getTitle());
            holder.news_from.setText(entity.getSource());
                holder.numbers.setVisibility(View.VISIBLE);
                holder.numbers.setText(entity.getReplyCount() + "跟帖");
        }
        return convertView;
    }

    public void updateList(List<PhoneDetail> newData){
        mDatas.addAll(newData);
        notifyDataSetChanged();
    }

    class ViewHolder {
        public ImageView img;
        public TextView news_title;
        public TextView news_from;
        public TextView numbers;
        //public TextView special_topic;
    }
}
