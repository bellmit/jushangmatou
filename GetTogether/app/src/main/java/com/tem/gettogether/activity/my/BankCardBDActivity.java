package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.utils.StatusBarUtil;

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
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
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
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
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
                    CusToast.showToast(getText(R.string.please_type_in_your_name));
                    return;
                }else if(Num.equals("")){
                    CusToast.showToast(getText(R.string.enter_bank_card_number));
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
