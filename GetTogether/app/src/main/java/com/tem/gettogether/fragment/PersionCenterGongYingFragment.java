package com.tem.gettogether.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.FansActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.ProductManagmentActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.StoreManagementActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.VisitorActivity;
import com.tem.gettogether.activity.my.authentication.AuthenticationActivity;
import com.tem.gettogether.activity.my.member.MemberCentreActivity;
import com.tem.gettogether.activity.my.refundprogress.RefundProgressActivity;
import com.tem.gettogether.activity.order.GongYingShangNewOrderActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.bean.OrderCountBean;
import com.tem.gettogether.dialog.CheckUpDialog;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.fragment_persion_center_gongying)
public class PersionCenterGongYingFragment extends BaseFragment {

    @ViewInject(R.id.tv_setting)
    TextView tv_setting;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.rl_zxkf)
    private RelativeLayout rl_zxkf;
    @ViewInject(R.id.tv_shop_RZ)
    private TextView tv_shop_RZ;
    @ViewInject(R.id.iv_head)
    private CircularImage iv_head;
    @ViewInject(R.id.rz_status_tv)
    private TextView rz_status_tv;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.huiyuan_iv)
    private ImageView huiyuan_iv;
    @ViewInject(R.id.tv_scNum)
    private TextView tv_scNum;
    @ViewInject(R.id.tv_zjNum)
    private TextView tv_zjNum;
    @ViewInject(R.id.tv_qb_num)
    private TextView tv_qb_num;
    @ViewInject(R.id.count_0)
    private TextView count_0;
    @ViewInject(R.id.count_1)
    private TextView count_1;
    @ViewInject(R.id.count_2)
    private TextView count_2;
    @ViewInject(R.id.count_3)
    private TextView count_3;
    private BaseActivity baseActivity;
    private MyMessageBean.ResultBean resultBean = new MyMessageBean.ResultBean();
    private String userLever;
    private MyMessageBean myMessageBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();

        upGetMessageData();
        initRefresh();
        getOrderNum();
    }

    /*
     * 店铺信息
     * */
    private void upGetMessageData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        baseActivity.showDialog();
        XUtil.Post(URLConstant.GET_MESSAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取个人信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        myMessageBean = gson.fromJson(result, MyMessageBean.class);
                        resultBean = myMessageBean.getResult();

                        Glide.with(getActivity()).load(myMessageBean.getResult().getHead_pic() + "").asBitmap().error(R.mipmap.myy322x).centerCrop().into(new BitmapImageViewTarget(iv_head));
                        tv_name.setText(myMessageBean.getResult().getNickname());
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.NAME, myMessageBean.getResult().getNickname());

                        userLever = myMessageBean.getResult().getLevel();
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.LEVER, userLever);
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, resultBean.getStore_status());
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.head_pic, resultBean.getHead_pic());
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.Shop_store_id, resultBean.getStore_id());
                        if (myMessageBean.getResult().getStore_count() != null) {
                            tv_scNum.setText(myMessageBean.getResult().getStore_count());
                        } else {
                            tv_scNum.setText("0");
                        }

                        if (myMessageBean.getResult().getFans_count() != null) {
                            tv_zjNum.setText(myMessageBean.getResult().getFans_count());
                        } else {
                            tv_zjNum.setText("0");
                        }

                        if (myMessageBean.getResult().getVisiters_count() != null) {
                            tv_qb_num.setText(myMessageBean.getResult().getVisiters_count());
                        } else {
                            tv_qb_num.setText("0");
                        }

                        if (userLever.equals("7")) {
                            huiyuan_iv.setBackground(null);
                        } else if (userLever.equals("1")) {
                            huiyuan_iv.setBackgroundResource(R.drawable.my_huiyuan_gray);
                        } else if (userLever.equals("2")) {
                            huiyuan_iv.setBackgroundResource(R.drawable.my_huiyuan);
                        }
                        if (resultBean.getStore_status().equals("1")) {
                            tv_shop_RZ.setText(getText(R.string.dianpuguanli));
                            rz_status_tv.setText("");//已认证
                        } else if (resultBean.getStore_status().equals("2")) {
                            rz_status_tv.setText(getText(R.string.certification_review));
                        } else if (resultBean.getStore_status().equals("3")) {
                            rz_status_tv.setText(getText(R.string.rzsb));
                        } else {
                            rz_status_tv.setText(getText(R.string.pending_certification));
                        }
                    } else {
//                        startActivity(new Intent(getActivity(), LoginActivity.class));
//                        getActivity().finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }


    // 获取退款状态
    public void getRefundProgress() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        XUtil.Post(URLConstant.GET_REFUND_PROGRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====退款状态===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        if (jsonObject.optString("result") == null || jsonObject.optString("result").equals("")) {
                            if (myMessageBean != null) {
                                startActivity(new Intent(getActivity(), MemberCentreActivity.class));
                            }
                        } else {
                            startActivity(new Intent(getActivity(), RefundProgressActivity.class));
                        }

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

    // 获取订单数量
    public void getOrderNum() {
        Map<String, Object> map = new HashMap<>();
        map.put("role_type", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.ROLE_TYPE, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        XUtil.Post(URLConstant.ORDER_NUM, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    //CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        OrderCountBean mOrderCountBean = gson.fromJson(result, OrderCountBean.class);
                     /*   if (!mOrderCountBean.getResult().getAll_count().equals("0")) {
                            count_0.setVisibility(View.VISIBLE);
                        } else {
                            count_0.setVisibility(View.GONE);
                        }*/
                        if (!mOrderCountBean.getResult().getWaitsend().equals("0")) {
                            count_1.setVisibility(View.VISIBLE);
                        } else {
                            count_1.setVisibility(View.GONE);
                        }
                        if (!mOrderCountBean.getResult().getWaitreceive().equals("0")) {
                            count_2.setVisibility(View.VISIBLE);
                        } else {
                            count_2.setVisibility(View.GONE);
                        }
                        if (!mOrderCountBean.getResult().getWaitccomment().equals("0")) {
                            count_3.setVisibility(View.VISIBLE);
                        } else {
                            count_3.setVisibility(View.GONE);
                        }
//                        count_0.setText(mOrderCountBean.getResult().getAll_count());
                        count_1.setText(mOrderCountBean.getResult().getWaitsend());
                        count_2.setText(mOrderCountBean.getResult().getWaitreceive());
                        count_3.setText(mOrderCountBean.getResult().getWaitccomment());
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

    @Event(value = {R.id.iv_head, R.id.tv_setting, R.id.rl_sprz, R.id.tv_name, R.id.tv_all, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,
            R.id.tv_dfh, R.id.tv_dsh, R.id.tv_dfk, R.id.rl_my_message, R.id.rl_dzgl, R.id.rl_ksbh, R.id.rl_zxkf, R.id.rl_tdyj, R.id.rl_gywm}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_setting:// 设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_name:// 修改名字
                upName();
                break;
            case R.id.ll_scj:// 商品
                startActivity(new Intent(getActivity(), ProductManagmentActivity.class));
                break;
            case R.id.ll_zj:// 粉丝
                startActivity(new Intent(getActivity(), FansActivity.class));
                break;
            case R.id.ll_qb:// 访客
                startActivity(new Intent(getActivity(), VisitorActivity.class));
                break;
            case R.id.tv_all:// 全部
                startActivity(new Intent(getActivity(), GongYingShangNewOrderActivity.class)
                        .putExtra("tabType", "0"));
                break;
            case R.id.tv_dfk:// 待收货
                startActivity(new Intent(getActivity(), GongYingShangNewOrderActivity.class)
                        .putExtra("tabType", "1"));
                break;
            case R.id.tv_dfh:// 待发货
                startActivity(new Intent(getActivity(), GongYingShangNewOrderActivity.class)
                        .putExtra("tabType", "2"));
                break;
            case R.id.tv_dsh:// 结款
                startActivity(new Intent(getActivity(), GongYingShangNewOrderActivity.class)
                        .putExtra("tabType", "3"));
                break;
            case R.id.rl_my_message:// 企业信息
                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("2")) {
                    CusToast.showToast(getText(R.string.store_review));
                    return;
                }
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
                    CusToast.showToast(getText(R.string.please_certify_shops_first));
                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0));
                break;
            case R.id.rl_ksbh:// 会员信息
                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("2")) {
                    CusToast.showToast(getText(R.string.store_review));
                    return;
                }
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
                    CusToast.showToast(getText(R.string.please_certify_shops_first));
                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                    return;
                }

                getRefundProgress();

                break;
            case R.id.rl_dzgl:// 我的店铺
                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("2")) {
                    CusToast.showToast(getText(R.string.store_review));
                    return;
                }
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
                    CusToast.showToast(getText(R.string.please_certify_shops_first));
                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                    return;
                }
                startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                        .putExtra("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, "0"))
                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                break;
            case R.id.rl_sprz: // 商铺认证
                if (resultBean.getStore_status().equals("1")) {//店铺管理
                    startActivity(new Intent(getActivity(), StoreManagementActivity.class));
                } else if (resultBean.getStore_status().equals("2")) {
                    CusToast.showToast(getText(R.string.store_review));
                    return;
                } else if (resultBean.getStore_status().equals("3")) {// 认证失败
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                } else if (resultBean.getStore_status().equals("4")) {// 认证
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                }
                break;
            case R.id.rl_zxkf:// 在线客服
                showPop(rl_zxkf);
                break;
            case R.id.rl_tdyj:// 提点意见
                startActivity(new Intent(getActivity(), TAdviseActivity.class));
                break;
            case R.id.rl_gywm:// 关于我们
                startActivity(new Intent(getActivity(), GYWeActivity.class));
                break;
        }
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setPullDownStr(getString(R.string.pull_down_refresh));
        headerView.setReleaseRefreshStr(getString(R.string.release_refresh));
        headerView.setRefreshingStr(getString(R.string.refreshing));
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableOverScroll(false);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                upGetMessageData();
                getOrderNum();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                refreshLayout.finishLoadmore();
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
            }
        });
    }

    private PopupWindow mPop;

    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
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
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }

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

    private void setPopClickListener(View view) {
        TextView tv_iteam1, photo, cancle;
        photo = view.findViewById(R.id.photo);
        cancle = view.findViewById(R.id.cancle);
        tv_iteam1 = view.findViewById(R.id.tv_iteam1);
        tv_iteam1.setText(R.string.kefudian);
        photo.setText(getResources().getText(R.string.call) + resultBean.getService_qq());
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri datav = Uri.parse("tel:" + resultBean.getService_qq());
                intent.setData(datav);
                startActivity(intent);
                mPop.dismiss();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        upGetMessageData();
        getOrderNum();
    }

    private void upName() {
        final CheckUpDialog dialogSucceed = new CheckUpDialog(getContext(), R.style.MyDialog);
        dialogSucceed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSucceed.setContentView(R.layout.activity_setting_name);
        dialogSucceed.setCancelable(false);//不能用返回键
        final EditText et_Name = (EditText) dialogSucceed.findViewById(R.id.et_Name);
        dialogSucceed.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSucceed.cancel();
            }
        });
        dialogSucceed.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_Name.getText().toString().equals("")) {
                    tv_name.setText(et_Name.getText().toString());
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                    map.put("nickname", et_Name.getText().toString());
                    upXGMessageData(map);
                    dialogSucceed.cancel();
                } else {
                    CusToast.showToast(getText(R.string.please_fill_nickname));
                    return;
                }
            }
        });
        dialogSucceed.show();
    }

    private void upXGMessageData(Map<String, Object> map) {
        baseActivity.showDialog();
        XUtil.Post(URLConstant.XIUGAI_MESSAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====修改个人信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                   // CusToast.showToast(msg);
                    if (res.equals("1")) {
                        upGetMessageData();
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
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }

}
