package com.tem.gettogether.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;


import com.tem.gettogether.bean.UserBean;
import com.tem.gettogether.bean.WeiXinBean1;
import com.tem.gettogether.bean.WeiXinMessageBean;
import com.tem.gettogether.rongyun.CustomizeMessage;
import com.tem.gettogether.rongyun.CustomizeMessageItemProvider;
import com.tem.gettogether.rongyun.ShopExtensionModule;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;

/**
 * Created by pc on 2018/4/16.
 */

public class BaseApplication extends Application {
    public static Context context;
    public static BaseApplication mInstance;
    public UserBean userBean;
    public int isWXPay = 100;
    public final String WXAPP_ID = "wx93eea65ba215f901";
    public IWXAPI api;
    public WeiXinBean1 wxbean;
    public WeiXinMessageBean bean;

    public static BaseApplication getInstance() {
        if (mInstance == null) {
            mInstance = (BaseApplication) context.getApplicationContext();
        }
        return mInstance;
    }
    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        context = getApplicationContext();
        CusToast.init(this);
        api = WXAPIFactory.createWXAPI(this, WXAPP_ID);
        RongIM.init(this);

        setInputProvider();
        UMShareAPI.get(this);
        initAppLanguage();
    }
    {
        PlatformConfig.setQQZone("101557245", "2fe9d31228f7ccb88ffd26beb709d31e");
    }
    private void setInputProvider() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new ShopExtensionModule());
            }
        }
        RongIM.registerMessageType(CustomizeMessage.class);
        RongIM.getInstance().registerMessageTemplate(new CustomizeMessageItemProvider());

    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */
    private static Map<String, Activity> destoryMap = new HashMap<>();

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }
    public void removerUser() {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            Activity activity = destoryMap.get(key);
            if (activity != null)
                activity.finish();

        }
    }

    public void initAppLanguage(){
        if (SharedPreferencesUtils.getString(this,BaseConstant.SPConstant.language,"").equals("zh")){
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = Locale.CHINESE;
            getResources().updateConfiguration(config,dm);
        }else if (SharedPreferencesUtils.getString(this,BaseConstant.SPConstant.language,"").equals("en")){
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = Locale.ENGLISH;
            getResources().updateConfiguration(config,dm);
        }else if (SharedPreferencesUtils.getString(this,BaseConstant.SPConstant.language,"").equals("ara")){
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = new Locale("ar");
            getResources().updateConfiguration(config,dm);
        }
    }
}
