package com.tem.gettogether.activity.my;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AliPayBean;
import com.tem.gettogether.bean.WechatDataBean;
import com.tem.gettogether.utils.PayResult;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_yechongzhi)
public class YEChongzhiActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_chongz_fs)
    private  LinearLayout ll_chongz_fs;
    @ViewInject(R.id.tv_pay_fs)
    TextView tv_pay_fs;
    @ViewInject(R.id.et_czje)
    private EditText et_czje;
    private int PayType=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {
        tv_title.setText("余额充值");
    }
    @Event(value = {R.id.rl_close,R.id.ll_chongz_fs,R.id.tv_sure}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_chongz_fs:
                showPop(ll_chongz_fs);

                break;
            case R.id.tv_sure:
                String czje=et_czje.getText().toString();
                if(!czje.equals("")&&Integer.parseInt(czje)>10){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("money",czje);
                    map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                    upYETXData(map3);
                }else {
                    CusToast.showToast("充值金额不能少于10");
                    return;
                }
                break;
        }
    }
    private void  upYETXData(Map<String,Object> map){
        String url = null;
        if(PayType==0){
            CusToast.showToast("请选择支付方式");
            return;
        }else if(PayType==1){//支付宝
            url=URLConstant.YECZ_ZFB;
        }else if(PayType==2){//微信
            url=URLConstant.YECZ_WX;
        }
        showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====余额充值===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        if(PayType==1){//支付宝
                            AliPayBean aliPayBean = gson.fromJson(result, AliPayBean.class);
                            Message msg2 = Message.obtain();
                            msg2.what = TOALIPAY;
                            msg2.obj = aliPayBean.getResult();
                            mHandler.sendMessage(msg2);
                        }else if(PayType==2){//微信
                            WechatDataBean wechatDataBean=gson.fromJson(result,WechatDataBean.class);
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(YEChongzhiActivity.this, null);
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
    private PopupWindow mPop;


    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.chongzhi_layout, null);
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

    private void setPopClickListener(View view) {
        ImageView iv_close;
         final ImageView iv_xz_zt2,iv_xz_zt3,iv_xz_zt;
        iv_close = view.findViewById(R.id.iv_close);
        iv_xz_zt2 = view.findViewById(R.id.iv_xz_zt2);
        iv_xz_zt3= view.findViewById(R.id.iv_xz_zt3);
        iv_xz_zt = view.findViewById(R.id.iv_xz_zt);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        iv_xz_zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt2.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt3.setImageResource(R.drawable.weixuanzhong);
                tv_pay_fs.setText("支付宝充值");
                PayType=1;
                mPop.dismiss();
            }
        });
        iv_xz_zt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt2.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt3.setImageResource(R.drawable.weixuanzhong);
                tv_pay_fs.setText("微信充值");
                PayType=2;
                mPop.dismiss();
            }
        });
        iv_xz_zt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt3.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt2.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt.setImageResource(R.drawable.weixuanzhong);
                tv_pay_fs.setText("银联充值");
                PayType=3;
                mPop.dismiss();
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
                PayTask alipay = new PayTask(YEChongzhiActivity.this);
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
