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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.home.HotFenLeiActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SouSuoDataBean;
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
@ContentView(R.layout.fragment_shopping)
public class ShoppingFragment extends BaseFragment {
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    @ViewInject(R.id.tv_zonghe)
    private TextView tv_zonghe;
    @ViewInject(R.id.tv_pfl)
    private TextView tv_pfl;
    @ViewInject(R.id.tv_xl)
    private TextView tv_xl;
    private String category_id;
    private String paixu="desc";
    private String sort="sort";
    private List<SouSuoDataBean.ResultBean> list;

    private List<SouSuoDataBean.ResultBean> resultBeans=new ArrayList<>();

    public static ShoppingFragment newInstance() {
        return new ShoppingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initData() {
        category_id=getActivity().getIntent().getExtras().getString("category_id");

        if(category_id!=null&&!category_id.equals("")){
            Map<String,Object> map3=new HashMap<>();
            map3.put("category_id",category_id);
            map3.put("page",PAGE_NUM);
            map3.put("sort",sort);
            map3.put("sort_asc",paixu);
            upShopData(map3);
        }
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
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("category_id",category_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    upShopData(map3);
                }
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("category_id",category_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);//销量
                    map3.put("sort_asc",paixu);
                    upShopData(map3);
                }
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(getContext(), 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("下拉加载");//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("松开加载");//松开的提示文字
        refreshViewHolder.setRefreshingText("加载中");//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);

    }
    public void clearList(List<SouSuoDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    @Event(value = {R.id.tv_zonghe,R.id.tv_pfl,R.id.tv_xl}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_zonghe:
                PAGE_NUM=1;
                tv_zonghe.setTextColor(getResources().getColor(R.color.home_red));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                sort="sort";
                paixu="desc";
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("category_id",category_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    upShopData(map3);
                }
                break;
            case R.id.tv_pfl:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.home_red));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                sort="batch_number";
                paixu="asc";
                PAGE_NUM=1;
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map2=new HashMap<>();
                    map2.put("page",PAGE_NUM);
                    map2.put("sort",sort);//批发
                    map2.put("sort_asc",paixu);
                    map2.put("category_id",category_id);
                    upShopData(map2);
                }
                break;
            case R.id.tv_xl:

                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.home_red));
                sort="sales_sum";
                paixu="desc";
                PAGE_NUM=1;
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map=new HashMap<>();
                    map.put("page",PAGE_NUM);
                    map.put("sort",sort);//销量
                    map.put("sort_asc",paixu);
                    map.put("category_id",category_id);
                    upShopData(map);
                }
                break;

        }
    }
    private void upShopData( Map<String,Object> map){
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--shopping参数打印--" + key, "" + value + "\n");
            }
        }
        XUtil.Post(URLConstant.SHANGPINLIEBIAO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
//                closeDialog();
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====商品列表或商品搜索===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        SouSuoDataBean souSuoDataBean=gson.fromJson(result,SouSuoDataBean.class);

                        if(PAGE_NUM==1){
                            resultBeans=souSuoDataBean.getResult();
                        }else {
                            list=souSuoDataBean.getResult();
                            if (list.size()==1){
                                CusToast.showToast("没有更新的数据");
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
                ShoppingAdapter adapter=new ShoppingAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    public class ShoppingAdapter extends BaseQuickAdapter {

        public ShoppingAdapter(List<SouSuoDataBean.ResultBean> data) {
            super(R.layout.shopping_fragment_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_pic=baseViewHolder.getView(R.id.iv_pic);
            Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_pic);
            baseViewHolder.setText(R.id.tv_shoping_jj,resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qpl,resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            baseViewHolder.setText(R.id.tv_hpl,resultBeans.get(baseViewHolder.getAdapterPosition()).getBest_percent()+"好评");
            baseViewHolder.getView(R.id.ll_All_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                                .putExtra("goods_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                    }


                }
            });
        }
    }

}
