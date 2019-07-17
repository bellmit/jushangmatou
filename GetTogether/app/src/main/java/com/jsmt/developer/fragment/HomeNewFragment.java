package com.jsmt.developer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.adapter.HomeBuyAdapter;
import com.jsmt.developer.adapter.HomeHotSellAdapter;
import com.jsmt.developer.adapter.HomeLianMengAdapter;
import com.jsmt.developer.adapter.HomeXinPinAdapter;
import com.jsmt.developer.bean.HomeBuyBean;
import com.jsmt.developer.bean.HomeDataNewBean;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.activity.home.ShopActivity;
import com.jsmt.developer.activity.my.XeiYiH5Activity;
import com.jsmt.developer.adapter.HomeBottomCateAdapter;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.BaseFragment;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.HomeDataBean;
import com.jsmt.developer.bean.ServiceProviderBean;
import com.jsmt.developer.utils.GlideImageLoader;
import com.jsmt.developer.utils.ListUtils;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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

@ContentView(R.layout.fragment_new_home)
public class HomeNewFragment extends BaseFragment implements View.OnClickListener{

    @ViewInject(R.id.tv_YuYQH)
    private TextView tv_YuYQH;
    @ViewInject(R.id.tv_sousuo)
    private TextView tv_sousuo;
    @ViewInject(R.id.rl_saoyisao)
    private RelativeLayout rl_saoyisao;
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.buy_recyclerView)// 外贸求购
    private RecyclerView buy_recyclerView;
    @ViewInject(R.id.sell_recyclerView)
    private RecyclerView sell_recyclerView;// 外贸热销
    @ViewInject(R.id.lianmeng_recyclerView)
    private RecyclerView lianmeng_recyclerView;
    @ViewInject(R.id.xinpin_recyclerView)
    private RecyclerView xinpin_recyclerView;

    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private  BaseActivity baseActivity;

    private List<HomeDataNewBean.ResultEntity.AdEntity> adBeans=new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> bottomCateBeans=new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> homeBuyBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Order_pastedEntity> sellBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Trade_unionEntity> lianmengBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Ftrade_newEntity> xinpinBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Ftrade_newEntity> xinpinTotalBeans = new ArrayList<>();


    private HomeBottomCateAdapter mHomeBottomCateAdapter;
    private HomeBuyAdapter mHomeBuyAdapter;
    private HomeHotSellAdapter mHomeHotSellAdapter;
    private HomeLianMengAdapter mHomeLianMengAdapter;
    private HomeXinPinAdapter mHomeXinPinAdapter;

    private int currentPage =1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
        initView();
        initData(currentPage);
    }

    private void initView(){
        initRefresh();
    }


    private void initData(int currentPage){
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
            map.put("page",currentPage);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.HONEDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeDataNewBean homeDataBean=gson.fromJson(result,HomeDataNewBean.class);
                        adBeans=homeDataBean.getResult().getAd();
                        bottomCateBeans=homeDataBean.getResult().getBottom_cate();
                        homeBuyBeans = homeDataBean.getResult().getFtrade_buy();
                        sellBeans = homeDataBean.getResult().getOrder_pasted();
                        lianmengBeans = homeDataBean.getResult().getTrade_union();
                        xinpinBeans = homeDataBean.getResult().getFtrade_new();
                        setAllData();
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

    private void initRefreshData(int currentPage){
        Log.d("chenshichun","------currentPage "+currentPage);
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
            map.put("page",currentPage);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.HONEDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeDataNewBean homeDataBean=gson.fromJson(result,HomeDataNewBean.class);
                        xinpinBeans.addAll(homeDataBean.getResult().getFtrade_new());
                        mHomeXinPinAdapter.notifyDataSetChanged();
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

    private void setAllData(){
        setBanner();
        mHomeBottomCateAdapter = new HomeBottomCateAdapter(getContext(),bottomCateBeans);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(mHomeBottomCateAdapter);

        mHomeBuyAdapter = new HomeBuyAdapter(getContext(),homeBuyBeans);
        buy_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        buy_recyclerView.setAdapter(mHomeBuyAdapter);

        mHomeHotSellAdapter = new HomeHotSellAdapter(getContext(),sellBeans);
        sell_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sell_recyclerView.setAdapter(mHomeHotSellAdapter);

        mHomeLianMengAdapter =  new HomeLianMengAdapter(getContext(),lianmengBeans);
        lianmeng_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        lianmeng_recyclerView.setAdapter(mHomeLianMengAdapter);

        mHomeXinPinAdapter = new HomeXinPinAdapter(getContext(),xinpinBeans);
        xinpin_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2, LinearLayoutManager.VERTICAL,false));
        xinpin_recyclerView.setAdapter(mHomeXinPinAdapter);

    }

    private void setBanner(){
        List<String> img = new ArrayList<>();
        for (int i=0;i<adBeans.size();i++){
            img.add(adBeans.get(i).getAd_code());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(img);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(adBeans.get(position).getType()==1){
                    if(adBeans.get(position).getAd_link()!=null&&!adBeans.get(position).getAd_link().equals("")){
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                    .putExtra("store_id",adBeans.get(position).getAd_link())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                        }

                    }else {
                        CusToast.showToast("暂无店铺ID");
                    }
                }else if(adBeans.get(position).getType()==2){
                    if(adBeans.get(position).getAd_link()!=null&&!adBeans.get(position).getAd_link().equals("")){
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(),XeiYiH5Activity.class)
                                    .putExtra("typeMain","1")
                                    .putExtra("h5url",adBeans.get(position).getAd_link()));
                        }

                    }else {
                        CusToast.showToast("暂无链接");
                    }

                }else {
                    CusToast.showToast("暂无操作");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void initRefresh(){
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                initData(1);
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initRefreshData(currentPage);
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
