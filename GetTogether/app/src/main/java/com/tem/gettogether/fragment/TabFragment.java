package com.tem.gettogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tem.gettogether.base.BaseFragment;

import org.xutils.x;


/**
 * 首页tabfragment
 */

public abstract  class TabFragment extends BaseFragment {

    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(getLayoutId(),null);
        initView();
        initData();
        initData(savedInstanceState);
        initListener();
        return  x.view().inject(this, inflater, container);
    }

    public abstract void initView() ;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    //加载数据
    protected abstract void loadData();

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置listener的操作
     */
    public void initListener() {

    }

    //传值
    public void initData(Bundle savedInstanceState) {
    }



    @Override
    protected void onFragmentFirstVisible() {
        //当第一次可见的时候，加载数据
        loadData();
    }

    public <T extends View> T getView(int viewId) {
        if(view == null){
            return null;
        }
        try {
            return (T) view.findViewById(viewId);
        } catch (Throwable e) {
            return null;
        }
    }
}