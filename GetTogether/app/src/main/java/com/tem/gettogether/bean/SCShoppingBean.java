package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-12.
 */

public class SCShoppingBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"collect_id":"55","add_time":"2018-12-24 14:32:12","goods_id":"244","goods_name":"皮革","shop_price":"0.00","batch_number":"1","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]
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
         * collect_id : 55
         * add_time : 2018-12-24 14:32:12
         * goods_id : 244
         * goods_name : 皮革
         * shop_price : 0.00
         * batch_number : 1
         * image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg
         */

        private String collect_id;
        private String add_time;
        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String batch_number;
        private String image;
        private String is_enquiry;

        public String getIs_enquiry() {
            return is_enquiry;
        }

        public void setIs_enquiry(String is_enquiry) {
            this.is_enquiry = is_enquiry;
        }

        public String getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(String collect_id) {
            this.collect_id = collect_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
