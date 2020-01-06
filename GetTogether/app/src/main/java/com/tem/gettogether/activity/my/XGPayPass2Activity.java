package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.OnPasswordInputFinish2;
import com.tem.gettogether.view.PasswordView2;
import com.tem.gettogether.view.PasswordView3;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_xgpay_pass2)
public class XGPayPass2Activity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.rl_title_right)
    private RelativeLayout rl_title_right;

    @ViewInject(R.id.passView2)
    private PasswordView2 passView2;
    @ViewInject(R.id.passView3)
    private PasswordView3 passView3;
    private String newPay;
    private String newPay2;

    @ViewInject(R.id.tv_paytitle)
    private TextView tv_paytitle;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;

    @Override
    protected void initData() {
        x.view().inject(this);

        tv_title.setText(R.string.xiugai_pay_mima);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(R.string.xiayibu);
        tv_title_right.setTextColor(getResources().getColor(R.color.my_xg_9b));
        rl_title_right.setEnabled(false);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);

        passView2.setOnFinishInput2(new OnPasswordInputFinish2() {
            @Override
            public void inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
                newPay=passView2.getStrPassword();
                passView2.setVisibility(View.GONE);
                passView3.setVisibility(View.VISIBLE);
                tv_paytitle.setText(getText(R.string.qzcsrzfmm));
                tv_title_right.setTextColor(getResources().getColor(R.color.my_xyb));
                rl_title_right.setEnabled(true);
            }

        });
        passView3.setOnFinishInput2(new OnPasswordInputFinish2() {
            @Override
            public void inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
                newPay2=passView3.getStrPassword();

            }

        });
    }
    @Event(value = {R.id.rl_close,R.id.rl_title_right,R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_title_right://下一步
                Map<String,Object> map=new HashMap<>();
                map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                map.put("newpass",newPay);
                map.put("opass",newPay2);
                upPassXGData(map);
                break;

        }
    }
    private void  upPassXGData(Map<String, Object> map){

        showDialog();
        XUtil.Post(URLConstant.XIUGIA_PAYPASS,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====修改支付密码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        Gson gson=new Gson();
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
