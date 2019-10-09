package com.tem.gettogether.activity.speechtranslation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cc.duduhuo.custoast.CusToast;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.speechtranslation
 * @ClassName: SpeechTranslationActivity
 * @Author: csc
 * @CreateDate: 2019/9/19 14:41
 * @Description:
 */

@ContentView(R.layout.activity_speech_translation)
public class SpeechTranslationActivity extends BaseMvpActivity<SpeechTranslationPresenter> implements SpeechTranslationContract.SpeechTranslationView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.btnVoice)
    private Button btnVoice;
    @ViewInject(R.id.to_result_tv)
    private TextView to_result_tv;
    @ViewInject(R.id.from_result_tv)
    private TextView from_result_tv;
    @ViewInject(R.id.spinner)
    private Spinner spinner;
    @ViewInject(R.id.spinner_aims)
    private Spinner spinner_aims;
    private String targetId;
    private String fromType;
    private String toType;
    private String[] languages;
    private static final int GET_RECODE_AUDIO = 1;

    @Override
    protected void initData() {
        x.view().inject(this);

        PackageManager pkgManager= getPackageManager();

        boolean audioSatePermission =

                pkgManager.checkPermission(Manifest.permission.RECORD_AUDIO, getPackageName()) == PackageManager.PERMISSION_GRANTED;

        if(Build.VERSION.SDK_INT>=23&&!audioSatePermission) {

            requestPermission();

        }

        mPresenter = new SpeechTranslationPresenter(getContext(), SpeechTranslationActivity.this);
        mPresenter.attachView(this);
        tv_title.setText(getText(R.string.speech_translation));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(getText(R.string.umeng_socialize_send_btn_str));
        targetId = getIntent().getStringExtra("targetId");
        spinner.setSelection(SharedPreferencesUtils.getInt(getContext(), BaseConstant.SPConstant.SPEECH_TRANSLATION_FROM,0), true);
        spinner_aims.setSelection(SharedPreferencesUtils.getInt(getContext(), BaseConstant.SPConstant.SPEECH_TRANSLATION_TO,1), true);
        languages = getResources().getStringArray(R.array.user_spingarr);
        setType();
    }
    private void setType(){
        fromType = languages[spinner.getSelectedItemPosition()];
        toType = languages[spinner_aims.getSelectedItemPosition()];
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECORD_AUDIO},GET_RECODE_AUDIO);

    }

    @Override
    protected void initView() {

        from_result_tv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("chenshichun", "=======IME_ACTION_DONE====");
                    mPresenter.goToTranslation(fromType, toType, from_result_tv.getText().toString());
                }
                return true;
            }
        });

        btnVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!fromType.equals(languages[0]) && !toType.equals(languages[0]))
                        {
                            CusToast.showToast(getText(R.string.not_supported_yet));
                        }else {
                            mPresenter.startRecord();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!fromType.equals(languages[0]) && !toType.equals(languages[0]))
                        {
                        }else {
                            mPresenter.stopRecord(fromType, toType);
                        }
                        break;
                }

                return false;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferencesUtils.saveInt(getContext(),BaseConstant.SPConstant.SPEECH_TRANSLATION_FROM,position);
                fromType = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_aims.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferencesUtils.saveInt(getContext(),BaseConstant.SPConstant.SPEECH_TRANSLATION_TO,position);
                toType = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @Event(value = {R.id.rl_close, R.id.delete_iv, R.id.tv_title_right, R.id.translation_conversion_ll})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_title_right:
                mPresenter.goSendTranslationMsg(from_result_tv.getText().toString(), to_result_tv.getText().toString(), targetId);
                break;
            case R.id.translation_conversion_ll:

                break;
        }
    }

    @Override
    public void getTranslationFromResult(String txt) {
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = txt;
        mHandle.sendMessage(msg);
    }

    @Override
    public void getTranslationToResult(String txt) {
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = txt;
        mHandle.sendMessage(msg);
    }


    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    from_result_tv.setVisibility(View.VISIBLE);
                    from_result_tv.setText("" + msg.obj);
                    break;
                case 1:
                    to_result_tv.setVisibility(View.VISIBLE);
                    to_result_tv.setText("" + msg.obj);
                    break;
            }
        }
    };

}
