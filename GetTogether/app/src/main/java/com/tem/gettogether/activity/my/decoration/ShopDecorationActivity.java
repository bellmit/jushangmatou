package com.tem.gettogether.activity.my.decoration;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.my.ShopBgActivity;
import com.tem.gettogether.activity.my.ShopLogoActivity;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsActivity;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.bean.ShopDecorationBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.PhotoUtil;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.permissions.PictureUtil;
import com.tem.gettogether.view.CircularImage;
import com.tem.gettogether.view.DecorationNoticeDialog;
import com.tem.gettogether.view.IdentityDialog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.decoration
 * @ClassName: ShopDecorationActivity
 * @Author: csc
 * @CreateDate: 2019/9/17 11:11
 * @Description:
 */

@ContentView(R.layout.activity_shop_decoration)
public class ShopDecorationActivity extends BaseMvpActivity<ShopDecorationPresenter> implements ShopDecorationContract.ShopDecorationView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_cpName)
    private TextView et_cpName;
    @ViewInject(R.id.logo_pic_iv)
    private CircularImage logo_pic_iv;
    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView publish_recy;
    @ViewInject(R.id.jianjie_tv)
    private EditText jianjie_tv;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String logo_image = "";
    private String bg_image = "";
    ShopDecorationBean.ResultBean mResultBean = new ShopDecorationBean.ResultBean();
    private String compressImageFilePath = "";
    private ArrayList<String> compressPaths = new ArrayList<>();
    final List<String> cartImage = new ArrayList<>();
    private String ad_code = "";
    private static String IMAGE_FILE_NAME = "user_head_icon.jpg";
    private final int PHOTO_PICKED_FROM_CAMERA = 111; // 用来标识头像来自系统拍照
    private final int CROP_FROM_CAMERA = 333;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        tv_title.setText(getText(R.string.store_construction));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(R.string.decoration_notice);
        imagePaths.clear();
        imagePaths.add(R.drawable.addtupian + "");
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress2/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    protected void initView() {
        mPresenter = new ShopDecorationPresenter(getContext(), ShopDecorationActivity.this);
        mPresenter.attachView(this);

        Map<String, Object> map = new HashMap<>();
        map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
        mPresenter.getShopDecorationData(map);

        publish_recy.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(this, imagePaths, this, this, 0);
        publish_recy.setAdapter(mTaskImgAdapter);
    }

    @Event(value = {R.id.shop_logo_ll, R.id.shop_bg_ll, R.id.rl_close, R.id.tv_fbShopping,R.id.tv_title_right})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.shop_logo_ll:
                startActivityForResult(new Intent(this, ShopLogoActivity.class).putExtra("logo_image", logo_image)
                        , 1000);
                break;
            case R.id.shop_bg_ll:
                startActivityForResult(new Intent(this, ShopBgActivity.class).putExtra("bg_image", bg_image)
                        , 1001);
                break;
            case R.id.tv_fbShopping:
                Map<String, Object> map = new HashMap<>();
                map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                map.put("store_name", et_cpName.getText().toString());
                map.put("app_store_logo", logo_image);
                map.put("app_store_banner", bg_image);
                map.put("seo_description", jianjie_tv.getText().toString());
                Log.d("chenshichun", "======seo_description=====" + jianjie_tv.getText().toString());
                ad_code = "";
                imagePaths.remove(imagePaths.size() - 1);
                if (imagePaths.size() > 0) {
                    for (int i = 0; i < imagePaths.size(); i++) {
                        if (i < imagePaths.size() - 1) {
                            ad_code += imagePaths.get(i) + ",";
                        } else {
                            ad_code += imagePaths.get(i);
                        }
                    }
                }
                map.put("ad_code", ad_code);
                Log.d("chenshichun", "======轮播=====" + ad_code);
                mPresenter.getShopDecorationModifyData(map);

                break;
            case R.id.tv_title_right:
                DecorationNoticeDialog mDecorationNoticeDialog = new DecorationNoticeDialog.Builder(mContext).create();
                mDecorationNoticeDialog.show();
                break;
        }
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        closeDialog();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void getShopDecorationData(ShopDecorationBean.ResultBean mResultBean) {
        this.mResultBean = mResultBean;
        et_cpName.setText(mResultBean.getStore_name());
        jianjie_tv.setText(mResultBean.getSeo_description());
        int imageSize = SizeUtil.dp2px(getContext(), 40);
        Glide.with(getContext()).load(mResultBean.getApp_store_logo()).placeholder(R.drawable.head_bg)
                .error(R.drawable.head_bg).override(imageSize, imageSize).into(logo_pic_iv);
        logo_image = mResultBean.getApp_store_logo();
        bg_image = mResultBean.getApp_store_banner();
        imagePaths.removeAll(imagePaths);
        if (mResultBean.getAd_code() != null && mResultBean.getAd_code().size() > 0) {
            for (int i = 0; i < mResultBean.getAd_code().size(); i++) {
                if (mResultBean.getAd_code().get(i).getAd_code().startsWith("http")) {
                    if (!mResultBean.getAd_code().get(i).getAd_code().equals("")) {
                        imagePaths.add(mResultBean.getAd_code().get(i).getAd_code());
                    }
                } else {
                    if (!mResultBean.getAd_code().get(i).getAd_code().equals("")) {
                        imagePaths.add("http://www.jsmtgou.com/jushangmatou" + mResultBean.getAd_code().get(i).getAd_code());
                    }
                }
            }
        }
        imagePaths.add(R.drawable.addtupian + "");
        mTaskImgAdapter.notifyDataSetChanged();
    }

    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.item_publishTask_image:
                Integer index = (Integer) v.getTag(R.id.postion);
                if (index == imagePaths.size() - 1) {
                    showPop(v);
                } else {
                    ArrayList<String> paths = new ArrayList<>();
                    paths.addAll(imagePaths);
                    paths.remove(paths.get(paths.size() - 1));

                    intent = new Intent(this, ShowImageDetail.class);
                    intent.putStringArrayListExtra("paths", paths);
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
                break;
            case R.id.delete_iv:
                final Integer index1 = (Integer) v.getTag(R.id.postion);
                /**
                 这里使用了 android.support.v7.app.AlertDialog.Builder
                 可以直接在头部写 import android.support.v7.app.AlertDialog
                 那么下面就可以写成 AlertDialog.Builder
                 */
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setMessage(getText(R.string.whether_to_confirm_the_deletion));
                builder.setNegativeButton(getText(R.string.quxiao), null);
                builder.setPositiveButton(getText(R.string.queding), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (imagePaths.size() > 0) {
                            imagePaths.remove(imagePaths.get(index1));
                        }
                        Log.d("chenshichun", "=====imagePaths======" + imagePaths);
//                        if (cartImage.size() > 0) {
//                            cartImage.remove(cartImage.get(index1));
//                        }

                        mTaskImgAdapter.notifyDataSetChanged();
                    }
                });
                if (imagePaths.size() - 1 != index1) {
                    builder.show();
                }
                break;
            default:
                break;
        }
    }

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

    private PopupWindow mPop;

    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.pop_layout_chanpin, null);
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
        TextView tv_paizhao, tv_xiangche, cancle;
        tv_xiangche = view.findViewById(R.id.tv_xiangche);
        tv_paizhao = view.findViewById(R.id.tv_paizhao);
        cancle = view.findViewById(R.id.cancle);
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {//申请WRITE_EXTERNAL_STORAGE权限

                    ActivityCompat.requestPermissions(ShopDecorationActivity.this, new String[]{Manifest.permission.CAMERA}, 1);

                } else {
                    imageCapture();//系统相机拍照
                    mPop.dismiss();
                }

            }
        });

        tv_xiangche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePaths.size() >= 9) {
                    CusToast.showToast(getText(R.string.select_up_to_9_images));
                    return;
                }

                Intent intent = new Intent(ShopDecorationActivity.this, MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9 - imagePaths.size());

                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, FROM_ALBUM_CODE);
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
            pictureUri = FileProvider.getUriForFile(getContext(),
                    "com.tem.gettogether.FileProvider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到pictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, PHOTO_PICKED_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1000:
                    logo_image = data.getStringExtra("logo_image_back");

                    int imageSize = SizeUtil.dp2px(getContext(), 40);
                    Glide.with(getContext()).load(logo_image).placeholder(R.drawable.head_bg)
                            .error(R.drawable.head_bg).override(imageSize, imageSize).into(logo_pic_iv);
                    break;
                case 1001:
                    bg_image = data.getStringExtra("bg_image_back");
                    break;
                case FROM_ALBUM_CODE:
                    if (null != data) {
                        if (!Confirg.compressFile.exists()) {
                            Confirg.compressFile = new File(Confirg.FilesPath);
                            Confirg.compressFile.mkdirs();
                        }
                        final List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        showDialog();
                        if (imagePaths.size() < 10) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < list.size(); i++) {
                                        final String pic_path = list.get(i);
                                        final String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 60);
                                        compressPaths.add(compressImage);
                                        Log.d("chenshichun", "=======targetPath====" + targetPath);

                                        try {
                                            ImageDataBean imageDataBean = null;
                                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_SHOP_BANNER_PICTURE);
                                            if (imageDataBean != null) {
                                                imagePaths.add(imagePaths.size() - 1, imageDataBean.getResult().getImage_show().get(0));
                                                mHandle.sendEmptyMessage(0);
                                            } else {
                                                mHandle.sendEmptyMessage(1);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        if (i == list.size() - 1) {
                                            closeDialog();
                                        }

                                    }
                                }
                            }).start();
                        } else {
                            CusToast.showToast(getText(R.string.unable_to_add_more_images));
                        }
                        mTaskImgAdapter.notifyDataSetChanged();
                    }
                    break;
                case PHOTO_PICKED_FROM_CAMERA:
                    PhotoUtil.startSystemCamera(ShopDecorationActivity.this, getContext());
                    break;
                case CROP_FROM_CAMERA:
                    setPicToView();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setPicToView() {
        if (PhotoUtil.mCropImageFile != null) {
            final String path = PhotoUtil.mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(PhotoUtil.mCropImageFile.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ImageDataBean imageDataBean = null;
                        imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(path), new File(path), URLConstant.UPLOAD_PICTURE);
                        if (imageDataBean != null) {
                            imagePaths.add(imagePaths.size() - 1, imageDataBean.getResult().getImage_show().get(0));
                            mHandle.sendEmptyMessage(0);
                        } else {
                            mHandle.sendEmptyMessage(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mTaskImgAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    break;
            }
        }
    };
}
