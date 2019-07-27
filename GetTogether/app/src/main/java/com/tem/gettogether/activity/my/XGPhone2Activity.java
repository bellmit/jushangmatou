package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.CountDownTimerUtils;
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
@ContentView(R.layout.activity_xgphone2)
public class XGPhone2Activity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.et_cade)
    private EditText et_cade;
    @ViewInject(R.id.tv_gain_cade)
    private TextView tv_gain_cade;
    @ViewInject(R.id.rl_title_right)
    private RelativeLayout rl_title_right;
    @ViewInject(R.id.tv_phone_ts)
    private TextView tv_phone_ts;
    private String newphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        newphone=getIntent().getStringExtra("newPhone");
        tv_phone_ts.setText("短信验证码已发送至+86 "+newphone+"，请填写验证码");
        tv_title.setText(R.string.bangding_phone);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(R.string.queding);
        tv_title_right.setTextColor(getResources().getColor(R.color.my_xg_9b));
        rl_title_right.setEnabled(false);
    }

    @Override
    protected void initView() {
        et_cade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_cade.getText().toString().length()==4){
                    tv_title_right.setTextColor(getResources().getColor(R.color.my_xyb));
                    rl_title_right.setEnabled(true);
                }

            }
        });
    }
    @Event(value = {R.id.rl_close,R.id.rl_title_right,R.id.tv_gain_cade}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_title_right://下一步
                String cade=et_cade.getText().toString();
                upBDPhone(cade);

                break;
            case R.id.tv_gain_cade:
                upCode(newphone);
                break;

        }
    }
    private void upBDPhone(String cade){
        Map<String,Object> map=new HashMap<>();
        map.put("newphone",newphone);
        map.put("newcode",cade);
        map.put("token", BaseApplication.getInstance().userBean.getToken());

        showDialog();
        XUtil.Post(URLConstant.BANGDING_PHONE,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====绑定手机号===", result);
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
        map.put("unique_id","3");
        showDialog();
        XUtil.Post(URLConstant.HUOQU_CODE,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====绑定手机号获取验证码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tv_gain_cade, 60000, 1000);
                    mCountDownTimerUtils.start();
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
