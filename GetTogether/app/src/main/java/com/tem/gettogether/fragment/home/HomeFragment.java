package com.tem.gettogether.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseMvpFragment;
import com.uber.autodispose.AutoDisposeConverter;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/13 09:28 
 */
@ContentView(R.layout.fragment_third_home)
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.HomeView, View.OnClickListener, View.OnLongClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new HomePresenter(getContext(), getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return null;
    }


    }
