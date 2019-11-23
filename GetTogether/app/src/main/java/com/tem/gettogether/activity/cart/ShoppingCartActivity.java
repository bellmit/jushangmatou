package com.tem.gettogether.activity.cart;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.AddressGLActivity;
import com.tem.gettogether.activity.my.CgsAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CardCloaseBean;
import com.tem.gettogether.bean.CartDataBean;
import com.tem.gettogether.bean.JieSuanBean;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

@ContentView(R.layout.activity_shopping_cart)
public class ShoppingCartActivity extends BaseActivity {
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    @ViewInject(R.id.tv_guanli)
    private TextView tv_guanli;
    private int NumConnect = 1;
    @ViewInject(R.id.tv_go_js)
    private TextView tv_go_js;
    @ViewInject(R.id.tv_all_price)
    private TextView tv_all_price;
    @ViewInject(R.id.iv_cart_All)
    private ImageView iv_cart_All;
    private boolean isGLWC = false;
    @ViewInject(R.id.tv_yx_show)
    private TextView tv_yx_show;
    private BaseActivity baseActivity;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    private List<CartDataBean.ResultBean.StoreListBean> storeListBeans = new ArrayList<>();
    private CartDataBean.ResultBean.TotalPriceBean totalPriceBeans = new CartDataBean.ResultBean.TotalPriceBean();
    private String goods_id;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        upCartData();
    }

    /**
     * 弹窗 语音提示
     *
     * @param cardCloaseBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(CardCloaseBean cardCloaseBean) {
        if (cardCloaseBean == null) return;
        if (cardCloaseBean.isCartClose() == true) {
            rl_close.setVisibility(View.VISIBLE);
            goods_id = cardCloaseBean.getGoods_id();
        }
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                upCartData();
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
//                upCartData();
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(getContext(), 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("" + getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("" + getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText("" + getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
//        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
//        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);

        adapter = new CartAdapter(storeListBeans);
        order_rl.setAdapter(adapter);
    }

    @Event(value = {R.id.rl_close, R.id.tv_guanli, R.id.tv_go_js, R.id.ll_all_xz}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                /*startActivity(new Intent(this, ShoppingParticularsActivity.class)
                        .putExtra("goods_id", goods_id));
                rl_close.setVisibility(View.GONE);*/
                finish();
                break;
            case R.id.tv_guanli:
                if (isGLWC == false) {
                    tv_guanli.setText(R.string.wancheng);
                    tv_go_js.setText(R.string.shanchu);
                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                    isGLWC = true;
                } else {
                    tv_guanli.setText(R.string.guanli);
                    tv_go_js.setText(R.string.goujiesuan);
                    upCartData();
                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                    isGLWC = false;
                }
                break;
            case R.id.tv_go_js:
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.IS_VERIFY, "0").equals("1")) {
                    if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.IS_VERIFY, "0").equals("3")) {
                        CusToast.showToast(getText(R.string.shz));
                        return;
                    } else {
                        CusToast.showToast(getText(R.string.please_first_purchase_the_buyer));
                        startActivity(new Intent(getContext(), CgsAuthenticationActivity.class));
                        return;
                    }
                }
                final List<Integer> cartId = new ArrayList<>();
                String str = "";
                if (storeListBeans != null && storeListBeans.size() > 0) {
                    for (int shopAll = 0; shopAll < storeListBeans.size(); shopAll++) {
                        for (int all = 0; all < storeListBeans.get(shopAll).getCartList().size(); all++) {
                            if (storeListBeans.get(shopAll).getCartList().get(all).getItemXZ().equals("1")) {
                                cartId.add(Integer.parseInt(storeListBeans.get(shopAll).getCartList().get(all).getCart_id()));
                            }
                        }
                    }
                    Collections.sort(cartId);
                    for (int i = 0; i < cartId.size(); i++) {
                        str += cartId.get(i) + ",";
                    }
                }
                if (!str.equals("")) {
                    String cartid = str.substring(0, str.length() - 1);
                    if (tv_go_js.getText().toString().equals(getResources().getString(R.string.shanchu))) {
                        upRemoveCartData(cartid);
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                        if (cartid != null && !cartid.equals("")) {
                            map.put("ids", cartid);
                        }
                        upJieSCartData(map, cartid);
                        Log.i("====购物车选择id----", cartid + "---");
//                        tv_all_price.setVisibility(View.VISIBLE);
//                        iv_cart_All.setImageResource(R.drawable.cart_wxz_icon);
//                        tv_yx_show.setText("");
//                        tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
//                        tv_go_js.setText("去结算");
//                        tv_all_price.setText("¥0.00");
//                        startActivity(new Intent(this,CloseAccountActivity.class)
//                        .putExtra("cartid",cartid));

                    }
                } else {

                    CusToast.showToast(getResources().getText(R.string.please_select_a_product));
                    return;
                }

                break;
            case R.id.ll_all_xz:
                if (storeListBeans != null) {
                    if (isShopXz == false) {
                        for (int shopAll = 0; shopAll < storeListBeans.size(); shopAll++) {
                            for (int all = 0; all < storeListBeans.get(shopAll).getCartList().size(); all++) {
                                storeListBeans.get(shopAll).getCartList().get(all).setItemXZ("1");
                            }
                        }

                        isShopXz = true;
                    } else {
                        for (int shopAll = 0; shopAll < storeListBeans.size(); shopAll++) {
                            for (int all = 0; all < storeListBeans.get(shopAll).getCartList().size(); all++) {
                                storeListBeans.get(shopAll).getCartList().get(all).setItemXZ("0");
                            }
                        }
                        isShopXz = false;
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    CusToast.showToast(getResources().getText(R.string.please_add_item));
                }

                break;
        }
    }

    private JieSuanBean.ResultBean.AddressListBean addressListBeans = new JieSuanBean.ResultBean.AddressListBean();

    private void upJieSCartData(Map<String, Object> map, final String cartid) {
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--购物车结算打印--" + key, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.JIESUAN_PAY, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====购物车结算===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        JieSuanBean jieSuanBean = gson.fromJson(result, JieSuanBean.class);
                        addressListBeans = jieSuanBean.getResult().getAddressList();
                        String cart_ids = jieSuanBean.getResult().getCart_ids();

                        upHQpriceData(cartid, cart_ids);

                    } else if (res.equals("-1")) {
                        CusToast.showToast(msg);
                        return;
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

    private void upHQpriceData(final String cartid, final String cart_ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        if (addressListBeans.getAddress_id() != null) {
            map.put("address_id", addressListBeans.getAddress_id() + "");
        }
//        map.put("act", "");
        map.put("ids", cart_ids);
        showDialog();
        XUtil.Post(URLConstant.TIJIAO_DiNDDAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取订单价格===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        tv_all_price.setVisibility(View.VISIBLE);
                        iv_cart_All.setImageResource(R.drawable.cart_wxz_icon);
                        tv_yx_show.setText("");
                        tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                        tv_go_js.setText(R.string.goujiesuan);
                        tv_all_price.setText("¥0.00");
                        startActivity(new Intent(getContext(), CloseAccountActivity.class)
                                .putExtra("cartid", cartid));
                    } else if (res.equals("-1")) {
                        startActivity(new Intent(getContext(), AddressGLActivity.class));
                        CusToast.showToast(msg);
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

    private boolean isALLxz = false;
    private boolean isShopXz = false;
    private boolean isShoppingXz = false;
    private ImageView iv_cart_shopping_isxz;
    private CartAdapter adapter;
    private int Allnum;

    public class CartAdapter extends BaseQuickAdapter {

        public CartAdapter(List<CartDataBean.ResultBean.StoreListBean> data) {
            super(R.layout.cart_item_order_fragment, data);
        }

        @Override
        protected void convert(final com.chad.library.adapter.base.BaseViewHolder baseViewHolder, Object o) {
            baseViewHolder.setText(R.id.tv_shop_name, storeListBeans.get(baseViewHolder.getAdapterPosition()).getStore_name());
            RecyclerView recyclerView = baseViewHolder.getView(R.id.cartItem_recycler);
            final ImageView iv_cart_isxz = baseViewHolder.getView(R.id.iv_cart_isxz);

            Allnum = totalPriceBeans.getNum();

            int shopNum = 0;
            for (int shopAll = 0; shopAll < storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().size(); shopAll++) {
                if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(shopAll).getItemXZ().equals("1")) {
                    shopNum++;
                }
            }
            if (shopNum == storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().size()) {
                iv_cart_isxz.setImageResource(R.drawable.cart_xz_icon);
                storeListBeans.get(baseViewHolder.getAdapterPosition()).setShopXZ("1");
//                tv_go_js.setBackgroundColor(getResources().getColor(R.color.my_red));
            } else {
                storeListBeans.get(baseViewHolder.getAdapterPosition()).setShopXZ("0");
                iv_cart_isxz.setImageResource(R.drawable.cart_wxz_icon);
            }

            int QBNum = 0;
            for (int shopAll = 0; shopAll < storeListBeans.size(); shopAll++) {
                if (storeListBeans.get(shopAll).getShopXZ().equals("1")) {
                    QBNum++;
                }
            }
            if (QBNum == storeListBeans.size()) {
                iv_cart_All.setImageResource(R.drawable.cart_xz_icon);
                tv_yx_show.setText("（" + totalPriceBeans.getNum() + "）");
                tv_go_js.setBackgroundColor(getResources().getColor(R.color.my_red));
                tv_go_js.setText(R.string.goujiesuan + "（" + totalPriceBeans.getNum() + "）");
                tv_all_price.setText("¥" + totalPriceBeans.getTotal_fee());
                tv_all_price.setVisibility(View.VISIBLE);
            } else {
                tv_all_price.setVisibility(View.VISIBLE);
                iv_cart_All.setImageResource(R.drawable.cart_wxz_icon);
                tv_yx_show.setText("");
                tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                tv_go_js.setText(R.string.goujiesuan);
                tv_all_price.setText("¥0.00");
            }
            baseViewHolder.getView(R.id.ll_shop_isxz).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int all = 0; all < storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().size(); all++) {
                        if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(all).getItemXZ().equals("0")) {
                            storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(all).setItemXZ("1");
                            storeListBeans.get(baseViewHolder.getAdapterPosition()).setShopXZ("1");

                        } else {
                            storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(all).setItemXZ("0");
                            storeListBeans.get(baseViewHolder.getAdapterPosition()).setShopXZ("0");

                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new BaseRVAdapter(getContext(), storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList()) {
                                        @Override
                                        public int getLayoutId(int viewType) {
                                            return R.layout.cartitem_recy_item_layout;
                                        }

                                        @Override
                                        public void onBind(BaseViewHolder holder, final int position) {
                                            iv_cart_shopping_isxz = holder.getImageView(R.id.iv_cart_shopping_isxz);
                                            TextView tv_red_num = holder.getTextView(R.id.tv_red_num);
                                            TextView tv_shopping_qpl = holder.getTextView(R.id.tv_shopping_qpl);
                                            TextView tv_guige = holder.getTextView(R.id.tv_guige);
                                            final TextView tv_connectNum = holder.getTextView(R.id.tv_connectNum);
                                            final TextView tv_shopping_price = holder.getTextView(R.id.tv_shopping_price);
                                            RoundImageView iv_shopping_image = (RoundImageView) holder.getImageView(R.id.iv_shopping_image);
                                            Glide.with(getContext()).load(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getCover_image()).error(R.mipmap.myy322x).into(iv_shopping_image);
                                            if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getSpec_key_name() != null &&
                                                    !storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getSpec_key_name().equals("")) {
                                                tv_guige.setVisibility(View.VISIBLE);
                                                tv_guige.setText(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getSpec_key_name());
                                            }
                                            tv_connectNum.setText(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getGoods_num());
                                            holder.getTextView(R.id.tv_connect).setText(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getGoods_name());
                                            if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getIs_enquiry().equals("1")) {//立即询价
                                                holder.getTextView(R.id.tv_red_num).setText(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getBatch_number() + getResources().getText(R.string.from_batch));
                                                tv_shopping_qpl.setVisibility(View.GONE);
                                                tv_red_num.setVisibility(View.VISIBLE);
                                                tv_shopping_price.setText(getResources().getText(R.string.ask));
//                            holder.getTextView(R.id.tv_cart_jian).setEnabled(false);
//                            holder.getTextView(R.id.tv_cart_add).setEnabled(false);

                                                tv_shopping_price.setTextColor(getResources().getColor(R.color.my_yellow));
                                            } else {
                                                tv_shopping_qpl.setVisibility(View.VISIBLE);
                                                tv_red_num.setVisibility(View.GONE);
                                                holder.getTextView(R.id.tv_shopping_qpl).setText(storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getBatch_number() + getResources().getText(R.string.from_batch));
                                                holder.getTextView(R.id.tv_shopping_price).setText(getResources().getText(R.string.renminbi_symbol) + storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getGoods_price() + "/" + getResources().getText(R.string.piece_tv));
                                                tv_shopping_price.setTextColor(getResources().getColor(R.color.my_red));
                                            }

                                            if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getItemXZ().equals("0")) {
                                                iv_cart_shopping_isxz.setImageResource(R.drawable.cart_wxz_icon);
                                                if (isGLWC == false) {
                                                    tv_go_js.setText(R.string.goujiesuan);
                                                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                                                } else {
                                                    tv_go_js.setText(R.string.shanchu);
                                                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                                                }
                                            } else {
                                                iv_cart_shopping_isxz.setImageResource(R.drawable.cart_xz_icon);
                                                if (isGLWC == false) {
                                                    tv_go_js.setText(R.string.goujiesuan);
                                                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.my_red));
                                                } else {
                                                    tv_go_js.setText(R.string.shanchu);
                                                    tv_go_js.setBackgroundColor(getResources().getColor(R.color.my_red));
                                                }
                                            }
                                            for (int shopAll1 = 0; shopAll1 < storeListBeans.size(); shopAll1++) {
                                                for (int shopAll = 0; shopAll < storeListBeans.get(shopAll1).getCartList().size(); shopAll++) {
                                                    if (storeListBeans.get(shopAll1).getCartList().get(shopAll).getItemXZ().equals("1")) {
                                                        tv_go_js.setBackgroundColor(getResources().getColor(R.color.my_red));
                                                        tv_all_price.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            }

                                            holder.getView(R.id.rl_item_zt).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getItemXZ().equals("0")) {
                                                        storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).setItemXZ("1");

                                                    } else {
                                                        storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).setItemXZ("0");
                                                    }
                                                    adapter.notifyDataSetChanged();

                                                }
                                            });
                                            tv_shopping_price.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (tv_shopping_price.getText().toString().equals(getResources().getText(R.string.ask))) {
                                                        try {

                                                            //发消息
                                                            if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0").equals("")) {
                                                                if (storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList() != null && storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getStore_user_id() != null) {
                                                                    RongTalk.doConnection(getContext(), SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0")
                                                                            , storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getStore_user_id(), storeListBeans.get(baseViewHolder.getAdapterPosition()).getStore_name(),
                                                                            storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getCover_image(), storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getStore_id());
                                                                } else {
                                                                    CusToast.showToast(getResources().getText(R.string.the_store_is_invalid));
                                                                }
                                                            }

                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                            CusToast.showToast(getResources().getText(R.string.customer_service_is_invalid));
                                                        }
                                                    }
                                                }
                                            });
                                            holder.getTextView(R.id.tv_cart_jian).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int numJian = Integer.parseInt(tv_connectNum.getText().toString().trim());
                                                    if (numJian <= 1) {
                                                        CusToast.showToast(getResources().getText(R.string.the_number_cannot_be_less_than_one));
                                                        return;
                                                    }
                                                    numJian--;
                                                    updateCartNum(numJian, storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getCart_id());
                                                    upCartData();
                                                    CartAdapter.this.notifyDataSetChanged();
                                                    Allnum--;
                                                    tv_go_js.setText(R.string.goujiesuan + "（" + Allnum + "）");
                                                    tv_yx_show.setText("（" + Allnum + "）");
