package com.tem.gettogether.activity.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ShopKeyBean;
import com.tem.gettogether.bean.ShopTopBean;
import com.tem.gettogether.bean.ShoppingFenLieBean;
import com.tem.gettogether.fragment.ShopDongTFragment;
import com.tem.gettogether.fragment.ShopHomeFragment;
import com.tem.gettogether.fragment.ShopPingJFragment;
import com.tem.gettogether.fragment.ShopShoppingFragment;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.GlideImageLoader;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.RoundImageView;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
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

@ContentView(R.layout.activity_shop)
public class ShopActivity extends BaseActivity {
    @ViewInject(R.id.tv_shou_home)
    private TextView tv_shou_home;
    @ViewInject(R.id.tv_shopping)
    private TextView tv_shopping;
    @ViewInject(R.id.tv_pingjai)
    private TextView tv_pingjai;
    @ViewInject(R.id.tv_dongtai)
    private TextView tv_dongtai;

    @ViewInject(R.id.line1)
    private View line1;
    @ViewInject(R.id.line2)
    private View line2;
    @ViewInject(R.id.line3)
    private View line3;
    @ViewInject(R.id.line4)
    private View line4;
    private int type;
    public static final int SHOPNHOME_TYPE = 201;//首页
    public static final int SHOPSHOPING_TYPE = 202;//商品
    public static final int SHOPPINGJIA_TYPE = 203;//评价
    public static final int SHOPDONGTAI_TYPE = 204;//动态
    private ShopHomeFragment shopHomeFragment;
    private ShopShoppingFragment shopShoppingFragment;
    private ShopPingJFragment shopPingJFragment;
    private ShopDongTFragment shopDongTFragment;
    private BaseFragment baseFragment;
    @ViewInject(R.id.ll_shopping_fenl)
    private LinearLayout ll_shopping_fenl;
    @ViewInject(R.id.ll_shop_xq)
    private LinearLayout ll_shop_xq;
    private PopupWindow mPop;
    private String store_id;
    private ShopTopBean.ResultBean resultBean = new ShopTopBean.ResultBean();
    private List<ShopTopBean.ResultBean.StoreBannerBean> storeBannerBeans = new ArrayList<>();
    private List<ShoppingFenLieBean.ResultBean> resultBeans = new ArrayList<>();
    @ViewInject(R.id.iv_shop_image)
    private RoundImageView iv_shop_image;
    @ViewInject(R.id.tv_shop_name)
    private TextView tv_shop_name;
    @ViewInject(R.id.tv_shop_xj)
    private TextView tv_shop_xj;
    @ViewInject(R.id.tv_shop_peopleNum)
    private TextView tv_shop_peopleNum;
    @ViewInject(R.id.iv_isgz_icon)
    private ImageView iv_isgz_icon;
    @ViewInject(R.id.tv_isgz)
    private TextView tv_isgz;
    @ViewInject(R.id.iv_image_top)
    private ImageView iv_image_top;
    @ViewInject(R.id.et_sousuo)
    private EditText et_sousuo;
    @ViewInject(R.id.rl_sousuo)
    private RelativeLayout rl_sousuo;
    @ViewInject(R.id.banner)
    private Banner banner;

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = this.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();

