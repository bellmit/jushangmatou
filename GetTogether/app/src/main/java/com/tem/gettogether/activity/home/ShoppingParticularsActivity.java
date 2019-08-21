package com.tem.gettogether.activity.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.activity.cart.CloseAccountActivity;
import com.tem.gettogether.activity.cart.ShoppingCartActivity;
import com.tem.gettogether.activity.my.AddressGLActivity;
import com.tem.gettogether.adapter.OrderDetailAdapter;
import com.tem.gettogether.adapter.RollingTextAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CardCloaseBean;
import com.tem.gettogether.bean.JieSuanBean;
import com.tem.gettogether.bean.OrderDetailBean;
import com.tem.gettogether.bean.ShoppingXQBean;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.GlideImageLoader;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
import com.tem.gettogether.view.RollTextItem;
import com.tem.gettogether.view.RoundImageView;
import com.tem.gettogether.view.TextViewSwitcher;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_shopping_particulars)
public class ShoppingParticularsActivity extends BaseActivity {
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.tv_image_num)
    private TextView tv_image_num;
    @ViewInject(R.id.tv_add)
    private TextView tv_add;
    @ViewInject(R.id.tv_num)
    private EditText tv_num;
    @ViewInject(R.id.tv_jian)
    private TextView tv_jian;
    @ViewInject(R.id.recyclerView_pj)
    private RecyclerView recyclerView_pj;
    @ViewInject(R.id.tv_jrgwc)
    private TextView tv_jrgwc;
    @ViewInject(R.id.tv_pj_num)
    private TextView tv_pj_num;
    @ViewInject(R.id.tv_hpd_num)
    private TextView tv_hpd_num;
    @ViewInject(R.id.tv_look_all_pj)
    private TextView tv_look_all_pj;
    @ViewInject(R.id.tv_kd_price)
    private TextView tv_kd_price;
    @ViewInject(R.id.tv_xl_num)
    private TextView tv_xl_num;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.tv_shopping_jj)
    private TextView tv_shopping_jj;
    @ViewInject(R.id.tv_qp_num)
    private TextView tv_qp_num;
    @ViewInject(R.id.ll_shuxing_xz)
    private LinearLayout ll_shuxing_xz;
    @ViewInject(R.id.tv_shopping_sc)
    private TextView tv_shopping_sc;
    @ViewInject(R.id.iv_is_sc)
    private ImageView iv_is_sc;
    @ViewInject(R.id.iv_shop_image)
    private ImageView iv_shop_image;
    @ViewInject(R.id.tv_shop_name)
    TextView tv_shop_name;
    @ViewInject(R.id.tv_shop_jj)
    private TextView tv_shop_jj;
    @ViewInject(R.id.tv_shop_spnum)
    private TextView tv_shop_spnum;
    @ViewInject(R.id.tv_shop_fs)
    private TextView tv_shop_fs;
    @ViewInject(R.id.tv_shop_dt)
    private TextView tv_shop_dt;
    @ViewInject(R.id.iv_shop_isgz)
    private ImageView iv_shop_isgz;
    @ViewInject(R.id.tv_sjgz)
    private TextView tv_sjgz;
    @ViewInject(R.id.tv_ljxj)
    private TextView tv_ljxj;
    @ViewInject(R.id.webView)
    private WebView webView;
    @ViewInject(R.id.ll_openWeb)
    private LinearLayout ll_openWeb;
    @ViewInject(R.id.rl_shard)
    private RelativeLayout rl_shard;
    @ViewInject(R.id.rolltext)
    private TextViewSwitcher rollingText;
    @ViewInject(R.id.order_detail_RecyclerView)
    private RecyclerView order_detail_RecyclerView;

    private List<RollTextItem> data = new ArrayList<>();

    private String goods_id;
    private ShoppingXQBean.ResultBean.GoodsBean goodsBean = new ShoppingXQBean.ResultBean.GoodsBean();
    private ShoppingXQBean.ResultBean.StoreBean storeBean = new ShoppingXQBean.ResultBean.StoreBean();
    private List<ShoppingXQBean.ResultBean.CommentBean> commentBeans = new ArrayList<>();
    private List<ShoppingXQBean.ResultBean.GalleryBean> galleryBeans = new ArrayList<>();
    private List<OrderDetailBean.ResultBean> orderDetails = new ArrayList<>();
    private String sctype;
    private ShareAction mShareAction;
    private UMShareListener mShareListener;
    private IWXAPI wxAPI;
    private String APP_ID = "wx93eea65ba215f901";
    private int allNum;
    private OrderDetailAdapter mOrderDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        wxAPI = WXAPIFactory.createWXAPI(ShoppingParticularsActivity.this, APP_ID, true);
        wxAPI.registerApp(APP_ID);

        initData();
        upShopXQData();
        initOrderData();
    }

    @Override
    protected void initData() {
        goods_id = getIntent().getStringExtra("goods_id");
        Log.i("=====店铺内商品id2--", goods_id + "");
        initLiuLangData();
    }

    @Override
    protected void initView() {
        tv_qp_num.setText(goodsBean.getBatch_number() + "");
        if (goodsBean.getBatch_number() != null && !goodsBean.getBatch_number().equals("")) {
            if (Integer.parseInt(goodsBean.getBatch_number()) > 0) {
                tv_num.setText(goodsBean.getBatch_number() + "");
            } else {
                tv_num.setText("1");
            }

        }

        tv_shopping_jj.setText(goodsBean.getGoods_name() + "");
        tv_kd_price.setText("快递费：0.00");
        tv_xl_num.setText("销量：" + goodsBean.getSales_sum());
        tv_address.setText(storeBean.getProvince() + storeBean.getCity() + "");
        tv_pj_num.setText("评价(" + goodsBean.getComment_count() + ")");
        tv_hpd_num.setText("好评度" + goodsBean.getBest_percent());
        if (goodsBean.getIs_collect() != null) {
            if (goodsBean.getIs_collect().equals("0")) {
                sctype = "0";
                iv_is_sc.setVisibility(View.VISIBLE);
                iv_is_sc.setImageResource(R.drawable.shopping_icon_wsc);
                tv_shopping_sc.setText(getResources().getText(R.string.collect));
            } else {
                sctype = "1";
                iv_is_sc.setVisibility(View.VISIBLE);
                iv_is_sc.setImageResource(R.drawable.shopping_icon_sc);
                tv_shopping_sc.setText("已收藏");
            }
        }
        if (storeBean.getIs_collect() != null) {
            if (storeBean.getIs_collect().equals("0")) {
                iv_shop_isgz.setVisibility(View.GONE);
                tv_sjgz.setText("关注商家");
            } else {
                iv_shop_isgz.setVisibility(View.VISIBLE);
                iv_shop_isgz.setImageResource(R.drawable.guanzhu_xx);
                tv_sjgz.setText("已关注");
            }
        }
        /*if (goodsBean.getIs_enquiry() != null) {
            if (goodsBean.getIs_enquiry().equals("1")) {
                tv_ljxj.setText(getResources().getText(R.string.ask));
            } else {
                tv_ljxj.setText(getResources().getText(R.string.buy));
            }

        } else {
            tv_ljxj.setText(getResources().getText(R.string.buy));
        }*/

        tv_shop_name.setText(storeBean.getStore_name());
        tv_shop_jj.setText(storeBean.getStore_des());
        tv_shop_spnum.setText(storeBean.getGoods_num());
        tv_shop_fs.setText(storeBean.getCollect_num());
        tv_shop_dt.setText("0");
        Glide.with(ShoppingParticularsActivity.this).load(storeBean.getStore_logo()).error(R.mipmap.myy322x).into(iv_shop_image);

        recyclerView_pj.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_pj.setAdapter(new BaseRVAdapter(this, commentBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.shopping_pj_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, int position) {
                if (position == commentBeans.size() - 1) {
                    holder.getView(R.id.view_line).setVisibility(View.GONE);
                }
                ImageView iv_iamge1 = holder.getImageView(R.id.iv_iamge1);
                ImageView iv_iamge2 = holder.getImageView(R.id.iv_iamge2);
                ImageView iv_iamge3 = holder.getImageView(R.id.iv_iamge3);

                CircularImage iv_user_image = holder.getView(R.id.iv_user_image);
                Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getHead_pic()).error(R.mipmap.myy322x).into(iv_user_image);
                holder.getTextView(R.id.tv_user_name).setText(commentBeans.get(position).getNickname());
                holder.getTextView(R.id.tv_pj_time).setText(commentBeans.get(position).getAdd_time());
                if (commentBeans.get(position).getImg().size() >= 3) {
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(1).getLogo()).error(R.mipmap.myy322x).into(iv_iamge2);
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(2).getLogo()).error(R.mipmap.myy322x).into(iv_iamge3);
                } else if (commentBeans.get(position).getImg().size() == 2) {
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(1).getLogo()).error(R.mipmap.myy322x).into(iv_iamge2);
                    iv_iamge3.setVisibility(View.INVISIBLE);
                }
                if (commentBeans.get(position).getImg().size() == 1) {
                    Glide.with(ShoppingParticularsActivity.this).load(commentBeans.get(position).getImg().get(0).getLogo()).error(R.mipmap.myy322x).into(iv_iamge1);
                    iv_iamge2.setVisibility(View.INVISIBLE);
                    iv_iamge3.setVisibility(View.INVISIBLE);
                } else {
                    iv_iamge1.setVisibility(View.GONE);
                    iv_iamge2.setVisibility(View.GONE);
                    iv_iamge3.setVisibility(View.GONE);

                }
                holder.getTextView(R.id.tv_pj_sx).setText(commentBeans.get(position).getSpec_key_name());
                holder.getTextView(R.id.tv_connect).setText(commentBeans.get(position).getContent());
                holder.getTextView(R.id.tv_gmnum).setText(commentBeans.get(position).getGoods_num());


            }


        });
        tv_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!tv_num.getText().toString().equals("")) {
                    allNum = Integer.parseInt(tv_num.getText().toString());
                }
            }
        });

        rollingText.setAdapter(new RollingTextAdapter() {
            @Override
            public int getCount() {
                return data.size() / 2;
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(Context context, View contentView, int position) {
                View view = View.inflate(context, R.layout.item_zhengzailiulan, null);
                ((TextView) view.findViewById(R.id.tv_1)).setText(data.get(position).getName());
                ((ImageView) view.findViewById(R.id.img_1)).setBackgroundResource(data.get(position).getImgId());
                ((TextView) view.findViewById(R.id.tv_detail)).setText(data.get(position).getMsg());
                ((TextView) view.findViewById(R.id.tv_2)).setText(data.get((position + 1) % data.size()).getName());
                ((ImageView) view.findViewById(R.id.img_2)).setBackgroundResource(data.get((position + 1) % data.size()).getImgId());
                ((TextView) view.findViewById(R.id.tv_detail2)).setText(data.get((position + 1) % data.size()).getMsg());
                return view;
            }
        });
        rollingText.startFlipping();
    }

    @Event(value = {R.id.iv_close, R.id.tv_jian, R.id.ll_openWeb, R.id.rl_shard, R.id.rl_kf, R.id.ll_gzsj, R.id.tv_ljxj, R.id.iv_dianpu, R.id.ll_issc, R.id.iv_go_cart, R.id.tv_add, R.id.tv_look_all_pj, R.id.ll_pingjia, R.id.tv_jrgwc, R.id.ll_shuxing_xz, R.id.rl_input_dp, R.id.rl_input_dp2}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.rl_shard:
                showPopShard(rl_shard);
                break;
            case R.id.ll_openWeb:
                ll_openWeb.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_go_cart:
                CardCloaseBean cardCloaseBean = new CardCloaseBean();
                cardCloaseBean.setGoods_id(goods_id);
                cardCloaseBean.setCartClose(true);
                EventBus.getDefault().post(cardCloaseBean);
//                startActivity(new Intent(this, MainActivity.class)
//                        .putExtra("tab", "3"));
                startActivity(new Intent(this, ShoppingCartActivity.class));
                break;
            case R.id.tv_jian:
//                num=Integer.parseInt(tv_num.getText().toString());
                allNum = Integer.parseInt(tv_num.getText().toString());
                if (allNum > 1) {
                    allNum--;
                } else {
                    CusToast.showToast("数量不能少于1");
                    return;
                }

                tv_num.setText(allNum + "");
                break;
            case R.id.tv_add:
                if (!tv_num.getText().toString().equals("")) {
                    allNum = Integer.parseInt(tv_num.getText().toString());
                    allNum++;
                    tv_num.setText(allNum + "");
                } else {
                    allNum = 0;
                }

                break;
            case R.id.ll_pingjia:
            case R.id.tv_look_all_pj:
                startActivity(new Intent(this, ShoppingEvaluateActivity.class)
                        .putExtra("goods_id", goods_id));
                break;
            case R.id.tv_jrgwc:
                if(SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.ROLE_TYPE, "1").equals("1")){
                    CusToast.showToast("供应商暂无此功能");
                }else{
                    showPop(tv_jrgwc);
                }
                break;
            case R.id.ll_shuxing_xz:
                showPop(ll_shuxing_xz);
                break;
            case R.id.ll_issc:
                upShoppingSC(sctype);
                break;
            case R.id.rl_input_dp:
                startActivityForResult(new Intent(ShoppingParticularsActivity.this, ShopActivity.class)
                        .putExtra("store_id", storeBean.getStore_id())
                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                break;
            case R.id.rl_input_dp2:
                startActivityForResult(new Intent(ShoppingParticularsActivity.this, ShopActivity.class)
                        .putExtra("store_id", storeBean.getStore_id())
                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                break;
            case R.id.iv_dianpu:
                startActivityForResult(new Intent(ShoppingParticularsActivity.this, ShopActivity.class)
                        .putExtra("store_id", storeBean.getStore_id())
                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                break;
            case R.id.ll_gzsj:
                upisGZData(storeBean.getStore_id());

                break;
            case R.id.tv_ljxj:
                try {
                    if (BaseApplication.getInstance().userBean != null) {
                        //发消息
                        if (BaseApplication.getInstance().userBean.getChat_id() != null && !BaseApplication.getInstance().userBean.getChat_id().equals("")) {
                            Log.e("====进入聊天界面  IMG===", BaseApplication.getInstance().userBean.getChat_id() + "");
                            if (storeBean != null && storeBean.getStore_user_id() != null) {
                                RongTalk.doConnection(ShoppingParticularsActivity.this, BaseApplication.getInstance().userBean.getChat_id()
                                        , storeBean.getStore_user_id(), storeBean.getStore_name(),
                                        storeBean.getStore_logo(), storeBean.getStore_id());
                            } else {
                                CusToast.showToast("该店铺无效");
                            }

                        }
                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                        CusToast.showToast("请先登录");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    CusToast.showToast("客服无效");
                }

                break;
            case R.id.rl_kf:
                try {
                    if (BaseApplication.getInstance().userBean != null) {
                        //发消息
                        if (BaseApplication.getInstance().userBean.getChat_id() != null && !BaseApplication.getInstance().userBean.getChat_id().equals("")) {
                            Log.e("====进入聊天界面  IMG===", BaseApplication.getInstance().userBean.getChat_id() + "");
                            if (storeBean != null && storeBean.getStore_user_id() != null) {
                                RongTalk.doConnection(ShoppingParticularsActivity.this, BaseApplication.getInstance().userBean.getChat_id()
                                        , storeBean.getStore_user_id(), storeBean.getStore_name(),
                                        storeBean.getStore_logo(), storeBean.getStore_id());
                            } else {
                                CusToast.showToast("该店铺无效");
                            }

                        }
                    } else {
                        CusToast.showToast("请先登录");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    CusToast.showToast("客服无效");
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }

    private PopupWindow mPop;
    private int numpositionCorclo = 0;
    private int numpositionCC = 0;
    private int numpositionML = 0;
    private int numpositionQT = 0;

    private int bannerNum = 0;

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
        final List<Integer> skuid = new ArrayList<>();

        if (mPop == null) {
            final View view = LayoutInflater.from(this).inflate(R.layout.pop_jrgwc_layout, null);
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
            RoundImageView iv_shopping_image = view.findViewById(R.id.iv_shopping_image);
            TextView tv_shopping_name = view.findViewById(R.id.tv_shopping_name);
            TextView tv_shopping_price = view.findViewById(R.id.tv_shopping_price);
            TextView tv_shopping_content = view.findViewById(R.id.tv_shopping_content);
            Glide.with(ShoppingParticularsActivity.this).load(goodsBean.getOriginal_img()).error(R.mipmap.myy322x).into(iv_shopping_image);
            tv_shopping_content.setText(goodsBean.getGoods_name());
            tv_shopping_price.setText(goodsBean.getShop_price());

            tv_shopping_name.setText(goodsBean.getBatch_number() + "件起批");
            final TextView tv_queding = view.findViewById(R.id.tv_queding);
            TextView tv_add_num = view.findViewById(R.id.tv_add_num);
            final EditText tv_num_all = view.findViewById(R.id.tv_num_all);
            tv_num_all.setText(String.valueOf(tv_num.getText().toString()));
            tv_queding.setText("确定(" + tv_num.getText().toString() + ")");
            TextView tv_jian_num = view.findViewById(R.id.tv_jian_num);
            final TextView tv_shuxing2 = view.findViewById(R.id.tv_shuxing2);
            final TextView tv_shuxing1 = view.findViewById(R.id.tv_shuxing1);
            tv_jian_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    allNum = Integer.parseInt(tv_num_all.getText().toString());
                    if (allNum > 1) {
                        allNum--;
                    } else {
                        CusToast.showToast("数量不能少于1");
                        return;
                    }
                    tv_queding.setText("确定(" + allNum + ")");
                    tv_num_all.setText(allNum + "");

                }
            });
            tv_add_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!tv_num_all.getText().toString().equals("")) {
                        allNum = Integer.parseInt(tv_num_all.getText().toString());
                        allNum++;
                        tv_num_all.setText(allNum + "");
                        tv_queding.setText("确定(" + allNum + ")");
                    } else {
                        allNum = 0;
                    }


                }
            });
            tv_num_all.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!tv_num_all.getText().toString().equals("")) {
                        allNum = Integer.parseInt(tv_num_all.getText().toString());
                        tv_num.setText(tv_num_all.getText().toString());
                        tv_queding.setText("确定(" + allNum + ")");
                    }
                }
            });
            final TextView tv_shuxing3 = view.findViewById(R.id.tv_shuxing3);
            final TextView tv_shuxing4 = view.findViewById(R.id.tv_shuxing4);

            if (goodsBean.getGoods_spec_list() != null) {
                if (goodsBean.getGoods_spec_list().size() >= 1) {
                    tv_shuxing1.setText(goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name());
                    view.findViewById(R.id.ll_shuxing2).setVisibility(View.GONE);
                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.GONE);
                    view.findViewById(R.id.ll_shuxing4).setVisibility(View.GONE);
                }
                if (goodsBean.getGoods_spec_list().size() >= 2) {
                    tv_shuxing2.setText(goodsBean.getGoods_spec_list().get(1).get(0).getSpec_name());

                    view.findViewById(R.id.ll_shuxing2).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.GONE);
                    view.findViewById(R.id.ll_shuxing4).setVisibility(View.GONE);
                }
                if (goodsBean.getGoods_spec_list().size() >= 3) {
                    tv_shuxing1.setText(goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name());
                    tv_shuxing2.setText(goodsBean.getGoods_spec_list().get(1).get(0).getSpec_name());
                    tv_shuxing3.setText(goodsBean.getGoods_spec_list().get(2).get(0).getSpec_name());

                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing2).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing4).setVisibility(View.GONE);
                }
                if (goodsBean.getGoods_spec_list().size() >= 4) {
                    tv_shuxing1.setText(goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name());
                    tv_shuxing2.setText(goodsBean.getGoods_spec_list().get(1).get(0).getSpec_name());
                    tv_shuxing3.setText(goodsBean.getGoods_spec_list().get(2).get(0).getSpec_name());
                    tv_shuxing4.setText(goodsBean.getGoods_spec_list().get(3).get(0).getSpec_name());
                    Log.i("====商品属性---", goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name()
                            + "--" + goodsBean.getGoods_spec_list().get(1).get(0).getSpec_name() + goodsBean.getGoods_spec_list().get(2).get(0).getSpec_name()
                            + goodsBean.getGoods_spec_list().get(3).get(0).getSpec_name());
                    view.findViewById(R.id.ll_shuxing4).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing2).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_shuxing3).setVisibility(View.VISIBLE);

                }
            }

            TextView tv_ljgm = view.findViewById(R.id.tv_ljgm);
           /* if (goodsBean.getIs_enquiry() != null) {
                if (goodsBean.getIs_enquiry().equals("1")) {
                    tv_ljgm.setVisibility(View.GONE);
                } else {
                    tv_ljxj.setText(getResources().getString(R.string.buy));
                    tv_ljgm.setVisibility(View.VISIBLE);
                }

            } else {
                tv_ljgm.setVisibility(View.VISIBLE);
            }*/
            //立即购买
            tv_ljgm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPop.dismiss();
                    allNum = Integer.parseInt(tv_num_all.getText().toString());
                    String sku = "";
                    if (goodsBean.getGoods_spec_list() != null) {
                        if (goodsBean.getGoods_spec_list().size() >= 1) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(0).get(numpositionCorclo).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 2) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(1).get(numpositionCC).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 3) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(2).get(numpositionML).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 4) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(3).get(numpositionQT).getItem_id()));
                        }
                        Collections.sort(skuid);
                        String str = "";
                        for (int i = 0; i < skuid.size(); i++) {
                            str += skuid.get(i) + "_";
                        }
                        sku = str.substring(0, str.length() - 1);
                    }

                    try {
//                            String skuidData=object.getString(sku);
//                            JSONObject jsonObject=new JSONObject(skuidData);
//                            String price=jsonObject.optString("price");
//                            String store_count=jsonObject.optString("store_count");
//                        startActivity(new Intent(ShoppingParticularsActivity.this, CloseAccountActivity.class)
//                                .putExtra("unique_id", "1")
//                                .putExtra("goods_id", goods_id)
//                                .putExtra("key", sku)
//                                .putExtra("goods_num", String.valueOf(num_gwc)));
                        Map<String, Object> map = new HashMap<>();
                        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                        map.put("goods_id", goods_id);
                        map.put("goods_num", String.valueOf(allNum));
                        map.put("unique_id", "1");
                        map.put("key", sku);
                        upJieSCartData(map, sku, allNum);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPop = null;

                }
            });
            tv_queding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//加入购物车
                    skuid.clear();
                    allNum = Integer.parseInt(tv_num_all.getText().toString());

                    String sku = null;
                    if (goodsBean.getGoods_spec_list() != null) {
                        if (goodsBean.getGoods_spec_list().size() >= 1) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(0).get(numpositionCorclo).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 2) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(1).get(numpositionCC).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 3) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(2).get(numpositionML).getItem_id()));
                        }
                        if (goodsBean.getGoods_spec_list().size() >= 4) {
                            skuid.add(Integer.parseInt(goodsBean.getGoods_spec_list().get(3).get(numpositionQT).getItem_id()));
                        }
                        Collections.sort(skuid);
                        String str = "";
                        for (int i = 0; i < skuid.size(); i++) {
                            str += skuid.get(i) + "_";
                        }
                        sku = str.substring(0, str.length() - 1);
                    }
                    try {
//                            String skuidData=object.getString(sku);
//                            JSONObject jsonObject=new JSONObject(skuidData);
//                            String price=jsonObject.optString("price");
//                            String store_count=jsonObject.optString("store_count");
                        upAddCart(String.valueOf(allNum), sku);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    mPop.dismiss();
                    mPop = null;
                }
            });
            view.findViewById(R.id.iv_dialog_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPop.dismiss();
                    mPop = null;
                }
            });
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
            RecyclerView recyView_color = view.findViewById(R.id.recyView_color);
            recyView_color.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
            RecyclerView recyView_color3 = view.findViewById(R.id.recyView_color3);
            recyView_color3.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
            RecyclerView recyView_color4 = view.findViewById(R.id.recyView_color4);
            recyView_color4.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

            if (goodsBean.getGoods_spec_list() != null && goodsBean.getGoods_spec_list().size() >= 1) {
                recyclerView.setAdapter(new BaseRVAdapter(this, goodsBean.getGoods_spec_list().get(0)) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.dialog_gwc_color;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position) {

                        final TextView tv_color_xz = holder.getTextView(R.id.tv_color_xz);
                        tv_color_xz.setText(goodsBean.getGoods_spec_list().get(0).get(position).getItem());
                        tv_shuxing1.setText(goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name());
                        for (int ii = 0; ii < goodsBean.getGoods_spec_list().get(0).size(); ii++) {
                            Log.i("===商品属性1", goodsBean.getGoods_spec_list().get(0).get(ii).getItem());
                        }
                        if (numpositionCorclo == position) {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                        } else {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.text3));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_text9_1bg));
                        }
                        tv_color_xz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                                tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                                numpositionCorclo = position;
                                notifyDataSetChanged();
                            }
                        });
                    }

                });
            }
            if (goodsBean.getGoods_spec_list() != null && goodsBean.getGoods_spec_list().size() >= 2) {
                recyView_color.setAdapter(new BaseRVAdapter(this, goodsBean.getGoods_spec_list().get(1)) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.dialog_gwc_color;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position2) {

                        final TextView tv_color_xz = holder.getTextView(R.id.tv_color_xz);
                        tv_color_xz.setText(goodsBean.getGoods_spec_list().get(1).get(position2).getItem());
                        for (int ii = 0; ii < goodsBean.getGoods_spec_list().get(1).size(); ii++) {
                            Log.i("===商品属性2", goodsBean.getGoods_spec_list().get(1).get(ii).getItem());
                        }
                        if (numpositionCC == position2) {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                        } else {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.text3));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_text9_1bg));
                        }
                        tv_color_xz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                                tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                                numpositionCC = position2;
                                notifyDataSetChanged();
                            }
                        });
                    }

                });
            }
            if (goodsBean.getGoods_spec_list() != null && goodsBean.getGoods_spec_list().size() >= 3) {
                recyView_color3.setAdapter(new BaseRVAdapter(this, goodsBean.getGoods_spec_list().get(2)) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.dialog_gwc_color;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position2) {

                        final TextView tv_color_xz = holder.getTextView(R.id.tv_color_xz);
                        tv_color_xz.setText(goodsBean.getGoods_spec_list().get(2).get(position2).getItem());
                        tv_shuxing3.setText(goodsBean.getGoods_spec_list().get(2).get(0).getSpec_name());
                        for (int ii = 0; ii < goodsBean.getGoods_spec_list().get(2).size(); ii++) {
                            Log.i("===商品属性3", goodsBean.getGoods_spec_list().get(2).get(ii).getItem());
                        }
                        if (numpositionML == position2) {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                        } else {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.text3));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_text9_1bg));
                        }
                        tv_color_xz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                                tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                                numpositionML = position2;
                                notifyDataSetChanged();
                            }
                        });
                    }

                });
            }
            if (goodsBean.getGoods_spec_list() != null && goodsBean.getGoods_spec_list().size() >= 4) {
                recyView_color4.setAdapter(new BaseRVAdapter(this, goodsBean.getGoods_spec_list().get(3)) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.dialog_gwc_color;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position2) {

                        final TextView tv_color_xz = holder.getTextView(R.id.tv_color_xz);
                        tv_color_xz.setText(goodsBean.getGoods_spec_list().get(3).get(position2).getItem());
                        for (int ii = 0; ii < goodsBean.getGoods_spec_list().get(3).size(); ii++) {
                            Log.i("===商品属性4", goodsBean.getGoods_spec_list().get(3).get(ii).getItem());
                        }
                        tv_shuxing1.setText(goodsBean.getGoods_spec_list().get(0).get(0).getSpec_name());
                        tv_shuxing2.setText(goodsBean.getGoods_spec_list().get(1).get(0).getSpec_name());
                        tv_shuxing3.setText(goodsBean.getGoods_spec_list().get(2).get(0).getSpec_name());
                        tv_shuxing4.setText(goodsBean.getGoods_spec_list().get(3).get(0).getSpec_name());
                        if (numpositionQT == position2) {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                        } else {
                            tv_color_xz.setTextColor(getResources().getColor(R.color.text3));
                            tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_text9_1bg));
                        }
                        tv_color_xz.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                tv_color_xz.setTextColor(getResources().getColor(R.color.home_red));
                                tv_color_xz.setBackground(getResources().getDrawable(R.drawable.shuxing_yx_red1bg));
                                numpositionQT = position2;
                                notifyDataSetChanged();
                            }
                        });
                    }

                });
            }


        }
    }

    private JSONObject object;

    private void upShopXQData() {
        Log.d("chenshichun", "-----goods_id  " + goods_id);
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("goods_id", goods_id);

        showDialog();
        XUtil.Post(URLConstant.SHOPFENLEI_XIANGQING, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.i("===商品详情--", result.toString());

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ShoppingXQBean shoppingXQBean = gson.fromJson(result, ShoppingXQBean.class);
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject1 = new JSONObject(result2);
                        String skudata = jsonObject1.optString("spec_goods_price");
                        goodsBean = shoppingXQBean.getResult().getGoods();
                        storeBean = shoppingXQBean.getResult().getStore();
                        commentBeans = shoppingXQBean.getResult().getComment();
                        galleryBeans = shoppingXQBean.getResult().getGallery();
//                        if (skudata != null && !skudata.equals("")) {
//                            Log.i("===商品详情--", "" + skudata);
//                            object = new JSONObject(skudata);
//                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                final List<String> img = new ArrayList<>();
                for (int i = 0; i < galleryBeans.size(); i++) {
                    img.add(galleryBeans.get(i).getImage_url());
                }
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(img);
//                banner.isAutoPlay(false);

                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        bannerNum = position;
                        tv_image_num.setText(position + "/" + galleryBeans.size());

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                banner.start();
                findViewById(R.id.iv_banner).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent2 = new Intent(ShoppingParticularsActivity.this, ShowImageDetail.class);
                        intent2.putStringArrayListExtra("paths", (ArrayList<String>) img);
                        intent2.putExtra("index", bannerNum);
                        startActivity(intent2);
//                        }

                    }
                });

                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
                settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
                if (webView != null) {
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            //   handler.sendEmptyMessage(1);
                            super.onPageFinished(view, url);
                        }
                    });
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl(goodsBean.getDetail());
                    Log.i("===h5显示--", goodsBean.getDetail());

                }
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }

    private void upAddCart(final String goods_num, String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goods_id);
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("goods_num", goods_num);
        if (key != null && !key.equals("")) {
            map.put("key", key);
        }
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key2 = iterator.next();
                Object value = map.get(key2);
                Log.e("--加入购物车--" + key2, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.ADDCART, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("===加入购物车--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();

                    }
                    CusToast.showToast(msg);

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

    private JieSuanBean.ResultBean.AddressListBean addressListBeans = new JieSuanBean.ResultBean.AddressListBean();

    private void upJieSCartData(Map<String, Object> map, final String sku, final int num_ljgm) {

        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--立即购买结算单--" + key, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.JIESUAN_PAY, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====立即购买结算单===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        JieSuanBean jieSuanBean = gson.fromJson(result, JieSuanBean.class);
                        addressListBeans = jieSuanBean.getResult().getAddressList();
                        String cart_ids = jieSuanBean.getResult().getCart_ids();

                        upHQpriceData(cart_ids, sku, num_ljgm);
                    } else if (res.equals("-1")) {
                        CusToast.showToast(msg);
                        return;
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
                ex.printStackTrace();
            }
        });
    }

    private void upHQpriceData(String cartIds, final String sku, final int num_ljgm) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        if (addressListBeans.getAddress_id() != null) {
            map.put("address_id", addressListBeans.getAddress_id() + "");
        }
