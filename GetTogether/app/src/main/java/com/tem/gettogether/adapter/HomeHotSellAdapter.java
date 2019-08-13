package com.tem.gettogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class HomeHotSellAdapter extends RecyclerView.Adapter<HomeHotSellAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Order_pastedEntity> mDatas;

    public HomeHotSellAdapter(Context context, List<HomeDataNewBean.ResultEntity.Order_pastedEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_hot_sell, parent,false);
        return new HomeHotSellAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize= SizeUtil.dp2px(context,110);
        Glide.with(context).load(mDatas.get(position).getOriginal_img()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);

        holder.product_title.setText(mDatas.get(position).getGoods_name());
        holder.buy_price_tv.setText(mDatas.get(position).getMember_goods_price());
        holder.tv_sell_count.setText(mDatas.get(position).getGoods_num());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BaseApplication.getInstance().userBean==null){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else {
                    context.startActivity(new Intent(context, ShoppingParticularsActivity.class)
                            .putExtra("goods_id", mDatas.get(position).getGoods_id()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic_iv;
        public TextView product_title;
        public TextView buy_price_tv;
        public TextView tv_sell_count;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            product_title = itemView.findViewById(R.id.product_title);
            buy_price_tv = itemView.findViewById(R.id.buy_price_tv);
            tv_sell_count = itemView.findViewById(R.id.tv_sell_count);
        }
    }
}
