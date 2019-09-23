package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.MyPublicTaskRecycleAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
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
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_tadvise)
public class TAdviseActivity extends BaseActivity implements  View.OnClickListener, View.OnLongClickListener{
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.et_yijian)
    private EditText et_yijian;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        imagePaths.clear();
        imagePaths.add(R.drawable.addtupian + "");
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.yjfg);

    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(this, imagePaths, this, this, 0);
        mRecyclerView.setAdapter(mTaskImgAdapter);
    }
    @Event(value = {R.id.rl_close,R.id.tv_yjtj}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.tv_yjtj:
                String content=et_yijian.getText().toString();
                if(content.length()<6){
                    CusToast.showToast("不能少于6个字");
                    return;
                }
                upYJData(content);
                break;
        }
    }
    private void upYJData(String msg_content) {
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("msg_content",msg_content);
        showDialog();
        XUtil.Post(URLConstant.TIJIAOYIJIAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====提交意见===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
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
                ex.printStackTrace();
                closeDialog();
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
