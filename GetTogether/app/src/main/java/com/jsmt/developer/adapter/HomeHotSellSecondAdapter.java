package com.jsmt.developer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmt.developer.R;
import com.jsmt.developer.bean.HomeDataNewBean;
import com.jsmt.developer.bean.HomeHotSellBean;
import com.jsmt.developer.utils.SizeUtil;
import com.jsmt.developer.utils.TransformationUtils;

import java.util.List;

public class HomeHotSellSecondAdapter extends RecyclerView.Adapter<HomeHotSellSecondAdapter.ViewHolder> {

    private Context context;
    private List<HomeHotSellBean.ResultEntity> mDatas;

    public HomeHotSellSecondAdapter(Context context, List<HomeHotSellBean.ResultEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_hot_sell, null, false);
        return new HomeHotSellSecondAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imageSize = SizeUtil.dp2px(context, 110);
        Glide.with(context).load(mDatas.get(position).getOriginal_img()).asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x)
                .override(imageSize, imageSize).into(holder.pic_iv);
        holder.product_title.setText(mDatas.get(position).getGoods_name());
        holder.buy_price_tv.setText(mDatas.get(position).getMember_goods_price());
        holder.tv_sell_count.setText(mDatas.get(position).getGoods_num());
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