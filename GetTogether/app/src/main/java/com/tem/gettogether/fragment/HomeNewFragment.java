package com.tem.gettogether.fragment;

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
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.my.XeiYiH5Activity;
import com.tem.gettogether.adapter.HomeBottomCateAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeDataBean;
import com.tem.gettogether.bean.ServiceProviderBean;
import com.tem.gettogether.utils.GlideImageLoader;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
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
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;

    private  BaseActivity baseActivity;
    private List<HomeDataBean.ResultBean.AdBean> adBeans=new ArrayList<>();
    private List<HomeDataBean.ResultBean.BottomCateBean> bottomCateBeans=new ArrayList<>();
    private HomeBottomCateAdapter mHomeBottomCateAdapter;

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
        initData();
    }

    private void initView(){
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3, LinearLayoutManager.VERTICAL,false));
        initRefresh();
    }


    private void initData(){
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.HONEDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====首页数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeDataBean homeDataBean=gson.fromJson(result,HomeDataBean.class);
                        adBeans=homeDataBean.getResult().getAd();
                        bottomCateBeans=homeDataBean.getResult().getBottom_cate();

                        initAllData();
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

    private void initAllData(){
        initBanner();
        mHomeBottomCateAdapter = new HomeBottomCateAdapter(getContext(),bottomCateBeans);
        recyclerView.setAdapter(mHomeBottomCateAdapter);
    }

    private void initBanner(){
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
                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
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
