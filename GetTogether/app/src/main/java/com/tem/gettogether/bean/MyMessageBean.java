package com.tem.gettogether.bean;

/**
 * Created by lt on 2019-03-12.
 */

public class MyMessageBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"user_id":"131","email":"","pay_password":null,"sex":"0","birthday":"1970-01-01 08:00:00","user_money":"0.00","frozen_money":"0.00","qq":null,"mobile":"17608286532","mobile_validated":"1","head_pic":null,"email_validated":"0","nickname":"17608286532","level":"1","total_amount":"0.00","is_lock":"0","token":"bf80482c952809d9bca7bed72d180aa3","truename":null,"chat_id":"B9Ifx70tscH0tQ1zyqr526ushHWTnh/j9tPUI9Ra9u/Hf55vieidrOmjnbE9RtbErKGAQdi4vSOxKj5fK2RgTA==","footprint_count":"1","coupon_count":"0","collect_count":"1","store_status":4,"waitPay":"1","waitSend":"0","waitReceive":"0","order_count":1,"level_name":"注册会员"}
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
         * user_id : 131
         * email :
         * pay_password : null
         * sex : 0
         * birthday : 1970-01-01 08:00:00
         * user_money : 0.00
         * frozen_money : 0.00
         * qq : null
         * mobile : 17608286532
         * mobile_validated : 1
         * head_pic : null
         * email_validated : 0
         * nickname : 17608286532
         * level : 1
         * total_amount : 0.00
         * is_lock : 0
         * token : bf80482c952809d9bca7bed72d180aa3
         * truename : null
         * chat_id : B9Ifx70tscH0tQ1zyqr526ushHWTnh/j9tPUI9Ra9u/Hf55vieidrOmjnbE9RtbErKGAQdi4vSOxKj5fK2RgTA==
         * footprint_count : 1
         * coupon_count : 0
         * collect_count : 1
         * store_status : 4
         * waitPay : 1
         * waitSend : 0
         * waitReceive : 0
         * order_count : 1
         * level_name : 注册会员
         */

        private String user_id;
        private String email;
        private String pay_password;
        private String sex;
        private String birthday;
        private String user_money;
        private String frozen_money;
        private String qq;
        private String mobile;
        private String mobile_validated;
        private String head_pic;
        private String email_validated;
        private String nickname;
        private String level;
        private String total_amount;
        private String is_lock;
        private String token;
        private String truename;
        private String chat_id;
        private String footprint_count;
        private String coupon_count;
        private String collect_count;
        private String store_status;
        private String waitPay;
        private String waitSend;
        private String waitReceive;
        private int order_count;
        private String level_name;
        private String service_qq;
        private String expire_time;
        private String is_verify;
        private String store_id;
        private String store_count;
        private String fans_count;
        private String visiters_count;
        private String fprint_count;
        private String my_orders;
        private String store_collect_count;
        private String cart_goods;

        public String getCart_goods() {
            return cart_goods;
        }

        public void setCart_goods(String cart_goods) {
            this.cart_goods = cart_goods;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public String getFans_count() {
            return fans_count;
        }

        public void setFans_count(String fans_count) {
            this.fans_count = fans_count;
        }

        public String getVisiters_count() {
            return visiters_count;
        }

        public void setVisiters_count(String visiters_count) {
            this.visiters_count = visiters_count;
        }

        public String getFprint_count() {
            return fprint_count;
        }

        public void setFprint_count(String fprint_count) {
            this.fprint_count = fprint_count;
        }

        public String getMy_orders() {
            return my_orders;
        }

        public void setMy_orders(String my_orders) {
            this.my_orders = my_orders;
        }

        public String getStore_collect_count() {
            return store_collect_count;
        }

        public void setStore_collect_count(String store_collect_count) {
            this.store_collect_count = store_collect_count;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(String is_verify) {
            this.is_verify = is_verify;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getService_qq() {
            return service_qq;
        }

        public void setService_qq(String service_qq) {
            this.service_qq = service_qq;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPay_password() {
            return pay_password;
        }

        public void setPay_password(String pay_password) {
            this.pay_password = pay_password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile_validated() {
            return mobile_validated;
        }

        public void setMobile_validated(String mobile_validated) {
            this.mobile_validated = mobile_validated;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getEmail_validated() {
            return email_validated;
        }

        public void setEmail_validated(String email_validated) {
            this.email_validated = email_validated;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(String is_lock) {
            this.is_lock = is_lock;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getChat_id() {
            return chat_id;
        }

        public void setChat_id(String chat_id) {
            this.chat_id = chat_id;
        }

        public String getFootprint_count() {
            return footprint_count;
        }

        public void setFootprint_count(String footprint_count) {
            this.footprint_count = footprint_count;
        }

        public String getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(String coupon_count) {
            this.coupon_count = coupon_count;
        }

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }

        public String getStore_status() {
            return store_status;
        }

        public void setStore_status(String store_status) {
            this.store_status = store_status;
        }

        public String getWaitPay() {
            return waitPay;
        }

        public void setWaitPay(String waitPay) {
            this.waitPay = waitPay;
        }

        public String getWaitSend() {
            return waitSend;
        }

        public void setWaitSend(String waitSend) {
            this.waitSend = waitSend;
        }

        public String getWaitReceive() {
            return waitReceive;
        }

        public void setWaitReceive(String waitReceive) {
            this.waitReceive = waitReceive;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }
    }
}
