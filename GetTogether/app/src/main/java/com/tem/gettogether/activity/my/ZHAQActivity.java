package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_zhaq)
public class ZHAQActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private String phone;
    @ViewInject(R.id.tv_dq_phone)
    private TextView tv_dq_phone;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.zhanghu_anquan);
        phone=getIntent().getStringExtra("phone");
        tv_dq_phone.setText(phone);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
    }
    @Event(value = {R.id.rl_close,R.id.rl_xgPhone,R.id.rl_pay_Password,R.id.rl_bank_bd,R.id.rl_xg_loginPass}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_xgPhone://修改手机号
                startActivity(new Intent(this,XGPhoneActivity.class)
                .putExtra("phone",phone));
                break;
            case R.id.rl_pay_Password://修改支付密码
//                startActivity(new Intent(this,XGPayPassWordActivity.class));
                startActivity(new Intent(this,XGPayPass2Activity.class));


                break;
            case R.id.rl_bank_bd://绑定银行卡
                startActivity(new Intent(this,BankCardBDActivity.class));
                break;
            case R.id.rl_xg_loginPass://修改登录密码
                startActivity(new Intent(this,AmendLoginPassActivity.class));

                break;

        }
    }
}
