package com.jsmt.developer.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.home.HomeSouSuoActivity;
import com.jsmt.developer.activity.home.ShopActivity;
import com.jsmt.developer.activity.shopping.ShopFLConnectActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.BaseFragment;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.FenLieLeftBean;
import com.jsmt.developer.bean.FenLieRightBean;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.language.LanguageBean;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

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
import java.util.Map;

import cc.duduhuo.custoast.CusToast;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_fen_lei)
public class FenLeiFragment extends BaseFragment {

    @ViewInject(R.id.recyclerView_left)
    private RecyclerView recyclerView_left;
    @ViewInject(R.id.recyclerView_right)
    private RecyclerView recyclerView_right;
    private BaseActivity baseActivity;
    private List<FenLieRightBean.ResultBean> resultBeans = new ArrayList<>();
    private List<FenLieLeftBean.ResultBean> resultBeanList = new ArrayList<>();
    @ViewInject(R.id.tv_yuyQH)
    private TextView tv_yuyQH;
    private  BaseRVAdapter leftAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);

        baseActivity = (BaseActivity) getActivity();
        initData();
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
    private void initData() {
        recyclerView_left.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_right.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
    private int itemtype=-1;
    private boolean isOpen=true;
    private void initView() {
        recyclerView_left.setAdapter(new BaseRVAdapter(getActivity(), resultBeanList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fl_recy_left_title_layout;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_left).setText(resultBeanList.get(position).getMobile_name());
                View view_line_red=holder.getView(R.id.view_line_red);
                final RecyclerView recyclerView_left2=holder.getView(R.id.recyclerView_left2);
                recyclerView_left2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                if(itemtype==-1){
                    recyclerView_left2.setVisibility(View.GONE);
                    view_line_red.setVisibility(View.GONE);
//                    holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.white));
//                    holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.fenlei_red));
                }else
                if (itemtype == position) {
//                    holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.white));
//                    holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.fenlei_red));
                    if(isOpen==true){
                        view_line_red.setVisibility(View.VISIBLE);
                        recyclerView_left2.setVisibility(View.VISIBLE);
                        holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.white));
                        holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.fenlei_red));
                    }else{
                        recyclerView_left2.setVisibility(View.GONE);
                        view_line_red.setVisibility(View.GONE);
                        holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.cart_text_cf));
                        holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.text3));
                    }
                } else {
//                    holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.cart_text_cf));
//                    holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.text3));

                }
                intSetLeftbottom(recyclerView_left2);
                holder.getView(R.id.ll_title_left).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isOpen==true){
                            isOpen=false;
                        }else{
                            isOpen=true;
                        }
                        itemtype = position;
                        RightData(resultBeanList.get(position).getCategory_id());
                        notifyDataSetChanged();
                        leftAdapter.notifyDataSetChanged();
                    }
                });

            }

        });


    }
    private  int cengj;
    private int TwoType=0;
    private void intSetLeftbottom(RecyclerView recyclerView_left2){
        leftAdapter= new BaseRVAdapter(getActivity(),resultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fl_recy_left2_title_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position2) {
                holder.getTextView(R.id.tv_left).setText(resultBeans.get(position2).getMobile_name());
                 if (TwoType == position2) {
                    holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.fenlei_red));
                    holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.white));
                } else {
                    holder.getView(R.id.ll_title_left).setBackgroundColor(getResources().getColor(R.color.bg_f2));
                    holder.getTextView(R.id.tv_left).setTextColor(getResources().getColor(R.color.text3));
                }
                holder.getView(R.id.ll_title_left).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                for (int two=0;two<resultBeans.size();two++){
                                    if(resultBeans.get(position2).getParent_id().equals(resultBeans.get(two).getParent_id())){
                                        cengj=position2;
                                    }
                                }
                                TwoType=position2;
                                RightTwoData();
                                notifyDataSetChanged();
                    }
                });
            }

        };
        recyclerView_left2.setAdapter(leftAdapter);
    }
    private void RightTwoData(){
        recyclerView_right.setAdapter(new BaseRVAdapter(getActivity(), resultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fl_recy_right_content;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_title).setText(resultBeans.get(TwoType).getMobile_name());
                ImageView iv_image_banner=holder.getView(R.id.iv_image_banner);
                Glide.with(getActivity()).load(resultBeans.get(TwoType).getApp_banner()).error(R.mipmap.myy322x).into(iv_image_banner);
                RecyclerView recycler_fl_item = holder.getView(R.id.recycler_fl_item);
                recycler_fl_item.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
                LinearLayout ll_twoAll=holder.getView(R.id.ll_twoAll);
                    if(position==cengj){
                        recycler_fl_item.setAdapter(new BaseRVAdapter(getActivity(), resultBeans.get(cengj).getSub_category()) {
                            @Override
                            public int getLayoutId(int viewType) {
                                return R.layout.recy_right_shopping_item;
                            }

                            @Override
                            public void onBind(BaseViewHolder holder, final int position2) {
                                ImageView iv_image = holder.getImageView(R.id.iv_image);
                                Glide.with(getActivity()).load(resultBeans.get(cengj).getSub_category().get(position2).getApp_image()).error(R.mipmap.myy322x).into(iv_image);
                                holder.getTextView(R.id.tv_name).setText(resultBeans.get(cengj).getSub_category().get(position2).getMobile_name());
                                holder.getView(R.id.ll_shopping_item).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivityForResult(new Intent(getActivity(), ShopFLConnectActivity.class)
                                                .putExtra("title", resultBeans.get(cengj).getSub_category().get(position2).getMobile_name() + "")
                                                .putExtra("id",resultBeans.get(cengj).getSub_category().get(position2).getId())
                                                .putExtra("type", ShopFLConnectActivity.NICKNAME_TYPE), ShopFLConnectActivity.NICKNAME_TYPE);
                                    }
                                });
                            }
                        });
                        ll_twoAll.setVisibility(View.VISIBLE);
                    }else{
                        ll_twoAll.setVisibility(View.GONE);
                    }



            }

        });
    }
    private void RightSetData() {
        recyclerView_right.setAdapter(new BaseRVAdapter(getActivity(), resultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fl_recy_right_content;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_title).setText(resultBeans.get(position).getMobile_name());
                RecyclerView recycler_fl_item = holder.getView(R.id.recycler_fl_item);
                ImageView iv_image_banner=holder.getView(R.id.iv_image_banner);
                Glide.with(getActivity()).load(resultBeans.get(position).getApp_banner()).error(R.mipmap.myy322x).into(iv_image_banner);
                iv_image_banner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(resultBeans.get(position).getApp_link()!=null&&!resultBeans.get(position).getApp_link().equals("")){
                            startActivityForResult(new Intent(getActivity(), ShopActivity.class)
                                    .putExtra("store_id",resultBeans.get(position).getApp_link())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                        }else{
                            CusToast.showToast("暂无店铺Id");
                        }
                    }
                });
                recycler_fl_item.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
                recycler_fl_item.setAdapter(new BaseRVAdapter(getActivity(), resultBeans.get(position).getSub_category()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.recy_right_shopping_item;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position2) {
                        ImageView iv_image = holder.getImageView(R.id.iv_image);
                        Glide.with(getActivity()).load(resultBeans.get(position).getSub_category().get(position2).getApp_image()).error(R.mipmap.myy322x).into(iv_image);

                        holder.getTextView(R.id.tv_name).setText(resultBeans.get(position).getSub_category().get(position2).getMobile_name());
                        holder.getView(R.id.ll_shopping_item).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityForResult(new Intent(getActivity(), ShopFLConnectActivity.class)
                                        .putExtra("title", resultBeans.get(position).getSub_category().get(position2).getMobile_name() + "")
//                                        .putExtra("category_id","454")
                                        .putExtra("id",resultBeans.get(position).getSub_category().get(position2).getId())
                                        .putExtra("type", ShopFLConnectActivity.NICKNAME_TYPE), ShopFLConnectActivity.NICKNAME_TYPE);
                            }
                        });
                    }


                });
            }


        });
    }

    @Event(value = {R.id.rl_saoyisao, R.id.tv_yuyQH,R.id.tv_sousuo_fl}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_yuyQH:
                break;
            case R.id.rl_saoyisao:
//                //点击二维码扫码
//                Intent mipcaIntent = new Intent();
//                mipcaIntent.setClass(getActivity(), MipcaActivityCapture.class);
//                startActivityForResult(mipcaIntent, 900);
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                        .putExtra("store_id", "0"));
                break;
            case R.id.tv_sousuo_fl:
                startActivity(new Intent(getActivity(), HomeSouSuoActivity.class)
                        .putExtra("store_id", "0"));
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        LeftData();
    }


    private void LeftData() {
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.SHOPFNENLIE_LEFTDATA, map,new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====分类左侧数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        FenLieLeftBean fenLieLeftBean = gson.fromJson(result, FenLieLeftBean.class);
                        resultBeanList = fenLieLeftBean.getResult();
                        if (resultBeanList.size() >= 1) {
                            RightData(resultBeanList.get(0).getCategory_id());
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

    private void RightData(String category_id) {
        Map<String, Object> map = new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        map.put("parent_id", category_id);
        baseActivity.showDialog();
        XUtil.Post(URLConstant.SHOPFENLEI_RIGHT, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====分类右侧数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        FenLieRightBean fenLieRightBean = gson.fromJson(result, FenLieRightBean.class);
                        resultBeans = fenLieRightBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
                initView();
                RightSetData();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 900) {
            String result = data.getStringExtra("result");
            CusToast.showToast(result);
            Log.i("=====二维码扫描---", result);

        }
    }
    /**
     * 语言切换
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LanguageBean.LanguageRefresh bean) {
        if (bean != null) {
            if(bean.getRefresh().equals("2")){
                Log.e("====分类翻译==", "========22222");

                languageQH();
            }
        }
    }
    /**
     * 语言切换
     */
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
                Log.e("====分类翻译=="+yuyan+"--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        LeftData();
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
    public void onDestroyView() {
        super.onDestroyView();
    }

}
