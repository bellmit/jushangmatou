package com.tem.gettogether.activity.my;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.YHCardBean;
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

@ContentView(R.layout.activity_yetixian)
public class YETixianActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_tixian_fs)
    private  LinearLayout ll_tixian_fs;
    @ViewInject(R.id.et_TXJE)
    private EditText et_TXJE;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    private List<YHCardBean.ResultBean> resultBeans=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
        upcardLBData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        tv_title.setText("余额提现");
    }
    @Event(value = {R.id.rl_close,R.id.ll_tixian_fs,R.id.tv_sure}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.ll_tixian_fs:
                if(resultBeans.size()==0){
                    CusToast.showToast("请先绑定银行卡");
                    return;
                }
//                showPop(ll_tixian_fs);
                break;
            case R.id.tv_sure:
                String jitx=et_TXJE.getText().toString();
                if(!jitx.equals("")&&Integer.parseInt(jitx)>10){
                    Map<String,Object> map3=new HashMap<>();
                    map3.put("money",jitx);
                    map3.put("token", BaseApplication.getInstance().userBean.getToken());
                    if(resultBeans.size()>0){
                        map3.put("bank_id",resultBeans.get(0).getBank_id());
                    }

                    upYETXData(map3);
                }else {
                    CusToast.showToast("提现金额不能少于10");
                    return;
                }
                break;

        }
    }
    private void  upcardLBData(){
        Map<String,Object> map=new HashMap<>();
        map.put("token",BaseApplication.getInstance().userBean.getToken());
        showDialog();
        XUtil.Post(URLConstant.YINHANGFKALIEBIAO,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====银行卡列表===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        YHCardBean yhCardBean=gson.fromJson(result,YHCardBean.class);
                        resultBeans=yhCardBean.getResult();
                        if(resultBeans.size()>0){

                            String Num=resultBeans.get(0).getBank_number();
                            Log.i("====银行卡列表1===", Num);

                            String cardNum = null;
                            if(Num!=null&&Num.length()>=4){
                                cardNum=Num.substring(Num.length()-4,Num.length());
                                Log.i("====银行卡列表2===", cardNum);

                            }
                            tv_name.setText(resultBeans.get(0).getBank_name()+"("+cardNum+")");
                        }
                    }else {
                        CusToast.showToast(msg);

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
    private void  upYETXData(Map<String,Object> map){

        showDialog();
        XUtil.Post(URLConstant.YETX_TX,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====余额提现===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if(res.equals("1")){
                        Gson gson=new Gson();

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
    private PopupWindow mPop;


    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.chongzhi_layout, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(true);
            mPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });
            //设置弹窗内的点击事件
            setPopClickListener(view);
        }
    }

    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
    }

    private void setPopClickListener(View view) {
        ImageView iv_close;
        final ImageView iv_xz_zt2,iv_xz_zt3,iv_xz_zt;
        iv_close = view.findViewById(R.id.iv_close);
        iv_xz_zt2 = view.findViewById(R.id.iv_xz_zt2);
        iv_xz_zt3= view.findViewById(R.id.iv_xz_zt3);
        iv_xz_zt = view.findViewById(R.id.iv_xz_zt);

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        iv_xz_zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt2.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt3.setImageResource(R.drawable.weixuanzhong);

                mPop.dismiss();
            }
        });
        iv_xz_zt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt2.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt3.setImageResource(R.drawable.weixuanzhong);
                mPop.dismiss();
            }
        });
        iv_xz_zt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_xz_zt3.setImageResource(R.drawable.xuanzhongf);
                iv_xz_zt2.setImageResource(R.drawable.weixuanzhong);
                iv_xz_zt.setImageResource(R.drawable.weixuanzhong);
                mPop.dismiss();
            }
        });
    }
}
