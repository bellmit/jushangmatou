package com.tem.gettogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ClothingBazaarActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeDataBean;
import com.tem.gettogether.bean.HomeGFLBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

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

@ContentView(R.layout.fragment_tab_main)
public class TabMainFragment extends BaseFragment {
    private int mTab;
    private int state = -1;
    private BaseActivity baseActivity;
    @ViewInject(R.id.iv_FL_pic)
    private ImageView iv_FL_pic;
    @ViewInject(R.id.tv_Fl_zl)
    private TextView tv_Fl_zl;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_FL_dec)
    private TextView tv_FL_dec;
    @ViewInject(R.id.recycler_gfl)
    private RecyclerView recycler_gfl;
    private HomeGFLBean.ResultBean resultGFLBean=new HomeGFLBean.ResultBean();
    private List<HomeDataBean.ResultBean.PavilionBean> pavilionBeans=new ArrayList<>();

    public static TabMainFragment getInstance(int tab) {
        TabMainFragment fragment = new TabMainFragment();
        fragment.setArguments(setArguments(tab));
        return fragment;
    }
    public static Bundle setArguments(int tab) {
        Bundle bundle = new Bundle();
        bundle.putInt("tab", tab);
        return bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loadData();
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        baseActivity= (BaseActivity) getActivity();
        upHomeData();
        initView();
        super.onActivityCreated(savedInstanceState);
    }
    private void initView(){
        recycler_gfl.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        upHomeTitle(pavilionBeans.get(mTab).getPavilion_id());

    }

    private void upHomeData(){
        baseActivity.showDialog();
        XUtil.Get(URLConstant.HONEDATA,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====管分类===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeDataBean homeDataBean=gson.fromJson(result,HomeDataBean.class);
                        pavilionBeans=homeDataBean.getResult().getPavilion();

//                        if(pavilionBeans.size()>=1){
//                            upHomeTitle(pavilionBeans.get(0).getPavilion_id());
//                        }

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
                Log.i("====首页市场分类数据22===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HomeGFLBean homeSCFLDataBean=gson.fromJson(result,HomeGFLBean.class);
                        resultGFLBean=homeSCFLDataBean.getResult();
                        tv_Fl_zl.setText(resultGFLBean.getPavilion_cate().size()+"款");
                        Glide.with(getActivity()).load(resultGFLBean.getApp_img()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(iv_FL_pic);
                        tv_title.setText(resultGFLBean.getApp_title());
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
    protected void loadData() {
        mTab = getArguments().getInt("tab");
        if (mTab == 0) {
            state = -1;
        } else if (mTab == 1) {
            state = 100;
        } else if (mTab == 2) {
            state = 101;
        } else if (mTab == 3) {
            state = 2;
        }
        else if (mTab == 4) {
            state = 2;
        }
    }
}
