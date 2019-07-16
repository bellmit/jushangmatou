package com.tem.gettogether.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tem.gettogether.activity.cart.CloseAccountActivity;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.my.LookWuLiuActivity;
import com.tem.gettogether.activity.my.OrderXQActivity;
import com.tem.gettogether.activity.my.PostEvaluationActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.R;
//import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AliPayBean;
import com.tem.gettogether.bean.JieSuanBean;
import com.tem.gettogether.bean.MyOrderdataBean;
import com.tem.gettogether.bean.ShopLBBean;
import com.tem.gettogether.bean.WechatDataBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.PayResult;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.OnPasswordInputFinish;
import com.tem.gettogether.view.OnPasswordInputFinish2;
import com.tem.gettogether.view.PasswordView;
import com.tem.gettogether.view.PasswordView2;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.powerfulrecyclerview.HomeListFreshRecyclerView;
import com.tem.gettogether.view.pull.PullLoadMoreRecyclerView;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

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
@ContentView(R.layout.fragment_my_order)
public class MyOrderFragment extends BaseFragment {
    @ViewInject(R.id.order_rl)
    private  HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    @ViewInject(R.id.ll_empty)
    private LinearLayout llEmpty;
    private int mTab=0;
    private int PAGE_NUM = 1;
    private int state = -1;
    private List<MyOrderdataBean.ResultBean> list;
    private List<MyOrderdataBean.ResultBean> resultBeans=new ArrayList<>();
    private BaseActivity baseActivity;

