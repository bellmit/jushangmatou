package com.tem.gettogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.MyShopActivity;
import com.tem.gettogether.activity.my.CorporateInformationActivity;
import com.tem.gettogether.activity.my.FansActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.XunPanTuiSongActivity;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.utils.Contacts;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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

    private BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
    }

    @Event(value = {R.id.tv_setting, R.id.rl_sprz, R.id.tv_name, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,
            R.id.tv_dfh, R.id.tv_dsh, R.id.rl_my_message, R.id.rl_dzgl, R.id.rl_ksbh, R.id.rl_zxkf, R.id.rl_tdyj,R.id.rl_gywm}, type = View.OnClickListener.class)
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
                startActivity(new Intent(getActivity(), CorporateInformationActivity.class).putExtra(Contacts.PERSION_ENTERPRISE_INFORMATION,0));
                break;
            case R.id.rl_ksbh:// 会员信息
                break;
            case R.id.rl_dzgl:// 推送轮盘
                startActivity(new Intent(getActivity(), XunPanTuiSongActivity.class));
                break;
            case R.id.rl_sprz: // 商铺认证
                startActivity(new Intent(getActivity(), ShopAuthenticationActivity.class));
//                startActivity(new Intent(getActivity(), MyShopActivity.class));
                break;
            case R.id.rl_zxkf:// 在线客服
                break;
            case R.id.rl_tdyj:// 提点意见
                break;
            case R.id.rl_gywm:// 关于我们
                break;
        }
    }
}