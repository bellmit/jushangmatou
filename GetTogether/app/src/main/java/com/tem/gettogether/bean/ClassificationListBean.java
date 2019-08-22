package com.tem.gettogether.bean;

import java.util.List;

public class ClassificationListBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"level_id":"7","cover_image":"","goods_name":"测试","shop_price":"100.00","batch_number":"258"}]
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
         * level_id : 7
         * cover_image :
         * goods_name : 测试
         * shop_price : 100.00
         * batch_number : 258
         */
        private String goods_id;
        private String level_id;
        private String cover_image;
        private String goods_name;
        private String shop_price;
        private String batch_number;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getcover_image() {
            return cover_image;
        }

        public void setcover_image(String cover_image) {
            this.cover_image = cover_image;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getBatch_number() {
            return batch_number;
        }

        public void setBatch_number(String batch_number) {
            this.batch_number = batch_number;
        }
    }
}
