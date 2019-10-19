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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CompanyPersionInformationBean;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.permissions.AppUtils;
import com.tem.gettogether.utils.permissions.FileUtils;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.permissions.PictureUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_corporate_information)

public class CorporateInformationActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private int informationType = 0;// 0:企业信息  1:个人信息
    @ViewInject(R.id.card2_ll)
    private LinearLayout card2_ll;
    @ViewInject(R.id.head_iv)
    private CircularImage head_iv;
    @ViewInject(R.id.change_head_tv)
    private TextView change_head_tv;
    @ViewInject(R.id.name_tv)
    private TextView name_tv;
    @ViewInject(R.id.man_rb)
    private RadioButton man_rb;
    @ViewInject(R.id.lady_rb)
    private RadioButton lady_rb;
    @ViewInject(R.id.phone_num_tv)
    private TextView phone_num_tv;
    @ViewInject(R.id.country_tv)
    private TextView country_tv;
    @ViewInject(R.id.card_status_tv)
    private TextView card_status_tv;
    @ViewInject(R.id.company_address)
    private TextView company_address;
    @ViewInject(R.id.category_tv)
    private TextView category_tv;
    @ViewInject(R.id.company_profile_tv)
    private EditText company_profile_tv;
    @ViewInject(R.id.ll_image_xg)
    private LinearLayout ll_image_xg;
    @ViewInject(R.id.country_ll)
    private LinearLayout country_ll;

    private List<CompanyPersionInformationBean.ResultBean.CountryBean> countryBeanBeans = new ArrayList<>();// 店铺主营大类数据


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
    private int type;
    private String countryId = "-1";

    @Override
    protected void initData() {
        x.view().inject(this);
        informationType = getIntent().getIntExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0);
        tv_title.setText(informationType == 0 ? getResources().getText(R.string.qiyexinxi) : getResources().getText(R.string.gerenxinxi));
        card2_ll.setVisibility(informationType == 0 ? View.VISIBLE : View.GONE);
        if (informationType == 0) {
            getData();
        } else {
            getPersionData();
        }
    }

    private void getPersionData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        showDialog();
        XUtil.Post(URLConstant.PERSION_INFO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====个人信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CompanyPersionInformationBean mCompanyPersionInformationBean = gson.fromJson(result, CompanyPersionInformationBean.class);
                        Glide.with(getContext()).load(mCompanyPersionInformationBean.getResult().getHead_pic() + "").error(R.mipmap.myy322x).centerCrop().into(head_iv);
                        name_tv.setText(mCompanyPersionInformationBean.getResult().getNickname());
                        country_tv.setText(mCompanyPersionInformationBean.getResult().getCountry_name());
                        phone_num_tv.setText(mCompanyPersionInformationBean.getResult().getMobile());
                        countryId = mCompanyPersionInformationBean.getResult().getCountry_id();
                        if (mCompanyPersionInformationBean.getResult().getSex().equals("0")) {
                            man_rb.setChecked(true);
                            lady_rb.setChecked(false);
                        } else {
                            man_rb.setChecked(false);
                            lady_rb.setChecked(true);
                        }
                        countryBeanBeans.removeAll(countryBeanBeans);
                        countryBeanBeans.addAll(mCompanyPersionInformationBean.getResult().getCountry());
                    } else {
                        String msg = jsonObject.optString("msg");
                        CusToast.showToast(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                closeDialog();
                super.onFinished();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();

            }
        });
    }

    @Override
    protected void initView() {

    }

    private void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        showDialog();
        XUtil.Post(URLConstant.ENTERPISE_INFO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====企业信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CompanyPersionInformationBean mCompanyPersionInformationBean = gson.fromJson(result, CompanyPersionInformationBean.class);
                        Glide.with(getContext()).load(mCompanyPersionInformationBean.getResult().getHead_pic() + "").error(R.mipmap.myy322x).centerCrop().into(head_iv);
                        name_tv.setText(mCompanyPersionInformationBean.getResult().getNickname());
                        country_tv.setText(mCompanyPersionInformationBean.getResult().getCountry_name());
                        phone_num_tv.setText(mCompanyPersionInformationBean.getResult().getMobile());
                        company_profile_tv.setText(mCompanyPersionInformationBean.getResult().getCompany_content());
                        countryId = mCompanyPersionInformationBean.getResult().getCountry_id();

                        if (mCompanyPersionInformationBean.getResult().getCompany_province() == null || mCompanyPersionInformationBean.getResult().getCompany_province().equals("null")) {
                            company_address.setText("");
                        } else {
                            company_address.setText(mCompanyPersionInformationBean.getResult().getCompany_province()
                                    + mCompanyPersionInformationBean.getResult().getCompany_district()
                                    + mCompanyPersionInformationBean.getResult().getCompany_city()
                                    + mCompanyPersionInformationBean.getResult().getCompany_address());
                        }
                        if (mCompanyPersionInformationBean.getResult().getSex().equals("0")) {
                            man_rb.setChecked(true);
                            lady_rb.setChecked(false);
                        } else {
                            man_rb.setChecked(false);
                            lady_rb.setChecked(true);
                        }
                        category_tv.setText(mCompanyPersionInformationBean.getResult().getSc_name());
                        countryBeanBeans.removeAll(countryBeanBeans);
                        countryBeanBeans.addAll(mCompanyPersionInformationBean.getResult().getCountry());
                        Log.d("chenshichun", "======size=====" + mCompanyPersionInformationBean.getResult().getCountry().size());
                        Log.d("chenshichun", "=====countryBeanBeanscountryBeanBeans======" + countryBeanBeans.size());
                    } else {
                        String msg = jsonObject.optString("msg");
                        CusToast.showToast(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                closeDialog();
                super.onFinished();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }

    private void saveData(String role_type) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        map.put("country_id", countryId);
        map.put("role_type", role_type);
        map.put("sex", man_rb.isChecked() ? "0" : "1");
        map.put("company_content", company_profile_tv.getText().toString());
        XUtil.Post(URLConstant.ENTERPISE_INFO_SAVE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====保存企业信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.optString("msg");
                    String res = jsonObject.optString("status");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }

    @Event(value = {R.id.rl_close, R.id.ll_image_xg, R.id.country_ll, R.id.save_tv}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_image_xg:
                type = 0;
                showPop(ll_image_xg);
                break;
            case R.id.country_ll:
                type = 1;
                showCountryPop(country_ll);
                break;
            case R.id.save_tv:
                if (informationType == 0) {// 企业
                    saveData("1");
                } else {// 个人
                    saveData("0");
                }
                break;
        }
    }

    private PopupWindow mPop;
    private PopupWindow mCountryPop;

    //显示弹窗
    private void showCountryPop(View v) {
        initmCountryPop();
        if (mCountryPop.isShowing())
            return;
        //设置弹窗底部位置
        mCountryPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private void showPop(View v) {
        Log.d("chenshichun", "====showPop=======");
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;

    //初始化弹窗
    private void initmCountryPop() {
        if (mCountryPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.main_qgxx_layout, null);
            mCountryPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mCountryPop.setFocusable(true);
            mCountryPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mCountryPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mCountryPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mCountryPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            tv_xz_title.setText(getText(R.string.select_country));
            initSetdialog();
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCountryPop.dismiss();
                }
            });
        }
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

    private void initSetdialog() {
        Log.d("chenshichun", "======countryBeanBeans=====" + countryBeanBeans);
        Log.d("chenshichun", "=======countryBeanBeanssss====" + countryBeanBeans.get(0).getCountry_name());
        BaseRVAdapter baseRVAdapter = new BaseRVAdapter(this, countryBeanBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                Log.d("chenshichun", "=========countryBeanBeans.get(position).getCountry_name()==" + countryBeanBeans.get(position).getCountry_name());
                holder.getTextView(R.id.tv_item).setText(countryBeanBeans.get(position).getCountry_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        countryId = countryBeanBeans.get(position).getId();
                        country_tv.setText(countryBeanBeans.get(position).getCountry_name());
                        mCountryPop.dismiss();
                    }
                });
            }

        };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }

    private void upXGMessageData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.XIUGAI_MESSAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====修改个人信息===", result);
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

    public void setPicToView() {
        if (mCropImageFile != null) {
            String path = mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
            head_iv.setImageBitmap(bitmap);
            Map<String, Object> map = new HashMap<>();
            map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
            upMessageData(map);
//            GlideLoadUtils.getInstance().glideAvatarLoad(this, path, iv_head, R.mipmap.myy322x);

        }
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

    private void upMessageData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.SHANGCHUAN_IMAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====上传头像===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean = gson.fromJson(result, ImageDataBean.class);
                        Map<String, Object> map = new HashMap<>();
                        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                        if (imageDataBean.getResult().getImage_show().size() >= 0) {
                            map.put("head_pic", imageDataBean.getResult().getImage_show().get(0));
                        }
                        upXGMessageData(map);
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
