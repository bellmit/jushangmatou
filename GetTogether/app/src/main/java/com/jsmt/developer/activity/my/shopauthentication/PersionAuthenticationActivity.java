package com.jsmt.developer.activity.my.shopauthentication;

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
import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.PersonagerMessageBean;
import com.jsmt.developer.utils.Contacts;
import com.jsmt.developer.utils.permissions.PermissionsActivity;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

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
    @ViewInject(R.id.factory_name)
    private TextView factory_name;
    @ViewInject(R.id.factory_address)
    private TextView factory_address;

    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans = new ArrayList<>();// 店铺主营大类数据
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans = new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans = new ArrayList<>();

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
            factory_address.setText("公司地址");
        } else if (authenticationType == 2) {
            tv_title.setText("工厂入驻");
            name_ll.setVisibility(View.VISIBLE);
            address_ll.setVisibility(View.VISIBLE);
            factory_name.setText("工厂名称");
            factory_address.setText("工厂地址");
        }
    }

    @Event(value = {R.id.rl_close, R.id.tv_upStep, R.id.tv_downStep, R.id.ll_dpzydl, R.id.ll_gfl}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_upStep:
                finish();
                break;
            case R.id.tv_downStep:
                if (authenticationType == 1) {
                    startActivity(new Intent(PersionAuthenticationActivity.this, PersionAuthenticationSecondActivity.class));
                } else if (authenticationType == 0) {
                    startActivity(new Intent(PersionAuthenticationActivity.this, DistributorAuthenticationActivity.class));
                } else if (authenticationType == 2) {
                    startActivity(new Intent(PersionAuthenticationActivity.this, FactoryAuthenticationActivity.class));
                }
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
        }
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
