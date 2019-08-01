package com.tem.gettogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.fragment_open_vip)
public class OpenVipFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
