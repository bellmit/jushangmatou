package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.fragment.XunPanFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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
    @ViewInject(R.id.head_view)
    private TextView head_view;
    @ViewInject(R.id.tv_title_right)
    TextView tv_title_right;
    private List<String> myTitle;
    private List<Fragment>myFragment;
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("询盘推送");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setTextSize(14);
        tv_title_right.setText("查看会员权限");
        head_view.setVisibility(View.GONE);
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
    @Event(value = {R.id.tv_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                startActivity(new Intent(XunPanTuiSongActivity.this, VipCenterActivity.class));
                break;
        }
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
