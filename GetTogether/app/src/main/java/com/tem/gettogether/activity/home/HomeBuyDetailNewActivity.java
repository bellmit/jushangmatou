package com.tem.gettogether.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.my.WaiMaoQiuGouActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.WaiMaoQiuGouBean;
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
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;


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
    private int page;

    @Override
    protected void initData() {
        x.view().inject(this);
        trade_id = getIntent().getStringExtra("trade_id");
        isHomeList = getIntent().getIntExtra("witch_page", 0);
        page = getIntent().getIntExtra("page", 0);
        initDatas(page);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    private void initDatas(int page) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        map.put("trade_id", trade_id);
        showDialog();
        String url = "";
        if (page == 0) {
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
        country_tv.setText("出口国家：" + waiMaoQiuGouBeans.get(0).getCountry_name());
        product_title.setText("" + waiMaoQiuGouBeans.get(0).getGoods_name());
        delivery_time_tv.setText("交货时间：" + waiMaoQiuGouBeans.get(0).getAttach_time());
        num_tv.setText("求购数量：" + waiMaoQiuGouBeans.get(0).getGoods_num());
        release_time_tv.setText("发布时间：" + waiMaoQiuGouBeans.get(0).getAdd_time());
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
                    CusToast.showToast("采购商暂无此功能");
                    return;
                }

                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "").equals("1")) {
                    CusToast.showToast("商铺未认证，请先认证商铺!");
                    return;
                }

                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "").equals("7")) {
                    CusToast.showToast("请先升级高级会员!");
                    return;
                }

                try {
                    if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0").equals("")) {

                        if (waiMaoQiuGouBeans != null && waiMaoQiuGouBeans.get(0).getUser_id() != null) {
                            RongTalk.doConnection(HomeBuyDetailNewActivity.this, SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0")
                                    , waiMaoQiuGouBeans.get(0).getUser_id(), waiMaoQiuGouBeans.get(0).getNickname(),
                                    "", "");
                        } else {
                            CusToast.showToast("该店铺无效");
                        }
                    }

                    RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
                        @Override
                        public io.rong.imlib.model.Message onSend(io.rong.imlib.model.Message message) {
                            return message;
                        }

                        @Override
                        public boolean onSent(io.rong.imlib.model.Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                            Log.d("chenshichun", "=======1message.getContent()====" + message.getContent());
                            Log.d("chenshichun", "======1message.getSentStatus()=====" + message.getSentStatus());
                            /*if(!message.getContent().equals("我是消息内容")) {
                                TextMessage myTextMessage = TextMessage.obtain("我是消息内容");
                                Message myMessage = Message.obtain(waiMaoQiuGouBeans.get(0).getUser_id(), Conversation.ConversationType.PRIVATE, myTextMessage);
                                RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                                    @Override
                                    public void onAttached(Message message) {
                                        //消息本地数据库存储成功的回调
                                    }

                                    @Override
                                    public void onSuccess(Message message) {
                                        //消息通过网络发送成功的回调
                                    }

                                    @Override
                                    public void onError(Message message, RongIMClient.ErrorCode errorCode) {

                                    }
                                });
                            }*/
                            return false;
                        }
                    });

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

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setEnableLoadmore(false);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                initDatas(page);
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
