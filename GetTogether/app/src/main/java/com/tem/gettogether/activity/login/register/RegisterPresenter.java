package com.tem.gettogether.activity.login.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tem.gettogether.activity.RegisterActivity;
import com.tem.gettogether.activity.login.countrycode.CountryCodeActivity;
import com.tem.gettogether.activity.my.XeiYiH5Activity;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.xutils3.CountDownTimerUtils3;
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
 * date : 2019/10/23 16:49 
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView> implements RegisterContract.Presenter {
    private RegisterModel model;
    private Context mContext;
    private Activity mActivity;

    public RegisterPresenter(Context context, Activity mActivity) {
        model = new RegisterModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void phoneRegister() {
        mView.phoneRegister();
    }

    @Override
    public void emailRegister() {
        mView.emailRegister();
    }

    @Override
    public void upVerificationCode(String phone, String mobileCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("unique_id", "0");
        map.put("mobile_code", mobileCode);
        mView.showLoading();
        mView.codeTime();
        XUtil.Post(URLConstant.PHOEN_CODE, map, new MyCallBack<String>() {
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

    @Override
    public void upCodeZHUCE() {
        XUtil.Post(URLConstant.ZHUCEXIYEYI, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====注册协议===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject2 = new JSONObject(result2);
                        String url = jsonObject2.optString("url");
                        mActivity.startActivity(new Intent(mActivity, XeiYiH5Activity.class)
                                .putExtra("typeMain", "2")
                                .putExtra("h5url", url));
                    } else {
                        CusToast.showToast(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public void upEmailCode(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("mail", email);
        map.put("unique_id", "0");
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

    @Override
    public void chooseStrategicAlliance() {
        mView.chooseStrategicAlliance();
    }

    @Override
    public void toChooseCountryCode() {
        mActivity.startActivityForResult(new Intent(mActivity, CountryCodeActivity.class), 100);
    }

    @Override
    public void register(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.PHONE_EMAIL_REGISTER, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====注册===", result);
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

}
