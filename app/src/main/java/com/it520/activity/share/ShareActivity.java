package com.it520.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.it520.activity.R;
import com.it520.activity.util.ActivityUtils;
import com.it520.activity.yxxbase.BaseActivity;

public abstract class ShareActivity extends BaseActivity {

    public static final String EXTRA_SHAREINFO = "extra_shareInfo";
    private static final String TAG = "ShareActivity";
    private ShareClick mShareClick;
    protected ShareInfo mShareInfo;
    private ShareHelper mShareHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mShareClick == null) {
            mShareClick = new ShareClick();
        }
        View view = findShareViewParent();
        initShareItem(view);
        mShareInfo = new ShareInfo();

    }

    private void initShareItem(View view) {

        view.findViewById(R.id.sns_sina_ll).setOnClickListener(mShareClick);
        view.findViewById(R.id.sns_qqfriends_ll).setOnClickListener(mShareClick);
        view.findViewById(R.id.sns_qzone_ll).setOnClickListener(mShareClick);
        view.findViewById(R.id.sns_more_ll).setOnClickListener(mShareClick);
    }


    class ShareClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setShareInfo();
            if (mShareInfo == null) {
                throw new NullPointerException("分享的信息不能为空");
            }
            switch (v.getId()) {
                case R.id.sns_sina_ll:
                    Intent intent = new Intent(ShareActivity.this, ShareEditActivity.class);
                    intent.putExtra(EXTRA_SHAREINFO, mShareInfo);
                    ActivityUtils.startActivity(ShareActivity.this, intent, 0, false);
                    break;
                case R.id.sns_qqfriends_ll:
                    ShareHelper.share(ShareActivity.this, ShareHelper.PlatformToShare.QQ_FRIENDS, mShareInfo);
                    break;
                case R.id.sns_qzone_ll:
                    ShareHelper.share(ShareActivity.this, ShareHelper.PlatformToShare.QQ_ZONE, mShareInfo);
                    break;
                case R.id.sns_more_ll:
                    share();
                    break;
            }
        }
    }

    protected void share() {
        if (mShareHelper == null)
            mShareHelper = new ShareHelper();
        setShareInfo();
        mShareHelper.shareDir(this, mShareInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShareClick != null) {
            mShareClick = null;
        }
        mShareHelper = null;
        mShareInfo = null;
    }

    /**
     * 设置ShareInfo,
     *
     * @see com.it520.activity.share.ShareInfo
     */
    protected abstract void setShareInfo();

    /**
     * 找到shareView 的父控件
     */
    protected abstract View findShareViewParent();

}
