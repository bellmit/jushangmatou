package com.tem.gettogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.tem.gettogether.activity.my.BuyingManagementActivity;
import com.tem.gettogether.activity.my.CgsAuthenticationActivity;
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.ShopRzFailedActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.utils.Contacts;
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
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
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
                        Glide.with(getActivity()).load(myMessageBean.getResult().getHead_pic() + "").asBitmap().error(R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_head));
                        tv_name.setText(myMessageBean.getResult().getNickname());
                        is_verify = myMessageBean.getResult().getIs_verify();
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

    @Event(value = {R.id.tv_setting, R.id.rl_sprz, R.id.tv_name, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,
            R.id.tv_dfh, R.id.tv_dsh, R.id.rl_my_message, R.id.rl_dzgl, R.id.rl_ksbh, R.id.rl_zxkf, R.id.rl_tdyj, R.id.rl_gywm}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:// 设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_name:// 修改名字
                break;
            case R.id.ll_scj:// 收藏夹
                break;
            case R.id.ll_zj:// 足迹
                break;
            case R.id.ll_qb:// 我的采购
                break;
            case R.id.tv_all:// 全部
                break;
            case R.id.tv_dfk:// 已下单
                break;
            case R.id.tv_dfh:// 已收货
                break;
            case R.id.tv_dsh:// 已结款
                break;
            case R.id.rl_my_message:// 个人信息
                startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 1));
                break;
            case R.id.rl_ksbh:// 求购管理
                startActivity(new Intent(getActivity() , BuyingManagementActivity.class));
                break;
            case R.id.rl_dzgl:// 收货地址
                break;
            case R.id.rl_sprz: // 采购商认证
                if (is_verify.equals("0")) {
                    startActivity(new Intent(getActivity(), CgsAuthenticationActivity.class));
                } else if (is_verify.equals("1")) {
                    CusToast.showToast("认证通过");
                } else if (is_verify.equals("2")) {
                    startActivity(new Intent(getActivity(), ShopRzFailedActivity.class).putExtra(Contacts.RZ_TYPE, 1));
                } else if (is_verify.equals("3")) {
                    CusToast.showToast("审核中");
                }
                break;
            case R.id.rl_zxkf:// 在线客服
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
                refreshLayout.finishRefreshing();
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
}
