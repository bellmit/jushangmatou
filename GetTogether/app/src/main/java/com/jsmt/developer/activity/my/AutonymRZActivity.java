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

@ContentView(R.layout.activity_autonym_rz)
public class AutonymRZActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("实名认证");
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_tijiao}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_tijiao:
                startActivity(new Intent(this,AutonymSHActivity.class));
                break;

        }
    }
}
