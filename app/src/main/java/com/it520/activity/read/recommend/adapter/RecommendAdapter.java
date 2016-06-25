package com.it520.activity.read.recommend.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.read.recommend.bean.RecommendDetail;
import com.it520.activity.read.recommend.popuwin.ChooseDislikePopupWindow;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @author moy
 * @time 2016/6/20  17:06
 * @desc 推荐的适配器
 */

public class RecommendAdapter extends BaseAdapter {

    private final Context mContext;
    private final DisplayImageOptions options;
    private final ChooseDislikePopupWindow mPopWindow;
    private List<RecommendDetail> mDatas;
    private String docid;//详情id
    private RecommendDetail mEntity;
    private int mPosition;

    public static final int LOADHEAD = 1;
    public static final int LOADEND = 2;
    public RecommendAdapter(Context context) {
        mContext = context;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mPopWindow = new ChooseDislikePopupWindow(mContext);

    }

    public void setDatas(List<RecommendDetail> datas,int where) {
        if(mDatas==null) {
        mDatas = datas;
        }else {
            if(where==LOADHEAD) {
            mDatas.addAll(0,datas);
            }else {
            mDatas.addAll(datas);
            }
        }

    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
         mPosition =  position;
        int type = getItemViewType(position);

        if (convertView == null) {
            if (type == 1) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.read_detail_mults_item_layout, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.img2 = (ImageView) convertView.findViewById(R.id.img2);
                holder.img3 = (ImageView) convertView.findViewById(R.id.img3);
                holder.recReason = (TextView) convertView.findViewById(R.id.read_local);
                holder.title = (TextView) convertView.findViewById(R.id.read_title);
                holder.source = (TextView) convertView.findViewById(R.id.read_resouse);
                holder.read_detele = (ImageView) convertView.findViewById(R.id.read_delete);
                holder.read_local_ll = (LinearLayout) convertView.findViewById(R.id.read_local_ll);

                convertView.setTag(holder);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.read_detail_item_layout, null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.recReason = (TextView) convertView.findViewById(R.id.read_local);
                holder.title = (TextView) convertView.findViewById(R.id.read_title);
                holder.source = (TextView) convertView.findViewById(R.id.read_resouse);
                holder.read_detele = (ImageView) convertView.findViewById(R.id.read_delete);
                holder.read_local_ll = (LinearLayout) convertView.findViewById(R.id.read_local_ll);

                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        mEntity = getItem(position);
        if (mEntity != null) {
            if(type==1) {
            ImageLoader.getInstance().displayImage(mEntity.getImgnewextra().get(0).getImgsrc(), holder.img2, options);
            ImageLoader.getInstance().displayImage(mEntity.getImgnewextra().get(1).getImgsrc(), holder.img3, options);
            }
            ImageLoader.getInstance().displayImage(mEntity.getImg(), holder.img, options);
            holder.read_local_ll.setVisibility(View.GONE);
            holder.title.setText(mEntity.getTitle());
            holder.source.setText(mEntity.getSource());
            if (mEntity.isLocal()) {
                holder.read_local_ll.setVisibility(View.VISIBLE);
                holder.recReason.setText(mEntity.getRecReason());
            }
            holder.read_detele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int [] location=new int[2];
                    view.getLocationInWindow(location);

                    RecommendDetail item = getItem(mPosition==0?0:mPosition-1);
                    mPopWindow.show(item,view,20,location[1]);
                }
            });
        }
        return convertView;
    }

    @Override
    public RecommendDetail getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position).isMulti()) {
            return 1;
        }
        return 0;
    }

    class ViewHolder {
        public TextView recReason;
        public TextView title;
        public ImageView img;
        public TextView source;

        public ImageView read_detele;
        public ImageView img2;
        public ImageView img3;
        public LinearLayout read_local_ll;



    }

}
