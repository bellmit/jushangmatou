package com.tem.gettogether.activity;

import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_my_shop)
public class MyShopActivity extends BaseActivity{
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("我的店铺");
    }

    @Override
    protected void initView() {

    }
}

