package com.jsmt.developer.activity.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.adapter.HomeHotSellSecondAdapter;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.HomeHotSellBean;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

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

@ContentView(R.layout.activity_hot_sell)
public class HomeHotSellActivity extends BaseActivity {

    @ViewInject(R.id.sell_RecyclerView)
    private RecyclerView sell_RecyclerView;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private HomeHotSellSecondAdapter mHomeHotSellSecondAdapter;
    private List<HomeHotSellBean.ResultEntity> homeDataBean = new ArrayList<>();
    private int currentPage = 1;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getResources().getText(R.string.waimaorexiao));
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
        String yuyan = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
            map.put("page", currentPage);
        }
        showDialog();
        XUtil.Post(URLConstant.HONEHOTSELLDATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (!isLoadMore) {
                            homeDataBean = gson.fromJson(result, HomeHotSellBean.class).getResult();
                            setData();
                        } else {
                            homeDataBean.addAll(gson.fromJson(result, HomeHotSellBean.class).getResult());
                            mHomeHotSellSecondAdapter.notifyDataSetChanged();
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
        mHomeHotSellSecondAdapter = new HomeHotSellSecondAdapter(getContext(), homeDataBean);
        sell_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sell_RecyclerView.setAdapter(mHomeHotSellSecondAdapter);
    }

    private void initRefresh() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                initDatas(1, false);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, true);
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
}
