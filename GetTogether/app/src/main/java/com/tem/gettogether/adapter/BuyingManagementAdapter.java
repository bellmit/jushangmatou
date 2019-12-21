package com.tem.gettogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.activity.home.LianMengDetailActivity;
import com.tem.gettogether.adapter.viewHolder.CommonAdapter;
import com.tem.gettogether.adapter.viewHolder.ViewHolder;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.bean.BuyingManagementBean;
import com.tem.gettogether.bean.QiuGouListBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.SizeUtil;

import java.util.ArrayList;
import java.util.List;

public class BuyingManagementAdapter extends CommonAdapter<BuyingManagementBean.ResultBean.BuyInfoBean> {

    private ItemClickListener listener;
    private Context context;

    public BuyingManagementAdapter(Context context, List<BuyingManagementBean.ResultBean.BuyInfoBean> datas, int layoutId) {
        super(context, datas, layoutId);
        this.context = context;
    }

    @Override
    public void convert(final ViewHolder holder, final BuyingManagementBean.ResultBean.BuyInfoBean resultBean) {
        int imageSize = SizeUtil.dp2px(context, 110);
        Glide.with(context).load(resultBean.getGoods_logo().get(0)).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into((ImageView) holder.getView(R.id.pic_iv));
        holder.setText(R.id.product_title, resultBean.getGoods_name());
        holder.setText(R.id.buy_style_tv, resultBean.getRelease_type());
        holder.setText(R.id.buy_time_tv, resultBean.getAttach_time());
        holder.setText(R.id.chukou_tv, context.getString(R.string.export) + resultBean.getCountry_name());
        final ArrayList<String> resultList = new ArrayList<>();
        resultList.add(resultBean.getEnd_pic());
        String language = SharedPreferencesUtils.getLanguageString(context, BaseConstant.SPConstant.language, "");
        if (resultBean.getStatus().equals("2")) {// 图片已上传
            holder.getView(R.id.upload_completed_order_tv).setVisibility(View.GONE);
            holder.getView(R.id.transaction_iv).setVisibility(View.VISIBLE);
            if (language.equals("ara")){
                holder.getView(R.id.transaction_iv).setBackgroundResource(R.drawable.transaction_icon_ara);
            }else if(language.equals("en")){
                holder.getView(R.id.transaction_iv).setBackgroundResource(R.drawable.transaction_icon_en);
            }else{
                holder.getView(R.id.transaction_iv).setBackgroundResource(R.drawable.transaction_icon);
            }
        }
        final EasySwipeMenuLayout easySwipeMenuLayout = holder.getView(R.id.action_bar);
        holder.setOnClickListener(R.id.upload_completed_order_tv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.uploadCompletedOrder(holder.getPosition());
            }
        });

        //删除
        holder.setOnClickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //侧滑初始化
                easySwipeMenuLayout.resetStatus();
                listener.ItemDeleteClickListener(v, holder.getPosition());
            }
        });

        holder.setOnClickListener(R.id.cl_frame, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (resultBean.getStatus().equals("2")) {// 图片已上传
                    Intent intent = new Intent(context, ShowImageDetail.class);
                    intent.putStringArrayListExtra("paths", resultList);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {

        void ItemClickListener(View view, int position);

        void ItemDeleteClickListener(View view, int position);

        void uploadCompletedOrder(int position);
    }

}
