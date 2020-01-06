package com.tem.gettogether.activity.my;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.BuyManagerAdapter;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.BuyingManagementBean;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.retrofit.UploadUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.StatusBarUtil;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;

@ContentView(R.layout.activity_buying_managerment)
public class BuyingManagementActivity extends BaseActivity implements BuyManagerAdapter.onItemClickView {
    @ViewInject(R.id.sell_RecyclerView)
    private RecyclerView sell_RecyclerView;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.refreshLayout)
    private TwinklingRefreshLayout refreshLayout;
    @ViewInject(R.id.ll_empty)
    private RelativeLayout ll_empty;
    @ViewInject(R.id.status_bar_id)
    private View status_bar_id;
    private BuyManagerAdapter mBuyManagerAdapter;
    private List<BuyingManagementBean.ResultBean> mBuyingManagementBean = new ArrayList<>();

    private int currentPage = 1;
    private String compressImageFilePath;
    private String trade_id = "";
    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.buying_management));
        StatusBarUtil.setTranslucentStatus(this);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) status_bar_id.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.height = getStatusBarHeight(getContext());
        status_bar_id.setLayoutParams(linearParams);
        setData();
        initDatas(1, false);
        initRefresh();
    }

    @Override
    protected void initView() {
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }

    private void initDatas(final int currentPage, final boolean isLoadMore) {
        Map<String, Object> map = new HashMap<>();
        map.put("page", currentPage);
        map.put("user_id", SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.USERID, ""));
        map.put("list_row", 10);
        String yuyan = SharedPreferencesUtils.getLanguageString(getContext(), BaseConstant.SPConstant.language, "");
        map.put("language", yuyan);
        showDialog();
        XUtil.Post(URLConstant.BUY_MANAGER, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.d("chenshichun", "======求购管理=====" + result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");

                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        if (!isLoadMore) {
                            if (jsonObject.optString("result") == null || jsonObject.optString("result").equals("")) {
                                mBuyingManagementBean.removeAll(mBuyingManagementBean);
                                mBuyManagerAdapter.notifyDataSetChanged();
                                ll_empty.setVisibility(View.VISIBLE);
                            } else {
                                mBuyingManagementBean.removeAll(mBuyingManagementBean);
                                mBuyingManagementBean.addAll(gson.fromJson(result, BuyingManagementBean.class).getResult());
                                mBuyManagerAdapter.notifyDataSetChanged();
                                ll_empty.setVisibility(View.GONE);
                            }

                        } else {
                            if (gson.fromJson(result, BuyingManagementBean.class).getResult().size() > 0) {
                                mBuyingManagementBean.addAll(gson.fromJson(result, BuyingManagementBean.class).getResult());
                                mBuyManagerAdapter.notifyDataSetChanged();
                            } else {
                                CusToast.showToast(getResources().getText(R.string.no_more_data));
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }


    private void deleteData(String trade_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("trade_id", trade_id);
        showDialog();
        XUtil.Post(URLConstant.BUYING_MANAGEMENT_DELETE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        initDatas(1, false);
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

    private void uploadImg(String trade_id, String image) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", trade_id);
        map.put("image_show", image);
        showDialog();
        XUtil.Post(URLConstant.BUYER_TRADE_DODE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("chenshichun", "--uploadImg-result--" + result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        initDatas(1, false);
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

    private void setData() {
        mBuyManagerAdapter = new BuyManagerAdapter(getContext(), mBuyingManagementBean);
        sell_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sell_RecyclerView.setAdapter(mBuyManagerAdapter);
        mBuyManagerAdapter.setOnClickView(this);
    }

    private void initRefresh() {
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setPullDownStr(getString(R.string.pull_down_refresh));
        headerView.setReleaseRefreshStr(getString(R.string.release_refresh));
        headerView.setRefreshingStr(getString(R.string.refreshing));
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getContext());
        refreshLayout.setBottomView(loadingView);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                currentPage = 1;
                initDatas(currentPage, false);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                currentPage++;
                initDatas(currentPage, true);
            }

            @Override
            public void onFinishRefresh() {
                super.onFinishRefresh();
            }

            @Override
            public void onFinishLoadMore() {
                super.onFinishLoadMore();
            }
        });
    }
    ImageDataBean imageDataBean = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                final String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);

                final String targetPath = compressImageFilePath + Confirg.df.
                        format(new Date()) + ".jpg";
                final String compressImage = BitnapUtils.compressImage(picturePath, targetPath, 40);
                showDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            imageDataBean = UploadUtil.uploadFile(BitnapUtils.readStream(targetPath), new File(targetPath), URLConstant.UPLOAD_BINARY_END_PIC);
                            if (imageDataBean != null) {
                                Log.e("chenshichun", "-----" + imageDataBean.getResult().getImage_show());
                                mHandle.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            mHandle.sendEmptyMessage(1);
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    closeDialog();
                    uploadImg(trade_id,imageDataBean.getResult().getImage_show().get(0));
                    break;
                case 1:
                    closeDialog();
                    break;
            }
        }
    };

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onDelete(int position, int position1) {
        deleteData(mBuyingManagementBean.get(position).getBuy_info().get(position1).getTrade_id());
    }

    @Override
    public void uploadCompletedOrder(int position, int position1) {
        trade_id = mBuyingManagementBean.get(position).getBuy_info().get(position1).getTrade_id();
        PictureSelector
                .create(BuyingManagementActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 500, 1, 1);
    }
}
