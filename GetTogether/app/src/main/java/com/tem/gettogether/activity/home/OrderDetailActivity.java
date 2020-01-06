package com.tem.gettogether.activity.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.OrderDetailAdapter;
import com.tem.gettogether.adapter.OrderDetailMoreAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeLianMengBean;
import com.tem.gettogether.bean.ShoppingXQBean;
import com.tem.gettogether.bean.ShoppingXQMoreBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/19 15:23 
 */
@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    private OrderDetailMoreAdapter mOrderDetailAdapter;
    private List<ShoppingXQMoreBean.ResultBean> mOrderBeans = new ArrayList<>();

    private int currentPage = 1;
    private String goodsId;
    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);

        goodsId = getIntent().getStringExtra("goods_id");
        tv_title.setText(getText(R.string.dingdanxiangqing));
        initDatas(1, true, false);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void setData() {
        mOrderDetailAdapter = new OrderDetailMoreAdapter(getContext(), mOrderBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mOrderDetailAdapter);
    }

    private void initDatas(final int currentPage, final boolean isNormal, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goodsId);
        map.put("page", currentPage);
        map.put("list_row", 10);

        showDialog();
        XUtil.Post(URLConstant.ORDER_DETAIL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun","---订单详情--"+result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (isNormal) {
                            mOrderBeans = gson.fromJson(result, ShoppingXQMoreBean.class).getResult();
                            if (mOrderBeans.size() == 0) {
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                ll_empty.setVisibility(View.GONE);
                                setData();
                            }
                        } else {
                            if (isLoadMore) {

                                if (gson.fromJson(result, HomeLianMengBean.class).getResult().size() > 0) {
                                    mOrderBeans.addAll(gson.fromJson(result, ShoppingXQMoreBean.class).getResult());
                                    mOrderDetailAdapter.notifyDataSetChanged();
                                } else {
                                    CusToast.showToast(getResources().getText(R.string.no_more_data));
                                }
                            } else {
                                if (gson.fromJson(result, HomeLianMengBean.class).getResult().size() == 0) {
                                    ll_empty.setVisibility(View.VISIBLE);
                                } else {
                                    ll_empty.setVisibility(View.GONE);
                                }
                                mOrderBeans.clear();
                                mOrderBeans.addAll(gson.fromJson(result, ShoppingXQMoreBean.class).getResult());
                                mOrderDetailAdapter.notifyDataSetChanged();
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
                closeDialog();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
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
                initDatas(1, false, false);
                currentPage = 1;
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, false, true);
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

}