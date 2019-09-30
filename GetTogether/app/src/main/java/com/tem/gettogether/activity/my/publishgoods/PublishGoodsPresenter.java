package com.tem.gettogether.activity.my.publishgoods;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.CategoriesBean;
import com.tem.gettogether.bean.ReasultBean;
import com.tem.gettogether.retrofit.RetrofitHelper;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.publishgoods
 * @ClassName: PublishGoodsPresenter
 * @Author: csc
 * @CreateDate: 2019/9/6 14:25
 * @Description:
 */
public class PublishGoodsPresenter extends BasePresenter<PublishGoodsContract.PublishGoodsView> implements PublishGoodsContract.Presenter {

    private PublishGoodsModel model;
    private Context mContext;
    private Activity mActivity;

    public PublishGoodsPresenter(Context context, Activity mActivity) {
        model = new PublishGoodsModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }


    /*
     * 获取商品分类数据
     * */
    @Override
    public void getStoreCate(Map<String, Object> map) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.getStoreCate(map).enqueue(new Callback<CategoriesBean>() {
            @Override
            public void onResponse(Call<CategoriesBean> call, Response<CategoriesBean> response) {
                mView.hideLoading();
                mView.getStoreCate(response.body().getResult());
            }

            @Override
            public void onFailure(Call<CategoriesBean> call, Throwable t) {
                mView.hideLoading();
            }
        });
    }

    /*
     * 上传商品
     * */
    @Override
    public void uploadProduct(Map<String, Object> map) {
        Log.d("chenshichun", "=======uploadProduct====" + map);
        XUtil.Post(URLConstant.ADDXINCHANPIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====商品上传===", result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    CusToast.showToast(msg);
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        mActivity.finish();
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
                Log.d("chenshichun", "===========" + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }


    /*
     * 商品分类弹窗
     * */
    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg, recyclerView_qg2;
    private TextView tv_xz_title;
    private int itmeXZ = 0;
    private String majorClassName;// 大类名字
    private String smallClassName;// 小类名字
    private String majorClassId = "";// 大类ID
    private String smallClassId = "";// 小类ID

    @Override
    public void showStoreCatePop(View view, List<CategoriesBean.ResultBean> mCategoriesBeans) {
        showPop(view, mCategoriesBeans);
    }

    //显示弹窗
    private void showPop(View v, List<CategoriesBean.ResultBean> mCategoriesBeans) {
        initPop(mCategoriesBeans);
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.6f;
        mActivity.getWindow().setAttributes(lp);
    }

    private void initPop(List<CategoriesBean.ResultBean> mCategoriesBeans) {
        if (mPop == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.main_addshpping_layout, null);
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
                    WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    mActivity.getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg2 = view.findViewById(R.id.recyclerView_qg2);
            recyclerView_qg2.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            tv_xz_title = view.findViewById(R.id.tv_xz_title);
            initSetdialog(mCategoriesBeans);
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
    }

    private void initSetdialog(final List<CategoriesBean.ResultBean> mCategoriesBeans) {
        BaseRVAdapter baseRVAdapter = new BaseRVAdapter(mContext, mCategoriesBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.add_shpping_layout;
            }

            @Override
            public void onBind(final BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(position).getName());
                if (itmeXZ == position) {
                    holder.getTextView(R.id.tv_item).setTextColor(mContext.getResources().getColor(R.color.my_red));
                } else {
                    holder.getTextView(R.id.tv_item).setTextColor(mContext.getResources().getColor(R.color.text3));
                }
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itmeXZ = position;
                        notifyDataSetChanged();
                        BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(mContext, mCategoriesBeans.get(position).getSon()) {
                            @Override
                            public int getLayoutId(int viewType) {
                                return R.layout.add_shpping_layout;
                            }

                            @Override
                            public void onBind(BaseViewHolder holder2, final int position2) {
                                holder2.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(position).getSon().get(position2).getName());

                                holder2.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mView.getChooseStoreCate(mCategoriesBeans.get(position).getName()
                                                , mCategoriesBeans.get(position).getSon().get(position2).getName()
                                                , mCategoriesBeans.get(position).getId()
                                                , mCategoriesBeans.get(position).getSon().get(position2).getId());
                                        mPop.dismiss();
                                    }
                                });
                            }

                        };
                        baseRVAdapter2.notifyDataSetChanged();
                        recyclerView_qg2.setAdapter(baseRVAdapter2);
                    }
                });
            }

        };

        if (mCategoriesBeans.size() > 0) {
            BaseRVAdapter baseRVAdapter2 = new BaseRVAdapter(mContext, mCategoriesBeans.get(0).getSon()) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.add_shpping_layout;
                }

                @Override
                public void onBind(BaseViewHolder holder2, final int position2) {
                    holder2.getTextView(R.id.tv_item).setText(mCategoriesBeans.get(0).getSon().get(position2).getName());
                    holder2.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mView.getChooseStoreCate(mCategoriesBeans.get(0).getName()
                                    , mCategoriesBeans.get(0).getSon().get(position2).getName()
                                    , mCategoriesBeans.get(0).getId()
                                    , mCategoriesBeans.get(0).getSon().get(position2).getId());
                            mPop.dismiss();
                        }
                    });
                }

            };
            baseRVAdapter2.notifyDataSetChanged();
            recyclerView_qg2.setAdapter(baseRVAdapter2);
        }

        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);
    }

}
