package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.dialog.DialogBuilder;
import com.tem.gettogether.dialog.Effectstype;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/*
* 不用的类
* */
@ContentView(R.layout.activity_glaccount)
public class GLAccountActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(getText(R.string.linked_account));
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.rl_wechat}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_wechat:
                final DialogBuilder dialogLogout = new DialogBuilder(GLAccountActivity.this, R.style.dialog_untran);
                Effectstype effect = Effectstype.SHAKE;
                dialogLogout.isvisibiliby();
                dialogLogout
                        .withTitle(getText(R.string.determine_disassociation))
                        .withTitleColor("#000000")                                  //def
                        .withDividerColor("#000000")                              //def
                        .withMessage("解绑微信账号后将无法继续使用它登录\n" +
                                "该账号")                     //.withMessage(null)  no Msg
                        .withMessageColor("#000000")                                //def
                        //.withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                        .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                        .withDuration(0)                                          //def    数值约大动画越明显
                        .withEffect(effect)                                         //def Effectstype.Slidetop
                        .withButton1Text(getText(R.string.quxiao))
                        .withButton2Text(getText(R.string.queding))
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogLogout.dismiss();
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogLogout.dismiss();

                            }
                        })
                        .show();
                break;


        }
    }
}
