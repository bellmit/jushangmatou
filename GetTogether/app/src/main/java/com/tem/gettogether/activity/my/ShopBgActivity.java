package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.wildma.pictureselector.PictureSelector;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.Date;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my
 * @ClassName: ShopBgActivity
 * @Author: csc
 * @CreateDate: 2019/9/17 14:56
 * @Description:
 */
@ContentView(R.layout.activity_shop_bg)
public class ShopBgActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.iamge_iv)
    private ImageView iamge_iv;
    @ViewInject(R.id.tv_fbShopping)
    private TextView tv_fbShopping;
    private String compressImageFilePath;
    private String bg_image = "";

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("店铺背景");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("保存");
        bg_image = getIntent().getStringExtra("bg_image");
        Glide.with(ShopBgActivity.this).load(bg_image).error(R.drawable.head_bg).into(iamge_iv);
    }

    @Override
    protected void initView() {
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Event(value = {R.id.rl_close, R.id.tv_fbShopping, R.id.rl_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_fbShopping:
                PictureSelector
                        .create(ShopBgActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_title_right:
                setResult(RESULT_OK, new Intent().putExtra("bg_image_back", bg_image));
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                final String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                final String targetPath = compressImageFilePath + Confirg.df.
                        format(new Date()) + ".jpg";
                final String compressImage = BitnapUtils.compressImage(picturePath, targetPath, 40);
                showDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ImageDataBean imageDataBean = null;
                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_SHOP_PICTURE);
                            if (imageDataBean != null) {
                                bg_image = imageDataBean.getResult().getImage_show().get(0);
                                mHandle.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            mHandle.sendEmptyMessage(1);
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    closeDialog();
                    Glide.with(ShopBgActivity.this).load(bg_image).error(R.drawable.head_bg).into(iamge_iv);
                    break;
                case 1:
                    closeDialog();
                    break;
            }
        }
    };

}
