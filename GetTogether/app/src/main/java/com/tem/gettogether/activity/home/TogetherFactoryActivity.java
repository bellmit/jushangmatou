package com.tem.gettogether.activity.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.TogetherFactoryAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeLianMengBean;
import com.tem.gettogether.bean.HotSouSuoBean;
import com.tem.gettogether.bean.SouSuoLSBean;
import com.tem.gettogether.bean.TogetherFactoryBean;
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
 * date : 2019/11/18 15:50 
 */

@ContentView(R.layout.activity_together_factory)
public class TogetherFactoryActivity extends BaseActivity {
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
    @ViewInject(R.id.recy_hot)
    private RecyclerView recy_sous_ls;
    @ViewInject(R.id.iv_remove)
    private ImageView iv_remove;
    private TogetherFactoryAdapter mTogetherFactoryAdapter;
    private List<TogetherFactoryBean.ResultBean> homeDataBean = new ArrayList<>();
    private int currentPage = 1;
    private LocationManager locationManager;
    private double lat, lng;
    private final static int LOCATION_CODE = 1111;

    @Override
    protected void initData() {
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams();
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        tv_title.setText(R.string.together_facatory);

        // 获取位置服务
        String serviceName = Context.LOCATION_SERVICE;
        // 调用getSystemService()方法来获取LocationManager对象
        locationManager = (LocationManager) getSystemService(serviceName);

        // 指定LocationManager的定位方法
        String provider = LocationManager.GPS_PROVIDER;
        // 调用getLastKnownLocation()方法获取当前的位置信息
        //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
        } else {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null) {
                CusToast.showToast(R.string.turn_on_mobile_positioning);
                return;
            }
            //获取纬度
            lat = location.getLatitude();
            //获取经度
            lng = location.getLongitude();
        }
        initDatas(1, true, false);
        initRefresh();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意。
                    // 执形我们想要的操作
                    String provider = LocationManager.GPS_PROVIDER;
                    // 调用getLastKnownLocation()方法获取当前的位置信息
                    //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        //请求权限
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
                    } else {
                        Location location = locationManager.getLastKnownLocation(provider);
                        if (location == null) {
                            CusToast.showToast(R.string.turn_on_mobile_positioning);
                            return;
                        }
                        //获取纬度
                        lat = location.getLatitude();
                        //获取经度
                        lng = location.getLongitude();
                    }

                    initDatas(1, false, false);
                    currentPage = 1;
                } else {
                    finish();
                }
            }
        }
    }

    @Override
    protected void initView() {
    }

    @Event(value = {R.id.rl_close, R.id.rl_search, R.id.iv_remove,R.id.et_sousuo})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_search:
                startActivity(new Intent(this, TogetherFactoryHistoryActivity.class));
                break;
            case R.id.et_sousuo:
                startActivity(new Intent(this, TogetherFactoryHistoryActivity.class));
                break;
        }
    }

    private void setData() {
        mTogetherFactoryAdapter = new TogetherFactoryAdapter(getContext(), homeDataBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mTogetherFactoryAdapter);
    }




    private void initDatas(final int currentPage, final boolean isNormal, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getLanguageString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
            map.put("page", currentPage);
        }
        map.put("lat", lat);
        map.put("lng", lng);
        showDialog();
        XUtil.Post(URLConstant.FACTORY_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "---聚工厂--" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (isNormal) {
                            homeDataBean = gson.fromJson(result, TogetherFactoryBean.class).getResult();
                            if (homeDataBean.size() == 0) {
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                ll_empty.setVisibility(View.GONE);
                                setData();
                            }
                        } else {
                            if (isLoadMore) {

                                if (gson.fromJson(result, HomeLianMengBean.class).getResult().size() > 0) {
                                    homeDataBean.addAll(gson.fromJson(result, TogetherFactoryBean.class).getResult());
                                    mTogetherFactoryAdapter.notifyDataSetChanged();
                                } else {
                                    CusToast.showToast(getResources().getText(R.string.no_more_data));
                                }
                            } else {
                                if (gson.fromJson(result, HomeLianMengBean.class).getResult().size() == 0) {
                                    ll_empty.setVisibility(View.VISIBLE);
                                } else {
                                    ll_empty.setVisibility(View.GONE);
                                }
                                homeDataBean.clear();
                                homeDataBean.addAll(gson.fromJson(result, TogetherFactoryBean.class).getResult());
                                mTogetherFactoryAdapter.notifyDataSetChanged();
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
