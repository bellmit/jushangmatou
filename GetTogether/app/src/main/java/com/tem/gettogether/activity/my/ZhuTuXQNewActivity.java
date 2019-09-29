package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_zhutu)

public class ZhuTuXQNewActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_fbShopping)
    private TextView tv_fbShopping;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.iamge_iv)
    private ImageView iamge_iv;
    private String compressImageFilePath;
    private String cover_image="";
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.product_master_map));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(getText(R.string.save));
        cover_image=getIntent().getStringExtra("cover_image");
        Glide.with(ZhuTuXQNewActivity.this).load(cover_image).error(R.mipmap.myy322x).into(iamge_iv);
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
                        .create(ZhuTuXQNewActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_title_right:
                setResult(RESULT_OK,new Intent().putStringArrayListExtra("listImage2", cartImage));
                finish();
                break;
        }
    }
    ArrayList<String> cartImage = new ArrayList<>();

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
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            ImageDataBean imageDataBean = null;
                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath),new File(targetPath),URLConstant.UPLOAD_PICTURE);
                            if(imageDataBean!=null){
                                cartImage.removeAll(cartImage);
                                cartImage.add(imageDataBean.getResult().getImage_show().get(0));
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

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    closeDialog();
                    Glide.with(ZhuTuXQNewActivity.this).load(cartImage.get(0)).error(R.mipmap.myy322x).into(iamge_iv);
                    break;
                case 1:
                    closeDialog();
                    break;
            }
        }
    };
}
