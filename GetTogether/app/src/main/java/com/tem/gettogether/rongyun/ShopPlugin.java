package com.tem.gettogether.rongyun;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.ShoppingKUActivity;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HeadMessageBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.IPluginRequestPermissionResultCallback;
import io.rong.imkit.utilities.PermissionCheckUtil;

/**
 * Created by lt on 2019-04-17.
 */

public class ShopPlugin implements IPluginModule {
    private Context mContext;

    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.shoppingku);
    }

    @Override
    public String obtainTitle(Context context) {
        this.mContext = context;
        return context.getString(R.string.shoppingku);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        String[] permissions = {Manifest.permission.RECORD_AUDIO};
        if (PermissionCheckUtil.checkPermissions(fragment.getActivity(), permissions)) {
            getUserMessage(fragment, rongExtension);
        } else {
            rongExtension.requestPermissionForPluginResult(permissions, IPluginRequestPermissionResultCallback.REQUEST_CODE_PERMISSION_PLUGIN, this);
        }
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }

    private void getUserMessage(final Fragment fragment, final RongExtension rongExtension) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", rongExtension.getTargetId());
        XUtil.Post(URLConstant.MESSAGE_HEAD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取头像信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        HeadMessageBean mHeadMessageBean = gson.fromJson(result, HeadMessageBean.class);
                        if (mHeadMessageBean.getResult().getRole_type().equals("1")) {// 1是供应商
                            if (mHeadMessageBean.getResult().getStore_id() != null && !mHeadMessageBean.getResult().getStore_id().equals("")) {
                                fragment.getActivity().startActivity(new Intent(fragment.getActivity(), ShoppingKUActivity.class)
                                        .putExtra("targetId", rongExtension.getTargetId())
                                        .putExtra("store_id", mHeadMessageBean.getResult().getStore_id()));
                            } else {
                                CusToast.showToast(mContext.getText(R.string.dpspkysx));
                            }
                        }
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
                ex.printStackTrace();
            }
        });
    }
}
