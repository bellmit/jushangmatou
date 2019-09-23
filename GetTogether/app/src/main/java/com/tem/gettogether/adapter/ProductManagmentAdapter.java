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
import com.tem.gettogether.bean.ProductManagementBean;
import com.tem.gettogether.utils.SizeUtil;

import java.util.List;

public class ProductManagmentAdapter extends RecyclerView.Adapter<ProductManagmentAdapter.ViewHolder> {

    private Context context;
    private List<ProductManagementBean.ResultBean> mDatas;
    private int pageType;
    private onClickView onClickView;

    public ProductManagmentAdapter(Context context, List<ProductManagementBean.ResultBean> mDatas, int pageType) {
        this.context = context;
        this.mDatas = mDatas;
        this.pageType = pageType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_managment, null);
        return new ProductManagmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize = SizeUtil.dp2px(context, 110);
        Glide.with(context).load(mDatas.get(position).getcover_image()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.iv_image);
        holder.tv_title.setText(mDatas.get(position).getGoods_name());
        if(mDatas.get(position).getIs_enquiry().equals("1")){
            holder.tv_Shop_price.setText(context.getText(R.string.negotiable_tv));
        }else{
            holder.tv_Shop_price.setText("￥"+mDatas.get(position).getShop_price());
        }
        holder.tv_shuoming.setText(mDatas.get(position).getBatch_number() + "件");
        if (pageType == 0) {
            holder.shelf_tv.setVisibility(View.GONE);
            holder.obtained_tv.setVisibility(View.VISIBLE);
            holder.edit_tv.setVisibility(View.VISIBLE);
            holder.delete_tv.setVisibility(View.GONE);
        } else if (pageType == 1) {
            holder.shelf_tv.setVisibility(View.VISIBLE);
            holder.obtained_tv.setVisibility(View.GONE);
            holder.edit_tv.setVisibility(View.VISIBLE);
            holder.delete_tv.setVisibility(View.VISIBLE);
        } else {
            holder.shelf_tv.setVisibility(View.GONE);
            holder.obtained_tv.setVisibility(View.GONE);
            holder.edit_tv.setVisibility(View.VISIBLE);
            holder.delete_tv.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ShoppingParticularsActivity.class)
                        .putExtra("goods_id", mDatas.get(position).getGoods_id()));
            }
        });
        holder.shelf_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.setShelfClick(mDatas.get(position).getGoods_id());
            }
        });
        holder.obtained_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.setObtainedClick(mDatas.get(position).getGoods_id());
            }
        });
        holder.edit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.setEditClick(mDatas.get(position).getGoods_id());
            }
        });
        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.setDeleteClick(mDatas.get(position).getGoods_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;
        public TextView tv_title;
        public TextView tv_Shop_price;
        public TextView tv_shuoming;
        public TextView shelf_tv;
        public TextView obtained_tv;
        public TextView edit_tv;
        public TextView delete_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_Shop_price = itemView.findViewById(R.id.tv_Shop_price);
            tv_shuoming = itemView.findViewById(R.id.tv_shuoming);
            shelf_tv = itemView.findViewById(R.id.shelf_tv);
            obtained_tv = itemView.findViewById(R.id.obtained_tv);
            edit_tv = itemView.findViewById(R.id.edit_tv);
            delete_tv = itemView.findViewById(R.id.delete_tv);
        }
    }

    public interface onClickView {
        public void setShelfClick(String id);
        public void setObtainedClick(String id);
        public void setEditClick(String id);
        public void setDeleteClick(String id);
    }

    public void setOnClickView(onClickView onClickView) {
        this.onClickView = onClickView;
    }
}
