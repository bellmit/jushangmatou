package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.bean.SpecificationsBean;
import com.tem.gettogether.bean.VisitorBean;
import com.tem.gettogether.rongyun.RongTalk;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.SizeUtil;
import com.tem.gettogether.view.CircularImage;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.adapter
 * @ClassName: VisitorAdapter
 * @Author: csc
 * @CreateDate: 2019/9/15 13:53
 * @Description:
 */
public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> {

    private Context context;
    private List<VisitorBean.ResultBean.VsBean> mDatas;
    private VisitorDetailsAdapter mVisitorDetailsAdapter;
    List<VisitorBean.ResultBean.VsBean.VisitersBean> mDatasssss = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public VisitorAdapter(Context context, List<VisitorBean.ResultBean.VsBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public VisitorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visitor, parent, false);
        return new VisitorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VisitorAdapter.ViewHolder holder, final int position) {
        holder.time_tv.setText(mDatas.get(position).getTime());
        holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.mRecyclerView.setAdapter(new BaseRVAdapter(context, mDatas.get(position).getVisiters()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_visitor_detail;
            }

            @Override
            public void onBind(com.tem.gettogether.base.BaseViewHolder holder, final int position2) {
                CircularImage head_pic = holder.getView(R.id.head_pic);
                int imageSize = SizeUtil.dp2px(context, 60);
                Glide.with(context).load(mDatas.get(position).getVisiters().get(position2).getHead_pic()).asBitmap().placeholder(R.mipmap.myy322x)
                        .error(R.mipmap.myy322x).override(imageSize, imageSize).into(head_pic);
                holder.getTextView(R.id.tv_name).setText(mDatas.get(position).getVisiters().get(position2).getNickname());
                holder.getTextView(R.id.detail_tv).setText(context.getText(R.string.user_tv) +
                        mDatas.get(position).getVisiters().get(position2).getNickname() + mContext.getString(R.string.followed_your_shop));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       mOnItemClickListener.onItemClick(position,position2);
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
        public TextView time_tv;
        public RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            time_tv = itemView.findViewById(R.id.time_tv);
            mRecyclerView = itemView.findViewById(R.id.mRecyclerView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position,int position2);
    }

    public void setOnClickItem(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}
