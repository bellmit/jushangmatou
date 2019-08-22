package com.tem.gettogether.activity.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.CaiGouShangOrderFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_my_order)

public class CaiGouShangOrderActivity extends BaseActivity {
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
    private NoScrollViewPagerAdapter pagerAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("我的订单");
        initFragmentTitle();
        initView();
    }

    @Override
    protected void initView() {
        mAdapter = new OrderAdapter(getSupportFragmentManager(), mList_title, list_fragment);
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
        String tabType = getIntent().getStringExtra("tabType");

        if (tabType != null && !tabType.equals("")) {
            vp_client.setCurrentItem(Integer.parseInt(tabType));
        }
    }

    private void initFragmentTitle() {
        titles = new String[]{getResources().getString(R.string.all), "待发货", "待收货", getResources().getString(R.string.yijiekuan), getResources().getString(R.string.complete)};
        mList_title = new ArrayList<>();
        list_fragment = new ArrayList<>();
        mList_title.add(getResources().getString(R.string.all));
        mList_title.add("待发货");
        mList_title.add("待收货");
        mList_title.add(getResources().getString(R.string.yijiekuan));
        mList_title.add(getResources().getString(R.string.complete));

        for (int a = 0; a < titles.length; a++) {
            ps_tab.addTab(ps_tab.newTab());
            ps_tab.getTabAt(a).setText(titles[a]);
            CaiGouShangOrderFragment mFragment = CaiGouShangOrderFragment.getInstance(a);
            list_fragment.add(a, mFragment);
        }
/*        if (pagerAdapter == null) {
            pagerAdapter = new NoScrollViewPagerAdapter(getSupportFragmentManager());//继承FragmentPagerAdapter
        }
        vp_client.setAdapter(pagerAdapter);
        vp_client.setOffscreenPageLimit(1);
        ps_tab.setupWithViewPager(vp_client);
        pagerAdapter.setNewFragments();//这是我在FragmentPagerAdapter中定义的刷新方法，*/


        ps_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int math = (int) ((Math.random() * 100));
                Intent intent = new Intent("ORDER_REFRESH_DATA");
                intent.putExtra("page", tab.getPosition());
                intent.putExtra("random",math);
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
        FragmentManager mFragmentManager;

        public OrderAdapter(FragmentManager fm, List<String> list_Title, List<Fragment> mFragments) {
            super(fm);
            this.list_Title = list_Title;
            this.mFragmentManager = fm;
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

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position);
        }
    }

    private class NoScrollViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> tags;//标示fragment的tag
        private FragmentManager fragmentManager;

        NoScrollViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.tags = new ArrayList<>();
            this.fragmentManager = fm;
        }

        //Fragment的实例化工作
        @Override
        public Fragment getItem(int position) {
            return CaiGouShangOrderFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return mList_title.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList_title.get(position);
        }

        //返回PagerAdapter.POSITION_NONE保证调用notifyDataSetChanged刷新Fragment。
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;//必须返回的是POSITION_NONE，否则不会刷新

        }

        //这个就不说了
        private String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        //必须重写此方法，添加tag一一做记录
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            tags.add(makeFragmentName(container.getId(), getItemId(position)));
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            this.fragmentManager.beginTransaction().show(fragment).commit();
            return fragment;
        }

        //根据tag查找缓存的fragment，移除缓存的fragment，替换成新的
        public void setNewFragments() {
            if (this.tags != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                for (int i = 0; i < tags.size(); i++) {
                    fragmentTransaction.remove(fragmentManager.findFragmentByTag(tags.get(i)));
                }
                fragmentTransaction.commit();
                fragmentManager.executePendingTransactions();
                tags.clear();
            }
            notifyDataSetChanged();
        }
    }
}
