package com.tem.gettogether.activity.my.shopauthentication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import com.tem.gettogether.activity.my.EnterpriseShopRZActivity;
import com.tem.gettogether.activity.my.UploadQYActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.PersonagerMessageBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_persion_rz)
public class PersionAuthenticationActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_upStep)
    private TextView tv_upStep;
    @ViewInject(R.id.tv_downStep)
    private TextView tv_downStep;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.tv_dpzy)
    private TextView tv_dpzy;
    @ViewInject(R.id.tv_gfl)
    private TextView tv_gfl;
    @ViewInject(R.id.et_dpzzh)
    private TextView et_dpzzh;
    @ViewInject(R.id.et_dl_pass)
    private TextView et_dl_pass;
    @ViewInject(R.id.et_dpAddress)
    private TextView et_dpAddress;
    @ViewInject(R.id.ll_dpzydl)
    private LinearLayout ll_dpzydl;
    @ViewInject(R.id.ll_gfl)
    private LinearLayout ll_gfl;
    @ViewInject(R.id.name_ll)
    private LinearLayout name_ll;
    @ViewInject(R.id.address_ll)
    private LinearLayout address_ll;
    @ViewInject(R.id.ll_shop_quyu)
    private LinearLayout ll_shop_quyu;
    @ViewInject(R.id.factory_name)
    private TextView factory_name;
    @ViewInject(R.id.factory_address)
    private TextView factory_address;
    @ViewInject(R.id.tv_shop_quyu)
    private TextView tv_shop_quyu;
    @ViewInject(R.id.tv_gongdizhi)
    private TextView tv_gongdizhi;
    @ViewInject(R.id.tv_nextStep)
    private TextView tv_nextStep;
    @ViewInject(R.id.factory_detail_address)
    private TextView factory_detail_address;
    @ViewInject(R.id.company_name_tv)
    private TextView company_name_tv;
    @ViewInject(R.id.company_detail_address_et)
    private EditText company_detail_address_et;

    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans = new ArrayList<>();// 店铺主营大类数据
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans = new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans = new ArrayList<>();

    private String company_province, company_city, company_district, store_province, store_city, store_district;

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

    private int authenticationType = 0;

    @Override
    protected void initData() {
        x.view().inject(this);
        authenticationType = getIntent().getIntExtra(Contacts.AUTHENTICATION_TYPE, 1);
        getData();
        upGetAddressData("0");
    }

    @Override
    protected void initView() {
        if (authenticationType == 1) {
            tv_title.setText("个人入驻");
            name_ll.setVisibility(View.GONE);
            address_ll.setVisibility(View.GONE);
        } else if (authenticationType == 0) {
            tv_title.setText("经销商入驻");
            name_ll.setVisibility(View.VISIBLE);
            address_ll.setVisibility(View.VISIBLE);
            factory_name.setText("公司名称");
            factory_address.setText("公司所在区域");
            factory_detail_address.setText("公司详细地址");
        } else if (authenticationType == 2) {
            tv_title.setText("工厂入驻");
            name_ll.setVisibility(View.VISIBLE);
            address_ll.setVisibility(View.VISIBLE);
            factory_name.setText("工厂名称");
            factory_address.setText("工厂所在区域");
            factory_detail_address.setText("工厂详细地址");
        }
    }

    @Event(value = {R.id.rl_close, R.id.tv_upStep, R.id.tv_downStep, R.id.ll_dpzydl, R.id.ll_gfl, R.id.address_ll, R.id.ll_shop_quyu, R.id.tv_nextStep}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_upStep:
                finish();
                break;
            case R.id.ll_dpzydl://店铺主营大类
                type = 1;
                mPop = null;
                showPop(ll_dpzydl);
                break;
            case R.id.ll_gfl://管分类
                type = 2;
                mPop = null;
                showPop(ll_gfl);
                break;
            case R.id.address_ll:
                addressOrShopType = 0;
                showPopAddress(address_ll);
                break;
            case R.id.ll_shop_quyu:
                addressOrShopType = 1;
                showPopAddress(ll_shop_quyu);
                break;
            case R.id.tv_downStep:
                Map<String, Object> map = new HashMap<>();
                if (BaseApplication.getInstance().userBean == null) return;
                map.put("token", BaseApplication.getInstance().userBean.getToken());
                if (authenticationType != 1) {
                    map.put("company_name", company_name_tv.getText().toString());
                    map.put("company_province", company_province);
                    map.put("company_city", company_city);
                    map.put("company_district", company_district);
                    map.put("company_address", company_detail_address_et.getText().toString());
                }
                map.put("store_name", et_name.getText().toString());
                map.put("password", et_dl_pass.getText().toString());
                map.put("seller_name", et_dpzzh.getText().toString());
                map.put("sc_id", DPDL);
                map.put("store_province", store_province);
                map.put("store_city", store_city);
                map.put("store_district", store_district);
                map.put("store_address", et_dpAddress.getText().toString());
                map.put("apply_type", authenticationType);
                if (authenticationType == 1) {
                    upPersionData(map);
                } else {
                    upTJSHData(map);
                }
                break;
        }
    }

    // 经销商或工厂
    private void upTJSHData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.QIYESHANGPU_RENZHENG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商铺企业入驻提交审核===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        startActivity(new Intent(PersionAuthenticationActivity.this, DistributorAuthenticationActivity.class)
                                .putExtra(Contacts.DEALER_OR_FACTORY_AUTHENTICATION, authenticationType));
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


    // 个人
    private void upPersionData(Map<String, Object> map) {
        showDialog();
        XUtil.Post(URLConstant.GERENRUZHU_RENZHENG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        startActivity(new Intent(PersionAuthenticationActivity.this, DistributorAuthenticationActivity.class)
                                .putExtra(Contacts.DEALER_OR_FACTORY_AUTHENTICATION, authenticationType));
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


    /*
     * 获取地址
     * */
    private List<AddressDataBean.ResultBean> addresssresultBeans = new ArrayList<>();
    private boolean isOne = true;

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

    public void clearList(List<AddressDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
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
    private String addressType = "1";
    private String Sheng, city, qu, jiedao;

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
                    tv_sheng.setText("请选择");
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
                    tv_city.setText("请选择");
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
                    tv_qu.setText("请选择");
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
                            if (addressOrShopType == 0) {
                                company_province = addresssresultBeans.get(position).getId();
                            } else {
                                store_province = addresssresultBeans.get(position).getId();
                            }
                        } else if (addressType.equals("2")) {
                            ll_qu.setVisibility(View.VISIBLE);
                            addressType = "3";
                            city = addresssresultBeans.get(position).getId();
                            tv_city.setTextColor(getResources().getColor(R.color.text3));
                            view_line2.setVisibility(View.INVISIBLE);
                            tv_city.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());
                            if (addressOrShopType == 0) {
                                company_city = addresssresultBeans.get(position).getId();
                            } else {
                                store_city = addresssresultBeans.get(position).getId();
                            }
                        } else if (addressType.equals("3")) {
                            ll_jiedao.setVisibility(View.VISIBLE);
                            addressType = "4";
                            qu = addresssresultBeans.get(position).getId();
                            tv_qu.setTextColor(getResources().getColor(R.color.text3));
                            view_line3.setVisibility(View.INVISIBLE);
                            tv_qu.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());
                            if (addressOrShopType == 0) {
                                company_district = addresssresultBeans.get(position).getId();
                            } else {
                                store_district = addresssresultBeans.get(position).getId();
                            }
                        } else if (addressType.equals("4")) {
                            tv_jiedao.setTextColor(getResources().getColor(R.color.text3));
                            view_line4.setVisibility(View.INVISIBLE);
                            tv_jiedao.setText(addresssresultBeans.get(position).getName());
                            jiedao = addresssresultBeans.get(position).getId();
                            if (addressOrShopType == 0) {
                                tv_gongdizhi.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            } else {
                                tv_shop_quyu.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            }
                            mPopAddress.dismiss();

                        }
                        CusToast.showToast(addresssresultBeans.get(position).getName());
                        notifyDataSetChanged();
                    }
                });

            }


        });
    }

    private void getData() {
        showDialog();
        XUtil.Post(URLConstant.GRXINXLIEB, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===个人信息选择列表--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        PersonagerMessageBean personagerMessageBean = gson.fromJson(result, PersonagerMessageBean.class);
                        goodsCategoryBeans = personagerMessageBean.getResult().getGoods_category();
                        pavilionBeans = personagerMessageBean.getResult().getPavilion();
                        storeClassBeans = personagerMessageBean.getResult().getStore_class();
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
    private int addressOrShopType;// 0: 公司所在区域 1：店铺所在区域
    private String DPDL = "";
    private String GFL = "";
    private String DPXZ = "";

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
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            if (type == 1) {
                tv_xz_title.setText("请选择店铺主营大类");
                initSetdialog();
            } else if (type == 2) {
                tv_xz_title.setText("请选择馆分类");
                initSetdialog2();
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

    private void initSetdialog() {
        BaseRVAdapter baseRVAdapter = new BaseRVAdapter(this, goodsCategoryBeans) {
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
                        DPDL = goodsCategoryBeans.get(position).getId();
                        tv_dpzy.setText(goodsCategoryBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }

    private void initSetdialog2() {
        BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(this, pavilionBeans) {
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
                        GFL = pavilionBeans.get(position).getId();
                        tv_gfl.setText(pavilionBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }
}
