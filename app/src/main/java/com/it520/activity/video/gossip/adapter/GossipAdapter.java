package com.it520.activity.video.gossip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.util.ImageUtile;
import com.it520.activity.video.gossip.bean.Gossip;
import com.it520.activity.video.gossip.listener.ItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CEO:mac on 2016/6/20 17:16.
 * FUNCTION:
 */
public class GossipAdapter extends RecyclerView.Adapter<GossipAdapter.GossipHolder> {
    private List<Gossip> mDatas;
    private Context mContext;
    private ItemClickListener mListener;
    public void setListener(ItemClickListener listener) {
        mListener = listener;
    }
    public GossipAdapter(List<Gossip> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }
    //创建界面
    public GossipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gossip, parent, false);
        return new GossipHolder(view);
    }
    public void onBindViewHolder(final GossipHolder holder, int position) {
        Gossip gossip = mDatas.get(position);
        int length = gossip.getLength();
        Date date = new Date(length * 1000);
        int playCount = gossip.getPlayCount();
        ImageUtile.LoadImage(gossip.getCover(),holder.image);
        holder.title.setText(gossip.getTitle());
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String time = format.format(date);
        holder.time.setText(time+" / "+playCount+"播放");
        ImageUtile.LoadImage(gossip.getTopicImg(),holder.icon);
        holder.topic.setText(gossip.getTopicName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mListener!=null) {
                    int adapterPosition = holder.getAdapterPosition();
                    mListener.onItemClick(v,adapterPosition);
                }
            }
        });
    }
    public int getItemCount() {
        if(mDatas!=null) {
            return mDatas.size();
        }
        return 0;
    }
    public  class GossipHolder extends RecyclerView.ViewHolder{
        ImageView image;
        ImageView icon;
        TextView title;
        TextView time;
        TextView topic;
        public GossipHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_gossip_icon);
            title = (TextView) itemView.findViewById(R.id.item_gossip_title);
            time = (TextView) itemView.findViewById(R.id.item_gossip_time_count);
            icon = (ImageView) itemView.findViewById(R.id.gossip_small_icon);
            topic = (TextView) itemView.findViewById(R.id.topic);
        }
    }
}
