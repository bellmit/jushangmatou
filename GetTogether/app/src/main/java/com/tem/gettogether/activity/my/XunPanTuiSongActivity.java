package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.member.MemberCentreActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.fragment.XunPanFragment;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_xpts)
public class XunPanTuiSongActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.myTab)
    private TabLayout myTab;
    @ViewInject(R.id.myView)
    private ViewPager myView;
    @ViewInject(R.id.tv_title_right)
    TextView tv_title_right;
    private List<String> myTitle;
    private List<Fragment> myFragment;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.inquiry_push));
        if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7").equals("2")) {
            tv_title_right.setVisibility(View.VISIBLE);
            tv_title_right.setTextSize(14);
            tv_title_right.setText(getText(R.string.ckhyqx));
        }
        initDatas();
        initViews();
    }

    @Override
    protected void initView() {

    }

    private void initDatas() {
        myTitle = new ArrayList<>();
        String[] string = getResources().getStringArray(R.array.test1);
        for (int i = 0; i < string.length; i++) {
            myTitle.add(string[i]);
        }
        myFragment = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            XunPanFragment fragment = new XunPanFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("page", i);
            fragment.setArguments(bundle);
            myFragment.add(fragment);
        }
    }

    @Event(value = {R.id.tv_title_right, R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                startActivity(new Intent(XunPanTuiSongActivity.this, MemberCentreActivity.class));
                break;
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void initViews() {
        //预加载
        myView.setOffscreenPageLimit(myFragment.size());
        //适配器（容器都需要适配器）
        myView.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return myFragment.get(position);
            }

            @Override
            public int getCount() {
                return myFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return myTitle.get(position);
            }
        });
        myTab.setupWithViewPager(myView);

        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1 && !SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7").equals("2")) {
                    CusToast.showToast(getText(R.string.please_upgrade_the_premium_member_first));
                    startActivityForResult(new Intent(getContext(), MemberCentreActivity.class), 10000);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10000) {

            myTab.postDelayed(new Runnable() {
                @Override
                public void run() {
                    myTab.getTabAt(0).select();
                }
            }, 100);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
