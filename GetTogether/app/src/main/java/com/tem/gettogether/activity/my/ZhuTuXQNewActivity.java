package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
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

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("商品主图");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("保存");
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
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.rl_title_right:
                Log.d("chenshichun","==========cartImage=  "+cartImage);
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
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                iamge_iv.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                String targetPath = compressImageFilePath + Confirg.df.
                        format(new Date()) + ".jpg";
                final String compressImage = BitnapUtils.compressImage(picturePath, targetPath, 60);

                Map<String, Object> map = new HashMap<>();
                map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(BitmapFactory.decodeFile(compressImage)));
                upMessageData(map);
            }
        }
    }

    private void upMessageData(Map<String, Object> map) {
        XUtil.Post(URLConstant.SHANGCHUAN_IMAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====上传图片===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean = gson.fromJson(result, ImageDataBean.class);
                        Map<String, Object> map = new HashMap<>();
                        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                        if (imageDataBean.getResult().getImage_show().size() >= 0) {
                            map.put("head_pic", imageDataBean.getResult().getImage_show().get(0));
                            cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                        }
                    } else {
                        CusToast.showToast(msg);
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
}
