package com.tem.gettogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.LoginActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.utils.TransformationUtils;

import java.util.List;

public class HomeXinPinAdapter extends RecyclerView.Adapter<HomeXinPinAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Ftrade_newEntity> mDatas;

    public HomeXinPinAdapter(Context context, List<HomeDataNewBean.ResultEntity.Ftrade_newEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_xinpin, null);
        return new HomeXinPinAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize = SizeUtil.dp2px(context, 180);
        Glide.with(context).load(mDatas.get(position).getcover_image()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.iv_image);
        holder.title_tv.setText(mDatas.get(position).getGoods_name());
        if (mDatas.get(position).getIs_enquiry().equals("1")) {
            holder.price_tv.setText(context.getText(R.string.negotiable_tv));
        } else {
            holder.price_tv.setText("￥"+mDatas.get(position).getShop_price());
        }
        holder.count_tv.setText(mDatas.get(position).getBatch_number());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    context.startActivity(new Intent(context, ShoppingParticularsActivity.class)
                            .putExtra("goods_id", mDatas.get(position).getGoods_id()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;
        public TextView title_tv;
        public TextView price_tv;
        public TextView count_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            title_tv = itemView.findViewById(R.id.title_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            count_tv = itemView.findViewById(R.id.count_tv);
        }
    }
}
