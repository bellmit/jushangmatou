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
import com.tem.gettogether.activity.home.LianMengDetailActivity;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.bean.HomeLianMengBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.TransformationUtils;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(mDatas.get(position).getCompany_logo()).asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(new TransformationUtils(holder.pic_iv));
        holder.product_title.setText(mDatas.get(position).getCompany_name());
        holder.detail_tv.setText(context.getResources().getString(R.string.business)+mDatas.get(position).getMaindeal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, LianMengDetailActivity.class).putExtra(Contacts.LINGMENG_COMPANY_ID,mDatas.get(position).getCompanyid()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic_iv;
        public TextView product_title;
        public TextView detail_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            product_title = itemView.findViewById(R.id.product_title);
            detail_tv = itemView.findViewById(R.id.detail_tv);
        }
    }
}
