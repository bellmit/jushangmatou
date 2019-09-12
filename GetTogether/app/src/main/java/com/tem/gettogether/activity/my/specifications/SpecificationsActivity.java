package com.tem.gettogether.activity.my.specifications;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsActivity;
import com.tem.gettogether.activity.my.publishgoods.PublishGoodsPresenter;
import com.tem.gettogether.activity.my.specificationsdetail.SpecificationsDetailActivity;
import com.tem.gettogether.adapter.SpecificationsAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

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
    @ViewInject(R.id.description_tv)
    private TextView description_tv;
    private SpecificationsAdapter mSpecificationsAdapter;
    String format;
    String specification_description;
    private int selectCount;
    private String cat_id3;
    SpecificationsBean.ResultBean mSpecificationsBean;
    ArrayList<SpecificationsBean.ResultBean.SpecListBean> mLastSpecListBeanData = new ArrayList<>();
    @Override
    protected void initData() {
        x.view().inject(this);
        mPresenter = new SpecificationsPresenter(getContext(), SpecificationsActivity.this);
        mPresenter.attachView(this);
        tv_title.setText("产品规格");
        cat_id3 = getIntent().getStringExtra("cat_id3");
        format = getResources().getString(R.string.specification_description);
        specification_description = String.format(format, 0, 4);
        description_tv.setText(specification_description);
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
        map.put("cat_id3", cat_id3);
        mPresenter.getSpecificationsData(map);
    }


    @Override
    protected void initView() {


    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        closeDialog();
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
                if(selectCount<1){
                    CusToast.showToast("请选择至少1个规格");
                }else if (selectCount <= 4) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("SPECIFICATION_CHOSE",mLastSpecListBeanData);
                    startActivity(new Intent(this, SpecificationsDetailActivity.class).putExtras(bundle));
                } else {
                    CusToast.showToast("最多可选4个规格");
                }
                break;
        }
    }

    @Override
    public void getSpecificationsData(SpecificationsBean.ResultBean mmSpecificationsBean) {
        mSpecificationsBean = mmSpecificationsBean;
        mSpecificationsAdapter = new SpecificationsAdapter(getContext(), mSpecificationsBean.getSpecList());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mSpecificationsAdapter);
        mSpecificationsAdapter.setOnClickItem(new SpecificationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int mselectCount, List<SpecificationsBean.ResultBean.SpecListBean> mDatas) {
                mLastSpecListBeanData.removeAll(mLastSpecListBeanData);
                mLastSpecListBeanData.addAll(mDatas);
                selectCount = mselectCount;
                specification_description = String.format(format, mselectCount, 4);
                description_tv.setText(specification_description);

            }
        });
    }
}
