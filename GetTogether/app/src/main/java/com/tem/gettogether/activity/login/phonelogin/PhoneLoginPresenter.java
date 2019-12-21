package com.tem.gettogether.activity.login.phonelogin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.activity.login.countrycode.CountryCodeActivity;
import com.tem.gettogether.activity.login.forgetpassword.ForgetPasswordActivity;
import com.tem.gettogether.activity.login.register.RegisterActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.LoginBean;
import com.tem.gettogether.bean.LoginStatusBean;
import com.tem.gettogether.bean.UserBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.IdentityDialog;
import com.tem.gettogether.wxapi.WXEntryActivity;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/23 13:46 
 */
public class PhoneLoginPresenter extends BasePresenter<PhoneLoginContract.PhoneLoginView> implements PhoneLoginContract.Presenter {
    private PhoneLoginModel model;
    private Context mContext;
    private Activity mActivity;
    private IWXAPI api;
    private String APP_ID = "wxa6f24ff3369c8d21";

    public PhoneLoginPresenter(Context context, Activity mActivity) {
        model = new PhoneLoginModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void phoneLogin() {
        mView.phoneLogin();
    }

    @Override
    public void emailLogin() {
        mView.emailLogin();
    }

    @Override
    public void newUserRegistration() {// 新用户注册
        mContext.startActivity(new Intent(mActivity, RegisterActivity.class));
    }

    @Override
    public void toChooseCountryCode() {
        mActivity.startActivityForResult(new Intent(mActivity, CountryCodeActivity.class), 100);
    }

    @Override
    public void forgetPassword() {// 忘记密码
        mContext.startActivity(new Intent(mActivity, ForgetPasswordActivity.class));
    }

    @Override
    public void login(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.PHONE_EMAIL_LOGIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                mView.hideLoading();
                Log.e("chenshichun","--登录---"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(result, LoginBean.class);
//                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.PAYPASSWORD, pass);
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.TYPE, "0");
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.ROLE_TYPE, loginBean.getResult().getRole_type());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.USERID, loginBean.getResult().getUser_id());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.LEVER, loginBean.getResult().getLevel());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.head_pic, loginBean.getResult().getHead_pic());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.MOBILEPHONE, loginBean.getResult().getMobile());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.CHAT_ID, loginBean.getResult().getChat_id());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.NAME, loginBean.getResult().getNickname());
                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        userBean.setRole_type(loginBean.getResult().getRole_type());
                        userBean.setUser_id(loginBean.getResult().getUser_id());
                        userBean.setHead_pic(loginBean.getResult().getHead_pic());
                        userBean.setLever(loginBean.getResult().getLevel());
                        BaseApplication.getInstance().userBean = userBean;
                        mContext.startActivity(new Intent(mActivity, MainActivity.class).putExtra("LOGIN_TO_MIAN", 1));
                        Intent intent = new Intent();
                        intent.setAction("LOGIN_TO_MIAN");
                        mContext.sendBroadcast(intent);
                        mActivity.finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void facebookLogin() {// facebook登录

    }

    @Override
    public void wechatLogin() {// 微信登录
        if (api == null) {
            api = WXAPIFactory.createWXAPI(mContext, APP_ID, true);
            api.registerApp(APP_ID);
        }
        if (!api.isWXAppInstalled()) {
            //提醒用户没有安裝微信
            CusToast.showToast(R.string.uninstalled_weChat);
            return;
        }
        WXTextObject textObject = new WXTextObject();
        textObject.text = "text";
        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = textObject;
        message.description = "text";
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @Override
    public void facebookWxCheck(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.FACEBOOK_WX_CHECK, map, new MyCallBack<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(String result) {
                mView.hideLoading();
                try {
                    Log.e("chenshichun", "--微信facebook第三方登录状态检查接口---" + result);
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        LoginStatusBean mLoginStatusBean = gson.fromJson(result, LoginStatusBean.class);
                        if(mLoginStatusBean.getResult().getFacebook_status().equals("1")){
                            mView.thirdRoleTypeLogin(mLoginStatusBean.getResult().getRole_type());
                        }else{
                            mView.showIdentityDialog();
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                super.onSuccess(result);
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                super.onError(ex, isOnCallback);
            }
        });
    }

    @Override
    public void thirdLogin(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.DISANF_LOGIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "---facebook三方登录---" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.optString("msg");
                    String res = jsonObject.optString("status");
                    CusToast.showToast(msg);
                    if ("1".equals(res)) {
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        userBean.setUser_id(loginBean.getResult().getUser_id());
                        userBean.setHead_pic(loginBean.getResult().getHead_pic());
                        userBean.setLever(loginBean.getResult().getLevel());
                        BaseApplication.getInstance().userBean = userBean;
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.head_pic, loginBean.getResult().getHead_pic());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.openid, loginBean.getResult().getOpenid());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.NAME, loginBean.getResult().getNickname());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.USERID, loginBean.getResult().getUser_id());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.LEVER, loginBean.getResult().getLevel());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.ROLE_TYPE, loginBean.getResult().getRole_type());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.CHAT_ID, loginBean.getResult().getChat_id());
                        SharedPreferencesUtils.saveString(mActivity, BaseConstant.SPConstant.MOBILE_AVLIDATED, loginBean.getResult().getMobile_validated());
                        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                        mActivity.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mView.onError(ex);
                ex.printStackTrace();

            }
        });
    }
}
