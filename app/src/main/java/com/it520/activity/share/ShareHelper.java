package com.it520.activity.share;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.it520.activity.R;
import com.it520.activity.util.ActivityUtils;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.linkedin.LinkedIn;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.yixin.friends.Yixin;
import cn.sharesdk.yixin.moments.YixinMoments;
import cn.sharesdk.youdao.YouDao;

/**
 * Created by Yu xiangxin on 2016/6/22 - 1:25.
 *
 * @desc ${TODD}
 */
public class ShareHelper implements View.OnClickListener {
    private static final java.lang.String TAG = "ShareHelper";
    private ShareInfo mShareInfo;
    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mAlertDialog;
    private View mView;

    public static class PlatformToShare {
        public static final String QQ_FRIENDS = QQ.NAME;
        public static final String QQ_ZONE = QZone.NAME;
        public static final String SINA_WEIBO = SinaWeibo.NAME;
        public static final String YI_XIN = Yixin.NAME;
        public static final String YI_XIN_FRIEND = YixinMoments.NAME;
        public static final String EMAIL = Email.NAME;
        public static final String LINKED_IN = LinkedIn.NAME;
        public static final String YOU_DAO_YUN = YouDao.NAME;
    }

    public static void share(Context context, String platformToShare, ShareInfo info) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(true);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(info.getTitle());
        oks.setTitleUrl(info.getUrl());
        //微博 设置链接时,text不显示(待修复)
        oks.setText(platformToShare != null ? platformToShare == PlatformToShare.SINA_WEIBO ? info.getUrl() : info.getText() : info.getText());
        oks.setImageUrl(info.getImgUrl());
        oks.setUrl(info.getUrl());
        oks.setExecuteUrl(info.getUrl());
        oks.setComment(info.getComment());
        oks.setSite(context.getString(R.string.app_name));
        oks.setVenueName(context.getString(R.string.app_name));
        oks.setAddress(info.getAddress());
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
        //oks.setCallback(new OneKeyShareCallback());
        // 启动分享GUI
        oks.show(context);
    }


    public void shareDir(Context context, ShareInfo info) {
        mShareInfo = info;
        this.mContext = context;
        mBuilder = new AlertDialog.Builder(context);
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_share_layout, null);
        assignViews(mView);
        mBuilder.setView(mView);
        mAlertDialog = mBuilder.show();
    }


    private void assignViews(View view) {
        view.findViewById(R.id.sns_sina_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_qzone_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_qqfriends_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_yx_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_yx_friend_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_email_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_linked_in_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_ydy_dir_ll).setOnClickListener(this);
        view.findViewById(R.id.sns_more_dir_ll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String platName = null;
        switch (v.getId()) {
            case R.id.sns_sina_dir_ll:
                Intent intent = new Intent(mContext, ShareEditActivity.class);
                intent.putExtra(ShareEditActivity.EXTRA_SHAREINFO, mShareInfo);
                ActivityUtils.startActivity(mContext, intent, 0, false);
                mAlertDialog.dismiss();
                mBuilder = null;
                mView = null;
                return;
            case R.id.sns_qzone_dir_ll:
                platName = PlatformToShare.QQ_ZONE;
                break;
            case R.id.sns_qqfriends_dir_ll:
                platName = PlatformToShare.QQ_FRIENDS;
                break;
            case R.id.sns_yx_dir_ll:
                platName = PlatformToShare.YI_XIN;
                break;
            case R.id.sns_yx_friend_dir_ll:
                platName = PlatformToShare.YI_XIN_FRIEND;
                break;
            case R.id.sns_email_dir_ll:
                platName = PlatformToShare.EMAIL;
                break;
            case R.id.sns_linked_in_dir_ll:
                platName = PlatformToShare.LINKED_IN;
                break;
            case R.id.sns_ydy_dir_ll:
                platName = PlatformToShare.YOU_DAO_YUN;
                break;
            case R.id.sns_more_dir_ll:
                break;

        }
        share(mContext, platName, mShareInfo);
        mAlertDialog.dismiss();
        mBuilder = null;
        mView = null;
    }


}
