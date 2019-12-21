package com.tem.gettogether.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HotSouSuoBean;
import com.tem.gettogether.bean.SouSuoLSBean;
import com.tem.gettogether.utils.ListUtils;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.UiUtils;
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

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_home_sou_suo)

public class HomeSouSuoActivity extends BaseActivity {

    @ViewInject(R.id.recy_hot)
    private RecyclerView recy_hot;
    @ViewInject(R.id.recy_sous_ls)
    private RecyclerView recy_sous_ls;
    private List<SouSuoLSBean.ResultBean> resultBeans=new ArrayList<>();
    private List<HotSouSuoBean.ResultBean> hotresultBeans=new ArrayList<>();
    private List<HotSouSuoBean.ResultBean> list;
    private String store_id;
    @ViewInject(R.id.et_sousuo)
    private EditText et_sousuo;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    private boolean is_yilian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        StatusBarUtil.setTranslucentStatus(this);
        is_yilian = getIntent().getBooleanExtra("is_yilian",false);
        initData();
        initView();
        upSouSuoLSData();
        upSouSuoHotData();
    }

    @Override
    protected void initData() {
        recy_hot.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));
        recy_sous_ls.setLayoutManager(new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false));
        store_id = SharedPreferencesUtils.getString(getContext(),BaseConstant.SPConstant.Shop_store_id,"0");
    }

    @Override
    protected void initView() {
        et_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(et_sousuo.getText().toString().trim().equals("")){
                        CusToast.showToast(getText(R.string.qsrssnr));
                        return false;
                    }
                    startActivity(new Intent(HomeSouSuoActivity.this, HotFenLeiActivity.class)
                            .putExtra("keywords",et_sousuo.getText().toString().trim())
                            .putExtra("is_yilian",is_yilian));
                }
                return false;
            }
        });
    }
    @Event(value = {R.id.rl_close,R.id.rl_cancle,R.id.iv_ShuaX,R.id.iv_remove,R.id.rl_sousuo}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.rl_cancle:
                if(et_sousuo.getText().toString().trim().equals("")){
                    CusToast.showToast(getText(R.string.qsrssnr));
                    return;
                }
                startActivity(new Intent(this, HotFenLeiActivity.class)
                        .putExtra("keywords",et_sousuo.getText().toString().trim())
                        .putExtra("is_yilian",is_yilian));
                break;
            case R.id.iv_remove:
                upRemoveData();
                break;
            case R.id.iv_ShuaX:
                upSouSuoHotData();
                break;
            case R.id.rl_sousuo:
                if(et_sousuo.getText().toString().trim().equals("")){
                    CusToast.showToast(getText(R.string.qsrssnr));
                    return;
                }
                startActivity(new Intent(this, HotFenLeiActivity.class)
                        .putExtra("is_yilian",is_yilian)
                        .putExtra("keywords",et_sousuo.getText().toString().trim()));
                break;

        }
    }
    private void upSouSuoLSData(){
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        Log.d("chenshichun","==========="+store_id);
        //map.put("store_id",store_id);
        map.put("user_id",SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));

        showDialog();
        XUtil.Post(URLConstant.SOUSUOLISHI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====搜索历史===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        SouSuoLSBean souSuoLSBean=gson.fromJson(result,SouSuoLSBean.class);
                        resultBeans=souSuoLSBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                recy_sous_ls.setAdapter(new BaseRVAdapter(HomeSouSuoActivity.this,resultBeans) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.sousuo_item_layout;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position) {
                        TextView tv_sousuo_tite=holder.getTextView(R.id.tv_sousuo_tite);
                        tv_sousuo_tite.setText(resultBeans.get(position).getContent());
                        holder.getTextView(R.id.tv_sousuo_tite).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(HomeSouSuoActivity.this, HotFenLeiActivity.class)
                                        .putExtra("is_yilian",is_yilian)
                                        .putExtra("keywords",resultBeans.get(position).getContent()));
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
    private void upSouSuoHotData(){
        Map<String,Object> map=new HashMap<>();
        map.put("store_id","0");
        showDialog();
        XUtil.Post(URLConstant.RESHOULIEBIAO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====热门搜索===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        HotSouSuoBean hotSouSuoBean=gson.fromJson(result,HotSouSuoBean.class);
//                        hotresultBeans=hotSouSuoBean.getResult();
                        list=hotSouSuoBean.getResult();
                        if (ListUtils.isEmpty(list)){
                            CusToast.showToast(getResources().getText(R.string.no_more_data));
                            return;
                        }
                        hotresultBeans.removeAll(hotresultBeans);
                        hotresultBeans.addAll(list);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                recy_hot.setAdapter(new BaseRVAdapter(HomeSouSuoActivity.this,hotresultBeans) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.sousuo_item_layout;
                    }

                    @Override
                    public void onBind(BaseViewHolder holder, final int position) {
                        TextView tv_sousuo_tite=holder.getTextView(R.id.tv_sousuo_tite);
                        tv_sousuo_tite.setText(hotresultBeans.get(position).getContent());
                        holder.getTextView(R.id.tv_sousuo_tite).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(HomeSouSuoActivity.this, HotFenLeiActivity.class)
                                        .putExtra("is_yilian",is_yilian)
                                        .putExtra("keywords",hotresultBeans.get(position).getContent()));
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
    private void upRemoveData(){
        Map<String,Object> map=new HashMap<>();
        map.put("token", SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.TOKEN, ""));
        map.put("user_id",SharedPreferencesUtils.getString(getContext(), BaseConstant.SPConstant.USERID, ""));
        map.put("store_id",store_id);
        showDialog();
        XUtil.Post(URLConstant.REMOVESOUSUOLISHI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====删除历史搜索===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        upSouSuoLSData();
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
}
