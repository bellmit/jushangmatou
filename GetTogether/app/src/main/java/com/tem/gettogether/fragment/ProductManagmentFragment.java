package com.tem.gettogether.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.ProductManagmentAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ProductManagementBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.fragment_product_managment)
public class ProductManagmentFragment extends BaseFragment {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    private ProductManagmentAdapter mProductManagmentAdapter;
    private int currentPage = 1;
    private List<ProductManagementBean.ResultBean> mProductManagementBeans = new ArrayList<>();
    private int typePage;
    private int tap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        typePage = getArguments().getInt("page");
        if (typePage == 0) {//出售中商品列表
            tap = 1;
        } else if (typePage == 1) {//已下架商品列表
            tap = 0;
        } else {//表示待发布
            tap = 2;
        }
        initDatas(1, false);
        initRefresh();

        IntentFilter filter = new IntentFilter();
        filter.addAction("REFRESH_DATA");
        //注册广播接收
        getContext().registerReceiver(new MyReceiver01(), filter);
    }

    private void initDatas(final int currentPage, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        map.put("page", currentPage);
        Log.d("chenshichun","=====currentPage======"+currentPage);
        map.put("list_row", 10);
        map.put("tap", tap);
        XUtil.Post(URLConstant.PRODUCT_MANAGEMENT, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.d("chenshichun", "======产品管理=====" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (!isLoadMore) {
                            mProductManagementBeans = gson.fromJson(result, ProductManagementBean.class).getResult();
                            if (mProductManagementBeans.size() == 0) {
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                ll_empty.setVisibility(View.GONE);
                                setData();
                            }
                        } else {
                            if(gson.fromJson(result, ProductManagementBean.class).getResult()!=null&&gson.fromJson(result, ProductManagementBean.class).getResult().size()>0) {
                                mProductManagementBeans.addAll(gson.fromJson(result, ProductManagementBean.class).getResult());
                                mProductManagmentAdapter.notifyDataSetChanged();
                            }else{
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }

    private void operationalData(String goodsId, String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goodsId);
        XUtil.Post(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.d("chenshichun", "======商品操作=====" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        initDatas(1, false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }

    private void setData() {
        mProductManagmentAdapter = new ProductManagmentAdapter(getContext(), mProductManagementBeans, typePage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mProductManagmentAdapter);
        mProductManagmentAdapter.setOnClickView(new ProductManagmentAdapter.onClickView() {
            @Override
            public void setShelfClick(String id) {
                operationalData(id, URLConstant.SHELF_URL);
            }

            @Override
            public void setObtainedClick(String id) {
                operationalData(id, URLConstant.OBTAINED_URL);
            }

            @Override
            public void setEditClick(String id) {

            }

            @Override
            public void setDeleteClick(String id) {
                operationalData(id, URLConstant.DELETE_URL);
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
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                currentPage = 1;
                initDatas(currentPage, false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, true);
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

    public class MyReceiver01 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取广播的名字
            String action = intent.getAction();
            if ("REFRESH_DATA".equals(action)) {
                typePage = intent.getIntExtra("page", 0);
                if (typePage == 0) {//出售中商品列表Store/getPurchaseList
                    tap = 1;
                } else if (typePage == 1) {//已下架商品列表
                    tap = 0;
                } else {//表示待发布
                    tap = 2;
                }
                initDatas(1, false);
            }
        }
    }

}