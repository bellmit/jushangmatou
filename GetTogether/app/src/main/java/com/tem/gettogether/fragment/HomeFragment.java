package com.tem.gettogether.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.classification.ClassificationActivity;
import com.tem.gettogether.activity.home.BKRecommecdActivity;
import com.tem.gettogether.activity.home.ClothingBazaarActivity;
import com.tem.gettogether.activity.home.HomeGongGaoActivity;
import com.tem.gettogether.activity.home.HomeSouSuoActivity;
import com.tem.gettogether.activity.home.MainHotTJActivity;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.activity.my.XeiYiH5Activity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeDataBean;
import com.tem.gettogether.bean.HomeGFLBean;
import com.tem.gettogether.bean.MainHotTJBean;
import com.tem.gettogether.utils.GlideImageLoader;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.TransformationUtils;
import com.tem.gettogether.utils.UiUtils;
import com.tem.gettogether.utils.language.LanguageBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.recyclerView_tab)
    private RecyclerView recyclerView_tab;
    private List<String> list2;
    private BaseRVAdapter baseRVAdapter;
    @ViewInject(R.id.recy_shopping_item)
    private RecyclerView recy_shopping_item;
    @ViewInject(R.id.recy_hot_item)
    private RecyclerView recy_hot_item;
    @ViewInject(R.id.recy_tjzq_item)
    private RecyclerView recy_tjzq_item;

    @ViewInject(R.id.recy_shop_tj_item)
    private RecyclerView recy_shop_tj_item;
    private   BaseActivity baseActivity;
    private int isxz=0;
    @ViewInject(R.id.tv_gonggao)
    private TextView tv_gonggao;
    private List<HomeDataBean.ResultBean.AdBean> adBeans=new ArrayList<>();
    private List<HomeDataBean.ResultBean.BottomCateBean> bottomCateBeans=new ArrayList<>();
    private HomeDataBean.ResultBean.NoticeBean noticeBeans=new HomeDataBean.ResultBean.NoticeBean();
    private List<HomeDataBean.ResultBean.PavilionBean> pavilionBeans=new ArrayList<>();
    private List<HomeDataBean.ResultBean.StoreBean> storeBeans=new ArrayList<>();
    private List<HomeDataBean.ResultBean.TopCateBean> topCateBeans=new ArrayList<>();
    private List<HomeGFLBean.ResultBean> resultGFLList=new ArrayList<>();
    private HomeGFLBean.ResultBean resultGFLBean=new HomeGFLBean.ResultBean();
    private  BaseRVAdapter baseRVAdapterSC;

    @ViewInject(R.id.ll_rmtj)
    private LinearLayout ll_rmtj;
    @ViewInject(R.id.ll_tjzq)
    private LinearLayout ll_tjzq;
    @ViewInject(R.id.tv_hotrmTJ)
    private TextView tv_hotrmTJ;
    @ViewInject(R.id.tv_hotTjzq)
    private TextView tv_hotTjzq;
    @ViewInject(R.id.view_line1)
    private View view_line1;
    @ViewInject(R.id.view_line2)
    private View view_line2;
    private String titleTab;
    @ViewInject(R.id.recyclerView_bottom)
    private RecyclerView recyclerView_bottom;
    private int bottomType=1;
    @ViewInject(R.id.iv_bottom_imag)
    private ImageView iv_bottom_imag;
    @ViewInject(R.id.ll_look_more)
    private TextView ll_look_more;
    @ViewInject(R.id.tv_YuYQH)
    private TextView tv_YuYQH;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
        EventBus.getDefault().register(this);

        initdata();
        languageQH();
    }
    private void languageQH(){
        String yuyan=SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            if(yuyan.equals("en")){
                upQieHuanYY(1,yuyan);
            } else if(yuyan.equals("ara")){
                upQieHuanYY(0,yuyan);
            }else {
                upQieHuanYY(0,"");
            }
        }

    }
    private void initdata() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3,LinearLayoutManager.VERTICAL,false));
        recyclerView_tab.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recy_shopping_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recy_hot_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recy_tjzq_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        recy_shop_tj_item.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView_bottom.setLayoutManager(new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private  int bannerNum=0;
    public void initView() {
        List<String> img = new ArrayList<>();
        for (int i=0;i<adBeans.size();i++){
            img.add(adBeans.get(i).getAd_code());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(img);
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(adBeans.get(position).getType()==1){
                    if(adBeans.get(position).getAd_link()!=null&&!adBeans.get(position).getAd_link().equals("")){
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                    .putExtra("store_id",adBeans.get(position).getAd_link())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                        }

                    }else {
                        CusToast.showToast("暂无店铺ID");
                    }
                }else if(adBeans.get(position).getType()==2){
                    if(adBeans.get(position).getAd_link()!=null&&!adBeans.get(position).getAd_link().equals("")){
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(),XeiYiH5Activity.class)
                                    .putExtra("typeMain","1")
                                    .putExtra("h5url",adBeans.get(position).getAd_link()));
                        }

                    }else {
                        CusToast.showToast("暂无链接");
                    }

                }else {
                    CusToast.showToast("暂无操作");
                }
            }
        });


        tv_gonggao.setText(noticeBeans.getTitle());
        recyclerView.setAdapter(new BaseRVAdapter(getActivity(), bottomCateBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.home_layout_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                if(bottomCateBeans.get(position).getMobile_name()!=null&&!bottomCateBeans.get(position).getMobile_name().equals("")){
                    holder.getTextView(R.id.tv_name).setText(bottomCateBeans.get(position).getMobile_name()+"");

                }else{
                    holder.getTextView(R.id.tv_name).setText("暂无数据");
                }
                if(bottomCateBeans.get(position).getMobile_des()!=null&&!bottomCateBeans.get(position).getMobile_des().equals("")){
                    holder.getTextView(R.id.tv_name_dec).setText(bottomCateBeans.get(position).getMobile_des()+"");

                }else{
                    holder.getTextView(R.id.tv_name_dec).setText("暂无数据");
                }
                if(position==2||position==5){
                    holder.getView(R.id.view_line_right).setVisibility(View.GONE);
                }
                if(position==0||position==1||position==2){
                    holder.getView(R.id.view_line_bo).setVisibility(View.VISIBLE);
                }
                ImageView iv_left_image=holder.getImageView(R.id.iv_left_image);
                ImageView iv_right_image=holder.getImageView(R.id.iv_right_image);
                Glide.with(getActivity()).load(bottomCateBeans.get(position).getApp_bottom_pic()) .asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(new TransformationUtils(iv_left_image));
                Glide.with(getActivity()).load(bottomCateBeans.get(position).getApp_bottom_pic()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_right_image);

                holder.getView(R.id.ll_bktj_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*startActivity(new Intent(getActivity(), BKRecommecdActivity.class)
                        .putExtra("category_id",bottomCateBeans.get(position).getId()));*/
                        startActivity(new Intent(getContext(), ClassificationActivity.class)
                                .putExtra("classification_id",bottomCateBeans.get(position).getCategory_id())
                                .putExtra("classification_type",2)
                                .putExtra("classification_name",bottomCateBeans.get(position).getName()));
                    }
                });


            }


        });
