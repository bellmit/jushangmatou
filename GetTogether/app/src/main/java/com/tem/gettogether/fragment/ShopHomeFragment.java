package com.tem.gettogether.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShopKeyBean;
import com.tem.gettogether.bean.ShopShoppingBean;
import com.tem.gettogether.bean.ShopTopBean;
import com.tem.gettogether.utils.GlideImageLoader;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_shop_home)
public class ShopHomeFragment extends BaseFragment {

    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.tv_tj)
    private TextView tv_tj;
    @ViewInject(R.id.tv_xl)
    private TextView tv_xl;
    @ViewInject(R.id.tv_xp)
    private TextView tv_xp;
    @ViewInject(R.id.tv_qpl)
    private TextView tv_qpl;
    private String cat_id;
    private String type="recommend";
    private String sousuoConnect;
    private String store_id;
    private String sort="sort";
    private List<ShopShoppingBean.ResultBean.GoodsListBean> goodsListBeans=new ArrayList<>();
    private List<ShopShoppingBean.ResultBean.GoodsListBean> list;
    public static ShopHomeFragment newInstance() {
        return new ShopHomeFragment();
    }
    private   BaseActivity baseActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        baseActivity= (BaseActivity) getActivity();

        store_id=getActivity().getIntent().getExtras().getString("store_id");

        initView();
        Map<String,Object> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",PAGE_NUM);
        map.put("sort",sort);
        map.put("mode","desc");
        map.put("type","recommend");

        upShopData(map);
        super.onActivityCreated(savedInstanceState);
    }
    public void initView() {

        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(goodsListBeans);
                Map<String,Object> map=new HashMap<>();
                map.put("store_id",store_id);
                map.put("page",PAGE_NUM);
                map.put("sort",sort);
                map.put("mode","desc");
                if(sousuoConnect!=null){
                    map.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map.put("cat_id",cat_id);
                }
                if(type!=null&&!type.equals("")){
                    map.put("type",type);
                }
                upShopData(map);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                Map<String,Object> map=new HashMap<>();
                map.put("store_id",store_id);
                map.put("page",PAGE_NUM);
                map.put("sort",sort);
                map.put("mode","desc");
                if(sousuoConnect!=null){
                    map.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map.put("cat_id",cat_id);
                }
                if(type!=null&&!type.equals("")){
                    map.put("type",type);
                }
                upShopData(map);
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(""+getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(""+getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(""+getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);


    }
    public void clearList(List<ShopShoppingBean.ResultBean.GoodsListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    @Event(value = {R.id.tv_tj,R.id.tv_xl,R.id.tv_xp,R.id.ll_qpl,R.id.ll_look_more}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_tj:
                tv_tj.setTextColor(getResources().getColor(R.color.home_red));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                tv_xp.setTextColor(getResources().getColor(R.color.text3));
                tv_qpl.setTextColor(getResources().getColor(R.color.text3));
                PAGE_NUM=1;
                clearList(goodsListBeans);
                type="recommend";
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("store_id",store_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);
                    map3.put("mode","desc");
                if(sousuoConnect!=null){
                    map3.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map3.put("cat_id",cat_id);
                }
                    map3.put("type",type);
                    upShopData(map3);
                break;
            case R.id.tv_xl:
                tv_tj.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.home_red));
                tv_xp.setTextColor(getResources().getColor(R.color.text3));
                tv_qpl.setTextColor(getResources().getColor(R.color.text3));
                sort="sales";
                type="";
                PAGE_NUM=1;
                clearList(goodsListBeans);
                Map<String,Object> map2=new HashMap<>();
                map2.put("store_id",store_id);
                map2.put("page",PAGE_NUM);
                map2.put("sort",sort);
                map2.put("mode","desc");
                 if(sousuoConnect!=null){
                    map2.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map2.put("cat_id",cat_id);
                }
                upShopData(map2);
                break;
            case R.id.tv_xp:
                tv_tj.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                tv_xp.setTextColor(getResources().getColor(R.color.home_red));
                tv_qpl.setTextColor(getResources().getColor(R.color.text3));
                sort="sales";
                type="new";
                PAGE_NUM=1;
                clearList(goodsListBeans);
                Map<String,Object> map1=new HashMap<>();
                map1.put("store_id",store_id);
                map1.put("page",PAGE_NUM);
                map1.put("sort",sort);
                map1.put("mode","desc");
                if(sousuoConnect!=null){
                    map1.put("keywords",sousuoConnect);
                }
                if(cat_id!=null&&!cat_id.equals("")){
                    map1.put("cat_id",cat_id);
                }
                map1.put("type",type);
                upShopData(map1);
                break;
            case R.id.ll_qpl:
                tv_tj.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                tv_xp.setTextColor(getResources().getColor(R.color.text3));
                tv_qpl.setTextColor(getResources().getColor(R.color.home_red));
                sort="batch_number";
                type="";
                PAGE_NUM=1;
                clearList(goodsListBeans);
                Map<String,Object> map=new HashMap<>();
                map.put("store_id",store_id);
                map.put("page",PAGE_NUM);
                map.put("sort",sort);
                map.put("mode","desc");
                if(sousuoConnect!=null){
                    map.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map.put("cat_id",cat_id);
                }
                upShopData(map);
                break;
            case R.id.ll_look_more:
                PAGE_NUM++;
                Map<String,Object> map4=new HashMap<>();
                map4.put("store_id",store_id);
                map4.put("page",PAGE_NUM);
                map4.put("sort",sort);
                map4.put("mode","desc");
                if(sousuoConnect!=null){
                    map4.put("keywords",sousuoConnect);
                }
                if(cat_id!=null){
                    map4.put("cat_id",cat_id);
                }
                if(type!=null&&!type.equals("")){
                    map4.put("type",type);
                }
                upShopData(map4);
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 根据搜索、商品分类刷新数据
     * @param shopKeyBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ShopKeyBean shopKeyBean) {
        if (shopKeyBean == null) return;
        store_id=shopKeyBean.getStore_id();

        if(shopKeyBean.getTypeLX().equals("0x1001")){
//            CusToast.showToast("0x1001");
            sousuoConnect=shopKeyBean.getSousuoConnect();
            cat_id=null;
            PAGE_NUM=1;
            Map<String,Object> map3=new HashMap<>();
            map3.put("store_id",store_id);
            map3.put("page",PAGE_NUM);
            map3.put("sort",sort);

            map3.put("mode","desc");
            map3.put("keywords",sousuoConnect);
            upShopData(map3);
        }else if(shopKeyBean.getTypeLX().equals("0x1002")){
//            CusToast.showToast("0x1002");
            cat_id=shopKeyBean.getCat_id();
            sousuoConnect=null;
            PAGE_NUM=1;
            Map<String,Object> map3=new HashMap<>();
            map3.put("store_id",store_id);
            map3.put("page",PAGE_NUM);
            map3.put("sort",sort);
            map3.put("mode","desc");
            map3.put("cat_id",cat_id);
            upShopData(map3);
        }

    }

    public class ShoppHomeAdapter extends BaseQuickAdapter {

        public ShoppHomeAdapter(List<ShopShoppingBean.ResultBean.GoodsListBean> data) {//
            super(R.layout.shop_home_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {

            RoundImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            Glide.with(getActivity()).load(goodsListBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_image);
            baseViewHolder.setText(R.id.tv_name,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_numbear,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+getText(R.string.from_batch));
            baseViewHolder.setText(R.id.tv_pingjia,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBest_percent()+getText(R.string.praise));
            baseViewHolder.getView(R.id.ll_item_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("=====店铺内商品id--",goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()+"");
                    startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                            .putExtra("goods_id",goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });
        }
    }
    private void upShopData( Map<String,Object> map){
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--搜索参数打印--" + key, "" + value + "\n");
            }
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.SHOPSHOPIINGDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====店铺首页商品===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShopShoppingBean souSuoDataBean=gson.fromJson(result,ShopShoppingBean.class);
                        if(PAGE_NUM==1){
                            goodsListBeans=souSuoDataBean.getResult().getGoods_list();

                        }else{
                            list=souSuoDataBean.getResult().getGoods_list();
                            if (ListUtils.isEmpty(list)){
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                                return;
                            }
                            goodsListBeans.addAll(list);
                        }
//                        if(goodsListBeans.size()==0){
//                            goodsListBeans.clear();
//                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                ShoppHomeAdapter adapter=new ShoppHomeAdapter(goodsListBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();

            }
        });
    }
}
