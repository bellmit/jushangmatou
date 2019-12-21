package com.tem.gettogether.rongyun;

import android.content.Context;
import android.util.Log;

import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/4 11:57 
 */
public class CharNotificationReceiver extends PushMessageReceiver {

    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
        Log.e("chenshichun","---收到服务器推送--");
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
        return false;
    }
}
