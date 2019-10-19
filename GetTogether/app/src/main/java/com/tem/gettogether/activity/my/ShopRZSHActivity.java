package com.tem.gettogether.activity.my;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.permissions.AppUtils;
import com.tem.gettogether.utils.permissions.FileUtils;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.permissions.PictureUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_shop_rzsh)
public class ShopRZSHActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_image_1)
    private ImageView iv_image_1;
    @ViewInject(R.id.iv_image_2)
    private ImageView iv_image_2;
    @ViewInject(R.id.iv_image_3)
    private ImageView iv_image_3;
    @ViewInject(R.id.iv_image_4)
    private ImageView iv_image_4;
    @ViewInject(R.id.rl_card_image1)
    private RelativeLayout rl_card_image1;
    @ViewInject(R.id.rl_card_image2)
    private RelativeLayout rl_card_image2;
    @ViewInject(R.id.rl_card_image3)
    private RelativeLayout rl_card_image3;
    @ViewInject(R.id.rl_card_image4)
    private RelativeLayout rl_card_image4;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_card_num)
    private EditText et_card_num;
    @ViewInject(R.id.et_zysp)
    private EditText et_zysp;
    @ViewInject(R.id.et_shop_name)
    private EditText et_shop_name;
    //系统相机
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 101;
    //系统相册
    public static final int REQUEST_CODE_PHOTO_PERMISSION = 102;
    //所需要的权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    //拍照所需要的权限
    static final String[] PERMISSIONS_CAMERA = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static String IMAGE_FILE_NAME = "user_head_icon.jpg";
    private final int PHOTO_PICKED_FROM_CAMERA = 111; // 用来标识头像来自系统拍照
    private final int PHOTO_PICKED_FROM_FILE = 222; // 用来标识从相册获取头像
    private final int CROP_FROM_CAMERA = 333;
    private File mCropImageFile;
    private int imageType=0;
    private String cardImage_z,cardImage_f;
    private String yyzz_z,yyzz_f;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.shopRZ);
        closeKeybord(et_name,this);
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.rl_card_image1,R.id.rl_card_image2,R.id.rl_card_image3,R.id.rl_card_image4,R.id.tv_tjsh}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                closeKeybord(et_name,this);
                finish();
                break;
            case R.id.rl_card_image1:
                imageType=1;
                showPop(rl_card_image1);
                break;
            case R.id.rl_card_image2:
                imageType=2;
                showPop(rl_card_image2);
                break;
            case R.id.rl_card_image3:
                imageType=3;
                showPop(rl_card_image3);
                break;
            case R.id.rl_card_image4:
                imageType=4;
                showPop(rl_card_image4);
                break;
            case R.id.tv_tjsh:
                String name=et_name.getText().toString();
                String cardNum=et_card_num.getText().toString();
                String phone =et_phone.getText().toString();
                String zysp=et_zysp.getText().toString();
                String shopName=et_shop_name.getText().toString();
                if(name.equals("")){
                    CusToast.showToast(getText(R.string.enter_your_real_name));
                    return;
                }else if(cardNum.equals("")){
                    CusToast.showToast(getText(R.string.please_enter_the_correct_ID_number));
                    return;
                }else if(phone.equals("")){
                    CusToast.showToast(getText(R.string.qsrsjh));
                    return;
                }else if(zysp.equals("")){
                    CusToast.showToast(getText(R.string.please_enter_the_main_product));
                    return;
                }else if(shopName.equals("")){
                    CusToast.showToast(getText(R.string.please_enter_the_name_of_the_store));
                    return;
                }else if(imageType==0){
                    CusToast.showToast(getText(R.string.please_upload_the_relevant_ID_photo));
                    return;
                }
                Map<String,Object> map=new HashMap<>();

                map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                map.put("store_person_name",name);
                map.put("store_person_identity",cardNum);
                map.put("store_person_mobile",phone);
                map.put("store_name",shopName);
                map.put("legal_identity_cert",cardImage_z);
                map.put("store_person_cert",cardImage_f);
                map.put("business_licence_cert",yyzz_z);
                map.put("store_zy",zysp);
                upShopRZData(map);
                break;

        }
    }

    private void upShopRZData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.SHOP_RENZHENG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商铺认证===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    private PopupWindow mPop;

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(true);
            mPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }
    private void setPopClickListener(View view) {
        TextView tv_iteam1, photo, cancle;
        photo = view.findViewById(R.id.photo);
        cancle = view.findViewById(R.id.cancle);
        tv_iteam1 = view.findViewById(R.id.tv_iteam1);
        tv_iteam1.setTextSize(16);
        tv_iteam1.setText(getText(R.string.take_photo));
        photo.setText(getText(R.string.album));
        tv_iteam1.setTextColor(getResources().getColor(R.color.black));
        photo.setTextColor(getResources().getColor(R.color.black));
        tv_iteam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionsActivity.startActivityForResult((Activity) mContext, REQUEST_CODE_CAMERA_PERMISSION, PERMISSIONS_CAMERA);//打开系统相机需要相机权限


                mPop.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统相册
                PermissionsActivity.startActivityForResult((Activity) mContext, REQUEST_CODE_PHOTO_PERMISSION, PERMISSIONS);
                mPop.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }

    /**
     * 设置图片
     */
    public void setPicToView() {
        if (mCropImageFile != null) {
            String path = mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
            if(imageType==1){
                iv_image_1.setImageBitmap(bitmap);

            } else if(imageType==2){
                iv_image_2.setImageBitmap(bitmap);

            } else if(imageType==3){
                iv_image_3.setImageBitmap(bitmap);

            }else if(imageType==4){
                iv_image_4.setImageBitmap(bitmap);

            }
            Map<String,Object> map=new HashMap<>();
            map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
            upInputImageData(map);
//            GlideLoadUtils.getInstance().glideAvatarLoad(this, path, iv_head, R.drawable.img12x);

        }
    }
    private void upInputImageData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.SHANGCHUAN_IMAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====上传图片===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean=gson.fromJson(result,ImageDataBean.class);
                        if(imageType==1){
                            cardImage_z=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据1---",imageDataBean.getResult().getImage_show().get(0));
                        }else if(imageType==2){
                            cardImage_f=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据22---",imageDataBean.getResult().getImage_show().get(0));
                        }else if(imageType==3){
                            yyzz_z=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据333---",imageDataBean.getResult().getImage_show().get(0));
                        }else if(imageType==4){
                            yyzz_f=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据4444---",imageDataBean.getResult().getImage_show().get(0));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //系统相机权限
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            } else {
                imageCapture();//系统相机拍照
            }
        }
        //拍照完成的回调
        if (requestCode == PHOTO_PICKED_FROM_CAMERA && resultCode == Activity.RESULT_OK) {//Activity.RESULT_OK可以确保拍照后有回调结果，屏蔽了返回键的回调
            startSystemCamera();
        }
        //裁剪的图片的回调
        if (requestCode == CROP_FROM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Uri cropUri = Uri.fromFile(mCropImageFile);
                setPicToView();
            }
        }
        //系统相册
        if (requestCode == REQUEST_CODE_PHOTO_PERMISSION) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            } else {
                chooseImageSys();//打开系统相册
            }
        }
        //从相册选择图片之后
        if (requestCode == PHOTO_PICKED_FROM_FILE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = PictureUtil.getImageUri(this, data);
                startPhotoZoom(uri);
            }
        }
    }
    //调用系统相册
    private void chooseImageSys() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_PICKED_FROM_FILE);
    }

    private void imageCapture() {
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
            pictureUri = FileProvider.getUriForFile(this,
                    "com.tem.gettogether.FileProvider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到pictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, PHOTO_PICKED_FROM_CAMERA);
    }

    /**
     * 系统拍照后裁剪
     */
    public void startSystemCamera() {
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        Uri pictureUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pictureUri = FileProvider.getUriForFile(this,
                    "com.tem.gettogether.FileProvider", pictureFile);
        } else {
            pictureUri = Uri.fromFile(pictureFile);
        }
        startPhotoZoom(pictureUri);
    }

    public void startPhotoZoom(Uri uri) {
        try {
            if (AppUtils.existSDCard()) {
                mCropImageFile = FileUtils.createTmpFile(this);
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
                startActivityForResult(intent, CROP_FROM_CAMERA);
            }
        } catch (Exception e) {

        }

    }
}
