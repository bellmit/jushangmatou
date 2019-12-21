package com.tem.gettogether.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tem.gettogether.R;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/22 11:52 
 */
public abstract class DialogGetHeadRefund extends Dialog implements View.OnClickListener {

    private Activity activity;
    private FrameLayout flt_amble_upload, refund_fl;
    private Button btn_cancel;
    private TextView explanationTv;
    private String explanation_tv="";
    public DialogGetHeadRefund(Activity activity,String explanation_tv) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.explanation_tv = explanation_tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_refund);

        flt_amble_upload = (FrameLayout) findViewById(R.id.flt_amble_upload);
        refund_fl = (FrameLayout) findViewById(R.id.refund_fl);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        explanationTv = (TextView) findViewById(R.id.explanation_tv);

        flt_amble_upload.setOnClickListener(this);
        refund_fl.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        explanationTv.setText(explanation_tv);
        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refund_fl:
                refund();
                this.cancel();
                break;
            case R.id.btn_cancel:
                this.cancel();
                break;
        }
    }

    public abstract void refund();

}