//        mList_title = new ArrayList<>();
//        list_fragment = new ArrayList<>();

//        if(pavilionBeans!=null){
//            for (int title=0;title<pavilionBeans.size();title++){
//                mList_title.add(pavilionBeans.get(title).getPavilion_name());
//                ps_tab.addTab(ps_tab.newTab());
//                ps_tab.getTabAt(title).setText(pavilionBeans.get(title).getPavilion_name());
//                TabMainFragment mFragment = TabMainFragment.getInstance(title);
//                list_fragment.add(title, mFragment);
//            }
////            for (int a = 0; a < mList_title.size(); a++) {
////                ps_tab.addTab(ps_tab.newTab());
////                ps_tab.getTabAt(a).setText(mList_title.get(a));
////                TabMainFragment mFragment = TabMainFragment.getInstance(a);
////                list_fragment.add(a, mFragment);
////            }
//        }

        baseRVAdapter=new BaseRVAdapter(getActivity(),pavilionBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.home_layout_tab_item;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_tab_title).setText(pavilionBeans.get(position).getPavilion_name()+"");
                if(isxz==position){
                    holder.getTextView(R.id.tv_tab_title).setTextColor(getResources().getColor(R.color.home_red));
                    holder.getView(R.id.view_line).setBackgroundColor(getResources().getColor(R.color.home_red));
                }else{
                    holder.getTextView(R.id.tv_tab_title).setTextColor(getResources().getColor(R.color.text3));
                    holder.getView(R.id.view_line).setBackgroundColor(getResources().getColor(R.color.white));
                }

                holder.getView(R.id.ll_tab_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                    holder.getTextView(R.id.tv_tab_title).setTextColor(getResources().getColor(R.color.home_red));
                                    holder.getView(R.id.view_line).setBackgroundColor(getResources().getColor(R.color.home_red));
                                    isxz=position;
                        baseRVAdapter.notifyDataSetChanged();
                        upHomeTitle(pavilionBeans.get(position).getPavilion_id());

                    }
                });

            }

        };
        recyclerView_tab.setAdapter(baseRVAdapter);


        recy_shop_tj_item.setAdapter(new BaseRVAdapter(getActivity(),storeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.home_shop_tj_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                ImageView iv_shop_image=holder.getImageView(R.id.iv_shop_image);
                CircularImage iv_shop_head=holder.getView(R.id.iv_shop_head);
                holder.getTextView(R.id.tv_shop_dec).setText(storeBeans.get(position).getStore_des()+"");

                holder.getTextView(R.id.tv_shop_name).setText(storeBeans.get(position).getStore_name()+"");
                Glide.with(getActivity()).load(storeBeans.get(position).getStore_banner()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_shop_image);
                Glide.with(getActivity()).load(storeBeans.get(position).getStore_logo()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_shop_head);

                holder.getView(R.id.ll_shop_tj).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                    .putExtra("store_id",storeBeans.get(position).getStore_id())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                        }

                    }
                });
            }


        });

    }
    @Event(value = {R.id.rl_saoyisao,R.id.tv_YuYQH,R.id.tv_gonggao,R.id.tv_sousuo,R.id.ll_rmtj,R.id.ll_tjzq,R.id.ll_look_more}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_YuYQH:
                showPop(tv_YuYQH);
                break;
            case R.id.rl_saoyisao:
                //点击二维码扫码
//                Intent mipcaIntent = new Intent();
//                mipcaIntent.setClass(getActivity(),MipcaActivityCapture.class);
//                startActivityForResult(mipcaIntent,900);
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                        .putExtra("store_id","0"));
                break;
            case R.id.tv_gonggao:
                startActivity(new Intent(getActivity(),HomeGongGaoActivity.class)
                .putExtra("article_id",noticeBeans.getArticle_id()));
                break;
            case R.id.tv_sousuo:
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                .putExtra("store_id","0"));
                break;
            case R.id.ll_rmtj:
                bottomType=1;
                startActivity(new Intent(getActivity(),MainHotTJActivity.class)
                .putExtra("bottomType","1"));
