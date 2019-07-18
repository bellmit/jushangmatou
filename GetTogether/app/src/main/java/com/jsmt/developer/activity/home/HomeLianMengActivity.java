package com.jsmt.developer.activity.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.adapter.HomeHotSellSecondAdapter;
import com.jsmt.developer.adapter.HomeLianMengSecondAdapter;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.HomeHotSellBean;
import com.jsmt.developer.bean.HomeLianMengBean;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_home_lianmeng)
public class HomeLianMengActivity extends BaseActivity {
    @ViewInject(R.id.sell_RecyclerView)
    private RecyclerView sell_RecyclerView;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private HomeLianMengSecondAdapter mHomeLianMengSecondAdapter;
    private List<HomeLianMengBean.ResultEntity> homeDataBean = new ArrayList<>();
    private int currentPage =1;
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getResources().getText(R.string.waimaolianmeng));
        initDatas(1,false);
        initRefresh();
    }

    @Override
    protected void initView() {

    }

    private void initDatas(final int currentPage, final boolean isLoadMore){
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
            map.put("page",currentPage);
        }
        showDialog();
        XUtil.Post(URLConstant.HONEALLIANCEDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        if(!isLoadMore) {
                            homeDataBean = gson.fromJson(result, HomeLianMengBean.class).getResult();
                            setData();
                        }else{
                            homeDataBean.addAll(gson.fromJson(result, HomeLianMengBean.class).getResult());
                            mHomeLianMengSecondAdapter.notifyDataSetChanged();
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


    private void setData(){
        mHomeLianMengSecondAdapter = new HomeLianMengSecondAdapter(getContext(), homeDataBean);
        sell_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sell_RecyclerView.setAdapter(mHomeLianMengSecondAdapter);
    }

    private void initRefresh(){
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                initDatas(1,false);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage,true);
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
