package com.tem.gettogether.activity.my.publishgoods;

import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.ReasultBean;
import com.tem.gettogether.retrofit.RetrofitHelper;
import com.tem.gettogether.retrofit.RetrofitService;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.publishgoods
 * @ClassName: PublishGoodsModel
 * @Author: csc
 * @CreateDate: 2019/9/6 14:27
 * @Description:
 */
public class PublishGoodsModel implements PublishGoodsContract.Model{

    @Override
    public Call<CategoriesBean> getStoreCate(Map<String, Object> map) {
        return RetrofitHelper.get(RetrofitService.class).getStoreCate(map);
    }

    @Override
    public Call<ReasultBean> uploadProduct(RequestBody body) {
        return RetrofitHelper.get(RetrofitService.class).uploadProduct(body);
    }


}
