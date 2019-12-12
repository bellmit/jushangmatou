package com.tem.gettogether.activity.my;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.PhotoUtil;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.permissions.PermissionsChecker;
import com.tem.gettogether.utils.permissions.PictureUtil;
import com.wildma.pictureselector.PictureSelector;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.duduhuo.custoast.CusToast;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

@ContentView(R.layout.activity_tu_wen_xq)
public class TuWenXQActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_fbShopping)
    private TextView tv_fbShopping;
    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.tv_shanchu)
    private TextView tv_shanchu;
    private PermissionsChecker checker;

    private String compressImageFilePath;
    private String[] strPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODEBBB = 100;
    private ArrayList<String> imagePaths = new ArrayList<>();
    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView recyclerView;
    private List<Integer> imageRes = new ArrayList<>();
    private BaseRVAdapter mTaskImgAdapter;
    private String strImage = "";
    private final int CROP_FROM_CAMERA = 333;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);

        checker = new PermissionsChecker(this);
        tv_title.setText(R.string.product_graphic_details);
        tv_title_right.setText(R.string.save);
        cartImage = getIntent().getStringArrayListExtra("listImage");
        tv_title_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new BaseRVAdapter(this, cartImage) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recy_image_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, int position) {
                ImageView iv_image = holder.getImageView(R.id.iv_image);
                Glide.with(TuWenXQActivity.this).load(cartImage.get(position)).error(R.mipmap.myy322x).into(iv_image);
            }

        };
        recyclerView.setAdapter(mTaskImgAdapter);
    }

    @Event(value = {R.id.rl_close, R.id.tv_fbShopping, R.id.rl_title_right, R.id.tv_shanchu}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_fbShopping:
                showPop(tv_fbShopping);
                break;
            case R.id.rl_title_right:
                if (cartImage.size() > 0) {
                    for (int i = 0; i < cartImage.size(); i++) {
                        if (i < cartImage.size() - 1) {
                            strImage += cartImage.get(i) + ",";
                        } else {
                            strImage += cartImage.get(i);
                        }
                    }
//                    Log.i("===返回图片--",strImage);
                    setResult(RESULT_OK, new Intent().putExtra("goods_content", strImage)
                            .putStringArrayListExtra("listImage", cartImage));
                    finish();
                } else {
                    CusToast.showToast(getText(R.string.please_add_an_image));
                    return;
                }
                break;
            case R.id.tv_shanchu:
                cartImage.remove(cartImage.size() - 1);
                imagePaths.remove(imagePaths.size() - 1);
                mTaskImgAdapter.notifyDataSetChanged();
                if (cartImage.size() > 0) {
                    tv_shanchu.setVisibility(View.VISIBLE);
                } else {
                    tv_shanchu.setVisibility(View.GONE);
                }
                break;
        }
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

    private void setPopClickListener(View view) {
        TextView tv_paizhao, tv_xiangche, cancle;
        tv_xiangche = view.findViewById(R.id.tv_xiangche);
        tv_paizhao = view.findViewById(R.id.tv_paizhao);
        cancle = view.findViewById(R.id.cancle);
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {//申请WRITE_EXTERNAL_STORAGE权限

                    ActivityCompat.requestPermissions(TuWenXQActivity.this, new String[]{Manifest.permission.CAMERA}, 1);

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

                Intent intent = new Intent(TuWenXQActivity.this, MultiImageSelectorActivity.class);
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

    private static String IMAGE_FILE_NAME = "user_head_icon.jpg";
    private final int PHOTO_PICKED_FROM_CAMERA = 111; // 用来标识头像来自系统拍照

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
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checker.lacksPermissions(strPermissions)) {
                permissionActivity();
            }
        }
    }

    private void permissionActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODEBBB, strPermissions);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //相册
                case FROM_ALBUM_CODE:
                    if (null != data) {
                        if (!Confirg.compressFile.exists()) {
                            Confirg.compressFile = new File(Confirg.FilesPath);
                            Confirg.compressFile.mkdirs();
                        }

                        final List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        showDialog();
                        if (imagePaths.size() < 9) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < list.size(); i++) {
                                        final String pic_path = list.get(i);
                                        final String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 90);
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        final int finalI = i;

                                        try {
                                            ImageDataBean imageDataBean = null;
                                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_PICTURE);

                                            if (imageDataBean != null) {
                                                imagePaths.add(list.get(finalI));
                                                cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                                                mHandle.sendEmptyMessage(0);
                                            } else {
                                                mHandle.sendEmptyMessage(1);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        if (i == list.size() - 1) {
                                            Log.e("chenshichun", "---stop--");
                                            closeDialog();
                                        }
                                    }
                                }
                            }).start();
                        } else {
                            CusToast.showToast(getText(R.string.unable_to_add_more_images));
                        }
                    }
                    break;
                case PHOTO_PICKED_FROM_CAMERA:
                    PhotoUtil.startSystemCamera(TuWenXQActivity.this, getContext());
                    break;
                case CROP_FROM_CAMERA:
                    setPicToView();
                    break;
            }
        }
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
                            imagePaths.add(imagePaths.size(), path);
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

    ArrayList<String> cartImage = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity_distributor_rz in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mTaskImgAdapter.notifyDataSetChanged();
                    if (cartImage.size() > 0) {
                        tv_shanchu.setVisibility(View.VISIBLE);
                    } else {
                        tv_shanchu.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    CusToast.showToast(getText(R.string.upload_failed));
                    break;
            }
        }
    };
}
