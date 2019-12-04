package com.tem.gettogether.activity.my.publishgoods;

import android.view.View;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.ReasultBean;
import com.tem.gettogether.bean.ShopEditBean;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.publishgoods
 * @ClassName: PublishGoodsContract
 * @Author: csc
 * @CreateDate: 2019/9/6 14:25
 * @Description:
 */
public class PublishGoodsContract {
    interface Model {
        Call<CategoriesBean> getStoreCate(Map<String, Object> map);

        Call<ReasultBean> uploadProduct(RequestBody body);
    }

    interface PublishGoodsView extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void getStoreCate(List<CategoriesBean.ResultBean> mCategoriesBeans);

        void getChooseStoreCate(String majorClassName, String smallClassName, String majorClassId, String smallClassId);

        void getShopEditData(ShopEditBean.ResultBean mResultBean);
    }

    interface Presenter {
        /*
         * 获取商品分类数据
         * */
        void getStoreCate(Map<String, Object> map);

        void showStoreCatePop(View view, List<CategoriesBean.ResultBean> mCategoriesBeans);

        void uploadProduct(Map<String, Object> map);

        void editShop(Map<String,Object> map);
    }
}
