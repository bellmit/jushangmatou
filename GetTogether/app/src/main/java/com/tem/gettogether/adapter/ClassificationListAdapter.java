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
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.bean.ClassificationListBean;
import com.tem.gettogether.view.RoundImageView;

import java.util.List;

public class ClassificationListAdapter extends RecyclerView.Adapter<ClassificationListAdapter.ViewHolder> {

    private Context context;
    private List<ClassificationListBean.ResultBean> mDatas;
    private ClassificationListAdapter.OnItemClickListener mOnItemClickListener;
    int choosePos = 0;

    public ClassificationListAdapter(Context context, List<ClassificationListBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ClassificationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classification_list, null);
        return new ClassificationListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassificationListAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(mDatas.get(position).getcover_image()).error(R.mipmap.myy322x).into(holder.iv_image);
        holder.tv_name.setText(mDatas.get(position).getGoods_name());
        if(mDatas.get(position).getIs_enquiry().equals("1")){
            holder.tv_price.setText("面议");
        }else{
            holder.tv_price.setText("￥" + mDatas.get(position).getShop_price());
        }
        holder.tv_numbear.setText(mDatas.get(position).getBatch_number()+"个起购");
        if(mDatas.get(position).getLevel_id()!=null) {
            if (mDatas.get(position).getLevel_id().equals("7")) {
                holder.tv_member.setVisibility(View.GONE);
            } else if (mDatas.get(position).getLevel_id().equals("1")) {
                holder.tv_member.setVisibility(View.VISIBLE);
                holder.tv_member.setBackgroundResource(R.drawable.putong_icon);
            } else if (mDatas.get(position).getLevel_id().equals("2")) {
                holder.tv_member.setVisibility(View.VISIBLE);
                holder.tv_member.setBackgroundResource(R.drawable.haoji_icon);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShoppingParticularsActivity.class)
                        .putExtra("goods_id",mDatas.get(position).getGoods_id()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RoundImageView iv_image;
        public TextView tv_name;
        public TextView tv_price;
        public TextView tv_numbear;
        public ImageView tv_member;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_numbear = itemView.findViewById(R.id.tv_numbear);
            tv_member = itemView.findViewById(R.id.tv_member);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(ClassificationListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}