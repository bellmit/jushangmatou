package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.BuyingManagementBean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/26 10:26 
 */
public class BuyManagerAdapter extends RecyclerView.Adapter<BuyManagerAdapter.ViewHolder> {

    private Context context;
    private List<BuyingManagementBean.ResultBean> mDatas;
    private BuyManagerAdapter.onItemClickView onClickView;

    public BuyManagerAdapter(Context context, List<BuyingManagementBean.ResultBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public BuyManagerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buying_managerment, null);
        return new BuyManagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuyManagerAdapter.ViewHolder holder, final int position) {
        holder.time_tv.setText(mDatas.get(position).getTime());
        BuyingManagementAdapter mBuyManagerAdapter = new BuyingManagementAdapter(context, mDatas.get(position).getBuy_info(), R.layout.item_cehua);
        holder.list_view.setAdapter(mBuyManagerAdapter);
        setListViewHeightBasedOnChildren(holder.list_view);
        mBuyManagerAdapter.setOnItemClickListener(new BuyingManagementAdapter.ItemClickListener() {
            @Override
            public void ItemClickListener(View view, int position) {

            }

            @Override
            public void ItemDeleteClickListener(View view, int position1) {
                onClickView.onDelete(position, position1);
            }

            @Override
            public void uploadCompletedOrder(int position1) {
                onClickView.uploadCompletedOrder(position, position1);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView time_tv;
        public ListView list_view;

        public ViewHolder(View itemView) {
            super(itemView);
            time_tv = itemView.findViewById(R.id.time_tv);
            list_view = itemView.findViewById(R.id.list_view);
        }
    }

    public interface onItemClickView {
        void onClick(int position);

        void onDelete(int position, int position1);

        void uploadCompletedOrder(int position, int position1);
    }

    public void setOnClickView(BuyManagerAdapter.onItemClickView onClickView) {
        this.onClickView = onClickView;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
// 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
// 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
// listView.getDividerHeight()获取子项间分隔符占用的高度
// params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
