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
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.CaiGouShangOrderAdapter;
import com.tem.gettogether.adapter.GongYingShangOrderAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyOrderdataBean;
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

@ContentView(R.layout.fragment_new_my_order)
public class GongYingShangOrderFragment extends BaseFragment {
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    private GongYingShangOrderAdapter mOrderAdapter;
    private List<MyOrderdataBean.ResultBean> resultBeans = new ArrayList<>();
    private int currentPage = 1;
    private BaseActivity baseActivity;
    private int mTab = 0;

    public static GongYingShangOrderFragment getInstance(int tab) {
        GongYingShangOrderFragment fragment = new GongYingShangOrderFragment();
        fragment.setArguments(setArguments(tab));
        return fragment;
    }

    public static Bundle setArguments(int tab) {
        Bundle bundle = new Bundle();
        bundle.putInt("tab", tab);
        return bundle;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.d("chenshichun","=======onCreateAnimation====");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return x.view().inject(this, inflater, container);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        baseActivity = (BaseActivity) getActivity();
        loadData();
        initDatas(1, true, false);
        initRefresh();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ORDER_REFRESH_DATA");
        //注册广播接收
        getContext().registerReceiver(new MyReceiver01(), filter);
//        ll_empty.setVisibility(View.VISIBLE);
        super.onActivityCreated(savedInstanceState);
    }

    private void loadData() {
        mTab = getArguments().getInt("tab");
    }

    private void initDatas(final int currentPage, final boolean isNormal, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        map.put("role_type", 1);
        Log.d("chenshichun","===========mTab  "+mTab);
        if (mTab == 0) {// 全部
            map.put("type", "1");
        } else if (mTab == 1) {// 待发货
            map.put("type", "WAITSEND");
        } else if (mTab == 2) {// 待收货
            map.put("type", "WAITRECEIVE");
        } else if (mTab == 3) {// 待结款
            map.put("type", "WAITCCOMMENT");
        } else if (mTab == 4) {// 已完成
            map.put("type", "FINISH");
        }
        map.put("page", currentPage);
        map.put("list_row", 10);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.DINGDAN_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.d("chenshichun", "=======供应商订单====" + result);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (isNormal) {
                            resultBeans = gson.fromJson(result, MyOrderdataBean.class).getResult();
                            if (resultBeans.size() > 0) {
                                ll_empty.setVisibility(View.GONE);
                            }
                            setData();
                        } else {
                            if (isLoadMore) {

                                if (gson.fromJson(result, MyOrderdataBean.class).getResult().size() > 0) {
                                    resultBeans.addAll(gson.fromJson(result, MyOrderdataBean.class).getResult());
                                    mOrderAdapter.notifyDataSetChanged();
                                }

                            } else {
                                if(jsonObject.optString("result").equals("")){// 刷新没数据
                                    resultBeans.removeAll(resultBeans);
                                    mOrderAdapter.notifyDataSetChanged();
                                }else {
                                    Log.d("chenshichun","========有数据===");
                                    resultBeans.removeAll(resultBeans);
                                    resultBeans.addAll(gson.fromJson(result, MyOrderdataBean.class).getResult());
                                    mOrderAdapter.notifyDataSetChanged();
                                }
                                Log.d("chenshichun","========有数据size==="+resultBeans.size());
                                if (resultBeans.size() > 0) {
                                    ll_empty.setVisibility(View.GONE);
                                }else{
                                    ll_empty.setVisibility(View.VISIBLE);
                                }
                            }
                            Log.d("chenshichun","=======notifyDataSetChanged====");
                            mOrderAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private void confirmSend(String Url, String orderId) {// 确认发货
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        map.put("order_id", orderId);
        baseActivity.showDialog();
        XUtil.Post(Url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        initDatas(1, false, false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private void setData() {
        mOrderAdapter = new GongYingShangOrderAdapter(getContext(), resultBeans, mTab);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mOrderAdapter);
        mOrderAdapter.setOnClickItem(new GongYingShangOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("chenshichun", "========getOrder_id===  " + resultBeans.get(position).getOrder_id());
                if (resultBeans.get(position).getOrder_status_code().equals("WAITSEND")) {
                    confirmSend(URLConstant.CONFIRM_SEND, resultBeans.get(position).getOrder_id());// 确认发货
                } else if (resultBeans.get(position).getOrder_status_code().equals("WAITCCOMMENT")) {
                    confirmSend(URLConstant.CONFIRM_GET_MONEY, resultBeans.get(position).getOrder_id());// 确认结款
                }
            }
        });
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
                initDatas(1, false, false);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, false, true);
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

    public static int currentRandom=-1;
    public class MyReceiver01 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取广播的名字
            String action = intent.getAction();
            if ("ORDER_REFRESH_DATA".equals(action)) {
                mTab = intent.getIntExtra("page", 0);
//                int random = intent.getIntExtra("random",0);
//                if(currentRandom != random) {
                    initDatas(1, false, false);
//                    currentRandom = random;
//                }
            }
        }
    }

}
