package com.it520.activity.news.city.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.news.city.bean.ItemInfo;
import com.it520.activity.news.city.bean.WhetherInfo;
import com.it520.activity.news.city.util.BitmapUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class CityNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> /*implements View.OnClickListener*/ {
    private static final String TAG = "CityNewsAdapter";
    public List<ItemInfo> mItemInfos;

    private LayoutInflater mInflater;
    DisplayImageOptions mOptions;
    private Context mContext;

    private static final int TYPE_VP = 0;// 头条栏
    private static final int TYPE_ITEM = 1;// 一般item
    private static final int TYPE_LONG = 2;// 长条item
    private static final int TYPE_LIVE = 3;// 直播item
    private static final int TYPE_SPECIAL = 4;// 专题item
    private static final int TYPE_MULTI_IMAGE = 5;// 多张图片item
    public WhetherInfo mWhetherData;
    public boolean isWhetherPageShowing = false;

    public CityNewsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        mItemInfos = new ArrayList<>();
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    // 加入itemInfos
    public void addItemInfos(List<ItemInfo> itemInfos) {
        mItemInfos.clear();
        mItemInfos.addAll(itemInfos);
        notifyDataSetChanged();
    }

    // 刷新加入到尾部
    public void addToLast(List<ItemInfo> itemInfos) {
        int start = mItemInfos.size();
        mItemInfos.addAll(start, itemInfos);
        // 只更新后面的数据
        notifyItemRangeChanged(start, mItemInfos.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View itemView;
        switch (viewType) {
            case TYPE_VP:
//                itemView = mInflater.inflate(R.layout.city_item_vp, parent, false);
//                viewHolder = new CityNewsVpViewHolder(itemView);
                Log.d(TAG, "TYPE_VP");
                itemView = mInflater.inflate(R.layout.city_item_gallery_layout, parent, false);
                viewHolder = new CityNewsGalleryViewHolder(itemView);
                break;
            case TYPE_ITEM:
                itemView = mInflater.inflate(R.layout.city_item_layout, parent, false);
                viewHolder = new CityNewsItemViewHolder(itemView);
                break;
            case TYPE_LONG:
                itemView = mInflater.inflate(R.layout.city_item_long_layout, parent, false);
                viewHolder = new CityNewsLongViewHolder(itemView);
                break;
            case TYPE_MULTI_IMAGE:
                itemView = mInflater.inflate(R.layout.city_item_multi_layout, parent, false);
                viewHolder = new CityNewsMultiViewHolder(itemView);
                break;
            case TYPE_LIVE:
                itemView = mInflater.inflate(R.layout.city_item_long_layout, parent, false);
                viewHolder = new CityNewsLongViewHolder(itemView);
                break;
            case TYPE_SPECIAL:
                itemView = mInflater.inflate(R.layout.city_item_layout, parent, false);
                viewHolder = new CityNewsItemViewHolder(itemView);
                break;
            default:
                itemView = mInflater.inflate(R.layout.city_item_layout, parent, false);
                viewHolder = new CityNewsItemViewHolder(itemView);
                break;
        }
//        itemView.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "position -> " + position);
        // 广告栏位
        if(position == 0 && mItemInfos.size() > 0) {
            // set gallery
            //mCurrentDocid = mItemInfos
        }
        // 其他item view
        else if(position > 0) {
            int type = getItemViewType(position);
            ItemInfo itemInfo = mItemInfos.get(position);
            switch (type) {
                case TYPE_ITEM:
                    CityNewsItemViewHolder itemViewHolder =(CityNewsItemViewHolder) holder;
                    itemViewHolder.setData(itemInfo);
                    break;
                case TYPE_MULTI_IMAGE:
                    CityNewsMultiViewHolder multiViewHolder =(CityNewsMultiViewHolder) holder;
                    multiViewHolder.setData(itemInfo);
                    break;
                case TYPE_LONG:
                    CityNewsLongViewHolder longViewHolder =(CityNewsLongViewHolder) holder;
                    longViewHolder.setData(itemInfo);
                    break;
                case TYPE_SPECIAL:
                    CityNewsItemViewHolder specialViewHolder =(CityNewsItemViewHolder) holder;
                    specialViewHolder.setData(itemInfo);
                    specialViewHolder.news_city_reply_count.setText("专题");
                    specialViewHolder.news_city_reply_count.setTextColor(Color.RED);
                    break;
                case TYPE_LIVE:
                    CityNewsLongViewHolder liveViewHolder =(CityNewsLongViewHolder) holder;
                    liveViewHolder.setData(itemInfo);
                    liveViewHolder.news_city_type.setText("直播");
                    liveViewHolder.news_city_type.setTextColor(Color.RED);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = mItemInfos.size();
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        ItemInfo itemInfo = mItemInfos.get(position);
        // 返回不同的item view类型
        if(position == 0) {
            return TYPE_VP;
        }
        if(!TextUtils.isEmpty(itemInfo.getTAG())) {
            return TYPE_LIVE;
        }
        if(itemInfo.getSkipType() != null && itemInfo.getSkipType().equals("special")) {
            return TYPE_SPECIAL;
        }
        if(itemInfo.getEditor() != null) {
            return TYPE_LONG;
        }
        if(itemInfo.getImgextra() != null && itemInfo.getImgextra().size() > 0) {
            return TYPE_MULTI_IMAGE;
        }
        if(itemInfo.getImgextra() != null && itemInfo.getImgextra().size() > 0) {
            return TYPE_MULTI_IMAGE;
        }
        return TYPE_ITEM;
    }

    public void setWhetherData(WhetherInfo whetherData) {
        this.mWhetherData = whetherData;
    }

    class CityNewsGalleryViewHolder extends RecyclerView.ViewHolder{
        public ViewPager viewPager;
        public CityNewsGalleryViewHolder(View itemView) {
            super(itemView);
            // find view
            viewPager = (ViewPager) itemView.findViewById(R.id.city_view_pager);
            viewPager.setAdapter(new GalleryAdapter());
        }
    }
    class GalleryAdapter extends PagerAdapter {
        private final static int GALLERY_ITEM_COUNT = 2;
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % GALLERY_ITEM_COUNT;
            View itemView = getView(container, index);
            isWhetherPageShowing = (index == 0);
            Log.d(TAG, "isWhetherPageShowing " + isWhetherPageShowing);
            container.addView(itemView);
            return itemView;
        }

        private View getView(ViewGroup container, int index) {
            View itemView;
            if(index == 0) {
                                itemView = mInflater.inflate(R.layout.city_item_vp, container, false);
                CityNewsVpViewHolder vpViewHolder = new CityNewsVpViewHolder(itemView);
                vpViewHolder.setData(mItemInfos.get(0));
            } else {

                itemView = mInflater.inflate(R.layout.city_item_whether_layout, container, false);
                WhetherInfo.DayInfo dayInfo = mWhetherData.getDayInfos().get(0);
                ((TextView)itemView.findViewById(R.id.city_whether_temp_tv)).setText(dayInfo.getTemperature());
                ((TextView)itemView.findViewById(R.id.whether_date)).setText(dayInfo.getDate());
                ((TextView)itemView.findViewById(R.id.whether_climate)).setText(dayInfo.getClimate());
                ((TextView)itemView.findViewById(R.id.whether_wind)).setText(dayInfo.getWind());
                ((TextView)itemView.findViewById(R.id.whether_pm2d5)).setText(mWhetherData.getPm2d5().getPm2_5());
                ((TextView)itemView.findViewById(R.id.whether_quality)).setText("良");
                ((TextView)itemView.findViewById(R.id.city_whether_title)).setText("北京新闻");

                ImageView whetherIv = (ImageView) itemView.findViewById(R.id.city_whether_iv);
                ImageLoader.getInstance().displayImage(mWhetherData.getPm2d5().getNbg1(), whetherIv, mOptions);
            }
//            itemView.setOnClickListener(CityNewsAdapter.this);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(getView(container, position % GALLERY_ITEM_COUNT));
        }
    }


    class CityNewsItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView news_city_img;
        public TextView news_city_title;
        public TextView news_city_from;
        public TextView news_city_reply_count;

        public CityNewsItemViewHolder(View itemView) {
            super(itemView);
            news_city_img = (ImageView) itemView.findViewById(R.id.news_city_img);
            news_city_title = (TextView) itemView.findViewById(R.id.news_city_title);
            news_city_from = (TextView) itemView.findViewById(R.id.news_city_from);
            news_city_reply_count = (TextView) itemView.findViewById(R.id.news_city_reply_count);
        }

        public void setData(ItemInfo itemInfo) {
            news_city_title.setText(itemInfo.getTitle());
            news_city_from.setText(itemInfo.getSource());
            String text = itemInfo.getReplyCount() == 0 ? "" :
                    itemInfo.getReplyCount() + "跟帖";
            news_city_reply_count.setText(text);

            ImageLoader.getInstance().displayImage(itemInfo.getImgsrc(),
                    news_city_img, mOptions);
        }
    }

    class CityNewsVpViewHolder extends RecyclerView.ViewHolder {
        public ImageView cityVpIv;
        public TextView cityVpTv;

        public CityNewsVpViewHolder(View itemView) {
            super(itemView);
            cityVpIv = (ImageView) itemView.findViewById(R.id.city_vp_iv);
            cityVpTv = (TextView) itemView.findViewById(R.id.city_vp_title);
        }

        public void setData(ItemInfo itemInfo) {
            cityVpTv.setText(itemInfo.getTitle());

            List<ItemInfo.ImgextraBean> imgextra = itemInfo.getImgextra();
            if(imgextra != null && imgextra.size() > 1) {
                BitmapUtil.overlay(imgextra, cityVpIv);
            }
            else {
                String imgsrc = itemInfo.getImgsrc();
                ImageLoader.getInstance().displayImage(imgsrc, cityVpIv, mOptions);
            }
        }
    }

    class CityNewsLongViewHolder extends RecyclerView.ViewHolder {
        public ImageView cityBarIv;
        public TextView news_city_title;
        public TextView news_city_from;
        public TextView news_city_type;

        public CityNewsLongViewHolder(View itemView) {
            super(itemView);
            cityBarIv = (ImageView) itemView.findViewById(R.id.news_city_long_img);
            news_city_title = (TextView) itemView.findViewById(R.id.news_city_long_title_tv);
            news_city_from = (TextView) itemView.findViewById(R.id.news_city_long_from);
            news_city_type = (TextView) itemView.findViewById(R.id.news_city_long_type);
        }

        public void setData(ItemInfo itemInfo) {
            news_city_title.setText(itemInfo.getTitle());
            news_city_from.setText(itemInfo.getSource());
            news_city_type.setText(itemInfo.getReplyCount() + "跟帖");

            ImageLoader.getInstance().displayImage(itemInfo.getImgsrc(),
                    cityBarIv, mOptions);
        }
    }

    class CityNewsMultiViewHolder extends RecyclerView.ViewHolder {
        public ImageView cityIv1;
        public ImageView cityIv2;
        public ImageView cityIv3;
        public TextView news_city_title;
        public TextView news_city_from;
        public TextView news_city_type;

        public CityNewsMultiViewHolder(View itemView) {
            super(itemView);
            cityIv3 = (ImageView) itemView.findViewById(R.id.news_city_multi_img);
            cityIv1 = (ImageView) itemView.findViewById(R.id.news_city_multi_img_1);
            cityIv2 = (ImageView) itemView.findViewById(R.id.news_city_multi_img_2);
            news_city_title = (TextView) itemView.findViewById(R.id.news_city_multi_title_tv);
            news_city_from = (TextView) itemView.findViewById(R.id.news_city_multi_from);
            news_city_type = (TextView) itemView.findViewById(R.id.news_city_multi_type);
        }

        public void setData(ItemInfo itemInfo) {
            news_city_title.setText(itemInfo.getTitle());
            news_city_from.setText(itemInfo.getSource());
            news_city_type.setText(itemInfo.getReplyCount() + "跟帖");

            ImageLoader.getInstance().displayImage(itemInfo.getImgsrc(),
                    cityIv1, mOptions);
            ImageLoader.getInstance().displayImage(itemInfo.getImgextra().get(0).getImgsrc(),
                    cityIv2, mOptions);
            ImageLoader.getInstance().displayImage(itemInfo.getImgextra().get(1).getImgsrc(),
                    cityIv3, mOptions);
        }
    }
}
