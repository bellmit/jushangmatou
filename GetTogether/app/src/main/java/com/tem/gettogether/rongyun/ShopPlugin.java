package com.tem.gettogether.rongyun;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.ShoppingKUActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongExtension;
import io.rong.imkit.RongIM;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.IPluginRequestPermissionResultCallback;
import io.rong.imkit.utilities.PermissionCheckUtil;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.RichContentMessage;

/**
 * Created by lt on 2019-04-17.
 */

public class ShopPlugin  implements IPluginModule  {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.shoppingku);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.shoppingku);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        String[] permissions = {Manifest.permission.RECORD_AUDIO};
        if (PermissionCheckUtil.checkPermissions(fragment.getActivity(), permissions)) {
           String  store_id=SharedPreferencesUtils.getString(BaseApplication.getInstance().mInstance, BaseConstant.SPConstant.Shop_store_id,"");

                if(store_id!=null&&!store_id.equals("")){
                    fragment.getActivity().startActivity(new Intent(fragment.getActivity(),ShoppingKUActivity.class)
                            .putExtra("targetId",rongExtension.getTargetId())
                    .putExtra("store_id",store_id));
                }else{
                    CusToast.showToast("店铺商品库已失效");
                }

        } else {
            rongExtension.requestPermissionForPluginResult(permissions, IPluginRequestPermissionResultCallback.REQUEST_CODE_PERMISSION_PLUGIN, this);
        }
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
