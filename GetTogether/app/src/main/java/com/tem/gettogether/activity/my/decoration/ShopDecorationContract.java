package com.tem.gettogether.activity.my.decoration;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.ShopDecorationBean;

import java.util.Map;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.decoration
 * @ClassName: ShopDecorationContract
 * @Author: csc
 * @CreateDate: 2019/9/17 11:12
 * @Description:
 */
public class ShopDecorationContract {

    interface ShopDecorationView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void getShopDecorationData(ShopDecorationBean.ResultBean mResultBean);
    }

    interface Presenter {

        void getShopDecorationData(Map<String, Object> map);

        void getShopDecorationModifyData(Map<String, Object> map);
    }

}
