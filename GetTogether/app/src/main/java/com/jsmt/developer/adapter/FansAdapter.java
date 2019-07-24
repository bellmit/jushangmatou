package com.jsmt.developer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsmt.developer.R;
import com.jsmt.developer.bean.HomeDataNewBean;

import java.util.List;

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.ViewHolder> {

    private Context context;
    private List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas;

    public FansAdapter(Context context, List<HomeDataNewBean.ResultEntity.Ftrade_buyEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fans, parent,false);
        return new FansAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /*int imageSize= SizeUtil.dp2px(context,110);
        Glide.with(context).load(mDatas.get(position).getGoods_logo()).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);*/
    }

    @Override
    public int getItemCount() {
        return 15/*mDatas.size()*/;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
