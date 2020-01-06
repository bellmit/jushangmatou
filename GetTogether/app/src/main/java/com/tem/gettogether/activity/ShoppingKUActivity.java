package com.tem.gettogether.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShoppingKuBean;
import com.tem.gettogether.rongyun.CustomizeBuyMessage;
import com.tem.gettogether.rongyun.CustomizeMessage;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

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
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;


@ContentView(R.layout.activity_shopping_ku)
public class ShoppingKUActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private String store_id;
    private List<ShoppingKuBean.ResultBean.GoodsListBean> goodsListBeans = new ArrayList<>();
    private List<ShoppingKuBean.ResultBean.GoodsListBean> listBeans;
    private String targetId;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.commodity_library));
        store_id = getIntent().getStringExtra("store_id");
        targetId = getIntent().getStringExtra("targetId");
        findViewById(R.id.rl_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        upshopKuData();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);

        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingKUActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM = 1;
                clearList(goodsListBeans);
                upshopKuData();

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingKUActivity.this)) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                upshopKuData();
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

    public void clearList(List<ShoppingKuBean.ResultBean.GoodsListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    private void upshopKuData() {
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("page", PAGE_NUM);
        String yuyan = SharedPreferencesUtils.getLanguageString(getContext(), BaseConstant.SPConstant.language, "");
        map.put("language", yuyan);
        XUtil.Post(URLConstant.SHOPSHOPIINGDATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====商品库===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ShoppingKuBean shoppingKuBean = gson.fromJson(result, ShoppingKuBean.class);
                        if (PAGE_NUM == 1) {
                            goodsListBeans = shoppingKuBean.getResult().getGoods_list();
                        } else {
                            listBeans = shoppingKuBean.getResult().getGoods_list();
                            if (listBeans.size() == 0) {
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                                return;
                            }
                            goodsListBeans.addAll(listBeans);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ShoppingKudapter adapter = new ShoppingKudapter(goodsListBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }

    public class ShoppingKudapter extends BaseQuickAdapter {

        public ShoppingKudapter(List<ShoppingKuBean.ResultBean.GoodsListBean> data) {
            super(R.layout.shopping_ku_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_pic = baseViewHolder.getView(R.id.iv_pic);
            Glide.with(ShoppingKUActivity.this).load(goodsListBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_pic);
            baseViewHolder.setText(R.id.tv_shoping_jj, goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qpl, goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number() + getText(R.string.from_batch));
            baseViewHolder.getView(R.id.tv_send).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomizeBuyMessage customizeMessage = new CustomizeBuyMessage(goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id(), goodsListBeans.get(baseViewHolder.getAdapterPosition()).getImage(), goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name(),
                            goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number() + getText(R.string.batch), "", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, "")
                            , getString(R.string.commodity), "");
                    byte[] bvvv = customizeMessage.encode();
                    CustomizeBuyMessage richContentMessage = new CustomizeBuyMessage(bvvv);
                    io.rong.imlib.model.Message myMessage = io.rong.imlib.model.Message.obtain(targetId, Conversation.ConversationType.PRIVATE, richContentMessage);
                    RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(io.rong.imlib.model.Message message) {
                            //消息本地数据库存储成功的回调
                        }

                        @Override
                        public void onSuccess(io.rong.imlib.model.Message message) {
                            Log.d("chenshichun", "======发送成功=====");
                            finish();
                            //消息通过网络发送成功的回调
                            CusToast.showToast(getText(R.string.message_successed));
                        }

                        @Override
                        public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {
                            //消息发送失败的回调
                            Log.d("chenshichun", "======消息发送失败=====");
                            CusToast.showToast(getText(R.string.message_failed));
                        }
                    });
                }
            });

        }
    }
}
