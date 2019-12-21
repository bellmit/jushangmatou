package com.tem.gettogether.activity.my.authentication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.AuthenticationBean;
import com.tem.gettogether.bean.PersonagerMessageBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/4 15:22 
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.AuthenticationView> implements AuthenticationContract.Presenter {
    private AuthenticationModel model;
    private Context mContext;
    private Activity mActivity;
    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans = new ArrayList<>();// 店铺主营大类数据
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans = new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans = new ArrayList<>();

    public AuthenticationPresenter(Context context, Activity mActivity) {
        model = new AuthenticationModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void getData() {
        mView.showLoading();
        Log.e("chenshichun", "--getData---");
        XUtil.Post(URLConstant.GRXINXLIEB, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "===个人信息选择列表--" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        PersonagerMessageBean personagerMessageBean = gson.fromJson(result, PersonagerMessageBean.class);
                        mView.getData(personagerMessageBean);
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
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mView.onError(ex);
            }
        });
    }

    @Override
    public void upGetAddressData(String parent_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", parent_id);
        mView.showLoading();
        XUtil.Post(URLConstant.GET_ADDRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取地区===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AddressDataBean addressDataBean = gson.fromJson(result, AddressDataBean.class);
                        mView.getAddressData(addressDataBean.getResult());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                mView.hideLoading();
                mView.addressData();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mView.onError(ex);

            }
        });
    }


    @Override
    public void saveEnterprisePersonalStore(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.ENTERPRICE_PERSION_STORE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "---认证--" + result);
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
                ex.printStackTrace();
                mView.onError(ex);
            }
        });
    }

    @Override
    public void getAuthenticationData(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.AUTHENTICATION_DATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "---获取店铺认证资料接口--" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AuthenticationBean mAuthenticationBean = gson.fromJson(result, AuthenticationBean.class);
                        mView.setRejectionInformation(mAuthenticationBean.getResult());
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
                ex.printStackTrace();
                mView.onError(ex);
            }
        });
    }

    @Override
    public void upGetRzFailedData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(mContext, BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(mContext, BaseConstant.SPConstant.USERID, ""));

        mView.showLoading();
        XUtil.Post(URLConstant.RZ_FAILED, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====驳回原因===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        mView.setRejectionReason(jsonObject.optString("result"));
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
                ex.printStackTrace();
                mView.onError(ex);
            }
        });
    }

    @Override
    public void showDistributor() {
        mView.showDistributor();
    }

    @Override
    public void showFactory() {
        mView.showFactory();
    }

    @Override
    public void showPersonal() {
        mView.showPersonal();
    }

}
