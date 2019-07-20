package com.jsmt.developer.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_amenf_login_pass)
public class AmendLoginPassActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_new_pass)
    private EditText et_new_pass;
    @ViewInject(R.id.et_old_pass)
    private  EditText et_old_pass;
    @ViewInject(R.id.et_new_pass2)
    private EditText et_new_pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.xiugai_loginpay_padd);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_sure}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_sure:
                String oldpass=et_old_pass.getText().toString();
                String newpass=et_new_pass.getText().toString();
                String newpass2=et_new_pass2.getText().toString();
                if(oldpass.equals("")){
                    CusToast.showToast("请输入原密码");
                    return;
                }else  if(oldpass.equals("")){
                    CusToast.showToast("请输入新密码");
                    return;
                }else  if(oldpass.equals("")){
                    CusToast.showToast("请确认新密码");
                    return;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("token", BaseApplication.getInstance().userBean.getToken());
                map.put("newpass",newpass);
                map.put("opass",newpass2);
                map.put("pass",oldpass);

                upPassXGData(map);
                break;

        }
    }
    private void  upPassXGData(Map<String, Object> map){

        showDialog();
        XUtil.Post(URLConstant.XIUGIA_LOGINPASSWORLD,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====修改登录密码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        startActivity(new Intent(AmendLoginPassActivity.this, LoginActivity.class));
                        CusToast.showToast("请重新登录");
                        finish();
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