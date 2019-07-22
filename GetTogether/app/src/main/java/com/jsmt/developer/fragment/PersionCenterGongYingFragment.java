package com.jsmt.developer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.activity.my.SettingActivity;
import com.jsmt.developer.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.fragment_persion_center_gongying)
public class PersionCenterGongYingFragment extends BaseFragment {

    @ViewInject(R.id.tv_setting)
    TextView tv_setting;
    @ViewInject(R.id.rl_sprz)
    private RelativeLayout rl_sprz;

    private BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
    }
    @Event(value = {R.id.tv_setting,R.id.rl_sprz}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()){
            case R.id.tv_setting:
                startActivity(new Intent(getActivity(),SettingActivity.class));
                break;
            case R.id.rl_sprz:
                startActivity(new Intent(getActivity(),ShopAuthenticationActivity.class));
                break;
        }
    }
}
