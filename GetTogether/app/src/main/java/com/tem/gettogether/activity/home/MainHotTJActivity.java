package com.tem.gettogether.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MainHotTJBean;
import com.tem.gettogether.bean.SouSuoDataBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;
import com.tem.gettogether.view.pull.PullLoadMoreRecyclerView;

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

@ContentView(R.layout.activity_main_hot_tj)
public class MainHotTJActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private String bottomType;
    @ViewInject(R.id.iv_bottom_imag)
    private ImageView iv_bottom_imag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PAGENUM=1;
        upHottuijian();
    }

    @Override
    protected void initData() {
        bottomType=getIntent().getStringExtra("bottomType");
        if (bottomType.equals("1")) {
            tv_title.setText(getResources().getText(R.string.selling));
        }else {
            tv_title.setText(getResources().getText(R.string.special));
        }

    }

    @Override
    protected void initView() {

        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(MainHotTJActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGENUM=1;
                clearList(houtResultBeans);
                upHottuijian();

            }
            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(MainHotTJActivity.this)) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGENUM++;
                upHottuijian();
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
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }
    public void clearList(List<MainHotTJBean.ResultBean.ListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    private int PAGENUM=1;
    private List<MainHotTJBean.ResultBean.ListBean> houtResultBeans=new ArrayList<>();
    private List<MainHotTJBean.ResultBean.ListBean> list;
    private MainHotTJBean.ResultBean.BannerBean hotBannerBean=new MainHotTJBean.ResultBean.BannerBean();
    private void upHottuijian(){
        Map<String,Object> map=new HashMap<>();
        String url;
        if(bottomType.equals("1")){
            url= URLConstant.HOT_TUIJIAN;
        }else {
            url=URLConstant.HOT_TEJIAZUANQU;

        }
        map.put("page",PAGENUM);
        showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====热卖推荐--==", result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        MainHotTJBean homeSCFLDataBean=gson.fromJson(result,MainHotTJBean.class);
                        if(PAGENUM==1){
                            hotBannerBean=homeSCFLDataBean.getResult().getBanner();
                            houtResultBeans=homeSCFLDataBean.getResult().getList();
                        }else {
                            hotBannerBean=homeSCFLDataBean.getResult().getBanner();
                            list=homeSCFLDataBean.getResult().getList();
                            if (list.size()==0){
                                CusToast.showToast("没有更新的数据");
                                return;
                            }
                            houtResultBeans.addAll(list);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                Glide.with(MainHotTJActivity.this).load(hotBannerBean.getAd_code()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_bottom_imag);
                iv_bottom_imag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(hotBannerBean.getAd_link()!=null&&!hotBannerBean.getAd_link().equals("")){
                            if(BaseApplication.getInstance().userBean==null){
                                startActivity(new Intent(MainHotTJActivity.this,LoginActivity.class));
                            }else {
                                startActivityForResult(new Intent(MainHotTJActivity.this, ShopActivity.class)
                                        .putExtra("store_id",hotBannerBean.getAd_link())
                                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                            }


                        }else {
                            CusToast.showToast("暂无店铺ID");
                        }
                    }
                });
                HotTJAdapter adapter=new HotTJAdapter(houtResultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }
    public class HotTJAdapter extends BaseQuickAdapter {
        public HotTJAdapter( List<MainHotTJBean.ResultBean.ListBean> data) {
            super(R.layout.bk_recommecnd_item, data);
        }
        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            Glide.with(MainHotTJActivity.this).load(houtResultBeans.get(baseViewHolder.getAdapterPosition()).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
            TextView tv_name=baseViewHolder.getView(R.id.tv_name);
            TextView tv_qigou=baseViewHolder.getView(R.id.tv_qigou);
            TextView tv_price=baseViewHolder.getView(R.id.tv_price);

            tv_name.setText(houtResultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            tv_qigou.setText(houtResultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            tv_price.setText(houtResultBeans.get(baseViewHolder.getAdapterPosition()).getShop_price());
            baseViewHolder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(MainHotTJActivity.this,LoginActivity.class));
                    }else {
                        startActivity(new Intent(MainHotTJActivity.this,ShoppingParticularsActivity.class)
                                .putExtra("goods_id",houtResultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                    }

                }
            });


        }
    }
}
