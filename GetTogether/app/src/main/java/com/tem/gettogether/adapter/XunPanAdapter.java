package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.bean.QiuGouListBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.SizeUtil;

import java.util.List;

import me.nereo.multi_image_selector.bean.Image;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/25 09:54 
 */
public class XunPanAdapter extends RecyclerView.Adapter<XunPanAdapter.ViewHolder> {

    private Context context;
    private List<QiuGouListBean.ResultBean> mDatas;
    private XunPanAdapter.onClickView onClickView;

    public XunPanAdapter(Context context, List<QiuGouListBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public XunPanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xunpan, null);
        return new XunPanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XunPanAdapter.ViewHolder holder, final int position) {
        int imageSize = SizeUtil.dp2px(context, 110);
        Glide.with(context).load(mDatas.get(position).getGoods_logo().get(0)).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.pic_iv);
        holder.product_title.setText(mDatas.get(position).getGoods_name());
        holder.buy_style_tv.setText(mDatas.get(position).getRelease_type());
        holder.buy_time_tv.setText(mDatas.get(position).getAttach_time());
        holder.chukou_tv.setText(mDatas.get(position).getCountry_name());
        String language = SharedPreferencesUtils.getLanguageString(context, BaseConstant.SPConstant.language, "");

        if (mDatas.get(position).getStatus() != null && mDatas.get(position).getStatus().equals("2")) {
            holder.transaction_iv.setVisibility(View.VISIBLE);
            if (language.equals("ara")){
                holder.transaction_iv.setBackgroundResource(R.drawable.transaction_icon_ara);
            }else if(language.equals("en")){
                holder.transaction_iv.setBackgroundResource(R.drawable.transaction_icon_en);
            }else{
                holder.transaction_iv.setBackgroundResource(R.drawable.transaction_icon);
            }
        } else {
            holder.transaction_iv.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.onClick(position);
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
        public TextView buy_time_tv;
        public TextView buy_style_tv;
        public TextView chukou_tv;
        public ImageView transaction_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            pic_iv = itemView.findViewById(R.id.pic_iv);
            product_title = itemView.findViewById(R.id.product_title);
            buy_time_tv = itemView.findViewById(R.id.buy_time_tv);
            buy_style_tv = itemView.findViewById(R.id.buy_style_tv);
            chukou_tv = itemView.findViewById(R.id.chukou_tv);
            transaction_iv = itemView.findViewById(R.id.transaction_iv);
        }
    }

    public interface onClickView {
        public void onClick(int position);
    }

    public void setOnClickView(XunPanAdapter.onClickView onClickView) {
        this.onClickView = onClickView;
    }
}