package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
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
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(R.string.xiugai_loginpay_padd);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
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
                    CusToast.showToast(getText(R.string.enter_original_password));
                    return;
                }else  if(oldpass.equals("")){
                    CusToast.showToast(getText(R.string.enter_new_password));
                    return;
                }else  if(oldpass.equals("")){
                    CusToast.showToast(getText(R.string.confirm_new_password));
                    return;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
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
                        CusToast.showToast(getText(R.string.please_login_again));
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
