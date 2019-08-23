package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.bean.OrderDetailBean;
import com.tem.gettogether.bean.ShoppingXQBean;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.utils.TransformationUtils;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private Context context;
    private List<ShoppingXQBean.ResultBean.OrderBean> mDatas;

    public OrderDetailAdapter(Context context, List<ShoppingXQBean.ResultBean.OrderBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_detail, null);
        return new OrderDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name_tv.setText(mDatas.get(position).getUser_name()==null?"null":mDatas.get(position).getUser_name());
        holder.address_tv.setText(mDatas.get(position).getCountry_name()==null?"null":mDatas.get(position).getCountry_name());
        holder.color_tv.setText(mDatas.get(position).getKey_name()==null?"null":mDatas.get(position).getKey_name());
        holder.price_tv.setText(mDatas.get(position).getOrder_amount()==null?"null":mDatas.get(position).getOrder_amount());
        holder.num_tv.setText(mDatas.get(position).getGoods_num()==null?"null":mDatas.get(position).getGoods_num());
        holder.time_tv.setText(mDatas.get(position).getAdd_time()==null?"null":mDatas.get(position).getAdd_time());
        if(position%2==1) {
            holder.itemView.setBackgroundColor(0xffd1d1d1);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_tv;
        public TextView address_tv;
        public TextView color_tv;
        public TextView price_tv;
        public TextView num_tv;
        public TextView time_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            color_tv = itemView.findViewById(R.id.color_tv);
            price_tv = itemView.findViewById(R.id.price_tv);
            num_tv = itemView.findViewById(R.id.num_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }
    }
}
