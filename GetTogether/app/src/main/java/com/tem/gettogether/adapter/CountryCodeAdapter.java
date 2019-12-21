package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.CountryCodeBean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/29 16:57 
 */
public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.ViewHolder> {

    private Context context;
    private List<CountryCodeBean.ResultBean.CountryBean> mDatas;
    private OnItemClickListener mOnItemClickListener;

    public CountryCodeAdapter(Context context, List<CountryCodeBean.ResultBean.CountryBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public CountryCodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country_code, null);
        return new CountryCodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryCodeAdapter.ViewHolder holder, final int position) {
        holder.name.setText(mDatas.get(position).getCountry_name());
        holder.code.setText(mDatas.get(position).getMobile_code());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView code;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            code = itemView.findViewById(R.id.code);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
