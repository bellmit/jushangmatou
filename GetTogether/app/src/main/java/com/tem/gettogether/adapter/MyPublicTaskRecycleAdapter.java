package com.tem.gettogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.tem.gettogether.R;

import java.util.List;

import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.download.ImageDownloader;

public class MyPublicTaskRecycleAdapter extends RecyclerView.Adapter<MyPublicTaskRecycleAdapter.ViewHolder> {
    /**
     * ItemClick的回调接口
     *
     * @author LT
     */
    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private List<String> mPaths;
    private Context context;
    private OnClickListener onClickListener;
    private OnLongClickListener longClickListener;

    public MyPublicTaskRecycleAdapter(Context context, List<Integer> datas, List<String> mPaths,
                                      OnClickListener onClickListener, OnLongClickListener longClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.context = context;
        this.mPaths = mPaths;
        this.onClickListener = onClickListener;
        this.longClickListener = longClickListener;

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        // View layout;
        ImageView mImg;
        // TextView name;
        // TextView price;
    }

    @Override
    public int getItemCount() {
        return mPaths.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_advice_image, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mImg = (ImageView) view.findViewById(R.id.item_publishTask_image);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        // viewHolder.mImg.setImageResource(mDatas.get(position));

        System.out.println("onBindViewHolder:" + mPaths.size());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                // .displayer(new CircleBitmapDisplayer())
                .build();
        String drawableUrl;
        if (position == mPaths.size() - 1) {
            drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap(mPaths.get(position));
        } else {
            drawableUrl = ImageDownloader.Scheme.FILE.wrap(mPaths.get(position));
        }

        System.out.println("onBindViewHolder:" + drawableUrl);
        ImageLoader.getInstance().displayImage(drawableUrl, viewHolder.mImg, options);

        viewHolder.mImg.setOnClickListener(onClickListener);
        viewHolder.mImg.setOnLongClickListener(longClickListener);
        viewHolder.mImg.setTag(position);


    }

}