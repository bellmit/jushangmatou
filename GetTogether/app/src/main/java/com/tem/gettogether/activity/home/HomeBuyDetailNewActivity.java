package com.tem.gettogether.activity.home;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.WaiMaoQiuGouBean;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;


@ContentView(R.layout.activity_home_buy_detail)
public class HomeBuyDetailNewActivity extends BaseActivity {
    @ViewInject(R.id.user_name_tv)
    private TextView user_name_tv;
    private String trade_id;
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.type_tv)
    private TextView type_tv;
    @ViewInject(R.id.country_tv)
    private TextView country_tv;
    @ViewInject(R.id.product_title)
    private TextView product_title;
    @ViewInject(R.id.delivery_time_tv)
    private TextView delivery_time_tv;
    @ViewInject(R.id.num_tv)
    private TextView num_tv;
    @ViewInject(R.id.release_time_tv)
    private TextView release_time_tv;
    @ViewInject(R.id.head_iv)
    private ImageView head_iv;
    @ViewInject(R.id.view_other_tv)
    private TextView view_other_tv;
    @ViewInject(R.id.online_communication_tv)
    private TextView online_communication_tv;


    private List<WaiMaoQiuGouBean.ResultEntity> waiMaoQiuGouBeans = new ArrayList<>();

    @Override
    protected void initData() {
        x.view().inject(this);
        trade_id = getIntent().getStringExtra("trade_id");
        initDatas();
    }

    @Override
    protected void initView() {

    }

    private void initDatas() {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        map.put("trade_id", trade_id);
        Log.d("chenshichun", "======trade_id=====  " + trade_id);
        showDialog();
        XUtil.Post(URLConstant.HOMEQIUGOUDETAIL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        WaiMaoQiuGouBean waiMaoQiuGouBean = gson.fromJson(result, WaiMaoQiuGouBean.class);
                        waiMaoQiuGouBeans = waiMaoQiuGouBean.getResult();
                        initViews();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private void initViews() {
        type_tv.setText(waiMaoQiuGouBeans.get(0).getRelease_type());
        user_name_tv.setText(getResources().getText(R.string.user_name) + "" + waiMaoQiuGouBeans.get(0).getMobile());
        country_tv.setText("出口国家：" + waiMaoQiuGouBeans.get(0).getCountry_name());
        product_title.setText("" + waiMaoQiuGouBeans.get(0).getGoods_name());
        delivery_time_tv.setText("交货时间：" + waiMaoQiuGouBeans.get(0).getAttach_time());
        num_tv.setText("求购数量：" + waiMaoQiuGouBeans.get(0).getGoods_num());
        release_time_tv.setText("发布时间：" + waiMaoQiuGouBeans.get(0).getAdd_time());

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(waiMaoQiuGouBeans.get(0).getGoods_logo());
        banner.start();

        online_communication_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (BaseApplication.getInstance().userBean != null) {
                        if (BaseApplication.getInstance().userBean.getChat_id() != null && !BaseApplication.getInstance().userBean.getChat_id().equals("")) {

                            if (waiMaoQiuGouBeans != null && waiMaoQiuGouBeans.get(0).getUser_id() != null) {
                                RongTalk.doConnection(HomeBuyDetailNewActivity.this, BaseApplication.getInstance().userBean.getChat_id()
                                        , waiMaoQiuGouBeans.get(0).getUser_id(), "",
                                        "", "");
                            } else {
                                CusToast.showToast("该店铺无效");
                            }
                        }
                    } else {
                        CusToast.showToast("token失效");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CusToast.showToast("该店铺无效");
                }
            }
        });
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


}
