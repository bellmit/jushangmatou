package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.bean.VisitorBean;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.view.CircularImage;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.adapter
 * @ClassName: VisitorDetailsAdapter
 * @Author: csc
 * @CreateDate: 2019/9/16 9:21
 * @Description:
 */
public class VisitorDetailsAdapter extends RecyclerView.Adapter<VisitorDetailsAdapter.ViewHolder> {

    private Context context;
    private List<VisitorBean.ResultBean.VsBean.VisitersBean> mDatas;

    public VisitorDetailsAdapter(Context context, List<VisitorBean.ResultBean.VsBean.VisitersBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public VisitorDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visitor_detail, parent, false);
        return new VisitorDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VisitorDetailsAdapter.ViewHolder holder, final int position) {
        int imageSize = SizeUtil.dp2px(context, 60);
        Glide.with(context).load(mDatas.get(position).getHead_pic()).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into(holder.head_pic);
        holder.tv_name.setText(mDatas.get(position).getNickname());
        holder.detail_tv.setText(context.getText(R.string.user_tv)+mDatas.get(position).getNickname()+context.getText(R.string.visited_your_store));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircularImage head_pic;
        public TextView tv_name;
        public TextView detail_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            head_pic = itemView.findViewById(R.id.head_pic);
            tv_name = itemView.findViewById(R.id.tv_name);
            detail_tv = itemView.findViewById(R.id.detail_tv);
        }
    }
}
