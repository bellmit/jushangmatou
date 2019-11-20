package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.MyShopActivity;
import com.tem.gettogether.activity.my.decoration.ShopDecorationActivity;
import com.tem.gettogether.activity.my.member.MemberCentreActivity;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.utils.AppManager;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;

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
    protected void initData() {
        x.view().inject(this);
        AppManager.getAppManager().addActivity(this);
        tv_title.setText(R.string.dianpuguanli);
        if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7").equals("2")) {
            rl_title_right.setVisibility(View.GONE);
        } else {
            rl_title_right.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
    }

    @Event(value = {R.id.rl_close, R.id.tv_cpgl, R.id.tv_decoration, R.id.tv_dpxx, R.id.tv_fbcp, R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_cpgl:
                startActivity(new Intent(this, ProductManagmentActivity.class));
                break;
            case R.id.tv_fbcp:
                startActivity(new Intent(this, PublishGoodsActivity.class/*NewAddShoppingActivity.class*/));
                break;
            case R.id.tv_dpxx:
                startActivity(new Intent(this, MyShopActivity.class));
                break;
            case R.id.rl_title_right:
                startActivity(new Intent(this, MemberCentreActivity.class));
                break;
            case R.id.tv_decoration:
                startActivity(new Intent(this, ShopDecorationActivity.class));
                break;
        }
    }
}
