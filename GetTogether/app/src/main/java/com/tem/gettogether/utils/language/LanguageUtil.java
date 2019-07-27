package com.tem.gettogether.utils.language;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

/**
 * desc 语言工具类
 *
 * @author litao
 * 2018/11/25 1:58 PM
 */
public class LanguageUtil {
    /**
     * 保存SharedPreferences的文件名
     */
    private static final String LANGUAGE_FILE = "language_file";
    private static final String LANGUAGE = "language";

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 8.0需要使用createConfigurationContext处理
            return updateResources(context);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context updateResources(Context context) {
        Resources resources = context.getResources();
        Locale locale = getUserLocale();// getSetLocale方法是获取新设置的语言

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    /**
     * 更新Locale
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void updateLocale(Locale userLocale) {
        if (needUpdateLocale(userLocale)) {
            Context context = UtilsLanguage.getContext().getApplicationContext();
            Configuration configuration = context.getResources().getConfiguration();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(userLocale);
            } else {
                configuration.locale = userLocale;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, displayMetrics);
            saveUserLocale(userLocale);
            EventBus.getDefault().post(new LanguageBean(userLocale));
        }
    }


    /**
     * 获取用户设置的Locale
     */
    public static Locale getUserLocale() {
        SharedPreferences sp = UtilsLanguage.getContext().getSharedPreferences(LANGUAGE_FILE, Context.MODE_PRIVATE);
        String local = sp.getString(LANGUAGE, "");
        if (!TextUtils.isEmpty(local)) {
            return jsonToLocale(local);
        }
        return jsonToLocale(localeToJson(getCurrentLocale()));
    }

    /**
     * 获取当前的Locale
     */
    private static Locale getCurrentLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
            locale = UtilsLanguage.getContext().getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = UtilsLanguage.getContext().getResources().getConfiguration().locale;
        }
        return locale;
    }

    /**
     * 保存用户设置的Locale
     */
    private static void saveUserLocale(Locale pUserLocale) {
        SharedPreferences sp = UtilsLanguage.getContext().getSharedPreferences(LANGUAGE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        String localeJson = localeToJson(pUserLocale);
        edit.putString(LANGUAGE, localeJson);
        edit.apply();
    }

    /**
     * Locale转成json
     */
    private static String localeToJson(Locale locale) {
        Gson gson = new Gson();
        return gson.toJson(locale);
    }

    /**
     * json转成Locale
     */
    private static Locale jsonToLocale(String local) {
        Gson gson = new Gson();
        return gson.fromJson(local, Locale.class);
    }

    /**
     * 判断需不需要更新
     */
    private static boolean needUpdateLocale(Locale locale) {
        return locale != null && !getCurrentLocale().equals(locale);
    }

}
