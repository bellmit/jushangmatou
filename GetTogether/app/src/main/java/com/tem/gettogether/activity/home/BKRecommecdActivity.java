package com.tem.gettogether.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SouSuoDataBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
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

@ContentView(R.layout.activity_bkrecommecd)
public class BKRecommecdActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private List<SouSuoDataBean.ResultBean> resultBeans=new ArrayList<>();
    private List<SouSuoDataBean.ResultBean> list;
    private String category_id;
    private String paixu="asc";
    private String sort="sort";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }
    @Override
    protected void initData() {
        tv_title.setText(getResources().getText(R.string.baokuan));
        category_id=getIntent().getExtras().getString("category_id");

        if(category_id!=null&&!category_id.equals("")){
            Map<String,Object> map3=new HashMap<>();
//            map3.put("category_id",category_id);
            map3.put("page",PAGE_NUM);
            map3.put("sort",sort);
            map3.put("sort_asc",paixu);
            map3.put("one_id",category_id);

            upShopData(map3);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(BKRecommecdActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(resultBeans);
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
//                    map3.put("category_id",category_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);
                    map3.put("sort_asc",paixu);
                    map3.put("one_id",category_id);

                    upShopData(map3);
                }
            }
            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(BKRecommecdActivity.this)) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                if(category_id!=null&&!category_id.equals("")){
                    Map<String,Object> map3=new HashMap<>();
//                    map3.put("category_id",category_id);
                    map3.put("page",PAGE_NUM);
                    map3.put("sort",sort);//销量
                    map3.put("sort_asc",paixu);
                    map3.put("one_id",category_id);


                    upShopData(map3);
                }
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 2));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white);//背景色
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
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
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
                BKRecommecndAdapter adapter=new BKRecommecndAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    public class BKRecommecndAdapter extends BaseQuickAdapter{
        public BKRecommecndAdapter( List<SouSuoDataBean.ResultBean> data) {
            super(R.layout.bk_recommecnd_item, data);
        }
        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            Glide.with(BKRecommecdActivity.this).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_image);
            baseViewHolder.setText(R.id.tv_name,resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qigou,resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            baseViewHolder.setText(R.id.tv_price,resultBeans.get(baseViewHolder.getAdapterPosition()).getShop_price());
            baseViewHolder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(BKRecommecdActivity.this,LoginActivity.class));
                    }else {
                        startActivity(new Intent(BKRecommecdActivity.this,ShoppingParticularsActivity.class)
                                .putExtra("goods_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                    }

                }
            });

        }
    }

}
