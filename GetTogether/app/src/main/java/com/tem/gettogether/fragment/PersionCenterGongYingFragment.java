package com.tem.gettogether.fragment;

import android.content.Intent;
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
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.FansActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.ShopRzFailedActivity;
import com.tem.gettogether.activity.my.StoreManagementActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.VipCenterActivity;
import com.tem.gettogether.activity.my.VisitorActivity;
import com.tem.gettogether.activity.my.XunPanTuiSongActivity;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.activity.order.GongYingOrderActivity;
import com.tem.gettogether.activity.order.GongYingShangNewOrderActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.dialog.CheckUpDialog;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
import com.tem.gettogether.wxapi.WXEntryActivity;

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
    @ViewInject(R.id.ll_scj)
    private LinearLayout ll_scj;
    @ViewInject(R.id.ll_zj)
    private LinearLayout ll_zj;
    @ViewInject(R.id.ll_qb)
    private LinearLayout ll_qb;
    @ViewInject(R.id.tv_all)
    private TextView tv_all;
    @ViewInject(R.id.tv_dfk)
    private TextView tv_dfk;
    @ViewInject(R.id.tv_dfh)
    private TextView tv_dfh;
    @ViewInject(R.id.tv_dsh)
    private TextView tv_dsh;
    @ViewInject(R.id.rl_my_message)
    private RelativeLayout rl_my_message;
    @ViewInject(R.id.rl_ksbh)
    private RelativeLayout rl_ksbh;
    @ViewInject(R.id.rl_sprz)
    private RelativeLayout rl_sprz;
    @ViewInject(R.id.rl_dzgl)
    private RelativeLayout rl_dzgl;
    @ViewInject(R.id.rl_zxkf)
    private RelativeLayout rl_zxkf;
    @ViewInject(R.id.rl_tdyj)
    private RelativeLayout rl_tdyj;
    @ViewInject(R.id.rl_gywm)
    private RelativeLayout rl_gywm;
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
                        if (userLever.equals("7")) {
                            huiyuan_iv.setBackground(null);
                        } else if (userLever.equals("1")) {
                            huiyuan_iv.setBackgroundResource(R.drawable.my_huiyuan_gray);
                        } else if (userLever.equals("2")) {
                            huiyuan_iv.setBackgroundResource(R.drawable.my_huiyuan);
                        }
                        if (resultBean.getStore_status().equals("1")) {
                            tv_shop_RZ.setText("店铺管理");
                            rz_status_tv.setText("已认证");
                        } else if (resultBean.getStore_status().equals("2")) {
                            rz_status_tv.setText("认证审核中");
                        } else {
                            rz_status_tv.setText("待认证");
                        }
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
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
                    CusToast.showToast("商铺未认证，请先认证商铺!");
                    return;
                }
                startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0));
                break;
            case R.id.rl_ksbh:// 会员信息
                if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0").equals("1")) {
                    CusToast.showToast("商铺未认证，请先认证商铺!");
                    return;
                }
                if (myMessageBean != null) {
                    startActivity(new Intent(getActivity(), VipCenterActivity.class)
                            .putExtra("head_pic", myMessageBean.getResult().getHead_pic())
                            .putExtra("shop_status", resultBean.getStore_status()));
                }
                break;
            case R.id.rl_dzgl:// 推送轮盘
                startActivity(new Intent(getActivity(), XunPanTuiSongActivity.class));
                break;
            case R.id.rl_sprz: // 商铺认证
                if (resultBean.getStore_status().equals("1")) {//店铺管理

                    startActivity(new Intent(getActivity(), StoreManagementActivity.class));
                } else if (resultBean.getStore_status().equals("2")) {
                    CusToast.showToast("店铺审核中");
                    return;
                } else if (resultBean.getStore_status().equals("3")) {// 认证失败
                    startActivity(new Intent(getActivity(), ShopRzFailedActivity.class)
                            .putExtra(Contacts.RZ_TYPE, 0));
                } else if (resultBean.getStore_status().equals("4")) {// 认证
                    startActivity(new Intent(getActivity(), ShopAuthenticationActivity.class));
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
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                upGetMessageData();
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
                    CusToast.showToast("请填写昵称");
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
                    CusToast.showToast(msg);
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
