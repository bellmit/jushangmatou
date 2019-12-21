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
import com.tem.gettogether.activity.home.HomeHotSellActivity;
import com.tem.gettogether.activity.home.HomeLianMengActivity;
import com.tem.gettogether.activity.home.TogetherFactoryActivity;
import com.tem.gettogether.activity.linyi.LinYiClassificationActivity;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.utils.SizeUtil;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/14 14:10 
 */
public class TopNavAdapter extends RecyclerView.Adapter<TopNavAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Top_navEntity> mDatas;

    public TopNavAdapter(Context context, List<HomeDataNewBean.ResultEntity.Top_navEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public TopNavAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_nav, null);
        return new TopNavAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopNavAdapter.ViewHolder holder, final int position) {
        int imageSize = SizeUtil.dp2px(context, 50);

        Glide.with(context).load(mDatas.get(position).getNav_logo()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);
        holder.company_name.setText(mDatas.get(position).getNav_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:// 外贸热销
                        context.startActivity(new Intent(context, HomeHotSellActivity.class));
                        break;
                    case 1:// 临沂
                        context.startActivity(new Intent(context, LinYiClassificationActivity.class));
                        break;
                    case 2:// 外贸求购
                        context.startActivity(new Intent(context, HomeLianMengActivity.class));
                        break;
                    case 3:
                        context.startActivity(new Intent(context, TogetherFactoryActivity.class));
                        break;
                }
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
