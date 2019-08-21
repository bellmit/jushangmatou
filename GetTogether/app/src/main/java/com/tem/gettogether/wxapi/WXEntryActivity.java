package com.tem.gettogether.wxapi;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.activity.RegisterActivity;
import com.tem.gettogether.activity.SplashActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.LoginBean;
import com.tem.gettogether.bean.UserBean;
import com.tem.gettogether.bean.WeiXinBean1;
import com.tem.gettogether.bean.WeiXinMessageBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.ShowPopupWindow;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private static final String SECRET = "e0032941280de56565b3512804f0df67";
    private static final String WXAPP_ID = "wx93eea65ba215f901";
    private String url_access_token = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    private String url_refresh_token = "https://api.weixin.qq.com/sns/oauth2/refresh_token?";
    private String userinfo_url = "https://api.weixin.qq.com/sns/userinfo?";
    private Map<String, Object> map = new HashMap<>();
    private Map<String, Object> map_user = new HashMap<>();
    private Map<String, Object> map_refresh_token = new HashMap<>();
    private Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WXAPP_ID, true);
        api.registerApp(WXAPP_ID);

        api.handleIntent(getIntent(), this);
        gson = new Gson();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {//分享
            Log.e("===微信分享==", "微信分享操作.....");
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    CusToast.showToast("分享成功");
//                    Log.i("===微信分享", "微信分享成功.....");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://分享取消
                    CusToast.showToast("分享取消");
//                    Log.i("===微信分享", "微信分享取消.....");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://分享被拒绝
                    CusToast.showToast("分享被拒绝");
//                    Log.i("===微信分享", "微信分享被拒绝.....");
                    finish();
                    break;

            }
        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//登陆
//            Log.e("==微信登陆", "微信登录操作.....");
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    Log.e("resp", resp.getType() + "55");
                    if (resp.getType() == 1) {
                        String code = ((SendAuth.Resp) resp).code;
                        System.out.println(code);
                        map.put("appid", WXAPP_ID);
                        map.put("secret", SECRET);
                        map.put("code", code);
                        map.put("grant_type", "authorization_code");
                        XUtil.Post(url_access_token, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
//                                Log.i("====微信回调11111", "onSuccess: " + result);
                                java.lang.reflect.Type type = new TypeToken<WeiXinBean1>() {
                                }.getType();
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    WeiXinBean1 bean1 = new WeiXinBean1();
                                    bean1.setAccess_token(jsonObject.optString("access_token"));
                                    bean1.setExpires_in(jsonObject.optString("expires_in"));
                                    bean1.setRefresh_token(jsonObject.optString("refresh_token"));
                                    bean1.setOpenid(jsonObject.optString("openid"));
                                    bean1.setScope(jsonObject.optString("scope"));
                                    bean1.setUnionid(jsonObject.optString("unionid"));
                                    BaseApplication.getInstance().wxbean = bean1;
                                    getWeiXinUser();
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFinished() {
                                super.onFinished();
                            }
                        });

                    } else if (resp.getType() == 255) {
                        System.out.println(255 + "");
                        finish();
                    } else {
                        System.out.println("platform " + resp.getType());
                    }
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    finish();
                    break;
                default:
                    break;
            }
        }

    }

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private WeiXinMessageBean bean;

    private void getWeiXinUser() {
        map_user.put("access_token", BaseApplication.getInstance().wxbean.getAccess_token());
        map_user.put("openid", BaseApplication.getInstance().wxbean.getOpenid());
        XUtil.Post(userinfo_url, map_user, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("===微信回调22222", "onSuccess==" + result);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    bean = new WeiXinMessageBean();
//                    bean = gson.fromJson(result.toString(), WeiXinMessageBean.class);
                    bean.setOpenid(jsonObject.optString("openid"));
                    bean.setNickname(jsonObject.optString("nickname"));
                    bean.setSex(jsonObject.optString("sex"));
                    bean.setLanguage(jsonObject.optString("language"));
                    bean.setCity(jsonObject.optString("city"));
                    bean.setProvince(jsonObject.optString("province"));
                    bean.setCountry(jsonObject.optString("country"));
                    bean.setHeadimgurl(jsonObject.optString("headimgurl"));
                    bean.setUnionid(jsonObject.optString("unionid"));

//                    Log.i("===微信信息", "onSuccess: "+bean.toString());
                    BaseApplication.getInstance().bean = bean;

                    upLogin();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }

    public Dialog dialog;

    public void showDialog() {

        if (null == dialog) {
            dialog = ShowPopupWindow.unCancleableDialog(this);
        }
        dialog.show();
    }

    public void closeDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }

    private void upLogin() {
        Map<String, Object> map = new HashMap<>();
        map.put("openid", BaseApplication.getInstance().bean.getOpenid());
        map.put("from", "wx");//登陆类型
        map.put("nickname", BaseApplication.getInstance().bean.getNickname());
        map.put("head_pic", BaseApplication.getInstance().bean.getHeadimgurl());
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--微信登陆参数222--" + key, "" + value + "\n");
            }
        }
        showDialog();
        XUtil.Post(URLConstant.DISANF_LOGIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("==微信登陆222--", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.optString("msg");
                    String res = jsonObject.optString("status");

                    if ("1".equals(res)) {
                        Log.i("==微信登陆333--", result);
                        CusToast.showToast(msg);
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                        SharedPreferencesUtils.saveString(WXEntryActivity.this, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(WXEntryActivity.this, BaseConstant.SPConstant.head_pic, BaseApplication.getInstance().bean.getHeadimgurl());
                        SharedPreferencesUtils.saveString(WXEntryActivity.this, BaseConstant.SPConstant.openid, BaseApplication.getInstance().bean.getOpenid());
                        SharedPreferencesUtils.saveString(WXEntryActivity.this, BaseConstant.SPConstant.NAME, BaseApplication.getInstance().bean.getNickname());
                        SharedPreferencesUtils.saveString(WXEntryActivity.this, BaseConstant.SPConstant.TYPE, "3");//微信
                        SharedPreferencesUtils.saveString(WXEntryActivity.this,BaseConstant.SPConstant.USERID,loginBean.getResult().getUser_id());
                        SharedPreferencesUtils.saveString(WXEntryActivity.this,BaseConstant.SPConstant.LEVER,loginBean.getResult().getLevel());

                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        userBean.setUser_id(loginBean.getResult().getUser_id());
                        userBean.setHead_pic(loginBean.getResult().getHead_pic());
                        userBean.setLever(loginBean.getResult().getLevel());
                        BaseApplication.getInstance().userBean = userBean;
//                        startActivity(new Intent(WXEntryActivity.this,MainActivity.class));

                        if (loginBean.getResult().getMobile_validated().equals("0")) {
                            startActivity(new Intent(WXEntryActivity.this, RegisterActivity.class).putExtra(Contacts.REGISTER_TYPE, 1).putExtra(Contacts.REGISTER_OPEN_ID, BaseApplication.getInstance().bean.getOpenid()));
                        } else {
                            startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                        }

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


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }


}
