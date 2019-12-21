package com.tem.gettogether.activity.login.forgetpassword;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tem.gettogether.activity.login.countrycode.CountryCodeActivity;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

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
 * date : 2019/10/24 10:20 
 */
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.ForgetPasswordView> implements ForgetPasswordContract.Presenter {
    private ForgetPasswordModel model;
    private Context mContext;
    private Activity mActivity;

    public ForgetPasswordPresenter(Context context, Activity mActivity) {
        model = new ForgetPasswordModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void phoneChangePas() {
        mView.phoneChangePas();
    }

    @Override
    public void emailChangePas() {
        mView.emailChangePas();
    }

    @Override
    public void toChooseCountryCode() {
        mActivity.startActivityForResult(new Intent(mActivity, CountryCodeActivity.class),100);
    }

    @Override
    public void submit(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.FIND_PASSWORD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====改密码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
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
                mView.hideLoading();
            }
        });
    }

    @Override
    public void upVerificationCode(String phone, String mobileCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("unique_id", "2");
        map.put("mobile_code", mobileCode);
        mView.showLoading();
        mView.codeTime();
        XUtil.Post(URLConstant.PHOEN_CODE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取验证码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {

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
                mView.hideLoading();
            }
        });
    }

    @Override
    public void upEmailCode(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("mail", email);
        map.put("unique_id", "2");
        mView.showLoading();
        mView.codeTime();
        XUtil.Post(URLConstant.EMAIL_CODE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====注册获取验证码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
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
                mView.hideLoading();
            }
        });
    }


}
