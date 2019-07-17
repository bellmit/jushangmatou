package com.jsmt.developer.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Created by 84410 on 2018-07-02.
 */

public class Utils {
    private static Context context;
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
    /**
     * 获取当前系统的语言环境
     *
     * @param context
     * @return boolean
     */
    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
    /* 读取Assets文件夹中的图片资源
    * @param context
    * @param fileName 图片名称
    * @return
    */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    /**
     * 获取Bitmap数据
     *
     * @param imgPath
     * @return
     */
    public static Bitmap getBitmapData(String imgPath) {
        Bitmap bm = BitmapFactory.decodeFile(imgPath, getBitmapOption(1)); // 将图片的长和宽缩小为原来的1/2
        return bm;
    }
    /**
     * BitmapOption 位图选项
     * @param inSampleSize
     * @return
     */
    private static BitmapFactory.Options getBitmapOption(int inSampleSize) {
        System.gc();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444; // T4 二维码图片效果最佳
        return options;
    }
    /**
     * jpg png bmp 彩色图片转换Bitmap数据为int[]数组
     * @param bm
     * @param path
     * @return int[]
     */
    public static int[] getPixelsByBitmap(Bitmap bm) {
        int width, heigh;
        width = bm.getWidth();
        heigh = bm.getHeight();
        int iDataLen = width * heigh;
        int[] pixels = new int[iDataLen];
        bm.getPixels(pixels, 0, width, 0, 0, width, heigh);
        return pixels;
    }

}

