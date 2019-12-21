package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/21 10:33 
 */
public class ShoppingXQMoreBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"user_name":"嘎嘎","country_name":"巴林","goods_num":"11","add_time":"2019-11-28 13:56:18","order_amount":"0.00","key_name":null},{"user_name":"嘎嘎","country_name":"巴林","goods_num":"11","add_time":"2019-11-28 11:41:43","order_amount":"0.00","key_name":null},{"user_name":"嘎嘎","country_name":"巴林","goods_num":"11","add_time":"2019-11-28 11:33:29","order_amount":"0.00","key_name":null}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * user_name : 嘎嘎
         * country_name : 巴林
         * goods_num : 11
         * add_time : 2019-11-28 13:56:18
         * order_amount : 0.00
         * key_name : null
         */

        private String user_name;
        private String country_name;
        private String goods_num;
        private String add_time;
        private String order_amount;
        private String key_name;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }
    }
}
