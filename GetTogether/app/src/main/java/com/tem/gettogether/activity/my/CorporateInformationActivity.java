package com.tem.gettogether.activity.my;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.Contacts;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_corporate_information)

public class CorporateInformationActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private int informationType = 0;// 0:企业信息  1:个人信息
    @ViewInject(R.id.card2_ll)
    private LinearLayout card2_ll;
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(informationType==0?getResources().getText(R.string.qiyexinxi):getResources().getText(R.string.gerenxinxi));
        informationType = getIntent().getIntExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0);
        card2_ll.setVisibility(informationType==0? View.VISIBLE:View.GONE);
    }

    @Override
    protected void initView() {

    }
}
