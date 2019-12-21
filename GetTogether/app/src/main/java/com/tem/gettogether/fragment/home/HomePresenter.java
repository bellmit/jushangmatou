package com.tem.gettogether.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import java.util.Locale;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/13 09:29 
 */
public class HomePresenter extends BasePresenter<HomeContract.HomeView> implements HomeContract.Presenter {
    private HomeModel model;
    private Context mContext;
    private Activity mActivity;

    public HomePresenter(Context context, Activity mActivity) {
        model = new HomeModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

}
