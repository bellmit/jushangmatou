package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.bean.StoreManagerListEntity;

import java.util.ArrayList;
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
    private OnDataListener onDataListener;
    List<StoreManagerListEntity.GuigesEntity> mDatas;
    private int currentCount;

    public SpecificationsDetailAddAdapter(Context context, int currentCount, List<StoreManagerListEntity.GuigesEntity> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        this.currentCount = currentCount;
        Log.d("chenshichun",""+mDatas);
    }

    @Override
    public SpecificationsDetailAddAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_specifications, null);
        return new SpecificationsDetailAddAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecificationsDetailAddAdapter.ViewHolder holder, final int position) {
        if (position == mDatas.get(currentCount).guigeArray.size() - 1) {
            holder.delete_iv.setVisibility(View.GONE);
            holder.text_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String name = holder.text_et.getText().toString().trim();
                        if (!TextUtils.isEmpty(name)) {
//                            mDatas.remove(mDatas.size()-1);
                            mDatas.get(currentCount).guigeArray.remove(mDatas.get(currentCount).guigeArray.size()-1);
                            mDatas.get(currentCount).guigeArray.add(name);
                            mDatas.get(currentCount).guigeArray.add("");
//                            holder.text_et.setFocusable(false);
                            notifyDataSetChanged();
                            List<String> list = bindAnotherRecyler();
                            onDataListener.onSaveData(position,list);
                        }
                    } else {

                    }
                    return false;
                }
            });
        }else{
            holder.delete_iv.setVisibility(View.VISIBLE);
        }
        holder.text_et.setText(mDatas.get(currentCount).guigeArray.get(position));
        holder.delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.get(currentCount).guigeArray.remove(position);
//                holder.text_et.setFocusable(true);
                notifyDataSetChanged();
                List<String> list = bindAnotherRecyler();
                onDataListener.onSaveData(position,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.get(currentCount).guigeArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public EditText text_et;
        public ImageView delete_iv;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            text_et = itemView.findViewById(R.id.text_et);
            delete_iv = itemView.findViewById(R.id.delete_iv);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }

    public interface OnDataListener {
        void onSaveData(int currentCount,List<String> mDatas);
    }

    public void setOnDataItem(OnDataListener onDataListener) {
        this.onDataListener = onDataListener;
    }

    private List<String> bindAnotherRecyler() {
        int b = 0;
        if (mDatas.size() > 0) {
            List<String> copylist = new ArrayList<>();
            for (int a = 0; a < mDatas.size(); a++) {
                if (mDatas.get(a).guigeArray.size() != 0) {
                    copylist.addAll(mDatas.get(a).guigeArray);
                    b = a;
                    break;
                }
            }
            copylist.remove(copylist.size()-1);
            if (copylist.size() > 0) {
                List<String> L0 = new ArrayList<>();
                L0.addAll(copylist);
                for (int i = b + 1; i < mDatas.size(); i++) {
                    List<String> L1 = mDatas.get(i).guigeArray;

                    List<String> list = new ArrayList<>();
                    for (int j = 0; j < L0.size(); j++) {
                        for (int z = 0; z < L1.size()-1; z++) {
                            String s = L0.get(j) + "," + L1.get(z);
                            list.add(s);
                        }
                    }
                    if (list.size() != 0) {
                        L0 = list;
                    }
                }
                return L0;
            }
        }
        return null;
    }

}