package com.jsmt.developer.activity.my;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.MyMessageBean;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;
import com.jsmt.developer.view.CircularImage;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_getOut)
    private  TextView tv_getOut;
    @ViewInject(R.id.iv_head)
    private CircularImage iv_head;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_hydj)
    private TextView tv_hydj;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.settings);

    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.rl_zhaq,R.id.rl_address_gl,R.id.ll_grxx,R.id.rl_gl_account,R.id.tv_getOut,R.id.rl_smrz}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_zhaq://账户安全
               startActivity(new Intent(this,ZHAQActivity.class)
               .putExtra("phone",phone));
                break;
            case R.id.rl_address_gl://地址管理
                startActivity(new Intent(this,AddressGLActivity.class));
                break;
            case R.id.ll_grxx://个人信息
                startActivity(new Intent(this,GRMeassageActivity.class));
                break;
            case R.id.rl_gl_account://关联账号
                startActivity(new Intent(this,GLAccountActivity.class));
                break;
            case R.id.rl_smrz://实名认证
                startActivity(new Intent(this,AutonymRZActivity.class));

                break;
            case R.id.tv_getOut:
                showPop(tv_getOut);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        upGetMessageData();
    }

    private void upGetMessageData() {
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        showDialog();
        XUtil.Post(URLConstant.GET_MESSAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取个人信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        MyMessageBean myMessageBean=gson.fromJson(result,MyMessageBean.class);
                        Glide.with(SettingActivity.this).load(myMessageBean.getResult().getHead_pic()+"").asBitmap().error( R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_head));
                        tv_name.setText(myMessageBean.getResult().getNickname());
                        tv_hydj.setText(myMessageBean.getResult().getLevel_name());
                        phone=myMessageBean.getResult().getMobile();
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

    private PopupWindow mPop;
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.pop_layout, null);
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
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }

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

    private void setPopClickListener(View view) {
        TextView video, photo, cancle;
        photo = view.findViewById(R.id.photo);
        cancle = view.findViewById(R.id.cancle);

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLogout();
                mPop.dismiss();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }
    private void upLogout(){
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token",BaseApplication.getInstance().userBean.getToken());
        showDialog();
        XUtil.Post(URLConstant.LOGOUT_TC,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====退出登录===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        BaseApplication.getInstance().removerUser();
                        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                        BaseApplication.destoryActivity("login");
                        finish();
                    }else{
                        BaseApplication.getInstance().removerUser();
                        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                        BaseApplication.destoryActivity("login");
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
}
