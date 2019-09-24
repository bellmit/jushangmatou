package com.tem.gettogether.activity.my.specificationsdetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.specifications.SpecificationsActivity;
import com.tem.gettogether.activity.my.specifications.SpecificationsPresenter;
import com.tem.gettogether.adapter.GoodsSpecTypeNumberAdapter;
import com.tem.gettogether.adapter.SpecificationsDetailAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.bean.StoreManagerListEntity;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @ViewInject(R.id.goods_spec_recycleview)
    private RecyclerView goods_spec_recycleview;

    private SpecificationsDetailAdapter mSpecificationsDetailAdapter;
    private GoodsSpecTypeNumberAdapter mNumberAdapter;

    private List<StoreManagerListEntity.GuigesEntity> mSpecNameList = new ArrayList<>();
    private List<StoreManagerListEntity.SkuListEntity> mSpecPriceList = new ArrayList<>();
    ArrayList<SpecificationsBean.ResultBean.SpecListBean> mLastSpecListBeanData;

    List<String> mLastDatas = new ArrayList<>();

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(R.string.product_specifications);
        mPresenter = new SpecificationsDetailPresenter(getContext(), SpecificationsDetailActivity.this);
        mPresenter.attachView(this);
        mLastSpecListBeanData = (ArrayList<SpecificationsBean.ResultBean.SpecListBean>) getIntent().getSerializableExtra("SPECIFICATION_CHOSE");
        for (int i = 0; i < mLastSpecListBeanData.size(); i++) {
            if (mLastSpecListBeanData.get(i).getIsChoose() != null && mLastSpecListBeanData.get(i).getIsChoose().equals("true")) {

                StoreManagerListEntity.GuigesEntity entity = new StoreManagerListEntity.GuigesEntity();
                entity.title = mLastSpecListBeanData.get(i).getName();
                entity.titleID = mLastSpecListBeanData.get(i).getId();
                Log.d("chenshichun", "=====entity.titleID ======" + entity.titleID);
                entity.guigeArray.add("");
                mSpecNameList.add(entity);
            }

        }
        mSpecificationsDetailAdapter = new SpecificationsDetailAdapter(getContext(), mSpecNameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mSpecificationsDetailAdapter);

        mNumberAdapter = new GoodsSpecTypeNumberAdapter(this, mSpecPriceList);
        goods_spec_recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        goods_spec_recycleview.setAdapter(mNumberAdapter);
        goods_spec_recycleview.setNestedScrollingEnabled(false);

        mSpecificationsDetailAdapter.setCallItem(new SpecificationsDetailAdapter.OnCallListener() {
            @Override
            public void onCallAddData(String name, int position, List<String> mDatas) {
                Map<String, Object> map = new HashMap<>();
                map.put("spec_id", mSpecNameList.get(position).titleID);
                map.put("spec_item", name);
                map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                mPresenter.addSpecifications(map);

                mLastDatas.removeAll(mLastDatas);
                if (mDatas != null && mDatas.size() > 0)
                    mLastDatas.addAll(mDatas);

            }

            @Override
            public void onCallDeleteData(String name, int position, List<String> mDatas) {
                Map<String, Object> map = new HashMap<>();
                map.put("spec_id", mSpecNameList.get(position).titleID);
                map.put("spec_item", name);
                map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                mPresenter.deleteSpecifications(map);

                mLastDatas.removeAll(mLastDatas);
                if (mDatas != null && mDatas.size() > 0)
                    mLastDatas.addAll(mDatas);
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

    @Override
    public void specificationsListView() {
        mSpecPriceList.clear();
        String sku_name = "";
        for (int i = 0; i < mSpecNameList.size(); i++) {
            if (i < mSpecNameList.size() - 1) {
                sku_name = sku_name + mSpecNameList.get(i).title + ",";
            } else {
                sku_name = sku_name + mSpecNameList.get(i).title;
            }
        }
        if (mLastDatas != null) {
            for (int i = 0; i < mLastDatas.size(); i++) {
                StoreManagerListEntity.SkuListEntity serverEntity = new StoreManagerListEntity.SkuListEntity();
                serverEntity.spec = mLastDatas.get(i);
                serverEntity.sku_name = sku_name;
                mSpecPriceList.add(serverEntity);
            }
        }
        mNumberAdapter.notifyDataSetChanged();
    }
}