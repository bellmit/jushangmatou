package com.tem.gettogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MemberAmountBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.fragment_member_classification)
public class MemberClassificationFragment extends BaseFragment {
    @ViewInject(R.id.gaojihuiyuan_tv)
    private TextView gaojihuiyuan_tv;
    @ViewInject(R.id.putonghuiyuan_tv)
    private TextView putonghuiyuan_tv;
    private String ordinaryMember,seniorMember,lever;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getMemberFee();
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void getMemberFee() {

        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));

        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID ,""));

        XUtil.Post(URLConstant.JOIN_USER_LEVER, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("会员价格：", result);
                super.onSuccess(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (status.equals("1")) {
                        Gson gson=new Gson();
                        MemberAmountBean memberAmountBean =gson.fromJson(result,MemberAmountBean.class);
                        ordinaryMember = memberAmountBean.getResult().getRegularmdeposit();
                        seniorMember = memberAmountBean.getResult().getSeniormfee();
                        lever = memberAmountBean.getResult().getLevel();
                        if(lever.equals("7")){
                            gaojihuiyuan_tv.setText(seniorMember+"元/年");
                        }else if(lever.equals("1")){
                            putonghuiyuan_tv.setText(seniorMember+"元/押金");
                        }
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
