package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/26 10:05 
 */
public class BuyingManagementBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"time":"2019-11-26","buy_info":[{"trade_id":"541","user_id":"780","add_time":"2019-11-26 09:46","goods_name":"test","goods_logo":["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191126/20191126094647_10137.jpg"],"end_pic":"","release_type":"现货库存","attach_time":"15天内","end_time":null,"status":"1","country_name":"伊朗"}]}]
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
         * time : 2019-11-26
         * buy_info : [{"trade_id":"541","user_id":"780","add_time":"2019-11-26 09:46","goods_name":"test","goods_logo":["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191126/20191126094647_10137.jpg"],"end_pic":"","release_type":"现货库存","attach_time":"15天内","end_time":null,"status":"1","country_name":"伊朗"}]
         */

        private String time;
        private List<BuyInfoBean> buy_info;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<BuyInfoBean> getBuy_info() {
            return buy_info;
        }

        public void setBuy_info(List<BuyInfoBean> buy_info) {
            this.buy_info = buy_info;
        }

        public static class BuyInfoBean {
            /**
             * trade_id : 541
             * user_id : 780
             * add_time : 2019-11-26 09:46
             * goods_name : test
             * goods_logo : ["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191126/20191126094647_10137.jpg"]
             * end_pic :
             * release_type : 现货库存
             * attach_time : 15天内
             * end_time : null
             * status : 1
             * country_name : 伊朗
             */

            private String trade_id;
            private String user_id;
            private String add_time;
            private String goods_name;
            private String end_pic;
            private String release_type;
            private String attach_time;
            private Object end_time;
            private String status;
            private String country_name;
            private List<String> goods_logo;

            public String getTrade_id() {
                return trade_id;
            }

            public void setTrade_id(String trade_id) {
                this.trade_id = trade_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getEnd_pic() {
                return end_pic;
            }

            public void setEnd_pic(String end_pic) {
                this.end_pic = end_pic;
            }

            public String getRelease_type() {
                return release_type;
            }

            public void setRelease_type(String release_type) {
                this.release_type = release_type;
            }

            public String getAttach_time() {
                return attach_time;
            }

            public void setAttach_time(String attach_time) {
                this.attach_time = attach_time;
            }

            public Object getEnd_time() {
                return end_time;
            }

            public void setEnd_time(Object end_time) {
                this.end_time = end_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            public List<String> getGoods_logo() {
                return goods_logo;
            }

            public void setGoods_logo(List<String> goods_logo) {
                this.goods_logo = goods_logo;
            }
        }
    }
}
