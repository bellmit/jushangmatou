package com.tem.gettogether.activity.my.decoration;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeHotSellBean;
import com.tem.gettogether.bean.ShopDecorationBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.decoration
 * @ClassName: ShopDecorationPresenter
 * @Author: csc
 * @CreateDate: 2019/9/17 11:12
 * @Description:
 */
public class ShopDecorationPresenter extends BasePresenter<ShopDecorationContract.ShopDecorationView> implements ShopDecorationContract.Presenter {
    private ShopDecorationModel model;
    private Context mContext;
    private Activity mActivity;

    public ShopDecorationPresenter(Context context, Activity mActivity) {
        model = new ShopDecorationModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void getShopDecorationData(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.SHOP_DECORATION, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===店铺装修===", result.toString());
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        mView.getShopDecorationData(gson.fromJson(result, ShopDecorationBean.class).getResult());
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
                Log.d("chenshichun", "===========" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void getShopDecorationModifyData(Map<String, Object> map) {
        mView.showLoading();
        XUtil.Post(URLConstant.SHOP_DECORATION_MODIFY, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===店铺装修修改===", result.toString());
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    Log.d("chenshichun","=======msg===="+msg);
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
                Log.d("chenshichun", "===========" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

}
