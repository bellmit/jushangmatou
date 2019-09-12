package com.tem.gettogether.activity.my.specificationsdetail;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.ybm.app.common.WindowToast.ToastTips;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specificationsdetail
 * @ClassName: SpecificationsDetailPresenter
 * @Author: csc
 * @CreateDate: 2019/9/9 15:37
 * @Description:
 */
public class SpecificationsDetailPresenter extends BasePresenter<SpecificationsDetailContract.SpecificationsDetailView> implements SpecificationsDetailContract.Presenter {
    private SpecificationsDetailModel model;
    private Context mContext;
    private Activity mActivity;

    public SpecificationsDetailPresenter(Context context, Activity mActivity) {
        model = new SpecificationsDetailModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    /*
     * 增加规格
     * */
    @Override
    public void addSpecifications(Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();

        XUtil.Post(URLConstant.SPECIFICARIONS_ADD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====增加规格===", result);
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        mView.specificationsListView();
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

    /*
     * 删除规格
     * */
    @Override
    public void deleteSpecifications(Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();

        XUtil.Post(URLConstant.SPECIFICARIONS_DELETE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====删除规格===", result);
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        mView.specificationsListView();
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