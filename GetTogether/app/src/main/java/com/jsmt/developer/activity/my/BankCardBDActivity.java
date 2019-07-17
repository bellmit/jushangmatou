package com.jsmt.developer.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_bank_card_bd)
public class BankCardBDActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_yhanka)
    private EditText et_yhanka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        BaseApplication.addDestoryActivity(this, "bang");
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.yinhangka_bd);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_xiayibu}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_xiayibu://下一步
                String Name=et_name.getText().toString();
                String Num=et_yhanka.getText().toString();
                if(Name.equals("")){
                    CusToast.showToast("请输入姓名");
                    return;
                }else if(Num.equals("")){
                    CusToast.showToast("请输入银行卡号");
                    return;
                }else{
                    startActivity(new Intent(this,BankCardQRActivity.class)
                    .putExtra("Name",Name)
                    .putExtra("Num",Num));
                }
                break;

        }
    }
}
