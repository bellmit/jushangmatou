package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.ServiceProviderBean;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.adapter
 * @ClassName: SpecificationsDetailAdapter
 * @Author: csc
 * @CreateDate: 2019/9/9 15:24
 * @Description:
 */
public class SpecificationsDetailAdapter extends RecyclerView.Adapter<SpecificationsDetailAdapter.ViewHolder> {

    private Context context;
    private List<ServiceProviderBean.ResultEntity> mDatas;
    private SpecificationsDetailAdapter.OnItemClickListener mOnItemClickListener;
    SpecificationsDetailAddAdapter mSpecificationsDetailAddAdapter;
    LinearLayoutManager layoutManager;

    public SpecificationsDetailAdapter(Context context, List<ServiceProviderBean.ResultEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public SpecificationsDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specifications_detail, null);
        return new SpecificationsDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecificationsDetailAdapter.ViewHolder holder, final int position) {

        holder.add_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpecificationsDetailAddAdapter = new SpecificationsDetailAddAdapter(context, 2);
                layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.recyclerView.setLayoutManager(layoutManager);
                holder.recyclerView.setAdapter(mSpecificationsDetailAddAdapter);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return /*mDatas.size()*/2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView specifications_tv;
        public TextView delete_tv;
        public RecyclerView recyclerView;
        private ConstraintLayout add_cl;

        public ViewHolder(View itemView) {
            super(itemView);
            specifications_tv = itemView.findViewById(R.id.specifications_tv);
            delete_tv = itemView.findViewById(R.id.delete_tv);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            add_cl = itemView.findViewById(R.id.add_cl);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(SpecificationsDetailAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}