//                PAGENUM=1;
//                clearList(houtResultBeans);
//                upHottuijian(bottomType);
//                tv_hotrmTJ.setTextColor(getResources().getColor(R.color.my_yellow));
//                tv_hotTjzq.setTextColor(getResources().getColor(R.color.black));
//                view_line1.setVisibility(View.VISIBLE);
//                view_line2.setVisibility(View.INVISIBLE);

                break;
            case R.id.ll_tjzq:
                bottomType=2;
                startActivity(new Intent(getActivity(),MainHotTJActivity.class)
                        .putExtra("bottomType","2"));
//                PAGENUM=1;
//                clearList(houtResultBeans);
//                upHottuijian(bottomType);
//                tv_hotTjzq.setTextColor(getResources().getColor(R.color.my_yellow));
//                tv_hotrmTJ.setTextColor(getResources().getColor(R.color.black));
//                view_line2.setVisibility(View.VISIBLE);
//                view_line1.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_look_more:
                PAGENUM++;
                upHottuijian(bottomType);
//                startActivity(new Intent(getActivity(),MainhotLookMoreActivity.class)
//                .putExtra("bottomType",String.valueOf(bottomType)));
                break;
        }
    }
    public void clearList(List<MainHotTJBean.ResultBean.ListBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.TOP, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }

    private PopupWindow mPop;
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_layout_yuyan, null);
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

    private void setPopClickListener(View view) {
        view.findViewById(R.id.rl_item).setOnClickListener(this);
        view.findViewById(R.id.rl_item2).setOnClickListener(this);
        view.findViewById(R.id.rl_item3).setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void loadData() {

    }
    private void initSetBottomdata(){
        Glide.with(getActivity()).load(hotBannerBean.getAd_code()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_bottom_imag);
        iv_bottom_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hotBannerBean.getAd_link()!=null&&!hotBannerBean.getAd_link().equals("")){
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }else {
                        startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                .putExtra("store_id",hotBannerBean.getAd_link())
                                .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                    }

                }else {
                    CusToast.showToast("暂无店铺ID");
                }
            }
        });
        recyclerView_bottom.setAdapter(new BaseRVAdapter(getActivity(),houtResultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.bk_recommecnd_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                ImageView iv_image=holder.getView(R.id.iv_image);
                Glide.with(getActivity()).load(houtResultBeans.get(position).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
               holder.getTextView(R.id.tv_name).setText(houtResultBeans.get(position).getGoods_name());
                holder.getTextView(R.id.tv_qigou).setText(houtResultBeans.get(position).getBatch_number()+"件起批");
                holder.getTextView(R.id.tv_price).setText(houtResultBeans.get(position).getShop_price());
                holder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                                    .putExtra("goods_id",houtResultBeans.get(position).getGoods_id()));
                        }

                    }
                });

            }

        });
    }
    private int PAGENUM=1;
    private List<MainHotTJBean.ResultBean.ListBean> houtResultBeans=new ArrayList<>();
    private List<MainHotTJBean.ResultBean.ListBean> list;
    private MainHotTJBean.ResultBean.BannerBean hotBannerBean=new MainHotTJBean.ResultBean.BannerBean();
    private List<MainHotTJBean.ResultBean.ListBean> houtResultBeans2=new ArrayList<>();

    private void upHottuijian(final int i){
        Map<String,Object> map=new HashMap<>();
        String url;
        if(i==1){
            url=URLConstant.HOT_TUIJIAN;
        }else {
            url=URLConstant.HOT_TEJIAZUANQU;

        }
        map.put("page",PAGENUM);
        baseActivity.showDialog();
        XUtil.Post(url,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====热卖推荐--==", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        MainHotTJBean homeSCFLDataBean=gson.fromJson(result,MainHotTJBean.class);
//                        houtResultBeans=homeSCFLDataBean.getResult().getList();
                        if(i==1){
                            hotBannerBean=homeSCFLDataBean.getResult().getBanner();
                            list=homeSCFLDataBean.getResult().getList();
                            if (ListUtils.isEmpty(list)){
                                UiUtils.toast("没有更新的数据");
                                return;
                            }
                            houtResultBeans.addAll(list);
                            recy_hot_tj();
                        }else {
                            hotBannerBean=homeSCFLDataBean.getResult().getBanner();
                            list=homeSCFLDataBean.getResult().getList();
                            if (ListUtils.isEmpty(list)){
                                UiUtils.toast("没有更新的数据");
                                return;
                            }
                            houtResultBeans2.addAll(list);
                            recy_tjzq();
                        }

                        initSetBottomdata();

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
                ex.printStackTrace();
            }
        });
    }
    private void recy_hot_tj(){
        recy_hot_item.setAdapter(new BaseRVAdapter(getActivity(), houtResultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recy_home_hot_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                ImageView iv_image=holder.getView(R.id.iv_image);
                Glide.with(getActivity()).load(houtResultBeans.get(position).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
                holder.getTextView(R.id.tv_name).setText(houtResultBeans.get(position).getGoods_name());
                holder.getTextView(R.id.tv_qigou).setText(houtResultBeans.get(position).getBatch_number()+"件起批");
                holder.getTextView(R.id.tv_price).setText(houtResultBeans.get(position).getShop_price());
                holder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                                    .putExtra("goods_id",houtResultBeans.get(position).getGoods_id()));
                        }

                    }
                });
            }

        });
    }
    private void recy_tjzq(){
        recy_tjzq_item.setAdapter(new BaseRVAdapter(getActivity(), houtResultBeans2) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recy_home_hot_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                ImageView iv_image=holder.getView(R.id.iv_image);
                Glide.with(getActivity()).load(houtResultBeans2.get(position).getOriginal_img()).error(R.mipmap.myy322x).into(iv_image);
                holder.getTextView(R.id.tv_name).setText(houtResultBeans2.get(position).getGoods_name());
                holder.getTextView(R.id.tv_qigou).setText(houtResultBeans2.get(position).getBatch_number()+"件起批");
                holder.getTextView(R.id.tv_price).setText(houtResultBeans2.get(position).getShop_price());
                holder.getView(R.id.ll_item_all2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(BaseApplication.getInstance().userBean==null){
                            startActivity(new Intent(getActivity(),LoginActivity.class));
                        }else {
                            startActivity(new Intent(getActivity(),ShoppingParticularsActivity.class)
                                    .putExtra("goods_id",houtResultBeans2.get(position).getGoods_id()));
                        }

                    }
                });
            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 900) {
            String result = data.getStringExtra("result");
          Log.i("=====二维码扫描---",result);
        }
    }
    private void upHomeData(){
        Map<String,Object> map=new HashMap<>();
        String yuyan=SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.HONEDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====首页数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeDataBean homeDataBean=gson.fromJson(result,HomeDataBean.class);
                        adBeans=homeDataBean.getResult().getAd();
                        topCateBeans=homeDataBean.getResult().getTop_cate();
                        bottomCateBeans=homeDataBean.getResult().getBottom_cate();
                        pavilionBeans=homeDataBean.getResult().getPavilion();
                        storeBeans=homeDataBean.getResult().getStore();
                        noticeBeans=homeDataBean.getResult().getNotice();
                        initView();
                        if(pavilionBeans.size()>=1){
                            upHomeTitle(pavilionBeans.get(0).getPavilion_id());
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

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();

            }
        });
    }

    private void upHomeTitle(int fenleiId){
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        map.put("pavilion_id",fenleiId);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.HONE_GUANFENLIELIEBIO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====首页市场分类数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeGFLBean homeSCFLDataBean=gson.fromJson(result,HomeGFLBean.class);
                        resultGFLBean=homeSCFLDataBean.getResult();
                        List<String> list=new ArrayList<>();
                        list.add("");
//                        resultGFLList=homeSCFLDataBean.getResult();
                        baseRVAdapterSC=new BaseRVAdapter(getActivity(),list) {
                            @Override
                            public int getLayoutId(int viewType) {
                                return R.layout.recy_home_shopping_item;
                            }
                            @Override
                            public void onBind(BaseViewHolder holder, final int position) {
                                ImageView iv_FL_pic=holder.getImageView(R.id.iv_FL_pic);
                                    holder.getTextView(R.id.tv_Fl_zl).setText(resultGFLBean.getPavilion_cate().size()+"款");
                                    Glide.with(getActivity()).load(resultGFLBean.getApp_img()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_FL_pic);
                                    holder.getTextView(R.id.tv_title).setText(resultGFLBean.getApp_title());
                                iv_FL_pic.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(resultGFLBean.getApp_link()!=null&&!resultGFLBean.getApp_link().equals("")){
                                            if(BaseApplication.getInstance().userBean==null){
                                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                            }else {
                                                startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                                        .putExtra("store_id",resultGFLBean.getApp_link())
                                                        .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                                            }

                                        }else{
                                            CusToast.showToast("暂无店铺Id");
                                        }
                                    }
                                });
                                RecyclerView recycler_gfl=holder.getView(R.id.recycler_gfl);
                                recycler_gfl.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                                recycler_gfl.setAdapter(new BaseRVAdapter(getActivity(),resultGFLBean.getPavilion_cate()) {
                                    @Override
                                    public int getLayoutId(int viewType) {
                                        return R.layout.recy_homegfl_item;
                                    }

                                    @Override
                                    public void onBind(BaseViewHolder holder, final int position2) {
                                        ImageView iv_FL_image=holder.getImageView(R.id.iv_FL_image);
                                        if(resultGFLBean.getPavilion_cate().size()>0){
                                            holder.getTextView(R.id.iv_FL_name).setText(resultGFLBean.getPavilion_cate().get(position2).getName()+"");
                                            Glide.with(getActivity()).load(resultGFLBean.getPavilion_cate().get(position2).getApp_banner()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_FL_image);

                                        }
                                        holder.getView(R.id.ll_fenlei_item1).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if(resultGFLBean.getPavilion_cate().size()>0){
                                                    startActivity(new Intent(getActivity(), ClothingBazaarActivity.class)
                                                            .putExtra("title",resultGFLBean.getPavilion_cate().get(position2).getName())
                                                            .putExtra("parent_id",resultGFLBean.getPavilion_cate().get(position2).getPavilion_cate_id()));

                                                }else{
                                                    CusToast.showToast("暂无数据");
                                                }

                                            }
                                        });
                                    }

                                });

                            }
                        };
                        recy_shopping_item.setAdapter(baseRVAdapterSC);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();

                baseRVAdapterSC.notifyDataSetChanged();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    /**
     * 语言切换
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LanguageBean.LanguageRefresh bean) {
        if (bean != null) {
            if(bean.getRefresh().equals("1")){
                Log.e("====首页翻译==", "========1111");

                languageQH();
            }
        }
    }
    private void upQieHuanYY(final int tupe_yy, final String  yuyan){
        Map<String,Object> map=new HashMap<>();
        if(tupe_yy!=0){
            map.put("language",yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.YUYAN_QIEHUAN,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.e("====首页翻译=="+yuyan+"--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        upHomeData();
                        upHottuijian(1);
                        upHottuijian(2);

                    }
                } catch (Exception e) {
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
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_item:
                DisplayMetrics dm = getResources().getDisplayMetrics();
                Configuration config = getResources().getConfiguration();
                // 应用用户选择语言
                config.locale = Locale.CHINESE;
                getResources().updateConfiguration(config,dm);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"zh");
                mPop.dismiss();

//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                getActivity().startActivity(intent);
//
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(0);
                restartApplication(getActivity());
                break;
            case R.id.rl_item2:
                DisplayMetrics dm2 = getResources().getDisplayMetrics();
                Configuration config2 = getResources().getConfiguration();
                // 应用用户选择语言
                config2.locale = Locale.ENGLISH;
                getResources().updateConfiguration(config2,dm2);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"en");
                mPop.dismiss();

//                Intent intent2 = new Intent(getActivity(), MainActivity.class);
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                getActivity().startActivity(intent2);
//
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(0);
                restartApplication(getActivity());
                break;
            case R.id.rl_item3:
                DisplayMetrics dm3 = getResources().getDisplayMetrics();
                Configuration config3 = getResources().getConfiguration();
                // 应用用户选择语言
                config3.locale = new Locale("ar");
                getResources().updateConfiguration(config3,dm3);
                SharedPreferencesUtils.saveString(getActivity(),BaseConstant.SPConstant.language,"ara");
                mPop.dismiss();
                restartApplication(getActivity());
                break;
        }
    }

    public void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}