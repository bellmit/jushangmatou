package com.tem.gettogether.activity.home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SouSuoDataBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

@ContentView(R.layout.activity_hot_fen_lei)
public class HotFenLeiActivity extends BaseActivity {
    @ViewInject(R.id.tv_zonghe)
    private TextView tv_zonghe;
    @ViewInject(R.id.chengjiaocishu)
    private TextView tv_pfl;
    @ViewInject(R.id.price_tv)
    private TextView tv_xl;
    @ViewInject(R.id.huifulv)
    private TextView huifulv;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private String category_id;
    private String keywords;
    private String paixu = "desc";
    private String sort = "sort";
    @ViewInject(R.id.et_sousuo)
    private EditText et_sousuo;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    private List<SouSuoDataBean.ResultBean> list;
    private List<SouSuoDataBean.ResultBean> resultBeans = new ArrayList<>();
    private boolean zonghepaixu, chengjiapaixu, huifupaixu, jiagepaixu;
    private boolean isYiLain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        isYiLain = getIntent().getBooleanExtra("is_yilian",false);
        StatusBarUtil.setTranslucentStatus(this);

        initData();
        initView();
    }

    @Override
    protected void initData() {
        category_id = getIntent().getStringExtra("category_id");
        keywords = getIntent().getStringExtra("keywords");
        tv_title.setText(keywords);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (category_id != null && !category_id.equals("")) {
            Map<String, Object> map3 = new HashMap<>();
            map3.put("category_id", category_id);
            map3.put("page", 1);
            map3.put("sort", sort);
            map3.put("sort_asc", paixu);
            upShopData(map3);
        } else if (keywords != null && !keywords.equals("")) {
            Map<String, Object> map3 = new HashMap<>();
            map3.put("page", 1);
            map3.put("sort", sort);
            map3.put("sort_asc", paixu);
            map3.put("keywords", keywords);
            upShopData(map3);
        }
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(HotFenLeiActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM = 1;
                clearList(resultBeans);
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("category_id", category_id);
                    map3.put("page", PAGE_NUM);
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    upShopData(map3);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("page", PAGE_NUM);
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    map3.put("keywords", keywords);
                    upShopData(map3);
                }

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(HotFenLeiActivity.this)) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("category_id", category_id);
                    map3.put("page", PAGE_NUM);
                    map3.put("sort", sort);//销量
                    map3.put("sort_asc", paixu);
                    upShopData(map3);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("page", PAGE_NUM);
                    map3.put("sort", sort);//销量
                    map3.put("sort_asc", paixu);
                    map3.put("keywords", keywords);
                    upShopData(map3);
                }
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 2));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(""+getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(""+getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(""+getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);


    }

    private void setDrawableRight(TextView view, boolean isDown) {
        Drawable img_on, img_off;
        Resources res = getResources();
        img_off = res.getDrawable(R.drawable.jiangxu);
        img_on = res.getDrawable(R.drawable.shengxu);
        img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
        img_on.setBounds(0, 0, img_on.getMinimumWidth(), img_on.getMinimumHeight());
        view.setCompoundDrawables(null, null, isDown ? img_off : img_on, null); //设置左图标
    }

    @Event(value = {R.id.rl_close, R.id.tv_zonghe, R.id.huifulv, R.id.chengjiaocishu, R.id.price_tv, R.id.rl_sousuo}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_zonghe:
                tv_zonghe.setTextColor(getResources().getColor(R.color.home_red));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                huifulv.setTextColor(getResources().getColor(R.color.text3));

                sort = "sort";
                if (!zonghepaixu) {
                    paixu = "desc";
                    setDrawableRight(tv_zonghe,false);
                    zonghepaixu = true;
                } else {
                    setDrawableRight(tv_zonghe,true);
                    paixu = "asc";
                    zonghepaixu = false;
                }
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("category_id", category_id);
                    map3.put("page", "1");
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    upShopData(map3);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("page", "1");
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    map3.put("keywords", keywords);
                    upShopData(map3);
                }

                break;
            case R.id.huifulv:
                huifulv.setTextColor(getResources().getColor(R.color.home_red));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));

                sort = "sort";
                if (!huifupaixu) {
                    paixu = "desc";
                    setDrawableRight(huifulv,false);
                    huifupaixu = true;
                } else {
                    setDrawableRight(huifulv,true);
                    paixu = "asc";
                    huifupaixu = false;
                }
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("category_id", category_id);
                    map3.put("page", "1");
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    upShopData(map3);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("page", "1");
                    map3.put("sort", sort);
                    map3.put("sort_asc", paixu);
                    map3.put("keywords", keywords);
                    upShopData(map3);
                }
                break;
            case R.id.chengjiaocishu:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.home_red));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                huifulv.setTextColor(getResources().getColor(R.color.text3));

                sort = "sales_sum";
                if (!chengjiapaixu) {
                    paixu = "desc";
                    setDrawableRight(tv_pfl,false);
                    chengjiapaixu = true;
                } else {
                    setDrawableRight(tv_pfl,true);
                    paixu = "asc";
                    chengjiapaixu = false;
                }
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("page", "1");
                    map2.put("sort", sort);//批发
                    map2.put("sort_asc", paixu);
                    map2.put("category_id", category_id);
                    upShopData(map2);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("page", "1");
                    map2.put("sort", sort);//批发
                    map2.put("sort_asc", paixu);
                    map2.put("keywords", keywords);
                    upShopData(map2);
                }


                break;
            case R.id.price_tv:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.home_red));
                huifulv.setTextColor(getResources().getColor(R.color.text3));
                sort = "shop_price";
                if (!jiagepaixu) {
                    paixu = "desc";
                    setDrawableRight(tv_xl,false);
                    jiagepaixu = true;
                } else {
                    setDrawableRight(tv_xl,true);
                    paixu = "asc";
                    jiagepaixu = false;
                }
                if (category_id != null && !category_id.equals("")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("page", "1");
                    map.put("sort", sort);//销量
                    map.put("sort_asc", paixu);
                    map.put("category_id", category_id);
                    upShopData(map);
                } else if (keywords != null && !keywords.equals("")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("page", "1");
                    map.put("sort", sort);//销量
                    map.put("sort_asc", paixu);
                    map.put("keywords", keywords);
                    upShopData(map);
                }
                break;
            case R.id.rl_sousuo:
                String sousuo = et_sousuo.getText().toString().trim();
                if (sousuo.equals("")) {
                    CusToast.showToast(getText(R.string.qsrssnr));
                    return;
                }
                keywords = sousuo;
                category_id = "";
                Map<String, Object> map = new HashMap<>();
                map.put("page", "1");
                map.put("sort", sort);
                map.put("sort_asc", paixu);
                map.put("keywords", sousuo);
                upShopData(map);

                break;


        }
    }

    public void clearList(List<SouSuoDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    private void upShopData(Map<String, Object> map) {
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID,""));
        //map.put("store_id",SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id,""));
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--搜索参数打印--" + key, "" + value + "\n");
            }
        }
        String url;
        if(isYiLain){
            url = URLConstant.YILIAN_SEARCH;
        }else{
            url = URLConstant.SHANGPINLIEBIAO;
        }
        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====商品列表或商品搜索===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        SouSuoDataBean souSuoDataBean = gson.fromJson(result, SouSuoDataBean.class);
                        if (PAGE_NUM == 1) {
                            resultBeans = souSuoDataBean.getResult();
                        } else {
                            list = souSuoDataBean.getResult();
                            if (ListUtils.isEmpty(list)) {
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                                return;
                            }
                            resultBeans.addAll(list);
                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
//                closeDialog();
                if(resultBeans.size()==0){
                    ll_empty.setVisibility(View.VISIBLE);
                }else{
                    ll_empty.setVisibility(View.GONE);
                }
                HotFLAdapter adapter = new HotFLAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                closeDialog();
                ex.printStackTrace();

            }
        });
    }

    public class HotFLAdapter extends BaseQuickAdapter {

        public HotFLAdapter(List<SouSuoDataBean.ResultBean> data) {//
            super(R.layout.shop_home_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            RoundImageView iv_image = baseViewHolder.getView(R.id.iv_image);
            Glide.with(HotFenLeiActivity.this).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getcover_image()).error(R.mipmap.myy322x).into(iv_image);
            baseViewHolder.setText(R.id.tv_name, resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_numbear, resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number() + "件起批");
            baseViewHolder.setText(R.id.tv_pingjia, resultBeans.get(baseViewHolder.getAdapterPosition()).getBest_percent() + "好评");
            baseViewHolder.getView(R.id.ll_item_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivity(new Intent(HotFenLeiActivity.this, ShoppingParticularsActivity.class)
                                .putExtra("goods_id", resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });
        }
    }

}