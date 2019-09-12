package com.tem.gettogether.activity.my.specifications;

import com.tem.gettogether.base.BaseView;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.SpecificationsBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specifications
 * @ClassName: SpecificationsContract
 * @Author: csc
 * @CreateDate: 2019/9/9 11:33
 * @Description:
 */
public class SpecificationsContract {

    interface Model {
        Call<SpecificationsBean> getSpecificationsData(Map<String, Object> map);
    }

    interface SpecificationsView extends BaseView {
        void getSpecificationsData(SpecificationsBean.ResultBean mSpecificationsBean);
    }

    interface Presenter {
        void getSpecificationsData(Map<String, Object> map);
    }
}
