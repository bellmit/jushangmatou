package com.tem.gettogether.activity.login.phonelogin;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.translation.TranslationActivity;
import com.tem.gettogether.activity.translation.TranslationPresenter;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.utils.PayManager;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.view.IdentityDialog;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 * description ： 电话号码登录
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/23 13:41 
 */
@ContentView(R.layout.activity_third_phone_login)
public class PhoneLoginActivity extends BaseMvpActivity<PhoneLoginPresenter> implements PhoneLoginContract.PhoneLoginView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.phone_login_tv)
    private TextView phone_login_tv;
    @ViewInject(R.id.email_login_tv)
    private TextView email_login_tv;
    @ViewInject(R.id.ll_phone_num)
    private LinearLayout ll_phone_num;
    @ViewInject(R.id.ll_email)
    private LinearLayout ll_email;
    @ViewInject(R.id.et_phone_num)
    private EditText et_phone_num;
    @ViewInject(R.id.et_email)
    private EditText et_email;
    @ViewInject(R.id.et_password)
    private EditText et_password;
    @ViewInject(R.id.code_tv)
    private TextView code_tv;
    private CallbackManager mCallbackManager;
    private String mobileCode = "86";
    private String countryName = "中国";
    private int type = 0;
    private String fid = "";
    private String nickname = "";
    private String head_pic = "";

    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new PhoneLoginPresenter(getContext(), PhoneLoginActivity.this);
        mPresenter.attachView(this);
        tv_title.setText(R.string.denglu);
        PayManager.getPayManager().addActivity(this);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e("chenshichun", "--onSuccess---" + loginResult.getAccessToken());
                        getLoginInfo(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.e("chenshichun", "--onCancel---");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("chenshichun", "-onError----"+exception);
                    }
                });
    }

    @Event(value = {R.id.rl_close, R.id.phone_login_tv, R.id.email_login_tv, R.id.tv_new_user_registration,
            R.id.tv_forgetPwd, R.id.tv_login, R.id.ll_facebook_login, R.id.ll_wechat_login, R.id.code_tv})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.phone_login_tv:// 手机号登录
                mPresenter.phoneLogin();
                type = 0;
                break;
            case R.id.email_login_tv:// 邮箱登录
                mPresenter.emailLogin();
                type = 1;
                break;
            case R.id.tv_new_user_registration:// 新用户注册
                mPresenter.newUserRegistration();
                break;
            case R.id.tv_forgetPwd:// 忘记密码
                mPresenter.forgetPassword();
                break;
            case R.id.tv_login:// 登录
                Map<String, Object> map = new HashMap<>();
                if (type == 0) {
                    map.put("username", et_phone_num.getText().toString());
                } else {
                    map.put("username", et_email.getText().toString());
                }
                map.put("type", "0");
                map.put("class", type);// 手机登录，没有验证码登录写死0
                map.put("password", et_password.getText().toString());
                map.put("unique_id", 1);
                mPresenter.login(map);
                break;
            case R.id.ll_facebook_login:// facebook登录
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                break;
            case R.id.ll_wechat_login:// 微信登录
                mPresenter.wechatLogin();
                break;
            case R.id.code_tv:// 选择国家区号
                mPresenter.toChooseCountryCode();
                break;
        }
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
    public void phoneLogin() {// 手机号登录
        et_phone_num.setText("");
        et_email.setText("");
        et_password.setText("");
        phone_login_tv.setTextColor(getResources().getColor(R.color.black));
        email_login_tv.setTextColor(getResources().getColor(R.color.text6));
        ll_email.setVisibility(View.GONE);
        ll_phone_num.setVisibility(View.VISIBLE);
        setDrawableLeftPic(phone_login_tv, email_login_tv);
    }

    @Override
    public void emailLogin() {// 邮箱登录
        et_phone_num.setText("");
        et_email.setText("");
        et_password.setText("");
        phone_login_tv.setTextColor(getResources().getColor(R.color.text6));
        email_login_tv.setTextColor(getResources().getColor(R.color.black));
        ll_email.setVisibility(View.VISIBLE);
        ll_phone_num.setVisibility(View.GONE);
        setDrawableLeftPic(email_login_tv, phone_login_tv);
    }

    @Override
    public void showIdentityDialog() {
        IdentityDialog mIdentityDialog = new IdentityDialog.Builder(mContext)
                .setBuyersClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("openid", "");
                        map.put("fid", fid);
                        map.put("role_type", 0);
                        map.put("from", "facebook");//登陆类型
                        map.put("nickname", nickname);
                        map.put("head_pic", head_pic);
                        map.put("mobile_validated", 0);
                        mPresenter.thirdLogin(map);
                    }
                }).setSupplierClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("chenshichun", "---setSupplierClick--");
                        Map<String, Object> map = new HashMap<>();
                        map.put("openid", "");
                        map.put("fid", fid);
                        map.put("role_type", 1);
                        map.put("from", "facebook");//登陆类型
                        map.put("nickname", nickname);
                        map.put("head_pic", head_pic);
                        map.put("mobile_validated", 0);
                        mPresenter.thirdLogin(map);
                    }
                }).setCloseClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .create();
        mIdentityDialog.show();
    }

    @Override
    public void thirdRoleTypeLogin(String roleType) {
        Map<String, Object> map = new HashMap<>();
        map.put("openid", "");
        map.put("fid", fid);
        map.put("role_type", roleType);
        map.put("from", "facebook");//登陆类型
        map.put("nickname", nickname);
        map.put("head_pic", head_pic);
        map.put("mobile_validated", 0);
        mPresenter.thirdLogin(map);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 3) {
            mobileCode = data.getStringExtra("MOBILE_CODE");
            countryName = data.getStringExtra("COUNTRY_NAME");
            code_tv.setText(countryName + "(+" + mobileCode + ")");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getLoginInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    fid = object.optString("id");   //比如:1565455221565
                    nickname = object.optString("name");  //比如：Zhang San
                    String gender = object.optString("gender");  //性别：比如 male （男）  female （女）
                    String emali = object.optString("email");  //邮箱：比如：56236545@qq.com
                    //获取用户头像
                    JSONObject object_pic = object.optJSONObject("picture");
                    JSONObject object_data = object_pic.optJSONObject("data");
                    head_pic = object_data.optString("url");
                    //获取地域信息
                    String locale = object.optString("locale");   //zh_CN 代表中文简体

                    Map<String, Object> map = new HashMap<>();
                    map.put("fid", fid);
                    map.put("openid", "");
                    mPresenter.facebookWxCheck(map);
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time,timezone,age_range,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
