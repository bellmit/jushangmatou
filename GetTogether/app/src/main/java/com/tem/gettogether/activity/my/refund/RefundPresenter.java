package com.tem.gettogether.activity.my.refund;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.bean.RefundAmountBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/22 14:49 
 */
public class RefundPresenter extends BasePresenter<RefundContract.RefundView> implements RefundContract.Presenter {
    private Context mContext;
    private Activity mActivity;

    public RefundPresenter(Context context, Activity mActivity) {
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void refundSave(String url, Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();

        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====退款结果===", result);
                mView.hideLoading();
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
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void refundAmount(Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();

        XUtil.Post(URLConstant.GET_REFUND_AMOUNT, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取金额===", result);
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        RefundAmountBean  refundAmountBean = gson.fromJson(result, RefundAmountBean.class);
                        mView.getRefundAmount(refundAmountBean.getResult());
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
                ex.printStackTrace();
            }
        });
    }


}
