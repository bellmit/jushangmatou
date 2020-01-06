package com.tem.gettogether.rongyun;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/24 16:53 
 */

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huawei.hms.support.api.push.PushReceiver;


public class HUAWEIPushRevicer extends PushReceiver {

    @Override
    public void onToken(Context context, String token, Bundle bundle) {
        super.onToken(context, token, bundle);
        Log.e("chenshichun", "onToken: token   :  " + token);
        Toast.makeText(context, "token" + token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        try {
            //开发者可以自己解析消息内容，然后做相应的处理
            String content = new String(msg, "UTF-8");
            Log.e("chenshichun", "收到PUSH透传消息,消息内容为:" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
