package com.tem.gettogether.rongyun;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.MainActivity;
import com.tem.gettogether.base.BaseFragment;
import com.tem.gettogether.fragment.TabFragment;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.push.RongPushClient;

public class ConversationListFragment extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);

    }


}
