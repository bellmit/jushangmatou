package com.tem.gettogether.bean;

import java.util.List;

public class ProductManagementBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"goods_id":"5754","goods_name":"好的哈哈哈","store_count":"6767","shop_price":"5454.00","cover_image":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808113215_50293.jpeg"}]
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
         * goods_id : 5754
         * goods_name : 好的哈哈哈
         * store_count : 6767
         * shop_price : 5454.00
         * cover_image : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808113215_50293.jpeg
         */

        private String goods_id;
        private String goods_name;
        private String batch_number;
        private String shop_price;
        private String cover_image;
        private String is_enquiry;

        public String getIs_enquiry() {
            return is_enquiry;
        }

        public void setIs_enquiry(String is_enquiry) {
            this.is_enquiry = is_enquiry;
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

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getcover_image() {
            return cover_image;
        }

        public void setcover_image(String cover_image) {
            this.cover_image = cover_image;
        }
    }
}
