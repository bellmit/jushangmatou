package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
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

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_bank_card_qr)
public class BankCardQRActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.et_yinhang)
    private EditText et_yinhang;
    private String Name,Num;
    @ViewInject(R.id.checkbox)
    private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.yinhangka_bd);
        Name=getIntent().getStringExtra("Name");
        Num=getIntent().getStringExtra("Num");
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_xiayibu}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_xiayibu://下一步
                String yinhang=et_yinhang.getText().toString();
                if(yinhang.equals("")){
                    CusToast.showToast("请填写所属银行");
                    return;
                }else{
                    if(checkbox.isChecked()==true){
                        upyinhCode(yinhang);

                    }else {
                        CusToast.showToast("请勾选用户协议");
                        return;
                    }
                }
                break;

        }
    }
    private void upyinhCode(final String yinhang){
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_name",Name);
        map.put("bank_number",Num);
        map.put("bank_name",yinhang);

        showDialog();
        XUtil.Post(URLConstant.BANNGDING,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====绑定银行卡===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        CusToast.showToast("绑定成功");
                        BaseApplication.destoryActivity("bang");

                        finish();

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
