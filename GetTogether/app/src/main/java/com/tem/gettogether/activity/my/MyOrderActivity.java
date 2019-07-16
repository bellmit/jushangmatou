package com.tem.gettogether.activity.my;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.MyOrderFragment;
import com.tem.gettogether.utils.UiUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_order)
public class MyOrderActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ps_tab)
    private TabLayout ps_tab;
    @ViewInject(R.id.vp_client)
    private ViewPager vp_client;
    private ArrayList<String> mList_title;
    private List<Fragment> list_fragment;
    private OrderAdapter mAdapter;
    private String mTabId;
    private int position = 0;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initFragmentTitle();
        initData();
        initView();

    }



    private void initFragmentTitle() {
        titles = new String[]{getResources().getString(R.string.all),getResources().getString(R.string.wait_h),getResources().getString(R.string.wait_f),getResources().getString(R.string.wait_s),getResources().getString(R.string.complete)};
        mList_title = new ArrayList<>();
        list_fragment = new ArrayList<>();
        mList_title.add(getResources().getString(R.string.all));
        mList_title.add(getResources().getString(R.string.wait_h));
        mList_title.add(getResources().getString(R.string.wait_f));
        mList_title.add(getResources().getString(R.string.wait_s));
        mList_title.add(getResources().getString(R.string.complete));

        for (int a = 0; a < titles.length; a++) {
            ps_tab.addTab(ps_tab.newTab());
            ps_tab.getTabAt(a).setText(titles[a]);
            MyOrderFragment mFragment = MyOrderFragment.getInstance(a);
            list_fragment.add(a, mFragment);
        }

    }
    @Override
    protected void initData() {
        tv_title.setText("我的订单");

    }

    @Override
    protected void initView() {
        mAdapter = new OrderAdapter(getSupportFragmentManager(),mList_title, list_fragment);
        vp_client.setOffscreenPageLimit(6);
        vp_client.setAdapter(mAdapter);
        ps_tab.setupWithViewPager(vp_client);
        if (!TextUtils.isEmpty(mTabId)) {
            int i = Integer.parseInt(mTabId);
            position = i;
            vp_client.setCurrentItem(i);
        }

        vp_client.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        String   tabType=getIntent().getStringExtra("tabType");

        if(tabType!=null&&!tabType.equals("")){
            vp_client.setCurrentItem(Integer.parseInt(tabType));
        }
//        else{
//            vp_client.setCurrentItem(0);
//
//        }

    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }
    private class OrderAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;     //fragment列表
        private List<String> list_Title;                              //tab名的列表

        public OrderAdapter(FragmentManager fm,  List<String> list_Title, List<Fragment> mFragments) {
            super(fm);
            this.list_Title = list_Title;

            this.list_fragment = mFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment != null && !list_fragment.isEmpty() ? list_fragment.size() : 0;
        }
//        @Override
//        public int getCount() {
//            return list_Title.size();
//        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }
    }
}
