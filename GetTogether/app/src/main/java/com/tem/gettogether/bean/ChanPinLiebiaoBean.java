package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-14.
 */

public class ChanPinLiebiaoBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"goods_id":"4994","goods_name":"hi","store_count":"778","shop_price":"4554.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190511/20190511225718_.jpeg","is_on_sale":"1"},{"goods_id":"4993","goods_name":"农民","store_count":"66","shop_price":"519.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190511/20190511225535_.jpeg","is_on_sale":"1"},{"goods_id":"4964","goods_name":"cs","store_count":"0","shop_price":"0.00","original_img":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/2019/05-09/5cd3c2b3620dc.jpg","is_on_sale":"1"},{"goods_id":"4944","goods_name":"12369","store_count":"10","shop_price":"888.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190508/20190508170808_.jpeg","is_on_sale":"1"},{"goods_id":"4943","goods_name":"测试123","store_count":"100","shop_price":"100.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190508/20190508170620_.jpeg","is_on_sale":"1"},{"goods_id":"4921","goods_name":"qq","store_count":"1","shop_price":"1.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190508/20190508151420_.jpeg","is_on_sale":"1"},{"goods_id":"4878","goods_name":"卡扣","store_count":"0","shop_price":"56.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190508/20190508093222_.jpeg","is_on_sale":"1"},{"goods_id":"4877","goods_name":"qq","store_count":"10","shop_price":"10.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190508/20190508085206_.jpeg","is_on_sale":"1"},{"goods_id":"4805","goods_name":"咯嗯","store_count":"8566","shop_price":"56666.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506202137_.jpeg","is_on_sale":"1"},{"goods_id":"4804","goods_name":"咯嗯","store_count":"10","shop_price":"56666.00","original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506201807_.jpeg","is_on_sale":"1"}]
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
         * goods_id : 4994
         * goods_name : hi
         * store_count : 778
         * shop_price : 4554.00
         * original_img : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190511/20190511225718_.jpeg
         * is_on_sale : 1
         */

        private String goods_id;
        private String goods_name;
        private String store_count;
        private String shop_price;
        private String original_img;
        private String is_on_sale;

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

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(String is_on_sale) {
            this.is_on_sale = is_on_sale;
        }
    }
}
