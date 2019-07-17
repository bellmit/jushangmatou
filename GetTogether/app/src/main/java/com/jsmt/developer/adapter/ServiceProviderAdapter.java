package com.jsmt.developer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.bean.ServiceProviderBean;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ViewHolder> {

    private Context context;
    private List<ServiceProviderBean.ResultEntity> mDatas;

    public ServiceProviderAdapter(Context context, List<ServiceProviderBean.ResultEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_service_provider,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title_tv.setText(mDatas.get(position).getCompany_name());
        holder.address_tv.setText(mDatas.get(position).getAddress());
        holder.detail_tv.setText(mDatas.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView pic_iv;
        public TextView  title_tv;
        public TextView address_tv;
        public TextView detail_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            title_tv = itemView.findViewById(R.id.title_tv);
            address_tv = itemView.findViewById(R.id.address_tv);
            detail_tv = itemView.findViewById(R.id.detail_tv);
        }
    }
}
