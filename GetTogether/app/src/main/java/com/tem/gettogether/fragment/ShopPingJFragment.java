package com.tem.gettogether.fragment;


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
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShopPingJiaBean;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
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
@ContentView(R.layout.fragment_shop_ping_j)
public class ShopPingJFragment extends BaseFragment {

    @ViewInject(R.id.shopping_order_rl)
    private HomeListFreshRecyclerView shopping_order_rl;
    @ViewInject(R.id.shopping_order_refresh_fragment)
    private BGARefreshLayout shopping_order_refresh_fragment;
    private int PAGE_NUM = 1;
    private   BaseActivity baseActivity;
    private String store_id;
    public static ShopPingJFragment newInstance() {
        return new ShopPingJFragment();
    }
    private List<ShopPingJiaBean.ResultBean.CommentListBean> commentListBeans=new ArrayList<>();

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
                PAGE_NUM++;
                upShopPJData(store_id,PAGE_NUM);
                return true;
            }
        });
        shopping_order_rl.setLayoutManager(new GridLayoutManager(getContext(), 2));
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



    }
    public class ShopShopPJAdapter extends BaseQuickAdapter {

        public ShopShopPJAdapter(List<ShopPingJiaBean.ResultBean.CommentListBean> data) {
            super(R.layout.shop_home_pj_item, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Object o) {
            baseViewHolder.setText(R.id.tv_shop_name,commentListBeans.get(baseViewHolder.getAdapterPosition()).getNickname());
            baseViewHolder.setText(R.id.tv_content,commentListBeans.get(baseViewHolder.getAdapterPosition()).getContent());
            RoundImageView iv_pic=baseViewHolder.getView(R.id.iv_pic);
            CircularImage iv_image_head=baseViewHolder.getView(R.id.iv_image_head);
            if(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().size()>=1){
                Glide.with(getActivity()).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(0)).error(R.mipmap.myy322x).into(iv_pic);
            }
            Glide.with(getActivity()).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getHead_pic()).error(R.drawable.img12x).into(iv_image_head);

        }
    }
    private void  upShopPJData(final String store_id, int page){
        Map<String,Object> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        XUtil.Post(URLConstant.SHOPPINGJIA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                shopping_order_refresh_fragment.endRefreshing();
                shopping_order_refresh_fragment.endLoadingMore();
                Log.i("====获取商品评价==="+store_id, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShopPingJiaBean shopPingJiaBean=gson.fromJson(result,ShopPingJiaBean.class);
                        commentListBeans=shopPingJiaBean.getResult().getComment_list();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ShopShopPJAdapter adapter=new ShopShopPJAdapter(commentListBeans);
                shopping_order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }

}
