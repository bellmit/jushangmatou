package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.StoreManagerListEntity;

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
    List<StoreManagerListEntity.GuigesEntity> mDatas;
    private OnCallListener mOnCallListener;
    SpecificationsDetailAddAdapter mSpecificationsDetailAddAdapter;

    public SpecificationsDetailAdapter(Context context, List<StoreManagerListEntity.GuigesEntity> mDatas) {
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
        mSpecificationsDetailAddAdapter = new SpecificationsDetailAddAdapter(context, position, mDatas);
        holder.recyclerView.setAdapter(mSpecificationsDetailAddAdapter);

        mSpecificationsDetailAddAdapter.setOnDataItem(new SpecificationsDetailAddAdapter.OnDataListener() {
            @Override
            public void onSaveData(int currentCount, List<String> mDatas) {
                Log.d("chenshichun","---mSpecificationsDetailAddAdapter------"+mDatas);
                mOnCallListener.onCallData(position,mDatas);
            }
        });

        holder.specifications_tv.setText(mDatas.get(position).title);

        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
        }
    }

    public interface OnCallListener {
        void onCallData(int position,List<String> mDatas);
    }

    public void setCallItem(OnCallListener onCallListener) {
        this.mOnCallListener = onCallListener;
    }

}