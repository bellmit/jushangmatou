package com.tem.gettogether.activity.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.AddressGLActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AliPayBean;
import com.tem.gettogether.bean.JieSPriceBean;
import com.tem.gettogether.bean.JieSuanBean;
import com.tem.gettogether.bean.WechatDataBean;
import com.tem.gettogether.utils.PayResult;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.OnPasswordInputFinish2;
import com.tem.gettogether.view.PasswordView2;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_close_account)
public class CloseAccountActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_cart_jian)
    private  TextView tv_cart_jian;
    @ViewInject(R.id.tv_connectNum)
    private  TextView tv_connectNum;
    @ViewInject(R.id.tv_cart_add)
    private  TextView tv_cart_add;
    @ViewInject(R.id.tv_tijiao)
    private TextView tv_tijiao;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.tv_yunfei)
    private TextView tv_yunfei;
    @ViewInject(R.id.tv_shopping_price)
    private TextView tv_shopping_price;
    @ViewInject(R.id.tv_lx_phone)
    private TextView tv_lx_phone;
    @ViewInject(R.id.tv_lxr)
    private TextView tv_lxr;
    @ViewInject(R.id.tv_zongjia)
    private TextView tv_zongjia;
    @ViewInject(R.id.tv_address)
    private  TextView tv_address;
    @ViewInject(R.id.rl_psfs)
    private RelativeLayout rl_psfs;
    @ViewInject(R.id.rl_title_right)
    private RelativeLayout rl_title_right;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    private String cartid;
    private JieSuanBean.ResultBean.AddressListBean addressListBeans=new JieSuanBean.ResultBean.AddressListBean();
    private JieSuanBean.ResultBean.TotalPriceBean totalPriceBean=new JieSuanBean.ResultBean.TotalPriceBean();
    private List<JieSuanBean.ResultBean.StoreListBean> storeListBeans=new ArrayList<>();
    private JieSPriceBean.ResultBean resultBean=new JieSPriceBean.ResultBean();
    private String cart_ids;
    private String goods_id,unique_id,goods_num,key;
    private int ADDRESS=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();

    }

    @Override
    protected void initData() {
        tv_title.setText("结算单");
        cartid=getIntent().getStringExtra("cartid");
        goods_id=getIntent().getStringExtra("goods_id");
        unique_id=getIntent().getStringExtra("unique_id");
        goods_num=getIntent().getStringExtra("goods_num");
        key=getIntent().getStringExtra("key");
        rl_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("修改地址");
        upJieSCartData();
    }

    @Override
    protected void initView() {
        tv_lxr.setText("联系人："+addressListBeans.getConsignee());
        tv_lx_phone.setText("联系电话："+addressListBeans.getMobile());
        tv_shopping_price.setText("¥"+resultBean.getGoodsFee());
        tv_yunfei.setText("¥"+resultBean.getPostFee());
        tv_zongjia.setText("合计金额：￥"+resultBean.getPayables());
        tv_address.setText("配送地址："+addressListBeans.getProvince()+addressListBeans.getCity()+addressListBeans.getDistrict()+addressListBeans.getTwon()+addressListBeans.getAddress());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        if (storeListBeans.size()>=1){
            recyclerView.setAdapter(new BaseRVAdapter(this,storeListBeans) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.jiesuandan_item;
                }

                @Override
                public void onBind(BaseViewHolder holder, final int position) {
                    holder.getTextView(R.id.tv_shop_name).setText(storeListBeans.get(position).getStore_name());

                    RecyclerView recyclerView_item=holder.getView(R.id.recyclerView_item);
                    recyclerView_item.setLayoutManager(new LinearLayoutManager(CloseAccountActivity.this,LinearLayoutManager.VERTICAL,false));
                    recyclerView_item.setAdapter(new BaseRVAdapter(CloseAccountActivity.this,storeListBeans.get(position).getCartList()) {
                        @Override
                        public int getLayoutId(int viewType) {
                            return R.layout.shopping_item_recy;
                        }

                        @Override
                        public void onBind(BaseViewHolder holder, int position2) {
                            ImageView iv_image=holder.getImageView(R.id.iv_image);
                            Glide.with(CloseAccountActivity.this).load(storeListBeans.get(position).getCartList().get(position2).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image);

                            holder.getTextView(R.id.tv_shop_ms).setText(storeListBeans.get(position).getCartList().get(position2).getGoods_name());
                            holder.getTextView(R.id.tv_shuxing).setText(storeListBeans.get(position).getCartList().get(position2).getSpec_key_name());
                            holder.getTextView(R.id.tv_price).setText("￥"+storeListBeans.get(position).getCartList().get(position2).getGoods_price());
                            holder.getTextView(R.id.tv_num).setText("x"+storeListBeans.get(position).getCartList().get(position2).getGoods_num());
                        }

                    });

                }

            });
        }

    }
    @Event(value = {R.id.rl_close,R.id.tv_cart_add,R.id.tv_cart_jian,R.id.tv_tijiao,R.id.rl_psfs,R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_cart_add:
                int numadd=Integer.parseInt(tv_connectNum.getText().toString().trim());
                numadd++;
                tv_connectNum.setText(numadd+"");
                break;
            case R.id.tv_cart_jian:
                int numJian=Integer.parseInt(tv_connectNum.getText().toString().trim());
                if(numJian<=1){
                    CusToast.showToast("数量不能少于1");
                    return;
                }
                numJian--;
                tv_connectNum.setText(numJian+"");
                break;
            case R.id.rl_psfs:
//                showPopPSFS(rl_psfs);
                break;
            case R.id.tv_tijiao:
                upTJDDData();
                break;
            case R.id.rl_title_right:
                if(unique_id!=null&&unique_id.equals("1")){
                    startActivityForResult(new Intent(this,AddressGLActivity.class)
                            .putExtra("unique_id",unique_id)
                            .putExtra("goods_id",goods_id)
                            .putExtra("goods_num",goods_num)
                            .putExtra("key",key),ADDRESS);
                }else{
                    startActivityForResult(new Intent(this,AddressGLActivity.class)
                            .putExtra("unique_id","2")
                            .putExtra("cart_ids",cart_ids),ADDRESS);
                }

                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADDRESS&&resultCode == 101) {
            unique_id=data.getStringExtra("unique_id");
            if(unique_id.equals("2")){
                cart_ids=data.getStringExtra("cart_ids");
            }else {
                goods_id=data.getStringExtra("goods_id");
                goods_num=data.getStringExtra("goods_num");
                key=getIntent().getStringExtra("key");
            }
            upJieSCartData();
        }
    }

    private String orderNum;
    private void upTJDDData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        Log.d("chenshichun","======addressListBeans.getAddress_id()=====  "+addressListBeans.getAddress_id());
        map.put("address_id", addressListBeans.getAddress_id());
        map.put("act", "submit_order");
