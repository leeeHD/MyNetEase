package com.it520.activity.news.sport;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.news.sport.bean.Detail;
import com.it520.activity.news.sport.bean.ImageInfor;
import com.it520.activity.news.sport.util.GsonUtil;
import com.it520.activity.util.Contance;
import com.it520.activity.util.HttpResponse;
import com.it520.activity.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author moy
 * @time 2016/6/19  22:30
 * @desc 详情
 */

public class SportDetailActivity extends Activity {
    WebView webView;
    public final static String DOC_ID="doc_id";
    final static int INITDATE = 0;
    final static int ERROR =1;
    final static int UPDATE_REPLY_COUNT =2;

    MyHandler handler ;
    TextView replay_count;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_detail);
        handler = new MyHandler(this);
        webView = (WebView) findViewById(R.id.webview);
        replay_count = (TextView) findViewById(R.id.replay_count);
        //获取某条新闻的id
        String docid = getIntent().getStringExtra(DOC_ID);
        Log.i("moy","docid:"+docid);
        getDate(docid);
    }

    //展示失败信息
    public void showError(String msg){
        Message message =   handler.obtainMessage(ERROR);
        message.obj = "网络请求失败!!!";
        handler.sendMessage(message);

    }


    public void showContent(String htmlContent){
        //第二个参数 html的代码 , 第三个参数 mine type , 第四个参数就是编码格式
        webView.loadDataWithBaseURL(null,htmlContent,"text/html","utf-8",null);
    }

    public void getDate(final String docId){
        //生成获取详细信息的url
        String url = Contance.getDetailUrl(docId);
        Log.i("moy","url:"+url);
        HttpUtil util = HttpUtil.getInstance();
        util.doGet(url, new HttpResponse<String>(String.class) {
            @Override
            public void onSuccess(String content) {
                if(TextUtils.isEmpty(content)){
                    showError("网络请求失败!!!");
                    return;
                }
                try {
                    JSONObject js =new JSONObject(content);
                    JSONObject doc_content = js.optJSONObject(docId);
                    Detail detail = GsonUtil.parse(doc_content.toString(), Detail.class);
                    if(detail!=null) {
                        //html的内容体
                        String body = detail.getBody();
                        //html内部使用的图片
                        List<ImageInfor> images =  detail.getImg();
                        for(int i =0 ;i<images.size();i++){
                            ImageInfor image = images.get(i);
                            String ref = image.getRef();
                            String imgHTML="<IMG src='" + image.getSrc() + "'/>"
                                    +"<p><span style='font-size:15px;'><strong>"+image.getAlt()+"</strong></span></p>";
                            //替换每一个显示图片的html
                            body =  body.replace(ref,imgHTML);
                        }
                        //拼装标题
                        String titleHTML ="<p><span style='font-size:20px;'><strong>"+detail.getTitle()+"</strong></span></p>";
                        //在标题后面拼装时间和来源
                        titleHTML=titleHTML+"<p><span style='color:#666666;'>"+detail.getSource()+"&nbsp&nbsp"+detail.getPtime()+"</span></p>";

                        StringBuilder builder = new StringBuilder();
                        builder.append("<html>")
                                .append("<head><style>img{width:100%}</style></head>")
                                .append(titleHTML)
                                .append(body)
                                .append("</html>");


                        Message initMessage = handler.obtainMessage(INITDATE);
                        initMessage.obj = builder.toString();
                        handler.sendMessage(initMessage);

                        Message re_message = handler.obtainMessage(UPDATE_REPLY_COUNT,detail.getReplyCount(),0);

                        handler.sendMessage(re_message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });


    }

    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }


    public void updateRelyCount(int relyCount){
        replay_count.setText(String.valueOf(relyCount));
    }
    static class MyHandler extends Handler {
        WeakReference<SportDetailActivity> activity;
        public MyHandler(SportDetailActivity activity){
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SportDetailActivity act =   activity.get();
            if(act == null){
                return;
            }
            switch (msg.what){
                case INITDATE:
                    String html_content = (String) msg.obj;
                    act.showContent(html_content);
                    break;
                case ERROR:
                    act.showToast((String)msg.obj);
                    break;

                case UPDATE_REPLY_COUNT:
                    act.updateRelyCount(msg.arg1);
                    break;
            }
        }
    }
}
