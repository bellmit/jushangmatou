package com.tem.gettogether.activity;

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
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShoppingKuBean;
import com.tem.gettogether.rongyun.CustomizeMessage;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;


@ContentView(R.layout.activity_shopping_ku)
public class ShoppingKUActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private String store_id;
    private List<ShoppingKuBean.ResultBean.GoodsListBean> goodsListBeans=new ArrayList<>();
    private List<ShoppingKuBean.ResultBean.GoodsListBean> listBeans;
    private String targetId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        upshopKuData();

    }

    @Override
    protected void initData() {
        tv_title.setText(getText(R.string.commodity_library));
        store_id=getIntent().getStringExtra("store_id");
        targetId=getIntent().getStringExtra("targetId");
        findViewById(R.id.rl_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingKUActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(goodsListBeans);
                upshopKuData();

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingKUActivity.this)) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                upshopKuData();
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
    public void clearList(List<ShoppingKuBean.ResultBean.GoodsListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    private void upshopKuData(){
        Map<String,Object> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",PAGE_NUM);

        XUtil.Post(URLConstant.SHOPSHOPIINGDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====商品库===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShoppingKuBean shoppingKuBean =gson.fromJson(result,ShoppingKuBean.class);
                        if(PAGE_NUM==1){
                            goodsListBeans=shoppingKuBean.getResult().getGoods_list();
                        }else {
                            listBeans=shoppingKuBean.getResult().getGoods_list();
                            if (listBeans.size()==0){
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                                return;
                            }
                            goodsListBeans.addAll(listBeans);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                ShoppingKudapter adapter=new ShoppingKudapter(goodsListBeans);
                order_rl.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();

            }
        });
    }
    public class ShoppingKudapter extends BaseQuickAdapter {

        public ShoppingKudapter(List<ShoppingKuBean.ResultBean.GoodsListBean> data) {
            super(R.layout.shopping_ku_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_pic=baseViewHolder.getView(R.id.iv_pic);
            Glide.with(ShoppingKUActivity.this).load(goodsListBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_pic);
            baseViewHolder.setText(R.id.tv_shoping_jj,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qpl,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+getText(R.string.from_batch));
            baseViewHolder.getView(R.id.tv_send).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    CusToast.showToast("功能暂未开放");
//                    SharedPreferencesUtils.saveString(ShoppingKUActivity.this, BaseConstant.SPConstant.Shop_goods_id,goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id());
                    CustomizeMessage customizeMessage=new CustomizeMessage(goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id(),goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name(),goodsListBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number(),
                            goodsListBeans.get(baseViewHolder.getAdapterPosition()).getImage(),store_id);
                    byte[] bvvv=customizeMessage.encode();
                    CustomizeMessage richContentMessage=new CustomizeMessage(bvvv);
                    Message myMessage = Message.obtain(targetId, Conversation.ConversationType.PRIVATE, richContentMessage);
                    RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(Message message) {
                            //消息本地数据库存储成功的回调
                        }

                        @Override
                        public void onSuccess(Message message) {
                            //消息通过网络发送成功的回调
                            CusToast.showToast(getText(R.string.sent_successfully));
                            finish();
                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                            //消息发送失败的回调
                            Log.i("====消息发送失败--",message+"--"+errorCode.getMessage());
                        }
                    });
                }
            });
//            baseViewHolder.getView(R.id.ll_All_item).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(ShoppingKUActivity.this,ShoppingParticularsActivity.class)
//                            .putExtra("goods_id",goodsListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
//                }
//            });
        }
    }
}
