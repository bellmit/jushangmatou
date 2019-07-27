package com.tem.gettogether.activity.my;

import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_shop_management)

public class ShopManagementActivity extends BaseActivity{

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("店铺管理");
    }

    @Override
    protected void initView() {

    }

}
