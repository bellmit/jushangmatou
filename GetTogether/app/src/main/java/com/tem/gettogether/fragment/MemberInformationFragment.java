package com.tem.gettogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MemberInformationBean;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.fragment_member_information)
public class MemberInformationFragment extends BaseFragment {
    @ViewInject(R.id.detail_ll)
    private LinearLayout detail_ll;
    @ViewInject(R.id.userName)
    private TextView userName;
    @ViewInject(R.id.user_lever)
    private TextView user_lever;
    @ViewInject(R.id.ustart_time)
    private TextView ustart_time;
    @ViewInject(R.id.uexpire_time)
    private TextView uexpire_time;
    @ViewInject(R.id.pay_fee)
    private TextView pay_fee;
    @ViewInject(R.id.pay_type)
    private TextView pay_type;
    private String userLever;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userLever = BaseApplication.getInstance().userBean.getLever();
        if (userLever.equals("7")) {
            user_lever.setText("游客");
            detail_ll.setVisibility(View.GONE);
        } else if (user_lever.equals("1")) {
            user_lever.setText("普通会员");
        } else if (user_lever.equals("2")) {
            user_lever.setText("高级会员");
        }

        userName.setText(BaseApplication.getInstance().userBean.getUserName());
        getMemberInformationData();
    }

    private void getMemberInformationData() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("user_id", BaseApplication.getInstance().userBean.getUser_id());
        XUtil.Post(URLConstant.MEMBER_INFORMATION, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取会员信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        MemberInformationBean memberInformationBean = gson.fromJson(result, MemberInformationBean.class);
                        ustart_time.setText(memberInformationBean.getResult().getUstart_time());
                        uexpire_time.setText(memberInformationBean.getResult().getUexpire_time());
                        pay_type.setText(memberInformationBean.getResult().getPay_name());
                        if(user_lever.equals("1")) {
                            pay_fee.setText(memberInformationBean.getResult().getRegularmdeposit());
                        }else if(user_lever.equals("2")){
                            pay_fee.setText(memberInformationBean.getResult().getSeniormfee());
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
