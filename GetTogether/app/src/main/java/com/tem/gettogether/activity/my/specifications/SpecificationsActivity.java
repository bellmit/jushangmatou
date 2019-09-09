package com.tem.gettogether.activity.my.specifications;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.TextDescriptionActivity;
import com.tem.gettogether.activity.my.TuWenXQActivity;
import com.tem.gettogether.activity.my.ZhuTuXQNewActivity;
import com.tem.gettogether.activity.my.specificationsdetail.SpecificationsDetailActivity;
import com.tem.gettogether.activity.my.specificationsdetail.SpecificationsDetailContract;
import com.tem.gettogether.adapter.SpecificationsAdapter;
import com.tem.gettogether.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specifications
 * @ClassName: SpecificationsActivity
 * @Author: csc
 * @CreateDate: 2019/9/9 11:33
 * @Description:商品规格
 */

@ContentView(R.layout.activity_specifications)
public class SpecificationsActivity extends BaseMvpActivity<SpecificationsPresenter> implements SpecificationsContract.SpecificationsView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private SpecificationsAdapter mSpecificationsAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText("产品规格");

        mSpecificationsAdapter = new SpecificationsAdapter(getContext(), null);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mSpecificationsAdapter);
        mSpecificationsAdapter.setOnClickItem(new SpecificationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
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

    @Event(value = {R.id.rl_close, R.id.tv_sure}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_sure:
                startActivity(new Intent(this, SpecificationsDetailActivity.class));
                break;
        }
    }
}
