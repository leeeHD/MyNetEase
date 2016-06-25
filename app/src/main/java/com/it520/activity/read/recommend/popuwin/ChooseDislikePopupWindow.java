package com.it520.activity.read.recommend.popuwin;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.read.recommend.bean.RecommendDetail;

/**
 * @author moy
 * @time 2016/6/22  10:23
 * @desc ${TODD}
 */

public class ChooseDislikePopupWindow implements View.OnClickListener, IPopupWindown {
    private Context mContext;
    private PopupWindow mPopupWindow;
    private RecommendDetail mBean;
    private TextView mSource;
    private TextView mSort;

    public ChooseDislikePopupWindow(Context context) {
        this.mContext = context;
        initView();
    }
    @Override
    public void initView() {
        //初始化布局
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contentView = inflater.inflate(R.layout.read_pop_view, null);
        mSource = (TextView) contentView.findViewById(R.id.read_source);
        mSort = (TextView) contentView.findViewById(R.id.read_sort);
        TextView mSureTv = (TextView) contentView.findViewById(R.id.read_submit_tv);
        mSureTv.setOnClickListener(this);
        //初始化PopWindow
        mPopupWindow = new PopupWindow(contentView, -2, -2);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.update();
    }

    @Override
    public void show(Object bean, View anchor,int x,int y) {
        mBean = (RecommendDetail) bean;


        if (mPopupWindow != null) {
            mSource.setText(((RecommendDetail) bean).getSource());
            String s = ((RecommendDetail) bean).getUnlikeReason().get(0).replace("^\\d$","");
            mSort.setText(s);
            mPopupWindow.showAtLocation(anchor, Gravity.LEFT+Gravity.TOP, 20, y);
        }
    }

    @Override
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 提交
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        // TODO: 2016/6/22 传值回去
        dismiss();
    }
}
