package com.jsmt.developer.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.utils.xutils3.CountDownTimerUtils3;
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

@ContentView(R.layout.activity_forget_pass)
public class ForgetPassActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_passworld)
    private EditText et_passworld;
    @ViewInject(R.id.et_passworld2)
    private EditText et_passworld2;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.tv_code)
    private TextView tv_code;
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.forget_pass);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_goLogin,R.id.tv_code,R.id.tv_queren}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_code:
                if(et_phone.getText().toString().length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }
                upCode(et_phone.getText().toString());
                break;
            case R.id.tv_queren:
                String phone=et_phone.getText().toString().trim();
                String password=et_passworld.getText().toString().trim();
                String password2=et_passworld2.getText().toString().trim();

                String code=et_code.getText().toString().trim();
                if(phone.length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }else if(code.length()<4){
                    CusToast.showToast("请输入正确的验证码");
                    return;
                }else if(password.length()<6){
                    CusToast.showToast("请输入新密码");
                    return;
                }else if(password2.length()<6){
                    CusToast.showToast("请输入确认密码");
                    return;
                }
                upForGetPass(phone,password,password2,code);
                break;

        }
    }
    private void upForGetPass(String phone,String pass,String pass2,String code){
        Map<String,Object> map=new HashMap<>();
        map.put("username",phone);
        map.put("new_password",pass);
        map.put("confirm_password",pass2);
        map.put("code",code);
        showDialog();
        XUtil.Post(URLConstant.FORGET_PASS,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====忘记登录密码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);

                    if(res.equals("1")){
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
    private void upCode(String phone){
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",phone);
        map.put("unique_id","2");
        showDialog();
        XUtil.Post(URLConstant.HUOQU_CODE,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====忘记密码验证码===", result);
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
}
