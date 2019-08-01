package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.HomeDataNewBean;

import java.util.List;

public class ProductManagmentAdapter extends RecyclerView.Adapter<ProductManagmentAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas;

    public ProductManagmentAdapter(Context context, List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_managment, null);
        return new ProductManagmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 10/*mDatas.size()*/;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
