package com.tem.gettogether.fragment;

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
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.MainActivity;

import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.bean.PersonagerMessageBean;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.ListUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_personage_rz)
public class PersonageRZActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_dpzzh)
    private EditText et_dpzzh;
    @ViewInject(R.id.et_dl_pass)
    private EditText et_dl_pass;
    @ViewInject(R.id.et_dpfzrName)
    private EditText et_dpfzrName;
    @ViewInject(R.id.et_fzrPhone)
    private EditText et_fzrPhone;
    @ViewInject(R.id.et_fzrQQ)
    private EditText et_fzrQQ;
    @ViewInject(R.id.et_dpAddress)
    private EditText et_dpAddress;
    @ViewInject(R.id.et_fzrWXH)
    private EditText et_fzrWXH;
    @ViewInject(R.id.et_fzrWhat)
    private EditText et_fzrWhat;
    @ViewInject(R.id.et_fzrFace)
    private EditText et_fzrFace;
    @ViewInject(R.id.et_dzyx)
    private EditText et_dzyx;
    @ViewInject(R.id.et_card_num)
    private EditText et_card_num;
    @ViewInject(R.id.et_yhkhh)
    private EditText et_yhkhh;
    @ViewInject(R.id.et_yhzh)
    private EditText et_yhzh;
    @ViewInject(R.id.et_khyhzh)
    private EditText et_khyhzh;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.tv_khhSZD)
    private TextView tv_khhSZD;
    @ViewInject(R.id.ll_dpzydl)
    private LinearLayout ll_dpzydl;
    @ViewInject(R.id.ll_gfl)
    private LinearLayout ll_gfl;
    @ViewInject(R.id.ll_dpxz)
    private LinearLayout ll_dpxz;
    @ViewInject(R.id.ll_khhszd)
    private LinearLayout ll_khhszd;
    @ViewInject(R.id.tv_dpzy)
    private TextView tv_dpzy;
    @ViewInject(R.id.tv_gfl)
    private TextView tv_gfl;
    @ViewInject(R.id.tv_dpxz)
    private TextView tv_dpxz;
    private int imageType=0;
    @ViewInject(R.id.iv_image_1)
    private ImageView iv_image_1;
    @ViewInject(R.id.iv_image_2)
    private ImageView iv_image_2;
    @ViewInject(R.id.rl_card_image1)
    private RelativeLayout rl_card_image1;
    @ViewInject(R.id.rl_card_image2)
    private RelativeLayout rl_card_image2;
    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans=new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans=new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans=new ArrayList<>();
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
    private String cardImage_z,cardImage_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initData();
        initView();
        upRequest();
    }

    @Override
    protected void initData() {
        tv_title.setText(getText(R.string.individual_settlement));
        upGetAddressData("0");

    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.ll_dpzydl,R.id.tv_upStep,R.id.tv_tjsh,R.id.rl_card_image1,R.id.rl_card_image2,R.id.ll_gfl,R.id.ll_dpxz,R.id.ll_khhszd}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_upStep:
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_tjsh://提交审核
                String dpname=et_name.getText().toString();
                String cardNum=et_card_num.getText().toString();
                String dpzh =et_dpzzh.getText().toString();
                String dpdlPass=et_dl_pass.getText().toString();
                String dpfzrMZ=et_dpfzrName.getText().toString();
                String dpfzrPhone=et_fzrPhone.getText().toString();
                String dpfzrQQ=et_fzrQQ.getText().toString();
                String fzrWX=et_fzrWXH.getText().toString();
                String fzrApp=et_fzrWhat.getText().toString();
                String fzrFace=et_fzrFace.getText().toString();
                String fzrEmail=et_dzyx.getText().toString();
                String dpAddress=et_dpAddress.getText().toString();
                String yhkhm=et_yhkhh.getText().toString();
                String yhzh=et_yhzh.getText().toString();
                String khyhName=et_khyhzh.getText().toString();
                Map<String,Object> map=new HashMap<>();
                map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                map.put("store_name",dpname);
                map.put("sc_id",DPDL);
                map.put("pavilion_id",GFL);
                map.put("store_type",DPXZ);
                map.put("seller_name",dpzh);
                map.put("password",dpdlPass);
                map.put("store_person_name",dpfzrMZ);
                map.put("store_person_mobile",dpfzrPhone);
                map.put("store_person_qq",dpfzrQQ);
                map.put("store_person_wx",fzrWX);
                map.put("store_person_wt",fzrApp);
                map.put("store_person_fc",fzrFace);
                map.put("store_person_email",fzrEmail);
                map.put("store_address",dpAddress);
                map.put("store_person_identity",cardNum);
                map.put("legal_identity_cert",cardImage_z);
                map.put("store_person_cert",cardImage_f);
                map.put("bank_account_name",yhkhm);
                map.put("bank_account_number",yhzh);
                map.put("bank_branch_name",khyhName);
                map.put("bank_province",Sheng);
                map.put("bank_city",city);
                map.put("bank_district",qu);
                upTJSHData(map);
                break;
            case R.id.rl_card_image1:
                imageType=1;
                showPopimage(rl_card_image1);
                break;
            case R.id.rl_card_image2:
                imageType=2;
                showPopimage(rl_card_image2);
                break;
            case R.id.ll_dpzydl://店铺主营大类
                type=1;
                mPop=null;
                showPop(ll_dpzydl);
                break;
            case R.id.ll_gfl://管分类
                type=2;
                mPop=null;
                showPop(ll_gfl);
                 return;
            case R.id.ll_dpxz://店铺性质
                type=3;
                mPop=null;
                showPop(ll_dpxz);
                break;
            case R.id.ll_khhszd://开户银行支行所在地
                showPopAddress(ll_khhszd);
                break;
        }
    }
    private void upTJSHData(Map<String, Object> map){
        showDialog();
        XUtil.Post(URLConstant.GERENRUZHU_RENZHENG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商铺个人入驻提交审核===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        startActivity(new Intent(PersonageRZActivity.this, MainActivity.class)
                                .putExtra("tab", "4"));
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
    private void upRequest(){
        showDialog();
        XUtil.Post(URLConstant.GRXINXLIEB,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===个人信息选择列表--",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        PersonagerMessageBean personagerMessageBean=gson.fromJson(result,PersonagerMessageBean.class);
                        goodsCategoryBeans=personagerMessageBean.getResult().getGoods_category();
                        pavilionBeans=personagerMessageBean.getResult().getPavilion();
                        storeClassBeans=personagerMessageBean.getResult().getStore_class();
                    }else {
                        CusToast.showToast(msg);
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
                closeDialog();
            }
        });
    }
    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;
    private int type;
    private String DPDL="";
    private String GFL="";
    private String DPXZ="";
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
    private void initPop() {
        if (mPop == null) {
            view = LayoutInflater.from(this).inflate(R.layout.main_qgxx_layout, null);
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
                    WindowManager.LayoutParams lp =getWindow().getAttributes();
                    lp.alpha = 1f;
                   getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_xz_title=view.findViewById(R.id.tv_xz_title);
            if(type==1){
                tv_xz_title.setText(getText(R.string.choose_main_store_category));
                initSetdialog();
            }else if(type==2){
                tv_xz_title.setText(getText(R.string.select_museum_classification));
                initSetdialog2();
            }else if(type==3){
                tv_xz_title.setText(getText(R.string.please_select_nature_of_the_store));
                initSetdialog3();
            }
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
    }
    private PopupWindow mPopImage;

    //显示弹窗
    private void showPopimage(View v) {
        initPopImage();
        if (mPopImage.isShowing())
            return;
        //设置弹窗底部位置
        mPopImage.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }
    //初始化弹窗
    private void initPopImage() {
        if (mPopImage == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
            mPopImage = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopImage.setFocusable(true);
            mPopImage.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopImage.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopImage.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopImage.setOnDismissListener(new PopupWindow.OnDismissListener() {
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


                mPopImage.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到系统相册
                PermissionsActivity.startActivityForResult((Activity) mContext, REQUEST_CODE_PHOTO_PERMISSION, PERMISSIONS);
                mPopImage.dismiss();
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
    public void setPicToView() {
        if (mCropImageFile != null) {
            String path = mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
            if(imageType==1){
                iv_image_1.setImageBitmap(bitmap);

            } else if(imageType==2){
                iv_image_2.setImageBitmap(bitmap);

            }
            Map<String,Object> map=new HashMap<>();
            map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
            upInputImageData(map);
//            GlideLoadUtils.getInstance().glideAvatarLoad(this, path, iv_head, R.drawable.img12x);

        }
    }
    private void upInputImageData(Map<String, Object> map) {
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
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean=gson.fromJson(result,ImageDataBean.class);
                        if(imageType==1){
                            cardImage_z=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据1---",imageDataBean.getResult().getImage_show().get(0));
                        }else if(imageType==2){
                            cardImage_f=imageDataBean.getResult().getImage_show().get(0);
//                            Log.i("====上传图片数据22---",imageDataBean.getResult().getImage_show().get(0));
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
    private void initSetdialog(){
        BaseRVAdapter baseRVAdapter= new BaseRVAdapter(this,goodsCategoryBeans) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.fabuqiug_layout;
                }

                @Override
                public void onBind(BaseViewHolder holder, final int position) {
                    holder.getTextView(R.id.tv_item).setText(goodsCategoryBeans.get(position).getName());
                    holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DPDL=goodsCategoryBeans.get(position).getId();
                            tv_dpzy.setText(goodsCategoryBeans.get(position).getName());
                            mPop.dismiss();
                        }
                    });
                }

            };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }
    private void initSetdialog2(){
        BaseRVAdapter  baseRVAdapter2=new BaseRVAdapter(this,pavilionBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(pavilionBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GFL=pavilionBeans.get(position).getId();
                        tv_gfl.setText(pavilionBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }
    private void initSetdialog3(){
        BaseRVAdapter baseRVAdapter3=  new BaseRVAdapter(this,storeClassBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(storeClassBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DPXZ=storeClassBeans.get(position).getId();
                        tv_dpxz.setText(storeClassBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter3.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter3);
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
    private PopupWindow mPopAddress;

    //显示弹窗
    private void showPopAddress(View v) {
        initPopAddress();
        if (mPopAddress.isShowing())
            return;
        //设置弹窗底部位置
        mPopAddress.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private RecyclerView recyclerView;
    private View viewAddress;
    private TextView tv_sheng, tv_city, tv_qu, tv_jiedao;
    private LinearLayout ll_sheng, ll_city, ll_qu, ll_jiedao;
    private View view_line, view_line2, view_line3, view_line4;
    private List<AddressDataBean.ResultBean> addresssresultBeans = new ArrayList<>();
    private String addressType = "1";
    private String Sheng, city, qu, jiedao;
    private boolean isOne = true;
    //初始化弹窗
    private void initPopAddress() {
        if (mPopAddress == null) {
            viewAddress = LayoutInflater.from(this).inflate(R.layout.pop_address_xzlayout, null);
            mPopAddress = new PopupWindow(viewAddress, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopAddress.setFocusable(true);
            mPopAddress.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopAddress.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopAddress.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopAddress.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = viewAddress.findViewById(R.id.iv_dialog_close);
            tv_sheng = viewAddress.findViewById(R.id.tv_sheng);
            tv_city = viewAddress.findViewById(R.id.tv_city);
            tv_qu = viewAddress.findViewById(R.id.tv_qu);
            tv_jiedao = viewAddress.findViewById(R.id.tv_jiedao);
            recyclerView = viewAddress.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            view_line = viewAddress.findViewById(R.id.view_line);
            view_line2 = viewAddress.findViewById(R.id.view_line2);
            view_line3 = viewAddress.findViewById(R.id.view_line3);
            view_line4 = viewAddress.findViewById(R.id.view_line4);
            ll_sheng = viewAddress.findViewById(R.id.ll_sheng);
            ll_city = viewAddress.findViewById(R.id.ll_city);
            ll_qu = viewAddress.findViewById(R.id.ll_qu);
            ll_jiedao = viewAddress.findViewById(R.id.ll_jiedao);

            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopAddress.dismiss();
                }
            });
            if (isOne = true) {
                Log.i("======选择内容888--", "==");
                addressSetData();
            }
            ll_sheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "1";
                    isOne = false;
                    upGetAddressData("0");
                    tv_sheng.setText(getText(R.string.please_choose));
                    view_line.setVisibility(View.VISIBLE);
                    tv_sheng.setTextColor(getResources().getColor(R.color.my_red));
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.GONE);
                    ll_qu.setVisibility(View.GONE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });
            ll_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "2";

                    if (Sheng != null) {
                        upGetAddressData(Sheng);
                    }
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.VISIBLE);
                    tv_city.setText(getText(R.string.please_choose));
                    view_line2.setVisibility(View.VISIBLE);

                    tv_city.setTextColor(getResources().getColor(R.color.my_red));

                    ll_qu.setVisibility(View.GONE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });
            ll_qu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "3";
                    if (city != null) {
                        upGetAddressData(city);
                    }
                    tv_qu.setText(getText(R.string.please_choose));
                    view_line3.setVisibility(View.VISIBLE);
                    tv_qu.setTextColor(getResources().getColor(R.color.my_red));
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.VISIBLE);
                    ll_qu.setVisibility(View.VISIBLE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });

        }
    }

    private void addressSetData() {

        recyclerView.setAdapter(new BaseRVAdapter(this, addresssresultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.address_item_layout;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(addresssresultBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isOne = false;
                        upGetAddressData(addresssresultBeans.get(position).getId());
                        if (addressType.equals("1")) {
                            ll_city.setVisibility(View.VISIBLE);
                            addressType = "2";
                            Sheng = addresssresultBeans.get(position).getId();
                            tv_sheng.setTextColor(getResources().getColor(R.color.text3));
                            view_line.setVisibility(View.INVISIBLE);
                            tv_sheng.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());
                        } else if (addressType.equals("2")) {
                            ll_qu.setVisibility(View.VISIBLE);
                            addressType = "3";
                            city = addresssresultBeans.get(position).getId();
                            tv_city.setTextColor(getResources().getColor(R.color.text3));
                            view_line2.setVisibility(View.INVISIBLE);
                            tv_city.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("3")) {
                            ll_jiedao.setVisibility(View.VISIBLE);
                            addressType = "4";
                            qu = addresssresultBeans.get(position).getId();
                            tv_qu.setTextColor(getResources().getColor(R.color.text3));
                            view_line3.setVisibility(View.INVISIBLE);
                            tv_qu.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("4")) {
                            tv_jiedao.setTextColor(getResources().getColor(R.color.text3));
                            view_line4.setVisibility(View.INVISIBLE);
                            tv_jiedao.setText(addresssresultBeans.get(position).getName());
                            jiedao = addresssresultBeans.get(position).getId();
                            tv_khhSZD.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            mPopAddress.dismiss();

                        }
//                        CusToast.showToast(addresssresultBeans.get(position).getName());
                        notifyDataSetChanged();
                    }
                });

            }


        });
    }
    public void clearList(List<AddressDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    private void upGetAddressData(String parent_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", parent_id);

        showDialog();
        XUtil.Post(URLConstant.GET_ADDRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取地区===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AddressDataBean addressDataBean = gson.fromJson(result, AddressDataBean.class);
                        clearList(addresssresultBeans);
                        addresssresultBeans = addressDataBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                if (isOne == false) {
                    Log.i("======选择内容000--", "==");
                    addressSetData();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }
}
