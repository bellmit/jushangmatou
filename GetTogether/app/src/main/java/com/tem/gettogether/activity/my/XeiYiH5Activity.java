package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_message_h5)
public class XeiYiH5Activity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.webView)
    private WebView webView;
    private String h5url,typeMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(this);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        typeMain=getIntent().getStringExtra("typeMain");
        h5url=getIntent().getStringExtra("h5url");
        if(typeMain.equals("1")){
            tv_title.setText(getText(R.string.carousel_details));
        }else {
            tv_title.setText(getText(R.string.jushang_terminal_service_agreement));

        }
    }

    @Override
    protected void initView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        if (webView != null) {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    //   handler.sendEmptyMessage(1);
                    super.onPageFinished(view, url);
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(h5url);

        }
    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;

        }
    }
}
