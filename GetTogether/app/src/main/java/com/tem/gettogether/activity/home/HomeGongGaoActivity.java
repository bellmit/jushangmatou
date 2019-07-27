package com.tem.gettogether.activity.home;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MessageTGBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_home_gong_gao)
public class HomeGongGaoActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private String article_id;
    @ViewInject(R.id.tv_gg_content)
    private TextView tv_gg_content;
    @ViewInject(R.id.tv_gg_time)
    private TextView tv_gg_time;
    @ViewInject(R.id.tv_gg_title)
    private TextView tv_gg_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        upMessage();
    }

    @Override
    protected void initData() {
        tv_title.setText("公告详情");
        article_id=getIntent().getStringExtra("article_id");
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;

        }
    }
    private void upMessage(){
        Map<String,Object> map=new HashMap<>();
        map.put("article_id",article_id);
        showDialog();
        XUtil.Post(URLConstant.MESSAGEXIANGQING,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====通告详情===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                   if(res.equals("1")){
                       Gson gson=new Gson();
                       MessageTGBean messageTGBean=gson.fromJson(result,MessageTGBean.class);
                       tv_gg_title.setText(messageTGBean.getResult().getTitle());
//                       tv_gg_content.setText(messageTGBean.getResult().getContent());
                       tv_gg_content.setText(Html.fromHtml(messageTGBean.getResult().getContent()));

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
                closeDialog();

            }
        });
    }
}
