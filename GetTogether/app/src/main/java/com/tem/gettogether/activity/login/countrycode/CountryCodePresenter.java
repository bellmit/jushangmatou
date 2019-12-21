package com.tem.gettogether.activity.login.countrycode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CountryCodeBean;
import com.tem.gettogether.bean.LoginBean;
import com.tem.gettogether.bean.UserBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/29 17:04 
 */
public class CountryCodePresenter extends BasePresenter<CountryCodeContract.CountryCodeView> implements CountryCodeContract.Presenter {
    private CountryCodeModel model;
    private Context mContext;
    private Activity mActivity;
    List<CountryCodeBean.ResultBean.CountryBean> mDatas;
    public CountryCodePresenter(Context context, Activity mActivity) {
        model = new CountryCodeModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void getCountryCode() {
        Map<String, Object> map = new HashMap<>();
        map.put("language", SharedPreferencesUtils.getLanguageString(mContext, BaseConstant.SPConstant.language, ""));
        mView.showLoading();
        XUtil.Post(URLConstant.COUNTRY_CODE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                mView.hideLoading();
                Log.i("====获取地区code===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CountryCodeBean mCountryCodeBean = gson.fromJson(result, CountryCodeBean.class);
                        mView.countryCodeData(mCountryCodeBean.getResult().getCountry());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
