package com.tem.gettogether.activity.my;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.BuyingManagementAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.QiuGouListBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
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

@ContentView(R.layout.activity_buying_managerment)
public class BuyingManagementActivity extends BaseActivity implements BuyingManagementAdapter.ItemClickListener {
    @ViewInject(R.id.sell_RecyclerView)
    private ListView sell_RecyclerView;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private BuyingManagementAdapter mHomeBuyAdapter;
    private List<QiuGouListBean.ResultBean> homeDataBean = new ArrayList<>();

    private int currentPage = 1;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("求购管理");
        setData();
        initDatas(1, false);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void initDatas(final int currentPage, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", currentPage);
        map.put("user_id",SharedPreferencesUtils.getString(this,BaseConstant.SPConstant.USERID,""));
        map.put("list_row",10);
        showDialog();
        XUtil.Post(URLConstant.BUY_MANAGER, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.d("chenshichun","======求购管理====="+jsonObject.optString("result"));

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (!isLoadMore) {
                            if(jsonObject.optString("result").equals("null")){
                                homeDataBean.removeAll(homeDataBean);
                                mHomeBuyAdapter.notifyDataSetChanged();
                            }else {
                                homeDataBean.removeAll(homeDataBean);
                                homeDataBean.addAll(gson.fromJson(result, QiuGouListBean.class).getResult());
                                mHomeBuyAdapter.notifyDataSetChanged();
                            }

                        } else {
                            if(gson.fromJson(result, QiuGouListBean.class).getResult().size()>0) {
                                homeDataBean.addAll(gson.fromJson(result, QiuGouListBean.class).getResult());
                                mHomeBuyAdapter.notifyDataSetChanged();
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


    private void deleteData(String trade_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("trade_id", trade_id);
        showDialog();
        XUtil.Post(URLConstant.BUYING_MANAGEMENT_DELETE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        initDatas(1, false);
                        mHomeBuyAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private void setData() {
        mHomeBuyAdapter = new BuyingManagementAdapter(getContext(), homeDataBean, R.layout.item_cehua);
        sell_RecyclerView.setAdapter(mHomeBuyAdapter);
        mHomeBuyAdapter.setOnItemClickListener(BuyingManagementActivity.this);
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
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

    @Override
    public void ItemClickListener(View view, int position) {

    }

    @Override
    public void ItemDeleteClickListener(View view, int position) {
        deleteData(homeDataBean.get(position).getTrade_id());
    }
}
