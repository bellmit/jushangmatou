package com.tem.gettogether.activity.my;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CompanyPersionInformationBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_corporate_information)

public class CorporateInformationActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private int informationType = 0;// 0:企业信息  1:个人信息
    @ViewInject(R.id.card2_ll)
    private LinearLayout card2_ll;
    @ViewInject(R.id.head_iv)
    private ImageView head_iv;
    @ViewInject(R.id.change_head_tv)
    private TextView change_head_tv;
    @ViewInject(R.id.name_tv)
    private TextView name_tv;
    @ViewInject(R.id.man_rb)
    private RadioButton man_rb;
    @ViewInject(R.id.lady_rb)
    private RadioButton lady_rb;
    @ViewInject(R.id.phone_num_tv)
    private TextView phone_num_tv;
    @ViewInject(R.id.country_tv)
    private TextView country_tv;
    @ViewInject(R.id.card_status_tv)
    private TextView card_status_tv;
    @ViewInject(R.id.company_address)
    private TextView company_address;
    @ViewInject(R.id.category_tv)
    private TextView category_tv;
    @ViewInject(R.id.company_profile_tv)
    private TextView company_profile_tv;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(informationType == 0 ? getResources().getText(R.string.qiyexinxi) : getResources().getText(R.string.gerenxinxi));
        informationType = getIntent().getIntExtra(Contacts.PERSION_ENTERPRISE_INFORMATION, 0);
        card2_ll.setVisibility(informationType == 0 ? View.VISIBLE : View.GONE);
        getData();
    }

    @Override
    protected void initView() {

    }

    private void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID ,""));
        XUtil.Post(URLConstant.ENTERPISE_INFO, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====企业信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CompanyPersionInformationBean mCompanyPersionInformationBean = gson.fromJson(result, CompanyPersionInformationBean.class);
                        Glide.with(getContext()).load(mCompanyPersionInformationBean.getResult().getHead_pic() + "").error(R.drawable.img12x).centerCrop().into(head_iv);
                        name_tv.setText(mCompanyPersionInformationBean.getResult().getNickname());
                        phone_num_tv.setText(mCompanyPersionInformationBean.getResult().getMobile());
                        company_address.setText(mCompanyPersionInformationBean.getResult().getCompany_province()
                                + mCompanyPersionInformationBean.getResult().getCompany_district()
                                + mCompanyPersionInformationBean.getResult().getCompany_city());
                        category_tv.setText(mCompanyPersionInformationBean.getResult().getSc_name());
                    } else {
                        String msg = jsonObject.optString("msg");
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
                ex.printStackTrace();

            }
        });
    }

}
