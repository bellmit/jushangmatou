package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-04.
 */

public class SouSuoDataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"goods_id":"583","goods_name":"堆堆袜 女 日系秋冬棉薄款女袜 纯色复古森系短靴袜套长筒袜子","shop_price":"2.50","batch_number":"5000","best_percent":"100%","cover_image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/583/goods_thumb_583_400_400.png"}]
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
         * goods_id : 583
         * goods_name : 堆堆袜 女 日系秋冬棉薄款女袜 纯色复古森系短靴袜套长筒袜子
         * shop_price : 2.50
         * batch_number : 5000
         * best_percent : 100%
         * cover_image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/583/goods_thumb_583_400_400.png
         */

        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String batch_number;
        private String best_percent;
        private String cover_image;
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

        public String getBest_percent() {
            return best_percent;
        }

        public void setBest_percent(String best_percent) {
            this.best_percent = best_percent;
        }

        public String getcover_image() {
            return cover_image;
        }

        public void setcover_image(String cover_image) {
            this.cover_image = cover_image;
        }
    }
}
