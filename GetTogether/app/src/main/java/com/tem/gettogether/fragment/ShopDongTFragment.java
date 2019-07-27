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
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShopDongtaiBean;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;

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
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_shop_dong_t)
public class ShopDongTFragment extends BaseFragment {

    @ViewInject(R.id.shopping_order_rl)
    private HomeListFreshRecyclerView shopping_order_rl;
    @ViewInject(R.id.shopping_order_refresh_fragment)
    private BGARefreshLayout shopping_order_refresh_fragment;
    @ViewInject(R.id.ll_look_more)
    private LinearLayout ll_look_more;
    private int PAGE_NUM = 1;
    private   BaseActivity baseActivity;
    private String store_id;
    public static ShopDongTFragment newInstance() {
        return new ShopDongTFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        baseActivity= (BaseActivity) getActivity();

        store_id=getActivity().getIntent().getExtras().getString("store_id");
        initView();
        upShopPJData(store_id,1);
        super.onActivityCreated(savedInstanceState);
    }
    public void initView() {

        shopping_order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    if (shopping_order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        shopping_order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                upShopPJData(store_id,1);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
//                PAGE_NUM++;
//                upShopPJData(store_id,PAGE_NUM);
                return true;
            }
        });
        shopping_order_rl.setLayoutManager(new GridLayoutManager(getContext(), 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("下拉加载");//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("松开加载");//松开的提示文字
        refreshViewHolder.setRefreshingText("加载中");//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        shopping_order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        shopping_order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(shopping_order_rl);
        List<String> list=new ArrayList<>();
//        for (int i=0;i<1;i++){
//            list.add("");
//        }


    }
    private List<ShopDongtaiBean.ResultBean> resultBeans=new ArrayList<>();
    public class ShopShopDTAdapter extends BaseQuickAdapter {

        public ShopShopDTAdapter(List<ShopDongtaiBean.ResultBean> data) {
            super(R.layout.shop_home_dt_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {

            RoundImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getImages().size()>0){
                Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getImages().get(0)).error(R.mipmap.myy322x).into(iv_image);
            }
            baseViewHolder.setText(R.id.tv_miaos,resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qipiNum,resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                            .putExtra("goods_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });
        }
    }
    @Event(value = {R.id.ll_look_more}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_look_more:
                PAGE_NUM++;
                upShopPJData(store_id,PAGE_NUM);
                break;

        }
    }
    private void  upShopPJData(final String store_id, final int page){
        Map<String,Object> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        XUtil.Post(URLConstant.SHOPPINGJIA_DONGTAI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                shopping_order_refresh_fragment.endRefreshing();
                shopping_order_refresh_fragment.endLoadingMore();
                Log.i("====获取店铺动态==="+page, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShopDongtaiBean shopDongtaiBean=gson.fromJson(result,ShopDongtaiBean.class);
                        List<ShopDongtaiBean.ResultBean> list=shopDongtaiBean.getResult();
                        if(page==1){
                            resultBeans=shopDongtaiBean.getResult();

                        }else{
                            if(list.size()==0){
                                CusToast.showToast("没有数据了~~");
                                return;
                            }
                            resultBeans.addAll(list);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ShopShopDTAdapter adapter=new ShopShopDTAdapter(resultBeans);
                shopping_order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }

}
