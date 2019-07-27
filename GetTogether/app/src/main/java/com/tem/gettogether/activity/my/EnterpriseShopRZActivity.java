package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.PersonagerMessageBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

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

@ContentView(R.layout.activity_enterprise_shop_rz)
public class EnterpriseShopRZActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_nextStep)
    private TextView tv_nextStep;
    @ViewInject(R.id.tv_upStep)
    private TextView tv_upStep;
    @ViewInject(R.id.et_dpzzh)
    private EditText et_dpzzh;
    @ViewInject(R.id.et_dl_pass)
    private EditText et_dl_pass;
    @ViewInject(R.id.et_dpfzrName)
    private EditText et_dpfzrName;
    @ViewInject(R.id.et_fzrPhone)
    private EditText et_fzrPhone;
    @ViewInject(R.id.et_fzrQQ)
    private EditText et_fzrQQ;
    @ViewInject(R.id.et_fzrWXH)
    private EditText et_fzrWXH;
    @ViewInject(R.id.et_fzrWhat)
    private EditText et_fzrWhat;
    @ViewInject(R.id.et_fzrFace)
    private EditText et_fzrFace;
    @ViewInject(R.id.et_dzyx)
    private EditText et_dzyx;
    @ViewInject(R.id.et_yhkhh)
    private EditText et_yhkhh;
    @ViewInject(R.id.et_yhzh)
    private EditText et_yhzh;
    @ViewInject(R.id.et_khyhzh)
    private EditText et_khyhzh;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.tv_khhSZD)
    private TextView tv_khhSZD;
    @ViewInject(R.id.ll_dpzydl)
    private LinearLayout ll_dpzydl;
    @ViewInject(R.id.ll_gfl)
    private LinearLayout ll_gfl;
    @ViewInject(R.id.ll_dpxz)
    private LinearLayout ll_dpxz;
    @ViewInject(R.id.ll_khhszd)
    private LinearLayout ll_khhszd;
    @ViewInject(R.id.tv_dpzy)
    private TextView tv_dpzy;
    @ViewInject(R.id.tv_gfl)
    private TextView tv_gfl;
    @ViewInject(R.id.tv_dpxz)
    private TextView tv_dpxz;
    private int imageType=0;
    @ViewInject(R.id.et_xiangXxadd)
    private EditText et_xiangXxadd;
    @ViewInject(R.id.ll_dizhixz)
    private LinearLayout ll_dizhixz;
    @ViewInject(R.id.tv_dpAddress)
    private TextView tv_dpAddress;
    @ViewInject(R.id.ll_yhdizhi)
    private LinearLayout ll_yhdizhi;
    private int isAddress;
    private List<PersonagerMessageBean.ResultBean.GoodsCategoryBean> goodsCategoryBeans=new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.PavilionBean> pavilionBeans=new ArrayList<>();
    private List<PersonagerMessageBean.ResultBean.StoreClassBean> storeClassBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        BaseApplication.addDestoryActivity(this,"RenZ2");
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("企业入驻");
        upGetAddressData("0");
        upRequest();

    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_upStep,R.id.tv_nextStep,R.id.ll_dizhixz,R.id.ll_dpzydl,R.id.tv_upStep,R.id.ll_gfl,R.id.ll_dpxz,R.id.ll_yhdizhi}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
            case R.id.tv_upStep:
                finish();
                break;
            case R.id.tv_nextStep:
                String dpname=et_name.getText().toString();
                String dpzh =et_dpzzh.getText().toString();
                String dpdlPass=et_dl_pass.getText().toString();
                String dpfzrMZ=et_dpfzrName.getText().toString();
                String dpfzrPhone=et_fzrPhone.getText().toString();
                String dpfzrQQ=et_fzrQQ.getText().toString();
                String fzrWX=et_fzrWXH.getText().toString();
                String fzrApp=et_fzrWhat.getText().toString();
                String fzrFace=et_fzrFace.getText().toString();
                String fzrEmail=et_dzyx.getText().toString();
                String dpAddress=et_xiangXxadd.getText().toString();
                String yhkhm=et_yhkhh.getText().toString();
                String yhzh=et_yhzh.getText().toString();
                String khyhName=et_khyhzh.getText().toString();
                Map<String,Object> map=new HashMap<>();
                if(BaseApplication.getInstance().userBean==null)return;
                map.put("token",BaseApplication.getInstance().userBean.getToken());
                map.put("store_name",dpname);
                map.put("sc_id",DPDL);
                map.put("pavilion_id",GFL);
                map.put("store_type",DPXZ);
                map.put("seller_name",dpzh);
                map.put("password",dpdlPass);
                map.put("store_person_name",dpfzrMZ);
                map.put("store_person_mobile",dpfzrPhone);
                map.put("store_person_qq",dpfzrQQ);
                map.put("store_person_wx",fzrWX);
                map.put("store_person_wt",fzrApp);
                map.put("store_person_fc",fzrFace);
                map.put("store_person_email",fzrEmail);
                map.put("store_address",dpAddress);
                map.put("bank_account_name",yhkhm);
                map.put("bank_account_number",yhzh);
                map.put("bank_branch_name",khyhName);
                map.put("bank_province",KhhSheng);
                map.put("bank_city",Khhcity);
                map.put("bank_district",Khhqu);
                map.put("store_province",Sheng);
                map.put("store_city",city);
                map.put("store_district",qu);
                upTJSHData(map);
                break;
            case R.id.ll_dpzydl://店铺主营大类
                type=1;
                mPop=null;
                showPop(ll_dpzydl);
                break;
            case R.id.ll_gfl://管分类
                type=2;
                mPop=null;
                showPop(ll_gfl);
                return;
            case R.id.ll_dpxz://店铺性质
                type=3;
                mPop=null;
                showPop(ll_dpxz);
                break;
            case R.id.ll_yhdizhi://开户银行支行所在地
                isAddress=2;
                mPopAddress=null;
                showPopAddress(ll_yhdizhi);
                break;
            case R.id.ll_dizhixz://店铺
                isAddress=1;
                mPopAddress=null;
                showPopAddress(ll_dizhixz);
                break;
        }
    }
    private void upTJSHData(Map<String, Object> map){
        showDialog();
        XUtil.Post(URLConstant.QIYESHANGPU_RENZHENG, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商铺企业入驻提交审核===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        startActivity(new Intent(EnterpriseShopRZActivity.this,UploadQYActivity.class));

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
                closeDialog();
            }
        });
    }
    private void upRequest(){
        showDialog();
        XUtil.Post(URLConstant.GRXINXLIEB,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===个人信息选择列表--",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        PersonagerMessageBean personagerMessageBean=gson.fromJson(result,PersonagerMessageBean.class);
                        goodsCategoryBeans=personagerMessageBean.getResult().getGoods_category();
                        pavilionBeans=personagerMessageBean.getResult().getPavilion();
                        storeClassBeans=personagerMessageBean.getResult().getStore_class();
                    }else {
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
            }
        });
    }
    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;
    private int type;
    private String DPDL="";
    private String GFL="";
    private String DPXZ="";
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
    private void initPop() {
        if (mPop == null) {
            view = LayoutInflater.from(this).inflate(R.layout.main_qgxx_layout, null);
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
                    WindowManager.LayoutParams lp =getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            tv_xz_title=view.findViewById(R.id.tv_xz_title);
            if(type==1){
                tv_xz_title.setText("请选择店铺主营大类");
                initSetdialog();
            }else if(type==2){
                tv_xz_title.setText("请选择馆分类");
                initSetdialog2();
            }else if(type==3){
                tv_xz_title.setText("请选择店铺性质");
                initSetdialog3();
            }
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
    }
    private void initSetdialog(){
        BaseRVAdapter baseRVAdapter= new BaseRVAdapter(this,goodsCategoryBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsCategoryBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DPDL=goodsCategoryBeans.get(position).getId();
                        tv_dpzy.setText(goodsCategoryBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }
    private void initSetdialog2(){
        BaseRVAdapter  baseRVAdapter2=new BaseRVAdapter(this,pavilionBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(pavilionBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GFL=pavilionBeans.get(position).getId();
                        tv_gfl.setText(pavilionBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }
    private void initSetdialog3(){
        BaseRVAdapter baseRVAdapter3=  new BaseRVAdapter(this,storeClassBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(storeClassBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DPXZ=storeClassBeans.get(position).getId();
                        tv_dpxz.setText(storeClassBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter3.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter3);
    }

    private PopupWindow mPopAddress;

    //显示弹窗
    private void showPopAddress(View v) {
        initPopAddress();
        if (mPopAddress.isShowing())
            return;
        //设置弹窗底部位置
        mPopAddress.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private RecyclerView recyclerView;
    private View viewAddress;
    private TextView tv_sheng, tv_city, tv_qu, tv_jiedao;
    private LinearLayout ll_sheng, ll_city, ll_qu, ll_jiedao;
    private View view_line, view_line2, view_line3, view_line4;
    private List<AddressDataBean.ResultBean> addresssresultBeans = new ArrayList<>();
    private String addressType = "1";
    private String Sheng, city, qu, jiedao;
    private String KhhSheng,Khhcity,Khhqu;
    private boolean isOne = true;
    //初始化弹窗
    private void initPopAddress() {
        if (mPopAddress == null) {
            viewAddress = LayoutInflater.from(this).inflate(R.layout.pop_address_xzlayout, null);
            mPopAddress = new PopupWindow(viewAddress, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPopAddress.setFocusable(true);
            mPopAddress.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPopAddress.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPopAddress.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPopAddress.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = viewAddress.findViewById(R.id.iv_dialog_close);
            tv_sheng = viewAddress.findViewById(R.id.tv_sheng);
            tv_city = viewAddress.findViewById(R.id.tv_city);
            tv_qu = viewAddress.findViewById(R.id.tv_qu);
            tv_jiedao = viewAddress.findViewById(R.id.tv_jiedao);
            recyclerView = viewAddress.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            view_line = viewAddress.findViewById(R.id.view_line);
            view_line2 = viewAddress.findViewById(R.id.view_line2);
            view_line3 = viewAddress.findViewById(R.id.view_line3);
            view_line4 = viewAddress.findViewById(R.id.view_line4);
            ll_sheng = viewAddress.findViewById(R.id.ll_sheng);
            ll_city = viewAddress.findViewById(R.id.ll_city);
            ll_qu = viewAddress.findViewById(R.id.ll_qu);
            ll_jiedao = viewAddress.findViewById(R.id.ll_jiedao);

            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopAddress.dismiss();
                }
            });
            if (isOne = true) {
                Log.i("======选择内容888--", "==");
                addressSetData();
            }
            ll_sheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "1";
                    isOne = false;
                    upGetAddressData("0");
                    tv_sheng.setText("请选择");
                    view_line.setVisibility(View.VISIBLE);
                    tv_sheng.setTextColor(getResources().getColor(R.color.my_red));
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.GONE);
                    ll_qu.setVisibility(View.GONE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });
            ll_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "2";

                    if (Sheng != null) {
                        upGetAddressData(Sheng);
                    }
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.VISIBLE);
                    tv_city.setText("请选择");
                    view_line2.setVisibility(View.VISIBLE);

                    tv_city.setTextColor(getResources().getColor(R.color.my_red));

                    ll_qu.setVisibility(View.GONE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });
            ll_qu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressType = "3";
                    if (city != null) {
                        upGetAddressData(city);
                    }
                    tv_qu.setText("请选择");
                    view_line3.setVisibility(View.VISIBLE);
                    tv_qu.setTextColor(getResources().getColor(R.color.my_red));
                    ll_sheng.setVisibility(View.VISIBLE);
                    ll_city.setVisibility(View.VISIBLE);
                    ll_qu.setVisibility(View.VISIBLE);
                    ll_jiedao.setVisibility(View.GONE);
                }
            });

        }
    }

    private void addressSetData() {

        recyclerView.setAdapter(new BaseRVAdapter(this, addresssresultBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.address_item_layout;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(addresssresultBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isOne = false;
                        upGetAddressData(addresssresultBeans.get(position).getId());
                        if (addressType.equals("1")) {

                            ll_city.setVisibility(View.VISIBLE);
                            addressType = "2";
                            if(isAddress==1){
                                Sheng = addresssresultBeans.get(position).getId();
                            }else {
                                KhhSheng= addresssresultBeans.get(position).getId();
                            }
                            tv_sheng.setTextColor(getResources().getColor(R.color.text3));
                            view_line.setVisibility(View.INVISIBLE);
                            tv_sheng.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("2")) {
                            ll_qu.setVisibility(View.VISIBLE);
                            addressType = "3";
                            if(isAddress==1){
                                city = addresssresultBeans.get(position).getId();
                            }else {
                                Khhcity= addresssresultBeans.get(position).getId();
                            }
                            tv_city.setTextColor(getResources().getColor(R.color.text3));
                            view_line2.setVisibility(View.INVISIBLE);
                            tv_city.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("3")) {

                            ll_jiedao.setVisibility(View.VISIBLE);
                            addressType = "4";
                            qu = addresssresultBeans.get(position).getId();
                            if(isAddress==1){
                                qu = addresssresultBeans.get(position).getId();
                            }else {
                                Khhqu= addresssresultBeans.get(position).getId();
                            }
                            tv_qu.setTextColor(getResources().getColor(R.color.text3));
                            view_line3.setVisibility(View.INVISIBLE);
                            tv_qu.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("4")) {
                            tv_jiedao.setTextColor(getResources().getColor(R.color.text3));
                            view_line4.setVisibility(View.INVISIBLE);
                            tv_jiedao.setText(addresssresultBeans.get(position).getName());
                            jiedao = addresssresultBeans.get(position).getId();
                            if(isAddress==1){
                                tv_dpAddress.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            }else {
                                tv_khhSZD.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            }
                            mPopAddress.dismiss();

                        }
//                        CusToast.showToast(addresssresultBeans.get(position).getName());
                        notifyDataSetChanged();
                    }
                });

            }


        });
    }
    public void clearList(List<AddressDataBean.ResultBean> list) {
        if (!ListUtils.isEmpty(list)) {
            list.clear();
        }
    }

    private void upGetAddressData(String parent_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", parent_id);

        showDialog();
        XUtil.Post(URLConstant.GET_ADDRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取地区===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AddressDataBean addressDataBean = gson.fromJson(result, AddressDataBean.class);
                        clearList(addresssresultBeans);
                        addresssresultBeans = addressDataBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                if (isOne == false) {
                    Log.i("======选择内容000--", "==");
                    addressSetData();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }
}
