package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-10.
 */

public class KSBHBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"goods_id":"244","goods_name":"皮革","shop_price":"0.00","batch_number":"1","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]
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
         * goods_id : 244
         * goods_name : 皮革
         * shop_price : 0.00
         * batch_number : 1
         * best_percent : 100%
         * image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg
         */

        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String batch_number;
        private String best_percent;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
