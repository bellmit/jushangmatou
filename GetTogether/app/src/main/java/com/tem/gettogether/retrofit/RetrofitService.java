package com.tem.gettogether.retrofit;

import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.bean.ReasultBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @POST("Api/Index/home")
    Call<HomeDataNewBean> getHomeData(@QueryMap Map<String, Object> map);

    /*
    * 店铺上新里商品分类
    * */
    @POST("Api/User/get_goods_category")
    Call<CategoriesBean> getStoreCate(@QueryMap Map<String, Object> map);

    /*
    * 上传商品接口
    * */
    @POST("Api/User/new_add_goods")
    Call<ReasultBean> uploadProduct(@Body RequestBody body);
}
