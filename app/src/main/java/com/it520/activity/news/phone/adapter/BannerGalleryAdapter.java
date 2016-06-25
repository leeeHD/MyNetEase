package com.it520.activity.news.phone.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.it520.activity.R;
import com.it520.activity.news.phone.bean.LYBAds;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @author jackytan
 */
public class BannerGalleryAdapter extends BaseNewsAdapter<LYBAds> {

    private Context context = null;
    private Drawable mDftDrawable = null;
    private LayoutInflater inflater;
    private DisplayImageOptions options;

    public BannerGalleryAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        //return Integer.MAX_VALUE;
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public LYBAds getItem(int position) {
        if (mDatas.size() > 0) {
            int selIndex = position % mDatas.size();
            return super.getItem(selIndex);
        }
        return null;
    }

    // TODO: 2016/6/20/020 修改url地址
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_banner_gallery_item, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setFocusable(false);

        LYBAds entity = getItem(position);
        if (entity != null) {
            ImageLoader.getInstance().displayImage(entity.getImgUrl(), holder.img, options);
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
    }

    public void updateBannerGallery(List<LYBAds> liveBannerList) {
        if (liveBannerList != null) {
            this.setData(liveBannerList);
        }
        this.notifyDataSetChanged();
    }

}
