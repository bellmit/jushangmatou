package com.tem.gettogether.activity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.bean.ShopInformationBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_my_shop)
public class MyShopActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_name)
    private TextView et_name;
    @ViewInject(R.id.tv_dpzy)
    private TextView tv_dpzy;
    @ViewInject(R.id.et_dpzzh)
    private TextView et_dpzzh;
    @ViewInject(R.id.et_dpAddress)
    private TextView et_dpAddress;
    @ViewInject(R.id.et_card_num)
    private TextView et_card_num;
    @ViewInject(R.id.iv_image_1)
    private ImageView iv_image_1;
    @ViewInject(R.id.iv_image_2)
    private ImageView iv_image_2;
    @ViewInject(R.id.iv_image_3)
    private ImageView iv_image_3;
    @ViewInject(R.id.iv_image_4)
    private ImageView iv_image_4;
    @ViewInject(R.id.iv_image_5)
    private ImageView iv_image_5;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getResources().getText(R.string.my_shop));
        upGetMessageData();
    }

    @Override
    protected void initView() {

    }


    private void upGetMessageData() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("user_id", BaseApplication.getInstance().userBean.getUser_id());
        map.put("store_status", 1);
        XUtil.Post(URLConstant.SHOP_INFORMATION, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取店铺信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ShopInformationBean mShopInformationBean = gson.fromJson(result, ShopInformationBean.class);
                        et_name.setText(mShopInformationBean.getResult().get(0).getStore_name());
                        tv_dpzy.setText(mShopInformationBean.getResult().get(0).getSc_name());
                        et_dpzzh.setText(mShopInformationBean.getResult().get(0).getSeller_name());
                        et_dpAddress.setText(mShopInformationBean.getResult().get(0).getStore_province()
                                + mShopInformationBean.getResult().get(0).getStore_city()
                                + mShopInformationBean.getResult().get(0).getStore_district()
                                + mShopInformationBean.getResult().get(0).getStore_address());
                        et_card_num.setText(mShopInformationBean.getResult().get(0).getLegal_identity());
                        if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) != null) {
                            Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_image_1));
                        }
                        if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) != null) {
                            Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_image_2));
                        }
                        if (mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) != null) {
                            Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_image_3));
                        }
                        if (mShopInformationBean.getResult().get(0).getFactory_scene().get(0) != null) {
                            Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getFactory_scene().get(0) + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_image_4));
                        }
                        if (mShopInformationBean.getResult().get(0).getFactory_scene().get(1) != null) {
                            Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getFactory_scene().get(1) + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_image_5));
                        }
                        Log.d("chenshichun", "===========end");
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

