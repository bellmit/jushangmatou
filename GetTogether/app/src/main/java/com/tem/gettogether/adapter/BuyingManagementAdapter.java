package com.tem.gettogether.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;
import com.tem.gettogether.R;
import com.tem.gettogether.adapter.viewHolder.CommonAdapter;
import com.tem.gettogether.adapter.viewHolder.ViewHolder;
import com.tem.gettogether.bean.QiuGouListBean;
import com.tem.gettogether.utils.SizeUtil;

import java.util.List;

public class BuyingManagementAdapter extends CommonAdapter<QiuGouListBean.ResultBean> {

    private ItemClickListener listener;
    private Context context;

    public BuyingManagementAdapter(Context context, List<QiuGouListBean.ResultBean> datas, int layoutId) {
        super(context, datas, layoutId);
        this.context = context;
    }

    @Override
    public void convert(final ViewHolder holder, QiuGouListBean.ResultBean resultBean) {
        int imageSize = SizeUtil.dp2px(context, 110);
        Glide.with(context).load(resultBean.getGoods_logo().get(0)).asBitmap().placeholder(R.mipmap.myy322x)
                .error(R.mipmap.myy322x).override(imageSize, imageSize).into((ImageView) holder.getView(R.id.pic_iv));
        holder.setText(R.id.product_title, resultBean.getGoods_name());
        holder.setText(R.id.buy_style_tv, resultBean.getRelease_type());
        holder.setText(R.id.buy_time_tv, resultBean.getAttach_time());
        holder.setVisible(R.id.time_tv,true);
        holder.setText(R.id.chukou_tv, resultBean.getCountry_name());
        holder.setText(R.id.time_tv,resultBean.getAdd_time());
        final EasySwipeMenuLayout easySwipeMenuLayout = holder.getView(R.id.action_bar);

        //删除
        holder.setOnClickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //侧滑初始化
                easySwipeMenuLayout.resetStatus();
                listener.ItemDeleteClickListener(v, holder.getPosition());
            }
        });

    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {

        void ItemClickListener(View view, int position);

        void ItemDeleteClickListener(View view, int position);

    }

}
