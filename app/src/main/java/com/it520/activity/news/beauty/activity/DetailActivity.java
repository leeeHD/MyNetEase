package com.it520.activity.news.beauty.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.it520.activity.R;
import com.it520.activity.news.beauty.bean.Detail;
import com.it520.activity.news.beauty.bean.ImageInfo;
import com.it520.activity.share.ShareActivity;
import com.it520.activity.share.ShareInfo;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;
import com.it520.activity.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 许羊成:mac on 2016/6/19 20:02.
 * FUNCTION:
 */
public class DetailActivity extends ShareActivity implements View.OnClickListener {
    public static final String DOC_ID = "doc_id";
    public static final String EXTRA_SHAREINFO = ShareActivity.EXTRA_SHAREINFO;
    private static final int INIT_DATA = 0;
    private static final java.lang.String TAG = "DetailActivity";
    private WebView mBeautyDetail;
    private MyHandler mHandler;
    private View mParentView;
    private View mShare_ly;

    protected void onCreate(Bundle savedInstanceState) {

        mParentView = LayoutInflater.from(this).inflate(R.layout.activity_beauty_detail, null);
        setContentView(mParentView);
        mBeautyDetail = (WebView) findViewById(R.id.beauty_wb);
        String docId = getIntent().getStringExtra(DOC_ID);
        mHandler = new MyHandler(this);
        getData(docId);
        initView();

        super.onCreate(savedInstanceState);
    }

    /**
     * 为 shareInfo 赋值,跳转到此activity的页面必须传入一个 ShareInfo
     *
     * @see com.it520.activity.share.ShareInfo
     */
    @Override
    protected void setShareInfo() {
        mShareInfo = (ShareInfo) getIntent().getSerializableExtra(EXTRA_SHAREINFO);
    }

    /**
     * 返回当前布局的父容器
     *
     * @return
     */
    @Override
    protected View findShareViewParent() {
        return mParentView;
    }


    private void getData(final String docId) {


        String url = Contance.getBeautyDetailUrl(docId);
        System.out.println(url);
        HttpUtil httpUtil = HttpUtil.getInstance();
        httpUtil.doGet(url, new HttpResponse<String>(String.class) {
            public void onSuccess(String content) {
                if (TextUtils.isEmpty(content)) {
                    //网络请求失败
                    return;
                } else {
                    try {
                        //先将内容转换为JSONObject对象
                        JSONObject js = new JSONObject(content);
                        //利用optJSONObject不会报错
                        JSONObject doc_content = js.optJSONObject(docId);
                        Detail detail = JsonUtil.parse(doc_content.toString(), Detail.class);
                        if (detail != null) {
                            //获取内容体
                            String body = detail.getBody();
                            //获取到html内部使用的照片
                            List<ImageInfo> images = detail.getImg();
                            for (int i = 0; i < images.size(); i++) {
                                ImageInfo imageInfo = images.get(i);
                                String ref = imageInfo.getRef();
                                String imgHTML = "<IMG src='" + imageInfo.getSrc() + "'/>";
                                //System.out.println("图片地址:"+imgHTML);
                                //用src替换每一个图片
                                body = body.replace(ref, imgHTML);
                            }
                            //拼装标题
                            String titleHTML = "<p><span style='font-size:18px;'><strong>" + detail.getTitle() + "</strong></span></p>";
                            //在标题后面拼装时间和来源
                            titleHTML = titleHTML + "<p><span style='color:#666666;'>" + detail.getSource() + "&nbsp&nbsp" + detail.getPtime() + "</span></p>";
                            StringBuilder sb = new StringBuilder();
                            sb.append("<html>")
                                    .append("<head><style>img{width:100%}</style></head>")
                                    .append(titleHTML)
                                    .append(body)
                                    .append("</html>");
                            Message msg = mHandler.obtainMessage(INIT_DATA);
                            msg.obj = sb.toString();
                            mHandler.sendMessage(msg);
                            //Message countMsg = handler.obtainMessage(UPDATE_REPLY_COUNT, detail.getReplyCount(), 0);
                            //handler.sendMessage(countMsg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void onError(String msg) {

            }
        });
    }


    private static class MyHandler extends Handler {
        WeakReference<DetailActivity> weakReference;

        public MyHandler(DetailActivity detailActivity) {
            weakReference = new WeakReference<>(detailActivity);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DetailActivity detailActivity = weakReference.get();
            if (detailActivity == null) {
                return;
            }
            switch (msg.what) {
                case INIT_DATA:
                    String html_content = (String) msg.obj;
                    detailActivity.showContent(html_content);
                    break;
            }
        }
    }

    private void showContent(String htmlContent) {
        mBeautyDetail.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);


    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.article_share).setOnClickListener(this);

        mBeautyDetail.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mShare_ly.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                mShare_ly.setVisibility(View.GONE);
            }
        });


        mShare_ly = findViewById(R.id.share_ly);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.article_share:
                share();//分享
                break;
        }
    }


}
