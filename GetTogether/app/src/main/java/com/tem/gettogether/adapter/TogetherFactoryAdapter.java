package com.tem.gettogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.FactoryWebActivity;
import com.tem.gettogether.activity.my.OrderXQActivity;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.bean.TogetherFactoryBean;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.view.RoundImageView;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/18 17:15 
 */
public class TogetherFactoryAdapter extends RecyclerView.Adapter<TogetherFactoryAdapter.ViewHolder> {

    private Context context;
    private List<TogetherFactoryBean.ResultBean> mDatas;

    public TogetherFactoryAdapter(Context context, List<TogetherFactoryBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public TogetherFactoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_together_factory, null);
        return new TogetherFactoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TogetherFactoryAdapter.ViewHolder holder, final int position) {
        holder.title_tv.setText(mDatas.get(position).getFactory_name());
        holder.business_tv.setText(mDatas.get(position).getMaindeal());
        holder.item_recyclerView.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false));
        if (mDatas.get(position).getDistance() != null && !mDatas.get(position).getDistance().equals("null")) {
            holder.position_tv.setText(context.getString(R.string.away_from_me) + " " + mDatas.get(position).getDistance() + " " + context.getString(R.string.km));
        } else {
            holder.position_tv.setText(R.string.km_0);
        }
        holder.call_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri datav = Uri.parse(context.getText(R.string.tel_tv) + mDatas.get(position).getCellphone());
                intent.setData(datav);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FactoryWebActivity.class)
                        .putExtra("website", mDatas.get(position).getWebsite())
                        .putExtra("factory_name", mDatas.get(position).getFactory_name()));
            }
        });
        holder.item_recyclerView.setAdapter(new BaseRVAdapter(context, mDatas.get(position).getFm()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_factory_img;
            }

            @Override
            public void onBind(com.tem.gettogether.base.BaseViewHolder holder, final int position2) {
                ImageView iv_image_shopping = holder.getView(R.id.pic_iv);
                Glide.with(context).load(mDatas.get(position).getFm().get(position2).getFimages()).error(R.mipmap.myy322x).into(iv_image_shopping);
                holder.getTextView(R.id.name_tv).setText(mDatas.get(position).getFm().get(position2).getFimages_title());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, FactoryWebActivity.class)
                                .putExtra("website", mDatas.get(position).getWebsite())
                                .putExtra("factory_name", mDatas.get(position).getFactory_name()));
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title_tv;
        public TextView call_tv;
        public TextView position_tv;
        public TextView business_tv;
        public RecyclerView item_recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.title_tv);
            call_tv = itemView.findViewById(R.id.call_tv);
            position_tv = itemView.findViewById(R.id.position_tv);
            business_tv = itemView.findViewById(R.id.business_tv);
            item_recyclerView = itemView.findViewById(R.id.item_recyclerView);
        }
    }
}
