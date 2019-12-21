package com.tem.gettogether.activity.my.refundprogress;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.CountryCodeBean;
import com.tem.gettogether.bean.RefundProgressBean;

import java.util.List;
import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/28 11:47 
 */
public class RefundProgressContract {
    interface RefundProgressView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void refundProgress(List<RefundProgressBean.ResultBean> mDatas);

    }

    interface Presenter {
        void getRefundProgress(Map<String, Object> map);
    }
}
