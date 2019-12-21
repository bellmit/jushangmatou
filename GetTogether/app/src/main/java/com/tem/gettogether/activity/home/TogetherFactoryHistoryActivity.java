package com.tem.gettogether.activity.home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.SouSuoLSBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/14 16:51 
 */

@ContentView(R.layout.activity_together_factory_history)
public class TogetherFactoryHistoryActivity extends BaseActivity {
    @ViewInject(R.id.recy_sous_ls)
    private RecyclerView recy_sous_ls;
    @ViewInject(R.id.et_sousuo)
    private EditText et_sousuo;
    private List<SouSuoLSBean.ResultBean> resultBeans = new ArrayList<>();

    @Override
    protected void initData() {
        getHistoryData();
    }

    @Override
    protected void initView() {
        recy_sous_ls.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
    }

    @Event(value = {R.id.rl_close, R.id.rl_search, R.id.iv_remove})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_search:
                startActivityForResult(new Intent(this, TogetherFactorySearchActivity.class).putExtra("SEARCH_KEYWORDS", et_sousuo.getText().toString()), 103);
                break;
            case R.id.iv_remove:
                upRemoveData();
                break;
        }
    }

    private void upRemoveData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        showDialog();
        XUtil.Post(URLConstant.FACTORY_SEARCH_DELETE_HISTORY_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "====删除历史搜索===" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        getHistoryData();
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

            }
        });
    }


    private void getHistoryData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.USERID, ""));
        showDialog();
        XUtil.Post(URLConstant.FACTORY_SEARCH_HISTORY_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.e("chenshichun", "====聚工厂搜索历史===" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        SouSuoLSBean souSuoLSBean = gson.fromJson(result, SouSuoLSBean.class);
                        resultBeans = souSuoLSBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                recy_sous_ls.setAdapter(new BaseRVAdapter(TogetherFactoryHistoryActivity.this, resultBeans) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.sousuo_item_layout;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position) {
                        TextView tv_sousuo_tite = holder.getTextView(R.id.tv_sousuo_tite);
                        tv_sousuo_tite.setText(resultBeans.get(position).getContent());
                        holder.getTextView(R.id.tv_sousuo_tite).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityForResult(new Intent(TogetherFactoryHistoryActivity.this, TogetherFactorySearchActivity.class).putExtra("SEARCH_KEYWORDS", resultBeans.get(position).getContent()), 103);
                            }
                        });
                    }

                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }

    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103) {
            getHistoryData();
        }
    }

}
