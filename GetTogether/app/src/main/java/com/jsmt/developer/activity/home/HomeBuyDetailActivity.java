package com.jsmt.developer.activity.home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.ShowImageDetail;
import com.jsmt.developer.activity.my.BuyMessageActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.WaiMaoQiuGouBean;
import com.jsmt.developer.rongyun.RongTalk;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

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

@ContentView(R.layout.recycler_qglb_iteam)
public class HomeBuyDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_shopName)
    private TextView tv_shopName;
    @ViewInject(R.id.tv_shop_ms)
    private TextView tv_shop_ms;
    @ViewInject(R.id.tv_qglx)
    private TextView tv_qglx;
    @ViewInject(R.id.tv_jhTime)
    private TextView tv_jhTime;
    @ViewInject(R.id.tv_qgNum)
    private TextView tv_qgNum;
    @ViewInject(R.id.tv_fbTime)
    private TextView tv_fbTime;
    @ViewInject(R.id.recy_image)
    private RecyclerView recy_image;
    @ViewInject(R.id.tv_zxgt)
    private TextView tv_zxgt;

    private List<WaiMaoQiuGouBean.ResultEntity> waiMaoQiuGouBeans = new ArrayList<>();
    private String trade_id;

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
        tv_name.setText(getResources().getText(R.string.user_name)+""+waiMaoQiuGouBeans.get(0).getMobile());
        tv_shopName.setText("商品名称：" + waiMaoQiuGouBeans.get(0).getGoods_name());
        tv_shop_ms.setText("出口国家：" + waiMaoQiuGouBeans.get(0).getGoods_desc());
        tv_qglx.setText("求购类型：" + waiMaoQiuGouBeans.get(0).getRelease_type());
        tv_jhTime.setText("交货时间：" + waiMaoQiuGouBeans.get(0).getAttach_time());
        tv_qgNum.setText("求购数量：" + waiMaoQiuGouBeans.get(0).getGoods_num());
        tv_fbTime.setText("发布时间：" + waiMaoQiuGouBeans.get(0).getAdd_time());

        tv_zxgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (BaseApplication.getInstance().userBean != null) {
                        if (BaseApplication.getInstance().userBean.getChat_id() != null && !BaseApplication.getInstance().userBean.getChat_id().equals("")) {

                            if (waiMaoQiuGouBeans != null && waiMaoQiuGouBeans.get(0).getUser_id() != null) {
                                RongTalk.doConnection(HomeBuyDetailActivity.this, BaseApplication.getInstance().userBean.getChat_id()
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

        recy_image.setLayoutManager(new GridLayoutManager(HomeBuyDetailActivity.this, 3, LinearLayoutManager.VERTICAL, false));
        if (waiMaoQiuGouBeans.get(0).getGoods_logo().size() > 0) {
            recy_image.setAdapter(new BaseRVAdapter(HomeBuyDetailActivity.this, waiMaoQiuGouBeans.get(0).getGoods_logo()) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.recyqgxx_image_item;
                }

                @Override
                public void onBind(BaseViewHolder holder, int position) {
                    ImageView iv_iamge_qg = holder.getImageView(R.id.iv_iamge_qg);
                    Glide.with(HomeBuyDetailActivity.this).load(waiMaoQiuGouBeans.get(0).getGoods_logo().get(position)).error(R.mipmap.myy322x).into(iv_iamge_qg);

                    iv_iamge_qg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(HomeBuyDetailActivity.this, ShowImageDetail.class);
                            intent.putStringArrayListExtra("paths", (ArrayList<String>) waiMaoQiuGouBeans.get(0).getGoods_logo());
                            intent.putExtra("index", String.valueOf(waiMaoQiuGouBeans.get(0)));
                            startActivity(intent);
                        }
                    });
                }

            });

        }
    }

}
