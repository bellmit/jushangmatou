package com.tem.gettogether.fragment.home;

import com.tem.gettogether.base.BaseView;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/13 09:28 
 */
public class HomeContract {
    interface HomeView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);
    }

    interface Presenter {
    }
}
