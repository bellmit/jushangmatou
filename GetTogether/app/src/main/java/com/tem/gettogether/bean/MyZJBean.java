package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-10.
 */

public class MyZJBean {


    /**
     * status : 1
     * msg : 获取成功
     * result : [{"footprint_id":"1863","pid":"1533","add_time":"1556414972","original_img":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/1533/goods_thumb_1533_400_400.png","goods_name":"新款糖果色t恤女短袖学生宽松韩国ins半袖上衣","store_id":"78","goods_price":"12.00","goods_sales":"0","batch_number":"10"},{"footprint_id":"1862","pid":"1531","add_time":"1556414969","original_img":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/1531/goods_thumb_1531_400_400.png","goods_name":"气质显瘦清新可爱女神针织衫 半身裙两件套","store_id":"78","goods_price":"0.00","goods_sales":"0","batch_number":"225"},{"footprint_id":"1861","pid":"1532","add_time":"1556414967","original_img":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/1532/goods_thumb_1532_400_400.jpeg","goods_name":"春季新款女式慵懒蝙蝠袖羊毛打底衫一字领宽松女装薄款针织衫","store_id":"82","goods_price":"55.00","goods_sales":"0","batch_number":"2"}]
     */

    private String status;
    private String msg;
    private List<ResultBean> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
         * footprint_id : 1863
         * pid : 1533
         * add_time : 1556414972
         * original_img : http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/1533/goods_thumb_1533_400_400.png
         * goods_name : 新款糖果色t恤女短袖学生宽松韩国ins半袖上衣
         * store_id : 78
         * goods_price : 12.00
         * goods_sales : 0
         * batch_number : 10
         */

        private String footprint_id;
        private String pid;
        private String add_time;
        private String original_img;
        private String goods_name;
        private String store_id;
        private String goods_price;
        private String goods_sales;
        private String batch_number;

        public String getFootprint_id() {
            return footprint_id;
        }

        public void setFootprint_id(String footprint_id) {
            this.footprint_id = footprint_id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_sales() {
            return goods_sales;
        }

        public void setGoods_sales(String goods_sales) {
            this.goods_sales = goods_sales;
        }

        public String getBatch_number() {
            return batch_number;
        }

        public void setBatch_number(String batch_number) {
            this.batch_number = batch_number;
        }
    }
}