//                                                    tv_connectNum.setText(numJian + "");
                                                }
                                            });
                                            holder.getTextView(R.id.tv_cart_add).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    int numadd = Integer.parseInt(tv_connectNum.getText().toString().trim());
                                                    numadd++;
                                                    updateCartNum(numadd, storeListBeans.get(baseViewHolder.getAdapterPosition()).getCartList().get(position).getCart_id());
                                                    upCartData();
                                                    CartAdapter.this.notifyDataSetChanged();
                                                    Allnum++;
                                                    tv_go_js.setText(R.string.goujiesuan + "（" + Allnum + "）");
                                                    tv_yx_show.setText("（" + Allnum + "）");
//                                                    tv_connectNum.setText(numadd + "");
                                                }
                                            });
                                        }
                                    }
            );

        }
    }

    private void updateCartNum(int goodsNum, String Id) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_num", goodsNum);
        map.put("id", Id);
        XUtil.Post(URLConstant.CART_NUM, map, new MyCallBack<String>() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {

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
            }

        });
    }

    private void upCartData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        XUtil.Post(URLConstant.CART_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====购物车列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CartDataBean cartDataBean = gson.fromJson(result, CartDataBean.class);
                        storeListBeans.removeAll(storeListBeans);
                        storeListBeans.addAll(cartDataBean.getResult().getStoreList());
                        totalPriceBeans = cartDataBean.getResult().getTotal_price();
                    } else if (msg.equals("")) {
                        tv_all_price.setVisibility(View.VISIBLE);
                        iv_cart_All.setImageResource(R.drawable.cart_wxz_icon);
                        tv_yx_show.setText("");
                        tv_go_js.setBackgroundColor(getResources().getColor(R.color.cart_bg_6c));
                        tv_go_js.setText(R.string.goujiesuan);
                        tv_all_price.setText("¥0.00");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }

    private void upRemoveCartData(String ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("ids", ids);

        XUtil.Post(URLConstant.REMOVE_CART, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====删除购物车===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                upCartData();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }
}
