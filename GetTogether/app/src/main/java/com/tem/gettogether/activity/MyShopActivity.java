package com.tem.gettogether.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.bean.ShopInformationBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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
    @ViewInject(R.id.except_company_ll)
    private LinearLayout except_company_ll;
    @ViewInject(R.id.except_persion_ll)
    private LinearLayout except_persion_ll;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;

    private String apply_type;//0企业2工厂

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        tv_title.setText(getResources().getText(R.string.my_shop));
        upGetMessageData();
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void upGetMessageData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
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
                        et_card_num.setText(mShopInformationBean.getResult().get(0).getStore_person_identity());
                        apply_type = mShopInformationBean.getResult().get(0).getApply_type();
                        if (apply_type.equals("1")) {// 个人
                            except_persion_ll.setVisibility(View.GONE);
                            if (mShopInformationBean.getResult().get(0).getStore_person_cert().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getStore_person_cert().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
                            }
                            if (mShopInformationBean.getResult().get(0).getStore_person_cert().get(1) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getStore_person_cert().get(1) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
                            }
                        } else if (apply_type.equals("0")) {//公司
                            except_company_ll.setVisibility(View.GONE);
                            if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
                            }

                            if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
                            }

                            if (mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_3);
                            }

                        } else if (apply_type.equals("2")) {// 工厂
                            if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
                            }
                            if (mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getLegal_identity_cert().get(1) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
                            }

                            if (mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getBusiness_licence_cert().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_3);
                            }

                            if (mShopInformationBean.getResult().get(0).getFactory_scene().get(0) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getFactory_scene().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_4);
                            }
                            if (mShopInformationBean.getResult().get(0).getFactory_scene().get(1) != null) {
                                Glide.with(getContext()).load(mShopInformationBean.getResult().get(0).getFactory_scene().get(1) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_5);
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

