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
import com.jsmt.developer.bean.HomeLianMengBean;
import com.jsmt.developer.utils.TransformationUtils;

import java.util.List;

public class HomeLianMengSecondAdapter extends RecyclerView.Adapter<HomeLianMengSecondAdapter.ViewHolder> {

    private Context context;
    private List<HomeLianMengBean.ResultEntity> mDatas;

    public HomeLianMengSecondAdapter(Context context, List<HomeLianMengBean.ResultEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_lianmeng_second, null);
        return new HomeLianMengSecondAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(mDatas.get(position).getCompany_images()).asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(new TransformationUtils(holder.pic_iv));
        holder.product_title.setText(mDatas.get(position).getCompany_name());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic_iv;
        public TextView product_title;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            product_title = itemView.findViewById(R.id.product_title);
        }
    }
}
