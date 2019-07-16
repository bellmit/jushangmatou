package com.tem.gettogether.utils.language;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tem.gettogether.R;

import java.util.List;
import java.util.Locale;

public class LanguageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<LanguageBean> mData;
    private Locale mLocale;
    private OnItemClickListener mListener;

    public LanguageAdapter(Context context, List<LanguageBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setSelect(Locale locale) {
        this.mLocale = locale;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_language, viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder holder = (MyHolder) viewHolder;
        holder.tv_name.setText(mData.get(i).getName());
        if (mLocale.getLanguage().equals(mData.get(i).getLanguage().getLanguage())) {
            holder.iv_check.setVisibility(View.VISIBLE);
        } else {
            holder.iv_check.setVisibility(View.INVISIBLE);
        }
        if(mData.get(i).getName().equals("中文")){
            holder.iv_yuyan.setImageResource(R.drawable.zhongwen);

        } if(mData.get(i).getName().equals("Chinese")){
            holder.iv_yuyan.setImageResource(R.drawable.zhongwen);

        }else if(mData.get(i).getName().equals("English")){
            holder.iv_yuyan.setImageResource(R.drawable.yingwen);

        }else if(mData.get(i).getName().equals("英文")){
            holder.iv_yuyan.setImageResource(R.drawable.yingwen);

        }
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        ImageView iv_check;
        RelativeLayout rl_item;
        ImageView iv_yuyan;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_item_language_name);
            iv_check = itemView.findViewById(R.id.iv_item_language_check);
            rl_item = itemView.findViewById(R.id.rl_item);
            iv_yuyan=itemView.findViewById(R.id.iv_yuyan);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int postion);
    }
}
