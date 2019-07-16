package com.tem.gettogether.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShoppingPJBean;
import com.tem.gettogether.bean.SouSuoDataBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.NetWorkUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
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

@ContentView(R.layout.activity_shopping_evaluate)
public class ShoppingEvaluateActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.order_rl)
    private HomeListFreshRecyclerView order_rl;
    @ViewInject(R.id.order_refresh_fragment)
    private BGARefreshLayout order_refresh_fragment;
    private int PAGE_NUM = 1;
    @ViewInject(R.id.tv_text1)
    private TextView tv_text1;
    @ViewInject(R.id.tv_text2)
    private TextView tv_text2;
    @ViewInject(R.id.tv_text3)
    private TextView tv_text3;
    @ViewInject(R.id.tv_text4)
    private TextView tv_text4;
    @ViewInject(R.id.ll_item1)
    private LinearLayout ll_item1;
    @ViewInject(R.id.ll_item2)
    private LinearLayout ll_item2;
    @ViewInject(R.id.ll_item3)
    private LinearLayout ll_item3;
    @ViewInject(R.id.ll_item4)
    private LinearLayout ll_item4;
    private String goods_id;
    private String commentType="1";
    private List<ShoppingPJBean.ResultBean.CommentListBean> list;
    private List<ShoppingPJBean.ResultBean.CommentListBean> commentListBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        upShoppingSC(commentType);
    }
    @Override
    protected void initData() {
        tv_title.setText("评价");
        goods_id=getIntent().getStringExtra("goods_id");

    }
    @Override
    protected void initView() {
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingEvaluateActivity.this)) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;

                }
                clearList(commentListBeans);
                upShoppingSC(commentType);

            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(ShoppingEvaluateActivity.this)) {
                    CusToast.showToast( "请检查网络");
                    return false;
                }
                PAGE_NUM++;
                upShoppingSC(commentType);

                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(this, 1));
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
    public void clearList(List<ShoppingPJBean.ResultBean.CommentListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    @Event(value = {R.id.rl_close,R.id.ll_item1,R.id.ll_item2,R.id.ll_item3,R.id.ll_item4}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_item1:
                tv_text1.setTextColor(getResources().getColor(R.color.white));
                ll_item1.setBackground(getResources().getDrawable(R.drawable.pingjia_bg5dp));

                tv_text2.setTextColor(getResources().getColor(R.color.text3));
                ll_item2.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text3.setTextColor(getResources().getColor(R.color.text3));
                ll_item3.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text4.setTextColor(getResources().getColor(R.color.text3));
                ll_item4.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                commentType="1";
                upShoppingSC(commentType);
                break;
            case R.id.ll_item2:
                tv_text2.setTextColor(getResources().getColor(R.color.white));
                ll_item2.setBackground(getResources().getDrawable(R.drawable.pingjia_bg5dp));

                tv_text1.setTextColor(getResources().getColor(R.color.text3));
                ll_item1.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));

                tv_text3.setTextColor(getResources().getColor(R.color.text3));
                ll_item3.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text4.setTextColor(getResources().getColor(R.color.text3));
                ll_item4.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                commentType="2";
                upShoppingSC(commentType);
                break;
            case R.id.ll_item3:
                tv_text3.setTextColor(getResources().getColor(R.color.white));
                ll_item3.setBackground(getResources().getDrawable(R.drawable.pingjia_bg5dp));

                tv_text1.setTextColor(getResources().getColor(R.color.text3));
                ll_item1.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text2.setTextColor(getResources().getColor(R.color.text3));
                ll_item2.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text4.setTextColor(getResources().getColor(R.color.text3));
                ll_item4.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                commentType="3";
                upShoppingSC(commentType);
                break;
            case R.id.ll_item4:
                tv_text4.setTextColor(getResources().getColor(R.color.white));
                ll_item4.setBackground(getResources().getDrawable(R.drawable.pingjia_bg5dp));
                tv_text1.setTextColor(getResources().getColor(R.color.text3));
                ll_item1.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text2.setTextColor(getResources().getColor(R.color.text3));
                ll_item2.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                tv_text3.setTextColor(getResources().getColor(R.color.text3));
                ll_item3.setBackground(getResources().getDrawable(R.drawable.pingjia_wxz_bg5dp));
                commentType="4";
                upShoppingSC(commentType);
                break;
        }
    }
    public class ShoppingPJAdapter extends BaseQuickAdapter {


        public ShoppingPJAdapter(List<ShoppingPJBean.ResultBean.CommentListBean> data) {
            super(R.layout.shopping_pj_item, data);
        }
        @Override
        protected void convert(BaseViewHolder baseViewHolder, Object o) {
            baseViewHolder.getView(R.id.view_line).setVisibility(View.GONE);

            ImageView iv_iamge1 = baseViewHolder.getView(R.id.iv_iamge1);
            ImageView iv_iamge2 =  baseViewHolder.getView(R.id.iv_iamge2);
            ImageView iv_iamge3 = baseViewHolder.getView(R.id.iv_iamge3);

            CircularImage iv_user_image = baseViewHolder.getView(R.id.iv_user_image);
            Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getHead_pic()).error(R.drawable.img12x).into(iv_user_image);
            baseViewHolder.setText(R.id.tv_user_name,commentListBeans.get(baseViewHolder.getAdapterPosition()).getNickname());
            baseViewHolder.setText(R.id.tv_pj_time,commentListBeans.get(baseViewHolder.getAdapterPosition()).getAdd_time());
            if (commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().size() >= 3) {
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(1).getLogo()).error(R.mipmap.myy322x).into(iv_iamge2);
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(2).getLogo()).error(R.mipmap.myy322x).into(iv_iamge3);
            } else if (commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().size() == 2) {
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(1).getLogo()).error(R.mipmap.myy322x).into(iv_iamge2);
                iv_iamge3.setVisibility(View.INVISIBLE);
            }
            if (commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().size() == 1) {
                Glide.with(ShoppingEvaluateActivity.this).load(commentListBeans.get(baseViewHolder.getAdapterPosition()).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                iv_iamge2.setVisibility(View.INVISIBLE);
                iv_iamge3.setVisibility(View.INVISIBLE);
            } else {
                iv_iamge1.setVisibility(View.GONE);
                iv_iamge2.setVisibility(View.GONE);
                iv_iamge3.setVisibility(View.GONE);

            }
            baseViewHolder.setText(R.id.tv_pj_sx,commentListBeans.get(baseViewHolder.getAdapterPosition()).getSpec_key_name());
            baseViewHolder.setText(R.id.tv_connect,commentListBeans.get(baseViewHolder.getAdapterPosition()).getContent());
            baseViewHolder.setText(R.id.tv_gmnum,commentListBeans.get(baseViewHolder.getAdapterPosition()).getGoods_num());

        }
    }
    private void upShoppingSC(String commentType){
        Map<String,Object> map=new HashMap<>();
        map.put("goods_id","244");
        map.put("commentType", commentType);
        showDialog();
        XUtil.Post(URLConstant.SHOPPING_PINGJIA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("===商品评价--",result);
                order_refresh_fragment.endRefreshing();
                order_refresh_fragment.endLoadingMore();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        ShoppingPJBean shoppingPJBean=gson.fromJson(result,ShoppingPJBean.class);
                        list=shoppingPJBean.getResult().getComment_list();
                        if (ListUtils.isEmpty(list)){
                            UiUtils.toast("没有更新的数据");
                            return;
                        }
                        commentListBeans.addAll(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                ShoppingPJAdapter shoppingPJAdapter=new ShoppingPJAdapter(commentListBeans);
                order_rl.setAdapter(shoppingPJAdapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }
}
