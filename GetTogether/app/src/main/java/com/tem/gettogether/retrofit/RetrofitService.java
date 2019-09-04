package com.tem.gettogether.retrofit;

import com.tem.gettogether.bean.HomeDataNewBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @POST("Api/Index/home")
    Call<HomeDataNewBean> getHomeData(@QueryMap Map<String, Object> map);

    /**
     * 上传头像
     */
    @Multipart
    @POST("/Api/Index/upload_binary")
    Call<Result<String>> uploadMemberIcon(@Part MultipartBody.Part part);

}
