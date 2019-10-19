package com.tem.gettogether.activity.my.shopauthentication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.UploadQYActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Contacts;
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
import me.nereo.multi_image_selector.bean.Image;

@ContentView(R.layout.activity_distributor_rz)
public class DistributorAuthenticationActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rl_card_image1)
    private RelativeLayout rl_card_image1;
    @ViewInject(R.id.rl_card_image2)
    private RelativeLayout rl_card_image2;
    @ViewInject(R.id.rl_card_image3)
    private RelativeLayout rl_card_image3;
    @ViewInject(R.id.rl_card_image4)
    private RelativeLayout rl_card_image4;
    @ViewInject(R.id.rl_card_image5)
    private RelativeLayout rl_card_image5;
    @ViewInject(R.id.iv_image_1)
    private ImageView iv_image_1;
    @ViewInject(R.id.iv_image_2)
    private ImageView iv_image_2;
    @ViewInject(R.id.iv_image_3)
    private ImageView iv_image_3;
    @ViewInject(R.id.iv_image_4)
    private ImageView iv_image_4;
    @ViewInject(R.id.iv_image_5)
    private ImageView iv_image_5;
    @ViewInject(R.id.et_card_num)
    private EditText et_card_num;
    @ViewInject(R.id.factory_ll)
    private LinearLayout factory_ll;
    @ViewInject(R.id.not_persion_ll)
    private LinearLayout not_persion_ll;
    @ViewInject(R.id.tv_upStep)
    private TextView tv_upStep;
    @ViewInject(R.id.iv_close1)
    private ImageView iv_close1;
    @ViewInject(R.id.iv_close2)
    private ImageView iv_close2;
    @ViewInject(R.id.iv_close3)
    private ImageView iv_close3;
    @ViewInject(R.id.iv_close4)
    private ImageView iv_close4;
    @ViewInject(R.id.iv_close5)
    private ImageView iv_close5;

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
    private int imageType = 0;
    private File mCropImageFile;
    private String Image_1, Image_2, Image_3, Image_4, Image_5;
    private int apply_type = 0;// 0：经销商  2：工厂

    @Override
    protected void initData() {
        x.view().inject(this);
        apply_type = getIntent().getIntExtra(Contacts.DEALER_OR_FACTORY_AUTHENTICATION, 0);
        if (apply_type == 0) {
            tv_title.setText(getText(R.string.distributor));
            not_persion_ll.setVisibility(View.VISIBLE);
            factory_ll.setVisibility(View.GONE);
        } else if (apply_type == 2) {
            tv_title.setText(getText(R.string.factory));
            not_persion_ll.setVisibility(View.VISIBLE);
            factory_ll.setVisibility(View.VISIBLE);
        } else {
            tv_title.setText(getText(R.string.personal));
            not_persion_ll.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.iv_close1, R.id.iv_close2, R.id.iv_close3, R.id.iv_close4, R.id.iv_close5, R.id.tv_upStep, R.id.rl_close, R.id.rl_card_image1, R.id.rl_card_image2, R.id.rl_card_image3, R.id.rl_card_image4, R.id.rl_card_image5, R.id.tv_save}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_close1:
                iv_image_1.setImageBitmap(null);
                iv_close1.setVisibility(View.GONE);
                break;
            case R.id.iv_close2:
                iv_image_2.setImageBitmap(null);
                iv_close2.setVisibility(View.GONE);
                break;
            case R.id.iv_close3:
                iv_image_3.setImageBitmap(null);
                iv_close3.setVisibility(View.GONE);
                break;
            case R.id.iv_close4:
                iv_image_4.setImageBitmap(null);
                iv_close4.setVisibility(View.GONE);
                break;
            case R.id.iv_close5:
                iv_image_5.setImageBitmap(null);
                iv_close5.setVisibility(View.GONE);
                break;
            case R.id.rl_close:
                closeKeybord(et_card_num, this);
                finish();
                break;
            case R.id.tv_upStep:
                closeKeybord(et_card_num, this);
                finish();
                break;
            case R.id.rl_card_image1:
                imageType = 1;
                showPop(rl_card_image1);
                break;
            case R.id.rl_card_image2:
                imageType = 2;
                showPop(rl_card_image2);
                break;
            case R.id.rl_card_image3:
                imageType = 3;
                showPop(rl_card_image3);
                break;
            case R.id.rl_card_image4:
                imageType = 4;
                showPop(rl_card_image4);
                break;
            case R.id.rl_card_image5:
                imageType = 5;
                showPop(rl_card_image5);
                break;
            case R.id.tv_save:
                if (et_card_num.getText().toString().equals(getResources().getString(R.string.please_input)) || et_card_num.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.please_enter_the_ID_number));
                    return;
                }
                if (iv_image_1.getDrawable() == null) {
                    CusToast.showToast(getText(R.string.please_upload_the_ID_card_front));
                    return;
                }
                if (iv_image_2.getDrawable() == null) {
                    CusToast.showToast(getText(R.string.please_upload_the_reverse_side_of_the_ID_card));
                    return;
                }


                if (apply_type != 1) {

                    if (iv_image_3.getDrawable() == null) {
                        CusToast.showToast(getText(R.string.please_upload_business_license));
                        return;
                    }

                    if (apply_type == 2) {
                        if (iv_image_4.getDrawable() == null && iv_image_5.getDrawable() == null) {
                            CusToast.showToast(getText(R.string.please_upload_factory_real_map));
                            return;
                        }
                    }

                    Map<String, Object> map = new HashMap<>();
                    map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                    map.put("legal_identity", et_card_num.getText().toString());
                    map.put("apply_type", apply_type);
                    map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));

                    if (Image_1 != null || Image_2 != null) {
                        map.put("legal_identity_cert", Image_1 + "," + Image_2);
                    }
                    if (Image_3 != null) {
                        map.put("business_licence_cert", Image_3);
                    }
                    if (Image_4 != null || Image_5 != null) {
                        map.put("factory_scene", Image_4 + "," + Image_5);
                    }
                    upTJSHData(map);
                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                    map.put("store_person_identity", et_card_num.getText().toString());
                    map.put("apply_type", apply_type);
                    if (Image_1 != null || Image_2 != null) {
                        map.put("store_person_cert", Image_1 + "," + Image_2);
                    }
                    map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));

                    upPersionData(map);
                }
                break;

        }
    }


    private void upTJSHData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.QIYEZIZI_SHANGFC, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====企业资质上传===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        String role_type = SharedPreferencesUtils.getString(DistributorAuthenticationActivity.this, BaseConstant.SPConstant.ROLE_TYPE, "");
                        if (role_type != null && role_type.equals("1")) {
                            startActivity(new Intent(DistributorAuthenticationActivity.this, com.tem.gettogether.activity.MainActivity.class)
                                    .putExtra("tab", "5"));
                        } else {
                            startActivity(new Intent(DistributorAuthenticationActivity.this, com.tem.gettogether.activity.MainActivity.class)
                                    .putExtra("tab", "6"));
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

    private void upPersionData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.GERENZIZI_SHANGFC, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("==== 个人资质上传===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        String role_type = SharedPreferencesUtils.getString(DistributorAuthenticationActivity.this, BaseConstant.SPConstant.ROLE_TYPE, "");
                        if (role_type != null && role_type.equals("1")) {
                            startActivity(new Intent(DistributorAuthenticationActivity.this, com.tem.gettogether.activity.MainActivity.class)
                                    .putExtra("tab", "5"));
                        } else {
                            startActivity(new Intent(DistributorAuthenticationActivity.this, com.tem.gettogether.activity.MainActivity.class)
                                    .putExtra("tab", "6"));
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
    ImageDataBean imageDataBean = null;
    public void setPicToView() {
        if (mCropImageFile != null) {
            final String path = mCropImageFile.getAbsolutePath();
//            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
//            Map<String, Object> map = new HashMap<>();
//            map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
//            upInputImageData(map, bitmap);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(path), new File(path), URLConstant.UPLOAD_PICTURE);
                        if (imageDataBean != null) {
                            mHandle.sendEmptyMessage(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (imageType == 1) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0)+"").error( R.mipmap.myy322x).centerCrop().into(iv_image_1);
                        iv_image_1.setVisibility(View.VISIBLE);
                        iv_close1.setVisibility(View.VISIBLE);
                        Image_1 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 2) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0)+"").error( R.mipmap.myy322x).centerCrop().into(iv_image_2);
                        iv_image_2.setVisibility(View.VISIBLE);
                        iv_close2.setVisibility(View.VISIBLE);
                        Image_2 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 3) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0)+"").error( R.mipmap.myy322x).centerCrop().into(iv_image_3);
                        iv_image_3.setVisibility(View.VISIBLE);
                        iv_close3.setVisibility(View.VISIBLE);
                        Image_3 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 4) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0)+"").error( R.mipmap.myy322x).centerCrop().into(iv_image_4);
                        iv_image_4.setVisibility(View.VISIBLE);
                        iv_close4.setVisibility(View.VISIBLE);
                        Image_4 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 5) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0)+"").error( R.mipmap.myy322x).centerCrop().into(iv_image_5);
                        iv_image_5.setVisibility(View.VISIBLE);
                        iv_close5.setVisibility(View.VISIBLE);
                        Image_5 = imageDataBean.getResult().getImage_show().get(0);
                    }
                    break;
                case 1:
                    break;
            }
        }
    };

    private void upInputImageData(Map<String, Object> map, final Bitmap bitmap) {
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
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean = gson.fromJson(result, ImageDataBean.class);
                        if (imageType == 1) {
                            iv_image_1.setImageBitmap(bitmap);
                            iv_image_1.setVisibility(View.VISIBLE);
                            iv_close1.setVisibility(View.VISIBLE);
                            Image_1 = imageDataBean.getResult().getImage_show().get(0);
                        } else if (imageType == 2) {
                            iv_image_2.setImageBitmap(bitmap);
                            iv_image_2.setVisibility(View.VISIBLE);
                            iv_close2.setVisibility(View.VISIBLE);
                            Image_2 = imageDataBean.getResult().getImage_show().get(0);
                        } else if (imageType == 3) {
                            iv_image_3.setImageBitmap(bitmap);
                            iv_image_3.setVisibility(View.VISIBLE);
                            iv_close3.setVisibility(View.VISIBLE);
                            Image_3 = imageDataBean.getResult().getImage_show().get(0);
                        } else if (imageType == 4) {
                            iv_image_4.setImageBitmap(bitmap);
                            iv_image_4.setVisibility(View.VISIBLE);
                            iv_close4.setVisibility(View.VISIBLE);
                            Image_4 = imageDataBean.getResult().getImage_show().get(0);
                        } else if (imageType == 5) {
                            iv_image_5.setImageBitmap(bitmap);
                            iv_image_5.setVisibility(View.VISIBLE);
                            iv_close5.setVisibility(View.VISIBLE);
                            Image_5 = imageDataBean.getResult().getImage_show().get(0);
                        }
                    } else {

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
                /*if (Build.MANUFACTURER.equals("HUAWEI")) {//解决华为手机调用裁剪出现圆形裁剪框
                    intent.putExtra("aspectX", 9998);
                    intent.putExtra("aspectY", 9999);
                } else {
                    intent.putExtra("aspectX", 1); // 裁剪框比例
                    intent.putExtra("aspectY", 1);
                }*/
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
