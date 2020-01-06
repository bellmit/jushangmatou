package com.tem.gettogether.activity.login.forgetpassword;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.login.phonelogin.PhoneLoginActivity;
import com.tem.gettogether.activity.login.phonelogin.PhoneLoginPresenter;
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
 * description ： 忘记密码
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/24 10:20 
 */
@ContentView(R.layout.activity_third_forget_password)
public class ForgetPasswordActivity extends BaseMvpActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.ForgetPasswordView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_phone_num)
    private LinearLayout ll_phone_num;
    @ViewInject(R.id.ll_email)
    private LinearLayout ll_email;
    @ViewInject(R.id.phone_change_pas_tv)
    private TextView phone_change_pas_tv;
    @ViewInject(R.id.email_change_pas_tv)
    private TextView email_change_pas_tv;
    @ViewInject(R.id.code_tv)
    private TextView code_tv;
    @ViewInject(R.id.et_phone_num)
    private EditText et_phone_num;
    @ViewInject(R.id.et_email)
    private EditText et_email;
    @ViewInject(R.id.et_new_pass)
    private EditText et_new_pass;
    @ViewInject(R.id.et_new_pass2)
    private EditText et_new_pass2;
    @ViewInject(R.id.verification_code_tv)
    private EditText verification_code_tv;
    @ViewInject(R.id.get_verification_code_tv)
    private TextView get_verification_code_tv;
    private String mobileCode = "86";
    private String countryName = "中国";
    private int type = 0;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new ForgetPasswordPresenter(getContext(), ForgetPasswordActivity.this);
        mPresenter.attachView(this);
        tv_title.setText(R.string.forget_pass);
    }

    @Event(value = {R.id.rl_close, R.id.phone_change_pas_tv, R.id.email_change_pas_tv,
            R.id.code_tv, R.id.submit_tv, R.id.get_verification_code_tv})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.phone_change_pas_tv:
                mPresenter.phoneChangePas();
                type = 0;
                break;
            case R.id.email_change_pas_tv:
                mPresenter.emailChangePas();
                type = 1;
                break;
            case R.id.code_tv:
                mPresenter.toChooseCountryCode();
                break;
            case R.id.submit_tv:
                Map<String, Object> map = new HashMap<>();
                if (type == 0) {
                    map.put("username", et_phone_num.getText().toString());
                } else {
                    map.put("username", et_email.getText().toString());
                }
                map.put("class", type);// 手机登录，没有验证码登录写死0
                map.put("code", verification_code_tv.getText().toString());
                map.put("new_password", et_new_pass.getText().toString());
                map.put("confirm_password", et_new_pass2.getText().toString());
                mPresenter.submit(map);
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
        showDialog();
    }

    @Override
    public void hideLoading() {
        closeDialog();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void phoneChangePas() {
        et_phone_num.setText("");
        et_email.setText("");
        verification_code_tv.setText("");
        et_new_pass.setText("");
        et_new_pass2.setText("");
        phone_change_pas_tv.setTextColor(getResources().getColor(R.color.black));
        email_change_pas_tv.setTextColor(getResources().getColor(R.color.text6));
        ll_email.setVisibility(View.GONE);
        ll_phone_num.setVisibility(View.VISIBLE);
        setDrawableLeftPic(phone_change_pas_tv, email_change_pas_tv);
    }

    @Override
    public void emailChangePas() {
        phone_change_pas_tv.setTextColor(getResources().getColor(R.color.text6));
        email_change_pas_tv.setTextColor(getResources().getColor(R.color.black));
        ll_email.setVisibility(View.VISIBLE);
        ll_phone_num.setVisibility(View.GONE);
        setDrawableLeftPic(email_change_pas_tv, phone_change_pas_tv);
    }

    @Override
    public void codeTime() {
        CountDownTimerUtils3 mCountDownTimerUtils = new CountDownTimerUtils3(get_verification_code_tv, 60000, 1000);
        mCountDownTimerUtils.start();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 3) {
            mobileCode = data.getStringExtra("MOBILE_CODE");
            countryName = data.getStringExtra("COUNTRY_NAME");
            code_tv.setText(countryName + "+(" + mobileCode + ")");
        }
    }
}
