package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.OnPasswordInputFinish;
import com.tem.gettogether.view.PasswordView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_xgpay_pass_word)
public class XGPayPassWordActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.rl_title_right)
    private RelativeLayout rl_title_right;

    @ViewInject(R.id.passView)
    private PasswordView passView;
    private String payPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("验证支付密码");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(R.string.xiayibu);
        tv_title_right.setTextColor(getResources().getColor(R.color.my_xg_9b));
        rl_title_right.setEnabled(false);
    }

    @Override
    protected void initView() {
// 添加回调接口
        passView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
//                CusToast.showToast(passView.getStrPassword());
                payPass=passView.getStrPassword();
                rl_title_right.setVisibility(View.GONE);
                tv_title_right.setTextColor(getResources().getColor(R.color.my_xyb));
                rl_title_right.setEnabled(true);

            }
            @Override
            public void outfo() {
                finish();
            }
            //确定
            @Override
            public void queding( ) {
                Map<String,Object> map=new HashMap<>();
                map.put("token", BaseApplication.getInstance().userBean.getToken());
                map.put("pass",payPass);
                upPassXGData(map);
            }
        });


    }
    private void  upPassXGData(Map<String, Object> map){

        showDialog();
        XUtil.Post(URLConstant.YANZHEWNG_PAYPASS,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====验证支付密码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        startActivity(new Intent(XGPayPassWordActivity.this,XGPayPass2Activity.class));
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
    @Event(value = {R.id.rl_close,R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_title_right://下一步
//                startActivity(new Intent(this,XGPhone2Activity.class));
                break;

        }
    }
}
