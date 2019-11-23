package com.tem.gettogether.activity.my.specificationsdetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.adapter.GoodsSpecTypeNumberAdapter;
import com.tem.gettogether.adapter.SpecificationsDetailAdapter;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseMvpActivity;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.bean.StoreManagerListEntity;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        StatusBarUtil.setTranslucentStatus(this);

        tv_title.setText(R.string.product_specifications);
        mPresenter = new SpecificationsDetailPresenter(getContext(), SpecificationsDetailActivity.this);
        mPresenter.attachView(this);
        mLastSpecListBeanData = (ArrayList<SpecificationsBean.ResultBean.SpecListBean>) getIntent().getSerializableExtra("SPECIFICATION_CHOSE");
        for (int i = 0; i < mLastSpecListBeanData.size(); i++) {
            if (mLastSpecListBeanData.get(i).getIsChoose() != null && mLastSpecListBeanData.get(i).getIsChoose().equals("true")) {

                StoreManagerListEntity.GuigesEntity entity = new StoreManagerListEntity.GuigesEntity();
                entity.title = mLastSpecListBeanData.get(i).getName();
                entity.titleID = mLastSpecListBeanData.get(i).getId();

                entity.guigeArray.add("");
                entity.itemGuigeArray.add("");
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
                Map<String, Object> map = new HashMap<>();
                map.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                map.put("sm_arr", getSmArr());
                map.put("spec_arr",getSpecArr());
                mPresenter.saveSpecifications(map);
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            View view = getCurrentFocus();
            if(view!=recyclerView){

            }
        }
        return super.dispatchTouchEvent(event);
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

    private String getSmArr() {
        switch (mSpecNameList.size()) {
            case 1:
                try {
                    JSONArray allArray = new JSONArray();
                    for (int i = 0; i < mSpecNameList.get(0).itemGuigeArray.size() - 1; i++) {
                        JSONObject obj = new JSONObject();
                        JSONObject object = new JSONObject();
                        JSONArray array = new JSONArray();
                        object.put("spec_id", mSpecNameList.get(0).titleID);
                        object.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                        object.put("item", mSpecNameList.get(0).itemGuigeArray.get(i));
                        array.put(object);
                        obj.put("key_name", array);
                        allArray.put(obj);
                    }
                    Log.e("chenshichun", "--result1---  " + allArray.toString());
                    return allArray.toString();
                } catch (JSONException e) {
                    Log.e("chenshichun", "-----" + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    JSONArray allArray1 = new JSONArray();
                    for (int i = 0; i < mSpecNameList.get(0).itemGuigeArray.size() - 1; i++) {
                        for (int j = 0; j < mSpecNameList.get(1).itemGuigeArray.size() - 1; j++) {
                            JSONObject obj1 = new JSONObject();
                            JSONObject object1 = new JSONObject();
                            JSONObject object2 = new JSONObject();
                            JSONArray array1 = new JSONArray();
                            object1.put("spec_id", mSpecNameList.get(0).titleID);
                            object1.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                            object1.put("item", mSpecNameList.get(0).itemGuigeArray.get(i));
                            object2.put("spec_id", mSpecNameList.get(1).titleID);
                            object2.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                            object2.put("item", mSpecNameList.get(1).itemGuigeArray.get(j));
                            array1.put(object1);
                            array1.put(object2);
                            obj1.put("key_name", array1);
                            allArray1.put(obj1);
                        }
                    }
                    Log.e("chenshichun", "--result2---  " + allArray1.toString());
                    return allArray1.toString();
                } catch (JSONException e) {
                    Log.e("chenshichun", "-----" + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    JSONArray allArray1 = new JSONArray();
                    for (int i = 0; i < mSpecNameList.get(0).itemGuigeArray.size() - 1; i++) {
                        for (int j = 0; j < mSpecNameList.get(1).itemGuigeArray.size() - 1; j++) {
                            for (int k = 0; k< mSpecNameList.get(2).itemGuigeArray.size() -1;k++){
                                JSONObject obj1 = new JSONObject();
                                JSONObject object1 = new JSONObject();
                                JSONObject object2 = new JSONObject();
                                JSONObject object3 = new JSONObject();
                                JSONArray array1 = new JSONArray();
                                object1.put("spec_id", mSpecNameList.get(0).titleID);
                                object1.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                object1.put("item", mSpecNameList.get(0).itemGuigeArray.get(i));
                                object2.put("spec_id", mSpecNameList.get(1).titleID);
                                object2.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                object2.put("item", mSpecNameList.get(1).itemGuigeArray.get(j));
                                object3.put("spec_id", mSpecNameList.get(2).titleID);
                                object3.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                object3.put("item", mSpecNameList.get(2).itemGuigeArray.get(k));
                                array1.put(object1);
                                array1.put(object2);
                                array1.put(object3);
                                obj1.put("key_name", array1);
                                allArray1.put(obj1);
                            }
                        }
                    }
                    Log.e("chenshichun", "--result3---  " + allArray1.toString());
                    return allArray1.toString();
                } catch (JSONException e) {
                    Log.e("chenshichun", "-----" + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    JSONArray allArray1 = new JSONArray();
                    for (int i = 0; i < mSpecNameList.get(0).itemGuigeArray.size() - 1; i++) {
                        for (int j = 0; j < mSpecNameList.get(1).itemGuigeArray.size() - 1; j++) {
                            for (int k = 0; k< mSpecNameList.get(2).itemGuigeArray.size() -1;k++){
                                for (int z = 0; z< mSpecNameList.get(2).itemGuigeArray.size() -1;z++) {
                                    JSONObject obj1 = new JSONObject();
                                    JSONObject object1 = new JSONObject();
                                    JSONObject object2 = new JSONObject();
                                    JSONObject object3 = new JSONObject();
                                    JSONObject object4 = new JSONObject();
                                    JSONArray array1 = new JSONArray();
                                    object1.put("spec_id", mSpecNameList.get(0).titleID);
                                    object1.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                    object1.put("item", mSpecNameList.get(0).itemGuigeArray.get(i));
                                    object2.put("spec_id", mSpecNameList.get(1).titleID);
                                    object2.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                    object2.put("item", mSpecNameList.get(1).itemGuigeArray.get(j));
                                    object3.put("spec_id", mSpecNameList.get(2).titleID);
                                    object3.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                    object3.put("item", mSpecNameList.get(2).itemGuigeArray.get(k));
                                    object4.put("spec_id", mSpecNameList.get(3).titleID);
                                    object4.put("store_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.Shop_store_id, ""));
                                    object4.put("item", mSpecNameList.get(3).itemGuigeArray.get(z));
                                    array1.put(object1);
                                    array1.put(object2);
                                    array1.put(object3);
                                    array1.put(object4);
                                    obj1.put("key_name", array1);
                                    allArray1.put(obj1);
                                }
                            }
                        }
                    }
                    Log.e("chenshichun", "--result4---  " + allArray1.toString());
                    return allArray1.toString();
                } catch (JSONException e) {
                    Log.e("chenshichun", "-----" + e.getMessage());
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }

    private String getSpecArr() {
        JSONArray array1 =new JSONArray();
        for (int i=0;i<mSpecPriceList.size();i++){
            JSONObject object2 =new JSONObject();
            try {
                object2.put("key_name",mSpecPriceList.get(i).spec);
                object2.put("store_count","0");
                object2.put("price","0");
                object2.put("sku","0");
                array1.put(object2);
            }catch (Exception e){

            }
        }
        Log.e("chenshichun","-----"+array1.toString());
        return array1.toString();
    }

}