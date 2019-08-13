package com.tem.gettogether.activity.my;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddBDFLBean;
import com.tem.gettogether.bean.AddShopFLBean;
import com.tem.gettogether.bean.AddShopGFLBean;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.ImageDataBean;
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
import com.tem.gettogether.view.CheckBoxSample;

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

@ContentView(R.layout.activity_new_add_shopping)
public class NewAddShoppingActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_cpName)
    private EditText et_cpName;
    @ViewInject(R.id.et_ShoppingHH)
    private EditText et_ShoppingHH;
    @ViewInject(R.id.et_QPNum)
    private EditText et_QPNum;
    @ViewInject(R.id.rt_tuwen)
    private EditText rt_tuwen;
    @ViewInject(R.id.tv_XZguan)
    private TextView tv_XZguan;
    @ViewInject(R.id.tv_XZshopFL)
    private TextView tv_XZshopFL;
    @ViewInject(R.id.tv_XZBDFL)
    private TextView tv_XZBDFL;
    @ViewInject(R.id.et_ShopingSJ)
    private TextView et_ShopingSJ;
    @ViewInject(R.id.checkbox1)
    private CheckBoxSample checkbox1;
    @ViewInject(R.id.checkbox2)
    private CheckBoxSample checkbox2;
    @ViewInject(R.id.checkbox3)
    private CheckBoxSample checkbox3;
    @ViewInject(R.id.checkbox4)
    private CheckBoxSample checkbox4;
    @ViewInject(R.id.tv_is_pic)
    private TextView tv_is_pic;
    @ViewInject(R.id.tv_ShoppingGG)
    private EditText tv_ShoppingGG;
    @ViewInject(R.id.et_KuCNum)
    private EditText et_KuCNum;
    @ViewInject(R.id.et_GuanJC)
    private EditText et_GuanJC;
    @ViewInject(R.id.et_ShoppingJJ)
    private EditText et_ShoppingJJ;

    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView publish_recy;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.tv_fbShopping)
    private TextView tv_fbShopping;
    @ViewInject(R.id.ll_shopName)
    private LinearLayout ll_shopName;
    @ViewInject(R.id.ll_shop_HH)
    private LinearLayout ll_shop_HH;
    @ViewInject(R.id.ll_shop_Num)
    private LinearLayout ll_shop_Num;
    @ViewInject(R.id.ll_xuanZG)
    private LinearLayout ll_xuanZG;
    @ViewInject(R.id.ll_shop_FL)
    private LinearLayout ll_shop_FL;
    @ViewInject(R.id.ll_bd_FL)
    private LinearLayout ll_bd_FL;
    @ViewInject(R.id.ll_shop_SJ)
    private LinearLayout ll_shop_SJ;
    @ViewInject(R.id.ll_shop_phone)
    private LinearLayout ll_shop_phone;

    @ViewInject(R.id.ll_shop_GG)
    private LinearLayout ll_shop_GG;
    @ViewInject(R.id.ll_KC_Num)
    private LinearLayout ll_KC_Num;
    @ViewInject(R.id.ll_GJC)
    private LinearLayout ll_GJC;
    @ViewInject(R.id.ll_shop_JJ)
    private LinearLayout ll_shop_JJ;
    @ViewInject(R.id.ll_XQ_ms)
    private LinearLayout ll_XQ_ms;
    private MyPublicTaskRecycleAdapter mTaskImgAdapter, mTaskImgAdapter1;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> imageTwoPaths = new ArrayList<>();

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
    private int type = 0;
    private List<AddShopGFLBean.ResultBean> resultBeansGFL = new ArrayList<>();
    private List<AddShopFLBean.ResultBean> resultBeansFL = new ArrayList<>();
    private List<AddBDFLBean.ResultBean> BDresultBeans = new ArrayList<>();
    private List<CategoriesBean.ResultBean> mCategoriesBeans = new ArrayList<>();

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
    private int isshanpinImage = 0;
    private String goods_content;
    private int recycleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        checker = new PermissionsChecker(this);

        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("新增产品");
        upBDFLData();
        upCategories();
        imagePaths.clear();
        imagePaths.add(R.drawable.addtupian + "");
        imageTwoPaths.clear();
        imageTwoPaths.add(R.drawable.addtupian + "");
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress2/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private int isxunjia = 0;
    private int isbaoyou = 0;

    @Override
    protected void initView() {
        checkbox2.toggle();
        checkbox4.toggle();
        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isxunjia = 1;
                checkbox1.toggle();
                checkbox2.setChecked(false);
                et_ShopingSJ.setText("面议");
                et_ShopingSJ.setClickable(false);
            }
        });
        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isxunjia = 0;
                checkbox1.setChecked(false);
                checkbox2.toggle();
                et_ShopingSJ.setText("");
                et_ShopingSJ.setClickable(true);
            }
        });
        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isbaoyou = 1;
                checkbox4.setChecked(false);
                checkbox3.toggle();
            }
        });
        checkbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isbaoyou = 0;
                checkbox3.setChecked(false);
                checkbox4.toggle();
            }
        });
        publish_recy.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(this, imageRes, imagePaths, this, this, 0);
        publish_recy.setAdapter(mTaskImgAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter1 = new MyPublicTaskRecycleAdapter(this, imageRes, imageTwoPaths, this, this, 1);
        recyclerView.setAdapter(mTaskImgAdapter1);
    }

    @Event(value = {R.id.rl_close, R.id.tv_fbShopping, R.id.ll_XQ_ms, R.id.ll_xuanZG, R.id.ll_shop_phone, R.id.ll_shop_FL, R.id.ll_bd_FL, R.id.ll_shop_GG}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_shop_phone:
                showPopImage(ll_shop_phone);
                break;
            case R.id.ll_shop_FL://商品分类
                Log.d("chenshichun", "======商品分类=====");
                type = 1;
                mPop = null;
                showPop(ll_shop_FL);
                break;
            /*case R.id.ll_XQ_ms:
                startActivityForResult(new Intent(NewAddShoppingActivity.this, TuWenXQActivity.class)
                        .putStringArrayListExtra("listImage", listImage), 6666);

                break;*/
            case R.id.ll_bd_FL://本店分类
                type = 3;
                mPop = null;
                showPop(ll_bd_FL);
                break;
            case R.id.ll_shop_GG://商品规格
//                upShoppingGGData();
                break;
            case R.id.tv_fbShopping:

                Map<String, Object> map = new HashMap<>();
                if (BaseApplication.getInstance().userBean == null) return;
                map.put("token", BaseApplication.getInstance().userBean.getToken());
                map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID ,""));

                if (et_cpName.getText().toString().equals("")) {
                    CusToast.showToast("请填写商品名称");
                    return;
                }
                if (et_QPNum.getText().toString().equals("")) {
                    CusToast.showToast("请填写起批个数");
                    return;
                }

                if (et_ShopingSJ.getText().toString().equals("")) {
                    CusToast.showToast("请填写商品价格");
                    return;
                }

                map.put("goods_name", et_cpName.getText().toString());
                Log.d("chenshichun", "======et_cpName=====  " + et_cpName.getText().toString());

                map.put("goods_sn", et_ShoppingHH.getText().toString());
                Log.d("chenshichun", "======et_ShoppingHH=====  " + et_ShoppingHH.getText().toString());

                map.put("batch_number", et_QPNum.getText().toString());
                Log.d("chenshichun", "======et_QPNum=====  " + et_QPNum.getText().toString());

