package com.jsmt.developer.activity.my.shopauthentication;

import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_persion_second_page)
public class PersionAuthenticationSecondActivity extends BaseActivity{
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("个人");
    }

    @Override
    protected void initView() {

    }
}
