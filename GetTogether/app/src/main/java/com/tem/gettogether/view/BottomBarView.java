package com.tem.gettogether.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tem.gettogether.R;


/**
 * 底部导航view
 */

public class BottomBarView extends BaseHeaderView implements View.OnClickListener{


    private RadioButton rbHome;
    private RadioButton rbPerformance;
    private RadioButton rb_message;
    private RadioButton rbContact;
    private RadioButton rbMe;
    private OnTabSelectListener onTabSelectListener;
    public BottomBarView(Context context) {
        super(context);
    }

    public BottomBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        setOrientation(HORIZONTAL);
        RadioGroup rg = getView(R.id.rg_bottom);
        rbHome = getView(R.id.rb_home);
        rbHome.setOnClickListener(this);
        rbPerformance = getView(R.id.rb_report);
        rbPerformance.setOnClickListener(this);
        rb_message=getView(R.id.rb_message);
        rb_message.setOnClickListener(this);
        rbContact = getView(R.id.rb_work);
        rbContact.setOnClickListener(this);
        rbMe = getView(R.id.rb_me);
        rbMe.setOnClickListener(this);
    }

    public void setTabPosition(int position){
        switch (position){
            case 0:
                rbHome.performClick();
                break;
            case 1:
                rbPerformance.performClick();
                break;
            case 2:
                rb_message.performClick();
                break;
            case 3:
                rbContact.performClick();
                break;
            case 4:
                rbMe.performClick();
                break;
        }
    }

    public interface OnTabSelectListener {
       void onTabSelected(int resId, View view);
    }

    public void setOnTabSelectListener(OnTabSelectListener listener){
        this.onTabSelectListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_bar_layout;
    }

    @Override
    public void onClick(View v) {
        if(onTabSelectListener !=null){
            onTabSelectListener.onTabSelected(v.getId(),v);
        }
        switch (v.getId()){
            case R.id.rb_home:
                break;
            case R.id.rb_report:
                break;
            case R.id.rb_message:
                break;
            case R.id.rb_work:
                break;
            case R.id.rb_me:
                break;
        }
    }
}
