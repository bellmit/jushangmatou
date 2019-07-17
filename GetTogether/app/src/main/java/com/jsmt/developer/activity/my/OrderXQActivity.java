package com.jsmt.developer.activity.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.AliPayBean;
import com.jsmt.developer.bean.OrderDataBean;
import com.jsmt.developer.bean.WechatDataBean;
import com.jsmt.developer.rongyun.RongTalk;
import com.jsmt.developer.utils.PayResult;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.jsmt.developer.view.OnPasswordInputFinish2;
import com.jsmt.developer.view.PasswordView2;
import com.jsmt.developer.view.RoundImageView;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

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

@ContentView(R.layout.activity_order_xq)
public class OrderXQActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_text1)
    private TextView tv_text1;
    @ViewInject(R.id.tv_text2)
    private TextView tv_text2;
    @ViewInject(R.id.tv_text3)
    private TextView tv_text3;
    @ViewInject(R.id.tv_red_right)
    private TextView tv_red_right;
    @ViewInject(R.id.tv_red_rightpj)
    private TextView tv_red_rightpj;
    @ViewInject(R.id.tv_ddzt_ts)
    private TextView tv_ddzt_ts;
    @ViewInject(R.id.tv_ddzt_time)
    private TextView tv_ddzt_time;
    @ViewInject(R.id.tv_name_phone)
    private TextView tv_name_phone;
    @ViewInject(R.id.tv_address_sh)
    private TextView tv_address_sh;
    @ViewInject(R.id.tv_shopName)
    private TextView tv_shopName;
    @ViewInject(R.id.tv_ddbh)
    private TextView tv_ddbh;
    @ViewInject(R.id.tv_xdTime)
    private TextView tv_xdTime;
    @ViewInject(R.id.tv_psfs)
    private TextView tv_psfs;
    @ViewInject(R.id.tv_spAll_Price)
    private TextView tv_spAll_Price;
    @ViewInject(R.id.tv_yf_price)
    private TextView tv_yf_price;
    @ViewInject(R.id.tv_yhzk)
    private TextView tv_yhzk;
    @ViewInject(R.id.tv_shopping_num)
    private TextView tv_shopping_num;
    @ViewInject(R.id.tv_all_price)
    private TextView tv_all_price;
    @ViewInject(R.id.tv_yunwei)
    private TextView tv_yunwei;
    @ViewInject(R.id.ll_lx_kf)
    private LinearLayout ll_lx_kf;
    @ViewInject(R.id.ll_bd_phone)
    private LinearLayout ll_bd_phone;
    @ViewInject(R.id.recycler_shopping)
    private RecyclerView recycler_shopping;

    @ViewInject(R.id.tv_text_remove)
    private TextView tv_text_remove;
    @ViewInject(R.id.tv_red_qrsh)
    private TextView tv_red_qrsh;
    private OrderDataBean.ResultBean resultBeans=new OrderDataBean.ResultBean();
    private List<OrderDataBean.ResultBean.GoodsListBean> goodsListBeans=new ArrayList<>();
    private String order_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        upOrderXQData();
    }

    @Override
    protected void initData() {
        tv_title.setText("订单详情");
        order_id=getIntent().getStringExtra("order_id");
        recycler_shopping.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    protected void initView() {
        tv_ddzt_ts.setText(resultBeans.getOrder_status_code());
        tv_ddzt_time.setText(resultBeans.getShipping_status());

        tv_name_phone.setText(resultBeans.getConsignee()+"   "+resultBeans.getMobile());
        tv_address_sh.setText("地址："+resultBeans.getProvince()+resultBeans.getCity()+resultBeans.getDeleted()+resultBeans.getTwon()+resultBeans.getAddress());
        tv_shopName.setText(resultBeans.getStore_name());
        tv_ddbh.setText("订单编号："+resultBeans.getOrder_sn());
        tv_xdTime.setText("下单时间："+resultBeans.getAdd_time());
        tv_psfs.setText("配送方式：普通快递");
        tv_spAll_Price.setText("+￥"+resultBeans.getGoods_price());
        tv_yf_price.setText("+￥"+resultBeans.getShipping_price());
        tv_yhzk.setText("-￥"+resultBeans.getShipping_price());
        tv_shopping_num.setText("共 "+resultBeans.getGoods_nums()+" 件商品 合计:");
        tv_yunwei.setText("（含运费"+resultBeans.getShipping_price()+"）");

        tv_all_price.setText(resultBeans.getOrder_amount());
        recycler_shopping.setAdapter(new BaseRVAdapter(this,goodsListBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.order_xqshoppingg_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, int position) {
                RoundImageView iv_image_shopping=holder.getView(R.id.iv_image_shopping);
                Glide.with(OrderXQActivity.this).load(goodsListBeans.get(position).getImage()).error(R.mipmap.myy322x).into(iv_image_shopping);

                holder.getTextView(R.id.tv_shopping_name).setText(goodsListBeans.get(position).getGoods_name());
                holder.getTextView(R.id.tv_shopping_qpl).setText(goodsListBeans.get(position).getSpec_key_name());
                holder.getTextView(R.id.tv_shopping_price).setText("¥"+goodsListBeans.get(position).getGoods_price()+"/件");
                holder.getTextView(R.id.tv_shoping_Num).setText("共"+goodsListBeans.get(position).getGoods_num()+"件");
            }

        });
        //所有按钮 1-显示 0 不显示
        if(resultBeans.getPay_btn()==1){//支付按钮
            tv_red_right.setVisibility(View.VISIBLE);
            tv_red_right.setText("立即付款");
        }
        if(resultBeans.getCancel_btn()==1){//取消订单
            tv_text1.setVisibility(View.VISIBLE);
            tv_text1.setText("取消订单");
        }
        if(resultBeans.getReceive_btn()==1){//确认收获
            tv_red_qrsh.setVisibility(View.VISIBLE);
            tv_red_qrsh.setText("确认收获");
        }else{
            tv_red_qrsh.setVisibility(View.GONE);
        }
        if(resultBeans.getComment_btn()==1){//评价
            tv_red_rightpj.setVisibility(View.VISIBLE);
        }else{
            tv_red_rightpj.setVisibility(View.GONE);
        }
        if(resultBeans.getShipping_btn()==1){//查看物流
            tv_text2.setVisibility(View.VISIBLE);
            tv_text2.setText("查看物流");
        }else{
            tv_text2.setVisibility(View.GONE);

        }
        if(resultBeans.getDel_btn()==1){//删除订单
            tv_text_remove.setVisibility(View.VISIBLE);
            tv_text_remove.setText("删除订单");
        }else{
            tv_text_remove.setVisibility(View.GONE);
        }
        if(resultBeans.getReturn_btn()==1){//立即退货
            tv_text3.setVisibility(View.VISIBLE);
            tv_text3.setText("申请退货");
        }

    }
    @Event(value = {R.id.rl_close,R.id.tv_text1,R.id.tv_red_right,R.id.ll_bd_phone,R.id.ll_lx_kf,R.id.tv_text2,R.id.tv_red_rightpj,R.id.tv_red_qrsh,R.id.tv_text_remove}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_text2://查看物流
                startActivity(new Intent(this, LookWuLiuActivity.class)
                        .putExtra("order_id",resultBeans.getOrder_id()));
                break;
            case R.id.tv_text1://取消订单
                upCancleCartData();

                break;
            case R.id.tv_text_remove://删除
                upremoveCartData();
                break;
            case R.id.tv_red_rightpj://评价
                startActivity(new Intent(this, PostEvaluationActivity.class)
                        .putExtra("order_id",resultBeans.getOrder_id()));

                break;
            case R.id.tv_red_right://立即付款

                showPop(tv_red_right);

                break;
            case R.id.tv_red_qrsh://确认收获
                upQRSHData();

                break;
            case R.id.ll_lx_kf:
                try {
                    if(BaseApplication.getInstance().userBean!=null){
                        if (BaseApplication.getInstance().userBean.getChat_id()!=null&&!BaseApplication.getInstance().userBean.getChat_id().equals("")) {
                            Log.e("====进入聊天界面  IMG===", BaseApplication.getInstance().userBean.getChat_id()+"");

                            if(resultBeans!=null&&resultBeans.getStore_qq()!=null){
                                RongTalk.doConnection(OrderXQActivity.this, BaseApplication.getInstance().userBean.getChat_id()
                                        ,resultBeans.getStore_qq(), resultBeans.getStore_name(),
                                        "",resultBeans.getStore_id());
                            }else{
                                CusToast.showToast("该店铺无效");
                            }

                        }
                    }else{
                        CusToast.showToast("token失效");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                    CusToast.showToast("该店铺无效");
                }
                break;
            case R.id.ll_bd_phone:
                showPopphone(ll_bd_phone);
                break;
        }
    }
    private PopupWindow mPopPhone;
    //初始化弹窗
    private void initPopphone() {
        if (mPopPhone == null) {
            View viewpone = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
            mPopPhone = new PopupWindow(viewpone, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopPhone.setFocusable(true);
            mPopPhone.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopPhone.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopPhone.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopPhone.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListenerphone(viewpone);
        }
    }

    //显示弹窗
    private void showPopphone(View v) {
        initPopphone();
        if (mPopPhone.isShowing())
            return;
        //设置弹窗底部位置
        mPopPhone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private void setPopClickListenerphone(View view) {
        TextView tv_iteam1, photo, cancle;
        photo = view.findViewById(R.id.photo);
        cancle = view.findViewById(R.id.cancle);
        tv_iteam1=view.findViewById(R.id.tv_iteam1);
        tv_iteam1.setText(R.string.kefudian);
        photo.setText("呼叫："+resultBeans.getStore_phone());
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri datav = Uri.parse("tel:" + resultBeans.getStore_phone());
                intent.setData(datav);
                startActivity(intent);
                mPopPhone.dismiss();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopPhone.dismiss();
            }
        });
    }
    private void upQRSHData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",resultBeans.getOrder_id());
        XUtil.Post(URLConstant.QUERENSH_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====确认收获===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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
                ex.printStackTrace();
            }
        });
    }
    private void upOrderXQData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        showDialog();
        XUtil.Post(URLConstant.QUERENSH_XIANGQING, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====订单详情===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        OrderDataBean orderDataBean=gson.fromJson(result,OrderDataBean.class);
                        resultBeans=orderDataBean.getResult();
                        goodsListBeans=orderDataBean.getResult().getGoods_list();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    private void upCancleCartData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",resultBeans.getOrder_id());
        showDialog();
        XUtil.Post(URLConstant.CANCLE_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====取消订单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    private void upremoveCartData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",resultBeans.getOrder_id());
        showDialog();
        XUtil.Post(URLConstant.SHANCHUDINGDAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====删除订单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    private PopupWindow mPop;

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(OrderXQActivity.this).inflate(R.layout.cart_close_account_layout, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(true);
            mPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }
    private int PayType=0;
    private TextView tv_sure;
    private void setPopClickListener(View view) {
        final ImageView iv_cancle,iv_ye_pay,iv_wecat_pay,iv_zfb_pay;
        iv_cancle = view.findViewById(R.id.iv_cancle);
        iv_ye_pay = view.findViewById(R.id.iv_ye_pay);
        iv_wecat_pay = view.findViewById(R.id.iv_wecat_pay);
        iv_zfb_pay = view.findViewById(R.id.iv_zfb_pay);


        TextView tv_payPrice=view.findViewById(R.id.tv_payPrice);
        LinearLayout ll_ye_pay=view.findViewById(R.id.ll_ye_pay);
        LinearLayout ll_wecat_pay=view.findViewById(R.id.ll_wecat_pay);
        LinearLayout ll_zfb_pay=view.findViewById(R.id.ll_zfb_pay);

        tv_payPrice.setText("¥"+resultBeans.getOrder_amount());

        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        ll_ye_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=1;
                iv_ye_pay.setImageResource(R.drawable.xuanzhongf);
                iv_wecat_pay.setImageResource(R.drawable.weixuanzhong);
                iv_zfb_pay.setImageResource(R.drawable.weixuanzhong);
            }
        });
        ll_zfb_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=2;
                iv_zfb_pay.setImageResource(R.drawable.xuanzhongf);
                iv_ye_pay.setImageResource(R.drawable.weixuanzhong);
                iv_wecat_pay.setImageResource(R.drawable.weixuanzhong);

            }
        });
        ll_wecat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=3;
                iv_wecat_pay.setImageResource(R.drawable.xuanzhongf);
                iv_ye_pay.setImageResource(R.drawable.weixuanzhong);
                iv_zfb_pay.setImageResource(R.drawable.weixuanzhong);

            }
        });

        tv_sure=view.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PayType==0){
                    CusToast.showToast("请选择支付方式");
                    return;
                } else if(PayType==1){//余额
                    mPop.dismiss();
                    showPop2(tv_sure);

                }else if(PayType==2){//支付宝
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",resultBeans.getMaster_order_sn());
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }else if(PayType==3){//微信
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",resultBeans.getMaster_order_sn());
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }
                mPop.dismiss();

            }
        });
    }
    private void  upYETXData(Map<String,Object> map){
        String url = null;
        if(PayType==1){//余额
            url=URLConstant.ORDER_YUE_PAY;
        }else if(PayType==2){//支付宝
            url=URLConstant.ORDER_ALI_PAY;
        }else if(PayType==3){//微信
            url=URLConstant.ORDER_WECHAT_PAY;
        }
        showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====结算单支付===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        if(PayType==1){//余额
                           finish();
                            CusToast.showToast(msg);
                        }else if(PayType==2){//支付宝
                            AliPayBean aliPayBean = gson.fromJson(result, AliPayBean.class);
                            Message msg2 = Message.obtain();
                            msg2.what = TOALIPAY;
                            msg2.obj = aliPayBean.getResult();
                            mHandler.sendMessage(msg2);
                        }else if(PayType==3){//微信
                            WechatDataBean wechatDataBean=gson.fromJson(result,WechatDataBean.class);
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(OrderXQActivity.this, null);
                            msgApi.registerApp(BaseApplication.getInstance().WXAPP_ID);
                            PayReq req = new PayReq();
                            req.appId = wechatDataBean.getResult().getAppid();
                            req.partnerId = wechatDataBean.getResult().getPartnerid();
                            req.prepayId = wechatDataBean.getResult().getPrepayid();
                            req.nonceStr = wechatDataBean.getResult().getNoncestr();
                            req.timeStamp = wechatDataBean.getResult().getTimestamp();
                            req.packageValue = "Sign=WXPay";
                            req.sign = wechatDataBean.getResult().getSign();
                            BaseApplication.getInstance().api.sendReq(req);
                        }

                    }else if(res.equals("-1")){
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
                ex.printStackTrace();
                closeDialog();

            }
        });
    }
    private String paypass;
    private PopupWindow mPopPay;

    //显示弹窗
    private void showPop2(View v) {
        initPop2();
        if (mPopPay.isShowing())
            return;
        //设置弹窗底部位置
        mPopPay.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }
    //初始化弹窗
    private void initPop2() {
        if (mPopPay == null) {
            View view = LayoutInflater.from(OrderXQActivity.this).inflate(R.layout.ye_pay_layout, null);
            mPopPay = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopPay.setFocusable(true);
            mPopPay.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopPay.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopPay.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopPay.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                   getWindow().setAttributes(lp);
                }
            });
            ImageView iv_cancle = view.findViewById(R.id.iv_cancle);

            TextView tv_payPrice=view.findViewById(R.id.tv_payPrice);
            tv_payPrice.setText("¥"+resultBeans.getOrder_amount());

            iv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopPay.dismiss();
                }
            });
            //设置弹窗内的点击事件
            final PasswordView2 passView=view.findViewById(R.id.passView);
            passView.setOnFinishInput2(new OnPasswordInputFinish2() {
                @Override
                public void inputFinish() {
                    // 输入完成后我们简单显示一下输入的密码
                    // 也就是说——>实现你的交易逻辑什么的在这里写
                    paypass=passView.getStrPassword();

                }

            });
            view.findViewById(R.id.tv_sure_pay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",resultBeans.getMaster_order_sn());
                    map3.put("pay_password", paypass);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());

                    Log.i("=====余额支付--",resultBeans.getMaster_order_sn()+"--"+paypass);
                    upYETXData(map3);
                    mPopPay.dismiss();
                }
            });
        }
    }
    //------------------------------------------------------------支付宝----------------
    private static final int TOALIPAY = 3;
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    //   Log.e("-----", resultInfo + "---" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        CusToast.showToast("支付成功");
                        finish();
//                        setStart(f2ZhiFuBaoBean.getInfo().getPartner(), f2ZhiFuBaoBean.getInfo().getTotal_fee());
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        CusToast.showToast("支付失败");
                    }
                    break;
                }

                case TOALIPAY: {
                    String obj = (String) msg.obj;
                    payV2(obj);
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 支付宝支付业务
     * 支付
     //     * @param v
     */
    public void payV2(final String info) {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        final String orderinfo = "_input_charset=\"" + info.get_input_charset() + "\"&body=\"" + info.getBody() + "\"&notify_url=\"" + info.getNotify_url() + "\"&out_trade_no=\"" + info.getOut_trade_no() + "\"&partner=\"" + info.getPartner() + "\"&payment_type=\"" + info.getPayment_type() + "\"&seller_id=\"" + info.getSeller_id() + "\"&service=\"" + info.getService() + "\"&subject=\"" + info.getSubject() + "\"&total_fee=\"" + info.getTotal_fee() + "\"&sign=\"" + info.getSign() + "\"&sign_type=\"" + info.getSign_type() + "\"";
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderXQActivity.this);
                Map<String, String> result = alipay.payV2(info, true);
//                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
