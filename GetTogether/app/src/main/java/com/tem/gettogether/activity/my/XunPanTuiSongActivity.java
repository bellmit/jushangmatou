package com.tem.gettogether.activity.my;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.XunPanFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_xpts)
public class XunPanTuiSongActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.myTab)
    private TabLayout myTab;
    @ViewInject(R.id.myView)
    private ViewPager myView;
    private List<String> myTitle;
    private List<Fragment>myFragment;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("询盘推送");
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
        myFragment.add(new XunPanFragment());
        myFragment.add(new XunPanFragment());

    }

    private void initViews(){
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
    }
}
