package com.jsmt.developer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.adapter.ServiceProviderAdapter;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.bean.ServiceProviderBean;
import com.jsmt.developer.utils.ListUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        initRefresh();
    }

    @Event(value = {R.id.back_icon})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.back_icon:
                finish();
                break;
        }
    }

    private void getData(){
        showDialog();
        XUtil.Post("http://www.jsmtgou.com/jushangmatou/index.php/Api/Goods/ftrade_union",new MyCallBack<String>(){
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
                        clearList(serviceProviderBeans);
                        serviceProviderBeans = serviceProviderBean.getResult();

                        mServiceProviderAdapter = new ServiceProviderAdapter(getContext(),serviceProviderBeans);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(mServiceProviderAdapter);
                        mServiceProviderAdapter.setOnClickItem(new ServiceProviderAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = getIntent();
                                Log.d("chenshichun","---------intent--- "+intent);

                                Bundle bundle = intent.getExtras();
                                Log.d("chenshichun","---------bundle--- "+bundle);

                                bundle.putString("companyName", serviceProviderBeans.get(position).getCompany_name());
                                bundle.putString("companyId", serviceProviderBeans.get(position).getCompanyid());
                                intent.putExtras(bundle);
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        });

                    }else {
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
                super.onFinished();
            }

        });
    }

    private void initRefresh(){
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                refreshLayout.finishLoadmore();
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
