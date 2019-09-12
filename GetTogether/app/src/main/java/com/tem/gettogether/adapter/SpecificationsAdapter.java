package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.ServiceProviderBean;
import com.tem.gettogether.bean.SpecificationsBean;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.adapter
 * @ClassName: SpecificationsAdapter
 * @Author: csc
 * @CreateDate: 2019/9/9 13:50
 * @Description:
 */
public class SpecificationsAdapter extends RecyclerView.Adapter<SpecificationsAdapter.ViewHolder> {

    private Context context;
    private SpecificationsAdapter.OnItemClickListener mOnItemClickListener;
    private SparseBooleanArray selectLists = new SparseBooleanArray();
    private int selectCount = 0;
    List<SpecificationsBean.ResultBean.SpecListBean> mDatas;

    public SpecificationsAdapter(Context context, List<SpecificationsBean.ResultBean.SpecListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public SpecificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specifications, null);
        return new SpecificationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecificationsAdapter.ViewHolder holder, final int position) {
        if (!selectLists.get(position)) {
            holder.text_tv.setBackgroundResource(R.drawable.specifications_unselect);
        } else {
            holder.text_tv.setBackgroundResource(R.drawable.specifications_select);
        }
        holder.text_tv.setText(mDatas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectLists.get(position)) {
                    selectLists.put(position, false);
                    selectCount--;
                } else {
                    selectLists.put(position, true);
                    selectCount++;
                }

                if(mDatas.get(position).getIsChoose()==null||!mDatas.get(position).getIsChoose().equals("true")){
                    mDatas.get(position).setIsChoose("true");
                }else{
                    mDatas.get(position).setIsChoose("false");
                }

                mOnItemClickListener.onItemClick(position, selectCount,mDatas);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            text_tv = itemView.findViewById(R.id.text_tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int selectCount,List<SpecificationsBean.ResultBean.SpecListBean> mDatas);
    }

    public void setOnClickItem(SpecificationsAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public SparseBooleanArray getSelectedItem() {
        return selectLists;
    }

}
