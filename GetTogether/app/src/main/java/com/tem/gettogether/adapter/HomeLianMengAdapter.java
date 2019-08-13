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
import com.tem.gettogether.activity.home.LianMengDetailActivity;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.utils.TransformationUtils;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int imageSize= SizeUtil.dp2px(context,100);

        Log.d("chenshichun","======getCompany_logo====="+mDatas.get(position).getCompany_logo());
        Glide.with(context).load(mDatas.get(position).getCompany_logo()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);
        holder.company_name.setText(mDatas.get(position).getCompany_name());
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
        public TextView company_name;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            company_name = itemView.findViewById(R.id.name_tv);
        }
    }
}
