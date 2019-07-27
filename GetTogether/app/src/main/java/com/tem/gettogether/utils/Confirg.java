package com.tem.gettogether.utils;

import android.os.Environment;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Confirg {
    public static boolean isRongCloudConnected = false;


    public static final String FilesPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/"; // 图片及其他数据保存文件夹
    public static File compressFile = new File(FilesPath);
    public static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

}
