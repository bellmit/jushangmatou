package com.tem.gettogether.activity.my.specificationsdetail;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseMvpActivity;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_specifications_detail)
public class SpecificationsNewDetailActivity extends BaseMvpActivity<SpecificationsDetailPresenter> implements SpecificationsDetailContract.SpecificationsDetailView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    @Override
    protected void initData() {
        x.view().inject(this);
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
}
