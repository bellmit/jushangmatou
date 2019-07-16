package com.tem.gettogether.rongyun;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.tem.gettogether.base.BaseApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * 融云工具类
 */
public class RongTalk {
    private static final String TAG = "====RongTalk工具类---";

    public static boolean isRongCloudConnected = false;

    /**
     * update----荣云单聊
     */
    public static void doConnection(final Activity activity, final String userToken,
                                    final String targetId, final String targetName, final String targetImage, final String store_id) {
        /**
         * 链接自己的token值
         */
        RongIM.connect(userToken, new RongIMClient.ConnectCallback() {

            @Override
            public void onSuccess(String arg0) {
                Log.e(TAG, arg0.toString());
                isRongCloudConnected = true;
                BaseApplication.getInstance().userBean.setStore_id(store_id);
                BaseApplication.getInstance().userBean.setTargetId(targetId);

                if (null != RongIM.getInstance()) {

                    /**
                     * 设置用户头像信息等
                     */
                    RongIM.getInstance().setCurrentUserInfo(
                            new UserInfo(BaseApplication.getInstance().userBean.getChat_id(),
                                    BaseApplication.getInstance().userBean.getUserName(),
                                    Uri.parse(BaseApplication.getInstance().userBean.getHeadImg()+"")));
                    if(targetImage!=null&&!targetImage.equals("")){
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, targetName, Uri.parse(targetImage)));
                    }else {
                        RongIM.getInstance().setCurrentUserInfo(new UserInfo(targetId, targetName, Uri.parse(BaseApplication.getInstance().userBean.getHeadImg()+"")));
                    }

                    RongFriends friend = new RongFriends(targetId, targetName, targetImage);

                    if (RongIM.getInstance() != null) {

                        System.out.println("----启动会话----");
                        RongIM.getInstance().startPrivateChat(activity, targetId, targetName);
                    }
                } else {
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode arg0) {
//                Toast.makeText(activity, "错误：" + arg0.toString(), Toast.LENGTH_SHORT).show();
                Log.e("==info---", arg0.toString());
            }

            @Override
            public void onTokenIncorrect() {

                System.out.println("------onTokenIncorrect-------");
            }
        });

    }

    /**
     * 序列化对象
     *
     * @param friend
     * @return
     * @throws IOException
     */
    private static String MySerialize(RongFriends friend) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(friend);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        Log.d(TAG, "serialize str =" + serStr);

        return serStr;
    }

//    /**
//     * -----保存对象
//     * @param activity
//     * @param strObject
//     */
//    private static void saveObject(Activity activity, String strObject) {
//        SharedPreferences sp = activity.getSharedPreferences("friend", Context.MODE_APPEND);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString("person", strObject + "=A=");
//        edit.commit();
////        MyApplication.getInstance().edit.putString("person", strObject + "=A=").commit();
//    }
}
