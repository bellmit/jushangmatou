package com.tem.gettogether.activity.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.adapter.FansAdapter;
import com.tem.gettogether.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_visitor)
public class VisitorActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    private FansAdapter mFansAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("访客");
        mFansAdapter = new FansAdapter(getContext(), null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mFansAdapter);
    }

    @Override
    protected void initView() {

    }
}

