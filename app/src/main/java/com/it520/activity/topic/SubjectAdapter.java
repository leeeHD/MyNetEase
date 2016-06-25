package com.it520.activity.topic;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.topic.model.bean.SubjBean;
import com.it520.activity.topic.model.bean.SubjectItemBean;
import com.it520.activity.util.BitmapUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SubjectAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "SubjectAdapter";
    private LayoutInflater mInflater;
    DisplayImageOptions mOptions;
    private Context mContext;

    // ItemView Type
    private static final int TYPE_BAR = 0;
    private static final int TYPE_ITEM = 1;

    // data set
    private List<SubjBean> mSubjInfos;
    private List<SubjectItemBean> mSubjectItemInfos;

    public void addSubjInfos(List<SubjBean> subjInfos) {
        mSubjInfos.clear();
        mSubjInfos.addAll(subjInfos);
        notifyDataSetChanged();
    }

    public void addSubjItemInfos(List<SubjectItemBean> subjItemInfos) {
        mSubjectItemInfos.clear();
        Log.d(TAG, "SubjectItemInfos -- " + subjItemInfos);
        mSubjectItemInfos.addAll(subjItemInfos);
        notifyDataSetChanged();
    }


    // 加载更多
    public void updateSubjItemInfos(List<SubjectItemBean> subjItemInfos) {
        int start = mSubjectItemInfos.size();
        mSubjectItemInfos.addAll(subjItemInfos);
        notifyItemRangeChanged(start, mSubjectItemInfos.size());
    }

    public SubjectAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        mSubjInfos = new ArrayList<>();
        mSubjectItemInfos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View itemView;
        switch (viewType) {
            case TYPE_BAR:
                itemView = mInflater.inflate(R.layout.topic_item_bar_layout
                        , parent, false);
                holder = new SubjectBarViewHolder(itemView);
                break;
            case TYPE_ITEM:
                itemView = mInflater.inflate(R.layout.topic_item_layout
                        , parent, false);
                holder = new SubjectItemViewHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_BAR:
                Log.d(TAG, "onBindViewHolder -- type -- " + itemViewType);
                SubjectBarViewHolder barViewHolder = (SubjectBarViewHolder) holder;
                barViewHolder.setData();
                break;
            case TYPE_ITEM:
                Log.d(TAG, "onBindViewHolder -- type -- " + itemViewType);
                SubjectItemViewHolder itemViewHolder = (SubjectItemViewHolder) holder;
                itemViewHolder.setData(mSubjectItemInfos.get(position - 1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        int count = 0;
        count = mSubjectItemInfos.size();
        if (mSubjInfos.size() > 0) {
            count += 1;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BAR;
        }
        return TYPE_ITEM;
    }

    class SubjectBarViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView mRecyclerView;

        public SubjectBarViewHolder(View itemView) {
            super(itemView);

            // find view
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.subject_item_bar_rv);
        }

        // set bar recycler view data here
        public void setData() {
            mRecyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = mInflater.inflate(R.layout.topic_bar_rv_item, parent, false);
                    return new SubjectBarItemViewHolder(view);
                }

                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    Log.d(TAG, "bar bind view holder -- " + mSubjInfos);
                    if (mSubjInfos == null || mSubjInfos.size() == 0) {
                        return;
                    }
                    ((SubjectBarItemViewHolder) holder).setData(mSubjInfos.get(position));
                }

                @Override
                public int getItemCount() {
                    return 5;
                }
            });
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    class SubjectBarItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconIv;
        public TextView titleTv;

        public SubjectBarItemViewHolder(View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.subject_bar_item_iv);
            titleTv = (TextView) itemView.findViewById(R.id.subject_bar_item_tv);
        }

        public void setData(SubjBean data) {
            ImageLoader.getInstance().displayImage(data.getPicUrl(), iconIv, mOptions);
            // #话题#
            titleTv.setText("#" + data.getTopicName() + "#");
            Log.d(TAG, "#" + data.getTopicName() + "#");
        }
    }

    class SubjectItemViewHolder extends RecyclerView.ViewHolder {
        public TextView topic_item_type_tv;
        public TextView topic_item_title_tv;
        public TextView topic_item_concern_tv;
        public TextView topic_item_talk_tv;
        public TextView topic_comment_tv_1;
        public TextView topic_comment_tv_2;
        public ImageView topic_head_icon_1;
        public ImageView topic_head_icon_2;

        public LinearLayout topic_text_container;
        public LinearLayout topic_pic_container;

        public ImageView topic_haspic_iv_1;
        public ImageView topic_haspic_iv_2;
        public ImageView topic_haspic_iv_3;


        public SubjectItemViewHolder(View itemView) {
            super(itemView);
            topic_item_title_tv = (TextView) itemView.findViewById(R.id.topic_item_title_tv);
            topic_head_icon_1 = (ImageView) itemView.findViewById(R.id.topic_head_icon_1);
            topic_head_icon_2 = (ImageView) itemView.findViewById(R.id.topic_head_icon_2);
            topic_item_type_tv = (TextView) itemView.findViewById(R.id.topic_item_type_tv);
            topic_item_concern_tv = (TextView) itemView.findViewById(R.id.topic_item_attention_tv);
            topic_item_talk_tv = (TextView) itemView.findViewById(R.id.topic_item_reply_tv);
            topic_comment_tv_1 = (TextView) itemView.findViewById(R.id.topic_comment_tv_1);
            topic_comment_tv_2 = (TextView) itemView.findViewById(R.id.topic_comment_tv_2);

            topic_text_container = (LinearLayout) itemView.findViewById(R.id.topic_text_container);
            topic_pic_container = (LinearLayout) itemView.findViewById(R.id.topic_pic_container);

            topic_haspic_iv_1 = (ImageView) itemView.findViewById(R.id.topic_haspic_iv_1);
            topic_haspic_iv_2 = (ImageView) itemView.findViewById(R.id.topic_haspic_iv_2);
            topic_haspic_iv_3 = (ImageView) itemView.findViewById(R.id.topic_haspic_iv_3);
        }

        public void setData(SubjectItemBean data) {
            topic_item_type_tv.setText(data.getClassification());
            topic_item_title_tv.setText(data.getName());
            topic_item_concern_tv.setText(data.getConcernCount() + "关注");
            topic_item_talk_tv.setText(data.getTalkCount() + "讨论");

            if (data.getTalkContent() != null) {
                topic_text_container.setVisibility(View.VISIBLE);
                topic_pic_container.setVisibility(View.GONE);
                Log.d(TAG, data.getTalkContent().toString());
                SubjectItemBean.TalkContentBean talkContentBean1 = data.getTalkContent().get(0);
                SubjectItemBean.TalkContentBean talkContentBean2 = data.getTalkContent().get(1);
                topic_comment_tv_1.setText(talkContentBean1.getContent());
                topic_comment_tv_2.setText(talkContentBean2.getContent());

                String picUrl1 = talkContentBean1.getUserHeadPicUrl();
                String picUrl2 = talkContentBean2.getUserHeadPicUrl();
                displayHeadImage(picUrl1, topic_head_icon_1);
                displayHeadImage(picUrl2, topic_head_icon_2);
            } else if (data.getTalkPicture() != null && data.getTalkPicture().size() > 0) {
                topic_text_container.setVisibility(View.GONE);
                topic_pic_container.setVisibility(View.VISIBLE);

                displayImage(data.getTalkPicture().get(0), topic_haspic_iv_1);
                displayImage(data.getTalkPicture().get(1), topic_haspic_iv_2);
                displayImage(data.getTalkPicture().get(2), topic_haspic_iv_3);
            }
        }

        public void displayImage(String uri, ImageView iv) {
            ImageLoader.getInstance().displayImage(
                    uri, iv, mOptions);
        }

        public void displayHeadImage(final String uri, final ImageView iv) {
            Observable
                    .create(new Observable.OnSubscribe<Bitmap>() {
                        @Override
                        public void call(Subscriber<? super Bitmap> subscriber) {
                            Bitmap bitmap = BitmapUtil.getBitmap(uri);
                            Bitmap roundBitmap = BitmapUtil.getRoundBitmap(bitmap);
                            subscriber.onNext(roundBitmap);
                            subscriber.onCompleted();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Bitmap bitmap) {
                            iv.setImageBitmap(bitmap);
                        }
                    });
        }
    }
}
