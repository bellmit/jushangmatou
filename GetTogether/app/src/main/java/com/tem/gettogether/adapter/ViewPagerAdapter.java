package com.tem.gettogether.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;   //fragment列表
    private List<String> titles;       //tab名的列表

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> data, List<String> titles) {
        super(fm);
        this.mList = data;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //根据下标获取指定标题的名字
        return titles.get(position%mList.size());
    }
}
