package com.it520.activity.video.gossip.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.it520.activity.R;
import com.it520.activity.util.ImageUtile;
import com.it520.activity.video.gossip.bean.Gossip;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * CEO:mac on 2016/6/21 12:47.
 * FUNCTION:
 */
public class GossipDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Gossip mGossip;
    private List<Gossip> mDatas;
    private enum ITEM_TYPE {
        ITEM_TYPE_SPECIAL, ITEM_TYPE_NORMAL
    }
    public GossipDetailAdapter(Context context, Gossip gossip, List<Gossip> recommend) {
        mContext = context;
        mGossip = gossip;
        mDatas = recommend;
    }
    //创建界面
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_SPECIAL.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gossip_detail_special_item, parent, false);
            return new GossipDetailSpecialHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gossip_detail_normal_item, parent, false);
            return new GossipDetailNormalHolder(view);
        }
        return null;
    }

    //绑定数据
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //点击进入后的第一个视频条目
        if (holder instanceof GossipDetailSpecialHolder) {
            final GossipDetailSpecialHolder specialHolder = (GossipDetailSpecialHolder) holder;
            specialHolder.replyCount.setText(mGossip.getReplyCount() + "");
            specialHolder.title.setText(mGossip.getTitle());
            specialHolder.topicName.setText(mGossip.getTopicName());
            ImageUtile.LoadImage(mGossip.getTopicImg(), specialHolder.topicImg);
            ImageUtile.LoadImage(mGossip.getCover(), specialHolder.icon);
            //进入之前先让圆形进度条消失
            specialHolder.pb.setVisibility(View.GONE);
            specialHolder.ll.setVisibility(View.GONE);
            specialHolder.pause.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    specialHolder.pb.setVisibility(View.VISIBLE);
                    specialHolder.rl.setVisibility(View.GONE);
                    initSpecialPlaying(specialHolder, specialHolder.vv, mGossip);
                }
            });
            if (!specialHolder.vv.isPlaying()) {
                System.out.println("停止播放---------->");
                specialHolder.rl.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof GossipDetailNormalHolder) {
            final GossipDetailNormalHolder normalHolder = (GossipDetailNormalHolder) holder;
            final Gossip gossip = mDatas.get(position - 1);
            normalHolder.replyCount.setText(gossip.getReplyCount() + "");
            normalHolder.title.setText(gossip.getTitle());
            ImageUtile.LoadImage(gossip.getCover(), normalHolder.icon);
            normalHolder.pb.setVisibility(View.GONE);
           /* normalHolder.icon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    normalHolder.pb.setVisibility(View.VISIBLE);
                    normalHolder.rl.setVisibility(View.GONE);
                    initNormalPlaying(normalHolder,normalHolder.vv,gossip);
                }
            });*/
            normalHolder.pause.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    normalHolder.pb.setVisibility(View.VISIBLE);
                    normalHolder.rl.setVisibility(View.GONE);
                    initNormalPlaying(normalHolder, normalHolder.vv, gossip);
                }
            });
            if (!normalHolder.vv.isPlaying()) {
                System.out.println("停止播放---------->");
                normalHolder.rl.setVisibility(View.VISIBLE);
            }
        }
    }
    //播放监听
    private void initSpecialPlaying(final GossipDetailSpecialHolder holder, final VideoView vv, Gossip gossip) {
        vv.setVideoURI(Uri.parse(gossip.getMp4_url()));
        vv.requestFocus();
        vv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        holder.ll.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        new Timer().schedule(new TimerTask() {
                            public void run() {
                                ((Activity) mContext).runOnUiThread(new Runnable() {
                                    public void run() {
                                        holder.ll.setVisibility(View.GONE);
                                    }
                                });
                            }
                        }, 3000);
                        break;
                }
                return true;
            }
        });
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                holder.pb.setVisibility(View.GONE);
                //获取总时间
                final int duration = vv.getDuration();


                final SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                String totalTime = format.format(new Date(duration));

                TextView videoTotalTime = (TextView) holder.ll.findViewById(R.id.videoTotalTime);
                final TextView videoCurrentTime = (TextView) holder.ll.findViewById(R.id.videoCurTime);
                videoTotalTime.setText(totalTime);
                final SeekBar seekBar = (SeekBar) holder.ll.findViewById(R.id.videoSeekBar);
                seekBar.setMax(duration);
                final ImageView pauseImg = (ImageView) holder.ll.findViewById(R.id.videoPauseImg);
                final ImageView playImg = (ImageView) holder.ll.findViewById(R.id.videoPlayImg);
                pauseImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        vv.pause();
                        pauseImg.setVisibility(View.INVISIBLE);
                        playImg.setVisibility(View.VISIBLE);
                    }
                });
                playImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        vv.start();
                        pauseImg.setVisibility(View.VISIBLE);
                        playImg.setVisibility(View.INVISIBLE);
                    }
                });
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        int currentPosition = vv.getCurrentPosition();
                        String currentTime = format.format(new Date(currentPosition));
                        int time = ((currentPosition * 100) / duration);
                        System.out.println("当前播放进度是"+currentPosition+"---->"+time);
                        System.out.println("总长度"+duration);
                        //seekBar.setEnabled(true);
                        seekBar.setProgress(currentPosition);
                        videoCurrentTime.setText(currentTime);
                        seekBar.setSecondaryProgress(percent);
                    }
                });
            }
        });
    }
    private void initNormalPlaying(final GossipDetailNormalHolder holder, VideoView vv, Gossip gossip) {
        vv.setVideoURI(Uri.parse(gossip.getMp4_url()));
        vv.requestFocus();
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                holder.pb.setVisibility(View.GONE);
            }
        });
    }

    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        } else {
            return 1;
        }
    }

    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_SPECIAL.ordinal() : ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal();
    }

    public class GossipDetailSpecialHolder extends RecyclerView.ViewHolder {
        VideoView vv;
        TextView title;
        TextView topicName;
        ImageView topicImg;
        TextView replyCount;
        ProgressBar pb;
        ImageView icon;
        ImageView pause;
        RelativeLayout rl;
        LinearLayout ll;

        public GossipDetailSpecialHolder(View itemView) {
            super(itemView);
            vv = (VideoView) itemView.findViewById(R.id.gossip_detail_vv);
            title = (TextView) itemView.findViewById(R.id.gossip_detail_title);
            topicName = (TextView) itemView.findViewById(R.id.gossip_detail_topic_name);
            topicImg = (ImageView) itemView.findViewById(R.id.gossip_detail_topic_img);
            replyCount = (TextView) itemView.findViewById(R.id.gossip_detail_reply_count);
            pb = (ProgressBar) itemView.findViewById(R.id.gossip_detail_pb);
            rl = (RelativeLayout) itemView.findViewById(R.id.gossip_detail_special_rl);
            icon = (ImageView) itemView.findViewById(R.id.item_gossip_special_icon);
            pause = (ImageView) itemView.findViewById(R.id.item_gossip_special_pause);
            ll = (LinearLayout) itemView.findViewById(R.id.gossip_detail_special_control);
        }
    }

    public class GossipDetailNormalHolder extends RecyclerView.ViewHolder {
        VideoView vv;
        TextView title;
        TextView replyCount;
        ProgressBar pb;
        ImageView icon;
        ImageView pause;
        RelativeLayout rl;

        public GossipDetailNormalHolder(View itemView) {
            super(itemView);
            vv = (VideoView) itemView.findViewById(R.id.gossip_detail_vv);
            title = (TextView) itemView.findViewById(R.id.gossip_detail_title);
            replyCount = (TextView) itemView.findViewById(R.id.gossip_detail_reply_count);
            pb = (ProgressBar) itemView.findViewById(R.id.gossip_detail_pb);
            rl = (RelativeLayout) itemView.findViewById(R.id.gossip_detail_normal_rl);
            icon = (ImageView) itemView.findViewById(R.id.item_gossip_normal_icon);
            pause = (ImageView) itemView.findViewById(R.id.item_gossip_normal_pause);
        }
    }
}
