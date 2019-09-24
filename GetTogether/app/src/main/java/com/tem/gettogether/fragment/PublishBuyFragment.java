package com.tem.gettogether.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.Base2Fragment;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.bean.QiuGouXXBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.permissions.AppUtils;
import com.tem.gettogether.utils.permissions.FileUtils;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.permissions.PermissionsChecker;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_publish_buy)
public class PublishBuyFragment extends Base2Fragment implements View.OnClickListener, View.OnLongClickListener {

    private BaseActivity baseActivity;
    @ViewInject(R.id.iv_qglx1)
    private ImageView iv_qglx1;
    @ViewInject(R.id.iv_qglx2)
    private ImageView iv_qglx2;
  /*  @ViewInject(R.id.keywords_tv)
    private EditText keywords_tv;*/
    @ViewInject(R.id.et_cpName)
    private EditText et_cpName;
    @ViewInject(R.id.et_cpms)
    private EditText et_cpms;
    @ViewInject(R.id.ll_cpfl)
    private LinearLayout ll_cpfl;
    @ViewInject(R.id.ll_qglx)
    private LinearLayout ll_qglx;
    @ViewInject(R.id.ll_jhTime)
    private LinearLayout ll_jhTime;
    @ViewInject(R.id.ll_ckgj)
    private LinearLayout ll_ckgj;
    @ViewInject(R.id.et_qgNum)
    private EditText et_qgNum;
    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView recyclerView;
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<Integer> imageRes = new ArrayList<>();
    private ArrayList<String> compressPaths = new ArrayList<>();
    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code

    private PermissionsChecker checker;

    private String compressImageFilePath;
    private String[] strPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODEBBB = 100;
    private final int CROP_FROM_CAMERA = 333;

