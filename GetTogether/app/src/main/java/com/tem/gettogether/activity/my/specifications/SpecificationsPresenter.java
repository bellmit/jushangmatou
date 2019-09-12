package com.tem.gettogether.activity.my.specifications;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specifications
 * @ClassName: SpecificationsPresenter
 * @Author: csc
 * @CreateDate: 2019/9/9 11:34
 * @Description:
 */
public class SpecificationsPresenter extends BasePresenter<SpecificationsContract.SpecificationsView> implements SpecificationsContract.Presenter {

    private SpecificationsModel model;
    private Context mContext;
    private Activity mActivity;

    public SpecificationsPresenter(Context context, Activity mActivity) {
        model = new SpecificationsModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void getSpecificationsData(Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();

        XUtil.Post(URLConstant.SPECIFICARIONS_DATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====规格数据===", result);
                mView.hideLoading();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        SpecificationsBean fenLieBean = gson.fromJson(result, SpecificationsBean.class);
                        mView.getSpecificationsData(fenLieBean.getResult());
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