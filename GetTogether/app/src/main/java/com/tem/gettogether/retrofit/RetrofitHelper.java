package com.tem.gettogether.retrofit;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient = null;
    public static final String BASE_URL="http://www.jsmtgou.com/jushangmatou/index.php/";

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void log(String message) {
                try {
                    StringReader reader = new StringReader(message);
                    Properties properties = new Properties();
                    properties.load(reader);
                    Log.e("cuckoo","myurl>>>>>>>>" + properties.toString());
                    properties.list(System.out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);// mod by csc from BODY
        builder.addInterceptor(loggingInterceptor);
        okHttpClient = builder.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)// add by csc for see http log
                .build();
    }

    public static <T> T get(Class<T> t){
        return retrofit.create(t);
    }

    /**
     * 将map数据转换为 普通的 json RequestBody
     * @param map 以前的请求参数
     * @return
     */
    public static RequestBody convertMapToBody(Map<?,?> map) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(map).toString());
    }

}
