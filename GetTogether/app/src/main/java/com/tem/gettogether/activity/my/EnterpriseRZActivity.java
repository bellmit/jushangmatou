package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.graphics.Color;
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

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.QIYeXZBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_enterprise_rz)
public class EnterpriseRZActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_upStep)
    private TextView tv_upStep;
    @ViewInject(R.id.tv_nextStep)
    private TextView tv_nextStep;
    @ViewInject(R.id.et_name)
    private EditText et_name;
    @ViewInject(R.id.iv_qglx1)
    private ImageView iv_qglx1;
    @ViewInject(R.id.iv_qglx2)
    private ImageView iv_qglx2;
    private int RZType=0;
    @ViewInject(R.id.et_gsAddress)
    private EditText et_gsAddress;
    @ViewInject(R.id.gdPhone)
    private EditText gdPhone;
    @ViewInject(R.id.et_dzyb)
    private EditText et_dzyb;
    @ViewInject(R.id.et_chuanz)
    private EditText et_chuanz;
    @ViewInject(R.id.et_yzbm)
    private EditText et_yzbm;
    @ViewInject(R.id.et_ZCZJ)
    private EditText et_ZCZJ;
    @ViewInject(R.id.et_yyzcNum)
    private EditText et_yyzcNum;
    @ViewInject(R.id.et_fddbr)
    private EditText et_fddbr;

    @ViewInject(R.id.et_jyfw)
    private EditText et_jyfw;
    @ViewInject(R.id.et_zzjgdm)
    private EditText et_zzjgdm;
    @ViewInject(R.id.et_swdjNum)
    private EditText et_swdjNum;
    @ViewInject(R.id.ll_isymysj)
    private LinearLayout ll_isymysj;
    @ViewInject(R.id.tv_ymysj)
    private TextView tv_ymysj;
    @ViewInject(R.id.ll_nsrLX)
    private LinearLayout ll_nsrLX;
    @ViewInject(R.id.ll_yyzzyxq)
    private LinearLayout ll_yyzzyxq;
    @ViewInject(R.id.ll_gsxz)
    private LinearLayout ll_gsxz;
    @ViewInject(R.id.ll_nslxSM)
    private LinearLayout ll_nslxSM;
    @ViewInject(R.id.tv_nslxSM)
    private TextView tv_nslxSM;
    @ViewInject(R.id.tv_nsrlx)
    private TextView tv_nsrlx;
    @ViewInject(R.id.tv_gongsixz)
    private TextView tv_gongsixz;
    @ViewInject(R.id.ll_gsAddress)
    private LinearLayout ll_gsAddress;
    @ViewInject(R.id.tv_gongdizhi)
    private  TextView tv_gongdizhi;
    @ViewInject(R.id.tv_starTime)
    private TextView tv_starTime;
    @ViewInject(R.id.tv_endTime)
    private TextView tv_endTime;
    private List<QIYeXZBean.ResultBean.CompanyTypeBean>companyTypeBeans=new ArrayList<>();
    private List<QIYeXZBean.ResultBean.RateListBean>rateListBeans=new ArrayList<>();
    private List<QIYeXZBean.ResultBean.TaxpayerBean>taxpayerBeans=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        BaseApplication.addDestoryActivity(this,"RenZ3");
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("企业入驻");
        upRequest();
        upGetAddressData("0");
    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_upStep,R.id.ll_gsAddress,R.id.tv_endTime,R.id.tv_starTime,R.id.tv_nextStep,R.id.ll_yyzzyxq,R.id.ll_nslxSM,R.id.ll_gsxz,R.id.ll_nsrLX,R.id.iv_qglx1, R.id.iv_qglx2}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
            case R.id.tv_upStep:
                finish();
                break;
            case R.id.ll_gsAddress:
                showPopAddress(ll_gsAddress);

                break;
            case R.id.ll_gsxz:
                type=1;
                mPop=null;
                showPop(ll_gsxz);
                break;
            case R.id.ll_yyzzyxq:

                break;
            case R.id.ll_nsrLX:
                type=2;
                mPop=null;
                showPop(ll_yyzzyxq);
                break;
            case R.id.ll_nslxSM:
                type=3;
                mPop=null;
                showPop(ll_nsrLX);

                break;
            case R.id.tv_starTime:
                showDateDialog();
                break;
            case R.id.tv_endTime:
                showDateDialogend();
                break;
            case R.id.iv_qglx1:
                RZType=1;
                iv_qglx1.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);
                tv_ymysj.setText("统一社会信用代码");
                ll_isymysj.setVisibility(View.GONE);
                break;
            case R.id.iv_qglx2:
                RZType=0;
                iv_qglx2.setImageResource(R.drawable.xuanzhongf);
                iv_qglx1.setImageResource(R.drawable.weixuanz);
                tv_ymysj.setText("营业执照注册号");
                ll_isymysj.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_nextStep:
                String company_name=et_name.getText().toString();
                Map<String,Object> map=new HashMap<>();
                if(BaseApplication.getInstance().userBean==null)return;
                map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
                map.put("company_name",company_name);
                map.put("company_type",GSXZ);
                map.put("company_website","");
                map.put("company_province",Sheng);
                map.put("company_city",city);
                map.put("company_district",qu);
                map.put("company_address",et_gsAddress.getText().toString());
                map.put("company_telephone",gdPhone.getText().toString());
                map.put("company_email",et_dzyb.getText().toString());
                map.put("company_fax",et_chuanz.getText().toString());
                map.put("company_zipcode",et_yzbm.getText().toString());
                map.put("threeinone",RZType);
                map.put("reg_capital",et_ZCZJ.getText().toString());
                map.put("business_licence_number",et_yyzcNum.getText().toString());
                map.put("legal_person",et_fddbr.getText().toString());
                map.put("business_date_start",tv_starTime.getText().toString());//有效期
                map.put("business_date_end",tv_endTime.getText().toString());
                map.put("business_scope",et_jyfw.getText().toString());

                if(RZType==0){
                    map.put("orgnization_code",et_zzjgdm.getText().toString());
                    map.put("attached_tax_number",et_swdjNum.getText().toString());
                }

                map.put("taxpayer",NSRLX);
                map.put("tax_rate",NSLXM);

                upTJSHData(map);
                break;
        }
    }
    private void upTJSHData(Map<String, Object> map){
        showDialog();
        XUtil.Post(URLConstant.QIYERZXXSC, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====企业认证信息上传===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        startActivity(new Intent(EnterpriseRZActivity.this,EnterpriseShopRZActivity.class));

//                        finish();
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
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    private void showDateDialog() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String []b=str.split("-");
        //时间选择器
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(Integer.parseInt(b[0]),Integer.parseInt(b[1])-1,Integer.parseInt(b[2]));
        Calendar startDate = Calendar.getInstance();
        startDate.set(1960,10,29);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2110,10,29);
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调

                tv_starTime.setText(getTime(date));

            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("设置开始时间")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setSubCalSize(18)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("Title")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.rgb(232,95,43))//标题文字颜色
                .setSubmitColor(Color.rgb(232,95,43))//确定按钮文字颜色
                .setCancelColor(Color.rgb(232,95,43))//取消按钮文字颜色
                .setTitleBgColor(Color.rgb(255,255,255))//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setDividerColor(Color.rgb(255,255,255))//分割线颜色
                .setRangDate(startDate,endDate)//默认是1960-2110年
                .setDate(selectedDate)// 默认是系统时间*/
                .build();
        pvTime.show();
    }
    private void showDateDialogend() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        String []b=str.split("-");
        //时间选择器
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(Integer.parseInt(b[0]),Integer.parseInt(b[1])-1,Integer.parseInt(b[2]));
        Calendar startDate = Calendar.getInstance();
        startDate.set(1960,10,29);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2110,10,29);
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
                tv_endTime.setText(getTime(date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .setCancelText("设置结束时间")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setContentSize(20)//滚轮文字大小
                .setSubCalSize(18)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("Title")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.rgb(232,95,43))//标题文字颜色
                .setSubmitColor(Color.rgb(232,95,43))//确定按钮文字颜色
                .setCancelColor(Color.rgb(232,95,43))//取消按钮文字颜色
                .setTitleBgColor(Color.rgb(255,255,255))//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setDividerColor(Color.rgb(255,255,255))//分割线颜色
                .setRangDate(startDate,endDate)//默认是1960-2110年
                .setDate(selectedDate)// 默认是系统时间*/
                .build();
        pvTime.show();
    }

    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;
    private int type;
    private String GSXZ="";
    private String NSRLX="";
    private String NSLXM="";
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
                tv_xz_title.setText("请选择公司性质");
                initSetdialog();
            }else if(type==2){
                tv_xz_title.setText("请选择营业执照有效期");
                initSetdialog2();
            }else if(type==3){
                tv_xz_title.setText("请选择纳税类型税码");
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
        BaseRVAdapter baseRVAdapter= new BaseRVAdapter(this,companyTypeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(companyTypeBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GSXZ=companyTypeBeans.get(position).getId();
                        tv_gongsixz.setText(companyTypeBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }
    private void initSetdialog2(){

        if(NSRLX.equals("")){
            if(taxpayerBeans.size()>0){
                NSRLX=taxpayerBeans.get(0).getId();
            }
        }
        BaseRVAdapter  baseRVAdapter2=new BaseRVAdapter(this,taxpayerBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(taxpayerBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NSRLX=taxpayerBeans.get(position).getId();
                        tv_nsrlx.setText(taxpayerBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }
    private void initSetdialog3(){
        BaseRVAdapter baseRVAdapter3=  new BaseRVAdapter(this,rateListBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(rateListBeans.get(position).getName());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NSLXM=rateListBeans.get(position).getId();
                        tv_nslxSM.setText(rateListBeans.get(position).getName());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter3.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter3);
    }
    private void upRequest(){
        showDialog();
        XUtil.Post(URLConstant.QIYEXUANZ_LIBIAO,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("===企业选择信息列表--",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        QIYeXZBean qiYeXZBean=gson.fromJson(result,QIYeXZBean.class);
                        companyTypeBeans=qiYeXZBean.getResult().getCompany_type();
                        rateListBeans=qiYeXZBean.getResult().getRate_list();
                        taxpayerBeans=qiYeXZBean.getResult().getTaxpayer();
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
                            Sheng = addresssresultBeans.get(position).getId();
                            tv_sheng.setTextColor(getResources().getColor(R.color.text3));
                            view_line.setVisibility(View.INVISIBLE);
                            tv_sheng.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());
                        } else if (addressType.equals("2")) {
                            ll_qu.setVisibility(View.VISIBLE);
                            addressType = "3";
                            city = addresssresultBeans.get(position).getId();
                            tv_city.setTextColor(getResources().getColor(R.color.text3));
                            view_line2.setVisibility(View.INVISIBLE);
                            tv_city.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("3")) {
                            ll_jiedao.setVisibility(View.VISIBLE);
                            addressType = "4";
                            qu = addresssresultBeans.get(position).getId();
                            tv_qu.setTextColor(getResources().getColor(R.color.text3));
                            view_line3.setVisibility(View.INVISIBLE);
                            tv_qu.setText(addresssresultBeans.get(position).getName());
                            upGetAddressData(addresssresultBeans.get(position).getId());

                        } else if (addressType.equals("4")) {
                            tv_jiedao.setTextColor(getResources().getColor(R.color.text3));
                            view_line4.setVisibility(View.INVISIBLE);
                            tv_jiedao.setText(addresssresultBeans.get(position).getName());
                            jiedao = addresssresultBeans.get(position).getId();
                            tv_gongdizhi.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            mPopAddress.dismiss();

                        }
                        CusToast.showToast(addresssresultBeans.get(position).getName());
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
