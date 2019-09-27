package com.tem.gettogether.activity.linyi;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.classification.ClassificationActivity;
import com.tem.gettogether.activity.home.HomeSouSuoActivity;
import com.tem.gettogether.adapter.ClassificationLeftAdapter;
import com.tem.gettogether.adapter.ClassificationRightAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CategoriesClassificationBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

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

@ContentView(R.layout.fragment_linyi_fenlei)
public class LinYiClassificationActivity extends BaseActivity {

    @ViewInject(R.id.recyclerView_left)
    private RecyclerView recyclerView_left;
    @ViewInject(R.id.recyclerView_right)
    private RecyclerView recyclerView_right;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    private BaseActivity baseActivity;
    private List<CategoriesClassificationBean.ResultBean> resultBeans = new ArrayList<>();
    private List<CategoriesClassificationBean.ResultBean> mRightDatas = new ArrayList<>();
    private ClassificationLeftAdapter mClassificationLeftAdapter;
    private ClassificationRightAdapter mClassificationRightAdapter;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.linyi_mall));
        initViews();
        initData(0);
    }

    private void initViews() {
        mClassificationLeftAdapter = new ClassificationLeftAdapter(getContext(), resultBeans);
        recyclerView_left.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_left.setAdapter(mClassificationLeftAdapter);

        mClassificationRightAdapter = new ClassificationRightAdapter(getContext(), mRightDatas);
        recyclerView_right.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_right.setAdapter(mClassificationRightAdapter);

        mClassificationLeftAdapter.setOnClickItem(new ClassificationLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mRightDatas.removeAll(mRightDatas);
                mRightDatas.add(resultBeans.get(position));
                mClassificationRightAdapter.notifyDataSetChanged();
            }
        });

        mClassificationRightAdapter.setOnClickItem(new ClassificationRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int position2) {
                startActivity(new Intent(getContext(), ClassificationActivity.class)
                        .putExtra("classification_id", mRightDatas.get(position).getSon().get(position2).getId())
                        .putExtra("classification_type", 3)
                        .putExtra("is_yilian", true)
                        .putExtra("classification_name", mRightDatas.get(position).getSon().get(position2).getName()));
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Event(value = {R.id.rl_close, R.id.rl_saoyisao, R.id.tv_sousuo_fl}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_saoyisao:
                Toast.makeText(getContext(), getText(R.string.camera_function_not_open_yet), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sousuo_fl:
                startActivity(new Intent(getContext(), HomeSouSuoActivity.class)
                        .putExtra("store_id", "0")
                        .putExtra("is_yilian", true));
                break;

        }
    }

    private void initData(final int postion) {

        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
        }
        showDialog();
        XUtil.Post(URLConstant.LINYI_SHOP_CLASSIFICATION, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====分类数据===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        CategoriesClassificationBean fenLieBean = gson.fromJson(result, CategoriesClassificationBean.class);
                        resultBeans.removeAll(resultBeans);
                        resultBeans.addAll(fenLieBean.getResult());
                        mRightDatas.removeAll(mRightDatas);
                        mRightDatas.add(fenLieBean.getResult().get(postion));
                        mClassificationLeftAdapter.notifyDataSetChanged();
                        mClassificationRightAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });

    }

}
