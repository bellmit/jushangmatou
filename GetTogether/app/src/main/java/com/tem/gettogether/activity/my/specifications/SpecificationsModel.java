package com.tem.gettogether.activity.my.specifications;

import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.retrofit.RetrofitHelper;
import com.tem.gettogether.retrofit.RetrofitService;

import java.util.Map;

import retrofit2.Call;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specifications
 * @ClassName: SpecificationsModel
 * @Author: csc
 * @CreateDate: 2019/9/9 11:34
 * @Description:
 */
public class SpecificationsModel implements SpecificationsContract.Model{
    @Override
    public Call<SpecificationsBean> getSpecificationsData(Map<String, Object> map) {
        return RetrofitHelper.get(RetrofitService.class).getSpecificationsData(map);
    }
}
