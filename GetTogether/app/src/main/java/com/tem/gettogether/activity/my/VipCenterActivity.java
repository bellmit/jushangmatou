package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.entity.TabEntity;
import com.tem.gettogether.fragment.MemberClassificationFragment;
import com.tem.gettogether.fragment.MemberInformationFragment;
import com.tem.gettogether.fragment.RefundFragment;
import com.tem.gettogether.utils.AppManager;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.view.CircularImage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_vip_center)
public class VipCenterActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.tl_2)
    private CommonTabLayout tl_2;
    @ViewInject(R.id.myView)
    private ViewPager mViewPager;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.head_pic)
    private CircularImage head_pic;
    @ViewInject(R.id.nick_name)
    private TextView nick_name;
    @ViewInject(R.id.account)
    private TextView account;
    private String headPic;
    private String[] mTitles;
    private int[] mIconUnselectIds = {
            R.drawable.member_classification_icon, /*R.drawable.join_membership_icon,*/
            R.drawable.member_information_icon, R.drawable.refund_icon};
    private int[] mIconSelectIds = {
            R.drawable.member_classification_icon, /*R.drawable.join_membership_icon,*/
            R.drawable.member_information_icon, R.drawable.refund_icon};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String shopStatus;
    @Override
    protected void initData() {
        x.view().inject(this);
        AppManager.getAppManager().addActivity(this);

        headPic = getIntent().getStringExtra("head_pic");
        shopStatus = SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.SHOP_STATUS, "0");
        tv_title.setText(getResources().getString(R.string.membership_center));
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(getResources().getString(R.string.upgrade_membership));
        if (SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7").equals("2")) {
            tv_title_right.setVisibility(View.GONE);
        }

        Glide.with(getContext()).load(/*headPic*/SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.head_pic, headPic)).error(R.mipmap.myy322x).centerCrop().into(head_pic);
        nick_name.setText(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.NAME, ""));
        account.setText(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.MOBILEPHONE, ""));
        if(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7").equals("1")){// 普通会员
            mTitles = new String[]{getResources().getString(R.string.membership_classification),
                    getResources().getString(R.string.membership_information), getResources().getString(R.string.application_refund)};
            mFragments.add(new MemberClassificationFragment());
            mFragments.add(new MemberInformationFragment());
            mFragments.add(new RefundFragment());
        }else{
            mTitles = new String[]{getResources().getString(R.string.membership_classification),
                    getResources().getString(R.string.membership_information)};
            mFragments.add(new MemberClassificationFragment());
            mFragments.add(new MemberInformationFragment());
        }


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tl_2();
    }

    @Override
    protected void initView() {

    }

    private void tl_2() {
        tl_2.setTabData(mTabEntities);
        tl_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tl_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Event(value = {R.id.rl_close, R.id.tv_title_right}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_title_right:
                if(shopStatus.equals("1")) {
                    startActivity(new Intent(this, BuyMemberActivity.class));
                }else{
                    CusToast.showToast(getText(R.string.no_store_data));
                }
                break;
        }
    }
}
