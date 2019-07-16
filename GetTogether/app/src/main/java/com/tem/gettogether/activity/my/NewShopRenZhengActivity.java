package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.fragment.PersonageRZActivity;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CheckBoxSample;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_new_shop_ren_zheng)
public class NewShopRenZhengActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_qglx1)
    private ImageView iv_qglx1;
    @ViewInject(R.id.iv_qglx2)
    private ImageView iv_qglx2;

    private int RZType=0;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_youxiang)
    private EditText et_youxiang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        BaseApplication.addDestoryActivity(this,"RenZ1");
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.shopRZ);
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close,R.id.tv_tjsh,R.id.iv_qglx1, R.id.iv_qglx2}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.iv_qglx1:
                RZType=1;
                iv_qglx1.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);

                break;
            case R.id.iv_qglx2:
                RZType=2;
                iv_qglx2.setImageResource(R.drawable.xuanzhongf);
                iv_qglx1.setImageResource(R.drawable.weixuanz);

                break;
            case R.id.tv_tjsh:

                if(RZType==0){
                    CusToast.showToast("请选择入住类型");
                   return;
                }else {
                    upRequest();
                }
                break;

        }
    }
    private void upRequest(){
        Map<String,Object> map=new HashMap<>();
        String name=et_name.getText().toString();
        String phone=et_phone.getText().toString();
        String youxiang=et_youxiang.getText().toString();
        if(name.equals("")){
            CusToast.showToast("请输入您身份证上的姓名");
            return;
        }else if(phone.equals("")){
            CusToast.showToast("请输入您的手机号");
            return;
        }else if(youxiang.equals("")){
            CusToast.showToast("请输入您身的电子邮箱");
            return;
        }
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("contacts_name",name);
        map.put("contacts_mobile",phone);
        map.put("contacts_email",youxiang);
        map.put("apply_type",RZType);
        showDialog();
        XUtil.Post(URLConstant.JINBENXINXI_UPLOADING,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===商铺认证--",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if(RZType==1){
                            startActivity(new Intent(NewShopRenZhengActivity.this, PersonageRZActivity.class));
                        }else if(RZType==2){
                            startActivity(new Intent(NewShopRenZhengActivity.this,EnterpriseRZActivity.class));
                        }
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