//                map.put("pavilion_id", addGID);
//                map.put("pavilion_cate_id", addGFLID);
//                map.put("cat_id1", addFL1ID);
                map.put("cat_id2", addGID);
                Log.d("chenshichun", "======addGID=====  " + addGID);

                map.put("cat_id3", addGFLID);
                Log.d("chenshichun", "======addGFLID=====  " + addGFLID);

//                map.put("store_cat_id1", BDFL1ID);
//                map.put("store_cat_id2", BDFL2ID);
//                map.put("store_cat_id3", BDL3ID);
                map.put("shop_price", et_ShopingSJ.getText().toString());
                Log.d("chenshichun", "======et_ShopingSJ=====  " + et_ShopingSJ);


                map.put("is_enquiry", isxunjia);
                Log.d("chenshichun", "======isxunjia=====  " + isxunjia);

//                map.put("is_free_shipping", isbaoyou);
                map.put("store_count", et_KuCNum.getText().toString());
                Log.d("chenshichun", "======et_KuCNum=====  " + et_KuCNum.getText().toString());

                map.put("keywords", et_GuanJC.getText().toString());
                Log.d("chenshichun", "======et_GuanJC=====  " + et_GuanJC.getText().toString());

                map.put("goods_remark", et_ShoppingJJ.getText().toString());
                Log.d("chenshichun", "======et_ShoppingJJ=====  " + et_ShoppingJJ.getText().toString());

                if (goods_content != null && !goods_content.equals("")) {
                    Log.d("chenshichun", "======goods_content=====  " + goods_content);
                    map.put("goods_content", rt_tuwen.getText().toString());
                }
                /*if (resultBeansGFL.size() > 0) {
                    map.put("sku_str", resultBeansGFL.get(0).getSku_str());
                }*/
                map.put("sku_str",tv_ShoppingGG.getText().toString());// 规格
                if (cartImage.size() > 0) {
                    for (int i = 0; i < cartImage.size(); i++) {
                        strImage += cartImage.get(i) + ",";
                    }
                }

                if (!strImage.equals("")) {
                    Log.d("chenshichun", "======strImage=====  " + strImage);
                    map.put("goods_images", strImage);
                }

                if (cartTwoImage.size() > 0) {
                    for (int i = 0; i < cartTwoImage.size(); i++) {
                        strTwoImage += cartTwoImage.get(i) + ",";
                    }
                }
                if (!strTwoImage.equals("")) {
                    Log.d("chenshichun", "======strTwoImage=====  " + strTwoImage);
                    map.put("original_img", strTwoImage);
                }
                map.put("is_on_sale", "1");
                upTJSHData(map);
                break;
        }
    }

    private void upTJSHData(Map<String, Object> map) {
        Log.d("chenshichun","=====请求======");
        XUtil.Post(URLConstant.ADDXINCHANPIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商品上传===", result.toString());
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

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }

    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg, recyclerView_qg2, recyclerView_qg3;
    private TextView tv_xz_title;
    private String ADDGName;
    private String ADDGFLName;
    private String addGID = "";
    private String addGFLID = "";
    private int itmeXZ = 0;

    private String addFL1ID = "";
    private String addFL2ID = "";
    private String addFL3ID = "";

    private String BDFL1ID = "";
    private String BDFL2ID = "";
    private String BDL3ID = "";

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
            view = LayoutInflater.from(this).inflate(R.layout.main_addshpping_layout, null);
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
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg2 = view.findViewById(R.id.recyclerView_qg2);
            recyclerView_qg2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            if (type == 1) {
                Log.d("chenshichun", "======请选择商品分类=====");
                tv_xz_title.setText("请选择商品分类");
                recyclerView_qg3 = view.findViewById(R.id.recyclerView_qg3);
                recyclerView_qg3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerView_qg3.setVisibility(View.GONE);
                initSetdialog();
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
        BaseRVAdapter baseRVAdapter = new BaseRVAdapter(this, mCategoriesBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.add_shpping_layout;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(position).getName());
                if (itmeXZ == position) {
                    holder.getTextView(R.id.tv_item).setTextColor(getResources().getColor(R.color.my_red));
                } else {
                    holder.getTextView(R.id.tv_item).setTextColor(getResources().getColor(R.color.text3));
                }
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itmeXZ = position;
                        ADDGName = mCategoriesBeans.get(position).getName();
                        addGID = mCategoriesBeans.get(position).getId();
                        notifyDataSetChanged();
                        BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(NewAddShoppingActivity.this, mCategoriesBeans.get(position).getSon()) {
                            @Override
                            public int getLayoutId(int viewType) {
                                return R.layout.add_shpping_layout;
                            }

                            @Override
                            public void onBind(BaseViewHolder holder2, final int position2) {
                                holder2.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(position).getSon().get(position2).getName());
                                holder2.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (ADDGName == null) {
                                            ADDGName = mCategoriesBeans.get(position).getName();
                                            addGID = mCategoriesBeans.get(position).getId();
                                        }
                                        addGFLID = mCategoriesBeans.get(position).getSon().get(position2).getId();
                                        tv_XZshopFL.setText(ADDGName + " " + mCategoriesBeans.get(position).getSon().get(position2).getName());
                                        mPop.dismiss();
                                    }
                                });
                            }

                        };
                        baseRVAdapter2.notifyDataSetChanged();
                        recyclerView_qg2.setAdapter(baseRVAdapter2);
                    }
                });
            }

        };

        if (mCategoriesBeans.size() > 0) {
            BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(NewAddShoppingActivity.this, mCategoriesBeans.get(0).getSon()) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.add_shpping_layout;
                }

                @Override
                public void onBind(BaseViewHolder holder2, final int position2) {
                    holder2.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(0).getSon().get(position2).getName());
                }

            };
            baseRVAdapter2.notifyDataSetChanged();
            recyclerView_qg2.setAdapter(baseRVAdapter2);
        }

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }

    private void upCategories() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) {
            CusToast.showToast("登录失效，请重新登录");
            return;
        }
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID ,""));

        showDialog();
        XUtil.Post(URLConstant.CATEGORIES, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===本店分类--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CategoriesBean mCategoriesBean = gson.fromJson(result, CategoriesBean.class);
                        mCategoriesBeans = mCategoriesBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }
        });
    }

    //本店分类
    private void upBDFLData() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) {
            CusToast.showToast("登录失效，请重新登录");
            return;
        }
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        showDialog();
        XUtil.Post(URLConstant.BENDIANFENLEI, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===本店分类--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AddBDFLBean addBDFLBean = gson.fromJson(result, AddBDFLBean.class);
                        BDresultBeans = addBDFLBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }
        });
    }

    //商品规格选项
    private void upShoppingGGData() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("cat_id3", addFL3ID);
        if (resultBeansGFL.size() > 0) {
            map.put("sku_str", resultBeansGFL.get(0).getSku_str());
        }

        showDialog();
        XUtil.Post(URLConstant.SHANGPING_GUIGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===商品规格选项--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject2 = new JSONObject(result2);
                        String url = jsonObject2.optString("url");
                        startActivity(new Intent(NewAddShoppingActivity.this, ShopGGH5Activity.class)
                                .putExtra("h5url", url));
                    } else {
                        CusToast.showToast(msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.item_publishTask_image:
                Integer index = (Integer) v.getTag(R.id.postion);
                Integer type = (Integer) v.getTag(R.id.type);
                recycleType = type;
                if (index == imagePaths.size() - 1 || index == imageTwoPaths.size() - 1) {
                    isshanpinImage = 0;
                    if (imagePaths.size() >= 10) {
                        CusToast.showToast("最多选择9张图片");
                        return;
                    }
                    if (imageTwoPaths.size() >= 10) {
                        CusToast.showToast("最多选择9张图片");
                        return;
                    }
                    intent = new Intent(this, MultiImageSelectorActivity.class);
                    // 是否显示调用相机拍照
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                    // 最大图片选择数量
                    if (recycleType == 0) {
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10 - imagePaths.size());
                    } else {
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10 - imageTwoPaths.size());
                    }

                    // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                    startActivityForResult(intent, FROM_ALBUM_CODE);
                } else {
                    ArrayList<String> paths = new ArrayList<>();
                    if (recycleType == 0) {
                        paths.addAll(imagePaths);
                    } else {
                        paths.addAll(imageTwoPaths);
                    }
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
                builder.setMessage("是否确认删除？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (recycleType == 0) {
                            if (imagePaths.size() > 0) {
                                imagePaths.remove(imagePaths.get(index1));
                            }
                            if (cartImage.size() > 0) {
                                cartImage.remove(cartImage.get(index1));
                            }
                        } else {
                            if (imageTwoPaths.size() > 0) {
                                imageTwoPaths.remove(imageTwoPaths.get(index1));
                            }
                            if (cartTwoImage.size() > 0) {
                                cartTwoImage.remove(cartTwoImage.get(index1));
                            }
                        }
                        /*if (compressPaths.size() > 0) {
                            compressPaths.remove(compressPaths.get(index1));
                        }*/


                        mTaskImgAdapter.notifyDataSetChanged();
                        mTaskImgAdapter1.notifyDataSetChanged();
                    }
                });
                if (imagePaths.size() - 1 != index1 || imageTwoPaths.size() - 1 != index1) {
                    builder.show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        return true;
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

    private PopupWindow mPopImage;

    //显示弹窗
    private void showPopImage(View v) {
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
        tv_iteam1.setText("拍照");
        photo.setText("相册");
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
                isshanpinImage = 1;
                Intent intent = new Intent(NewAddShoppingActivity.this, MultiImageSelectorActivity.class);
                // 是否显示调用相机拍照
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                // 最大图片选择数量
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent, FROM_ALBUM_CODE);

                mPopImage.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopImage.dismiss();
            }
        });
    }

    public void setPicToView() {
        if (mCropImageFile != null) {
            String path = mCropImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropImageFile.toString());
            isshanpinImage = 1;
            Map<String, Object> map = new HashMap<>();
            map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
            upMessageData(map, path);
        }
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

                        List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        if (recycleType == 0) {
                            if (imagePaths.size() < 10) {
                                for (int i = 0; i < list.size(); i++) {
                                    System.out.println("选中的路径：" + list.get(i));
                                    String pic_path = list.get(i);
                                    String targetPath = compressImageFilePath + Confirg.df.
                                            format(new Date()) + ".jpg";
                                    //调用压缩图片的方法，返回压缩后的图片path
                                    final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                    if (isshanpinImage == 0) {
                                        compressPaths.add(compressImage);
                                    }
                                    Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
                                    upMessageData(map, list.get(i));

                                }

                            } else {
                                CusToast.showToast("无法添加更多图片！");
                            }
                        } else {
                            if (imageTwoPaths.size() < 10) {
                                for (int i = 0; i < list.size(); i++) {
                                    System.out.println("选中的路径：" + list.get(i));
                                    String pic_path = list.get(i);
                                    String targetPath = compressImageFilePath + Confirg.df.
                                            format(new Date()) + ".jpg";
                                    //调用压缩图片的方法，返回压缩后的图片path
                                    final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                    if (isshanpinImage == 0) {
                                        compressPaths.add(compressImage);
                                    }
                                    Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("image_base_64_arr", "data:image/jpeg;base64," + Base64BitmapUtil.bitmapToBase64(bitmap));
                                    upMessageData(map, list.get(i));

                                }

                            } else {
                                CusToast.showToast("无法添加更多图片！");
                            }
                        }
                        Log.d("chenshichun", "======imagePaths=====  " + imagePaths);
                        Log.d("chenshichun", "===imageTwoPaths========  " + imageTwoPaths);

                        mTaskImgAdapter.notifyDataSetChanged();
                        mTaskImgAdapter1.notifyDataSetChanged();
                    }
                    break;
            }

        }
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
        if (requestCode == 6666) {
            if (resultCode == RESULT_OK) {
                goods_content = data.getExtras().getString("goods_content");
                listImage = data.getStringArrayListExtra("listImage");
                rt_tuwen.setText("已填写");
                Log.i("===返回图片22--", goods_content);
            }


        }
    }

    private ArrayList<String> listImage = new ArrayList<>();

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
                    "com.seven.modifyavatarmaster.fileprovider", pictureFile);
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
                    "com.seven.modifyavatarmaster.fileprovider", pictureFile);
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

    private String strImage = "";
    private String strTwoImage = "";
    final List<String> cartImage = new ArrayList<>();
    final List<String> cartTwoImage = new ArrayList<>();
    private String Image_1 = "";

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
                        if (recycleType == 0) {
                            imagePaths.add(imagePaths.size() - 1, path);
                            Log.d("chenshichun", "=======imagePaths==size==" + imagePaths.size());
                            mTaskImgAdapter.notifyDataSetChanged();
                        } else {
                            imageTwoPaths.add(imageTwoPaths.size() - 1, path);
                            mTaskImgAdapter1.notifyDataSetChanged();
                            Log.d("chenshichun", "======imageTwoPaths=size====" + imageTwoPaths.size());

                        }
                        ImageDataBean imageDataBean = gson.fromJson(result, ImageDataBean.class);
                        if (imageDataBean.getResult().getImage_show().size() > 0) {
                            if (isshanpinImage == 1) {
                                Image_1 = imageDataBean.getResult().getImage_show().get(0);
                                Log.i("====上传图片222===", Image_1 + "--");
                                tv_is_pic.setText("已上传");
                            } else {
                                if (recycleType == 0) {
                                    cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                                } else {
                                    cartTwoImage.add(imageDataBean.getResult().getImage_show().get(0));
                                }
                            }
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
