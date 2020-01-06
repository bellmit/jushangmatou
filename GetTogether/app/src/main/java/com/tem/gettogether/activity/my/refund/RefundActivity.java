package com.tem.gettogether.activity.my.refund;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsActivity;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsPresenter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.RefundAmountBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/22 14:48 
 */
@ContentView(R.layout.activity_refund_application)
public class RefundActivity extends BaseMvpActivity<RefundPresenter> implements RefundContract.RefundView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.account_tv)
    private TextView account_tv;
    @ViewInject(R.id.alipay_account_et)
    private EditText alipay_account_et;
    @ViewInject(R.id.alipay_name_et)
    private EditText alipay_name_et;
    @ViewInject(R.id.refund_item_tv)
    private TextView refund_item_tv;
    @ViewInject(R.id.refund_amount_tv)
    private TextView refund_amount_tv;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    private int REFUND_TYPE = 0;// 0：高级 1：普通退款
    private String url = "";

    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new RefundPresenter(getContext(), RefundActivity.this);
        mPresenter.attachView(this);
        Map<String, Object> map = new HashMap<>();
        map.put("level_id", "1");
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        mPresenter.refundAmount(map);
    }

    @Event(value = {R.id.rl_close, R.id.tv_save}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_save:
                Map<String, Object> map = new HashMap<>();
                map.put("level_id", "1");
                map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
                map.put("alipay_account", alipay_account_et.getText().toString());
                map.put("alipay_name", alipay_name_et.getText().toString());
                mPresenter.refundSave(url, map);
                break;
        }
    }

    @Override
    protected void initView() {
        tv_title.setText(getText(R.string.refund_application));
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        account_tv.setText(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.MOBILEPHONE, ""));
        REFUND_TYPE = getIntent().getIntExtra("REFUND_TYPE", 0);
        if (REFUND_TYPE == 0) {
            refund_item_tv.setText(R.string.sjhyyjyeth);
            url = URLConstant.ALIPAY_REFUND_BALANCE_URL;
        } else {
            refund_item_tv.setText(R.string.pthyyjyeth);
            url = URLConstant.ALIPAY_REFUND_URL;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
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
    public void getRefundAmount(RefundAmountBean.ResultBean mResultBean) {
        if (REFUND_TYPE == 0) {
            refund_amount_tv.setText(mResultBean.getUpgrade_account()+"¥");
        } else {
            refund_amount_tv.setText(mResultBean.getAccount()+"¥");
        }
    }
}
