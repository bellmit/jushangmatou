package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.GYWMBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_gywe)
public class GYWeActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_connect)
    private TextView tv_connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.gywm);

    }

    @Override
    protected void initView() {
        upGYData();
    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;

        }
    }
    private void upGYData() {
        showDialog();
        String language = SharedPreferencesUtils.getString(this,BaseConstant.SPConstant.language,"");
        Map<String,String> map = new HashMap<>();
        map.put("language",language);
        XUtil.Get(URLConstant.GYWMEN,map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====关于我们===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        GYWMBean gywmBean=gson.fromJson(result,GYWMBean.class);
//                        String htmlStr = "<font color='#0000FF' size='500px'>我是蓝色的rfghgfcvgbhnhgfcvgbhnbvgcfgbvhnjm文本</font><br><font color='#ff0000' size='40px' weight='600'>我是红色的文本</font><br><font color='#000000' size='20px'>我是黑色的文本</font>";
//                        tv_connect.setText(Html.fromHtml(htmlStr));
                        tv_connect.setText(Html.fromHtml(gywmBean.getResult().getContent()));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
}
