package com.tem.gettogether.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lt on 2019-03-15.
 */

public class JieSPriceBean {

    /**
     * status : 1
     * msg : 计算成功
     * result : {"postFee":0,"payables":2288,"goodsFee":2288,"store_order_amount":{"43":2288},"store_shipping_price":{"43":0},"store_goods_price":{"43":2288}}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * postFee : 0
         * payables : 2288
         * goodsFee : 2288
         * store_order_amount : {"43":2288}
         * store_shipping_price : {"43":0}
         * store_goods_price : {"43":2288}
         */

        private int postFee;
        private int payables;
        private int goodsFee;
        private StoreOrderAmountBean store_order_amount;
        private StoreShippingPriceBean store_shipping_price;
        private StoreGoodsPriceBean store_goods_price;

        public int getPostFee() {
            return postFee;
        }

        public void setPostFee(int postFee) {
            this.postFee = postFee;
        }

        public int getPayables() {
            return payables;
        }

        public void setPayables(int payables) {
            this.payables = payables;
        }

        public int getGoodsFee() {
            return goodsFee;
        }

        public void setGoodsFee(int goodsFee) {
            this.goodsFee = goodsFee;
        }

        public StoreOrderAmountBean getStore_order_amount() {
            return store_order_amount;
        }

        public void setStore_order_amount(StoreOrderAmountBean store_order_amount) {
            this.store_order_amount = store_order_amount;
        }

        public StoreShippingPriceBean getStore_shipping_price() {
            return store_shipping_price;
        }

        public void setStore_shipping_price(StoreShippingPriceBean store_shipping_price) {
            this.store_shipping_price = store_shipping_price;
        }

        public StoreGoodsPriceBean getStore_goods_price() {
            return store_goods_price;
        }

        public void setStore_goods_price(StoreGoodsPriceBean store_goods_price) {
            this.store_goods_price = store_goods_price;
        }

        public static class StoreOrderAmountBean {
            /**
             * 43 : 2288
             */

            @SerializedName("43")
            private int _$43;

            public int get_$43() {
                return _$43;
            }

            public void set_$43(int _$43) {
                this._$43 = _$43;
            }
        }

        public static class StoreShippingPriceBean {
            /**
             * 43 : 0
             */

            @SerializedName("43")
            private int _$43;

            public int get_$43() {
                return _$43;
            }

            public void set_$43(int _$43) {
                this._$43 = _$43;
            }
        }

        public static class StoreGoodsPriceBean {
            /**
             * 43 : 2288
             */

            @SerializedName("43")
            private int _$43;

            public int get_$43() {
                return _$43;
            }

            public void set_$43(int _$43) {
                this._$43 = _$43;
            }
        }
    }
}
