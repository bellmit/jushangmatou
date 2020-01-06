package com.tem.gettogether.activity.login.countrycode;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.adapter.CountryCodeAdapter;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.bean.CountryCodeBean;
import com.tem.gettogether.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/29 17:04 
 */
@ContentView(R.layout.activity_choose_country_code)
public class CountryCodeActivity extends BaseMvpActivity<CountryCodePresenter> implements CountryCodeContract.CountryCodeView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private CountryCodeAdapter mCountryCodeAdapter;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new CountryCodePresenter(getContext(), CountryCodeActivity.this);
        mPresenter.attachView(this);
        tv_title.setText(R.string.select_country_code);
        mPresenter.getCountryCode();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
    }

    @Override
    public void showLoading() {

    }

    @Event(value = {R.id.rl_close})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void countryCodeData(final List<CountryCodeBean.ResultBean.CountryBean> mDatas) {
        mCountryCodeAdapter = new CountryCodeAdapter(getContext(), mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mCountryCodeAdapter);
        mCountryCodeAdapter.setOnClickItem(new CountryCodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent();
                i.putExtra("COUNTRY_NAME", mDatas.get(position).getCountry_name());
                i.putExtra("MOBILE_CODE", mDatas.get(position).getMobile_code());
                setResult(3, i);
                finish();
            }
        });
    }
}
