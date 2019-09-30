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
import com.tem.gettogether.activity.cart.ShoppingCartActivity;
import com.tem.gettogether.activity.my.AddressGLActivity;
import com.tem.gettogether.activity.my.BuyingManagementActivity;
import com.tem.gettogether.activity.my.CgsAuthenticationActivity;
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.FansActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.SCconnectActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.ShopRzFailedActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.ZuJiActivity;
import com.tem.gettogether.activity.order.CaiGouShangNewOrderActivity;
import com.tem.gettogether.activity.order.CaiGouShangOrderActivity;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.fragment_persion_center_caigou)
public class PersionCenterCaiGouFragment extends BaseFragment {
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
    @ViewInject(R.id.iv_head)
    private CircularImage iv_head;
    private BaseActivity baseActivity;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.rz_status_tv)
    private TextView rz_status_tv;
    @ViewInject(R.id.tv_scNum)
    private TextView tv_scNum;
    @ViewInject(R.id.tv_zjNum)
    private TextView tv_zjNum;
    @ViewInject(R.id.tv_qb_num)
    private TextView tv_qb_num;
    private MyMessageBean.ResultBean resultBean = new MyMessageBean.ResultBean();
    private String is_verify;// 采购商认证状态

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
                        MyMessageBean myMessageBean = gson.fromJson(result, MyMessageBean.class);
                        resultBean = myMessageBean.getResult();
                        Glide.with(getActivity()).load(myMessageBean.getResult().getHead_pic() + "").asBitmap().error(R.mipmap.myy322x).centerCrop().into(new BitmapImageViewTarget(iv_head));
                        tv_name.setText(myMessageBean.getResult().getNickname());
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.NAME, myMessageBean.getResult().getNickname());
                        SharedPreferencesUtils.saveString(getContext(), BaseConstant.SPConstant.IS_VERIFY, myMessageBean.getResult().getIs_verify());
                        is_verify = myMessageBean.getResult().getIs_verify();
                        if(myMessageBean.getResult().getStore_collect_count()!=null) {
                            tv_scNum.setText(myMessageBean.getResult().getStore_collect_count());
                        }else{
                            tv_scNum.setText("0");
                        }

                        if(myMessageBean.getResult().getFprint_count()!=null) {
                            tv_zjNum.setText(myMessageBean.getResult().getFprint_count());
                        }else{
                            tv_zjNum.setText("0");
                        }

                        if(myMessageBean.getResult().getCart_goods()!=null) {
                            tv_qb_num.setText(myMessageBean.getResult().getCart_goods());
                        }else{
                            tv_qb_num.setText("0");
                        }

                        if (is_verify.equals("0")) {
                            rz_status_tv.setText(getText(R.string.pending_certification));
                        } else if (is_verify.equals("1")) {
                            rz_status_tv.setText(getText(R.string.verified_tv));
                        } else if (is_verify.equals("2")) {
                            rz_status_tv.setText(getText(R.string.rzsb));
                        } else if (is_verify.equals("3")) {
                            rz_status_tv.setText(getText(R.string.shz));
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

    @Event(value = {R.id.iv_head, R.id.tv_setting, R.id.rl_sprz, R.id.tv_name, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,
            R.id.tv_all, R.id.tv_dfk, R.id.tv_dfh, R.id.tv_dsh, R.id.rl_my_message, R.id.rl_dzgl, R.id.rl_ksbh, R.id.rl_zxkf, R.id.rl_tdyj, R.id.rl_gywm}, type = View.OnClickListener.class)
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
            case R.id.ll_scj:// 收藏夹
                startActivityForResult(new Intent(getActivity(), SCconnectActivity.class)
                        .putExtra("type", SCconnectActivity.NICKNAME_TYPE), SCconnectActivity.NICKNAME_TYPE);
                break;
            case R.id.ll_zj:// 足迹
                startActivity(new Intent(getActivity(), ZuJiActivity.class));
                break;
            case R.id.ll_qb:// 我的采购
                startActivity(new Intent(getActivity(), ShoppingCartActivity.class));
                break;
            case R.id.tv_all:// 全部
                startActivity(new Intent(getActivity(), CaiGouShangNewOrderActivity.class)
                        .putExtra("tabType", "0"));
                break;
            case R.id.tv_dfk:// 待收货
                startActivity(new Intent(getActivity(), CaiGouShangNewOrderActivity.class)
                        .putExtra("tabType", "1"));
                break;
            case R.id.tv_dfh:// 待发货
                startActivity(new Intent(getActivity(), CaiGouShangNewOrderActivity.class)
                        .putExtra("tabType", "2"));
                break;
            case R.id.tv_dsh:// 待结款
                startActivity(new Intent(getActivity(), CaiGouShangNewOrderActivity.class)
                        .putExtra("tabType", "3"));
                break;
            case R.id.rl_my_message:// 个人信息
                if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.IS_VERIFY, "0").equals("1")) {
                    startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 1));
                } else {
                    CusToast.showToast(getText(R.string.please_first_purchase_the_buyer));
                }
                break;
            case R.id.rl_ksbh:// 求购管理
                startActivity(new Intent(getActivity(), BuyingManagementActivity.class));
                break;
            case R.id.rl_dzgl:// 收货地址
                startActivity(new Intent(getActivity(), AddressGLActivity.class));
                break;
            case R.id.rl_sprz: // 采购商认证
                if (is_verify.equals("0")) {
                    startActivity(new Intent(getActivity(), CgsAuthenticationActivity.class));
                } else if (is_verify.equals("1")) {
                    CusToast.showToast(getText(R.string.certification_passed));
                } else if (is_verify.equals("2")) {
                    startActivity(new Intent(getActivity(), ShopRzFailedActivity.class).putExtra(Contacts.RZ_TYPE, 1));
                } else if (is_verify.equals("3")) {
                    CusToast.showToast(getText(R.string.shz));
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
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableOverScroll(false);
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

}
