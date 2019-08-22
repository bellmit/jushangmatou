package com.tem.gettogether.activity.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.caigou.CaiGouOrderAllFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderFaHuoFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderJieKuanFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderShouHuoFragment;
import com.tem.gettogether.fragment.caigou.CaiGouOrderWangChengFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_order)
public class CaiGouShangNewOrderActivity extends BaseActivity {
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
        list_fragment.add(new CaiGouOrderAllFragment());
        list_fragment.add(new CaiGouOrderFaHuoFragment());
        list_fragment.add(new CaiGouOrderShouHuoFragment());
        list_fragment.add(new CaiGouOrderJieKuanFragment());
        list_fragment.add(new CaiGouOrderWangChengFragment());
        //ViewPager的适配器，获得Fragment管理器
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
        //将TabLayout和ViewPager绑定在一起，一个动另一个也会跟着动
        tabLayout.setupWithViewPager(viewPager);
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
}
