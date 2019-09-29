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
 * @Projectgoods_name: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @Classgoods_name: CustomizeBuyMessage
 * @Author: csc
 * @CreateDate: 2019/9/25 15:21
 * @Description:
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "GT:goods", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CustomizeBuyMessage extends MessageContent {
    private String image;
    private String goods_id;
    private String goods_name;
    private String batch_number;
    private String type;
    private String store_id;
    private String goods_type;
    private String qiugou_type;

    public CustomizeBuyMessage(String goods_id,String image, String goods_name,
                               String batch_number,String type,String store_id,String goods_type,String qiugou_type) {
        this.goods_id = goods_id;
        this.image = image;
        this.goods_name = goods_name;
        this.batch_number = batch_number;
        this.type = type;
        this.store_id = store_id;
        this.goods_type = goods_type;
        this.qiugou_type = qiugou_type;
    }

    public String getQiugou_type() {
        return qiugou_type;
    }

    public void setQiugou_type(String qiugou_type) {
        this.qiugou_type = qiugou_type;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setBatch_number(String count) {
        this.batch_number = count;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("goods_id",getGoods_id());
            jsonObj.put("image", getImage());
            jsonObj.put("goods_name", getGoods_name());
            jsonObj.put("batch_number", getBatch_number());
            jsonObj.put("type", getType());
            jsonObj.put("store_id", getStore_id());
            jsonObj.put("goods_type", getGoods_type());
            jsonObj.put("qiugou_type", getQiugou_type());
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
            if (jsonObj.has("goods_id"))
                goods_id = jsonObj.optString("goods_id");
            if (jsonObj.has("image"))
                image = jsonObj.optString("image");
            if (jsonObj.has("goods_name"))
                goods_name = jsonObj.optString("goods_name");
            if (jsonObj.has("batch_number"))
                batch_number = jsonObj.optString("batch_number");
            if (jsonObj.has("type"))
                type = jsonObj.optString("type");
            if (jsonObj.has("store_id"))
                store_id = jsonObj.optString("store_id");
            if (jsonObj.has("goods_type"))
                goods_type = jsonObj.optString("goods_type");
            if (jsonObj.has("qiugou_type"))
                qiugou_type = jsonObj.optString("qiugou_type");
        } catch (JSONException e) {
            Log.e("===", "JSONException" + e.getMessage());
        }
    }

    public CustomizeBuyMessage(Parcel in) {
        setGoods_id(ParcelUtils.readFromParcel(in));
        setImage(ParcelUtils.readFromParcel(in));
        setGoods_name(ParcelUtils.readFromParcel(in));
        setBatch_number(ParcelUtils.readFromParcel(in));
        setType(ParcelUtils.readFromParcel(in));
        setStore_id(ParcelUtils.readFromParcel(in));
        setGoods_type(ParcelUtils.readFromParcel(in));
        setQiugou_type(ParcelUtils.readFromParcel(in));
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
        ParcelUtils.writeToParcel(dest, getGoods_id());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getImage());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getGoods_name());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getBatch_number());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getType());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getStore_id());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getGoods_type());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(dest, getQiugou_type());//该类为工具类，对消息中属性进行序列化
    }
}
