package com.tem.gettogether.activity.login.countrycode;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.CountryCodeBean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/29 17:04 
 */
public class CountryCodeContract {
    interface CountryCodeView extends BaseView {
        void countryCodeData(List<CountryCodeBean.ResultBean.CountryBean> mDatas);
    }

    interface Presenter {
        void getCountryCode();
    }
}
