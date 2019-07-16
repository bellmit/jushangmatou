package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.ShopXinXActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_store_management)
public class StoreManagementActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rl_title_right)
    private RelativeLayout rl_title_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.dianpuguanli);
        rl_title_right.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_cpgl,R.id.tv_dpxx,R.id.tv_fbcp,R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_cpgl:
//                CusToast.showToast("暂未开放");
                startActivity(new Intent(this,ChanPinGLActivity.class));
                break;
            case R.id.tv_fbcp:
                startActivity(new Intent(this,NewAddShoppingActivity.class));
                break;
            case R.id.tv_dpxx:
                startActivity(new Intent(this, ShopXinXActivity.class));
                break;
            case R.id.rl_title_right:
                startActivity(new Intent(this,BuyMemberActivity.class));

                break;

        }
    }
}
