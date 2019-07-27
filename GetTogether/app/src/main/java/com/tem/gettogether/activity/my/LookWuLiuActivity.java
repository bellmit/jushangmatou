package com.tem.gettogether.activity.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.LookWuliuBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;

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

@ContentView(R.layout.activity_look_wu_liu)
public class LookWuLiuActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    private String order_id;
    @ViewInject(R.id.recyclerView)
    private RecyclerView recyclerView;
    private List<LookWuliuBean.ResultBean> resultBeans=new ArrayList<>();
    @ViewInject(R.id.iv_wliu_iamge)
    private ImageView iv_wliu_iamge;
    @ViewInject(R.id.tv_isqianshou)
    private TextView tv_isqianshou;
    @ViewInject(R.id.tv_kdName)
    private TextView tv_kdName;
    @ViewInject(R.id.tv_lxdh)
    TextView tv_lxdh;
    @ViewInject(R.id.iv_kdlx)
    CircularImage iv_kdlx;
    @ViewInject(R.id.tv_ssgs)
    private TextView tv_ssgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("查看物流");
        order_id=getIntent().getStringExtra("order_id");
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        upData();
    }
    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }
    private void upData(){
        Map<String,Object> map=new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id","415");
        showDialog();
        XUtil.Post(URLConstant.LOOK_WULIUZHUANGTAI,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                Log.i("====查看物流===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        LookWuliuBean lookWuliuBean=gson.fromJson(result,LookWuliuBean.class);
                        resultBeans=lookWuliuBean.getResult();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                if(resultBeans.size()>0){
                    tv_kdName.setText(resultBeans.get(0).getShipping_name()+"："+resultBeans.get(0).getList().getNu());
                    tv_lxdh.setText("");
                    recyclerView.setAdapter(new BaseRVAdapter(LookWuLiuActivity.this,resultBeans.get(0).getList().getData()) {
                        @Override
                        public int getLayoutId(int viewType) {
                            return R.layout.wuliuzhuantai_recy;
                        }

                        @Override
                        public void onBind(BaseViewHolder holder, int position) {
                            TextView tv_time_day= holder.getTextView(R.id.tv_time_day);
                            TextView shijian= holder.getTextView(R.id.shijian);
                            TextView tv_wlzt= holder.getTextView(R.id.tv_wlzt);
                            TextView tv_dizhi= holder.getTextView(R.id.tv_dizhi);
                            ImageView tv_jindu_icon=holder.getImageView(R.id.tv_jindu_icon);
                            if(resultBeans.get(0).getList().getData().size()>0){
                                String time=resultBeans.get(0).getList().getData().get(position).getFtime();
                                String[]   sInfo = time.split(" ");//按照换行进行分割
                                tv_time_day.setText(sInfo[0]);
                                shijian.setText(sInfo[1]);
                                tv_wlzt.setText(resultBeans.get(0).getList().getData().get(position).getContext());
                            }

                        }

                    });
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();

            }
        });
    }
}
