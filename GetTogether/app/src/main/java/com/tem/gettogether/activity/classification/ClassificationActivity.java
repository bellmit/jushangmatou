package com.tem.gettogether.activity.classification;

import android.support.v7.widget.GridLayoutManager;
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
import com.tem.gettogether.adapter.ClassificationListAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ClassificationListBean;
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

@ContentView(R.layout.activity_classification_list)
public class ClassificationActivity extends BaseActivity {
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.sell_RecyclerView)
    private RecyclerView sell_RecyclerView;
    @ViewInject(R.id.tv_zonghe)
    private TextView tv_zonghe;
    @ViewInject(R.id.chengjiaocishu)
    private TextView chengjiaocishu;
    @ViewInject(R.id.huifulv)
    private TextView huifulv;
    @ViewInject(R.id.price_tv)
    private TextView price_tv;

    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private String classificationId;
    private ClassificationListAdapter mClassifcationListAdapter;
    List<ClassificationListBean.ResultBean> mClassificationListBeans = new ArrayList<>();
    private int currentPage;
    private String keyValue = "is_new";
    private String sort = "desc";
    private boolean isChengJiaoCiShuSort = false;
    private boolean isHuiFuLvSort = false;
    private boolean isJiaGeSort = false;
    private int type;
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getIntent().getStringExtra("classification_name"));
        classificationId = getIntent().getStringExtra("classification_id");
        type = getIntent().getIntExtra("classification_type",3);
        initViews();
        initDatas(1, classificationId, true, false);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    private void initViews() {
        mClassifcationListAdapter = new ClassificationListAdapter(getContext(), mClassificationListBeans);
        sell_RecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        sell_RecyclerView.setAdapter(mClassifcationListAdapter);
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
                initDatas(1, classificationId, false, false);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, classificationId, false, true);
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

    private void initDatas(int currentPage, String classificationId, final boolean isNormal, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
            map.put("id", classificationId);
            map.put("key", keyValue);
            map.put("sort", sort);
            map.put("page", currentPage);
            map.put("type",type);
        }
        showDialog();
        XUtil.Post(URLConstant.SEARCH_LIST_URL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.d("chenshichun","=====result======"+result);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (isNormal) {
                            mClassificationListBeans.removeAll(mClassificationListBeans);
                            mClassificationListBeans.addAll(gson.fromJson(result, ClassificationListBean.class).getResult());
                            mClassifcationListAdapter.notifyDataSetChanged();
                        } else {
                            if (isLoadMore) {
                                if (gson.fromJson(result, ClassificationListBean.class).getResult().size() > 0) {// 加载更多
                                    mClassificationListBeans.addAll(gson.fromJson(result, ClassificationListBean.class).getResult());
                                    mClassifcationListAdapter.notifyDataSetChanged();
                                }
                            } else {// 刷新
                                mClassificationListBeans.removeAll(mClassificationListBeans);
                                mClassificationListBeans.addAll(gson.fromJson(result, ClassificationListBean.class).getResult());
                                mClassifcationListAdapter.notifyDataSetChanged();
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
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }

    @Event(value = {R.id.rl_close, R.id.tv_zonghe, R.id.chengjiaocishu, R.id.huifulv, R.id.price_tv}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_zonghe:

                keyValue = "is_new";
                tv_zonghe.setTextColor(getResources().getColor(R.color.home_red));
                chengjiaocishu.setTextColor(getResources().getColor(R.color.black));
                huifulv.setTextColor(getResources().getColor(R.color.black));
                price_tv.setTextColor(getResources().getColor(R.color.black));

                break;
            case R.id.chengjiaocishu:
                tv_zonghe.setTextColor(getResources().getColor(R.color.black));
                chengjiaocishu.setTextColor(getResources().getColor(R.color.home_red));
                huifulv.setTextColor(getResources().getColor(R.color.black));
                price_tv.setTextColor(getResources().getColor(R.color.black));

                keyValue = "sales_sum";
                if (isChengJiaoCiShuSort) {
                    sort = "asc";
                } else {
                    sort = "desc";
                }
                isChengJiaoCiShuSort = !isChengJiaoCiShuSort;
                initDatas(1, classificationId, true, false);
                break;
            case R.id.huifulv:
                tv_zonghe.setTextColor(getResources().getColor(R.color.black));
                chengjiaocishu.setTextColor(getResources().getColor(R.color.black));
                huifulv.setTextColor(getResources().getColor(R.color.home_red));
                price_tv.setTextColor(getResources().getColor(R.color.black));
                keyValue = "is_recommend";
                if (isHuiFuLvSort) {
                    sort = "asc";
                } else {
                    sort = "desc";
                }
                isHuiFuLvSort = !isHuiFuLvSort;
                initDatas(1, classificationId, true, false);
                break;
            case R.id.price_tv:
                tv_zonghe.setTextColor(getResources().getColor(R.color.black));
                chengjiaocishu.setTextColor(getResources().getColor(R.color.black));
                huifulv.setTextColor(getResources().getColor(R.color.black));
                price_tv.setTextColor(getResources().getColor(R.color.home_red));
                keyValue = "shop_price";
                if (isJiaGeSort) {
                    sort = "asc";
                } else {
                    sort = "desc";
                }
                isJiaGeSort = !isJiaGeSort;
                initDatas(1, classificationId, true, false);
                break;
        }
    }

}
