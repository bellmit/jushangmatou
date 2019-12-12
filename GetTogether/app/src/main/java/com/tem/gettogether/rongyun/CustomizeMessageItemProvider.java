package com.tem.gettogether.rongyun;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.home.ShoppingParticularsActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/**
 * Created by lt on 2019-05-15.
 */
@ProviderTag(messageContent = CustomizeBuyMessage.class)
public class CustomizeMessageItemProvider extends IContainerItemProvider.MessageProvider<CustomizeBuyMessage>  {
    private Context con;
    class ViewHolder {
        ImageView iv_iamge_lt;
        TextView tv_title_lt;
        TextView tv_qplNum;
        LinearLayout ll_item;
    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        con = context;
        View view = LayoutInflater.from(context).inflate(R.layout.item_customize2_message, null);
        ViewHolder holder = new ViewHolder();
        holder.iv_iamge_lt = view.findViewById(R.id.iv_iamge_lt);
        holder.tv_title_lt = view.findViewById(R.id.tv_title_lt);
        holder.tv_qplNum = view.findViewById(R.id.tv_qplNum);
        holder.ll_item=view.findViewById(R.id.ll_item);
        view.setTag(holder);
        return view;
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale+0.5f);
    }
    @Override
    public void bindView(View view, int i, final CustomizeBuyMessage content, UIMessage message) {
        CustomizeMessageItemProvider.ViewHolder holder = (CustomizeMessageItemProvider.ViewHolder) view.getTag();

        if (message.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.ll_item.getLayoutParams();
//            layoutParams.leftMargin=dip2px(BaseApplication.getInstance().mInstance,40);
//            holder.ll_item.setLayoutParams(layoutParams);
        } else {
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.ll_item.getLayoutParams();
//            layoutParams.rightMargin=dip2px(BaseApplication.getInstance().mInstance,40);
//            holder.ll_item.setLayoutParams(layoutParams);
        }
        Glide.with(BaseApplication.getInstance()).load(content.getImage()).error(R.mipmap.myy322x).into(holder.iv_iamge_lt);
        SharedPreferencesUtils.saveString(BaseApplication.getInstance().mInstance, BaseConstant.SPConstant.Shop_store_id,content.getStore_id());

        holder.tv_title_lt.setText(content.getGoods_name());
        holder.tv_qplNum.setText(content.getBatch_number());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("===自定义消息--","--图片--"+content.getImage()+"--goods_id--"+content.getGoods_id()
                        +"--起批量--"+content.getBatch_number()+"--store_id--"+content.getStore_id());
                BaseApplication.getInstance().mInstance.startActivity(new Intent(BaseApplication.getInstance(), ShoppingParticularsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("goods_id",content.getGoods_id()));

            }
        });
    }

    @Override
    public Spannable getContentSummary(CustomizeBuyMessage customizeMessage) {
        return new SpannableString(con.getString(R.string.custom_message));
    }

    @Override
    public void onItemClick(View view, int i, CustomizeBuyMessage customizeMessage, UIMessage uiMessage) {
        CustomizeMessageItemProvider.ViewHolder holder = (CustomizeMessageItemProvider.ViewHolder) view.getTag();

    }


}
