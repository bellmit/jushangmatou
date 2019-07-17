package com.jsmt.developer.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_my_pocket)
public class MyPocketActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private String money;
    @ViewInject(R.id.tv_yE_money)
    private TextView tv_yE_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("我的钱包");
        money=getIntent().getStringExtra("money");
        tv_yE_money.setText(money);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.rl_zhaq,R.id.rl_tixian,R.id.ll_chognzhi,R.id.ll_tixian}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_zhaq:
                startActivity(new Intent(this,ChongZhiActivity.class));
                break;
            case R.id.rl_tixian:
                startActivity(new Intent(this,TiXianActivity.class));
                break;

            case R.id.ll_chognzhi:
                startActivity(new Intent(this,YEChongzhiActivity.class));

                break;
            case R.id.ll_tixian:
                startActivity(new Intent(this,YETixianActivity.class));

                break;


        }
    }
}
