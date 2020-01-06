package com.tem.gettogether.activity.login.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.ServiceProviderActivity;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.CountDownTimerUtils3;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： 注册
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/23 16:49 
 */
@ContentView(R.layout.activity_third_registered)
public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.RegisterView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.phone_register_tv)
    private TextView phone_register_tv;
    @ViewInject(R.id.email_register_tv)
    private TextView email_register_tv;
    @ViewInject(R.id.ll_phone_num)
    private LinearLayout ll_phone_num;
    @ViewInject(R.id.ll_email)
    private LinearLayout ll_email;
    @ViewInject(R.id.et_phone_num)
    private EditText et_phone_num;
    @ViewInject(R.id.et_email)
    private EditText et_email;
    @ViewInject(R.id.get_verification_code_tv)
    private TextView get_verification_code_tv;
    @ViewInject(R.id.zhanlvlianmeng_tv)
    private TextView zhanlvlianmeng_tv;
    @ViewInject(R.id.code_tv)
    private TextView code_tv;
    @ViewInject(R.id.register_tv)
    private TextView register_tv;
    @ViewInject(R.id.quyers_rb)
    private RadioButton quyers_rb;
    @ViewInject(R.id.supplier_rb)
    private RadioButton supplier_rb;
    @ViewInject(R.id.ll_zhanlvlianmeng)
    private LinearLayout ll_zhanlvlianmeng;
    @ViewInject(R.id.nickname_tv)
    private EditText nickname_tv;
    @ViewInject(R.id.et_passworld)
    private EditText et_passworld;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @ViewInject(R.id.verification_code_tv)
    private EditText verification_code_tv;
    private String companyName;
    private String companyId;
    private String mobileCode = "86";
    private String countryName = "中国";
    private int type = 0;
    @ViewInject(R.id.checkbox)
    private CheckBox checkbox;
    @ViewInject(R.id.tv_xieyi)
    private TextView tv_xieyi;
    private CountDownTimerUtils3 mCountDownTimerUtils;
    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new RegisterPresenter(getContext(), RegisterActivity.this);
        mPresenter.attachView(this);
        tv_title.setText(R.string.zhuche);
        checkbox.setChecked(true);
        supplier_rb.setChecked(true);
        ll_zhanlvlianmeng.setVisibility(View.GONE);
         mCountDownTimerUtils = new CountDownTimerUtils3(get_verification_code_tv, 60000, 1000);
    }

    @Event(value = {R.id.rl_close, R.id.phone_register_tv, R.id.email_register_tv,
            R.id.get_verification_code_tv, R.id.ll_zhanlvlianmeng, R.id.code_tv,
            R.id.register_tv, R.id.quyers_rb, R.id.supplier_rb,R.id.tv_xieyi})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.phone_register_tv:
                mPresenter.phoneRegister();
                type = 0;
                break;
            case R.id.email_register_tv:
                mPresenter.emailRegister();
                type = 1;
                break;
            case R.id.get_verification_code_tv:
                if (type == 0) {
                    if (et_phone_num.getText().toString().length() < 11) {
                        CusToast.showToast(getText(R.string.please_enter_the_full_mobile_number));
                        return;
                    }
                    mPresenter.upVerificationCode(et_phone_num.getText().toString(), mobileCode);
                } else {
                    if (et_email.getText().toString().length() < 11) {
                        CusToast.showToast(R.string.please_enter_full_email_address);
                        return;
                    }
                    mPresenter.upEmailCode(et_email.getText().toString());
                }
                break;
            case R.id.ll_zhanlvlianmeng:
                mPresenter.chooseStrategicAlliance();
                break;
            case R.id.code_tv:
                mPresenter.toChooseCountryCode();
                break;
            case R.id.quyers_rb:
                if (quyers_rb.isChecked()) {
                    ll_zhanlvlianmeng.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.supplier_rb:
                if (supplier_rb.isChecked()) {
                    ll_zhanlvlianmeng.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_xieyi:
                mPresenter.upCodeZHUCE();
                break;
            case R.id.register_tv:
                if (nickname_tv.getText().toString().length() < 1) {
                    CusToast.showToast(R.string.fill_in_nickname);
                    return;
                }
                if (type == 0) {
                    if (et_phone_num.getText().toString().length() < 11) {
                        CusToast.showToast(getText(R.string.please_enter_the_full_mobile_number));
                        return;
                    }
                } else {
                    if (et_email.getText().toString().length() < 1) {
                        CusToast.showToast(R.string.fill_in_full_mailbox);
                        return;
                    }
                }

                if (verification_code_tv.getText().toString().length() < 4) {
                    CusToast.showToast(R.string.please_enter_correct_verify_code);
                    return;
                }

                if (et_passworld.getText().toString().length() < 6) {
                    CusToast.showToast(getText(R.string.please_enter_your_password_tv));
                    return;
                }

                if (checkbox.isChecked() == false) {
                    CusToast.showToast(getText(R.string.please_read_the_service_agreement_and_agree));
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("nickname", nickname_tv.getText().toString());
                if (type == 0) {
                    map.put("mobile_code", mobileCode);
                    map.put("username", et_phone_num.getText().toString());
                } else {
                    map.put("username", et_email.getText().toString());
                }
                map.put("class", type);// 0：手机 1：邮箱
                map.put("code", verification_code_tv.getText().toString());
                map.put("role_type", quyers_rb.isChecked() ? 0 : 1);
                map.put("companyId", companyId);
                map.put("password", et_passworld.getText().toString());
                map.put("unique_id", 0);
                map.put("openid", "");
                mPresenter.register(map);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            companyName = bundle.getString("companyName");
            companyId = bundle.getString("companyId");
            zhanlvlianmeng_tv.setText(companyName);
        }
        if (requestCode == 100 && resultCode == 3) {
            mobileCode = data.getStringExtra("MOBILE_CODE");
            countryName = data.getStringExtra("COUNTRY_NAME");
            code_tv.setText(countryName + "+(" + mobileCode + ")");
        }
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void phoneRegister() {// 电话号码注册
        phone_register_tv.setTextColor(getResources().getColor(R.color.black));
        email_register_tv.setTextColor(getResources().getColor(R.color.text6));
        ll_email.setVisibility(View.GONE);
        ll_phone_num.setVisibility(View.VISIBLE);
        setDrawableLeftPic(phone_register_tv, email_register_tv);
        mCountDownTimerUtils.onCancel();
    }

    @Override
    public void emailRegister() {// 邮箱注册
        phone_register_tv.setTextColor(getResources().getColor(R.color.text6));
        email_register_tv.setTextColor(getResources().getColor(R.color.black));
        ll_email.setVisibility(View.VISIBLE);
        ll_phone_num.setVisibility(View.GONE);
        setDrawableLeftPic(email_register_tv, phone_register_tv);
        mCountDownTimerUtils.onCancel();
    }

    @Override
    public void codeTime() {
        mCountDownTimerUtils.start();
    }

    @Override
    public void chooseStrategicAlliance() {
        Intent intent = new Intent();
        intent.setClass(this, ServiceProviderActivity.class);
        startActivityForResult(intent, 0);
    }

    private void setDrawableLeftPic(TextView view, TextView view1) {
        Drawable drawableLeft = getResources().getDrawable(
                R.drawable.subscript_icon);
        view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                null, null, null);
        view1.setCompoundDrawablesWithIntrinsicBounds(null,
                null, null, null);
        view.setCompoundDrawablePadding(10);
    }

}
