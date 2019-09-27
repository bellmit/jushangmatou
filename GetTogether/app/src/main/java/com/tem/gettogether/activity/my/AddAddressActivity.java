package com.tem.gettogether.activity.my;

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
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.AddressXQBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.SwitchView;

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

@ContentView(R.layout.activity_add_address)
public class AddAddressActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.switchView)
    private SwitchView switchView;
    @ViewInject(R.id.tv_lx_xz)
    private TextView tv_lx_xz;
    @ViewInject(R.id.tv_lx_xz2)
    private TextView tv_lx_xz2;
    @ViewInject(R.id.iv_lx_xz)
    private ImageView iv_lx_xz;
    @ViewInject(R.id.iv_lx_xz2)
    private ImageView iv_lx_xz2;
    @ViewInject(R.id.tv_address)
    private TextView tv_address;
    @ViewInject(R.id.ll_address)
    private LinearLayout ll_address;
    private List<AddressDataBean.ResultBean> addresssresultBeans = new ArrayList<>();
    private String addressType = "1";
    private String Sheng, city, qu, jiedao;
    private boolean isOne = true;
    @ViewInject(R.id.et_shouhuoren)
    private EditText et_shouhuoren;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_xxdz)
    private EditText et_xxdz;
    private String is_default = "1";
    private String type = "1";
    private String address_id;
    private AddressXQBean.ResultBean resultBean = new AddressXQBean.ResultBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        initData();
        initView();
        upGetAddressData("0");

    }

    @Override
    protected void initData() {
        address_id = getIntent().getStringExtra("address_id");
        if (address_id != null) {
            tv_title.setText(getText(R.string.modify_shipping_address));
            upgetAddressData();

        } else {
            tv_title.setText(getText(R.string.new_harvest_address));
            switchView.setOpened(true);
            tv_lx_xz.setVisibility(View.GONE);
            tv_lx_xz2.setVisibility(View.VISIBLE);
            iv_lx_xz.setVisibility(View.VISIBLE);
            iv_lx_xz2.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initView() {
        switchView.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(View view) {
                if(switchView.isOpened()==false){
                    switchView.setOpened(true);
                    is_default = "1";
                }

            }

            @Override
            public void toggleToOff(View view) {
                if(switchView.isOpened()==true){
                    switchView.setOpened(false);
                    is_default = "0";
                }

            }
        });
    }

    @Event(value = {R.id.rl_close, R.id.tv_save, R.id.ll_gongsi, R.id.tv_lx_xz, R.id.ll_address}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_lx_xz:
                tv_lx_xz.setVisibility(View.GONE);
                tv_lx_xz2.setVisibility(View.VISIBLE);
                iv_lx_xz.setVisibility(View.VISIBLE);
                iv_lx_xz2.setVisibility(View.GONE);
                type = "0";
                break;

            case R.id.ll_gongsi:
                tv_lx_xz2.setVisibility(View.GONE);
                tv_lx_xz.setVisibility(View.VISIBLE);
                iv_lx_xz2.setVisibility(View.VISIBLE);
                iv_lx_xz.setVisibility(View.GONE);
                type = "1";
                break;
            case R.id.tv_save:
                String name = et_shouhuoren.getText().toString();
                String phone = et_phone.getText().toString();
                String xxdz = et_xxdz.getText().toString();
                if (name.equals("")) {
                    CusToast.showToast(getText(R.string.enter_consignee_name));
                    return;
                } else if (phone.equals("")) {
                    CusToast.showToast(getText(R.string.enter_harvester_contact_number));
                    return;
                } /*else if (Sheng == null || city == null || qu == null) {
                    CusToast.showToast(getText(R.string.select_shipping_address));
                    return;
                } */else if (xxdz.equals("")) {
                    CusToast.showToast(getText(R.string.enter_detail_address));
                    return;
                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

                    if (address_id != null) {
                        map.put("address_id", address_id);
                    }
                    map.put("consignee", name);
                    map.put("province", Sheng);
                    map.put("city", city);
                    map.put("district", qu);
                    map.put("twon", jiedao);
                    map.put("address", xxdz);
                    map.put("mobile", phone);
                    map.put("is_default", is_default);
                    map.put("type", type);
                    upSoveAddressData(map);
                }
                break;
            case R.id.ll_address:
                showPop(ll_address);
                break;
        }
    }

    private void upSoveAddressData(Map<String, Object> map) {

        showDialog();
        XUtil.Post(URLConstant.ADD_XIUGAI_ADDRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取地区===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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

    private void upgetAddressData() {
        Map<String, Object> map = new HashMap<>();
        if (address_id != null) {
            map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

            map.put("address_id", address_id);
        }
        showDialog();
        XUtil.Post(URLConstant.ADDRESS_XIANGQ, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====获取地址详情===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        AddressXQBean addressXQBean = gson.fromJson(result, AddressXQBean.class);
                        resultBean=addressXQBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                if(resultBean==null)return;
                et_shouhuoren.setText(resultBean.getConsignee());
                Sheng=resultBean.getProvince();
                city=resultBean.getCity();
                qu=resultBean.getDistrict();
                jiedao=resultBean.getTwon();
                et_phone.setText(resultBean.getMobile());
                et_xxdz.setText(resultBean.getAddress());
                tv_address.setText(resultBean.getProvince_name() + " " + resultBean.getCity_name() + " " + resultBean.getDistrict_name() + " " + resultBean.getTwon_name() );
                if (resultBean.getIs_default().equals("0")) {
                    tv_lx_xz.setVisibility(View.GONE);
                    tv_lx_xz2.setVisibility(View.VISIBLE);
                    iv_lx_xz.setVisibility(View.VISIBLE);
                    iv_lx_xz2.setVisibility(View.GONE);
                }else {
                    tv_lx_xz2.setVisibility(View.GONE);
                    tv_lx_xz.setVisibility(View.VISIBLE);
                    iv_lx_xz2.setVisibility(View.VISIBLE);
                    iv_lx_xz.setVisibility(View.GONE);
                }
                if(resultBean.getType().equals("1")){
                    switchView.setOpened(true);
                }else{
                    switchView.setOpened(false);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }

        });
    }


    private PopupWindow mPop;

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

    private RecyclerView recyclerView;
    private View view;
    private TextView tv_sheng, tv_city, tv_qu, tv_jiedao;
    private LinearLayout ll_sheng, ll_city, ll_qu, ll_jiedao;
    private View view_line, view_line2, view_line3, view_line4;

    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            view = LayoutInflater.from(this).inflate(R.layout.pop_address_xzlayout, null);
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
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            tv_sheng = view.findViewById(R.id.tv_sheng);
            tv_city = view.findViewById(R.id.tv_city);
            tv_qu = view.findViewById(R.id.tv_qu);
            tv_jiedao = view.findViewById(R.id.tv_jiedao);
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            view_line = view.findViewById(R.id.view_line);
            view_line2 = view.findViewById(R.id.view_line2);
            view_line3 = view.findViewById(R.id.view_line3);
            view_line4 = view.findViewById(R.id.view_line4);
            ll_sheng = view.findViewById(R.id.ll_sheng);
            ll_city = view.findViewById(R.id.ll_city);
            ll_qu = view.findViewById(R.id.ll_qu);
            ll_jiedao = view.findViewById(R.id.ll_jiedao);

            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
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
                    tv_sheng.setText(getText(R.string.please_choose));
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
                    tv_city.setText(getText(R.string.please_choose));
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
                    tv_qu.setText(getText(R.string.please_choose));
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

        recyclerView.setAdapter(new BaseRVAdapter(AddAddressActivity.this, addresssresultBeans) {
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
                            tv_address.setText(tv_sheng.getText() + " " + tv_city.getText() + " " + tv_qu.getText() + " " + tv_jiedao.getText().toString());
                            mPop.dismiss();

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
