package com.tem.gettogether.activity.login.forgetpassword;

import com.tem.gettogether.base.BaseView;

import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/24 10:20 
 */
public class ForgetPasswordContract {

    interface ForgetPasswordView extends BaseView {
        void phoneChangePas();
        void emailChangePas();
        void codeTime();
    }

    interface Presenter {
        void phoneChangePas();
        void emailChangePas();
        void toChooseCountryCode();
        void submit(Map<String, Object> map);
        void upVerificationCode(String phone,String mobileCode);
        void upEmailCode(String email);
    }
}
