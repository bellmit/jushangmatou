package com.jsmt.developer.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.home.HomeGongGaoActivity;
import com.jsmt.developer.activity.home.HomeHotSellActivity;
import com.jsmt.developer.activity.home.HomeLianMengActivity;
import com.jsmt.developer.activity.home.HomeSouSuoActivity;
import com.jsmt.developer.activity.my.BuyMessageActivity;
import com.jsmt.developer.adapter.HomeBuyAdapter;
import com.jsmt.developer.adapter.HomeHotSellAdapter;
import com.jsmt.developer.adapter.HomeLianMengAdapter;
import com.jsmt.developer.adapter.HomeXinPinAdapter;
import com.jsmt.developer.bean.HomeDataNewBean;
import com.jsmt.developer.view.BaseScrollView;
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
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.fragment_new_home)
public class HomeNewFragment extends BaseFragment implements View.OnClickListener{

    @ViewInject(R.id.tv_YuYQH)
    private TextView tv_YuYQH;
    @ViewInject(R.id.tv_sousuo)
    private TextView tv_sousuo;
    @ViewInject(R.id.tv_gonggao)
    private TextView tv_gonggao;
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

    @ViewInject(R.id.waimaoqiugou_tv)
    private TextView waimaoqiugou_tv;
    @ViewInject(R.id.waimaorexiao_tv)
    private TextView waimaorexiao_tv;
    @ViewInject(R.id.waimaolianmeng_tv)
    private TextView waimaolianmeng_tv;
    @ViewInject(R.id.back_top_btn)
    private ImageButton back_top_btn;

    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.mScrollView)
    private BaseScrollView mScrollView;
    private  BaseActivity baseActivity;

    private List<HomeDataNewBean.ResultEntity.AdEntity> adBeans=new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> bottomCateBeans=new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> homeBuyBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Order_pastedEntity> sellBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Trade_unionEntity> lianmengBeans = new ArrayList<>();
    private List<HomeDataNewBean.ResultEntity.Ftrade_newEntity> xinpinBeans = new ArrayList<>();
    private HomeDataNewBean.ResultEntity.NoticeEntity noticeBeans=new HomeDataNewBean.ResultEntity.NoticeEntity();


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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
        initView();
        initData(currentPage);

        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        back_top_btn.setVisibility(scrollY>500?View.VISIBLE:View.GONE);
            }
        });
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
                        noticeBeans = homeDataBean.getResult().getNotice();
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
        tv_gonggao.setText(noticeBeans.getTitle());
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


    @Event(value = {R.id.rl_saoyisao,R.id.tv_YuYQH,R.id.tv_gonggao,R.id.tv_sousuo,
            R.id.ll_rmtj,R.id.ll_tjzq,R.id.ll_look_more,R.id.waimaoqiugou_tv,R.id.waimaorexiao_tv,R.id.waimaolianmeng_tv,R.id.back_top_btn}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_YuYQH:
                showPop(tv_YuYQH);
                break;
            case R.id.rl_saoyisao:
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                        .putExtra("store_id","0"));
                break;
            case R.id.tv_gonggao:
                startActivity(new Intent(getActivity(),HomeGongGaoActivity.class)
                        .putExtra("article_id",noticeBeans.getArticle_id()));
                break;
            case R.id.tv_sousuo:
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                        .putExtra("store_id","0"));
                break;
            case R.id.waimaoqiugou_tv:
                startActivity(new Intent(getActivity(),BuyMessageActivity.class));
                break;
            case R.id.back_top_btn:
                mScrollView.scrollTo(0,0);
                break;
            case R.id.waimaorexiao_tv:
                startActivity(new Intent(getActivity(),HomeHotSellActivity.class));
                break;
            case R.id.waimaolianmeng_tv:
                startActivity(new Intent(getActivity(),HomeLianMengActivity.class));
                break;
        }
    }

    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.TOP, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }

    private PopupWindow mPop;
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_layout_yuyan, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(true);
            mPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }

    private void setPopClickListener(View view) {
        view.findViewById(R.id.rl_item).setOnClickListener(this);
        view.findViewById(R.id.rl_item2).setOnClickListener(this);
        view.findViewById(R.id.rl_item3).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_item:
                DisplayMetrics dm = getResources().getDisplayMetrics();
                Configuration config = getResources().getConfiguration();
                // 应用用户选择语言
                config.locale = Locale.CHINESE;
                getResources().updateConfiguration(config,dm);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"zh");
                mPop.dismiss();
                restartApplication(getActivity());
                break;
            case R.id.rl_item2:
                DisplayMetrics dm2 = getResources().getDisplayMetrics();
                Configuration config2 = getResources().getConfiguration();
                // 应用用户选择语言
                config2.locale = Locale.ENGLISH;
                getResources().updateConfiguration(config2,dm2);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"en");
                mPop.dismiss();
                restartApplication(getActivity());
                break;
            case R.id.rl_item3:
                DisplayMetrics dm3 = getResources().getDisplayMetrics();
                Configuration config3 = getResources().getConfiguration();
                // 应用用户选择语言
                config3.locale = new Locale("ar");
                getResources().updateConfiguration(config3,dm3);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"ara");
                mPop.dismiss();
                restartApplication(getActivity());
                break;
        }
    }

    public void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
