package com.tem.gettogether.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by litao
 * 注释:
 * Created by 2019/1/11.
 */

public class SharedPreferencesUtils {

    public static String SP_NAME = "config";
    public static String SP_LANGUAGE_NAME = "language_config";

    public static SharedPreferences sp;

    public static String saveString(Context context, String key, String value) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }

        sp.edit().putString(key, value).commit();

        return key;
    }

    public static String getLanguageString(Context context, String key, String defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_LANGUAGE_NAME, context.MODE_PRIVATE);
        }

        return sp.getString(key, defValue);
    }

    public static String saveLanguageString(Context context, String key, String value) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_LANGUAGE_NAME, context.MODE_PRIVATE);
        }

        sp.edit().putString(key, value).commit();

        return key;
    }

    public static String getString(Context context, String key, String defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }

        return sp.getString(key, defValue);
    }

    public static void saveBoolean(Context context, String key, boolean defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }

        sp.edit().putBoolean(key, defValue).commit();
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        }

        return sp.getBoolean(key, defValue);
    }

    public static void saveInt(Context context, String key, int defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }

        sp.edit().putInt(key, defValue).commit();
    }

    public static int getInt(Context context, String key,
                                 int defValue) {

        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        }
        return sp.getInt(key, defValue);
    }


}