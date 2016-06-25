package com.it520.activity.splash.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.it520.activity.R;
import com.it520.activity.main.IndexActivity;
import com.it520.activity.splash.bean.LinksParams;
import com.it520.activity.util.ActivityUtils;

/**
 * Created by kay on 16/5/10.
 */
public class WebViewActivity extends Activity {
    WebView webview;
    String url = "";
    static String URL_NAME = "url";
    TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webview = (WebView) findViewById(R.id.webview);


        LinksParams params = (LinksParams) getIntent().getSerializableExtra(URL_NAME);
        //url = "http://click.gridsumdissector.com/track.ashx?gsadid=gad_167_24SRI1D3";
        Log.i("hked", "params = " + params);
        title = (TextView) findViewById(R.id.title);
        url = params.getLink_url();
        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String html_title = view.getTitle();
                title.setText(html_title);
            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String html_title) {
                title.setText(html_title);
            }
        });

    }

    public void exit(View w) {
        ActivityUtils.startActivity(this, IndexActivity.class, 0, false);
        finish();
        overridePendingTransition(R.anim.activity_exit_in, R.anim.activity_exit_out);
    }
}
