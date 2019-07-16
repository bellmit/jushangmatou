package com.tem.gettogether.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.LoginBean;
import com.tem.gettogether.bean.UserBean;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.CountDownTimerUtils3;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.wxapi.WXEntryActivity;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.SocializeUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_passworld)
    private EditText et_passworld;
    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.view_line2)
    private View view_line2;
    @ViewInject(R.id.view_line)
    private View view_line;
    @ViewInject(R.id.tv_zhpass)
    private TextView tv_zhpass;
    @ViewInject(R.id.tv_phone)
    private TextView tv_phone;
    @ViewInject(R.id.tv_code)
    private TextView tv_code;
    @ViewInject(R.id.denglu_zt_2)
    private LinearLayout denglu_zt_2;
    @ViewInject(R.id.denglu_zt)
    private LinearLayout denglu_zt;
    private String type="0";
    @ViewInject(R.id.et_code)
    private EditText et_code;
    @ViewInject(R.id.ll_wechat_login)
    private LinearLayout ll_wechat_login;
    private IWXAPI api;
    private String APP_ID = "wx93eea65ba215f901";
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ,  SHARE_MEDIA.WEIXIN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initPlatforms();
        UMShareAPI.get(this).fetchAuthResultWithBundle(this, savedInstanceState, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                SocializeUtils.safeShowDialog(dialog2);
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize succeed", Toast.LENGTH_SHORT).show();
//                shareAdapter.notifyDataSetChanged();
                SocializeUtils.safeCloseDialog(dialog2);
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onError", Toast.LENGTH_SHORT).show();
//                shareAdapter.notifyDataSetChanged();
                SocializeUtils.safeCloseDialog(dialog2);
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getApplicationContext(), "onRestoreInstanceState Authorize onCancel", Toast.LENGTH_SHORT).show();
//                shareAdapter.notifyDataSetChanged();
                SocializeUtils.safeCloseDialog(dialog2);
            }
        });
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.denglu);

    }

    @Override
    protected void initView() {

    }
    @Event(value = {R.id.rl_close,R.id.tv_forgetPwd,R.id.ll_wechat_login,R.id.ll_qq_login,R.id.tv_zc,R.id.tv_login,R.id.tv_code,R.id.ll_phone_login,R.id.ll_zh_login}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_forgetPwd:
                startActivity(new Intent(this,ForgetPassActivity.class));
                break;
            case R.id.tv_code:
                if(et_phone.getText().toString().length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }
                upCode(et_phone.getText().toString());

                break;
            case R.id.ll_phone_login:
                view_line.setBackgroundColor(getResources().getColor(R.color.my_red));
                view_line2.setBackgroundColor(getResources().getColor(R.color.line));
                tv_phone.setTextColor(getResources().getColor(R.color.my_red));
                tv_zhpass.setTextColor(getResources().getColor(R.color.text3));
                denglu_zt.setVisibility(View.VISIBLE);
                denglu_zt_2.setVisibility(View.GONE);
                type="1";
                break;

            case R.id.ll_zh_login:
                view_line2.setBackgroundColor(getResources().getColor(R.color.my_red));
                view_line.setBackgroundColor(getResources().getColor(R.color.line));
                tv_zhpass.setTextColor(getResources().getColor(R.color.my_red));
                tv_phone.setTextColor(getResources().getColor(R.color.text3));
                denglu_zt.setVisibility(View.GONE);
                denglu_zt_2.setVisibility(View.VISIBLE);
                type="0";
                break;
            case R.id.tv_zc:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_login:
                String phone=et_phone.getText().toString().trim();
                String pass=et_passworld.getText().toString().trim();
                String code=et_code.getText().toString().trim();
                if(phone.length()<11){
                    CusToast.showToast("请输入完整的手机号");
                    return;
                }
                if(type.equals("0")){
                    if(pass.length()<6){
                        CusToast.showToast("请输入不少于6位的密码");
                        return;
                    }
                }
                upLogin(phone,pass,type);

                break;
            case R.id.ll_wechat_login:
                if (api == null) {
                    api = WXAPIFactory.createWXAPI(this, APP_ID, true);
                    api.registerApp(APP_ID);
                }
                if (!api.isWXAppInstalled()) {
                    //提醒用户没有按照微信
                    Toast.makeText(this, "没有安装微信,请先安装微信!", Toast.LENGTH_SHORT).show();
                    return;
                }
                WXTextObject textObject = new WXTextObject();
                textObject.text = "text";
                WXMediaMessage message = new WXMediaMessage();
                message.mediaObject = textObject;
                message.description = "text";
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
                break;
            case R.id.ll_qq_login:
                UMShareAPI.get(this).doOauthVerify(this, platforms.get(0).mPlatform, authListener3);

                break;

        }
    }
    private void upLogin(final String phone, final String pass, final String type){
        Map<String,Object> map=new HashMap<>();
        map.put("username",phone);
        map.put("type",type);
        if(type.equals("1")){
            if(et_code.getText().toString().trim().length()<4){
                CusToast.showToast("请输入正确的验证码");
                return;
            }
            map.put("code",et_code.getText().toString().trim());
        }else{
            map.put("password",pass);
        }
        showDialog();
        XUtil.Post(URLConstant.LOGIN,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====登录===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);

                    if(res.equals("1")){
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        if(type.equals("0")){
                            SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.MOBILEPHONE, phone);
                            SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.PAYPASSWORD, pass);
                        }
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.TYPE, type);

                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        BaseApplication.getInstance().userBean = userBean;
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
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
    private void upCode(String phone){
        Map<String,Object> map=new HashMap<>();
        map.put("mobile",phone);
        map.put("unique_id","0");
        showDialog();
        XUtil.Post(URLConstant.HUOQU_CODE,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====注册获取验证码===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        CountDownTimerUtils3 mCountDownTimerUtils = new CountDownTimerUtils3(tv_code, 60000, 1000);
                        mCountDownTimerUtils.start();
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
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    UMAuthListener authListener3 = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog2);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog2);
            UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, platform, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Set<String> set = map.keySet();
                    String QQname="";
                    String QQhead="";
                    String QQopenid="";
                    for (String string : set) {
                        if(string.equals("profile_image_url")){
                            QQhead= map.get(string);
                            Log.i("-----------头像",QQhead);
                        }
                        if(string.equals("screen_name")){
                            QQname= map.get(string);
                            Log.i("-----------昵称",QQname);
                        }
                        if(string.equals("openid")){
                            QQopenid= map.get(string);
                            Log.i("-----------openid",QQopenid);
                        }
                    }

                    upQQLogin(QQopenid,QQname,QQhead);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog2);
            Toast.makeText(LoginActivity.this, "QQ登录失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog2);
            Toast.makeText(LoginActivity.this, "您取消了QQ登录", Toast.LENGTH_LONG).show();
        }
    };
    private void upQQLogin(final String QQopenid, final String QQname, final String QQhead ){
        Map<String, Object> map = new HashMap<>();
        map.put("openid", QQopenid);
        map.put("from","qq");//登陆类型
        map.put("nickname",QQname);
        map.put("head_pic",QQhead);
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--QQ登陆--" + key, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.DISANF_LOGIN,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("==QQ登陆--",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String msg=jsonObject.optString("msg");
                    String res=jsonObject.optString("status");

                    if("1".equals(res)){
                        Log.i("==QQ登陆--",result);
                        CusToast.showToast(msg);
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.head_pic,QQhead);
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.openid, QQopenid);
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.NAME,QQname);
                        SharedPreferencesUtils.saveString(LoginActivity.this, BaseConstant.SPConstant.TYPE, "2");//QQ
                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        BaseApplication.getInstance().userBean = userBean;
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
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
                ex.printStackTrace();

            }
        });

    }

}
