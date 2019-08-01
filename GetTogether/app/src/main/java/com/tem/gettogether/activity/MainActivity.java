package com.tem.gettogether.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.fragment.CartFragment;
import com.tem.gettogether.fragment.FenLeiFragment;
import com.tem.gettogether.fragment.HomeNewFragment;
import com.tem.gettogether.fragment.MeFragment;
import com.tem.gettogether.fragment.MessageFragment;
import com.tem.gettogether.fragment.PersionCenterCaiGouFragment;
import com.tem.gettogether.fragment.PersionCenterGongYingFragment;
import com.tem.gettogether.fragment.PublishBuyFragment;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.language.LanguageBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.DragPointView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements IUnReadMessageObserver, DragPointView.OnDragListencer {

    private static MainActivity mainActivity;

    @ViewInject(R.id.ll_home)
    private LinearLayout ll_home;
    @ViewInject(R.id.ll_Fl)
    private LinearLayout ll_Fl;
    @ViewInject(R.id.ll_fbqg)
    private LinearLayout ll_fbqg;
    @ViewInject(R.id.ll_card)
    private LinearLayout ll_card;
    @ViewInject(R.id.ll_message)
    private LinearLayout ll_message;
    @ViewInject(R.id.ll_My)
    private LinearLayout ll_My;
    @ViewInject(R.id.iv_my)
    private ImageView iv_my;
    @ViewInject(R.id.iv_cart)
    private ImageView iv_cart;
    @ViewInject(R.id.iv_message)
    private ImageView iv_message;
    @ViewInject(R.id.iv_fbqg)
    private ImageView iv_fbqg;
    @ViewInject(R.id.iv_fl)
    private ImageView iv_fl;
    @ViewInject(R.id.iv_home)
    private ImageView iv_home;
    @ViewInject(R.id.tv_my)
    private TextView tv_my;
    @ViewInject(R.id.tv_card)
    private TextView tv_card;
    @ViewInject(R.id.tv_message)
    private TextView tv_message;
    @ViewInject(R.id.tv_fbqg)
    private TextView tv_fbqg;
    @ViewInject(R.id.tv_fenl)
    private TextView tv_fenl;
    @ViewInject(R.id.tv_home)
    private TextView tv_home;
    private CartFragment cartFragment;
    private MeFragment meFragment;
    private MessageFragment messageFragment;
    private FenLeiFragment fenLeiFragment;
    private HomeNewFragment homeFragment;
    private PersionCenterGongYingFragment persionCenterGongYingFragment;
    private PersionCenterCaiGouFragment persionCenterCaiGouFragment;
    protected int position = 0;
    protected FragmentTransaction ft;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private FragmentTransaction transaction;
    private PublishBuyFragment publishBuyFragment;
    private static boolean isExit = false;
    private int tab = 0;
    @ViewInject(R.id.seal_num)
    private DragPointView mUnreadNumView;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void initView() {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LanguageBean bean) {
        if (bean != null) {
            recreate();
        }
    }

    @Override
    protected void initData() {
        Intent dataIntent = getIntent();
        EventBus.getDefault().register(this);
        BaseApplication.addDestoryActivity(this, "login");

        openTab(true, dataIntent);
        getSwipeBackLayout().setEnableGesture(false);//禁止右滑退出

        mUnreadNumView.setDragListencer(this);

        fragmentList = new ArrayList<>();

        homeFragment = new HomeNewFragment();
        fenLeiFragment = new FenLeiFragment();
        messageFragment = new MessageFragment();
        publishBuyFragment = new PublishBuyFragment();
        persionCenterGongYingFragment = new PersionCenterGongYingFragment();
        persionCenterCaiGouFragment = new PersionCenterCaiGouFragment();
        cartFragment = new CartFragment();
        meFragment = new MeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(fenLeiFragment);
        fragmentList.add(publishBuyFragment);
        fragmentList.add(cartFragment);
        fragmentList.add(messageFragment);
        fragmentList.add(meFragment);
        fragmentList.add(persionCenterGongYingFragment);
        fragmentList.add(persionCenterCaiGouFragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_container, homeFragment);
        transaction.add(R.id.fl_container, fenLeiFragment);
        transaction.add(R.id.fl_container, publishBuyFragment);
        transaction.add(R.id.fl_container, cartFragment);
        transaction.add(R.id.fl_container, messageFragment);
        transaction.add(R.id.fl_container, meFragment);
        transaction.add(R.id.fl_container, persionCenterGongYingFragment);
        transaction.add(R.id.fl_container, persionCenterCaiGouFragment);

        transaction.hide(fenLeiFragment);
        transaction.hide(publishBuyFragment);
        transaction.hide(cartFragment);
        transaction.hide(messageFragment);
        transaction.hide(meFragment);
        transaction.hide(persionCenterGongYingFragment);
        transaction.hide(persionCenterCaiGouFragment);
        transaction.commit();
        tv_home.setTextColor(getResources().getColor(R.color.bottom_text));
        tv_fenl.setTextColor(getResources().getColor(R.color.text));
        tv_fbqg.setTextColor(getResources().getColor(R.color.text));
        tv_card.setTextColor(getResources().getColor(R.color.text));
        tv_message.setTextColor(getResources().getColor(R.color.text));
        tv_my.setTextColor(getResources().getColor(R.color.text));
        iv_home.setImageResource(R.drawable.shouye2);
        iv_fl.setImageResource(R.drawable.fenlei);
        iv_fbqg.setImageResource(R.drawable.fbqg_main);
        iv_cart.setImageResource(R.drawable.jinhuoche1);
        iv_message.setImageResource(R.drawable.liaotian);
        iv_my.setImageResource(R.drawable.wode);
        openTab(0);
    }


    @Event(value = {R.id.ll_home, R.id.ll_Fl, R.id.ll_fbqg, R.id.ll_card, R.id.ll_My, R.id.ll_message}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        String token = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.TOKEN, "");
        String role_type = SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.ROLE_TYPE, "");
        switch (view.getId()) {
            case R.id.ll_home:
                tv_home.setTextColor(getResources().getColor(R.color.bottom_text));
                tv_fenl.setTextColor(getResources().getColor(R.color.text));
                tv_fbqg.setTextColor(getResources().getColor(R.color.text));
                tv_card.setTextColor(getResources().getColor(R.color.text));
                tv_message.setTextColor(getResources().getColor(R.color.text));
                tv_my.setTextColor(getResources().getColor(R.color.text));
                iv_home.setImageResource(R.drawable.shouye2);
                iv_fl.setImageResource(R.drawable.fenlei);
                iv_fbqg.setImageResource(R.drawable.fbqg_main);
                iv_cart.setImageResource(R.drawable.jinhuoche1);
                iv_message.setImageResource(R.drawable.liaotian);
                iv_my.setImageResource(R.drawable.wode);
                hideFragment(0);

                break;
            case R.id.ll_Fl:
                tv_home.setTextColor(getResources().getColor(R.color.text));
                tv_fenl.setTextColor(getResources().getColor(R.color.bottom_text));
                tv_fbqg.setTextColor(getResources().getColor(R.color.text));
                tv_card.setTextColor(getResources().getColor(R.color.text));
                tv_message.setTextColor(getResources().getColor(R.color.text));
                tv_my.setTextColor(getResources().getColor(R.color.text));
                iv_home.setImageResource(R.drawable.shouye);
                iv_fl.setImageResource(R.drawable.fenlei2);
                iv_fbqg.setImageResource(R.drawable.fbqg_main);
                iv_cart.setImageResource(R.drawable.jinhuoche1);
                iv_message.setImageResource(R.drawable.liaotian);
                iv_my.setImageResource(R.drawable.wode);
                hideFragment(1);

                break;
            case R.id.ll_fbqg:
                tv_home.setTextColor(getResources().getColor(R.color.text));
                tv_fenl.setTextColor(getResources().getColor(R.color.text));
                tv_fbqg.setTextColor(getResources().getColor(R.color.bottom_text));
                tv_card.setTextColor(getResources().getColor(R.color.text));
                tv_message.setTextColor(getResources().getColor(R.color.text));
                tv_my.setTextColor(getResources().getColor(R.color.text));
                iv_home.setImageResource(R.drawable.shouye);
                iv_fl.setImageResource(R.drawable.fenlei);
                iv_fbqg.setImageResource(R.drawable.fbqh_2main);
                iv_cart.setImageResource(R.drawable.jinhuoche1);
                iv_message.setImageResource(R.drawable.liaotian);
                iv_my.setImageResource(R.drawable.wode);
                if (token != null && !token.equals("")) {
                    hideFragment(2);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.ll_card:
                tv_home.setTextColor(getResources().getColor(R.color.text));
                tv_fenl.setTextColor(getResources().getColor(R.color.text));
                tv_fbqg.setTextColor(getResources().getColor(R.color.text));
                tv_card.setTextColor(getResources().getColor(R.color.bottom_text));
                tv_message.setTextColor(getResources().getColor(R.color.text));
                tv_my.setTextColor(getResources().getColor(R.color.text));
                iv_home.setImageResource(R.drawable.shouye);
                iv_fl.setImageResource(R.drawable.fenlei);
                iv_fbqg.setImageResource(R.drawable.fbqg_main);
                iv_cart.setImageResource(R.drawable.jinhuoche2);
                iv_message.setImageResource(R.drawable.liaotian);
                iv_my.setImageResource(R.drawable.wode);
                if (token != null && !token.equals("")) {
                    hideFragment(3);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }

                break;
            case R.id.ll_message:
                tv_home.setTextColor(getResources().getColor(R.color.text));
                tv_fenl.setTextColor(getResources().getColor(R.color.text));
                tv_fbqg.setTextColor(getResources().getColor(R.color.text));
                tv_card.setTextColor(getResources().getColor(R.color.text));
                tv_message.setTextColor(getResources().getColor(R.color.bottom_text));
                tv_my.setTextColor(getResources().getColor(R.color.text));
                iv_home.setImageResource(R.drawable.shouye);
                iv_fl.setImageResource(R.drawable.fenlei);
                iv_fbqg.setImageResource(R.drawable.fbqg_main);
                iv_cart.setImageResource(R.drawable.jinhuoche1);
                iv_message.setImageResource(R.drawable.liaotian2);
                iv_my.setImageResource(R.drawable.wode);
                if (token != null && !token.equals("")) {
                    hideFragment(4);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.ll_My:
                if (token != null && !token.equals("")) {
                    tv_home.setTextColor(getResources().getColor(R.color.text));
                    tv_fenl.setTextColor(getResources().getColor(R.color.text));
                    tv_fbqg.setTextColor(getResources().getColor(R.color.text));
                    tv_card.setTextColor(getResources().getColor(R.color.text));
                    tv_message.setTextColor(getResources().getColor(R.color.text));
                    tv_my.setTextColor(getResources().getColor(R.color.bottom_text));
                    iv_home.setImageResource(R.drawable.shouye);
                    iv_fl.setImageResource(R.drawable.fenlei);
                    iv_fbqg.setImageResource(R.drawable.fbqg_main);
                    iv_cart.setImageResource(R.drawable.jinhuoche1);
                    iv_message.setImageResource(R.drawable.liaotian);
                    iv_my.setImageResource(R.drawable.wode2);
                    if (role_type != null && role_type.equals("1")) {
                        hideFragment(6);
                    } else {
                        hideFragment(7);
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public void openTab(int position) {

        hideFragment(position);

    }

    public void openTab(boolean isCreate, Intent intent) {
        String tabStr = "0";
        if (intent != null) {
            tabStr = intent.getStringExtra("tab");
        }
        int index = 0;
        try {
            index = Integer.parseInt(tabStr);
        } catch (Exception e) {

        }
        tab = index;
        if (isCreate) {//没有打开
            if (tab == 0) {
                return;
            }
            hideFragment(tab);
        } else {
            openTab(tab);
        }
    }

    private void hideFragment(int index) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        try {
            for (int i = 0; i < fragmentList.size(); i++) {
                if (i == index) {
                    transaction.show(fragmentList.get(i));
                } else {
                    transaction.hide(fragmentList.get(i));
                }

            }
            transaction.commit();
        } catch (Exception e) {
            //  initView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        upGetMessageData();

    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            CusToast.showToast("再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            System.exit(0);
            Process.killProcess(Process.myPid());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        openTab(false, intent);

    }

    @Override
    public void onDragOut() {
        mUnreadNumView.setVisibility(View.GONE);
    }

    @Override
    public void onCountChanged(int count) {
        if (count == 0) {
            if (messageNum > 0) {
                mUnreadNumView.setVisibility(View.VISIBLE);
                mUnreadNumView.setText(String.valueOf(count + messageNum));
            } else {
                mUnreadNumView.setVisibility(View.GONE);
            }
        } else if (count > 0 && count < 100) {
            mUnreadNumView.setVisibility(View.VISIBLE);
            upGetMessageData();
            mUnreadNumView.setText(String.valueOf(count + messageNum));
        } else {
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText(R.string.no_read_message);
        }
    }

    private int messageNum = 0;
    /**
     * 接收未读消息的监听器。
     */
    private RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            System.out.println("main---onMessageIncreased---未读消息条数：" + count);
            if (count == 0) {
                if (messageNum > 0) {
                    mUnreadNumView.setVisibility(View.VISIBLE);
                    mUnreadNumView.setText(String.valueOf(count + messageNum));
                } else {
                    mUnreadNumView.setVisibility(View.GONE);
                }
            } else if (count > 0 && count < 100) {
                mUnreadNumView.setVisibility(View.VISIBLE);
                upGetMessageData();
                mUnreadNumView.setText(String.valueOf(count + messageNum));
            } else {
                mUnreadNumView.setVisibility(View.VISIBLE);
                mUnreadNumView.setText(R.string.no_read_message);
            }
        }
    };

    private void upGetMessageData() {
        Map<String, Object> map = new HashMap<>();
        if (BaseApplication.getInstance().userBean == null) return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        XUtil.Post(URLConstant.XITONGXIAO_WEIDU, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====系统信息未读数量===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject2 = new JSONObject(result2);
                        String count = jsonObject2.optString("count");
                        messageNum = Integer.parseInt(count);
                        if (messageNum > 0) {
                            mUnreadNumView.setVisibility(View.VISIBLE);
                            mUnreadNumView.setText(String.valueOf(messageNum));
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
