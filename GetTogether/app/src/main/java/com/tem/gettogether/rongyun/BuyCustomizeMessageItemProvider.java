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
import com.tem.gettogether.activity.home.HomeBuyDetailNewActivity;
import com.tem.gettogether.base.BaseApplication;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @ClassName: BuyCustomizeMessageItemProvider
 * @Author: csc
 * @CreateDate: 2019/9/25 15:20
 * @Description:
 */

@ProviderTag(messageContent = CustomizeBuyMessage.class)
public class BuyCustomizeMessageItemProvider extends IContainerItemProvider.MessageProvider<CustomizeBuyMessage> {

    class ViewHolder {
        ImageView image;
        TextView name;
        TextView count;
        LinearLayout ll_item;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void bindView(View view, int i, final CustomizeBuyMessage customizeMessage, UIMessage uiMessage) {
        BuyCustomizeMessageItemProvider.ViewHolder holder = (BuyCustomizeMessageItemProvider.ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }

        Glide.with(BaseApplication.getInstance()).load(customizeMessage.getImage()).error(R.mipmap.myy322x).into(holder.image);

        holder.name.setText(customizeMessage.getName());
        holder.count.setText(customizeMessage.getCount());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("chenshichun","==========="+customizeMessage.getTrade_id());
                BaseApplication.getInstance().mInstance.startActivity(new Intent(BaseApplication.getInstance().mInstance, HomeBuyDetailNewActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("trade_id", customizeMessage.getTrade_id())
                        .putExtra("witch_page",0)
                        .putExtra("page", 0));
            }
        });
    }

    @Override
    public Spannable getContentSummary(CustomizeBuyMessage customizeBuyMessage) {
        return new SpannableString("这是一条自定义消息CustomizeMessage");
    }

    @Override
    public void onItemClick(View view, int i, CustomizeBuyMessage customizeBuyMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_qiugou_customize_message, null);
        BuyCustomizeMessageItemProvider.ViewHolder holder = new BuyCustomizeMessageItemProvider.ViewHolder();
        holder.image = view.findViewById(R.id.image_iv);
        holder.name = view.findViewById(R.id.name_tv);
        holder.count = view.findViewById(R.id.count_tv);
        holder.ll_item = view.findViewById(R.id.ll_item);
        view.setTag(holder);
        return view;
    }
}
