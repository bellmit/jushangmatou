package com.jsmt.developer.activity.my.shopauthentication;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
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
                RZType = 3;
                iv_qglx3.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);
                iv_qglx1.setImageResource(R.drawable.weixuanz);
                break;
            case R.id.tv_tjsh:
                if (RZType == 0) {
                    CusToast.showToast("请选择入驻类型");
                    return;
                } else {
                    upRequest();
                }
                break;

        }
    }

    private void upRequest() {
        Map<String, Object> map = new HashMap<>();
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String youxiang = et_youxiang.getText().toString();
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
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("contacts_name", name);
        map.put("contacts_mobile", phone);
        map.put("contacts_email", youxiang);
        map.put("apply_type", RZType);
        showDialog();
        XUtil.Post(URLConstant.JINBENXINXI_UPLOADING, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (RZType == 1) {
                            startActivity(new Intent(ShopAuthenticationActivity.this, PersionAuthenticationActivity.class));
                        } else if (RZType == 2) {
                            startActivity(new Intent(ShopAuthenticationActivity.this, FactoryAuthenticationActivity.class));
                        } else if(RZType == 3){
                            startActivity(new Intent(ShopAuthenticationActivity.this, DistributorAuthenticationActivity.class));
                        }
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

}
