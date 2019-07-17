package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-14.
 */

public class OrderDataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"order_id":"417","order_sn":"201903141438211229","master_order_sn":"201903141438218777","user_id":"143","order_status":"0","shipping_status":"0","pay_status":"0","consignee":"嗯嗯呢","country":"0","province":"天津市","city":"市辖区","district":"河东区","twon":"大直沽街道","address":"么得KKK咯弄","zipcode":"","mobile":"18339965555","email":"","shipping_code":"shentong","shipping_name":"申通物流","pay_code":"","pay_name":"","invoice_title":"","goods_price":"764.00","shipping_price":"0.00","user_money":"0.00","coupon_price":null,"integral":"0","integral_money":"0.00","order_amount":"764.00","total_amount":"764.00","add_time":"2019-03-14 14:38:21","confirm_time":"1970-01-01 08:00:00","pay_time":"1970-01-01 08:00:00","shipping_time":null,"order_prom_id":"0","order_prom_amount":"0.00","discount":"0.00","user_note":"","admin_note":"","parent_sn":null,"store_id":"61","is_comment":"0","deleted":"0","is_checkout":"0","transaction":null,"order_status_code":"WAITPAY","order_status_desc":"待支付","pay_btn":1,"cancel_btn":1,"receive_btn":0,"comment_btn":0,"shipping_btn":0,"return_btn":0,"store_name":"春春","store_qq":null,"store_phone":null,"invoice_no":null,"goods_list":[{"goods_id":"657","goods_name":"2019春季新款西装女韩版长袖小西装外套女职业西装女女装","goods_sn":"TP0000657","goods_num":"7","goods_price":"76.00","spec_key_name":"","spec_key":"175_177","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/657/goods_thumb_657_400_400.jpeg"},{"goods_id":"669","goods_name":"9219-22韩版时尚宴会女鞋细跟浅口尖头性感显瘦水钻彩钻高跟单鞋","goods_sn":"TP0000669","goods_num":"4","goods_price":"58.00","spec_key_name":"","spec_key":"174_178","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/669/goods_thumb_669_400_400.jpeg"}]}
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
         * order_id : 417
         * order_sn : 201903141438211229
         * master_order_sn : 201903141438218777
         * user_id : 143
         * order_status : 0
         * shipping_status : 0
         * pay_status : 0
         * consignee : 嗯嗯呢
         * country : 0
         * province : 天津市
         * city : 市辖区
         * district : 河东区
         * twon : 大直沽街道
         * address : 么得KKK咯弄
         * zipcode :
         * mobile : 18339965555
         * email :
         * shipping_code : shentong
         * shipping_name : 申通物流
         * pay_code :
         * pay_name :
         * invoice_title :
         * goods_price : 764.00
         * shipping_price : 0.00
         * user_money : 0.00
         * coupon_price : null
         * integral : 0
         * integral_money : 0.00
         * order_amount : 764.00
         * total_amount : 764.00
         * add_time : 2019-03-14 14:38:21
         * confirm_time : 1970-01-01 08:00:00
         * pay_time : 1970-01-01 08:00:00
         * shipping_time : null
         * order_prom_id : 0
         * order_prom_amount : 0.00
         * discount : 0.00
         * user_note :
         * admin_note :
         * parent_sn : null
         * store_id : 61
         * is_comment : 0
         * deleted : 0
         * is_checkout : 0
         * transaction : null
         * order_status_code : WAITPAY
         * order_status_desc : 待支付
         * pay_btn : 1
         * cancel_btn : 1
         * receive_btn : 0
         * comment_btn : 0
         * shipping_btn : 0
         * return_btn : 0
         * store_name : 春春
         * store_qq : null
         * store_phone : null
         * invoice_no : null
         * goods_list : [{"goods_id":"657","goods_name":"2019春季新款西装女韩版长袖小西装外套女职业西装女女装","goods_sn":"TP0000657","goods_num":"7","goods_price":"76.00","spec_key_name":"","spec_key":"175_177","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/657/goods_thumb_657_400_400.jpeg"},{"goods_id":"669","goods_name":"9219-22韩版时尚宴会女鞋细跟浅口尖头性感显瘦水钻彩钻高跟单鞋","goods_sn":"TP0000669","goods_num":"4","goods_price":"58.00","spec_key_name":"","spec_key":"174_178","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/669/goods_thumb_669_400_400.jpeg"}]
         */

        private String order_id;
        private String order_sn;
        private String master_order_sn;
        private String user_id;
        private String order_status;
        private String shipping_status;
        private String pay_status;
        private String consignee;
        private String country;
        private String province;
        private String city;
        private String district;
        private String twon;
        private String address;
        private String zipcode;
        private String mobile;
        private String email;
        private String shipping_code;
        private String shipping_name;
        private String pay_code;
        private String pay_name;
        private String invoice_title;
        private String goods_price;
        private String shipping_price;
        private String user_money;
        private Object coupon_price;
        private String integral;
        private String integral_money;
        private String order_amount;
        private String total_amount;
        private String add_time;
        private String confirm_time;
        private String pay_time;
        private Object shipping_time;
        private String order_prom_id;
        private String order_prom_amount;
        private String discount;
        private String user_note;
        private String admin_note;
        private Object parent_sn;
        private String store_id;
        private String is_comment;
        private String deleted;
        private String is_checkout;
        private Object transaction;
        private String order_status_code;
        private String order_status_desc;
        private String goods_nums;
        private int pay_btn;
        private int cancel_btn;
        private int receive_btn;
        private int comment_btn;
        private int shipping_btn;
        private int return_btn;
        private String store_name;
        private String store_qq;
        private String store_phone;
        private String invoice_no;
        private List<GoodsListBean> goods_list;
        private int del_btn;

        public int getDel_btn() {
            return del_btn;
        }

        public void setDel_btn(int del_btn) {
            this.del_btn = del_btn;
        }

        public String getGoods_nums() {
            return goods_nums;
        }

        public void setGoods_nums(String goods_nums) {
            this.goods_nums = goods_nums;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getMaster_order_sn() {
            return master_order_sn;
        }

        public void setMaster_order_sn(String master_order_sn) {
            this.master_order_sn = master_order_sn;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

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

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getInvoice_title() {
            return invoice_title;
        }

        public void setInvoice_title(String invoice_title) {
            this.invoice_title = invoice_title;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(String shipping_price) {
            this.shipping_price = shipping_price;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public Object getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(Object coupon_price) {
            this.coupon_price = coupon_price;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIntegral_money() {
            return integral_money;
        }

        public void setIntegral_money(String integral_money) {
            this.integral_money = integral_money;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(String confirm_time) {
            this.confirm_time = confirm_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public Object getShipping_time() {
            return shipping_time;
        }

        public void setShipping_time(Object shipping_time) {
            this.shipping_time = shipping_time;
        }

        public String getOrder_prom_id() {
            return order_prom_id;
        }

        public void setOrder_prom_id(String order_prom_id) {
            this.order_prom_id = order_prom_id;
        }

        public String getOrder_prom_amount() {
            return order_prom_amount;
        }

        public void setOrder_prom_amount(String order_prom_amount) {
            this.order_prom_amount = order_prom_amount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getUser_note() {
            return user_note;
        }

        public void setUser_note(String user_note) {
            this.user_note = user_note;
        }

        public String getAdmin_note() {
            return admin_note;
        }

        public void setAdmin_note(String admin_note) {
            this.admin_note = admin_note;
        }

        public Object getParent_sn() {
            return parent_sn;
        }

        public void setParent_sn(Object parent_sn) {
            this.parent_sn = parent_sn;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getIs_checkout() {
            return is_checkout;
        }

        public void setIs_checkout(String is_checkout) {
            this.is_checkout = is_checkout;
        }

        public Object getTransaction() {
            return transaction;
        }

        public void setTransaction(Object transaction) {
            this.transaction = transaction;
        }

        public String getOrder_status_code() {
            return order_status_code;
        }

        public void setOrder_status_code(String order_status_code) {
            this.order_status_code = order_status_code;
        }

        public String getOrder_status_desc() {
            return order_status_desc;
        }

        public void setOrder_status_desc(String order_status_desc) {
            this.order_status_desc = order_status_desc;
        }

        public int getPay_btn() {
            return pay_btn;
        }

        public void setPay_btn(int pay_btn) {
            this.pay_btn = pay_btn;
        }

        public int getCancel_btn() {
            return cancel_btn;
        }

        public void setCancel_btn(int cancel_btn) {
            this.cancel_btn = cancel_btn;
        }

        public int getReceive_btn() {
            return receive_btn;
        }

        public void setReceive_btn(int receive_btn) {
            this.receive_btn = receive_btn;
        }

        public int getComment_btn() {
            return comment_btn;
        }

        public void setComment_btn(int comment_btn) {
            this.comment_btn = comment_btn;
        }

        public int getShipping_btn() {
            return shipping_btn;
        }

        public void setShipping_btn(int shipping_btn) {
            this.shipping_btn = shipping_btn;
        }

        public int getReturn_btn() {
            return return_btn;
        }

        public void setReturn_btn(int return_btn) {
            this.return_btn = return_btn;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_qq() {
            return store_qq;
        }

        public void setStore_qq(String store_qq) {
            this.store_qq = store_qq;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public Object getInvoice_no() {
            return invoice_no;
        }

        public void setInvoice_no(String invoice_no) {
            this.invoice_no = invoice_no;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 657
             * goods_name : 2019春季新款西装女韩版长袖小西装外套女职业西装女女装
             * goods_sn : TP0000657
             * goods_num : 7
             * goods_price : 76.00
             * spec_key_name :
             * spec_key : 175_177
             * image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/657/goods_thumb_657_400_400.jpeg
             */

            private String goods_id;
            private String goods_name;
            private String goods_sn;
            private String goods_num;
            private String goods_price;
            private String spec_key_name;
            private String spec_key;
            private String image;

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

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
