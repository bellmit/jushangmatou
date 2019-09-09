package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.ServiceProviderBean;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.adapter
 * @ClassName: SpecificationsDetailAddAdapter
 * @Author: csc
 * @CreateDate: 2019/9/9 17:19
 * @Description:
 */
public class SpecificationsDetailAddAdapter extends RecyclerView.Adapter<SpecificationsDetailAddAdapter.ViewHolder> {

    private Context context;
    private int size;
    private SpecificationsDetailAddAdapter.OnItemClickListener mOnItemClickListener;

    public SpecificationsDetailAddAdapter(Context context, int size) {
        this.context = context;
        this.size = size;
    }

    @Override
    public SpecificationsDetailAddAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_specifications, null);
        return new SpecificationsDetailAddAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecificationsDetailAddAdapter.ViewHolder holder, final int position) {


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
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView specifications_tv;
        public TextView delete_tv;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            specifications_tv = itemView.findViewById(R.id.specifications_tv);
            delete_tv = itemView.findViewById(R.id.delete_tv);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickItem(SpecificationsDetailAddAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

}