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
import com.tem.gettogether.activity.classification.ClassificationActivity;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.bean.MenuItem;
import com.tem.gettogether.view.CircularImage;

import java.util.ArrayList;
import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {

    private Context context;
    List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> menuItems;

    public MainMenuAdapter(Context context, List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> menuItems) {

        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new MainMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(menuItems.get(position).getApp_image()).error(R.mipmap.myy322x).into(holder.pic);
        holder.product_title.setText(menuItems.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ClassificationActivity.class)
                        .putExtra("classification_id", menuItems.get(position).getCategory_id())
                        .putExtra("classification_type", 2)
                        .putExtra("is_yilian",false)
                        .putExtra("classification_name", menuItems.get(position).getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircularImage pic;
        public TextView product_title;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
            product_title = itemView.findViewById(R.id.text);
        }
    }
}
