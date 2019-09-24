package com.tem.gettogether.activity.my;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_message_h5)
public class ShopGGH5Activity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.webView)
    private WebView webView;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    private String h5url;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(getText(R.string.product_specifications_tv));
        h5url=getIntent().getStringExtra("h5url");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(getText(R.string.save));
        tv_title_right.setTextColor(getResources().getColor(R.color.my_yellow));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Event(value = {R.id.rl_close,R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_title_right:
                webView.evaluateJavascript("save", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.i("===js 返回的结果==",value+"");
                        finish();
                        //此处为 js 返回的结果
                    }
                });
                break;

        }
    }
}
