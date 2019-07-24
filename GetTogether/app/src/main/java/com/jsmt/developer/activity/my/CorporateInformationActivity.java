package com.jsmt.developer.activity.my;

import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_corporate_information)

public class CorporateInformationActivity extends BaseActivity{
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getResources().getText(R.string.qiyexinxi));
    }

    @Override
    protected void initView() {

    }
}
