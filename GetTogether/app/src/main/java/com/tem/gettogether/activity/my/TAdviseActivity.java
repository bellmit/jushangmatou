package com.tem.gettogether.activity.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

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
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

@ContentView(R.layout.activity_tadvise)
public class TAdviseActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_yijian)
    private EditText et_yijian;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;
    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code
    final List<String> cartImage = new ArrayList<>();
    private String compressImageFilePath;
    private ArrayList<String> compressPaths = new ArrayList<>();
    private String strTwoImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        initData();
        initView();
        imagePaths.clear();
        imagePaths.add(R.drawable.addtupian + "");
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress2/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.yjfg);

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(this, imagePaths, this, this, 0);
        mRecyclerView.setAdapter(mTaskImgAdapter);
    }

    @Event(value = {R.id.rl_close, R.id.tv_yjtj}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_yjtj:
                String content = et_yijian.getText().toString();
                if (content.length() < 6) {
                    CusToast.showToast(getText(R.string.no_less_than_six_words));
                    return;
                }
                upYJData(content);
                break;
        }
    }

    private void upYJData(String msg_content) {
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

        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("msg_content", msg_content);
        Log.d("chenshichun","=========message_img=="+strTwoImage);
        map.put("message_img", strTwoImage);
        showDialog();
        XUtil.Post(URLConstant.TIJIAOYIJIAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====提交意见===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.item_publishTask_image:
                Integer index = (Integer) v.getTag(R.id.postion);
                if (index == imagePaths.size() - 1) {

                    if (imagePaths.size() >= 3) {
                        CusToast.showToast(getText(R.string.select_up_to_2_images));
                        return;
                    }

                    intent = new Intent(this, MultiImageSelectorActivity.class);
                    // 是否显示调用相机拍照
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                    // 最大图片选择数量
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 3 - imagePaths.size());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
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
                                showDialog();
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
                                            showDialog();
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
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mTaskImgAdapter.notifyDataSetChanged();
                    closeDialog();
                    break;
                case 1:
                    closeDialog();
                    break;
            }
        }
    };

}
