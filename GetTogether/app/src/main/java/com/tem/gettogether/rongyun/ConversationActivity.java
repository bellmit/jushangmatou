package com.tem.gettogether.rongyun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShopActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.AddressDataBean;
import com.tem.gettogether.bean.HeadMessageBean;
import com.tem.gettogether.bean.MemberInformationBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

public class ConversationActivity extends BaseActivity implements OnClickListener {
    private TextView tv_title;

    private String getId, getTitle;
    private static final String TAG = "====RongTalk会话界面---";
    /**
     * 对方id
     */
    private String mTargetId;
    /**
     * 会话类型
     */
    private RelativeLayout base_rl;
    private Conversation.ConversationType mConversationType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
//        StatusBarUtil.setTranslucentStatus(this);

        initData();

        tv_title = (TextView) findViewById(R.id.tv_title);
        base_rl = (RelativeLayout) findViewById(R.id.base_rl);
        base_rl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        if (null != getTitle) {
            tv_title.setText(getTitle);
        } else {
            tv_title.setText(getText(R.string.rongyun_talk));
        }
        findViewById(R.id.rl_close).setOnClickListener(this);


        /**
         * 对话页面的一些时间监听处理
         */
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {


            // 消息长按事件
            @Override
            public boolean onMessageLongClick(Context arg0, View arg1, Message arg2) {
                // TODO Auto-generated method stub
                return false;
            }

            // 点击链接消息事件
            @Override
            public boolean onMessageLinkClick(Context arg0, String arg1) {
                // TODO Auto-generated method stub

                return false;
            }

            /**
             * 用户头像点击事件---- 如果自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false
             * 走融云默认处理方式。--其余事件处理方法一样
             */
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {

                getUserMessage(context,userInfo.getUserId());
                return true;
            }

            // 用户头像长按事件
            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {

                return false;
            }

            // 消息点击事件
            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                boolean isimage;

                if(message.getObjectName().contains("ImgTextMsg")){
                    String goods_id=SharedPreferencesUtils.getString(context, BaseConstant.SPConstant.Shop_goods_id,"");
                    if(goods_id!=null&&!goods_id.equals("")){
                        context.startActivity(new Intent(context, ShoppingParticularsActivity.class)
                                .putExtra("goods_id",goods_id));
                    }else{

                        CusToast.showToast(getText(R.string.spysx));
                    }
                    isimage=true;
                }else{
                    isimage=false;
                }
                return isimage;
            }
        });
/**
 * 设置位置信息的提供者。
 *
 * @param locationProvider 位置信息提供者。
 */
        RongIM.setLocationProvider(new RongIM.LocationProvider() {

            @Override
            public void onStartLocation(Context context, LocationCallback locationCallback) {
                //在这里打开你的地图页面,保存 locationCallback 对象。
//                MyApplication.getInstance().locationCallback = locationCallback;
//                Intent intent = new Intent(context, ShapeAddress.class);
//                startActivity(intent);
            }
        });
    }

    private void getUserMessage(final Context context, String userId){
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        XUtil.Post(URLConstant.MESSAGE_HEAD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取头像信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        HeadMessageBean mHeadMessageBean = gson.fromJson(result, HeadMessageBean.class);
                        if(mHeadMessageBean.getResult().getRole_type().equals("1")) {// 1是供应商
                            startActivity(new Intent(context, ShopActivity.class)
                                    .putExtra("store_id", mHeadMessageBean.getResult().getStore_id())
                                    .putExtra("type", ShopActivity.SHOPNHOME_TYPE));
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

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null)
            return;
        mTargetId = intent.getData().getQueryParameter("targetId");
        getTitle = intent.getData().getQueryParameter("title").toString();

//        mConversationType = Conversation.ConversationType.valueOf(intent.getData()
//                .getLastPathSegment().toUpperCase(Locale.US));
//        reconnect(mTargetId);
    }
    @Override
    protected void initView() {

    }
    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "---onTokenIncorrect--");
            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "---onSuccess--" + s);
                enterFragment(mConversationType, mTargetId);

            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Log.e(TAG, "---onError--" + e);
                enterFragment(mConversationType, mTargetId);
            }
        });

    }
    private ConversationFragmentEx fragment;

    /**
     * 加载会话页面 ConversationFragmentEx 继承自 ConversationFragment
     *
     * @param mConversationType 会话类型
     * @param mTargetId         会话 Id
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        fragment = new ConversationFragmentEx();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.conversation, fragment);
        transaction.commitAllowingStateLoss();
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rl_close:

                finish();
                break;

            default:
                break;
        }
    }


}