    private List<QiuGouXXBean.ResultBean.GoodsCateBean> goodsCateBeans = new ArrayList<>();
    private List<QiuGouXXBean.ResultBean.GoodsTimeBean> goodsTimeBeans = new ArrayList<>();
    private List<QiuGouXXBean.ResultBean.GoodsTypeBean> goodsTypeBeans = new ArrayList<>();
    private List<QiuGouXXBean.ResultBean.GoodsCountryBean> goodsCountryBeans = new ArrayList<>();
    private int type = 0;
    @ViewInject(R.id.tv_cpfl)
    private TextView tv_cpfl;
    @ViewInject(R.id.tv_qglx)
    private TextView tv_qglx;
    @ViewInject(R.id.tv_jhTime)
    private TextView tv_jhTime;
    @ViewInject(R.id.tv_ckgj)
    private TextView tv_ckgj;
    private int qgNum = 1;
    private String goods_cate = "";
    private String release_type = "";
    private String attach_time = "";
    private String country_name = "";
    private String country_id = "";
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.item_publishTask_image)
    private ImageView item_publishTask_image;
    private OnSwitchListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        checker = new PermissionsChecker(getActivity());
        initData();
        initView();
        upMainQGXXData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnSwitchListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        rl_close.setVisibility(View.GONE);
        tv_title.setText(R.string.fabuqiug);

        imagePaths.add(R.drawable.addtupian + "");
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void initView() {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(getActivity(), imagePaths, this, this, 0);
        recyclerView.setAdapter(mTaskImgAdapter);

    }

    @Event(value = {R.id.rl_close, R.id.iv_qglx1, R.id.iv_qglx2, R.id.ll_cpfl, R.id.ll_qglx, R.id.ll_jhTime, R.id.ll_ckgj, R.id.tv_fbqg}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_qglx1:
                qgNum = 1;
                iv_qglx1.setImageResource(R.drawable.radiobuttom_unselect);
                iv_qglx2.setImageResource(R.drawable.radiobuttom_select);
                et_qgNum.setEnabled(true);
                break;
            case R.id.iv_qglx2:
                qgNum = 2;
                iv_qglx2.setImageResource(R.drawable.radiobuttom_unselect);
                iv_qglx1.setImageResource(R.drawable.radiobuttom_select);
                et_qgNum.setEnabled(false);

                break;
            case R.id.ll_cpfl:
                type = 1;
                mPop = null;
                showPop(ll_cpfl);
                break;
            case R.id.ll_qglx:
                type = 2;
                mPop = null;
                showPop(ll_qglx);
                break;
            case R.id.ll_jhTime:
                type = 3;
                mPop = null;
                showPop(ll_jhTime);
                break;
            case R.id.ll_ckgj:
                type = 4;
                mPop = null;
                showPop(ll_ckgj);
                break;
            case R.id.tv_fbqg:
                    String name = et_cpName.getText().toString();
                    String cpms = et_cpms.getText().toString();
                    if (name.equals("")) {
                        CusToast.showToast(getText(R.string.txcpmc));
                        return;
                    }
                    if (cpms.equals("")) {
                        CusToast.showToast(getText(R.string.qtxcpms));
                        return;
                    }
                    /*if (keywords_tv.getText().toString().equals("")) {
                        CusToast.showToast("请填写产品关键词");
                        return;
                    }*/
                    if (country_name.equals("")) {
                        CusToast.showToast(getText(R.string.qxzckgj));
                        return;
                    }
                    if (release_type.equals("")) {
                        CusToast.showToast(getText(R.string.qxzqglx));
                        return;
                    }
                    if (attach_time.equals("")) {
                        CusToast.showToast(getText(R.string.qxzjhsj));
                        return;
                    }

                    if (qgNum == 0) {
                        CusToast.showToast(getText(R.string.qxzqgsl));
                        return;
                    }
                    if (qgNum == 1) {
                        if (et_qgNum.getText().toString().equals("")) {
                            CusToast.showToast(getText(R.string.qsrqgnum));
                            return;
                        }
                    }

                    Log.i("===图片22--", strImage + "");
                    upSCQGXXData();
                break;
        }
    }

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

                    showCameraPop(v);
                } else {
                    ArrayList<String> paths = new ArrayList<>();
                    paths.addAll(imagePaths);
                    paths.remove(paths.get(paths.size() - 1));

                    intent = new Intent(getActivity(), ShowImageDetail.class);
                    intent.putStringArrayListExtra("paths", paths);
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
                break;
            case R.id.delete_iv:
                final Integer index1 = (Integer) v.getTag(R.id.postion);
                System.out.println("index:===" + index1);
                /**
                 这里使用了 android.support.v7.app.AlertDialog.Builder
                 可以直接在头部写 import android.support.v7.app.AlertDialog
                 那么下面就可以写成 AlertDialog.Builder
                 */
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                builder.setMessage(getText(R.string.whether_to_confirm_the_deletion));
                builder.setNegativeButton(getText(R.string.quxiao), null);
                builder.setPositiveButton(getText(R.string.queding), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (imagePaths.size() > 0) {
                            imagePaths.remove(imagePaths.get(index1));
                        }
                        if (compressPaths.size() > 0) {
                            compressPaths.remove(compressPaths.get(index1));
                        }
                        if (cartImage.size() > 0) {
                            cartImage.remove(cartImage.get(index1));
                        }
                        mTaskImgAdapter.notifyDataSetChanged();
                    }
                });
                Log.d("chenshichun", "=======imagePaths====" + imagePaths);
                Log.d("chenshichun", "======imagePaths.size()=====" + imagePaths.size());
                if (imagePaths.size() - 1 != index1)
                    builder.show();

                break;

            default:
                break;
        }
    }

    private void upSCQGXXData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("goods_name", et_cpName.getText().toString());
        map.put("goods_desc", et_cpms.getText().toString());
//      map.put("goods_cate",goods_cate);
        map.put("release_type", release_type);
        map.put("attach_time", attach_time);
