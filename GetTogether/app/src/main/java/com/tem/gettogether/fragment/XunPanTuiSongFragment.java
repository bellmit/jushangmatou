package com.tem.gettogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_xpts)
public class XunPanTuiSongFragment extends BaseFragment {
    @ViewInject(R.id.rl_close)
    RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    TextView tv_title_right;

    @ViewInject(R.id.myTab)
    private TabLayout myTab;
    @ViewInject(R.id.myView)
    private ViewPager myView;
    private List<String> myTitle;
    private List<Fragment> myFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_close.setVisibility(View.GONE);
        tv_title.setText("询盘推送");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("查看会员权限");
        initDatas();
        initViews();
    }

    private void initDatas() {
        myTitle = new ArrayList<>();
        String[] string = getResources().getStringArray(R.array.test1);
        for (int i = 0; i < string.length; i++) {
            myTitle.add(string[i]);
        }
        myFragment = new ArrayList<>();
        myFragment.add(new XunPanFragment());
        myFragment.add(new XunPanFragment());

    }

    private void initViews() {
        //预加载
        myView.setOffscreenPageLimit(myFragment.size());
        //适配器（容器都需要适配器）
        myView.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
    }
}
