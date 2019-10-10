package com.tem.gettogether.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.my.VipCenterActivity;
import com.tem.gettogether.activity.my.WaiMaoQiuGouActivity;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.WaiMaoQiuGouBean;
import com.tem.gettogether.rongyun.CustomizeBuyMessage;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

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
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


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
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.goods_desc)
    private TextView goods_desc;

    private List<WaiMaoQiuGouBean.ResultEntity> waiMaoQiuGouBeans = new ArrayList<>();
    private int isHomeList = 0;
    private int type;

    @Override
    protected void initData() {
        x.view().inject(this);
        trade_id = getIntent().getStringExtra("trade_id");
        isHomeList = getIntent().getIntExtra("witch_page", 0);
        type = getIntent().getIntExtra("page", 0);
        Log.d("chenshichun","=====type======"+type);
        initDatas(type);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    private void initDatas(int type) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getLanguageString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        map.put("trade_id", trade_id);
        showDialog();
        String url = "";
        Log.d("chenshichun","====type======="+type);
        if (type == 0) {
            url = URLConstant.HOMEQIUGOUDETAIL1;
        } else {
            url = URLConstant.HOMEQIUGOUDETAIL;
        }
        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.d("chenshichun", "=======外贸求购详情====" + result);
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
                refreshLayout.finishRefreshing();
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
        int imageSize = SizeUtil.dp2px(getContext(), 80);
        Glide.with(getContext()).load(waiMaoQiuGouBeans.get(0).getHead_pic()).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(head_iv);
        user_name_tv.setText(getResources().getText(R.string.user_name) + "" + waiMaoQiuGouBeans.get(0).getNickname());
        country_tv.setText(getText(R.string.chugouguojia) + waiMaoQiuGouBeans.get(0).getCountry_name());
        product_title.setText("" + waiMaoQiuGouBeans.get(0).getGoods_name());
        delivery_time_tv.setText(getText(R.string.buy_time) + waiMaoQiuGouBeans.get(0).getAttach_time());
        num_tv.setText(getText(R.string.purchase_quantity) + waiMaoQiuGouBeans.get(0).getGoods_num());
        release_time_tv.setText(getText(R.string.release_time) + waiMaoQiuGouBeans.get(0).getAdd_time());
        goods_desc.setText(waiMaoQiuGouBeans.get(0).getGoods_desc());

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(waiMaoQiuGouBeans.get(0).getGoods_logo());
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent2 = new Intent(HomeBuyDetailNewActivity.this, ShowImageDetail.class);
                intent2.putStringArrayListExtra("paths", waiMaoQiuGouBeans.get(0).getGoods_logo());
                intent2.putExtra("index", position);
                startActivity(intent2);
            }
        });
        banner.start();

        online_communication_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.ROLE_TYPE, "").equals("0")) {
                    CusToast.showToast(getText(R.string.buyer_does_not_have_this_feature));
                    return;
                }

                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "").equals("2")) {
                    CusToast.showToast(getText(R.string.store_review));
                    startActivity(new Intent(getContext(), ShopAuthenticationActivity.class));
                    return;
                }

                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "").equals("1")) {
                    CusToast.showToast(getText(R.string.please_certify_shops_first));
                    startActivity(new Intent(getContext(), ShopAuthenticationActivity.class));
                    return;
                }

                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "").equals("7")) {
                    CusToast.showToast(getText(R.string.please_upgrade_the_premium_member_first));
                    startActivity(new Intent(getContext(), VipCenterActivity.class));
                    return;
                }

                try {
                    if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0").equals("")) {

                        if (waiMaoQiuGouBeans != null && waiMaoQiuGouBeans.get(0).getUser_id() != null) {
                            RongTalk.doConnection(HomeBuyDetailNewActivity.this, SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0")
                                    , waiMaoQiuGouBeans.get(0).getUser_id(), waiMaoQiuGouBeans.get(0).getNickname(),
                                    "", "");
                        } else {
                            CusToast.showToast(R.string.the_store_is_invalid);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    CusToast.showToast(getText(R.string.the_store_is_invalid));
                }
                Log.d("chenshichun", "=====trade_id======" + trade_id);
                Log.d("chenshichun","======type====="+(type+1));
                Log.d("chenshichun","=======targetId===="+waiMaoQiuGouBeans.get(0).getUser_id());
                sendCustomizeMessage( waiMaoQiuGouBeans.get(0).getUser_id(),trade_id, waiMaoQiuGouBeans.get(0).getGoods_logo().get(0),
                        waiMaoQiuGouBeans.get(0).getGoods_name(), waiMaoQiuGouBeans.get(0).getGoods_num() + "起批", "求购",""+(type+1));
            }
        });
    }

    private void sendCustomizeMessage(String targetId, String goods_id, String image, String goods_name,
                                      String batch_number, String goods_type, String qiugou_type) {
        CustomizeBuyMessage customizeMessage = new CustomizeBuyMessage(goods_id, image, goods_name, batch_number, "",  SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id,""), goods_type, qiugou_type);
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setPullDownStr(getString(R.string.pull_down_refresh));
        headerView.setReleaseRefreshStr(getString(R.string.release_refresh));
        headerView.setRefreshingStr(getString(R.string.refreshing));
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setEnableLoadmore(false);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                initDatas(type);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
            }
        });
    }

    @Event(value = {R.id.view_other_tv}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.view_other_tv:
                if (isHomeList == 0) {// 首页跳进来的
                    startActivity(new Intent(this, WaiMaoQiuGouActivity.class));
                } else if (isHomeList == 1) {// 列表跳进来的
                    finish();
                }
                break;
        }
    }
}
