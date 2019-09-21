package com.tem.gettogether.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.tem.gettogether.activity.classification.ClassificationActivity;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.bean.CategoriesClassificationBean;

import java.util.List;

public class ClassificationRightAdapter extends RecyclerView.Adapter<ClassificationRightAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesClassificationBean.ResultBean> mDatas;
    private ClassificationRightAdapter.OnItemClickListener mOnItemClickListener;
    int choosePos = 0;

    public ClassificationRightAdapter(Context context, List<CategoriesClassificationBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ClassificationRightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fl_recy_right_content, null);
        return new ClassificationRightAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassificationRightAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(mDatas.get(position).getApp_banner()).error(R.mipmap.myy322x).into(holder.iv_image_banner);
        holder.tv_title.setText(mDatas.get(position).getName());
        holder.recycler_fl_item.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false));
        holder.recycler_fl_item.setAdapter(new BaseRVAdapter(context, mDatas.get(position).getSon()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recy_right_shopping_item;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position2) {
                ImageView iv_image = holder.getImageView(R.id.iv_image);
                Glide.with(context).load(mDatas.get(position).getSon().get(position2).getApp_image()).error(R.mipmap.myy322x).into(iv_image);
                holder.getTextView(R.id.tv_name).setText(mDatas.get(position).getSon().get(position2).getName());
                holder.getView(R.id.ll_shopping_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Log.d("chenshichun","=====position2======"+mDatas.get(position).getSon().get(position2).getId());

                       context.startActivity(new Intent(context, ClassificationActivity.class)
                               .putExtra("classification_id",mDatas.get(position).getSon().get(position2).getId())
                               .putExtra("classification_type",3)
                               .putExtra("is_yilian",true)
                               .putExtra("classification_name",mDatas.get(position).getSon().get(position2).getName()));

                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*choosePos = position;
                notifyDataSetChanged();
                mOnItemClickListener.onItemDelete(position);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image_banner;
        public TextView tv_title;
        public RecyclerView recycler_fl_item;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image_banner = itemView.findViewById(R.id.iv_image_banner);
            tv_title = itemView.findViewById(R.id.tv_title);
            recycler_fl_item = itemView.findViewById(R.id.recycler_fl_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(ClassificationRightAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}