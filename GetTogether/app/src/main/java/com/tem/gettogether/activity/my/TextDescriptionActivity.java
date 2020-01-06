package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_text_description)
public class TextDescriptionActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.text_description_et)
    private EditText text_description_et;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    private String textDescription;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        tv_title.setText(getText(R.string.ignored_by_settings));
        textDescription = getIntent().getStringExtra("text_description");
        text_description_et.setText(textDescription);
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close, R.id.tv_save}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_save:
                if (text_description_et.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.the_content_can_not_be_blank));
                } else {
                    setResult(RESULT_OK, new Intent().putExtra("text_description", text_description_et.getText().toString()));
                    finish();
                }
                break;
        }
    }

}
