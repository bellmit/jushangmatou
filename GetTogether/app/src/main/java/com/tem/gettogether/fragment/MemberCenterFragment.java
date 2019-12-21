package com.tem.gettogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.BuyMemberActivity;
import com.tem.gettogether.activity.my.refund.RefundActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MemberInformationBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
import com.tem.gettogether.view.DialogGetHeadRefund;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/21 11:14 
 */
@ContentView(R.layout.item_viewpage)
public class MemberCenterFragment extends BaseFragment {
    @ViewInject(R.id.cl_bg)
    private ConstraintLayout mConstraintLayout;
    @ViewInject(R.id.head_iv)
    private CircularImage head_iv;
    @ViewInject(R.id.nick_name)
    private TextView nick_name;
    @ViewInject(R.id.count_tv)
    private TextView count_tv;
    @ViewInject(R.id.level_pic)
    private ImageView level_pic;
    @ViewInject(R.id.upgrade_membership_tv)
    private TextView upgrade_membership_tv;
    @ViewInject(R.id.deposit_payment_tv)
    private TextView deposit_payment_tv;
    @ViewInject(R.id.nonactivated_pic)
    private ImageView nonactivated_pic;
    private int typePage = 0;
    private String userLever = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        typePage = getArguments().getInt("page");
        userLever = SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.LEVER, "7");

        switch (typePage) {
            case 0:
                mConstraintLayout.setBackgroundResource(R.drawable.viewpage_gaoji);
                level_pic.setBackgroundResource(R.drawable.gaoji_icon);
                if (userLever.equals("2")) {// 高级会员
                    deposit_payment_tv.setVisibility(View.VISIBLE);
                    getMemberInformationData();
                    nonactivated_pic.setVisibility(View.INVISIBLE);
                    upgrade_membership_tv.setText(getString(R.string.renewal_immediately));
                }
                break;
            case 1:
                mConstraintLayout.setBackgroundResource(R.drawable.viewpage_putong);
                level_pic.setBackgroundResource(R.drawable.puton_icon);
                if (userLever.equals("1")) {// 普通会员
                    deposit_payment_tv.setVisibility(View.VISIBLE);
                    getMemberInformationData();
                    nonactivated_pic.setVisibility(View.INVISIBLE);
                    upgrade_membership_tv.setText(R.string.application_refund);
                }
                break;
        }

        Glide.with(getContext()).load(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.head_pic, "")).error(R.mipmap.myy322x).centerCrop().into(head_iv);
        nick_name.setText(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.NAME, ""));
        count_tv.setText(SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.MOBILEPHONE, ""));

    }

    @Event(value = {R.id.upgrade_membership_tv})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.upgrade_membership_tv:
                if (typePage == 0) {// 高级会员
                    if (userLever.equals("1")) {
                        new DialogGetHeadRefund(getActivity(), getString(R.string.gaoji_refund)) {
                            @Override
                            public void refund() {
                                startActivity(new Intent(getActivity(), RefundActivity.class).putExtra("REFUND_TYPE", 0));
                            }
                        }.show();
                    } else {
                        startActivity(new Intent(getActivity(), BuyMemberActivity.class).putExtra("REFUND_TYPE", 0));
                    }
                } else if (typePage == 1) {// 普通会员
                    if (userLever.equals("1")) {
                        new DialogGetHeadRefund(getActivity(), getString(R.string.putong_refund)) {
                            @Override
                            public void refund() {
                                startActivity(new Intent(getActivity(), RefundActivity.class).putExtra("REFUND_TYPE", 1));
                            }
                        }.show();
                    } else {
                        startActivity(new Intent(getActivity(), BuyMemberActivity.class).putExtra("REFUND_TYPE", 1));
                    }
                } else {
                    startActivity(new Intent(getActivity(), BuyMemberActivity.class));
                }
                break;
        }
    }

    private void getMemberInformationData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
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
                        if (userLever.equals("1")) {
                            deposit_payment_tv.setText(getText(R.string.deposit_payment_tv) + memberInformationBean.getResult().getUstart_time());
                        } else if (userLever.equals("2")) {
                            deposit_payment_tv.setText(getText(R.string.valid_until) + memberInformationBean.getResult().getUexpire_time());
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
