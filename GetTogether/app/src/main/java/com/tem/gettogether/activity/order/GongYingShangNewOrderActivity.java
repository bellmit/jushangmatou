package com.tem.gettogether.activity.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.CgsAuthenticationActivity;
import com.tem.gettogether.activity.my.shopauthentication.ShopAuthenticationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.caigou.CaiGouOrderAllFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderFaHuoFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderJieKuanFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderShouHuoFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderWangChengFragment;
import com.tem.gettogether.fragment.gongying.GongYingOrderAllFragment;
import com.tem.gettogether.fragment.gongying.GongYingOrderFaHuoFragment;
import com.tem.gettogether.fragment.gongying.GongYingOrderJieKuanFragment;
import com.tem.gettogether.fragment.gongying.GongYingOrderShouHuoFragment;
import com.tem.gettogether.fragment.gongying.GongYingOrderWangChengFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_order)
public class GongYingShangNewOrderActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ps_tab)
    private TabLayout tabLayout;
    @ViewInject(R.id.vp_client)
    private ViewPager viewPager;
    private ArrayList<String> mList_title;
    private List<Fragment> list_fragment;
    private String mTabId;
    private int position = 0;
    private String[] titles;
    private MyAdapter adapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("我的订单");
        titles = new String[]{getResources().getString(R.string.all), "待发货", "待收货", getResources().getString(R.string.yijiekuan), getResources().getString(R.string.complete)};
        list_fragment = new ArrayList<>();
        list_fragment.add(new GongYingOrderAllFragment());
        list_fragment.add(new GongYingOrderFaHuoFragment());
        list_fragment.add(new GongYingOrderShouHuoFragment());
        list_fragment.add(new GongYingOrderJieKuanFragment());
        list_fragment.add(new GongYingOrderWangChengFragment());
        //ViewPager的适配器，获得Fragment管理器
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager绑定在一起，一个动另一个也会跟着动
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        Intent intent = new Intent("GONGYING_ORDER_ALL_DATA");
                        sendBroadcast(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent("GONGYING_ORDER_FAHUO_DATA");
                        sendBroadcast(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent("GONGYING_ORDER_SHOUHUO_DATA");
                        sendBroadcast(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent("GONGYING_ORDER_JIEKUAN_DATA");
                        sendBroadcast(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent("GONGYING_ORDER_WANGCHENG_DATA");
                        sendBroadcast(intent4);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        String tabType = getIntent().getStringExtra("tabType");

        if (tabType != null && !tabType.equals("")) {
            viewPager.setCurrentItem(Integer.parseInt(tabType));
        }
    }

    @Override
    protected void initView() {

    }


    //创建Fragment的适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //获得每个页面的下标
        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        //获得List的大小
        @Override
        public int getCount() {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
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