//        map.put("shipping_code["+ storeListBeans.get(0).getStore_id()+"]","shentong");
        map.put("ids", cart_ids);

        showDialog();
        XUtil.Post(URLConstant.TIJIAO_DiNDDAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====提交订单===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        orderNum = jsonObject.optString("result");
                        showPop(tv_tijiao);
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
            }
        });
    }

    private void upJieSCartData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        if(unique_id!=null&&unique_id.equals("1")){
            map.put("goods_id", goods_id);
            map.put("goods_num", goods_num);
            map.put("unique_id", unique_id);
            if(key!=null&&!key.equals("")){
                map.put("key", key);
            }
        }else{
            if(cartid!=null&&!cartid.equals("")){
                map.put("ids", cartid);
            }
//                map.put("goods_num", goods_num);
//                map.put("goods_id", goods_id);
//                map.put("key", key);
        }

        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--结算参数打印--" + key, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.JIESUAN_PAY, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====结算单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        JieSuanBean jieSuanBean=gson.fromJson(result,JieSuanBean.class);
                        addressListBeans=jieSuanBean.getResult().getAddressList();
                        totalPriceBean=jieSuanBean.getResult().getTotalPrice();
                        storeListBeans=jieSuanBean.getResult().getStoreList();
                        cart_ids=jieSuanBean.getResult().getCart_ids();
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
                initView();
                upHQpriceData();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }
    private void upHQpriceData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        if(addressListBeans.getAddress_id()!=null){
            map.put("address_id", addressListBeans.getAddress_id()+"");
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
                        JieSPriceBean jieSPriceBean=gson.fromJson(result,JieSPriceBean.class);
                        resultBean=jieSPriceBean.getResult();
                    }else  if (res.equals("-1")){
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
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }
//    private PopupWindow mPopPSFS;
//
//    //显示弹窗
//    private void showPopPSFS(View v) {
//        if (mPopPSFS == null) {
//            View view = LayoutInflater.from(this).inflate(R.layout.jiesuan_psfs_layout, null);
//            mPopPSFS = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            //点击弹窗外消失mPop
//            mPopPSFS.setFocusable(true);
//            mPopPSFS.setOutsideTouchable(true);
//            //设置背景，才能使用动画效果
//            mPopPSFS.setBackgroundDrawable(new BitmapDrawable());
//            //设置动画
//            mPopPSFS.setAnimationStyle(R.style.PopWindowAnim);
//            //设置弹窗消失监听
//            mPopPSFS.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    WindowManager.LayoutParams lp = getWindow().getAttributes();
//                    lp.alpha = 1f;
//                    getWindow().setAttributes(lp);
//                }
//            });
//            view.findViewById(R.id.iv_cancle).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mPop.dismiss();
//                }
//            });
//           RecyclerView recycler_psfs=view.findViewById(R.id.recycler_psfs);
//            recycler_psfs.setLayoutManager(new LinearLayoutManager(CloseAccountActivity.this,LinearLayoutManager.VERTICAL,false));
//            recycler_psfs.setAdapter(new BaseRVAdapter(this) {
//                @Override
//                public int getLayoutId(int viewType) {
//                    return R.layout.peisongfan;
//                }
//
//                @Override
//                public void onBind(BaseViewHolder holder, int position) {
//
//                }
//
//            });
//        }
//        if (mPopPSFS.isShowing())
//            return;
//        //设置弹窗底部位置
//        mPopPSFS.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.6f;
//        getWindow().setAttributes(lp);
//    }

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
            View view = LayoutInflater.from(this).inflate(R.layout.cart_close_account_layout, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(false);
            mPop.setOutsideTouchable(false);
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

        tv_payPrice.setText("¥"+resultBean.getPayables());

        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
                finish();
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
                }else if(PayType==1){//余额
                    mPop.dismiss();
                    showPop2(tv_sure);

                }else if(PayType==2){//支付宝
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",orderNum);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }else if(PayType==3){//微信
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",orderNum);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }
                mPop.dismiss();


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
            View view = LayoutInflater.from(this).inflate(R.layout.ye_pay_layout, null);
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
            tv_payPrice.setText("¥"+resultBean.getPayables());

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
                    map3.put("master_order_sn",orderNum);
                    map3.put("pay_password", paypass);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());

                    upYETXData(map3);
                    mPopPay.dismiss();
                }
            });

        }
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
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(CloseAccountActivity.this, null);
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
                PayTask alipay = new PayTask(CloseAccountActivity.this);
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
