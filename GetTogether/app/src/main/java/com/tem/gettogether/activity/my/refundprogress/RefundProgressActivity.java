package com.tem.gettogether.activity.my.refundprogress;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.refund.RefundActivity;
import com.tem.gettogether.activity.my.refund.RefundPresenter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.bean.RefundProgressBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  
 * description ： 退款進度
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/28 11:47 
 */
@ContentView(R.layout.activity_third_refund_progress)
public class RefundProgressActivity extends BaseMvpActivity<RefundProgressPresenter> implements RefundProgressContract.RefundProgressView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.img_0)
    private ImageView img_0;
    @ViewInject(R.id.img_1)
    private ImageView img_1;
    @ViewInject(R.id.img_2)
    private ImageView img_2;
    @ViewInject(R.id.first_time)
    private TextView first_time;
    @ViewInject(R.id.second_time)
    private TextView second_time;
    @ViewInject(R.id.third_time)
    private TextView third_time;
    @ViewInject(R.id.second_tv)
    private TextView second_tv;
    @ViewInject(R.id.third_tv)
    private TextView third_tv;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        mPresenter = new RefundProgressPresenter(getContext(), RefundProgressActivity.this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initView() {
        tv_title.setText(R.string.refund_progress);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        mPresenter.getRefundProgress(map);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void refundProgress(List<RefundProgressBean.ResultBean> mDatas) {
        if (mDatas.size() == 1) {
            img_1.setBackgroundResource(R.drawable.refund_no);
            img_2.setBackgroundResource(R.drawable.refund_no);
            first_time.setText(mDatas.get(0).getCheck_time());
        } else if (mDatas.size() == 2) {
            img_1.setBackgroundResource(R.drawable.refund_ok);
            img_2.setBackgroundResource(R.drawable.refund_no);
            first_time.setText(mDatas.get(1).getCheck_time());
            second_time.setText(mDatas.get(0).getCheck_time());
            second_tv.setText(mDatas.get(0).getReview_msg());
        } else if (mDatas.size() == 3) {
            img_1.setBackgroundResource(R.drawable.refund_ok);
            img_2.setBackgroundResource(R.drawable.refund_ok);
            first_time.setText(mDatas.get(2).getCheck_time());
            second_time.setText(mDatas.get(1).getCheck_time());
            third_time.setText(mDatas.get(0).getCheck_time());
            second_tv.setText(mDatas.get(1).getReview_msg());
            third_tv.setText(mDatas.get(0).getReview_msg());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