    public static MyOrderFragment getInstance(int tab) {
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.setArguments(setArguments(tab));
        return fragment;
    }
    public static Bundle setArguments(int tab) {
        Bundle bundle = new Bundle();
        bundle.putInt("tab", tab);
        return bundle;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadData();
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        baseActivity= (BaseActivity) getActivity();

        initView();
        upJieSCartData(1);
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
                clearList(resultBeans);
                upJieSCartData(PAGE_NUM);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                upJieSCartData(PAGE_NUM);
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
    public void clearList(List<MyOrderdataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    private   TextView tv_red_right;
    private String master_order_sn;
    private String allPrice;
    public class OrderfragmentAdapter extends BaseQuickAdapter{

        public OrderfragmentAdapter(List<MyOrderdataBean.ResultBean> data) {
            super(R.layout.item_order_fragment, data);
        }

        @Override
        protected void convert(final com.chad.library.adapter.base.BaseViewHolder baseViewHolder, Object o) {
            baseViewHolder.setText(R.id.tv_shopName,resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_name());
            baseViewHolder.setText(R.id.tv_right_top,resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_status_desc());
            baseViewHolder.setText(R.id.tv_shopping_num,"共"+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_all_num()+" 件商品 合计:￥");
            baseViewHolder.setText(R.id.tv_all_peice,resultBeans.get(baseViewHolder.getAdapterPosition()).getTotal_amount()+"（含运费¥"+
                    resultBeans.get(baseViewHolder.getAdapterPosition()).getShipping_price()+"）");
            TextView tv_red_rightpj=baseViewHolder.getView(R.id.tv_red_rightpj);
            TextView tv_red_right_fk=baseViewHolder.getView(R.id.tv_red_right_fk);
            TextView tv_textRemov=baseViewHolder.getView(R.id.tv_textRemov);

            //所有按钮 1-显示 0 不显示
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getPay_btn()==1){//支付按钮 付款
                tv_red_right_fk.setVisibility(View.VISIBLE);
            }else {
                tv_red_right_fk.setVisibility(View.GONE);
            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getCancel_btn()==1){//取消订单
                baseViewHolder.setText(R.id.tv_text1,"取消订单");
                baseViewHolder.setVisible(R.id.tv_text1,true);
            }else{
                baseViewHolder.setVisible(R.id.tv_text1,false);

            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getReceive_btn()==1){//确认收获
                baseViewHolder.setText(R.id.tv_red_right,"确认收获");
                baseViewHolder.setVisible(R.id.tv_red_right,true);
            }else{
                baseViewHolder.setVisible(R.id.tv_red_right,false);

            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getComment_btn()==1){//评价
                tv_red_rightpj.setVisibility(View.VISIBLE);
            }else{
                tv_red_rightpj.setVisibility(View.GONE);
            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getShipping_btn()==1){//查看物流
                baseViewHolder.setText(R.id.tv_text2,"查看物流");
                baseViewHolder.setVisible(R.id.tv_text2,true);
            }else{
                baseViewHolder.setVisible(R.id.tv_text2,false);

            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getDel_btn()==1){//删除订单

                tv_textRemov.setVisibility(View.VISIBLE);
            }else {
                tv_textRemov.setVisibility(View.GONE);
            }
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getRemind_btn()==1){//提醒发货
                baseViewHolder.setText(R.id.tv_text3,"提醒发货");
                baseViewHolder.setVisible(R.id.tv_text3,true);
            }else{
                baseViewHolder.setVisible(R.id.tv_text3,false);

            }
             tv_red_right= baseViewHolder.getView(R.id.tv_red_right);
            tv_red_right_fk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//立即付款
                    allPrice=resultBeans.get(baseViewHolder.getAdapterPosition()).getTotal_amount();
                    master_order_sn=resultBeans.get(baseViewHolder.getAdapterPosition()).getMaster_order_sn();

                    showPop(tv_red_right);
                }
            });
            tv_red_rightpj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//评价
                    startActivity(new Intent(getActivity(), PostEvaluationActivity.class)
                            .putExtra("order_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id()));
                }
            });
            tv_red_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//确认收获
                    upQRSHData(resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id());
                }
            });
            tv_textRemov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upremoveCartData(resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id());

                }
            });
            final TextView tv_text1= baseViewHolder.getView(R.id.tv_text1);
            tv_text1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//取消订单
                    String text1= tv_text1.getText().toString();
                    upCancleCartData(resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id());
                }
            });
            final TextView tv_text3= baseViewHolder.getView(R.id.tv_text3);
            tv_text3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text3= tv_text3.getText().toString();
                    switch (text3){
                        case "提醒发货":
                            upTXCartData(resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id());
                            break;

                    }
                }
            });
            final TextView tv_text2= baseViewHolder.getView(R.id.tv_text2);
            tv_text2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text2= tv_text2.getText().toString();
                    switch (text2){
                        case "查看物流":
                            startActivity(new Intent(getActivity(), LookWuLiuActivity.class)
                            .putExtra("order_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id()));
                            break;

                    }
                }
            });
            baseViewHolder.getView(R.id.ll_shop).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                            .putExtra("store_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getStore_id())
                            .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                }
            });
           RecyclerView recyclerView_item= baseViewHolder.getView(R.id.recyclerView_item);
            recyclerView_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            recyclerView_item.setAdapter(new BaseRVAdapter(getActivity(),resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list()) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.recy_order_fragment_item;
                }

                @Override
                public void onBind(com.tem.gettogether.base.BaseViewHolder holder, int position) {
                    RoundImageView iv_image_shopping=holder.getView(R.id.iv_image_shopping);
                    Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getImage()).error(R.mipmap.myy322x).into(iv_image_shopping);

                    holder.getTextView(R.id.tv_shopping_name).setText(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getGoods_name());
                    holder.getTextView(R.id.tv_shopping_qpl).setText(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getSpec_key_name());
                    holder.getTextView(R.id.tv_shopping_price).setText("¥"+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getGoods_price()+"/件");
                    holder.getTextView(R.id.tv_shoping_Num).setText("共"+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getGoods_num()+"件");
                    holder.getTextView(R.id.tv_shopping_zt).setText(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_list().get(position).getGoods_sn());
                    holder.getView(R.id.ll_item_dd).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(), OrderXQActivity.class)
                            .putExtra("order_id",resultBeans.get(baseViewHolder.getAdapterPosition()).getOrder_id()));
                        }
                    });
                }


            });
        }

    }
    private void upJieSCartData(int page) {
        Map<String, Object> map = new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        if(mTab==0){
            map.put("type", "");
        }else  if(mTab==1){
            map.put("type", "WAITPAY");

        }else  if(mTab==2){
            map.put("type", "WAITSEND");

        }else  if(mTab==3){
            map.put("type", "WAITRECEIVE");

        }else  if(mTab==4){
            map.put("type", "WAITCCOMMENT_FINISH");
        }

        map.put("page",page);
        XUtil.Post(URLConstant.DINGDAN_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====订单列表===", result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        MyOrderdataBean myOrderdataBean=gson.fromJson(result,MyOrderdataBean.class);
                        if(PAGE_NUM==1){
                            resultBeans=myOrderdataBean.getResult();
                        }else {
                            list=myOrderdataBean.getResult();
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
                OrderfragmentAdapter adapter=new OrderfragmentAdapter(resultBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }
    private void upCancleCartData(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.CANCLE_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====取消订单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        upJieSCartData(1);
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
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }
    private void upTXCartData(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.TIXINGFAHUO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====提醒发货===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        upJieSCartData(1);
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
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }
    private void upremoveCartData(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.SHANCHUDINGDAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====删除订单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        upJieSCartData(1);
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
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }
    private void upQRSHData(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.QUERENSH_LIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====确认收获===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                upJieSCartData(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }
    protected void loadData() {
        mTab = getArguments().getInt("tab");
        if (mTab == 0) {
            state = -1;
        } else if (mTab == 1) {
            state = 100;
        } else if (mTab == 2) {
            state = 101;
        } else if (mTab == 3) {
            state = 2;
        }
        else if (mTab == 4) {
            state = 2;
        }
    }
    private PopupWindow mPop;

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.cart_close_account_layout, null);
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
    private int PayType=0;
    private TextView tv_sure;
    private void setPopClickListener(View view) {
        final ImageView iv_cancle,iv_ye_pay,iv_wecat_pay,iv_zfb_pay;
        iv_cancle = view.findViewById(R.id.iv_cancle);
        iv_ye_pay = view.findViewById(R.id.iv_ye_pay);
        iv_wecat_pay = view.findViewById(R.id.iv_wecat_pay);
        iv_zfb_pay = view.findViewById(R.id.iv_zfb_pay);


        TextView tv_payPrice=view.findViewById(R.id.tv_payPrice);
        LinearLayout ll_ye_pay=view.findViewById(R.id.ll_ye_pay);
        LinearLayout ll_wecat_pay=view.findViewById(R.id.ll_wecat_pay);
        LinearLayout ll_zfb_pay=view.findViewById(R.id.ll_zfb_pay);

        tv_payPrice.setText("¥"+allPrice);

        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        ll_ye_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=1;
                iv_ye_pay.setImageResource(R.drawable.xuanzhongf);
                iv_wecat_pay.setImageResource(R.drawable.weixuanzhong);
                iv_zfb_pay.setImageResource(R.drawable.weixuanzhong);
            }
        });
        ll_zfb_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=2;
                iv_zfb_pay.setImageResource(R.drawable.xuanzhongf);
                iv_ye_pay.setImageResource(R.drawable.weixuanzhong);
                iv_wecat_pay.setImageResource(R.drawable.weixuanzhong);

            }
        });
        ll_wecat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayType=3;
                iv_wecat_pay.setImageResource(R.drawable.xuanzhongf);
                iv_ye_pay.setImageResource(R.drawable.weixuanzhong);
                iv_zfb_pay.setImageResource(R.drawable.weixuanzhong);

            }
        });

        tv_sure=view.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PayType==0){
                    CusToast.showToast("请选择支付方式");
                    return;
                } else if(PayType==1){//余额
                    mPop.dismiss();
                    showPop2(tv_sure);

                }else if(PayType==2){//支付宝
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",master_order_sn);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }else if(PayType==3){//微信
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",master_order_sn);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    upYETXData(map3);
                }
                mPop.dismiss();

            }
        });
    }
    private void  upYETXData(Map<String,Object> map){
        String url = null;
        if(PayType==1){//余额
            url=URLConstant.ORDER_YUE_PAY;
        }else if(PayType==2){//支付宝
            url=URLConstant.ORDER_ALI_PAY;
        }else if(PayType==3){//微信
            url=URLConstant.ORDER_WECHAT_PAY;
        }
        baseActivity.showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====结算单支付===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        if(PayType==1){//余额
                            baseActivity.finish();
                            CusToast.showToast(msg);
                        }else if(PayType==2){//支付宝
                            AliPayBean aliPayBean = gson.fromJson(result, AliPayBean.class);
                            Message msg2 = Message.obtain();
                            msg2.what = TOALIPAY;
                            msg2.obj = aliPayBean.getResult();
                            mHandler.sendMessage(msg2);
                        }else if(PayType==3){//微信
                            WechatDataBean wechatDataBean=gson.fromJson(result,WechatDataBean.class);
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getActivity(), null);
                            msgApi.registerApp(BaseApplication.getInstance().WXAPP_ID);
                            PayReq req = new PayReq();
                            req.appId = wechatDataBean.getResult().getAppid();
                            req.partnerId = wechatDataBean.getResult().getPartnerid();
                            req.prepayId = wechatDataBean.getResult().getPrepayid();
                            req.nonceStr = wechatDataBean.getResult().getNoncestr();
                            req.timeStamp = wechatDataBean.getResult().getTimestamp();
                            req.packageValue = "Sign=WXPay";
                            req.sign = wechatDataBean.getResult().getSign();
                            BaseApplication.getInstance().api.sendReq(req);
                        }

                    }else if(res.equals("-1")){
                        CusToast.showToast(msg);
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
                ex.printStackTrace();
                baseActivity.closeDialog();

            }
        });
    }
    private String paypass;
    private PopupWindow mPopPay;

    //显示弹窗
    private void showPop2(View v) {
        initPop2();
        if (mPopPay.isShowing())
            return;
        //设置弹窗底部位置
        mPopPay.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }
    //初始化弹窗
    private void initPop2() {
        if (mPopPay == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.ye_pay_layout, null);
            mPopPay = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopPay.setFocusable(true);
            mPopPay.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopPay.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopPay.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopPay.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
                }
            });
            ImageView iv_cancle = view.findViewById(R.id.iv_cancle);

            TextView tv_payPrice=view.findViewById(R.id.tv_payPrice);
            tv_payPrice.setText("¥"+allPrice);

            iv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopPay.dismiss();
                }
            });
            //设置弹窗内的点击事件
            final PasswordView2 passView=view.findViewById(R.id.passView);
            passView.setOnFinishInput2(new OnPasswordInputFinish2() {
                @Override
                public void inputFinish() {
                    // 输入完成后我们简单显示一下输入的密码
                    // 也就是说——>实现你的交易逻辑什么的在这里写
                    paypass=passView.getStrPassword();

                }

            });
            view.findViewById(R.id.tv_sure_pay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("master_order_sn",master_order_sn);
                    map3.put("pay_password", paypass);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());

                    Log.i("=====余额支付--",master_order_sn+"--"+paypass);
                    upYETXData(map3);
                    mPopPay.dismiss();
                }
            });
        }
    }
    //------------------------------------------------------------支付宝----------------
    private static final int TOALIPAY = 3;
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    //   Log.e("-----", resultInfo + "---" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        CusToast.showToast("支付成功");
                        getActivity().finish();
//                        setStart(f2ZhiFuBaoBean.getInfo().getPartner(), f2ZhiFuBaoBean.getInfo().getTotal_fee());
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        CusToast.showToast("支付失败");
                    }
                    break;
                }

                case TOALIPAY: {
                    String obj = (String) msg.obj;
                    payV2(obj);
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 支付宝支付业务
     * 支付
     //     * @param v
     */
    public void payV2(final String info) {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        final String orderinfo = "_input_charset=\"" + info.get_input_charset() + "\"&body=\"" + info.getBody() + "\"&notify_url=\"" + info.getNotify_url() + "\"&out_trade_no=\"" + info.getOut_trade_no() + "\"&partner=\"" + info.getPartner() + "\"&payment_type=\"" + info.getPayment_type() + "\"&seller_id=\"" + info.getSeller_id() + "\"&service=\"" + info.getService() + "\"&subject=\"" + info.getSubject() + "\"&total_fee=\"" + info.getTotal_fee() + "\"&sign=\"" + info.getSign() + "\"&sign_type=\"" + info.getSign_type() + "\"";
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(info, true);
//                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


}
