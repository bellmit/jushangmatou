package com.jsmt.developer.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseApplication;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, "wx93eea65ba215f901");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    //处理微信支付回调
    @Override
    public void onResp(BaseResp resp) {
        Log.i(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.errCode==0) {
            BaseApplication.getInstance().isWXPay = resp.errCode;
//            Toast.makeText(WXPayEntryActivity.this, "微信支付成功啦", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            finish();
        }

//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            MyApplication.getInstance().isWXPay = resp.errCode;
//            System.out.println("MainApplication.getInstance().isWXPay" + MyApplication.getInstance().isWXPay);
//            finish();
//        }
    }
}