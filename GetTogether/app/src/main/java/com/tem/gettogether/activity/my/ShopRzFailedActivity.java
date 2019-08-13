package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.Contacts;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_shop_rz_failed)
public class ShopRzFailedActivity extends BaseActivity {
    @ViewInject(R.id.reason_detail_tv)
    private TextView reason_detail_tv;
    @ViewInject(R.id.rz_btn)
    private Button rz_btn;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    private String message;

    @Override
    protected void initData() {
        x.view().inject(this);
        message = getIntent().getStringExtra(Contacts.SHOP_RZ_FAILED);
        reason_detail_tv.setText(message);
        tv_title.setText("商铺认证");
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close,R.id.rz_btn}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rz_btn:
                startActivity(new Intent(this, ShopAuthenticationActivity.class));
                break;
        }
    }
}
