package com.jsmt.developer.activity.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.adapter.FansAdapter;
import com.jsmt.developer.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_fans)
public class FansActivity extends BaseActivity{
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    private FansAdapter mFansAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("粉丝");
        mFansAdapter = new FansAdapter(getContext(),null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFansAdapter);
    }

    @Override
    protected void initView() {

    }
}
