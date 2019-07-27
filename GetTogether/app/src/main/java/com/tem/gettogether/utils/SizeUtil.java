package com.tem.gettogether.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author wuzefeng
 * @date 2018/6/6
 */
public class SizeUtil {


    public static int dp2px(Context context,float dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale +0.5f);
    }

    public static int px2dp(Context context,float px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale +0.5f);
    }

    public static int screenWidth(Context context){
        WindowManager mWindowManager  = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int screenHeight(Activity context){
        WindowManager mWindowManager  = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    private static int imageSize;

    public static int getDefaultImageSize(Context context){
        if(imageSize==0){
            imageSize=SizeUtil.dp2px(context,50);
        }
        return imageSize;
    }


}
