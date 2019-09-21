package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-13.
 */

public class JieSuanBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"addressList":{"province":"北京市","city":"市辖区","district":"东城区","twon":"东华门街道","address":"测试","consignee":"测试","address_id":"79","email":"","mobile":"18705535535","zipcode":"111111"},"totalPrice":{"total_fee":30,"num":3},"storeList":[{"store_id":50,"store_name":"sorry","shippingList":[{"shipping_area_id":"117","shipping_code":"shentong","store_id":"50","name":"申通物流"},{"shipping_area_id":"118","shipping_code":"shunfeng","store_id":"50","name":"顺丰物流"}],"cartList":[{"cart_id":"339","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"10.00","goods_fee":30,"goods_num":"3","spec_key_name":"颜色:红 尺寸:100","spec_key":"174_177","selected":"1","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]}],"cart_ids":"339"}
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
         * addressList : {"province":"北京市","city":"市辖区","district":"东城区","twon":"东华门街道","address":"测试","consignee":"测试","address_id":"79","email":"","mobile":"18705535535","zipcode":"111111"}
         * totalPrice : {"total_fee":30,"num":3}
         * storeList : [{"store_id":50,"store_name":"sorry","shippingList":[{"shipping_area_id":"117","shipping_code":"shentong","store_id":"50","name":"申通物流"},{"shipping_area_id":"118","shipping_code":"shunfeng","store_id":"50","name":"顺丰物流"}],"cartList":[{"cart_id":"339","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"10.00","goods_fee":30,"goods_num":"3","spec_key_name":"颜色:红 尺寸:100","spec_key":"174_177","selected":"1","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]}]
         * cart_ids : 339
         */

        private AddressListBean addressList;
        private TotalPriceBean totalPrice;
        private String cart_ids;
        private List<StoreListBean> storeList;

        public AddressListBean getAddressList() {
            return addressList;
        }

        public void setAddressList(AddressListBean addressList) {
            this.addressList = addressList;
        }

        public TotalPriceBean getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(TotalPriceBean totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getCart_ids() {
            return cart_ids;
        }

        public void setCart_ids(String cart_ids) {
            this.cart_ids = cart_ids;
        }

        public List<StoreListBean> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<StoreListBean> storeList) {
            this.storeList = storeList;
        }

        public static class AddressListBean {
            /**
             * province : 北京市
             * city : 市辖区
             * district : 东城区
             * twon : 东华门街道
             * address : 测试
             * consignee : 测试
             * address_id : 79
             * email :
             * mobile : 18705535535
             * zipcode : 111111
             */

            private String province;
            private String city;
            private String district;
            private String twon;
            private String address;
            private String consignee;
            private String address_id;
            private String email;
            private String mobile;
            private String zipcode;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getTwon() {
                return twon;
            }

            public void setTwon(String twon) {
                this.twon = twon;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }
        }

        public static class TotalPriceBean {
            /**
             * total_fee : 30
             * num : 3
             */

            private int total_fee;
            private int num;

            public int getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(int total_fee) {
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
             * shippingList : [{"shipping_area_id":"117","shipping_code":"shentong","store_id":"50","name":"申通物流"},{"shipping_area_id":"118","shipping_code":"shunfeng","store_id":"50","name":"顺丰物流"}]
             * cartList : [{"cart_id":"339","store_id":"50","goods_id":"244","goods_name":"皮革","goods_price":"10.00","goods_fee":30,"goods_num":"3","spec_key_name":"颜色:红 尺寸:100","spec_key":"174_177","selected":"1","batch_number":"1","goods_logo":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]
             */

            private int store_id;
            private String store_name;
            private List<ShippingListBean> shippingList;
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

            public List<ShippingListBean> getShippingList() {
                return shippingList;
            }

            public void setShippingList(List<ShippingListBean> shippingList) {
                this.shippingList = shippingList;
            }

            public List<CartListBean> getCartList() {
                return cartList;
            }

            public void setCartList(List<CartListBean> cartList) {
                this.cartList = cartList;
            }

            public static class ShippingListBean {
                /**
                 * shipping_area_id : 117
                 * shipping_code : shentong
                 * store_id : 50
                 * name : 申通物流
                 */

                private String shipping_area_id;
                private String shipping_code;
                private String store_id;
                private String name;

                public String getShipping_area_id() {
                    return shipping_area_id;
                }

                public void setShipping_area_id(String shipping_area_id) {
                    this.shipping_area_id = shipping_area_id;
                }

                public String getShipping_code() {
                    return shipping_code;
                }

                public void setShipping_code(String shipping_code) {
                    this.shipping_code = shipping_code;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class CartListBean {
                /**
                 * cart_id : 339
                 * store_id : 50
                 * goods_id : 244
                 * goods_name : 皮革
                 * goods_price : 10.00
                 * goods_fee : 30
                 * goods_num : 3
                 * spec_key_name : 颜色:红 尺寸:100
                 * spec_key : 174_177
                 * selected : 1
                 * batch_number : 1
                 * goods_logo : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg
                 */

                private String cart_id;
                private String store_id;
                private String goods_id;
                private String goods_name;
                private String goods_price;
                private int goods_fee;
                private String goods_num;
                private String spec_key_name;
                private String spec_key;
                private String selected;
                private String batch_number;
                private String goods_logo;
                private String is_enquiry;

                public String getIs_enquiry() {
                    return is_enquiry;
                }

                public void setIs_enquiry(String is_enquiry) {
                    this.is_enquiry = is_enquiry;
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

                public int getGoods_fee() {
                    return goods_fee;
                }

                public void setGoods_fee(int goods_fee) {
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
