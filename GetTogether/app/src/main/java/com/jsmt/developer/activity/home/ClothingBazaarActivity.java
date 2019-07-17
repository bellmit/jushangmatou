package com.jsmt.developer.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.HomeSCFLDataBean;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.TransformationUtils;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

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

@ContentView(R.layout.activity_clothing_bazaar)
public class ClothingBazaarActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @ViewInject(R.id.iv_pic)
    private ImageView iv_pic;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_content)
    private  TextView tv_content;
    private String parent_id;
    private List<HomeSCFLDataBean.ResultBean> resultBeans=new ArrayList<>();
    private HomeSCFLDataBean.ResultBean resultGFLBean=new HomeSCFLDataBean.ResultBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        upGetTitle();
    }

    @Override
    protected void initData() {
        String title=getIntent().getStringExtra("title");
        tv_title.setText(title);
        parent_id=getIntent().getStringExtra("parent_id");
    }

    @Override
    protected void initView() {
        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultGFLBean.getPavilion().getApp_link()!=null&&!resultGFLBean.getPavilion().getApp_link().equals("")){
                    if(BaseApplication.getInstance().userBean==null){
                        startActivity(new Intent(ClothingBazaarActivity.this,LoginActivity.class));
                    }else {
                        startActivityForResult(new Intent(ClothingBazaarActivity.this, ShopActivity.class)
                                .putExtra("store_id",resultGFLBean.getPavilion().getApp_link())
                                .putExtra("type", ShopActivity.SHOPNHOME_TYPE), ShopActivity.SHOPNHOME_TYPE);
                    }

                }else{
                    CusToast.showToast("暂无店铺Id");
                }

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new BaseRVAdapter(this,resultGFLBean.getPavilion_cate()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.clothing_recy_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_fl_title).setText(resultGFLBean.getPavilion_cate().get(position).getFloor());
                RecyclerView recyclerView_sc=holder.getView(R.id.recyclerView_sc);
                recyclerView_sc.setLayoutManager(new LinearLayoutManager(ClothingBazaarActivity.this,LinearLayoutManager.HORIZONTAL,false));
               if( resultGFLBean.getPavilion_cate().size()<=0)return;
                if( resultGFLBean.getPavilion_cate().get(position).getGoods_category().size()<=0)return;
                recyclerView_sc.setAdapter(new BaseRVAdapter(ClothingBazaarActivity.this,resultGFLBean.getPavilion_cate().get(position).getGoods_category()) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.clothing_item_shopping;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position2) {
                        ImageView iv_image=holder.getImageView(R.id.iv_image);
                        holder.getTextView(R.id.tv_name).setText(resultGFLBean.getPavilion_cate().get(position).getGoods_category().get(position2).getName());
                        Glide.with(ClothingBazaarActivity.this).load(resultGFLBean.getPavilion_cate().get(position).getGoods_category().get(position2).getApp_image()).asBitmap().error(R.mipmap.myy322x).into(new TransformationUtils(iv_image));
                                tv_name.setText(resultGFLBean.getPavilion().getName());
                        Glide.with(ClothingBazaarActivity.this).load(resultGFLBean.getPavilion().getApp_banner()).error(R.mipmap.myy322x).into(iv_pic);
//                        Glide.with(ClothingBazaarActivity.this).load(resultGFLBean.getPavilion().getApp_banner()).asBitmap().error(R.mipmap.myy322x).into(new TransformationUtils(iv_pic));
                            tv_content.setText(resultGFLBean.getPavilion().getApp_des());
                        holder.getView(R.id.ll_fenlei_item).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityForResult(new Intent(ClothingBazaarActivity.this, ShopConnectActivity.class)
//                                        .putExtra("category_id","454")
                                        .putExtra("category_id",resultGFLBean.getPavilion_cate().get(position).getGoods_category().get(position2).getCategory_id())
                                        .putExtra("title",resultGFLBean.getPavilion().getName())
                                        .putExtra("type", ShopConnectActivity.NICKNAME_TYPE), ShopConnectActivity.NICKNAME_TYPE);
                            }
                        });

                    }

                });
            }

        });
    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;

        }
    }
    private void upGetTitle(){
        Map<String,Object> map=new HashMap<>();
        map.put("pavilion_cate_id",parent_id);
        String yuyan= SharedPreferencesUtils.getString(ClothingBazaarActivity.this, BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        showDialog();
        XUtil.Post(URLConstant.HONESHICHANGDATA,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取分类数据2===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeSCFLDataBean homeSCFLDataBean=gson.fromJson(result,HomeSCFLDataBean.class);
//                        resultBeans=homeSCFLDataBean.getResult();
                        resultGFLBean=homeSCFLDataBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }
}
