package com.tem.gettogether.rongyun;

import android.content.Context;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @ClassName: CustomizeMessageTranslationItemProvider
 * @Author: csc
 * @CreateDate: 2019/9/18 12:01
 * @Description:
 */
@ProviderTag(messageContent = CustomizeTranslationMessage.class)
public class CustomizeMessageTranslationItemProvider extends IContainerItemProvider.MessageProvider<CustomizeTranslationMessage>  {
    class ViewHolder {
        TextView from_tv;
        TextView to_tv;
        LinearLayout ll_item;

    }
    @Override
    public void bindView(View view, int i, CustomizeTranslationMessage customizeTranslationMessage, UIMessage uiMessage) {
        CustomizeMessageTranslationItemProvider.ViewHolder holder = (CustomizeMessageTranslationItemProvider.ViewHolder) view.getTag();
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.ll_item.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
        holder.from_tv.setText(customizeTranslationMessage.getFrom());
        holder.to_tv.setText(customizeTranslationMessage.getTo());
    }

    @Override
    public Spannable getContentSummary(CustomizeTranslationMessage customizeTranslationMessage) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, CustomizeTranslationMessage customizeTranslationMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_customize_translation, null);
        CustomizeMessageTranslationItemProvider.ViewHolder holder = new CustomizeMessageTranslationItemProvider.ViewHolder();
        holder.from_tv = view.findViewById(R.id.from_tv);
        holder.to_tv = view.findViewById(R.id.to_tv);
        holder.ll_item=view.findViewById(R.id.ll_item);
        view.setTag(holder);
        return view;
    }
}
