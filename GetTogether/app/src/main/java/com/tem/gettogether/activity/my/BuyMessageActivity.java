package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.BuyLieBiaoBean;
import com.tem.gettogether.bean.ChanPinLiebiaoBean;
import com.tem.gettogether.bean.MyZJBean;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
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
@ContentView(R.layout.activity_buy_message)
public class BuyMessageActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    private List<BuyLieBiaoBean.ResultBean> resultBeans=new ArrayList<>();
    private List<BuyLieBiaoBean.ResultBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        clearList(resultBeans);
        Map<String,Object> map3=new HashMap<>();
        map3.put("page","1");
        map3.put("token", BaseApplication.getInstance().userBean.getToken());
        upShopData(map3);
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.ksbh);

    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(BuyMessageActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(resultBeans);
                Map<String,Object> map3=new HashMap<>();
                map3.put("page",PAGE_NUM);
                map3.put("token", BaseApplication.getInstance().userBean.getToken());

                upShopData(map3);


            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(BuyMessageActivity.this)) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                Map<String,Object> map3=new HashMap<>();
                map3.put("page",PAGE_NUM);
                map3.put("token", BaseApplication.getInstance().userBean.getToken());

                upShopData(map3);
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 1));
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
    public void clearList(List<BuyLieBiaoBean.ResultBean> list) {
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
    private void  upShopData(Map<String,Object> map){
        XUtil.Post(URLConstant.SHOPPING_QIUGOULIEBIAO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                Log.i("====求购信息列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if(res.equals("1")){
                        Gson gson=new Gson();
                        BuyLieBiaoBean buyLieBiaoBean=gson.fromJson(result,BuyLieBiaoBean.class);
                        if(PAGE_NUM==1){
                            resultBeans=buyLieBiaoBean.getResult();
                        }else{
                            list=buyLieBiaoBean.getResult();
                            if (list.size()==0){
                                CusToast.showToast("没有数据了");
                                return;
                            }
                            resultBeans.addAll(list);
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

        public ShoppingAdapter(List<BuyLieBiaoBean.ResultBean> data) {//
            super(R.layout.recycler_qglb_iteam, data);
        }

        @Override
        protected void convert(final com.chad.library.adapter.base.BaseViewHolder baseViewHolder, Object o) {
            TextView tv_name=baseViewHolder.getView(R.id.tv_name);
            TextView tv_shopName=baseViewHolder.getView(R.id.tv_shopName);
            TextView tv_shop_ms=baseViewHolder.getView(R.id.tv_shop_ms);
            TextView tv_shop_fl=baseViewHolder.getView(R.id.tv_shop_fl);
            TextView tv_qglx=baseViewHolder.getView(R.id.tv_qglx);
            TextView tv_jhTime=baseViewHolder.getView(R.id.tv_jhTime);
            TextView tv_qgNum=baseViewHolder.getView(R.id.tv_qgNum);
            TextView tv_fbTime=baseViewHolder.getView(R.id.tv_fbTime);
            RecyclerView recy_image=baseViewHolder.getView(R.id.recy_image);
            tv_name.setText(getResources().getText(R.string.user_name)+""+resultBeans.get(baseViewHolder.getAdapterPosition()).getMobile());
            tv_shopName.setText("商品名称："+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            tv_shop_ms.setText("产品描述："+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_desc());
            tv_shop_fl.setText("产品分类："+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_cate());
            tv_qglx.setText("求购类型："+resultBeans.get(baseViewHolder.getAdapterPosition()).getRelease_type());
            tv_jhTime.setText("交货时间："+resultBeans.get(baseViewHolder.getAdapterPosition()).getAttach_time());
            tv_qgNum.setText("求购数量："+resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_num());
            tv_fbTime.setText("发布时间："+resultBeans.get(baseViewHolder.getAdapterPosition()).getAdd_time());
            TextView tv_zxgt=baseViewHolder.getView(R.id.tv_zxgt);
            tv_zxgt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if(BaseApplication.getInstance().userBean!=null){
                            if (BaseApplication.getInstance().userBean.getChat_id()!=null&&!BaseApplication.getInstance().userBean.getChat_id().equals("")) {
                                Log.e("====进入聊天界面  IMG===", BaseApplication.getInstance().userBean.getChat_id()+"");

                                if(resultBeans!=null&&resultBeans.get(baseViewHolder.getAdapterPosition()).getUser_id()!=null){
                                    RongTalk.doConnection(BuyMessageActivity.this, BaseApplication.getInstance().userBean.getChat_id()
                                            ,resultBeans.get(baseViewHolder.getAdapterPosition()).getUser_id(),"",
                                            "","");
                                }else{
                                    CusToast.showToast("该店铺无效");
                                }

                            }
                        }else{
                            CusToast.showToast("token失效");
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                        CusToast.showToast("该店铺无效");
                    }
                }
            });

            recy_image.setLayoutManager(new GridLayoutManager(BuyMessageActivity.this,3, LinearLayoutManager.VERTICAL,false));
            if(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_logo().size()>0){
                recy_image.setAdapter(new BaseRVAdapter(BuyMessageActivity.this,resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_logo()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.recyqgxx_image_item;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, int position) {
                        ImageView iv_iamge_qg=holder.getImageView(R.id.iv_iamge_qg);
                        Glide.with(BuyMessageActivity.this).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_logo().get(position)).error(R.mipmap.myy322x).into(iv_iamge_qg);

                        iv_iamge_qg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                               Intent intent = new Intent(BuyMessageActivity.this, ShowImageDetail.class);
                                intent.putStringArrayListExtra("paths", (ArrayList<String>) resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_logo());
                                intent.putExtra("index", String.valueOf(resultBeans.get(baseViewHolder.getAdapterPosition())));
                                startActivity(intent);
                            }
                        });
                    }

                });

            }



        }
    }
}
