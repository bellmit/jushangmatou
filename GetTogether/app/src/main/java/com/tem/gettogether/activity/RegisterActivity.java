package com.tem.gettogether.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.MessageH5Activity;
import com.tem.gettogether.activity.my.XeiYiH5Activity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.CountDownTimerUtils;
import com.tem.gettogether.utils.xutils3.CountDownTimerUtils3;
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

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_passworld)
    private EditText et_passworld;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.tv_code)
    private TextView tv_code;
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @ViewInject(R.id.checkbox)
    private CheckBox checkbox;
    @ViewInject(R.id.ll_All)
    private LinearLayout ll_All;
    @ViewInject(R.id.ll_success_zt)
    private LinearLayout ll_success_zt;
    @ViewInject(R.id.denglu_zt_5)
    private LinearLayout denglu_zt_5;
    @ViewInject(R.id.quyers_rb)
    private RadioButton quyers_rb;
    @ViewInject(R.id.supplier_rb)
    private RadioButton supplier_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.zhuche);
        checkbox.setChecked(true);
        quyers_rb.setChecked(true);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_register,R.id.tv_goLogin,R.id.tv_xieyi,R.id.tv_code,R.id.tv_goLogin2,R.id.denglu_zt_5,R.id.quyers_rb,R.id.supplier_rb}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_xieyi:
                upCodeZHUCE();
                break;
            case R.id.tv_code:
                if(et_phone.getText().toString().length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }
                upCode(et_phone.getText().toString());
                break;
            case R.id.tv_register:
                Log.d("chenshichun","-------"+quyers_rb.isChecked());
                String phone=et_phone.getText().toString().trim();
                String password=et_passworld.getText().toString().trim();
                String code=et_code.getText().toString().trim();
                if(phone.length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }else if(password.length()<6){
                    CusToast.showToast("请输入密码");
                    return;
                }else if(code.length()<4){
                    CusToast.showToast("请输入正确的验证码");
                    return;
                }else if(checkbox.isChecked()==false){
                    CusToast.showToast("请先阅读服务协议并同意");
                    return;
                }
                upRegister(phone,password,code);
                break;
            case R.id.tv_goLogin:
                finish();
                break;
            case R.id.tv_goLogin2:
                finish();
                break;
            case R.id.denglu_zt_5:
                Intent intent = new Intent();
                intent.setClass(this,ServiceProviderActivity.class);
                startActivity(intent);
                break;
            case R.id.quyers_rb:
                if(quyers_rb.isChecked()){
                    denglu_zt_5.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.supplier_rb:
                if(supplier_rb.isChecked()){
                    denglu_zt_5.setVisibility(View.GONE);
                }
                break;
        }
    }
    private void upRegister(String phone,String pass,String code){
        Map<String,Object> map=new HashMap<>();
        map.put("username",phone);
        map.put("password",pass);
        map.put("password2",pass);
        map.put("code",code);
        showDialog();
        XUtil.Post(URLConstant.REGISTER,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====注册===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        ll_success_zt.setVisibility(View.VISIBLE);
                        ll_All.setVisibility(View.GONE);
                    }else{
                        ll_All.setVisibility(View.VISIBLE);
                        ll_success_zt.setVisibility(View.GONE);
                    }
                    CusToast.showToast(msg);

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
    private void upCode(String phone){
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",phone);
        map.put("unique_id","0");
        showDialog();
        XUtil.Post(URLConstant.HUOQU_CODE,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====注册获取验证码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        CountDownTimerUtils3 mCountDownTimerUtils = new CountDownTimerUtils3(tv_code, 60000, 1000);
                        mCountDownTimerUtils.start();
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
    private void upCodeZHUCE(){

        showDialog();
        XUtil.Post(URLConstant.ZHUCEXIYEYI,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====注册协议===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject2 = new JSONObject(result2);
                        String url = jsonObject2.optString("url");

                        startActivity(new Intent(RegisterActivity.this,XeiYiH5Activity.class)
                                .putExtra("typeMain","2")
                                .putExtra("h5url",url));

                    }else {
                        CusToast.showToast(msg);
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
