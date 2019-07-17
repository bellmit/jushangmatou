package com.jsmt.developer.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.SouSuoDataBean;
import com.jsmt.developer.utils.ListUtils;
import com.jsmt.developer.utils.NetWorkUtils;
import com.jsmt.developer.utils.UiUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.jsmt.developer.view.RoundImageView;
import com.jsmt.developer.view.powerfulrecyclerview.HomeListFreshRecyclerView;

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

@ContentView(R.layout.activity_hot_fen_lei)
public class HotFenLeiActivity extends BaseActivity {
    @ViewInject(R.id.tv_zonghe)
    private TextView tv_zonghe;
    @ViewInject(R.id.tv_pfl)
    private TextView tv_pfl;
    @ViewInject(R.id.tv_xl)
    private TextView tv_xl;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private String category_id;
    private String keywords;
    private String paixu="desc";
    private String sort="sort";
    @ViewInject(R.id.et_sousuo)
    private EditText et_sousuo;
    private List<SouSuoDataBean.ResultBean> list;
    private List<SouSuoDataBean.ResultBean> resultBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        category_id=getIntent().getStringExtra("category_id");
        keywords=getIntent().getStringExtra("keywords");

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(category_id!=null&&!category_id.equals("")){
            Map<String,Object> map3=new HashMap<>();
            map3.put("category_id",category_id);
            map3.put("page",1);
            map3.put("sort",sort);
            map3.put("sort_asc",paixu);
            upShopData(map3);
        }else if(keywords!=null&&!keywords.equals("")){
            Map<String,Object> map3=new HashMap<>();
            map3.put("page",1);
            map3.put("sort",sort);
            map3.put("sort_asc",paixu);
            map3.put("keywords",keywords);
            upShopData(map3);
        }
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(HotFenLeiActivity.this)) {
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
                }else if(keywords!=null&&!keywords.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    map3.put("keywords",keywords);
                    upShopData(map3);
                }

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(HotFenLeiActivity.this)) {
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
                }else if(keywords!=null&&!keywords.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);//销量
                    map3.put("sort_asc",paixu);
                    map3.put("keywords",keywords);
                    upShopData(map3);
                }
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 2));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("下拉加载");//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("松开加载");//松开的提示文字
        refreshViewHolder.setRefreshingText("加载中");//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);


    }

    @Event(value = {R.id.rl_close,R.id.tv_zonghe,R.id.tv_pfl,R.id.tv_xl,R.id.rl_sousuo}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_zonghe:
                tv_zonghe.setTextColor(getResources().getColor(R.color.home_red));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                sort="sort";
                paixu="desc";
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("category_id",category_id);
                    map3.put("page","1");
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    upShopData(map3);
                }else if(keywords!=null&&!keywords.equals("")){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("page","1");
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    map3.put("keywords",keywords);
                    upShopData(map3);
                }

                break;
            case R.id.tv_pfl:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.home_red));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));
                sort="batch_number";
                paixu="asc";
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map2=new HashMap<>();
                    map2.put("page","1");
                    map2.put("sort",sort);//批发
                    map2.put("sort_asc",paixu);
                    map2.put("category_id",category_id);
                    upShopData(map2);
                }else if(keywords!=null&&!keywords.equals("")){
                    Map<String,Object> map2=new HashMap<>();
                    map2.put("page","1");
                    map2.put("sort",sort);//批发
                    map2.put("sort_asc",paixu);
                    map2.put("keywords",keywords);
                    upShopData(map2);
                }


                break;
            case R.id.tv_xl:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.home_red));
                sort="sales_sum";
                paixu="desc";
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map=new HashMap<>();
                    map.put("page","1");
                    map.put("sort",sort);//销量
                    map.put("sort_asc",paixu);
                    map.put("category_id",category_id);
                    upShopData(map);
                }else if(keywords!=null&&!keywords.equals("")){
                    Map<String,Object> map=new HashMap<>();
                    map.put("page","1");
                    map.put("sort",sort);//销量
                    map.put("sort_asc",paixu);
                    map.put("keywords",keywords);
                    upShopData(map);
                }
                break;
            case R.id.rl_sousuo:
                String sousuo=et_sousuo.getText().toString().trim();
                if(sousuo.equals("")){
                    CusToast.showToast("请输入搜索内容");
                    return;
                }
                keywords=sousuo;
                category_id="";
                Map<String,Object> map=new HashMap<>();
                map.put("page","1");
                map.put("sort",sort);
                map.put("sort_asc",paixu);
                map.put("keywords",sousuo);
                upShopData(map);

                break;


        }
    }
    public void clearList(List<SouSuoDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
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
                        }else{
                            list=souSuoDataBean.getResult();
                            if (ListUtils.isEmpty(list)){
                                UiUtils.toast("没有更新的数据");
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
//                closeDialog();
                HotFLAdapter adapter=new HotFLAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                closeDialog();
                ex.printStackTrace();

            }
        });
    }
    public class HotFLAdapter extends BaseQuickAdapter {

        public HotFLAdapter(List<SouSuoDataBean.ResultBean> data) {//
            super(R.layout.shop_home_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            RoundImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            Glide.with(HotFenLeiActivity.this).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_image);
            baseViewHolder.setText(R.id.tv_name,resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_numbear,resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            baseViewHolder.setText(R.id.tv_pingjia,resultBeans.get(baseViewHolder.getAdapterPosition()).getBest_percent()+"好评");
            baseViewHolder.getView(R.id.ll_item_all).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(HotFenLeiActivity.this,LoginActivity.class));
                    }else {
                        startActivity(new Intent(HotFenLeiActivity.this,ShoppingParticularsActivity.class)
                                .putExtra("goods_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                    }

                }
            });
        }
    }

}