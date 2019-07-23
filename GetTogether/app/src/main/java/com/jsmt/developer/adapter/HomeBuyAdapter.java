package com.jsmt.developer.adapter;

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
import com.jsmt.developer.R;
import com.jsmt.developer.activity.home.HomeBuyDetailActivity;
import com.jsmt.developer.activity.home.ShoppingParticularsActivity;
import com.jsmt.developer.bean.HomeDataNewBean;
import com.jsmt.developer.utils.SizeUtil;
import com.jsmt.developer.utils.TransformationUtils;

import java.util.List;

public class HomeBuyAdapter extends RecyclerView.Adapter<HomeBuyAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas;

    public HomeBuyAdapter(Context context, List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_buying, null);
        return new HomeBuyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize= SizeUtil.dp2px(context,110);
        Glide.with(context).load(mDatas.get(position).getGoods_logo()).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);
        holder.product_title.setText(mDatas.get(position).getGoods_name());
        holder.buy_style_tv.setText(mDatas.get(position).getRelease_type());
        holder.buy_time_tv.setText(mDatas.get(position).getAttach_time());
        holder.chukou_tv.setText(mDatas.get(position).getCountry_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, HomeBuyDetailActivity.class)
                        .putExtra("trade_id", mDatas.get(position).getTrade_id()));
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
        public TextView buy_time_tv;
        public TextView buy_style_tv;
        public TextView chukou_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            product_title = itemView.findViewById(R.id.product_title);
            buy_time_tv = itemView.findViewById(R.id.buy_time_tv);
            buy_style_tv = itemView.findViewById(R.id.buy_style_tv);
            chukou_tv = itemView.findViewById(R.id.chukou_tv);
        }
    }
}
