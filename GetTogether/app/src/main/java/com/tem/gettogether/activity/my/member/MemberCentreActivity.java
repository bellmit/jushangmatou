package com.tem.gettogether.activity.my.member;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.fragment.MemberCenterFragment;
import com.tem.gettogether.utils.AppManager;
import com.tem.gettogether.utils.PayManager;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.view.MyViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/18 14:35 
 */
@ContentView(R.layout.activity_member_centre)
public class MemberCentreActivity extends BaseMvpActivity<MemberCentrePresenter> implements MemberCentreContract.MemberCentreView, View.OnClickListener, View.OnLongClickListener {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.myViewPage)
    private MyViewPager myViewPage;
    @ViewInject(R.id.arrow_left)
    private ImageView arrow_left;
    @ViewInject(R.id.arrow_right)
    private ImageView arrow_right;
    @ViewInject(R.id.img)
    private ImageView img;
    private List<Fragment> myFragment;
    private List<String> myTitle;
    String userLever = "";

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.membership_center));
        StatusBarUtil.setTranslucentStatus(this);
        PayManager.getPayManager().addActivity(this);
        String yuyan = SharedPreferencesUtils.getLanguageString(getContext(), BaseConstant.SPConstant.language, "");
        if(yuyan.equals("zh")){
            img.setBackgroundResource(R.drawable.huiyuan_detail);
        }else if(yuyan.equals("en")){
            img.setBackgroundResource(R.drawable.huiyuan_detail_en);
        }

    }

    @Event(value = {R.id.rl_close, R.id.arrow_left, R.id.arrow_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.arrow_left:
                myViewPage.setCurrentItem(0);
                break;
            case R.id.arrow_right:
                myViewPage.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void initView() {
        mPresenter = new MemberCentrePresenter(getContext(), MemberCentreActivity.this);
        mPresenter.attachView(this);
        userLever = SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7");
        if (userLever.equals("2")) {
            arrow_left.setVisibility(View.GONE);
            arrow_right.setVisibility(View.GONE);
        }
        myTitle = new ArrayList<>();

        String[] string = getResources().getStringArray(R.array.test1);
        for (int i = 0; i < string.length; i++) {
            myTitle.add(string[i]);
        }

        myFragment = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MemberCenterFragment fragment = new MemberCenterFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("page", i);
            fragment.setArguments(bundle);
            myFragment.add(fragment);
        }

        //预加载
        myViewPage.setOffscreenPageLimit(myFragment.size());
        //适配器（容器都需要适配器）
        myViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        myViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        arrow_left.setVisibility(View.GONE);
                        arrow_right.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        arrow_left.setVisibility(View.VISIBLE);
                        arrow_right.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (userLever.equals("1")) {// 普通会员
            myViewPage.setCurrentItem(1);
            myViewPage.setScrollble(true);
        } else if (userLever.equals("2")) {
            myViewPage.setScrollble(false);
        } else {
            myViewPage.setScrollble(true);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
