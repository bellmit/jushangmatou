package com.tem.gettogether.activity.my.publishgoods;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.my.TextDescriptionActivity;
import com.tem.gettogether.activity.my.TuWenXQActivity;
import com.tem.gettogether.activity.my.ZhuTuXQNewActivity;
import com.tem.gettogether.activity.my.specifications.SpecificationsActivity;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.PhotoUtil;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.permissions.PermissionsActivity;

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
 * @Package: com.tem.gettogether.activity.my.publishgoods
 * @ClassName: PublishGoodsActivity
 * @Author: csc
 * @CreateDate: 2019/9/6 14:24
 * @Description:店铺上新
 */
@ContentView(R.layout.activity_publish_goods)
public class PublishGoodsActivity extends BaseMvpActivity<PublishGoodsPresenter> implements PublishGoodsContract.PublishGoodsView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_cpName)
    private EditText et_cpName;
    @ViewInject(R.id.et_ShoppingHH)
    private EditText et_ShoppingHH;
    @ViewInject(R.id.et_QPNum)
    private EditText et_QPNum;
    @ViewInject(R.id.rt_tuwen)
    private TextView rt_tuwen;
    @ViewInject(R.id.tv_XZshopFL)
    private TextView tv_XZshopFL;
    @ViewInject(R.id.et_ShopingSJ)
    private EditText et_ShopingSJ;
    @ViewInject(R.id.yes_rb)
    private RadioButton yes_rb;
    @ViewInject(R.id.et_KuCNum)
    private EditText et_KuCNum;
    @ViewInject(R.id.et_GuanJC)
    private EditText et_GuanJC;
    @ViewInject(R.id.et_ShoppingJJ)
    private EditText et_ShoppingJJ;
    @ViewInject(R.id.text_description_tv)
    private TextView text_description_tv;
    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView publish_recy;
    @ViewInject(R.id.ll_shop_FL)
    private LinearLayout ll_shop_FL;
    @ViewInject(R.id.fengmian_tv)
    private TextView fengmian_tv;
    @ViewInject(R.id.tv_ShoppingGG)
    private TextView tv_ShoppingGG;
    private List<CategoriesBean.ResultBean> mCategoriesBeans = new ArrayList<>();
    private String majorClassId = "";// 大类ID
    private String smallClassId = "";// 小类ID
    private String textDescription = "";// 详情介绍返回值
    private ArrayList<String> listImage = new ArrayList<>();
    private String goods_content = "";
    private String cover_image = "";
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;
    private List<Integer> imageRes = new ArrayList<>();
    private ArrayList<String> imagePaths = new ArrayList<>();
    final List<String> cartImage = new ArrayList<>();
    private String strTwoImage = "";
    private String compressImageFilePath;
    private ArrayList<String> compressPaths = new ArrayList<>();
    private String sku_str = "";
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.new_on_the_store));
        yes_rb.setChecked(true);
        et_ShopingSJ.setText(getText(R.string.negotiable_tv));
        et_ShopingSJ.setEnabled(false);
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
        mPresenter = new PublishGoodsPresenter(getContext(), PublishGoodsActivity.this);
        mPresenter.attachView(this);
        getSwipeBackLayout().setEnableGesture(false);//禁止右滑退出

        // 请求商品分类数据
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        mPresenter.getStoreCate(map);

        publish_recy.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(this, imagePaths, this, this, 0);
        publish_recy.setAdapter(mTaskImgAdapter);

        yes_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_ShopingSJ.setText(getText(R.string.negotiable_tv));
                    et_ShopingSJ.setEnabled(false);
                    closeKeybord(et_ShopingSJ, getContext());
                } else {
                    et_ShopingSJ.setText("");
                    et_ShopingSJ.setEnabled(true);
                }
            }
        });

    }

    @Event(value = {R.id.ll_fengmian, R.id.rl_close, R.id.tv_fbShopping, R.id.text_description_ll, R.id.ll_XQ_ms, R.id.ll_shop_FL, R.id.ll_shop_GG}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                dialog();
                break;
            case R.id.ll_shop_FL://商品分类

                if (mCategoriesBeans != null)
                    mPresenter.showStoreCatePop(ll_shop_FL, mCategoriesBeans);
                break;
            case R.id.ll_shop_GG://商品规格
                if (majorClassId.equals("") || smallClassId.equals("")) {
                    CusToast.showToast(getText(R.string.please_select_a_product_category));
                    return;
                }
                startActivityForResult(new Intent(this, SpecificationsActivity.class)
                        .putExtra("cat_id3", "" + smallClassId),1);
                break;
            case R.id.text_description_ll://详情介绍
                startActivityForResult(new Intent(this, TextDescriptionActivity.class)
                        .putExtra("text_description", textDescription), 8888);
                break;
            case R.id.ll_XQ_ms://详情图片
                startActivityForResult(new Intent(this, TuWenXQActivity.class)
                        .putStringArrayListExtra("listImage", listImage), 6666);
                break;
            case R.id.ll_fengmian://商品主图
                startActivityForResult(new Intent(this, ZhuTuXQNewActivity.class)
                        .putExtra("cover_image", cover_image), 7777);
                break;
            case R.id.tv_fbShopping://发布产品
                publishGoods();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return false;
        }
        return false;
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PublishGoodsActivity.this);
        builder.setMessage(getText(R.string.are_you_sure_you_want_to_quit));
        builder.setTitle(getText(R.string.prompt));
        builder.setPositiveButton(getText(R.string.sure_tv),
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        builder.setNegativeButton(getText(R.string.quxiao),
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
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
    public void getStoreCate(List<CategoriesBean.ResultBean> mCategoriesBeans) {
        this.mCategoriesBeans = mCategoriesBeans;
    }

    @Override
    public void getChooseStoreCate(String majorClassName, String smallClassName, String majorClassId, String smallClassId) {
        this.majorClassId = majorClassId;
        this.smallClassId = smallClassId;
        tv_XZshopFL.setText(majorClassName + " " + smallClassName);
    }

    private void publishGoods() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));

        if (et_cpName.getText().toString().equals("")) {
            CusToast.showToast(getText(R.string.please_fill_in_the_product_name));
            return;
        }
        if (et_GuanJC.getText().toString().equals("")) {
            CusToast.showToast(getText(R.string.please_fill_in_the_keywords));
            return;
        }
        if (et_QPNum.getText().toString().equals("")) {
            CusToast.showToast(getText(R.string.please_fill_in_the_number_of_batches));
            return;
        }
        if (majorClassId.equals("") || smallClassId.equals("")) {
            CusToast.showToast(getText(R.string.please_select_a_product_category));
            return;
        }
        if (et_ShopingSJ.getText().toString().equals("")) {
            CusToast.showToast(getText(R.string.please_fill_in_the_price_of_the_product));
            return;
        }
        if (cover_image.equals("")) {
            CusToast.showToast(getText(R.string.please_upload_the_main_product_map));
            return;
        }
        strTwoImage = "";
        if (cartImage.size() > 0) {
            for (int i = 0; i < cartImage.size(); i++) {
                if (i < cartImage.size() - 1) {
                    strTwoImage += cartImage.get(i) + ",";
                } else {
                    strTwoImage += cartImage.get(i);
                }
            }
        }
        if (strTwoImage.equals("")) {
            CusToast.showToast(getText(R.string.please_upload_the_product_carousel));
            return;
        }

        //商品名称
        map.put("goods_name", et_cpName.getText().toString());
        //商品货号
        map.put("goods_sn", et_ShoppingHH.getText().toString());
        //起批个数
        map.put("batch_number", et_QPNum.getText().toString());
        //商品分类大ID
        map.put("cat_id2", majorClassId);
        //商品分类小ID
        map.put("cat_id3", smallClassId);
        //是否询价
        map.put("is_enquiry", yes_rb.isChecked() ? "1" : "0");
        //商品价格
        map.put("shop_price", et_ShopingSJ.getText().toString());
        //库存数量
        map.put("store_count", et_KuCNum.getText().toString());
        //关键词
        map.put("keywords", et_GuanJC.getText().toString());
        //商品简介
        map.put("goods_remark", et_ShoppingJJ.getText().toString());
        //详情介绍
        if (!textDescription.equals("")) {
            map.put("goods_content", textDescription);
        }
        // 规格
        map.put("sku_str", sku_str);
        //详情图
        map.put("original_img", goods_content);
        //主图
        map.put("cover_image", cover_image);
        //轮播图
        map.put("goods_images", strTwoImage);
        map.put("is_on_sale", "1");
        mPresenter.uploadProduct(map);
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:// 规格
                    if(data != null) {
                        tv_ShoppingGG.setText(data.getStringExtra("key_name"));
                        sku_str = data.getStringExtra("item");
                    }
                    Log.e("chenshichun","-----sku_str  "+sku_str);
                    break;
                case 8888:
                    textDescription = data.getStringExtra("text_description");
                    text_description_tv.setText(textDescription);
                    break;
                case 6666:
                    goods_content = data.getExtras().getString("goods_content");
                    listImage = data.getStringArrayListExtra("listImage");
                    rt_tuwen.setText(getText(R.string.filled_in));
                    break;
                case 7777:
                    fengmian_tv.setText(getText(R.string.filled_in));
                    if (data.getStringArrayListExtra("listImage2").size() > 0)
                        cover_image = data.getStringArrayListExtra("listImage2").get(0);
                    break;
                case FROM_ALBUM_CODE:
                    if (null != data) {
                        if (!Confirg.compressFile.exists()) {
                            Confirg.compressFile = new File(Confirg.FilesPath);
                            Confirg.compressFile.mkdirs();
                        }
                        final List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        if (imagePaths.size() < 10) {
                            for (int i = 0; i < list.size(); i++) {
                                final String pic_path = list.get(i);
                                final String targetPath = compressImageFilePath + Confirg.df.
                                        format(new Date()) + ".jpg";
                                //调用压缩图片的方法，返回压缩后的图片path
                                final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 60);
                                compressPaths.add(compressImage);
                                Log.d("chenshichun", "=====targetPath======" + targetPath);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            ImageDataBean imageDataBean = null;
                                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_PICTURE);
                                            if (imageDataBean != null) {
                                                imagePaths.add(imagePaths.size() - 1, pic_path);
                                                cartImage.add(imageDataBean.getResult().getImage_show().get(0));
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
                        } else {
                            CusToast.showToast(getText(R.string.unable_to_add_more_images));
                        }
                        mTaskImgAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }

        //系统相机权限
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            } else {
                PhotoUtil.imageCapture(PublishGoodsActivity.this);//系统相机拍照
            }

        }
        //拍照完成的回调
        if (requestCode == PHOTO_PICKED_FROM_CAMERA && resultCode == Activity.RESULT_OK) {//Activity.RESULT_OK可以确保拍照后有回调结果，屏蔽了返回键的回调
            PhotoUtil.startSystemCamera(PublishGoodsActivity.this, getContext());
        }
        //裁剪的图片的回调
        if (requestCode == CROP_FROM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                Uri cropUri = Uri.fromFile(mCropImageFile);
                setPicToView();
            }
        }

    }

    public void setPicToView() {
        if (mCropImageFile != null) {
            final String path = mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ImageDataBean imageDataBean = null;
                        imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(path), new File(path), URLConstant.UPLOAD_PICTURE);
                        if (imageDataBean != null) {
                            imagePaths.add(imagePaths.size() - 1, path);
                            cartImage.add(imageDataBean.getResult().getImage_show().get(0));
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

    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.item_publishTask_image:
                Integer index = (Integer) v.getTag(R.id.postion);
                if (index == imagePaths.size() - 1) {

                    if (imagePaths.size() >= 10) {
                        CusToast.showToast(getText(R.string.select_up_to_9_images));
                        return;
                    }

                    intent = new Intent(this, MultiImageSelectorActivity.class);
                    // 是否显示调用相机拍照
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                    // 最大图片选择数量
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10 - imagePaths.size());
                    // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                    startActivityForResult(intent, FROM_ALBUM_CODE);
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
                        if (cartImage.size() > 0) {
                            cartImage.remove(cartImage.get(index1));
                        }
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
