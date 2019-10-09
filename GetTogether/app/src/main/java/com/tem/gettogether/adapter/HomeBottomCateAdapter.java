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
import com.tem.gettogether.activity.classification.ClassificationActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.bean.HomeDataBean;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.BKRecommecdActivity;
import com.tem.gettogether.bean.HomeDataBean;
import com.tem.gettogether.bean.HomeDataNewBean;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.TransformationUtils;

import java.util.List;

public class HomeBottomCateAdapter extends RecyclerView.Adapter<HomeBottomCateAdapter.ViewHolder>{

    private Context context;
    List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> mDatas;

    public HomeBottomCateAdapter(Context context, List<HomeDataNewBean.ResultEntity.Bottom_cateEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_layout_item,null);
        return new HomeBottomCateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mDatas.get(position).getMobile_name()!=null&&!mDatas.get(position).getMobile_name().equals("")){
            holder.tv_name.setText(mDatas.get(position).getMobile_name()+"");
        }else{
            holder.tv_name.setText(context.getText(R.string.no_data));
        }
        if(mDatas.get(position).getMobile_des()!=null&&!mDatas.get(position).getMobile_des().equals("")){
            holder.tv_name_dec.setText(mDatas.get(position).getMobile_des()+"");

        }else{
            holder.tv_name_dec.setText(context.getText(R.string.no_data));
        }
        if(position==2||position==5){
            holder.view_line_right.setVisibility(View.GONE);
        }
        if(position==0||position==1||position==2){
            holder.view_line_bo.setVisibility(View.VISIBLE);
        }
        Glide.with(context).load(mDatas.get(position).getApp_image()) .asBitmap().placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(new TransformationUtils(holder.iv_left_image));
        Glide.with(context).load(mDatas.get(position).getApp_image()).placeholder(R.mipmap.myy322x).error(R.mipmap.myy322x).into(holder.iv_right_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SharedPreferencesUtils.getString(context, BaseConstant.SPConstant.language, "").equals("en")) {
                    context.startActivity(new Intent(context, ClassificationActivity.class)
                            .putExtra("classification_id",mDatas.get(position).getCategory_id())
                            .putExtra("classification_type",2)
                            .putExtra("is_yilian",false)
                            .putExtra("classification_name",mDatas.get(position).getEn_mobile_name()));
                }else if(SharedPreferencesUtils.getString(context, BaseConstant.SPConstant.language, "").equals("ara")){
                    context.startActivity(new Intent(context, ClassificationActivity.class)
                            .putExtra("classification_id",mDatas.get(position).getCategory_id())
                            .putExtra("classification_type",2)
                            .putExtra("is_yilian",false)
                            .putExtra("classification_name",mDatas.get(position).getAra_mobile_name()));
                }else{
                    context.startActivity(new Intent(context, ClassificationActivity.class)
                            .putExtra("classification_id",mDatas.get(position).getCategory_id())
                            .putExtra("classification_type",2)
                            .putExtra("is_yilian",false)
                            .putExtra("classification_name",mDatas.get(position).getName()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView iv_left_image;
        public ImageView iv_right_image;
        public TextView tv_name_dec;
        public View view_line_bo;
        public View view_line_right;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_left_image = itemView.findViewById(R.id.iv_left_image);
            tv_name_dec = itemView.findViewById(R.id.tv_name_dec);
            view_line_bo = itemView.findViewById(R.id.view_line_bo);
            view_line_right = itemView.findViewById(R.id.view_line_right);
            iv_right_image = itemView.findViewById(R.id.iv_right_image);
        }
    }
}
