package com.tem.gettogether.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.my.AddressGLActivity;
import com.tem.gettogether.activity.my.BuyMessageActivity;
import com.tem.gettogether.activity.my.GRMeassageActivity;
import com.tem.gettogether.activity.my.GYWeActivity;
import com.tem.gettogether.activity.my.MemberActivity;
import com.tem.gettogether.activity.my.MyMessageActivity;
import com.tem.gettogether.activity.my.MyOrderActivity;
import com.tem.gettogether.activity.my.MyPocketActivity;
import com.tem.gettogether.activity.my.NewShopRenZhengActivity;
import com.tem.gettogether.activity.my.PostEvaluationActivity;
import com.tem.gettogether.activity.my.SCconnectActivity;
import com.tem.gettogether.activity.my.SettingActivity;
import com.tem.gettogether.activity.my.ShopDTFBActivity;
import com.tem.gettogether.activity.my.StoreManagementActivity;
import com.tem.gettogether.activity.my.TAdviseActivity;
import com.tem.gettogether.activity.my.ZuJiActivity;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.MyMessageBean;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.tem.gettogether.view.CircularImage;
import com.tem.gettogether.view.DragPointView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_me)
public class MeFragment extends TabFragment implements IUnReadMessageObserver, DragPointView.OnDragListencer {
    @ViewInject(R.id.tv_setting)
    TextView tv_setting;
    @ViewInject(R.id.ll_scj)
    TextView ll_scj;
    @ViewInject(R.id.ll_zj)
    LinearLayout ll_zj;
    @ViewInject(R.id.ll_qb)
    LinearLayout ll_qb;
    @ViewInject(R.id.tv_all)
    TextView tv_all;
    @ViewInject(R.id.tv_dfh)
    TextView tv_dfh;
    @ViewInject(R.id.tv_dfk)
    TextView tv_dfk;
    @ViewInject(R.id.tv_dsh)
    TextView tv_dsh;
    @ViewInject(R.id.rl_ksbh)
    RelativeLayout rl_ksbh;
    @ViewInject(R.id.rl_dzgl)
    RelativeLayout rl_dzgl;

