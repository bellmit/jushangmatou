package com.tem.gettogether.activity.login.register;

import com.tem.gettogether.base.BaseView;

import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/23 16:49 
 */
public class RegisterContract {
    interface RegisterView extends BaseView {
        void phoneRegister();
        void emailRegister();
        void codeTime();
        void chooseStrategicAlliance();
    }

    interface Presenter {
        void phoneRegister();
        void emailRegister();
        void upVerificationCode(String phone,String mobileCode);
        void upEmailCode(String email);
        void chooseStrategicAlliance();
        void toChooseCountryCode();
        void register(Map<String, Object> map);
        void upCodeZHUCE();
    }
}
