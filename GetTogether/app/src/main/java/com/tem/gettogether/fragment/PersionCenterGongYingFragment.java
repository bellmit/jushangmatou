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
import android.view.WindowManager;
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
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.FansActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.StoreManagementActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.VipCenterActivity;
import com.tem.gettogether.activity.my.VisitorActivity;
import com.tem.gettogether.activity.my.XunPanTuiSongActivity;
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
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    private BaseActivity baseActivity;
    private MyMessageBean.ResultBean resultBean = new MyMessageBean.ResultBean();
    private String userLever;
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
                        userLever = myMessageBean.getResult().getLevel();
                        if (resultBean.getStore_status() == 1) {
                            tv_shop_RZ.setText("店铺管理");
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

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }


    @Event(value = {R.id.tv_setting, R.id.rl_sprz, R.id.tv_name,R.id.tv_all, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,
            R.id.tv_dfh, R.id.tv_dsh, R.id.rl_my_message, R.id.rl_dzgl, R.id.rl_ksbh, R.id.rl_zxkf, R.id.rl_tdyj, R.id.rl_gywm}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:// 设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_name:// 修改名字
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
                break;
            case R.id.tv_dfk:// 已下单
                break;
            case R.id.tv_dfh:// 已收货
                break;
            case R.id.tv_dsh:// 已结款
                break;
            case R.id.rl_my_message:// 企业信息
                startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0));
                break;
            case R.id.rl_ksbh:// 会员信息
                startActivity(new Intent(getActivity(), VipCenterActivity.class));
                break;
            case R.id.rl_dzgl:// 推送轮盘
                startActivity(new Intent(getActivity(), XunPanTuiSongActivity.class));
                break;
            case R.id.rl_sprz: // 商铺认证
                if (resultBean.getStore_status() == 1) {//店铺管理
                    startActivity(new Intent(getActivity(), StoreManagementActivity.class));
                } else if (resultBean.getStore_status() == 2) {
                    CusToast.showToast("店铺审核中");
                    return;
                } else if (resultBean.getStore_status() == 0 || resultBean.getStore_status() == 3 || resultBean.getStore_status() == 4) {//店铺管理
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
