package com.tem.gettogether.activity.my.specificationsdetail;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.adapter.SpecificationsDetailAdapter;
import com.tem.gettogether.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specificationsdetail
 * @ClassName: SpecificationsDetailActivity
 * @Author: csc
 * @CreateDate: 2019/9/9 15:36
 * @Description:
 */
@ContentView(R.layout.activity_specifications_detail)
public class SpecificationsDetailActivity extends BaseMvpActivity<SpecificationsDetailPresenter> implements SpecificationsDetailContract.SpecificationsDetailView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private SpecificationsDetailAdapter mSpecificationsDetailAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("产品规格");

        mSpecificationsDetailAdapter = new SpecificationsDetailAdapter(getContext(), null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSpecificationsDetailAdapter);
    }

    @Override
    protected void initView() {

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

    @Event(value = {R.id.rl_close, R.id.tv_save}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_save:
                break;
        }
    }

}