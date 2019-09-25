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
 * @ClassName: CustomizeBuyMessage
 * @Author: csc
 * @CreateDate: 2019/9/25 15:21
 * @Description:
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "GT:buy", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeBuyMessage extends MessageContent {
    private String trade_id;
    private String image;
    private String name;
    private String count;

    public CustomizeBuyMessage(String trade_id,String image, String name, String count) {
        this.trade_id = trade_id;
        this.image = image;
        this.name = name;
        this.count = count;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("trade_id",getTrade_id());
            jsonObj.put("image", getImage());
            jsonObj.put("name", getName());
            jsonObj.put("count", getCount());
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

    public CustomizeBuyMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("trade_id"))
                trade_id = jsonObj.optString("trade_id");
            if (jsonObj.has("image"))
                image = jsonObj.optString("image");
            if (jsonObj.has("name"))
                name = jsonObj.optString("name");
            if (jsonObj.has("count"))
                count = jsonObj.optString("count");
        } catch (JSONException e) {
            Log.e("===", "JSONException" + e.getMessage());
        }
    }

    public CustomizeBuyMessage(Parcel in) {
        setTrade_id(ParcelUtils.readFromParcel(in));
        setImage(ParcelUtils.readFromParcel(in));
        setName(ParcelUtils.readFromParcel(in));
        setCount(ParcelUtils.readFromParcel(in));
    }

    public static final Creator<CustomizeBuyMessage> CREATOR = new Creator<CustomizeBuyMessage>() {

        @Override
        public CustomizeBuyMessage createFromParcel(Parcel source) {
            return new CustomizeBuyMessage(source);
        }

        @Override
        public CustomizeBuyMessage[] newArray(int size) {
            return new CustomizeBuyMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, getTrade_id());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getCount());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getName());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getImage());//该类为工具类，对消息中属性进行序列化
    }
}
