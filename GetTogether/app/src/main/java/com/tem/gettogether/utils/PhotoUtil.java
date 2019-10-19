package com.tem.gettogether.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.tem.gettogether.utils.permissions.AppUtils;
import com.tem.gettogether.utils.permissions.FileUtils;
import com.tem.gettogether.utils.permissions.PictureUtil;

import java.io.File;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.utils
 * @ClassName: PhotoUtil
 * @Author: csc
 * @CreateDate: 2019/9/7 11:58
 * @Description:
 */
public class PhotoUtil {
    private static String IMAGE_FILE_NAME = "user_head_icon.jpg";
    private static final int PHOTO_PICKED_FROM_CAMERA = 111; // 用来标识头像来自系统拍照
    private static File mCropImageFile;
    private static final int CROP_FROM_CAMERA = 333;

    public static void imageCapture(Activity activity) {
        Intent intent;
        Uri pictureUri;
        //getMyPetRootDirectory()得到的是Environment.getExternalStorageDirectory() + File.separator+"."
        //也就是我之前创建的存放头像的文件夹（目录）
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这一句非常重要
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //""中的内容是随意的，但最好用package名.provider名的形式，清晰明了
            pictureUri = FileProvider.getUriForFile(activity.getApplicationContext(),
                    "com.tem.gettogether.FileProvider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到pictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        activity.startActivityForResult(intent, PHOTO_PICKED_FROM_CAMERA);
    }

    /**
     * 系统拍照后裁剪
     */
    public static  void startSystemCamera(Activity activity,Context context) {
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        Uri pictureUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pictureUri = FileProvider.getUriForFile(context,
                    "com.tem.gettogether.FileProvider", pictureFile);
        } else {
            pictureUri = Uri.fromFile(pictureFile);
        }
        startPhotoZoom(activity,context,pictureUri);
    }

    public static void startPhotoZoom(Activity activity,Context context,Uri uri) {
        try {
            if (AppUtils.existSDCard()) {
                mCropImageFile = FileUtils.createTmpFile(context);
                Intent intent = new Intent("com.android.camera.action.CROP");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("crop", "true");
                if (Build.MANUFACTURER.equals("HUAWEI")) {//解决华为手机调用裁剪出现圆形裁剪框
                    intent.putExtra("aspectX", 9998);
                    intent.putExtra("aspectY", 9999);
                } else {
                    intent.putExtra("aspectX", 1); // 裁剪框比例
                    intent.putExtra("aspectY", 1);
                }
                intent.putExtra("outputX", 300); // 输出图片大小
                intent.putExtra("outputY", 300);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", false);
                intent.putExtra("circleCrop", false);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropImageFile));
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", true); // no face detection
                activity.startActivityForResult(intent, CROP_FROM_CAMERA);
            }
        } catch (Exception e) {

        }

    }
}
