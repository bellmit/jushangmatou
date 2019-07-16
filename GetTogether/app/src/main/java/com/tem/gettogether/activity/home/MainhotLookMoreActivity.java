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
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MainHotTJBean;
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
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

@ContentView(R.layout.activity_mainhot_look_more)
public class MainhotLookMoreActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
   private String bottomType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initData();
        initView();
    }

    @Override
    protected void initData() {
        bottomType=getIntent().getStringExtra("bottomType");
        if(bottomType.equals("1")){
            tv_title.setText(getResources().getText(R.string.selling));
        }else{
            tv_title.setText(getResources().getText(R.string.special));
        }
        upHottuijian(Integer.parseInt(bottomType));
    }

    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(MainhotLookMoreActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM=1;
                clearList(houtResultBeans);
                upHottuijian(Integer.parseInt(bottomType));

            }
            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(MainhotLookMoreActivity.this)) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                upHottuijian(Integer.parseInt(bottomType));

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
    public void clearList(List<MainHotTJBean.ResultBean.ListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    private List<MainHotTJBean.ResultBean.ListBean> houtResultBeans=new ArrayList<>();
    private List<MainHotTJBean.ResultBean.ListBean> list;
    private void upHottuijian(int i){
        Map<String,Object> map=new HashMap<>();
        String url;
        if(i==1){
            url= URLConstant.HOT_TUIJIAN;
        }else {
            url=URLConstant.HOT_TEJIAZUANQU;

        }
        map.put("page",PAGE_NUM);
       showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====热卖推荐--==", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    order_refresh_fragment.endRefreshing();
                    order_refresh_fragment.endLoadingMore();
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        MainHotTJBean homeSCFLDataBean=gson.fromJson(result,MainHotTJBean.class);
                        list=homeSCFLDataBean.getResult().getList();
                        if (ListUtils.isEmpty(list)){
                            UiUtils.toast("没有更新的数据");
                            return;
                        }
                        houtResultBeans.addAll(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                MoreRecommecndAdapter adapter=new MoreRecommecndAdapter(houtResultBeans);
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
    public class MoreRecommecndAdapter extends BaseQuickAdapter {
        public MoreRecommecndAdapter( List<MainHotTJBean.ResultBean.ListBean> data) {
            super(R.layout.bk_recommecnd_item, data);
        }
        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_image=baseViewHolder.getView(R.id.iv_image);
            Glide.with(MainhotLookMoreActivity.this).load(houtResultBeans.get(baseViewHolder.getAdapterPosition()).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
            baseViewHolder.setText(R.id.tv_name,houtResultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qigou,houtResultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number()+"件起批");
            baseViewHolder.setText(R.id.tv_price,houtResultBeans.get(baseViewHolder.getAdapterPosition()).getShop_price());
            baseViewHolder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainhotLookMoreActivity.this,ShoppingParticularsActivity.class)
                            .putExtra("goods_id",houtResultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });

        }
    }
}
