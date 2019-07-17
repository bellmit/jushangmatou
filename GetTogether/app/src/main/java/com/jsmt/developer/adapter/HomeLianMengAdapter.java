package com.jsmt.developer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jsmt.developer.R;
import com.jsmt.developer.bean.HomeDataNewBean;
import com.jsmt.developer.utils.TransformationUtils;

import java.util.List;

public class HomeLianMengAdapter extends RecyclerView.Adapter<HomeLianMengAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Trade_unionEntity> mDatas;

    public HomeLianMengAdapter(Context context, List<HomeDataNewBean.ResultEntity.Trade_unionEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_lianmeng, null);
        return new HomeLianMengAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(mDatas.get(position).getCompany_images()).asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(new TransformationUtils(holder.pic_iv));
        holder.company_name.setText(mDatas.get(position).getCompany_name());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic_iv;
        public TextView company_name;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            company_name = itemView.findViewById(R.id.name_tv);
        }
    }
}
