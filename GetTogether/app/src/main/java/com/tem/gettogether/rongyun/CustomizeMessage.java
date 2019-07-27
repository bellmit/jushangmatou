package com.tem.gettogether.rongyun;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * Created by lt on 2019-05-15.
 */
@MessageTag(value = "GT:goods", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeMessage extends MessageContent {
    private String goods_id;
    private String goods_name;
    private String batch_number;
    private String image;
    private String store_id;

    public CustomizeMessage(String goods_id, String goods_name, String batch_number, String image, String store_id) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.batch_number = batch_number;
        this.image = image;
        this.store_id = store_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public static Creator<CustomizeMessage> getCREATOR() {
        return CREATOR;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("goods_id", getGoods_id());
            jsonObj.put("goods_name", getGoods_name());
            jsonObj.put("batch_number", getBatch_number());
            jsonObj.put("image", getImage());
            jsonObj.put("store_id", getStore_id());
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
    public CustomizeMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            if (jsonObj.has("goods_id"))
                goods_id = jsonObj.optString("goods_id");
            if (jsonObj.has("goods_name"))
                goods_name = jsonObj.optString("goods_name");
            if (jsonObj.has("batch_number"))
                batch_number = jsonObj.optString("batch_number");
            if (jsonObj.has("image"))
                image = jsonObj.optString("image");
            if (jsonObj.has("store_id"))
                store_id = jsonObj.optString("store_id");

        } catch (JSONException e) {
            Log.e("===", "JSONException"+ e.getMessage());
        }

    }
    /**
     * 构造函数。
     *
     * @param in 初始化传入的 Parcel。
     */
    public CustomizeMessage(Parcel in) {
        setGoods_id(ParcelUtils.readFromParcel(in));
        setGoods_name(ParcelUtils.readFromParcel(in));
        setBatch_number(ParcelUtils.readFromParcel(in));
        setImage(ParcelUtils.readFromParcel(in));
        setStore_id(ParcelUtils.readFromParcel(in));
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<CustomizeMessage> CREATOR = new Creator<CustomizeMessage>() {

        @Override
        public CustomizeMessage createFromParcel(Parcel source) {
            return new CustomizeMessage(source);
        }

        @Override
        public CustomizeMessage[] newArray(int size) {
            return new CustomizeMessage[size];
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
        ParcelUtils.writeToParcel(dest, getGoods_id());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getGoods_name());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getBatch_number());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getImage());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getStore_id());//该类为工具类，对消息中属性进行序列化
    }

}
