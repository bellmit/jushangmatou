package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyZJBean;
import com.tem.gettogether.dialog.Effectstype;
import com.tem.gettogether.dialog.LogoutDialogBuilder;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
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
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

@ContentView(R.layout.activity_zu_ji)
public class ZuJiActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private List<MyZJBean.ResultBean> listBeanXES=new ArrayList<>();
    private List<MyZJBean.ResultBean> list;
    private Effectstype effect;
    @ViewInject(R.id.ll_look_more)
    private LinearLayout ll_look_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ZuJiActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(listBeanXES);
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("page",PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                upShopData(map3);


            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ZuJiActivity.this)) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                Map<String,Object> map3=new HashMap<>();
                map3.put("page",PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                upShopData(map3);
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText(""+getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText(""+getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText(""+getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);
    }
    public void clearList(List<MyZJBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    @Override
    protected void initData() {
        tv_title.setText(R.string.zuji);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(R.string.qingkong);
        Map<String,Object> map3=new HashMap<>();
        map3.put("page","1");
        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        upShopData(map3);

    }
    @Event(value = {R.id.rl_close,R.id.rl_title_right,R.id.ll_look_more}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_title_right://清空
                listBeanXES.clear();
                upRemoveAllData();
                break;
            case R.id.ll_look_more:
                PAGE_NUM++;
                Map<String,Object> map3=new HashMap<>();
                map3.put("page",PAGE_NUM);
                map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                upShopData(map3);
                break;

        }
    }
    private void  upShopData(Map<String,Object> map){

        XUtil.Post(URLConstant.SHOPPING_ZUJI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====足迹列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if(res.equals("1")){
                        Gson gson=new Gson();
                        MyZJBean myZJBean=gson.fromJson(result,MyZJBean.class);
                        if(PAGE_NUM==1){
                            listBeanXES=myZJBean.getResult();
                        }else{
                            list=myZJBean.getResult();
                            if (list.size()==0){
                                CusToast.showToast(getText(R.string.no_more_data));
                                return;
                            }
                            listBeanXES.addAll(list);
                        }

                    }else{
                        String msg = jsonObject.optString("msg");
                        CusToast.showToast(msg);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                    MyZujiAdapter adapter=new MyZujiAdapter(listBeanXES);
                    order_rl.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    private void upRemoveAllData(){
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        XUtil.Post(URLConstant.SHOPPING_REMOVEALLZUJI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====清空足迹===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        Map<String,Object> map3=new HashMap<>();
                        map3.put("page","1");
                        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                        upShopData(map3);
                    }
                    CusToast.showToast(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    private void upRemoveitenmData(String footprint_id){
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("footprint_id",footprint_id);

        XUtil.Post(URLConstant.SHOPPING_REMOVEITEMZUJI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Log.i("====删除item足迹===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        Map<String,Object> map3=new HashMap<>();
                        map3.put("page","1");
                        map3.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                        upShopData(map3);
                    }
                    CusToast.showToast(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    public class MyZujiAdapter extends BaseQuickAdapter {

        public MyZujiAdapter(List<MyZJBean.ResultBean> data) {//
            super(R.layout.recycler_zj_iteam, data);
        }

        @Override
        protected void convert(final com.chad.library.adapter.base.BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_image=baseViewHolder.getView(R.id.iv_image);
                    if(listBeanXES.get(baseViewHolder.getAdapterPosition()).getGoods_name()!=null&&!listBeanXES.get(baseViewHolder.getAdapterPosition()).getGoods_name().equals("")
                            &&!listBeanXES.get(baseViewHolder.getAdapterPosition()).getFootprint_id().equals("")&&!listBeanXES.get(baseViewHolder.getAdapterPosition()).getStore_id().equals("")) {
                        Glide.with(ZuJiActivity.this).load(listBeanXES.get(baseViewHolder.getAdapterPosition()).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
                        baseViewHolder.setText(R.id.tv_title,listBeanXES.get(baseViewHolder.getAdapterPosition()).getGoods_name());
                        baseViewHolder.setText(R.id.tv_price,"¥"+listBeanXES.get(baseViewHolder.getAdapterPosition()).getGoods_price());
                        baseViewHolder.setText(R.id.tv_qg,listBeanXES.get(baseViewHolder.getAdapterPosition()).getBatch_number()+getText(R.string.purchase));

                    }

            baseViewHolder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        /*if(!listBeanXES.get(baseViewHolder.getAdapterPosition()).getStore_id().equals("")) {
                            startActivityForResult(new Intent(ZuJiActivity.this, ShopActivity.class)
                                    .putExtra("store_id",listBeanXES.get(baseViewHolder.getAdapterPosition()).getStore_id())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);

                        }*/

                        if(!listBeanXES.get(baseViewHolder.getAdapterPosition()).getFootprint_id().equals("")){
                            startActivity(new Intent(ZuJiActivity.this, ShoppingParticularsActivity.class)
                                    .putExtra("goods_id",listBeanXES.get(baseViewHolder.getAdapterPosition()).getPid()));
                        }

                }
            });
            baseViewHolder.getView(R.id.ll_item).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final LogoutDialogBuilder dialogLogout = new LogoutDialogBuilder(ZuJiActivity.this, R.style.dialog_untran);
                    effect = Effectstype.SHAKE;
                    dialogLogout.isvisibiliby();
                    dialogLogout
                            .withTitleColor("#333333")                                  //def
                            .withDividerColor("#333333")                              //def
                            .withMessage(getText(R.string.qdscgzjm))                     //.withMessage(null)  no Msg
                            .withMessageColor("#333333")                                //def
                            //.withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                            .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                            .withDuration(0)                                          //def    数值约大动画越明显
                            .withEffect(effect)                                         //def Effectstype.Slidetop
                            .withButton1Text(getText(R.string.quxiao))
                            .withButton2Text(getText(R.string.queding))
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogLogout.dismiss();
                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        if(!listBeanXES.get(baseViewHolder.getAdapterPosition()).getFootprint_id().equals("")){
                                            upRemoveitenmData(listBeanXES.get(baseViewHolder.getAdapterPosition()).getFootprint_id());
                                            dialogLogout.dismiss();
                                        }

                                }
                            })
                            .show();
                    return true;
                }
            });
            
        }
    }
}
