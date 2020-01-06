package com.tem.gettogether.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SCShoppingBean;
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

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_shopping)
public class SCShoppingFragment extends BaseFragment {
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
    @ViewInject(R.id.ll_top)
    private LinearLayout ll_top;
    private BaseActivity baseActivity;
    private List<SCShoppingBean.ResultBean> resultBeans = new ArrayList<>();

    public static SCShoppingFragment newInstance() {
        return new SCShoppingFragment();
    }

    private Effectstype effect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        baseActivity = (BaseActivity) getActivity();

        initView();
        upShopLBData(PAGE_NUM);

        super.onActivityCreated(savedInstanceState);
    }

    public void initView() {
        ll_top.setVisibility(View.GONE);
        order_refresh_fragment.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    if (order_refresh_fragment.getCurrentRefreshStatus() == BGARefreshLayout.RefreshStatus.REFRESHING) {
                        order_refresh_fragment.endRefreshing();
                    }
                    return;
                }
                PAGE_NUM = 1;
                clearList(resultBeans);
                upShopLBData(PAGE_NUM);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                if (!NetWorkUtils.isNetworkAvailable(getContext())) {
                    CusToast.showToast(getResources().getText(R.string.please_check_the_network));
                    return false;
                }
                PAGE_NUM++;
                upShopLBData(PAGE_NUM);
                return true;
            }
        });
        order_rl.setLayoutManager(new GridLayoutManager(getContext(), 1));
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        // 设置下拉刷新
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.color_F3F5F4);//背景色
        refreshViewHolder.setPullDownRefreshText("" + getResources().getText(R.string.refresh_pull_down_text));//下拉的提示文字
        refreshViewHolder.setReleaseRefreshText("" + getResources().getText(R.string.refresh_release_text));//松开的提示文字
        refreshViewHolder.setRefreshingText("" + getResources().getText(R.string.refresh_ing_text));//刷新中的提示文字

        // 设置下拉刷新和上拉加载更多的风格
        order_refresh_fragment.setRefreshViewHolder(refreshViewHolder);
        order_refresh_fragment.shouldHandleRecyclerViewLoadingMore(order_rl);


    }

    public void clearList(List<SCShoppingBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    @Event(value = {R.id.tv_zonghe, R.id.tv_pfl, R.id.tv_xl}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_zonghe:
                tv_zonghe.setTextColor(getResources().getColor(R.color.home_red));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));

                break;
            case R.id.tv_pfl:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.home_red));
                tv_xl.setTextColor(getResources().getColor(R.color.text3));

                break;
            case R.id.tv_xl:
                tv_zonghe.setTextColor(getResources().getColor(R.color.text3));
                tv_pfl.setTextColor(getResources().getColor(R.color.text3));
                tv_xl.setTextColor(getResources().getColor(R.color.home_red));

                break;

        }
    }

    public class ShoppingAdapter extends BaseQuickAdapter {

        public ShoppingAdapter(List<SCShoppingBean.ResultBean> data) {
            super(R.layout.shopping_fragment_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, Object o) {
            ImageView iv_pic = baseViewHolder.getView(R.id.iv_pic);
            Glide.with(getActivity()).load(resultBeans.get(baseViewHolder.getAdapterPosition()).getImage()).error(R.mipmap.myy322x).into(iv_pic);
            baseViewHolder.setText(R.id.tv_shoping_jj, resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_name());
            baseViewHolder.setText(R.id.tv_qpl, resultBeans.get(baseViewHolder.getAdapterPosition()).getBatch_number() + getText(R.string.from_batch));
            if (resultBeans.get(baseViewHolder.getAdapterPosition()).getIs_enquiry() != null && resultBeans.get(baseViewHolder.getAdapterPosition()).getIs_enquiry().equals("1")) {
                baseViewHolder.setText(R.id.tv_price, getText(R.string.negotiable_tv));
            } else {
                baseViewHolder.setText(R.id.tv_price, "￥" + resultBeans.get(baseViewHolder.getAdapterPosition()).getShop_price());

            }
            baseViewHolder.getView(R.id.ll_All_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), ShoppingParticularsActivity.class)
                            .putExtra("goods_id", resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id()));
                }
            });
            baseViewHolder.getView(R.id.ll_All_item).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final LogoutDialogBuilder dialogLogout = new LogoutDialogBuilder(getActivity(), R.style.dialog_untran);
                    effect = Effectstype.SHAKE;
                    dialogLogout.isvisibiliby();
                    dialogLogout
                            .withTitleColor("#333333")                                  //def
                            .withDividerColor("#333333")                              //def
                            .withMessage(getText(R.string.qdscgspm))                     //.withMessage(null)  no Msg
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

                                    upShoppingSC(resultBeans.get(baseViewHolder.getAdapterPosition()).getGoods_id());
                                    dialogLogout.dismiss();

                                }
                            })
                            .show();

                    return true;
                }
            });
        }
    }

    private void upShoppingSC(String goods_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goods_id);
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("type", "1");

        XUtil.Post(URLConstant.SHOPPING_SHOUCANG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);

                Log.i("===商品删除--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CusToast.showToast(getText(R.string.delete_seccuss));
                        upShopLBData(PAGE_NUM);
                    } else {
                        CusToast.showToast(msg);

                    }

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


            }
        });
    }

    private void upShopLBData(final int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("page", page);
        String yuyan = SharedPreferencesUtils.getLanguageString(getContext(), BaseConstant.SPConstant.language, "");
        map.put("language", yuyan);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.SC_SHOPPINGLIEBIAO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====收藏商品列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    order_refresh_fragment.endRefreshing();
                    order_refresh_fragment.endLoadingMore();
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        SCShoppingBean scShoppingBean = gson.fromJson(result, SCShoppingBean.class);
                        if (page > 1) {
                            if (scShoppingBean.getResult().size() < 1) {
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                            } else {
                                resultBeans.addAll(scShoppingBean.getResult());
                            }
                        } else {
                            resultBeans = scShoppingBean.getResult();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                ShoppingAdapter adapter = new ShoppingAdapter(resultBeans);
                order_rl.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();

            }
        });
    }
}
