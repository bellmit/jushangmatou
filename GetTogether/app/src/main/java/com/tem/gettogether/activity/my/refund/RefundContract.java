package com.tem.gettogether.activity.my.refund;

import android.view.View;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.RefundAmountBean;

import java.util.List;
import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/22 14:49 
 */
public class RefundContract {

    interface RefundView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void getRefundAmount(RefundAmountBean.ResultBean mResultBean);
    }

    interface Presenter {
        void refundSave(String url, Map<String, Object> map);

        void refundAmount(Map<String, Object> map);
    }

}
