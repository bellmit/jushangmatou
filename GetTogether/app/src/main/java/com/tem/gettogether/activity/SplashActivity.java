package com.tem.gettogether.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

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
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.wxapi.WXEntryActivity;
import com.ybm.app.utils.NetUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;


/**
 * 启动页面
 */
public class SplashActivity extends BaseActivity {

    private static final int ANIMATION_DURATION = 1000;
    private boolean isStart = false;


    @Override
    protected void initData() {
        getConfig();
    }

    private void getConfig() {
        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {//没有网络
//            handler.postDelayed(goNextUiRunnable, ANIMATION_DURATION);
            return;
        } else {
//            handler.postDelayed(goNextUiRunnable, ANIMATION_DURATION);
            return;
        }
    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式（自己可以修改成想要的时间格式）
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getTimeCompareSize(String startTime, String endTime){
        int i=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                i= 1;
            }else if (date2.getTime()==date1.getTime()){
                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                //正常情况下的逻辑操作.
                i= 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  i;
    }
    @Override
    protected void initView() {
        String token = SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.TOKEN ,"");
        String phone = SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.MOBILEPHONE ,"");
        String pass = SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.PAYPASSWORD ,"");
        String type = SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.TYPE ,"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time=simpleDateFormat.format(date);
        int am_star_stau= getTimeCompareSize("2019年06月15日",time);
        Log.e("===软件限制===","=="+am_star_stau);
//        if(am_star_stau==3){
//            CusToast.showToast("版本失效，请重新下载新版本");
//            return;
//        }

        if(type!=null&&!type.equals("")){
            if(type.equals("0")){
                if(!phone.equals("")&&!pass.equals("")&&!type.equals("")){
                    upLogin(phone,pass,type);
                }else {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }
            }else  if(type.equals("2")){//QQ
                String openid=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.openid ,"");
                String nickname=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.NAME ,"");
                String head_pic=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.head_pic ,"");
                if(openid!=null&&!openid.equals("")){
                    upSANfangLogin(openid,nickname,head_pic,type);
                }else{
//                    startActivity(new Intent(this,LoginActivity.class));
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));

                    finish();
                }
            }else  if(type.equals("3")){//微信登录
                String openid=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.openid ,"");
                String nickname=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.NAME ,"");
                String head_pic=SharedPreferencesUtils.getString(SplashActivity.this, BaseConstant.SPConstant.head_pic ,"");
               if(openid!=null&&!openid.equals("")){
                   upSANfangLogin(openid,nickname,head_pic,type);
               }else{
//                   startActivity(new Intent(this,LoginActivity.class));
                   startActivity(new Intent(SplashActivity.this,MainActivity.class));

                   finish();
               }
            }else{
//                startActivity(new Intent(this,LoginActivity.class));
                startActivity(new Intent(SplashActivity.this,MainActivity.class));

                finish();
            }
        }else{
//            startActivity(new Intent(this,LoginActivity.class));
            startActivity(new Intent(SplashActivity.this,MainActivity.class));

            finish();
        }

    }
    private void upLogin(final String phone, final String pass, final String type){
        Map<String,Object> map=new HashMap<>();
        map.put("username",phone);
        map.put("type",type);
        map.put("password",pass);
//        showDialog();
        XUtil.Post(URLConstant.LOGIN,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====登录111===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        if(type.equals("0")){
                            SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.MOBILEPHONE, phone);
                            SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.PAYPASSWORD, pass);
                        }
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.TYPE, type);

                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        BaseApplication.getInstance().userBean = userBean;
                        doConnection();
                        gotoAtivity(MainActivity.class);
                        finish();

                    }else if(res.equals("-1")){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
//                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                closeDialog();

            }
        });
    }
    private void upSANfangLogin(final String openid, final String nickname, final String head_pic, final String type){
        final Map<String,Object> map=new HashMap<>();
        map.put("openid", openid);
        if(type.equals("2")){
            map.put("from","qq");//登陆类型

        }else if(type.equals("3")){
            map.put("from","wx");//登陆类型
        }
        map.put("nickname",nickname);
        map.put("head_pic",head_pic);
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object value = map.get(key);
                Log.e("--微信登陆参数--" + key, "" + value + "\n");
            }
        }
        XUtil.Post(URLConstant.DISANF_LOGIN,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("==微信登陆--",result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    String msg=jsonObject.optString("msg");
                    CusToast.showToast(msg);

                    if("1".equals(jsonObject.optString("status"))){
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.TOKEN, loginBean.getResult().getToken());
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.head_pic, head_pic);
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.openid,openid);
                        SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.NAME, nickname);
                        if(type.equals("2")){
                            SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.TYPE, "2");//QQ
                        }else if(type.equals("3")){
                            SharedPreferencesUtils.saveString(SplashActivity.this, BaseConstant.SPConstant.TYPE, "3");//微信
                        }
                        UserBean userBean = new UserBean();
                        userBean.setToken(loginBean.getResult().getToken());
                        userBean.setUserName(loginBean.getResult().getNickname());
                        userBean.setPhone(loginBean.getResult().getMobile());
                        userBean.setChat_id(loginBean.getResult().getChat_id());
                        BaseApplication.getInstance().userBean = userBean;
                        doConnection();
                        gotoAtivity(MainActivity.class);
                        finish();
                    }else if(jsonObject.optString("status").equals("-1")){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
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
    private TextView numTag2;

    /**
     * 接收未读消息的监听器。
     */
    private RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            System.out.println("main---onMessageIncreased---未读消息条数：" + count);
            if (count > 0) {
                if (null != numTag2) {
//                    numTag2.setVisibility(View.VISIBLE);
                }
            } else {
                if (null != numTag2) {
//                    numTag2.setVisibility(View.GONE);
                }
            }
        }
    };


    /**
     * update--
     */
    private void doConnection() {

        /**
         * 链接自己的token值
         */
        RongIM.connect(BaseApplication.getInstance().userBean.getChat_id(), new RongIMClient.ConnectCallback() {

            @Override
            public void onSuccess(String arg0) {
                Log.e("====连接融云--onSuccess", arg0.toString() + "");
                Confirg.isRongCloudConnected = true;
                /**
                 * 接收未读消息的监听器。
                 * @param listener  接收所有未读消息消息的监听器。
                 */

                final Conversation.ConversationType[] conversationTypes = {
                        Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
                };

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
                    }
                }, 500);
            }

            @Override
            public void onError(RongIMClient.ErrorCode arg0) {
                Log.e("====连接融云--onError", arg0.toString());
            }

            @Override
            public void onTokenIncorrect() {
                Log.e("====连接融云--onTokenI", "---onTokenIncorrect-----");
            }
        });

    }
    /**
     * 取消未执行的任务
     */
    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(goNextUiRunnable);
    }

    static Handler handler = new Handler();

    Runnable goNextUiRunnable = new Runnable() {
        @Override
        public void run() {
            gotoAtivity(MainActivity.class);
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
