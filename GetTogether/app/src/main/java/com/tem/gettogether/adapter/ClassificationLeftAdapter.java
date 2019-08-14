package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.CategoriesClassificationBean;

import java.util.List;

public class ClassificationLeftAdapter extends RecyclerView.Adapter<ClassificationLeftAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesClassificationBean.ResultBean> mDatas;
    private OnItemClickListener mOnItemClickListener;
    int choosePos = 0;

    public ClassificationLeftAdapter(Context context, List<CategoriesClassificationBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ClassificationLeftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classification_left, null);
        return new ClassificationLeftAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassificationLeftAdapter.ViewHolder holder, final int position) {
        if (choosePos == position) {
            holder.title_name.setTextColor(context.getResources().getColor(R.color.bottom_text));
            holder.title_name.setBackgroundColor(context.getResources().getColor(R.color.line1));
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.title_name.setTextColor(context.getResources().getColor(R.color.black));
            holder.title_name.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.view.setVisibility(View.GONE);
        }
        holder.title_name.setText(mDatas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePos = position;
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView title_name;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            title_name = itemView.findViewById(R.id.title_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}