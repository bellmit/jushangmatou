package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.CountDownTimerUtils2;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_shop_rz)
public class ShopRZActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_gain_cade)
    private TextView tv_gain_cade;
    @ViewInject(R.id.et_name)
    private EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.shopRZ);
        closeKeybord(et_name,this);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_gain_cade}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                closeKeybord(et_name,this);
                finish();
                break;
            case R.id.tv_gain_cade:
                CountDownTimerUtils2 mCountDownTimerUtils = new CountDownTimerUtils2(tv_gain_cade, 60000, 1000);
                mCountDownTimerUtils.start();
                break;

        }
    }
}
