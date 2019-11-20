package com.tem.gettogether.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.ServiceProviderAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.bean.QiuGouListBean;
import com.tem.gettogether.bean.ServiceProviderBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_service_provider)

public class ServiceProviderActivity extends BaseActivity {

    @ViewInject(R.id.mRecyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.back_icon)
    private ImageView back_icon;

    private LinearLayoutManager linearLayoutManager;
    private List<ServiceProviderBean.ResultEntity> serviceProviderBeans = new ArrayList<>();
    private ServiceProviderAdapter mServiceProviderAdapter;
    private int currentPage = 1;
    private boolean isLoadMore;
    @Override
    protected void initData() {
        getData(1,false);
    }

    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        initRefresh();
        initDatas();
    }

    @Event(value = {R.id.back_icon})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.back_icon:
                finish();
                break;
        }
    }

    private void getData(final int currentPage , final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getLanguageString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
            map.put("page", currentPage);
        }
        showDialog();
        XUtil.Post("http://www.jsmtgou.com/jushangmatou/index.php/Api/Goods/ftrade_union",map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ServiceProviderBean serviceProviderBean = gson.fromJson(result, ServiceProviderBean.class);
                        if (!isLoadMore) {
                            serviceProviderBeans.removeAll(serviceProviderBeans);
                            serviceProviderBeans.addAll(serviceProviderBean.getResult());
                            mServiceProviderAdapter.notifyDataSetChanged();
                        } else {
                            if(serviceProviderBean.getResult().size()>0) {
                                serviceProviderBeans.addAll(serviceProviderBean.getResult());
                                mServiceProviderAdapter.notifyDataSetChanged();
                            }else{
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                            }
                        }
                    } else {
                        CusToast.showToast(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                closeDialog();
                super.onError(ex, isOnCallback);
            }

            @Override
            public void onFinished() {
                closeDialog();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
                super.onFinished();
            }

        });
    }

    private void initDatas() {
        mServiceProviderAdapter = new ServiceProviderAdapter(getContext(), serviceProviderBeans);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mServiceProviderAdapter);
        mServiceProviderAdapter.setOnClickItem(new ServiceProviderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = getIntent();
                intent.putExtra("companyName", serviceProviderBeans.get(position).getCompany_name());
                intent.putExtra("companyId", serviceProviderBeans.get(position).getCompanyid());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setPullDownStr(getString(R.string.pull_down_refresh));
        headerView.setReleaseRefreshStr(getString(R.string.release_refresh));
        headerView.setRefreshingStr(getString(R.string.refreshing));
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                currentPage = 1;
                getData(currentPage,false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                getData(currentPage,true);
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
            }
        });
    }

    public void clearList(List<ServiceProviderBean.ResultEntity> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

}
