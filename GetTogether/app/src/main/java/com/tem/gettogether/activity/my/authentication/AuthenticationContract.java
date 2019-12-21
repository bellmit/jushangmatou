package com.tem.gettogether.activity.my.authentication;

import android.view.View;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.AuthenticationBean;
import com.tem.gettogether.bean.PersonagerMessageBean;

import java.util.List;
import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/4 15:22 
 */
public class AuthenticationContract {
    interface AuthenticationView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void showDistributor();

        void showFactory();

        void showPersonal();

        void getData(PersonagerMessageBean personagerMessageBean);

        void addressSetData();

        void getAddressData(List<AddressDataBean.ResultBean> addresssresultBeans);

        void addressData();

        void setRejectionReason(String rejectionReason);

        void setRejectionInformation(AuthenticationBean.ResultBean resultBean);
    }

    interface Presenter {
        void showDistributor();

        void showFactory();

        void showPersonal();

        void getData();

        void upGetAddressData(String parent_id);

        void saveEnterprisePersonalStore(Map<String, Object> map);

        void getAuthenticationData(Map<String, Object> map);

        void upGetRzFailedData();
    }
}
