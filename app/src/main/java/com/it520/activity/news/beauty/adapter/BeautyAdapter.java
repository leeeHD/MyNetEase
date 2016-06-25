package com.it520.activity.news.beauty.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.news.beauty.bean.Beauty;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageCache;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import cn.trinea.android.common.util.ObjectUtils;

/**
 * CEO:mac on 2016/6/19 14:57.
 * FUNCTION:
 */
public class BeautyAdapter extends BaseAdapter {
    private List<Beauty> mBeauties;
    private Context mContext;
    private DisplayImageOptions option;
    public BeautyAdapter(List<Beauty> beauties, Context context) {
        mBeauties = beauties;
        mContext = context;
        option = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
    }
    public int getCount() {
        return mBeauties.size();
    }
    public Object getItem(int position) {
        return mBeauties.get(position);
    }
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
     if(convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_beauty,null);
            holder.image = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.supportCount = (TextView) convertView.findViewById(R.id.support);
            holder.opposeCount = (TextView) convertView.findViewById(R.id.oppose);
            holder.replyCount = (TextView) convertView.findViewById(R.id.reply);
            holder.supportIcon = (ImageView) convertView.findViewById(R.id.up);
            holder.opposeIcon = (ImageView) convertView.findViewById(R.id.down);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
         holder.supportIcon.setSelected(false);
         holder.opposeIcon.setSelected(false);
        }
        final Beauty beauty = mBeauties.get(position);
        holder.image.setTag(beauty.getImgsrc());
        ImageCache cache = new ImageCache();
        if(cache.get(beauty.getImgsrc(),holder.image)) {
            holder.image.setImageDrawable(null);
        }

        new ImageMemoryCache.OnImageCallbackListener() {
            public void onPreGet(String imageUrl, View view) {

            }
            public void onGetNotInCache(String imageUrl, View view) {

            }
            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                if(view!=null&&loadedImage!=null) {
                        holder.image = (ImageView) view;
                    String imgUrl = (String) holder.image.getTag();
                    if(ObjectUtils.isEquals(imgUrl,imageUrl)) {
                        holder.image.setImageBitmap(loadedImage);
                    }
                }
            }
            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {

            }
        };
        initView(beauty,holder);
            holder.supportIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(holder.opposeIcon.isSelected()) {
                        holder.supportCount.setText(beauty.getUpTimes()+1+"");
                        holder.opposeCount.setText(beauty.getDownTimes()+"");
                        holder.supportIcon.setSelected(true);
                        holder.opposeIcon.setSelected(false);
                    }else{
                        holder.supportCount.setText(beauty.getUpTimes()+1+"");
                        holder.supportIcon.setSelected(true);
                    }
                }
            });
           holder.opposeIcon.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   if(holder.supportIcon.isSelected()) {
                       holder.opposeCount.setText(beauty.getDownTimes()+1+"");
                       holder.supportCount.setText(beauty.getUpTimes()+"");
                       holder.opposeIcon.setSelected(true);
                       holder.supportIcon.setSelected(false);
                   }else{
                       holder.opposeCount.setText(beauty.getDownTimes()+1+"");
                       holder.opposeIcon.setSelected(true);
                   }

               }
           });
        return convertView;
    }
    //填充数据
    private void initView(Beauty beauty, ViewHolder holder) {
       holder.title.setText(beauty.getTitle());
        holder.replyCount.setText(beauty.getReplyCount()+"");
        holder.supportCount.setText(beauty.getUpTimes()+"");
        holder.opposeCount.setText(beauty.getDownTimes()+"");
        ImageLoader.getInstance().displayImage(beauty.getImgsrc(),holder.image,option);
    }
    class ViewHolder{
        ImageView image;
        TextView title;
        TextView replyCount;
        TextView supportCount;
        TextView opposeCount;
        ImageView supportIcon;
        ImageView opposeIcon;
    }
}
