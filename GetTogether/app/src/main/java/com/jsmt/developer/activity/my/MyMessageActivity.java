package com.jsmt.developer.activity.my;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

public class MyMessageActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private FragmentTransaction transaction;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent dataIntent = getIntent();
        setContentView(R.layout.activity_my_message);

        initData();
        initView();

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        messageFragment = new MessageFragment();
        fragmentList.add(messageFragment);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_container, messageFragment);
        transaction.hide(messageFragment);
        transaction.commit();

        hideFragment(0);

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
    protected void initView() {

    }
}
