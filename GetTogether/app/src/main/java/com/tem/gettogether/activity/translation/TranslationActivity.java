package com.tem.gettogether.activity.translation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.translation
 * @ClassName: TranslationActivity
 * @Author: csc
 * @CreateDate: 2019/9/18 10:23
 * @Description:
 */

@ContentView(R.layout.activity_translation)
public class TranslationActivity extends BaseMvpActivity<TranslationPresenter> implements TranslationContract.TranslationView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.text_et)
    private EditText text_et;
    @ViewInject(R.id.result_tv)
    private TextView result_tv;
    @ViewInject(R.id.spinner)
    private Spinner spinner;
    @ViewInject(R.id.spinner_aims)
    private Spinner spinner_aims;
    @ViewInject(R.id.translation_conversion_ll)
    private LinearLayout translation_conversion_ll;
    private String targetId;
    private String fromType="中文";
    private String toType="英文";
    private String[] languages;
    private int spinnerPostion = 0;
    private int spinnerAimsPostion = 0;
    private int postitionA,postitionB;
    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new TranslationPresenter(getContext(), TranslationActivity.this);
        mPresenter.attachView(this);
        tv_title.setText("翻译助手");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("发送");
        targetId = getIntent().getStringExtra("targetId");
        spinner.setSelection(0,true);
        spinner_aims.setSelection(1,true);
        languages = getResources().getStringArray(R.array.user_spingarr);
    }

    @Override
    protected void initView() {
        text_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mPresenter.goToTranslation(fromType,toType,text_et.getText().toString());
                }
                return true;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromType = languages[position];
                spinnerPostion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_aims.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toType = languages[position];
                spinnerAimsPostion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Event(value = {R.id.rl_close, R.id.delete_iv, R.id.tv_title_right,R.id.translation_conversion_ll})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.delete_iv:
                text_et.setText("");
                result_tv.setVisibility(View.GONE);
                result_tv.setText("");
                break;
            case R.id.tv_title_right:
                mPresenter.goSendTranslationMsg(text_et.getText().toString(),result_tv.getText().toString(),targetId);
                break;
            case R.id.translation_conversion_ll:
                spinner.setSelection(postitionB);
                spinner_aims.setSelection(postitionA);
                postitionA = spinnerPostion;
                postitionB = spinnerAimsPostion;
                break;
        }
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        closeDialog();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void getTranslationResult(String txt) {
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = txt;
        mHandle.sendMessage(msg);
    }
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    result_tv.setVisibility(View.VISIBLE);
                    result_tv.setText("" + msg.obj);
                    break;
            }
        }
    };
}
