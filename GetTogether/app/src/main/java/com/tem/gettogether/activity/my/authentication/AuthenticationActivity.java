package com.tem.gettogether.activity.my.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.AuthenticationBean;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.bean.PersonagerMessageBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.wildma.pictureselector.PictureSelector;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： 店铺认证
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/4 15:22 
 */

@ContentView(R.layout.activity_third_authentication)
public class AuthenticationActivity extends BaseMvpActivity<AuthenticationPresenter> implements AuthenticationContract.AuthenticationView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.reason_for_rejection_ll)
    private LinearLayout reason_for_rejection_ll;
    @ViewInject(R.id.reason_for_rejection_tv)
    private TextView reason_for_rejection_tv;
    @ViewInject(R.id.linyi_cb)
    private CheckBox linyi_cb;
    @ViewInject(R.id.distributor_rb)
    private RadioButton distributor_rb;
    @ViewInject(R.id.factory_rb)
    private RadioButton factory_rb;
    @ViewInject(R.id.personal_rb)
    private RadioButton personal_rb;
    @ViewInject(R.id.not_persion_ll)
    private LinearLayout not_persion_ll;
    @ViewInject(R.id.module_ll)
    private LinearLayout module_ll;
    @ViewInject(R.id.factory_ll)
    private LinearLayout factory_ll;

    @ViewInject(R.id.ll_dpzydl)
    private LinearLayout ll_dpzydl;

    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_youxiang)
    private EditText et_youxiang;
    @ViewInject(R.id.et_moble)
    private EditText et_moble;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @ViewInject(R.id.et_shop_name)
    private EditText et_shop_name;
    @ViewInject(R.id.tv_dpzy)
    private TextView tv_dpzy;

    @ViewInject(R.id.tv_shop_quyu)
    private TextView tv_shop_quyu;
    @ViewInject(R.id.ll_shop_quyu)
    private LinearLayout ll_shop_quyu;
    @ViewInject(R.id.et_dpAddress)
    private TextView et_dpAddress;
    @ViewInject(R.id.iv_image_1)
    private ImageView iv_image_1;
    @ViewInject(R.id.iv_image_2)
    private ImageView iv_image_2;
    @ViewInject(R.id.iv_image_3)
    private ImageView iv_image_3;
    @ViewInject(R.id.iv_image_4)
    private ImageView iv_image_4;
    @ViewInject(R.id.iv_image_5)
    private ImageView iv_image_5;
    @ViewInject(R.id.iv_close1)
    private ImageView iv_close1;
    @ViewInject(R.id.iv_close2)
    private ImageView iv_close2;
    @ViewInject(R.id.iv_close3)
    private ImageView iv_close3;
    @ViewInject(R.id.iv_close4)
    private ImageView iv_close4;
    @ViewInject(R.id.iv_close5)
    private ImageView iv_close5;

    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans = new ArrayList<>();// 店铺主营大类数据
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans = new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans = new ArrayList<>();

    private List<AddressDataBean.ResultBean> addresssresultBeans = new ArrayList<>();
    private boolean isOne = true;
    private String store_province, store_city, store_district;
    private String compressImageFilePath;
    private int imageType = 0;
    private int identityType = -1;
    private String image1, image2, image3, image4, image5;

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        tv_title.setText(getText(R.string.shop_certification));
    }

    @Override
    protected void initView() {
        mPresenter = new AuthenticationPresenter(getContext(), AuthenticationActivity.this);
        mPresenter.attachView(this);
        mPresenter.getData();
        mPresenter.upGetAddressData("0");
        getSwipeBackLayout().setEnableGesture(false);//禁止右滑退出

        if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
            if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("3")) {
                reason_for_rejection_ll.setVisibility(View.VISIBLE);
            }
            mPresenter.upGetRzFailedData();
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.USERID, ""));
            mPresenter.getAuthenticationData(map);
        }

        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        distributor_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.showDistributor();
                }
            }
        });

        factory_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.showFactory();
                }
            }
        });

        personal_rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.showPersonal();
                }
            }
        });
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
        closeDialog();
        Log.e("chenshichun", "---throwable--" + throwable);
    }

    @Override
    public void showDistributor() {
        module_ll.setVisibility(View.VISIBLE);
        not_persion_ll.setVisibility(View.VISIBLE);
        factory_ll.setVisibility(View.GONE);
        identityType = 0;
    }

    @Override
    public void showFactory() {
        module_ll.setVisibility(View.VISIBLE);
        not_persion_ll.setVisibility(View.VISIBLE);
        factory_ll.setVisibility(View.VISIBLE);
        identityType = 2;
    }

    @Override
    public void showPersonal() {
        module_ll.setVisibility(View.VISIBLE);
        not_persion_ll.setVisibility(View.GONE);
        identityType = 1;
    }

    @Override
    public void getData(PersonagerMessageBean personagerMessageBean) {
        goodsCategoryBeans = personagerMessageBean.getResult().getGoods_category();
        pavilionBeans = personagerMessageBean.getResult().getPavilion();
        storeClassBeans = personagerMessageBean.getResult().getStore_class();
    }


    @Event(value = {R.id.rl_close, R.id.ll_dpzydl, R.id.ll_shop_quyu, R.id.rl_card_image1,
            R.id.rl_card_image2, R.id.rl_card_image3, R.id.rl_card_image4, R.id.rl_card_image5, R.id.tv_save})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                dialog();
                break;
            case R.id.ll_dpzydl://店铺主营大类
                showPop(ll_dpzydl);
                break;
            case R.id.ll_shop_quyu:// 店铺所在区域
                showPopAddress(ll_shop_quyu);
                break;
            case R.id.rl_card_image1:
                imageType = 0;

                PictureSelector
                        .create(AuthenticationActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_card_image2:
                imageType = 1;

                PictureSelector
                        .create(AuthenticationActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_card_image3:
                imageType = 2;

                PictureSelector
                        .create(AuthenticationActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_card_image4:
                imageType = 3;

                PictureSelector
                        .create(AuthenticationActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.rl_card_image5:
                imageType = 4;

                PictureSelector
                        .create(AuthenticationActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 500, 500, 1, 1);
                break;
            case R.id.tv_save:
                if (et_name.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.input_contacts_name));
                    return;
                } else if (et_phone.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.input_contacts_phone_nun));
                    return;
                } else if (et_youxiang.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.input_contacts_email));
                    return;
                }
                /*if (!isEmail(et_youxiang.getText().toString())) {
                    CusToast.showToast(getText(R.string.please_select_the_museum_classification));
                    return;
                }*/
                if (identityType == -1) {
                    CusToast.showToast(R.string.select_supplier_identity);
                    return;
                }

                if (et_name.getText().toString().equals("")) {
                    CusToast.showToast(getText(R.string.enter_store_name));
                    return;
                }
                if (tv_dpzy.getText().toString().equals(getText(R.string.please_choose))) {
                    CusToast.showToast(getText(R.string.choose_main_store_category));
                    return;
                }
                if (tv_shop_quyu.getText().toString().equals(getText(R.string.please_choose))) {
                    CusToast.showToast(getText(R.string.please_select_the_area_where_the_store_is_located));
                    return;
                }
                if (iv_image_1.getDrawable() == null) {
                    CusToast.showToast(getText(R.string.please_upload_the_ID_card_front));
                    return;
                }
                if (iv_image_2.getDrawable() == null) {
                    CusToast.showToast(getText(R.string.please_upload_the_reverse_side_of_the_ID_card));
                    return;
                }
                if (identityType != 1) {

                    if (iv_image_3.getDrawable() == null) {
                        CusToast.showToast(getText(R.string.please_upload_business_license));
                        return;
                    }

                    if (identityType == 2) {
                        if (iv_image_4.getDrawable() == null && iv_image_5.getDrawable() == null) {
                            CusToast.showToast(getText(R.string.please_upload_factory_real_map));
                            return;
                        }
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("user_id", SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.USERID, ""));
                map.put("contacts_name", et_name.getText().toString());
                map.put("contacts_mobile", et_phone.getText().toString());
                map.put("contacts_email", et_youxiang.getText().toString());
                map.put("apply_type", identityType);
                map.put("is_linyi", linyi_cb.isChecked() ? "1" : "0");
                map.put("mobile", et_moble.getText().toString());
                map.put("sc_id", shopCategory);
                map.put("store_name", et_shop_name.getText().toString());
                map.put("store_province", store_province);
                map.put("store_city", store_city);
                map.put("store_district", store_district);
                map.put("store_address", et_dpAddress.getText().toString());

                if (personal_rb.isChecked()) {
                    Log.e("chenshichun", "---个人身份证--" + (image1 + "," + image2));
                    if (image1 != null || image2 != null) {
                        map.put("store_person_cert", image1 + "," + image2);
                    }
                } else {
                    if (image3 != null) {
                        map.put("business_licence_cert", image3);
                    }
                    Log.e("chenshichun", "---企业身份证--" + (image1 + "," + image2));
                    if (image1 != null || image2 != null) {
                        map.put("legal_identity_cert", image1 + "," + image2);
                    }
                    if (image4 != null || image5 != null) {
                        map.put("factory_scene", image4 + "," + image5);
                    }
                }
                mPresenter.saveEnterprisePersonalStore(map);
                break;
        }
    }


    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;
    private String shopCategory = "";

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
            view = LayoutInflater.from(mContext).inflate(R.layout.main_qgxx_layout, null);
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
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            tv_xz_title.setText(mContext.getText(R.string.choose_main_store_category));
            initSetdialog();
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
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
                        shopCategory = goodsCategoryBeans.get(position).getId();
                        tv_dpzy.setText(goodsCategoryBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
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
                    mPresenter.upGetAddressData("0");
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
                        mPresenter.upGetAddressData(Sheng);
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
                        mPresenter.upGetAddressData(city);
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

    @Override
    public void addressSetData() {
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
                        mPresenter.upGetAddressData(addresssresultBeans.get(position).getId());
                        if (addressType.equals("1")) {
                            ll_city.setVisibility(View.VISIBLE);
                            addressType = "2";
                            Sheng = addresssresultBeans.get(position).getId();
                            tv_sheng.setTextColor(getResources().getColor(R.color.text3));
                            view_line.setVisibility(View.INVISIBLE);
                            tv_sheng.setText(addresssresultBeans.get(position).getName());
                            mPresenter.upGetAddressData(addresssresultBeans.get(position).getId());
                            store_province = addresssresultBeans.get(position).getId();
                        } else if (addressType.equals("2")) {
                            ll_qu.setVisibility(View.VISIBLE);
                            addressType = "3";
                            city = addresssresultBeans.get(position).getId();
                            tv_city.setTextColor(getResources().getColor(R.color.text3));
                            view_line2.setVisibility(View.INVISIBLE);
                            tv_city.setText(addresssresultBeans.get(position).getName());
                            mPresenter.upGetAddressData(addresssresultBeans.get(position).getId());
                            store_city = addresssresultBeans.get(position).getId();
                        } else if (addressType.equals("3")) {
                            ll_jiedao.setVisibility(View.VISIBLE);
                            addressType = "4";
                            qu = addresssresultBeans.get(position).getId();
                            tv_qu.setTextColor(getResources().getColor(R.color.text3));
                            view_line3.setVisibility(View.INVISIBLE);
                            tv_qu.setText(addresssresultBeans.get(position).getName());
                            mPresenter.upGetAddressData(addresssresultBeans.get(position).getId());
                            store_district = addresssresultBeans.get(position).getId();
                        } else if (addressType.equals("4")) {
                            tv_jiedao.setTextColor(getResources().getColor(R.color.text3));
                            view_line4.setVisibility(View.INVISIBLE);
                            tv_jiedao.setText(addresssresultBeans.get(position).getName());
                            jiedao = addresssresultBeans.get(position).getId();
                            tv_shop_quyu.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            mPopAddress.dismiss();

                        }
                        CusToast.showToast(addresssresultBeans.get(position).getName());
                        notifyDataSetChanged();
                    }
                });

            }


        });
    }

    @Override
    public void getAddressData(List<AddressDataBean.ResultBean> addressRresultBeans) {
        clearList(addresssresultBeans);
        addresssresultBeans = addressRresultBeans;
    }

    @Override
    public void addressData() {
        if (isOne == false) {
            addressSetData();
        }
    }

    @Override
    public void setRejectionReason(String rejectionReason) {
        reason_for_rejection_tv.setText(rejectionReason);
    }

    @Override
    public void setRejectionInformation(AuthenticationBean.ResultBean resultBean) {
        et_name.setText(resultBean.getContacts_name());
        et_phone.setText(resultBean.getContacts_mobile());
        et_youxiang.setText(resultBean.getContacts_email());

        if (resultBean.getApply_type().equals("0")) {
            distributor_rb.setChecked(true);
        } else if (resultBean.getApply_type().equals("1")) {
            personal_rb.setChecked(true);
        } else if (resultBean.getApply_type().equals("2")) {
            factory_rb.setChecked(true);
        }

        if (resultBean.getIs_linyi().equals("1")) {
            linyi_cb.setChecked(true);
        } else {
            linyi_cb.setChecked(false);
        }
        et_moble.setText(resultBean.getMobile());
        et_shop_name.setText(resultBean.getStore_name());
        tv_dpzy.setText(resultBean.getStype());
        tv_shop_quyu.setText(resultBean.getArea());
        et_dpAddress.setText(resultBean.getStore_address());
        shopCategory = resultBean.getSc_id();
        store_province = resultBean.getStore_province();
        store_city = resultBean.getStore_city();
        store_district = resultBean.getStore_district();

        if (resultBean.getApply_type().equals("1")) {
            if (resultBean.getStore_person_cert().get(0) != null) {
                image1 = resultBean.getStore_person_cert().get(0);
                Glide.with(getContext()).load(resultBean.getStore_person_cert().get(0)).error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
            }
            if (resultBean.getStore_person_cert().get(1) != null) {
                image2 = resultBean.getStore_person_cert().get(1);
                Glide.with(getContext()).load(resultBean.getStore_person_cert().get(1)).error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
            }
        } else {
            if (resultBean.getLegal_identity_cert().get(0) != null) {
                image1 = resultBean.getLegal_identity_cert().get(0);
                Glide.with(getContext()).load(resultBean.getLegal_identity_cert().get(0)).error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
            }
            if (resultBean.getLegal_identity_cert().get(1) != null) {
                image2 = resultBean.getLegal_identity_cert().get(1);
                Glide.with(getContext()).load(resultBean.getLegal_identity_cert().get(1)).error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
            }
        }


        if (resultBean.getBusiness_licence_cert().get(0) != null) {
            image3 = resultBean.getBusiness_licence_cert().get(0);
            Glide.with(getContext()).load(resultBean.getBusiness_licence_cert().get(0)).error(R.mipmap.myy322x).centerCrop().into(iv_image_3);
        }

        if (resultBean.getFactory_scene().get(0) != null) {
            image4 = resultBean.getFactory_scene().get(0);
            Glide.with(getContext()).load(resultBean.getFactory_scene().get(0)).error(R.mipmap.myy322x).centerCrop().into(iv_image_4);
        }

        if (resultBean.getFactory_scene().get(1) != null) {
            image5 = resultBean.getFactory_scene().get(1);
            Glide.with(getContext()).load(resultBean.getFactory_scene().get(1)).error(R.mipmap.myy322x).centerCrop().into(iv_image_5);
        }
    }

    public void clearList(List<AddressDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    ImageDataBean imageDataBean = null;

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
                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_PICTURE);
                            if (imageDataBean != null) {
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
                    if (imageType == 0) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_1);
                        iv_image_1.setVisibility(View.VISIBLE);
                        iv_close1.setVisibility(View.VISIBLE);
                        image1 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 1) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_2);
                        iv_image_2.setVisibility(View.VISIBLE);
                        iv_close2.setVisibility(View.VISIBLE);
                        image2 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 2) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_3);
                        iv_image_3.setVisibility(View.VISIBLE);
                        iv_close3.setVisibility(View.VISIBLE);
                        image3 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 3) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_4);
                        iv_image_4.setVisibility(View.VISIBLE);
                        iv_close4.setVisibility(View.VISIBLE);
                        image4 = imageDataBean.getResult().getImage_show().get(0);
                    } else if (imageType == 4) {
                        Glide.with(getContext()).load(imageDataBean.getResult().getImage_show().get(0) + "").error(R.mipmap.myy322x).centerCrop().into(iv_image_5);
                        iv_image_5.setVisibility(View.VISIBLE);
                        iv_close5.setVisibility(View.VISIBLE);
                        image5 = imageDataBean.getResult().getImage_show().get(0);
                    }
                    break;
                case 1:
                    closeDialog();
                    break;
            }
        }
    };

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AuthenticationActivity.this);
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

}
