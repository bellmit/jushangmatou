package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_shop_rz_failed)
public class ShopRzFailedActivity extends BaseActivity {
    @ViewInject(R.id.reason_detail_tv)
    private TextView reason_detail_tv;
    @ViewInject(R.id.rz_btn)
    private Button rz_btn;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    private String message;
    private int rz_type;//0:商铺认证 1：采购商认证

    @Override
    protected void initData() {
        x.view().inject(this);
        message = getIntent().getStringExtra(Contacts.SHOP_RZ_FAILED);
        rz_type = getIntent().getIntExtra(Contacts.RZ_TYPE, 0);
        if (rz_type == 0) {
            tv_title.setText("商铺认证");
            reason_detail_tv.setText(message);
        } else {
            tv_title.setText("采购商认证");
            getData();
        }
    }

    private void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        showDialog();
        XUtil.Post(URLConstant.CAIGOUSHANG_RZ_REASON, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====采购商认证失败原因===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        reason_detail_tv.setText(jsonObject.optString("result"));
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

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close, R.id.rz_btn}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rz_btn:
                if (rz_type == 0) {
                    startActivity(new Intent(this, ShopAuthenticationActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(this, CgsAuthenticationActivity.class));
                    finish();
                }
                break;
        }
    }
}
