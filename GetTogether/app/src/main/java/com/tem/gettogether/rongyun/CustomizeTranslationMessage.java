package com.tem.gettogether.rongyun;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @ClassName: CustomizeTranslationMessage
 * @Author: csc
 * @CreateDate: 2019/9/18 11:55
 * @Description:
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "GT:translation", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeTranslationMessage extends MessageContent {
    private String from;
    private String to;

    public CustomizeTranslationMessage(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("from", getFrom());
            jsonObj.put("to", getTo());
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    public CustomizeTranslationMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("from"))
                from = jsonObj.optString("from");
            if (jsonObj.has("to"))
                to = jsonObj.optString("to");
        } catch (JSONException e) {
            Log.e("===", "JSONException" + e.getMessage());
        }

    }

    /**
     * 构造函数。
     *
     * @param in 初始化传入的 Parcel。
     */
    public CustomizeTranslationMessage(Parcel in) {
        setFrom(ParcelUtils.readFromParcel(in));
        setTo(ParcelUtils.readFromParcel(in));
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<CustomizeTranslationMessage> CREATOR = new Creator<CustomizeTranslationMessage>() {

        @Override
        public CustomizeTranslationMessage createFromParcel(Parcel source) {
            return new CustomizeTranslationMessage(source);
        }

        @Override
        public CustomizeTranslationMessage[] newArray(int size) {
            return new CustomizeTranslationMessage[size];
        }
    };

    /**
     * 描述了包含在 Parcelable 对象排列信息中的特殊对象的类型。
     *
     * @return 一个标志位，表明Parcelable对象特殊对象类型集合的排列。
     */
    public int describeContents() {
        return 0;
    }

    /**
     * 将类的数据写入外部提供的 Parcel 中。
     *
     * @param dest  对象被写入的 Parcel。
     * @param flags 对象如何被写入的附加标志。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, getFrom());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getTo());//该类为工具类，对消息中属性进行序列化
    }
}