        shopHomeFragment = new ShopHomeFragment();
        shopShoppingFragment = new ShopShoppingFragment();
        shopPingJFragment = new ShopPingJFragment();
        shopDongTFragment = new ShopDongTFragment();
        if (savedInstanceState != null) {
            baseFragment = (BaseFragment) getSupportFragmentManager().getFragment(savedInstanceState, baseFragment.getClass().getSimpleName());
        } else {
            switch (type) {
                case SHOPNHOME_TYPE:
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    baseFragment = ShopHomeFragment.newInstance();
                    break;
                case SHOPSHOPING_TYPE:
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    baseFragment = ShopShoppingFragment.newInstance();
                    break;
                case SHOPPINGJIA_TYPE:
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.VISIBLE);
                    line4.setVisibility(View.INVISIBLE);
                    tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                    baseFragment = ShopPingJFragment.newInstance();
                    break;
                case SHOPDONGTAI_TYPE:
                    line1.setVisibility(View.INVISIBLE);
                    line2.setVisibility(View.INVISIBLE);
                    line3.setVisibility(View.INVISIBLE);
                    line4.setVisibility(View.VISIBLE);
                    tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                    baseFragment = ShopDongTFragment.newInstance();
                    break;
            }
        }
        if (!baseFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_item_shop, baseFragment, baseFragment.getClass().getSimpleName()).show(baseFragment).commit();
        }
        initView();
    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        store_id = getIntent().getStringExtra("store_id");
        upShopData(store_id);
        upShopFenLieData(store_id);
    }

    @Override
    protected void initView() {


    }

    public void onMessage(List<ShopTopBean.ResultBean.StoreBannerBean> storeBannerBean) {
        if (storeBannerBean == null) return;
        List<String> img = new ArrayList<>();
        for (int i = 0; i < storeBannerBean.size(); i++) {
            img.add(storeBannerBean.get(i).getAd_code());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(img);
        banner.start();

    }

    @Event(value = {R.id.rl_close, R.id.ll_shopping_fenl, R.id.ll_shop_xq, R.id.ll_lianxikefu, R.id.ll_shop_go, R.id.rl_right_more, R.id.ll_shop_home, R.id.rl_sousuo, R.id.ll_shop_shopping, R.id.ll_shop_pingj, R.id.ll_shop_dongt, R.id.ll_is_gz}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_lianxikefu:
                if (SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.ROLE_TYPE, "1").equals("1")) {
                    CusToast.showToast(getText(R.string.supplier_does_not_have_this_feature));
                    return;
                }
                try {

                    if (!SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0").equals("")) {

                        if (resultBean != null && resultBean.getStore_user_id() != null) {
                            RongTalk.doConnection(ShopActivity.this, SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.CHAT_ID, "0")
                                    , resultBean.getStore_user_id(), resultBean.getStore_name(),
                                    resultBean.getStore_logo(), resultBean.getStore_id());
                        } else {
                            CusToast.showToast(getText(R.string.the_store_is_invalid));
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CusToast.showToast(getText(R.string.the_store_is_invalid));
                }

                break;
            case R.id.rl_right_more:
                String sousuo2 = et_sousuo.getText().toString().trim();
                if (sousuo2.equals("")) {
                    CusToast.showToast(getText(R.string.please_enter_a_keyword));
                    return;
                }
                InputMethodManager imm2 = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                imm2.hideSoftInputFromWindow(rl_sousuo.getWindowToken(), 0);
                ShopKeyBean shopKeyBean2 = new ShopKeyBean();
                shopKeyBean2.setSousuoConnect(sousuo2);
                shopKeyBean2.setTypeLX("0x1001");
                shopKeyBean2.setStore_id(store_id);
                EventBus.getDefault().post(shopKeyBean2);
                break;
            case R.id.ll_shop_home:
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.INVISIBLE);
                tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                turnToFragment(baseFragment.getClass(), ShopHomeFragment.class, null);


                break;
            case R.id.ll_shop_shopping:
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.INVISIBLE);
                tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                bundle.putString("store_id", store_id);

                turnToFragment(baseFragment.getClass(), ShopShoppingFragment.class, bundle);

                break;
            case R.id.ll_shop_pingj:
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.INVISIBLE);
                tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                bundle.putString("store_id", store_id);
                turnToFragment(baseFragment.getClass(), ShopPingJFragment.class, bundle);

                break;
            case R.id.ll_shop_dongt:
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.VISIBLE);
                tv_shou_home.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_shopping.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_pingjai.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_dongtai.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                turnToFragment(baseFragment.getClass(), ShopDongTFragment.class, null);
                break;
            case R.id.ll_shopping_fenl://商品分类
                showPop(ll_shopping_fenl);
                break;
            case R.id.ll_shop_xq://店铺详情
                startActivity(new Intent(this, ShopXQActivity.class)
                        .putExtra("is_collect", resultBean.getIs_collect())
                        .putExtra("store_user_id", resultBean.getStore_user_id())
                        .putExtra("store_id", store_id));
                break;
            case R.id.ll_shop_go:
                startActivity(new Intent(this, ShopXQActivity.class)
                        .putExtra("is_collect", resultBean.getIs_collect())
                        .putExtra("store_user_id", resultBean.getStore_user_id())
                        .putExtra("store_id", store_id));


                break;
            case R.id.ll_is_gz://是否关注

                upisGZData(store_id);
                break;
            case R.id.rl_sousuo:
                String sousuo = et_sousuo.getText().toString().trim();
                if (sousuo.equals("")) {
                    CusToast.showToast(getText(R.string.please_enter_a_keyword));
                    return;
                }
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(rl_sousuo.getWindowToken(), 0);
                ShopKeyBean shopKeyBean = new ShopKeyBean();
                shopKeyBean.setSousuoConnect(sousuo);
                shopKeyBean.setTypeLX("0x1001");
                shopKeyBean.setStore_id(store_id);
                ;
                EventBus.getDefault().post(shopKeyBean);

                break;
        }
    }

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.pop_layout_shopping_fl, null);
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
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }

    private void setPopClickListener(View view) {
        RecyclerView recycler_shop_fl = view.findViewById(R.id.recycler_shop_fl);

        recycler_shop_fl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_shop_fl.setAdapter(new BaseRVAdapter(this, resultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.shop_item_fl;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_gl_item).setText(resultBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_gl_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPop.dismiss();
                        ShopKeyBean shopKeyBean = new ShopKeyBean();
                        shopKeyBean.setCat_id(resultBeans.get(position).getCat_id());
                        shopKeyBean.setTypeLX("0x1002");

                        shopKeyBean.setStore_id(store_id);
                        ;
                        EventBus.getDefault().post(shopKeyBean);

//                        CusToast.showToast("选中了"+position);
                    }
                });
            }

        });
    }

    private void upShopData(String store_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("store_id", store_id);
        showDialog();
        XUtil.Post(URLConstant.SHOPHOMEHEAD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取店铺上部数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ShopTopBean shopTopBean = gson.fromJson(result, ShopTopBean.class);
                        resultBean = shopTopBean.getResult();
                        storeBannerBeans = shopTopBean.getResult().getStore_banner();
                        EventBus.getDefault().post(storeBannerBeans);
                        tv_shop_name.setText(resultBean.getStore_name());
                        tv_shop_peopleNum.setText(resultBean.getStore_collect() + "人关注");
                        tv_shop_xj.setText(resultBean.getStore_name());
                        Glide.with(ShopActivity.this).load(resultBean.getStore_logo()).error(R.drawable.head_bg).into(iv_shop_image);

                        Glide.with(ShopActivity.this).load(resultBean.getStore_background()).error(R.drawable.shop_bg).into(iv_image_top);
                        if (resultBean.getIs_collect().equals("0")) {
                            tv_isgz.setText(getText(R.string.attention));
                            iv_isgz_icon.setVisibility(View.VISIBLE);
                            iv_isgz_icon.setImageResource(R.drawable.add_icon_b);
                        } else {
                            tv_isgz.setText(getText(R.string.has_been_concerned));
                            iv_isgz_icon.setVisibility(View.GONE);
                            iv_isgz_icon.setImageResource(R.drawable.yi_guanzhu);
                        }

//                        onMessage(resultBean.getStore_banner());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }

    private void upShopFenLieData(String store_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        showDialog();
        XUtil.Post(URLConstant.SHOPPINGFENLIE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取商品分类===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ShoppingFenLieBean shoppingFenLieBean = gson.fromJson(result, ShoppingFenLieBean.class);
                        resultBeans = shoppingFenLieBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }

    private void upisGZData(String store_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        showDialog();
        XUtil.Post(URLConstant.SHOPISGUANZHU, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    Log.i("====店铺关注===", result + msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (msg.equals(getText(R.string.successful_collection))) {
                            tv_isgz.setText(getText(R.string.has_been_concerned));
                            iv_isgz_icon.setVisibility(View.GONE);
                            iv_isgz_icon.setImageResource(R.drawable.yi_guanzhu);
                        } else if (msg.equals(getText(R.string.cancel_success))) {
                            tv_isgz.setText(getText(R.string.attention));
                            iv_isgz_icon.setVisibility(View.VISIBLE);
                            iv_isgz_icon.setImageResource(R.drawable.add_icon_b);
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
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }


    public void turnToFragment(Class<? extends BaseFragment> fromFragmentClass, Class<? extends BaseFragment> toFragmentClass, Bundle args) {

        FragmentManager fm = getSupportFragmentManager();

        //被切换的Fragment标签
        String fromTag = fromFragmentClass.getSimpleName();
        //切换到的Fragment标签
        String toTag = toFragmentClass.getSimpleName();
        //查找切换的Fragment
        Fragment fromFragment = fm.findFragmentByTag(fromTag);
        Fragment toFragment = fm.findFragmentByTag(toTag);
        //如果要切换到的Fragment不存在，则创建
        if (toFragment == null) {
            try {
                toFragment = toFragmentClass.newInstance();
                toFragment.setArguments(args);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //如果有参数传递，
        if (args != null && !args.isEmpty()) {
            toFragment.getArguments().putAll(args);
        }
        //Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        //设置Fragment切换效果
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        /**
         * 如果要切换到的Fragment没有被Fragment事务添加，则隐藏被切换的Fragment，添加要切换的Fragment
         * 否则，则隐藏被切换的Fragment，显示要切换的Fragment
         */
        if (!toFragment.isAdded()) {
            ft.hide(fromFragment).add(R.id.fragment_item_shop, toFragment, toTag);
        } else {
            ft.hide(fromFragment).show(toFragment);
        }
        baseFragment = (BaseFragment) toFragment;
        //添加到返回堆栈
        //        ft.addToBackStack(tag);
        //不保留状态提交事务
        ft.commitAllowingStateLoss();
    }

}
