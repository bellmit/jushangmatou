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
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.ProductManagmentFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_product_management)
public class ProductManagmentActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.myTab)
    private TabLayout myTab;
    @ViewInject(R.id.myView)
    private ViewPager myView;
    private List<String> myTitle;
    private List<Fragment> myFragment;
    private  FragmentPagerAdapter fragmentPagerAdapter;
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("产品管理");
        initDatas();
        initViews();
    }

    @Override
    protected void initView() {
        myTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Intent intent = new Intent("REFRESH_DATA");
                intent.putExtra("page", tab.getPosition());
                sendBroadcast(intent);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initDatas() {
        myTitle = new ArrayList<>();
        String[] string = getResources().getStringArray(R.array.test2);
        for (int i = 0; i < string.length; i++) {
            myTitle.add(string[i]);
        }

        myFragment = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ProductManagmentFragment fragment = new ProductManagmentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("page", i);
            fragment.setArguments(bundle);
            myFragment.add(fragment);
        }
    }

    private void initViews() {
        //预加载
        myView.setOffscreenPageLimit(myFragment.size());
        //适配器（容器都需要适配器）
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        };
        myView.setAdapter(fragmentPagerAdapter);
        myTab.setupWithViewPager(myView);
    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }
}
