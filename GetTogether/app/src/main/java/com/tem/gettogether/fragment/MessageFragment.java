package com.tem.gettogether.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.XTMessageActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.RongYunBean;
import com.tem.gettogether.rongyun.ConversationListAdapterEx;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_message)
public class MessageFragment extends BaseFragment {

    private BaseActivity baseActivity;

    @ViewInject(R.id.main_viewpager)
    private ViewPager main_viewpager;
    private List<Fragment> mFragment = new ArrayList<>();
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_xtMessage)
    private LinearLayout ll_xtMessage;
    long firstClick = 0;
    long secondClick = 0;
    private OnMessageListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnMessageListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("chenshichun", "------------------");
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        initData();

    }

    private void initData() {
        rl_close.setVisibility(View.GONE);
        tv_title.setTextColor(getResources().getColor(R.color.bottom_text));
        tv_title.setText(R.string.message);

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {
                return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);

        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseActivity.finish();
            }
        });
        ll_xtMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), XTMessageActivity.class), 10000);
            }
        });
        Fragment conversationList = initConversationList();
        mFragment.add(conversationList);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        main_viewpager.setAdapter(fragmentPagerAdapter);
        main_viewpager.setOffscreenPageLimit(1);
        main_viewpager.setCurrentItem(0, false);
        if (main_viewpager.getCurrentItem() == 0) {
            if (firstClick == 0) {
                firstClick = System.currentTimeMillis();
            } else {
                secondClick = System.currentTimeMillis();
            }
            RLog.i("MainActivity", "time = " + (secondClick - firstClick));
            if (secondClick - firstClick > 0 && secondClick - firstClick <= 800) {
                mConversationListFragment.focusUnreadItem();
                firstClick = 0;
                secondClick = 0;
            } else if (firstClick != 0 && secondClick != 0) {
                firstClick = 0;
                secondClick = 0;
            }

        }
    }


    private ConversationListFragment mConversationListFragment = null;
    private boolean isDebug;
    private Conversation.ConversationType[] mConversationsTypes = null;

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + baseActivity.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + baseActivity.getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    UserInfo userInfo;

    private UserInfo findUserById(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        Log.d("chenshichun","=====userId  "+userId);
        map.put("user_id", userId);
//        baseActivity.showDialog();
        XUtil.Post(URLConstant.RONGYUN_NICKNAME_HEADPIC, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    Log.d("chenshichun","======列表信息====="+jsonObject.optString("result"));

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        RongYunBean mRongYunBean = gson.fromJson(result, RongYunBean.class);
                        userInfo = new UserInfo(mRongYunBean.getResult().getUser_id(),
                                mRongYunBean.getResult().getNickname(),Uri.parse(""));
                        RongIM.getInstance().refreshUserInfoCache(userInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
//                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
//                baseActivity.closeDialog();
            }
        });
        return userInfo;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10000) {
            listener.refreshMessage();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public interface OnMessageListener {
        void refreshMessage();
    }
}
