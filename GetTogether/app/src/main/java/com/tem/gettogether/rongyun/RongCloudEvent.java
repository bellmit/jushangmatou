package com.tem.gettogether.rongyun;

import android.content.Context;
import android.util.Log;

import com.tem.gettogether.activity.SplashActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/4 11:55 
 */
public class RongCloudEvent implements RongIMClient.OnReceiveMessageListener, RongIMClient.ConnectionStatusListener, RongIM.OnSendMessageListener {
    private static RongCloudEvent mRongCloudInstance;
    private final Context mContext;
    //开始的时候是2000    改成1s了
    long time = 1000 * 60 * 30;
    static SplashActivity act;

    @Override
    public boolean onReceived(Message message, int i) {
        Log.d("", message.toString());
        //如果接受到了消息就需要把定时任务关闭
        return false;
    }

    /**
     * 初始化 RongCloud.
     *
     * @param context 上下文。
     */
    public static void init(Context context) {
        if (mRongCloudInstance == null) {
            synchronized (RongCloudEvent.class) {
                if (mRongCloudInstance == null) {
                    mRongCloudInstance = new RongCloudEvent(context);
                }
            }
        }
    }

    /**
     * 构造方法。
     *
     * @param context 上下文。
     */
    private RongCloudEvent(Context context) {
        mContext = context;
        initDefaultListener();
    }

    /**
     * 获取RongCloud 实例。
     *
     * @return RongCloud。
     */
    public static RongCloudEvent getInstance() {
        return mRongCloudInstance;
    }

    /**
     * RongIM.init(this) 后直接可注册的Listener。
     */
    private void initDefaultListener() {
        RongIM.getInstance().setOnReceiveMessageListener(this);//设置消息接收监听器
        RongIM.getInstance().setSendMessageListener(this);
        RongIM.setConnectionStatusListener(this);
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {

    }

    @Override
    public Message onSend(Message message) {
        Log.d("", message.toString());
        return null;
    }

    @Override
    public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
        Log.d("", message.toString());
        return false;
    }


}
