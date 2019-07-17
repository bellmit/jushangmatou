package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-13.
 */

public class CartDataBean {

    /**
     * status : 1
     * msg :
     * result : {"total_price":{"total_fee":9000,"num":300},"storeList":[{"store_id":50,"store_name":"sorry","is_own_shop":"0","cartList":[{"cart_id":"308","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"30.00","goods_fee":9000,"goods_num":"300","spec_key_name":"颜色:白 尺寸:100","spec_key":"175_177","selected":"1","is_enquiry":"0","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]}]}
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
         * total_price : {"total_fee":9000,"num":300}
         * storeList : [{"store_id":50,"store_name":"sorry","is_own_shop":"0","cartList":[{"cart_id":"308","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"30.00","goods_fee":9000,"goods_num":"300","spec_key_name":"颜色:白 尺寸:100","spec_key":"175_177","selected":"1","is_enquiry":"0","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]}]
         */

        private TotalPriceBean total_price;
        private List<StoreListBean> storeList;

        public TotalPriceBean getTotal_price() {
            return total_price;
        }

        public void setTotal_price(TotalPriceBean total_price) {
            this.total_price = total_price;
        }

        public List<StoreListBean> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<StoreListBean> storeList) {
            this.storeList = storeList;
        }

        public static class TotalPriceBean {
            /**
             * total_fee : 9000
             * num : 300
             */

            private Double total_fee;
            private int num;

            public Double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(Double total_fee) {
                this.total_fee = total_fee;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }

        public static class StoreListBean {
            /**
             * store_id : 50
             * store_name : sorry
             * is_own_shop : 0
             * cartList : [{"cart_id":"308","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"30.00","goods_fee":9000,"goods_num":"300","spec_key_name":"颜色:白 尺寸:100","spec_key":"175_177","selected":"1","is_enquiry":"0","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]
             */

            private int store_id;
            private String store_name;
            private String is_own_shop;
            private String shopXZ="0";

            public String getShopXZ() {
                return shopXZ;
            }

            public void setShopXZ(String shopXZ) {
                this.shopXZ = shopXZ;
            }

            private List<CartListBean> cartList;

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getIs_own_shop() {
                return is_own_shop;
            }

            public void setIs_own_shop(String is_own_shop) {
                this.is_own_shop = is_own_shop;
            }

            public List<CartListBean> getCartList() {
                return cartList;
            }

            public void setCartList(List<CartListBean> cartList) {
                this.cartList = cartList;
            }

            public static class CartListBean {
                /**
                 * cart_id : 308
                 * store_id : 50
                 * goods_id : 244
                 * goods_name : 皮革
                 * goods_price : 30.00
                 * goods_fee : 9000
                 * goods_num : 300
                 * spec_key_name : 颜色:白 尺寸:100
                 * spec_key : 175_177
                 * selected : 1
                 * is_enquiry : 0
                 * batch_number : 1
                 * goods_logo : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg
                 */

                private String cart_id;
                private String store_id;
                private String goods_id;
                private String store_user_id;
                private String goods_name;
                private String goods_price;
                private double goods_fee;
                private String goods_num;
                private String spec_key_name;
                private String spec_key;
                private String selected;
                private String is_enquiry;
                private String batch_number;
                private String goods_logo;
                private String itemXZ="0";

                public String getStore_user_id() {
                    return store_user_id;
                }

                public void setStore_user_id(String store_user_id) {
                    this.store_user_id = store_user_id;
                }

                public String getItemXZ() {
                    return itemXZ;
                }

                public void setItemXZ(String itemXZ) {
                    this.itemXZ = itemXZ;
                }

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
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

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public double getGoods_fee() {
                    return goods_fee;
                }

                public void setGoods_fee(double goods_fee) {
                    this.goods_fee = goods_fee;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getSpec_key_name() {
                    return spec_key_name;
                }

                public void setSpec_key_name(String spec_key_name) {
                    this.spec_key_name = spec_key_name;
                }

                public String getSpec_key() {
                    return spec_key;
                }

                public void setSpec_key(String spec_key) {
                    this.spec_key = spec_key;
                }

                public String getSelected() {
                    return selected;
                }

                public void setSelected(String selected) {
                    this.selected = selected;
                }

                public String getIs_enquiry() {
                    return is_enquiry;
                }

                public void setIs_enquiry(String is_enquiry) {
                    this.is_enquiry = is_enquiry;
                }

                public String getBatch_number() {
                    return batch_number;
                }

                public void setBatch_number(String batch_number) {
                    this.batch_number = batch_number;
                }

                public String getGoods_logo() {
                    return goods_logo;
                }

                public void setGoods_logo(String goods_logo) {
                    this.goods_logo = goods_logo;
                }
            }
        }
    }
}
