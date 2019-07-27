package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.bean.ServiceProviderBean;
import com.tem.gettogether.utils.SizeUtil;

import java.util.List;

public class ServiceProviderAdapter extends RecyclerView.Adapter<ServiceProviderAdapter.ViewHolder> {

    private Context context;
    private List<ServiceProviderBean.ResultEntity> mDatas;
    private OnItemClickListener mOnItemClickListener;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize= SizeUtil.dp2px(context,100);
        Glide.with(context).load(mDatas.get(position).getCompany_logo()).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);
        holder.title_tv.setText(mDatas.get(position).getCompany_name());
        holder.address_tv.setText(mDatas.get(position).getAddress());
        holder.detail_tv.setText(mDatas.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });
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

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnClickItem(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