//        map.put("act", "");
        map.put("ids", cartIds);
        showDialog();
        XUtil.Post(URLConstant.TIJIAO_DiNDDAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取订单价格===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
//                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        startActivity(new Intent(ShoppingParticularsActivity.this, CloseAccountActivity.class)
                                .putExtra("unique_id", "1")
                                .putExtra("goods_id", goods_id)
                                .putExtra("key", sku)
                                .putExtra("goods_num", String.valueOf(num_ljgm)));
                    } else if (res.equals("-1")) {
                        startActivity(new Intent(ShoppingParticularsActivity.this, AddressGLActivity.class));
                        CusToast.showToast(msg);
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
                ex.printStackTrace();
            }
        });
    }

    private void upShoppingSC(final String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goods_id);
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("type", type);
        showDialog();
        XUtil.Post(URLConstant.SHOPPING_SHOUCANG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("===商品收藏--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (sctype.equals("0")) {
                            iv_is_sc.setVisibility(View.VISIBLE);
                            iv_is_sc.setImageResource(R.drawable.shopping_icon_sc);
                            sctype = "1";
                            tv_shopping_sc.setText("已收藏");
                        } else {
                            sctype = "0";
                            iv_is_sc.setVisibility(View.VISIBLE);
                            iv_is_sc.setImageResource(R.drawable.shopping_icon_wsc);

                            tv_shopping_sc.setText("收藏");

                        }
                    }
                    CusToast.showToast(msg);

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
        if (BaseApplication.getInstance().userBean == null) return;
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
                        if (msg.equals("收藏成功")) {
                            tv_sjgz.setText("已关注");
                        } else if (msg.equals("取消成功")) {
                            tv_sjgz.setText("关注商家");
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

    private PopupWindow mPopShard;
    private View viewShard;
    private TextView tv_xz_title;

    //显示弹窗
    private void showPopShard(View v) {
        initPopShard();
        if (mPopShard.isShowing())
            return;
        //设置弹窗底部位置
        mPopShard.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private void initPopShard() {
        if (mPopShard == null) {
            viewShard = LayoutInflater.from(this).inflate(R.layout.main_shard_layout, null);
            mPopShard = new PopupWindow(viewShard, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopShard.setFocusable(true);
            mPopShard.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopShard.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopShard.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopShard.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            TextView tv_dialog_close = viewShard.findViewById(R.id.tv_dialog_close);
            tv_xz_title = viewShard.findViewById(R.id.tv_xz_title);
//            mShareAction.open();
            viewShard.findViewById(R.id.ll_wechat).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WXWebpageObject webpage = new WXWebpageObject();
                    webpage.webpageUrl = goodsBean.getDetail();//分享url
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = "来自聚商码头";
                    msg.description = goodsBean.getGoods_name();
                    Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    msg.thumbData = bmpToByteArray(thumb);
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;
                    req.scene = SendMessageToWX.Req.WXSceneSession;//好友
                    wxAPI.sendReq(req);
                    mPopShard.dismiss();
                }
            });
            viewShard.findViewById(R.id.ll_wxcircle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WXWebpageObject webpage = new WXWebpageObject();
                    webpage.webpageUrl = goodsBean.getDetail();//分享url
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = "来自聚商码头";
                    msg.description = goodsBean.getGoods_name();
                    Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    msg.thumbData = bmpToByteArray(thumb);//封面图片byte数组
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;//朋友圈
                    wxAPI.sendReq(req);
                    mPopShard.dismiss();
                }
            });
            viewShard.findViewById(R.id.ll_QQ).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UMWeb web = new UMWeb(goodsBean.getDetail() + "");
                    web.setTitle("来自聚商码头");
                    web.setDescription(goodsBean.getGoods_name());
                    web.setThumb(new UMImage(ShoppingParticularsActivity.this, R.mipmap.ic_launcher));
                    new ShareAction(ShoppingParticularsActivity.this).withMedia(web)
                            .setPlatform(SHARE_MEDIA.QQ)
                            .setCallback(mShareListener)
                            .share();
                    mPopShard.dismiss();
                }
            });
            viewShard.findViewById(R.id.ll_qzone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UMWeb web = new UMWeb(goodsBean.getDetail() + "");
                    web.setTitle("来自聚商码头");
                    web.setDescription(goodsBean.getGoods_name());
                    web.setThumb(new UMImage(ShoppingParticularsActivity.this, R.mipmap.ic_launcher));
                    new ShareAction(ShoppingParticularsActivity.this).withMedia(web)
                            .setPlatform(SHARE_MEDIA.QZONE)
                            .setCallback(mShareListener)
                            .share();
                    mPopShard.dismiss();
                }
            });
            tv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopShard.dismiss();
                }
            });

        }
    }

    /**
     * 得到Bitmap的byte
     *
     * @param bmp 图片
     * @return 返回压缩的图片
     */
    private static byte[] bmpToByteArray(Bitmap bmp) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void initLiuLangData() {
        data.add(new RollTextItem("来自伊朗的小地雷正在查看该商品", R.drawable.head_icon0, "小地雷"));
        data.add(new RollTextItem("来自埃及的高双正在查看该商品", R.drawable.head_icon1, "高双"));
        data.add(new RollTextItem("来自肯尼亚的cuckoo正在查看该商品", R.drawable.head_icon2, "cuckoo"));
        data.add(new RollTextItem("来自埃及的zhufeng正在查看该商品", R.drawable.head_icon3, "zhufeng"));
    }

    private void initOrderData() {
        OrderDetailBean.ResultBean mOrderDetailBean = new OrderDetailBean.ResultBean("Waithaka", "肯尼亚", "黑色", "32", "面议", "1600件", "2019-05-28");
        orderDetails.add(mOrderDetailBean);
        OrderDetailBean.ResultBean mOrderDetailBean1 = new OrderDetailBean.ResultBean("Talla Diakhate", "塞内加尔", "黑色", "32", "面议", "1700件", "2019-06-07");
        orderDetails.add(mOrderDetailBean1);
        OrderDetailBean.ResultBean mOrderDetailBean2 = new OrderDetailBean.ResultBean("Mohammed", "伊拉克", "黑色", "32", "面议", "1400件", "2019-06-17");
        orderDetails.add(mOrderDetailBean2);
        OrderDetailBean.ResultBean mOrderDetailBean3 = new OrderDetailBean.ResultBean("Mavas", "埃及", "黑色", "32", "面议", "4000件", "2019-06-29");
        orderDetails.add(mOrderDetailBean3);

        mOrderDetailAdapter = new OrderDetailAdapter(getContext(), orderDetails);
        order_detail_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        order_detail_RecyclerView.setAdapter(mOrderDetailAdapter);
    }
}
