package com.tem.gettogether.bean;

/**
 * Created by lt on 2019-04-23.
 */

public class CardCloaseBean {
    private String  goods_id;

    private boolean isCartClose;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public boolean isCartClose() {
        return isCartClose;
    }

    public void setCartClose(boolean cartClose) {
        isCartClose = cartClose;
    }
}
