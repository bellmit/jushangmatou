package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ChanPinLiebiaoBean;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

@ContentView(R.layout.activity_chan_pin_gl)
public class ChanPinGLActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private List<ChanPinLiebiaoBean.ResultBean> resultBeans = new ArrayList<>();
    private List<ChanPinLiebiaoBean.ResultBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(getText(R.string.product_management));

    }

    @Override
    protected void onResume() {
        super.onResume();
        PAGE_NUM = 1;
        Map<String, Object> map3 = new HashMap<>();
        map3.put("page", PAGE_NUM);
        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        upShopData(map3);
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ChanPinGLActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM = 1;
                Map<String, Object> map3 = new HashMap<>();
                map3.put("page", PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                upShopData(map3);


            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ChanPinGLActivity.this)) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                Map<String, Object> map3 = new HashMap<>();
                map3.put("page", PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                upShopData(map3);
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("" + getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("" + getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText("" + getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);
    }

    @Event(value = {R.id.rl_close, R.id.ll_look_more}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_look_more:
                PAGE_NUM++;
                Map<String, Object> map3 = new HashMap<>();
                map3.put("page", PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                break;
        }
    }

    private void upShopData(Map<String, Object> map) {
        XUtil.Post(URLConstant.SHOPPING_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====商品列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ChanPinLiebiaoBean chanPinLiebiaoBean = gson.fromJson(result, ChanPinLiebiaoBean.class);
                        if (PAGE_NUM == 1) {
                            resultBeans = chanPinLiebiaoBean.getResult();
                        } else {
                            list = chanPinLiebiaoBean.getResult();
                            if (list.size() == 0) {
                                CusToast.showToast(getText(R.string.no_more_data));
                                return;
                            }
                            resultBeans.addAll(list);
                        }

                    } else {
                        String msg = jsonObject.optString("msg");
                        CusToast.showToast(msg);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ChanpinLieBAdapter adapter = new ChanpinLieBAdapter(resultBeans);
                order_rl.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }

    public class ChanpinLieBAdapter extends BaseQuickAdapter {

        public ChanpinLieBAdapter(List<ChanPinLiebiaoBean.ResultBean> data) {//
            super(R.layout.recycler_shop_liebiao_iteam, data);
        }

        @Override
        protected void convert(final com.chad.library.adapter.base.BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_image = baseViewHolder.getView(R.id.iv_image);
            TextView tv_title = baseViewHolder.getView(R.id.tv_title);
            TextView tv_Shop_price = baseViewHolder.getView(R.id.tv_Shop_price);
            TextView tv_shuoming = baseViewHolder.getView(R.id.tv_shuoming);
            TextView tv_top_botton = baseViewHolder.getView(R.id.tv_top_botton);
            Glide.with(ChanPinGLActivity.this).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
            tv_title.setText(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            tv_Shop_price.setText(getText(R.string.renminbi_symbol) + resultBeans.get(baseViewHolder.getAdapterPosition()).getShop_price());
            tv_shuoming.setText(resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_count() + getText(R.string.piece_tv));
            if (resultBeans.get(baseViewHolder.getAdapterPosition()).getIs_on_sale().equals("1")) {
                tv_top_botton.setText(getText(R.string.obtained));
            } else {
                tv_top_botton.setText(getText(R.string.shelf));
            }
            tv_top_botton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upXJShopData(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id());
                }
            });
            baseViewHolder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChanPinGLActivity.this, ShoppingParticularsActivity.class)
                            .putExtra("goods_id", resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });
        }
    }

    private void upXJShopData(String goods_id) {
        Map<String, Object> map3 = new HashMap<>();
        map3.put("goods_id", goods_id);
        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        XUtil.Post(URLConstant.SHOPPING_XIAJIALIEBIAO, map3, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Log.i("====下架===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        PAGE_NUM = 1;
                        Map<String, Object> map3 = new HashMap<>();
                        map3.put("page", PAGE_NUM);
                        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                        upShopData(map3);

                    } else {
                        String msg = jsonObject.optString("msg");
                        CusToast.showToast(msg);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ChanpinLieBAdapter adapter = new ChanpinLieBAdapter(resultBeans);
                order_rl.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
}
