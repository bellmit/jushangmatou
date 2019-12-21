package com.tem.gettogether.activity.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.VisitorAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.FansDataBean;
import com.tem.gettogether.bean.VisitorBean;
import com.tem.gettogether.rongyun.RongTalk;
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

@ContentView(R.layout.activity_visitor)
public class VisitorActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.visitor_count)
    private TextView visitor_count;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    private VisitorAdapter mVisitorAdapter;
    List<VisitorBean.ResultBean.VsBean> mVisitorBean = new ArrayList<>();
    private int currentPage=1;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        tv_title.setText(getResources().getText(R.string.visitor));
        getVisiterData(1, false);
        initRefresh();
    }

    @Override
    protected void initView() {
        mVisitorAdapter = new VisitorAdapter(getContext(), mVisitorBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mVisitorAdapter);
        mVisitorAdapter.setOnClickItem(new VisitorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int position2) {
                RongTalk.doConnection(VisitorActivity.this, SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0")
                        , mVisitorBean.get(position).getVisiters().get(position2).getUser_id(), mVisitorBean.get(position).getVisiters().get(position2).getNickname(),
                        mVisitorBean.get(position).getVisiters().get(position2).getHead_pic(), null,null);
            }
        });
    }

    private void getVisiterData(int currentPage, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
        map.put("page", currentPage);
        map.put("list_row", 10);
        Log.d("chenshichun","======currentPage====="+currentPage);
        showDialog();
        XUtil.Post(URLConstant.VISITOR_DATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====访客===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (!isLoadMore) {
                            visitor_count.setText(gson.fromJson(result, VisitorBean.class).getResult().getCount());
                            mVisitorBean.removeAll(mVisitorBean);
                            if(gson.fromJson(result, VisitorBean.class).getResult().getVs()!=null)
                            mVisitorBean.addAll(gson.fromJson(result, VisitorBean.class).getResult().getVs());
                            mVisitorAdapter.notifyDataSetChanged();
                            if (gson.fromJson(result, VisitorBean.class).getResult().getCount().equals("0")) {
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                ll_empty.setVisibility(View.GONE);
                            }
                        } else {
                            if (gson.fromJson(result, VisitorBean.class).getResult().getVs() != null && gson.fromJson(result, VisitorBean.class).getResult().getVs().size() > 0) {
                                mVisitorBean.addAll(gson.fromJson(result, VisitorBean.class).getResult().getVs());
                                mVisitorAdapter.notifyDataSetChanged();
                            } else {
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
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setPullDownStr(getString(R.string.pull_down_refresh));
        headerView.setReleaseRefreshStr(getString(R.string.release_refresh));
        headerView.setRefreshingStr(getString(R.string.refreshing));
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                currentPage = 1;
                getVisiterData(currentPage, false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                getVisiterData(currentPage, true);
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

