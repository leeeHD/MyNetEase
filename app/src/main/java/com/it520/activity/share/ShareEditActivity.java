package com.it520.activity.share;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.util.DisplayUtils;
import com.it520.activity.yxxbase.BaseActivity;

/**
 * 新浪分享时的编辑页面
 */
public class ShareEditActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private static final java.lang.String TAG = "ShareEditActivity";
    private EditText mEditText;
    private TextView mLimitLengthTv;
    private TextView mTitleTv;
    private ShareInfo mShareInfo;
    public static final String EXTRA_SHAREINFO = ShareActivity.EXTRA_SHAREINFO;
    private ImageView mSendIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShareInfo = (ShareInfo) getIntent().getSerializableExtra(EXTRA_SHAREINFO);
        if (mShareInfo == null)
            throw new NullPointerException("分享的信息不能为空");
        setContentView(R.layout.activity_share_edit);
        initView();
    }


    private void initView() {
        findViewById(R.id.share_send_exit_iv).setOnClickListener(this);
        mSendIv = (ImageView) findViewById(R.id.share_send_sure_iv);
        mSendIv.setOnClickListener(this);

        mTitleTv = (TextView) findViewById(R.id.share_title_tv);
        mLimitLengthTv = (TextView) findViewById(R.id.share_edit_limit_length_tv);
        mEditText = (EditText) findViewById(R.id.share_edit_text_et);

        mEditText.addTextChangedListener(this);

        mTitleTv.setText(mShareInfo.getTitle());
        mEditText.setText("分享网易新闻" + mShareInfo.getText());


        int length = mEditText.getText().toString().length();
        mEditText.setSelection(length);//将光标追踪到内容的最后
        setLimitLenText(length);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_send_exit_iv:
                onBackPressed();
                break;
            case R.id.share_send_sure_iv:
                String text = mEditText.getText().toString();
                if (text.length() <= ShareInfo.MAX_LENGTH) {
                    mShareInfo.setText(text);
                    ShareHelper.share(this, ShareHelper.PlatformToShare.SINA_WEIBO, mShareInfo);
                    onBackPressed();
                } else {
                    Toast.makeText(ShareEditActivity.this, "字数超出!", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int currentLength = s.length();
        setLimitLenText(currentLength);
    }

    private void setLimitLenText(int currentLength) {
        String limitText = currentLength + "/" + ShareInfo.MAX_LENGTH;
        if (currentLength > ShareInfo.MAX_LENGTH) {
            mLimitLengthTv.setText(DisplayUtils.setTextSpanColor(limitText, Color.RED, 0, String.valueOf(currentLength).length()));
        } else {
            mLimitLengthTv.setText(limitText);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
