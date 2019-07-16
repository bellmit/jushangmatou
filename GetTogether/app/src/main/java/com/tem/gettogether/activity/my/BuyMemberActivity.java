package com.tem.gettogether.activity.my;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.ShoppingKUActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.PayResult;
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

@ContentView(R.layout.buy_member)
public class BuyMemberActivity extends BaseActivity {
    @ViewInject(R.id.choose_1)
    private LinearLayout choose_1;
    @ViewInject(R.id.choose_2)
    private LinearLayout choose_2;
    @ViewInject(R.id.choose_3)
    private LinearLayout choose_3;
    @ViewInject(R.id.rad_1)
    private ImageView rad_1;
    @ViewInject(R.id.rad_2)
    private ImageView rad_2;
    @ViewInject(R.id.rad_3)
    private ImageView rad_3;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_pay)
    private TextView tv_pay;


    /** 区别三种支付方式 0:我的钱包 1:支付宝 2:微信支付 **/
    public  String payWay = "zfb";
    public  String result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }
    @Event(value = {R.id.rl_close})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }
    @Override
    protected void initData() {

    }



    @Override

    protected void initView() {
        tv_title.setText("开通会员");
        choose_1=findViewById(R.id.choose_1);
        choose_2=findViewById(R.id.choose_2);
        choose_3=findViewById(R.id.choose_3);
        rad_1=findViewById(R.id.rad_1);
        rad_2=findViewById(R.id.rad_2);
        rad_3=findViewById(R.id.rad_3);

        choose_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_1.setImageResource(R.drawable.start_select);
                rad_2.setImageResource(R.drawable.start_noselect);
                rad_3.setImageResource(R.drawable.start_noselect);
                payWay="zfb";
            }
        });
        choose_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_1.setImageResource(R.drawable.start_noselect);
                rad_2.setImageResource(R.drawable.start_select);
                rad_3.setImageResource(R.drawable.start_noselect);
                payWay="wx";
            }
        });
        choose_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_1.setImageResource(R.drawable.start_noselect);
                rad_2.setImageResource(R.drawable.start_noselect);
                rad_3.setImageResource(R.drawable.start_select);
                payWay="yhk";
            }
        });
        tv_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payWay=="zfb"){
                    pay_zfb();

                }
                else if(payWay=="wx"){
                   wx();
                }
                else if(payWay=="yhk"){
                    startActivity(new Intent(BuyMemberActivity.this, ShoppingKUActivity.class));
                    Log.d("","yhk");
                }
            }
        });
    }
    //微信
    private void wx() {
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        XUtil.Post(URLConstant.DIANPU_WX_FUWUFEI, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    String result2 = jsonObject.optString("result");
                    JSONObject request = new JSONObject(result2);
                    String appid = request.optString("appid");
                    String noncestr = request.optString("noncestr");
                    String partnerid = request.optString("partnerid");
                    String prepayid = request.optString("prepayid");
                    String timestamp = request.optString("timestamp");
                    String sign = request.optString("sign");
                    Log.d("123",""+result2);

                    if (res.equals("1")) {
//                        Gson gson=new Gson();
//                        WechatDataBean wechatDataBean=gson.fromJson(result2,WechatDataBean.class);
                        final IWXAPI msgApi = WXAPIFactory.createWXAPI(BuyMemberActivity.this, null);
                        msgApi.registerApp(BaseApplication.getInstance().WXAPP_ID);
                        PayReq req = new PayReq();
                        req.appId = appid;
                        req.partnerId = partnerid;
                        req.prepayId = prepayid;
                        req.nonceStr = noncestr;
                        req.timeStamp =timestamp;
                        req.packageValue = "Sign=WXPay";
                        req.sign = sign;
                        BaseApplication.getInstance().api.sendReq(req);
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
                ex.printStackTrace();
            }
        });
    }

    //支付宝
    private void pay_zfb() {
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        XUtil.Post(URLConstant.DIANPU_ZFB_FUWUFEI, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        String result2 = jsonObject.optString("result");
                        Log.d("123",""+result2);
                        payV2(result2);
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
                ex.printStackTrace();
            }
        });
    }

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
                PayTask alipay = new PayTask(BuyMemberActivity.this);
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