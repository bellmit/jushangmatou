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

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShopLBBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

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
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_shop_d)
public class ShopDPFragment extends BaseFragment {
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private BaseActivity baseActivity;
    public static ShopDPFragment newInstance() {
        return new ShopDPFragment();
    }
    private String id;
    private String title;
    private List<ShopLBBean.ResultBean> list;

    private List<ShopLBBean.ResultBean> resultBeans=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        id=getActivity().getIntent().getExtras().getString("id");
        title=getActivity().getIntent().getExtras().getString("title");
        baseActivity= (BaseActivity) getActivity();
        initData();
        initView();
        upShopLBData();
        super.onActivityCreated(savedInstanceState);
    }
    private void initData(){
//        upShopLBData();
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
                clearList(resultBeans);
                if(id!=null&&!id.equals("")){
                    upShopLBData();
                }
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                upShopLBData();
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(getContext(), 1));
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
    public void clearList(List<ShopLBBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    public class ShoppDPAdapter extends BaseQuickAdapter {

        public ShoppDPAdapter(List<ShopLBBean.ResultBean> data) {
            super(R.layout.shopping_dp_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            RoundImageView iv_shop_head=baseViewHolder.getView(R.id.iv_shop_head);
            RoundImageView iv_image1=baseViewHolder.getView(R.id.iv_image1);
            RoundImageView iv_image2=baseViewHolder.getView(R.id.iv_image2);
            RoundImageView iv_image3=baseViewHolder.getView(R.id.iv_image3);
            baseViewHolder.setText(R.id.tv_name,resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_name());
            baseViewHolder.setText(R.id.tv_address,resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_address());
            Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_logo()).error(R.mipmap.myy322x).into(iv_shop_head);
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().size()==1){
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(0).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image1);
                iv_image2.setVisibility(View.GONE);
                iv_image3.setVisibility(View.GONE);
            }else if(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().size()==2){
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(0).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image1);
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(1).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image2);
                iv_image3.setVisibility(View.GONE);
            } else if(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().size()>=3){
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(0).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image1);
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(1).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image2);
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoodsList().get(2).getGoods_logo()).error(R.mipmap.myy322x).into(iv_image3);

            }else{
                iv_image1.setVisibility(View.GONE);
                iv_image2.setVisibility(View.GONE);
                iv_image3.setVisibility(View.GONE);
            }
            baseViewHolder.getView(R.id.tv_in_Shop).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                .putExtra("store_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_id())
                                .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);

                }
            });

        }
    }
    private void upShopLBData(){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("goods_keywword",title);
        map.put("page",PAGE_NUM);

        baseActivity.showDialog();
        XUtil.Post(URLConstant.SHOPLIEBIAO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====店铺列表fenlei===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    order_refresh_fragment.endRefreshing();
                    order_refresh_fragment.endLoadingMore();
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShopLBBean shopLBBean=gson.fromJson(result,ShopLBBean.class);
                        list=shopLBBean.getResult();
                        if (ListUtils.isEmpty(list)){
                            CusToast.showToast(getResources().getText(R.string.no_more_data));
                            return;
                        }
                        resultBeans.addAll(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                ShoppDPAdapter adapter=new ShoppDPAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();

            }
        });
    }
}