//        map.put("keywords", keywords_tv.getText().toString());
        map.put("country_id", country_id);
        if (qgNum == 1) {
            map.put("release", 1);
            map.put("goods_num", et_qgNum.getText().toString());
        } else if (qgNum == 2) {
            map.put("release", 2);
            map.put("goods_num", getText(R.string.anshangphuqpl));
        }
        if (cartImage.size() > 0) {
            for (int i = 0; i < cartImage.size(); i++) {
                if (i == 0) {
                    strImage += cartImage.get(i);
                } else {
                    strImage += "," + cartImage.get(i);
                }
            }
        }

        if (!strImage.equals("")) {
            map.put("goods_logo", strImage);
        }

        baseActivity.showDialog();
        XUtil.Post(URLConstant.MAIN_GQSC, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====求购发布===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        et_cpName.setText("");
//                        keywords_tv.setText("");
                        et_cpms.setText("");
                        tv_cpfl.setText("");
                        tv_qglx.setText("");
                        tv_jhTime.setText("");
                        et_qgNum.setText("");
                        tv_ckgj.setText("");
                        qgNum = 0;
                        iv_qglx1.setImageResource(R.drawable.radiobuttom_select);
                        iv_qglx2.setImageResource(R.drawable.radiobuttom_select);
                        imagePaths.clear();
                        cartImage.clear();
                        strImage = "";
                        imagePaths.add(R.drawable.addtupian + "");
                        mTaskImgAdapter.notifyDataSetChanged();
                        CusToast.showToast(msg);
                        listener.switchHome();
                    } else if (res.equals("-1")) {
                        CusToast.showToast(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }

    private void initPop() {
        if (mPop == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.main_qgxx_layout, null);
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
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            if (type == 1) {
                tv_xz_title.setText(getResources().getText(R.string.chanpinfenlei));
                initSetdialog();
            } else if (type == 2) {
                tv_xz_title.setText(getResources().getText(R.string.qiugouleixing2));
                initSetdialog2();
            } else if (type == 3) {
                tv_xz_title.setText(getResources().getText(R.string.jiaohuoshijian));
                initSetdialog3();
            } else if (type == 4) {
                tv_xz_title.setText(getResources().getText(R.string.qxzckgj));
                initSetdialog4();
            }
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
    }

    private void initSetdialog() {
        BaseRVAdapter baseRVAdapter = new BaseRVAdapter(getActivity(), goodsCateBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsCateBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goods_cate = goodsCateBeans.get(position).getCat_name();
                        tv_cpfl.setText(goodsCateBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);

    }

    private void initSetdialog2() {
        BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(getActivity(), goodsTypeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsTypeBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        release_type = goodsTypeBeans.get(position).getCat_name();
                        tv_qglx.setText(goodsTypeBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }

    private void initSetdialog3() {
        BaseRVAdapter baseRVAdapter3 = new BaseRVAdapter(getActivity(), goodsTimeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsTimeBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attach_time = goodsTimeBeans.get(position).getCat_name();
                        tv_jhTime.setText(goodsTimeBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter3.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter3);
    }

    private void initSetdialog4() {
        BaseRVAdapter baseRVAdapter4 = new BaseRVAdapter(getActivity(), goodsCountryBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsCountryBeans.get(position).getCountry_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        country_id = goodsCountryBeans.get(position).getCountry_id();
                        country_name = goodsCountryBeans.get(position).getCountry_name();
                        tv_ckgj.setText(goodsCountryBeans.get(position).getCountry_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter4.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter4);
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
        PermissionsActivity.startActivityForResult(getActivity(), REQUEST_CODEBBB, strPermissions);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("chenshichun", "=========" + requestCode);

        super.onActivityResult(requestCode, resultCode, data);
        //系统相机权限
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION) {
            if (resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            } else {
                Log.d("chenshichun", "=====系统相机拍照======");
                imageCapture();//系统相机拍照
            }
        }

        //裁剪的图片的回调
        if (requestCode == CROP_FROM_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                if (imagePaths.size() < 10) {
                    Uri cropUri = Uri.fromFile(mCropImageFile);
                    final String cropPath = "" + cropUri;
//                    imagePaths.add(imagePaths.size() - 1, cropPath.substring(cropPath.indexOf("/storage")));

//                    Map<String, Object> map = new HashMap<>();
//                    Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
//                    map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
//                    upMessageData(map, cropPath.substring(cropPath.indexOf("/storage")));
                    new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                ImageDataBean imageDataBean = null;
                                imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(cropPath.substring(cropPath.indexOf("/storage"))),new File(cropPath.substring(cropPath.indexOf("/storage"))),URLConstant.UPLOAD_PICTURE);
                                if(imageDataBean!=null){
                                    cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                                    imagePaths.add(imagePaths.size() - 1, cropPath.substring(cropPath.indexOf("/storage")));
                                    mHandle.sendEmptyMessage(0);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } else {
                    CusToast.showToast(getText(R.string.unable_to_add_more_images));
                }

            }
        }

        //拍照完成的回调
        if (requestCode == PHOTO_PICKED_FROM_CAMERA && resultCode == Activity.RESULT_OK) {//Activity.RESULT_OK可以确保拍照后有回调结果，屏蔽了返回键的回调
            startSystemCamera();
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //相册
                case FROM_ALBUM_CODE:
                    if (null != data) {
                        if (!Confirg.compressFile.exists()) {
                            Confirg.compressFile = new File(Confirg.FilesPath);
                            Confirg.compressFile.mkdirs();
                        }

                        List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        System.out.println("========：" + list.size());
                        if (imagePaths.size() < 10) {
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println("选中的路径：" + list.get(i));
                                final String pic_path = list.get(i);
                                String targetPath = compressImageFilePath + Confirg.df.
                                        format(new Date()) + ".jpg";
                                //调用压缩图片的方法，返回压缩后的图片path
                                final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                compressPaths.add(compressImage);
//                                imagePaths.add(imagePaths.size() - 1, list.get(i));
                                /*Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                Map<String, Object> map = new HashMap<>();
                                map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map, list.get(i));*/
                                new Thread(new Runnable(){
                                    @Override
                                    public void run() {
                                        try {
                                            ImageDataBean imageDataBean = null;
                                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(pic_path),new File(pic_path),URLConstant.UPLOAD_PICTURE);
                                            if(imageDataBean!=null){
                                                cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                                                imagePaths.add(imagePaths.size() - 1, pic_path);
                                                mHandle.sendEmptyMessage(0);
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
    }

    private String strImage = "";
    final List<String> cartImage = new ArrayList<>();

    private void upMessageData(Map<String, Object> map, final String path) {
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
                        imagePaths.add(imagePaths.size() - 1, path);
                        mTaskImgAdapter.notifyDataSetChanged();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onLongClick(View view) {
        final Integer index = (Integer) view.getTag();
        System.out.println("index:===" + index);
        /**
         这里使用了 android.support.v7.app.AlertDialog.Builder
         可以直接在头部写 import android.support.v7.app.AlertDialog
         那么下面就可以写成 AlertDialog.Builder
         */
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(getText(R.string.whether_to_confirm_the_deletion));
        builder.setNegativeButton(getText(R.string.quxiao), null);
        builder.setPositiveButton(getText(R.string.queding), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (imagePaths.size() > 0) {
                    imagePaths.remove(imagePaths.get(index));
                }
                if (compressPaths.size() > 0) {
                    compressPaths.remove(compressPaths.get(index));
                }
                if (cartImage.size() > 0) {
                    cartImage.remove(cartImage.get(index));
                }
                mTaskImgAdapter.notifyDataSetChanged();
            }
        });
        if (imagePaths.size() - 1 != index)
            builder.show();

        return true;
    }


    private void upMainQGXXData() {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.MAIN_QGFBXGXX, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====求购相关选项===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        QiuGouXXBean qiuGouXXBean = gson.fromJson(result, QiuGouXXBean.class);
                        goodsCateBeans = qiuGouXXBean.getResult().getGoods_cate();
                        goodsTimeBeans = qiuGouXXBean.getResult().getGoods_time();
                        goodsTypeBeans = qiuGouXXBean.getResult().getGoods_type();
                        goodsCountryBeans = qiuGouXXBean.getResult().getGoods_country();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }


    private PopupWindow mmPop;

    //显示弹窗
    private void showCameraPop(View v) {
        initmPop();
        if (mmPop.isShowing())
            return;
        //设置弹窗底部位置
        mmPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }

    //初始化弹窗
    private void initmPop() {
        if (mmPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
            mmPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mmPop.setFocusable(true);
            mmPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mmPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mmPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mmPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
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

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {//申请WRITE_EXTERNAL_STORAGE权限

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);

                } else {
                    imageCapture();//系统相机拍照
                }

//              PermissionsActivity.startActivityForResult(getActivity(), REQUEST_CODE_CAMERA_PERMISSION, PERMISSIONS_CAMERA);//打开系统相机需要相机权限
                mmPop.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统相册
                mmPop.dismiss();
                Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10 - imagePaths.size());

                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                startActivityForResult(intent, FROM_ALBUM_CODE);
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmPop.dismiss();
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
            pictureUri = FileProvider.getUriForFile(getContext(),
                    "com.seven.modifyavatarmaster.fileprovider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到pictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(intent, PHOTO_PICKED_FROM_CAMERA);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("chenshichun", "====requestCode=======" + requestCode);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageCapture();
                //权限获取成功
            } else {
                CusToast.showToast(getText(R.string.qxdkxjqx));
                //权限被拒绝
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 系统拍照后裁剪
     */
    public void startSystemCamera() {
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
        Uri pictureUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            pictureUri = FileProvider.getUriForFile(getContext(),
                    "com.seven.modifyavatarmaster.fileprovider", pictureFile);
        } else {
            pictureUri = Uri.fromFile(pictureFile);
        }
        startPhotoZoom(pictureUri);
    }

    private File mCropImageFile;

    public void startPhotoZoom(Uri uri) {
        try {
            if (AppUtils.existSDCard()) {
                mCropImageFile = FileUtils.createTmpFile(getContext());
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

    public interface OnSwitchListener {
        void switchHome();
    }

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    mTaskImgAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    break;
            }
        }
    };

}