    @ViewInject(R.id.rl_sprz)
    RelativeLayout rl_sprz;
    @ViewInject(R.id.rl_zxkf)
    RelativeLayout rl_zxkf;
    @ViewInject(R.id.rl_tdyj)
    RelativeLayout rl_tdyj;
    @ViewInject(R.id.rl_gywm)
    RelativeLayout rl_gywm;
    @ViewInject(R.id.iv_head)
    private CircularImage iv_head;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_scNum)
    private TextView tv_scNum;
    @ViewInject(R.id.tv_zjNum)
    private TextView tv_zjNum;
    @ViewInject(R.id.tv_qb_num)
    private TextView tv_qb_num;
    @ViewInject(R.id.tv_shop_RZ)
    private TextView tv_shop_RZ;
    @ViewInject(R.id.tv_shopZZ)
    private TextView tv_shopZZ;
    private   BaseActivity baseActivity;
    private MyMessageBean.ResultBean resultBean=new MyMessageBean.ResultBean();
    @ViewInject(R.id.seal_num)
    private DragPointView mUnreadNumView;
    @ViewInject(R.id.tv_hy_time)
    private TextView tv_hy_time;
    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
        initView();
    }

    @ViewInject(R.id.rl_fabupingji)
    private RelativeLayout rl_fabupingji;
    @Override
    public void initView() {
        mUnreadNumView.setDragListencer(this);
        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RongIM.getInstance().setOnReceiveUnreadCountChangedListener(mCountListener, conversationTypes);
            }
        }, 500);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        rl_fabupingji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PostEvaluationActivity.class));
            }
        });
    }

    @Override
    protected void loadData() {

    }
    @Event(value = {R.id.rl_dpgl,R.id.rl_sprz_new,R.id.iv_head,R.id.tv_setting, R.id.ll_scj, R.id.ll_zj, R.id.ll_qb,R.id.tv_all, R.id.tv_dfh, R.id.tv_dsh, R.id.tv_dfk
            , R.id.rl_ksbh, R.id.rl_dzgl, R.id.rl_sprz, R.id.rl_zxkf, R.id.rl_tdyj,R.id.rl_my_message, R.id.rl_gywm,R.id.ll_dprz}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                startActivity(new Intent(getActivity(),GRMeassageActivity.class));

                break;
            case R.id.tv_setting:
                startActivity(new Intent(getActivity(),SettingActivity.class));

                break;
            case R.id.rl_sprz_new:
                startActivity(new Intent(getActivity(),ShopDTFBActivity.class));

                break;
            case R.id.rl_dpgl:
                startActivity(new Intent(getActivity(), StoreManagementActivity.class));
                break;
            case R.id.ll_dprz://我的商铺
                if(resultBean.getStore_status()==1){//店铺管理
                    startActivity(new Intent(getActivity(), StoreManagementActivity.class));

                }else  if(resultBean.getStore_status()==2){
                    CusToast.showToast("店铺审核中");
                    return;
                } else  if(resultBean.getStore_status()==0||resultBean.getStore_status()==3||resultBean.getStore_status()==4){//店铺管理
//                    startActivity(new Intent(getActivity(),ShopRZSHActivity.class));
                    startActivity(new Intent(getActivity(),NewShopRenZhengActivity.class));


                }
//                startActivity(new Intent(getActivity(),ShopRZActivity.class));
                break;
            case R.id.ll_scj://收藏夹
                startActivityForResult(new Intent(getActivity(), SCconnectActivity.class)
                        .putExtra("type", SCconnectActivity.NICKNAME_TYPE), SCconnectActivity.NICKNAME_TYPE);
                break;
            case R.id.ll_zj://足迹
                startActivity(new Intent(getActivity(),ZuJiActivity.class));
                break;
            case R.id.ll_qb://钱包
                startActivity(new Intent(getActivity(),MyPocketActivity.class)
                .putExtra("money",tv_qb_num.getText().toString()));

                break;
            case R.id.tv_all://全部
                startActivity(new Intent(getActivity(),MyOrderActivity.class)
                        .putExtra("tabType","0"));

                break;
            case R.id.tv_dfh://待发货
                startActivity(new Intent(getActivity(),MyOrderActivity.class)
                        .putExtra("tabType","2"));

                break;
            case R.id.tv_dsh://待收货
                startActivity(new Intent(getActivity(),MyOrderActivity.class)
                        .putExtra("tabType","3"));

                break;
            case R.id.tv_dfk://待付款
                startActivity(new Intent(getActivity(),MyOrderActivity.class)
                        .putExtra("tabType","1"));

                break;
            case R.id.rl_ksbh://快速补货-\-求购信息
//                startActivity(new Intent(getActivity(),KSPHActivity.class));
                startActivity(new Intent(getActivity(),BuyMessageActivity.class));
                break;
            case R.id.rl_dzgl://地址管理
                startActivity(new Intent(getActivity(),AddressGLActivity.class));
                break;
            case R.id.rl_sprz://商铺认证
                if(resultBean.getStore_status()==1){//店铺管理
                    startActivity(new Intent(getActivity(), StoreManagementActivity.class));
                }else  if(resultBean.getStore_status()==2){
                    CusToast.showToast("店铺审核中");
                    return;
                } else  if(resultBean.getStore_status()==0||resultBean.getStore_status()==3||resultBean.getStore_status()==4){//店铺管理
//                    startActivity(new Intent(getActivity(),ShopRZSHActivity.class));
                    startActivity(new Intent(getActivity(),NewShopRenZhengActivity.class));


                }

                break;
            case R.id.rl_zxkf://在线客服
                showPop(rl_zxkf);

                break;
            case R.id.rl_tdyj://提点意见
                startActivity(new Intent(getActivity(),TAdviseActivity.class));

                break;
            case R.id.rl_gywm://关于我们
                startActivity(new Intent(getActivity(),GYWeActivity.class));

                break;
            case R.id.rl_my_message:
                startActivity(new Intent(getActivity(),MyMessageActivity.class));
                break;
            default:break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        upMessageNumData();
        upGetMessageData();
    }
    private PopupWindow mPop;
    //初始化弹窗
    private void initPop() {
        if (mPop == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_layout, null);
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
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
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
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }

    private void setPopClickListener(View view) {
        TextView tv_iteam1, photo, cancle;
        photo = view.findViewById(R.id.photo);
        cancle = view.findViewById(R.id.cancle);
        tv_iteam1=view.findViewById(R.id.tv_iteam1);
        tv_iteam1.setText(R.string.kefudian);
        photo.setText(getResources().getText(R.string.call)+resultBean.getService_qq());
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri datav = Uri.parse("tel:" + resultBean.getService_qq());
                intent.setData(datav);
                startActivity(intent);
                mPop.dismiss();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }
    private void upGetMessageData() {
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        baseActivity.showDialog();
        XUtil.Post(URLConstant.GET_MESSAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====获取个人信息===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        MyMessageBean myMessageBean=gson.fromJson(result,MyMessageBean.class);

                        Glide.with(getActivity()).load(myMessageBean.getResult().getHead_pic()+"").asBitmap().error( R.drawable.img12x).centerCrop().into(new BitmapImageViewTarget(iv_head));
                        tv_name.setText(myMessageBean.getResult().getNickname());
                        tv_qb_num.setText(myMessageBean.getResult().getUser_money());
                        tv_zjNum.setText(myMessageBean.getResult().getFootprint_count());
                        tv_scNum.setText(myMessageBean.getResult().getCollect_count());
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(myMessageBean.getResult().getUser_id(), myMessageBean.getResult().getNickname(), Uri.parse(String.valueOf(myMessageBean.getResult().getHead_pic()))));
                        if(myMessageBean.getResult().getExpire_time()!=null&&!myMessageBean.getResult().getExpire_time().equals("")){
                            tv_hy_time.setVisibility(View.VISIBLE);
                            tv_hy_time.setText(myMessageBean.getResult().getExpire_time()+"到期");
                        }



                        //有时间的时候显示多久到期
                        tv_hy_time.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(getActivity(), MemberActivity.class));
                            }
                        });



                        if(myMessageBean.getResult().getStore_status()==0){
                            tv_shopZZ.setText(R.string.shopRZ);
                            tv_shop_RZ.setText(R.string.shopRZ);
                        }else if(myMessageBean.getResult().getStore_status()==3){
                            tv_shopZZ.setText(R.string.shopRZ);
                            tv_shop_RZ.setText(R.string.shopRZ);
                        }else if(myMessageBean.getResult().getStore_status()==4){
                            tv_shopZZ.setText(R.string.shopRZ);
                            tv_shop_RZ.setText(R.string.shopRZ);
                        }else if(myMessageBean.getResult().getStore_status()==1){
                            tv_shopZZ.setText(R.string.dianpuguanli);
                            tv_shop_RZ.setText(R.string.dianpuguanli);
                        }
                        resultBean=myMessageBean.getResult();


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                baseActivity.closeDialog();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDragOut() {
        mUnreadNumView.setVisibility(View.GONE);

    }

    @Override
    public void onCountChanged(int count) {
        if (count == 0) {
            if(messageNum>0){
                mUnreadNumView.setVisibility(View.VISIBLE);
                    mUnreadNumView.setText(String.valueOf(count+messageNum));
            }else{
                mUnreadNumView.setVisibility(View.GONE);
            }
        } else if (count > 0 && count < 100) {
            mUnreadNumView.setVisibility(View.VISIBLE);
            upMessageNumData();
            mUnreadNumView.setText(String.valueOf(count+messageNum));
        } else {
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText(R.string.no_read_message);
        }
    }
    /**
     * 接收未读消息的监听器。
     */
    private RongIM.OnReceiveUnreadCountChangedListener mCountListener = new RongIM.OnReceiveUnreadCountChangedListener() {
        @Override
        public void onMessageIncreased(int count) {
            System.out.println("main---onMessageIncreased---未读消息条数：" + count);
            if (count == 0) {
                if(messageNum>0){
                    mUnreadNumView.setVisibility(View.VISIBLE);
                    mUnreadNumView.setText(String.valueOf(count+messageNum));
                }else{
                    mUnreadNumView.setVisibility(View.GONE);
                }
            } else if (count > 0 && count < 100) {
                mUnreadNumView.setVisibility(View.VISIBLE);
                upGetMessageData();
                mUnreadNumView.setText(String.valueOf(count+messageNum));
            } else {
                mUnreadNumView.setVisibility(View.VISIBLE);
                mUnreadNumView.setText(R.string.no_read_message);
            }
        }
    };
    private int messageNum=0;
    private void upMessageNumData() {
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null)return;
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        XUtil.Post(URLConstant.XITONGXIAO_WEIDU, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====系统信息未读数量===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        String result2 = jsonObject.optString("result");
                        JSONObject jsonObject2 = new JSONObject(result2);
                        String count = jsonObject2.optString("count");
                        messageNum=Integer.parseInt(count);
                        if(messageNum>0){
                            mUnreadNumView.setVisibility(View.VISIBLE);
                            mUnreadNumView.setText(String.valueOf(messageNum));
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }
}
