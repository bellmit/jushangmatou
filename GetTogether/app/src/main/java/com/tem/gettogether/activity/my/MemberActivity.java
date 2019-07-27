package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_member)
public class MemberActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }
    @Event(value = {R.id.rl_close, R.id.start_member}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.start_member:
                startActivity(new Intent(MemberActivity.this, BuyMemberActivity.class));
        }
    }
    @Override
    protected void initData() {

    }

    @Override

    protected void initView() {

    }
}
