package com.tem.gettogether.activity.my.shopauthentication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_shop_authentication)
public class ShopAuthenticationActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_qglx1)
    private ImageView iv_qglx1;
    @ViewInject(R.id.iv_qglx2)
    private ImageView iv_qglx2;
    @ViewInject(R.id.iv_qglx3)
    private ImageView iv_qglx3;
    private int RZType = 0;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_youxiang)
    private EditText et_youxiang;
    @ViewInject(R.id.et_moble)
    private EditText et_moble;

    @Override
    protected void initData() {

        x.view().inject(this);
        tv_title.setText(R.string.shopRZ);

    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close, R.id.tv_tjsh, R.id.iv_qglx1, R.id.iv_qglx2, R.id.iv_qglx3}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.iv_qglx1:
                RZType = 1;
                iv_qglx1.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);
                iv_qglx3.setImageResource(R.drawable.weixuanz);
                break;
            case R.id.iv_qglx2:
                RZType = 2;
                iv_qglx2.setImageResource(R.drawable.xuanzhongf);
                iv_qglx1.setImageResource(R.drawable.weixuanz);
                iv_qglx3.setImageResource(R.drawable.weixuanz);
                break;
            case R.id.iv_qglx3:
                RZType = 0;
                iv_qglx3.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);
                iv_qglx1.setImageResource(R.drawable.weixuanz);
                break;
            case R.id.tv_tjsh:
                upRequest();
                break;
        }
    }

    private void upRequest() {
        Map<String, Object> map = new HashMap<>();
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String youxiang = et_youxiang.getText().toString();
        String mobile = et_moble.getText().toString();
        if (name.equals("")) {
            CusToast.showToast("请输入您身份证上的姓名");
            return;
        } else if (phone.equals("")) {
            CusToast.showToast("请输入您的手机号");
            return;
        } else if (youxiang.equals("")) {
            CusToast.showToast("请输入您身的电子邮箱");
            return;
        }
        if(!isEmail(youxiang)){
            CusToast.showToast("请输入正确电子邮箱格式");
            return;
        }

        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("contacts_name", name);
        map.put("contacts_mobile", phone);
        map.put("contacts_email", youxiang);
        map.put("apply_type", RZType);
        map.put("mobile",mobile);
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        showDialog();
        Log.d("chenshichun","=====cscscscscscsc======");
        XUtil.Post(URLConstant.JINBENXINXI_UPLOADING, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.d("chenshichun","=====认证======"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        startActivity(new Intent(ShopAuthenticationActivity.this, PersionAuthenticationActivity.class)
                                .putExtra(Contacts.AUTHENTICATION_TYPE,RZType));
                    } else {
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

    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
