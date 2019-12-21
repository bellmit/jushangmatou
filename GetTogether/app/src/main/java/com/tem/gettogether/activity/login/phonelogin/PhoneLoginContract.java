package com.tem.gettogether.activity.login.phonelogin;

import com.tem.gettogether.base.BaseView;

import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/23 13:45 
 */
public class PhoneLoginContract {
    interface PhoneLoginView extends BaseView {
        void phoneLogin();
        void emailLogin();
        void showIdentityDialog();
        void thirdRoleTypeLogin(String roleType);
    }

    interface Presenter {
        void phoneLogin();
        void emailLogin();
        void newUserRegistration();
        void toChooseCountryCode();
        void forgetPassword();
        void login(Map<String, Object> map);
        void facebookLogin();
        void wechatLogin();
        void facebookWxCheck(Map<String, Object> map);
        void thirdLogin(Map<String, Object> map);
    }
}
