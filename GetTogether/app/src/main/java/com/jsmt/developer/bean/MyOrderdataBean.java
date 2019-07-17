package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-14.
 */

public class MyOrderdataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"order_id":"415","store_id":"50","shipping_price":"22.00","total_amount":"3052.00","order_status_code":"WAITPAY","order_status_desc":"待支付","pay_btn":1,"cancel_btn":1,"receive_btn":0,"comment_btn":0,"shipping_btn":0,"return_btn":0,"goods_list":[{"goods_id":"244","goods_name":"皮革","goods_sn":"TP0000463","goods_num":"101","goods_price":"30.00","spec_key_name":"颜色:白 尺寸:100","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}],"goods_all_num":101,"store_name":"sorry"}]
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
         * order_id : 415
         * store_id : 50
         * shipping_price : 22.00
         * total_amount : 3052.00
         * order_status_code : WAITPAY
         * order_status_desc : 待支付
         * pay_btn : 1
         * cancel_btn : 1
         * receive_btn : 0
         * comment_btn : 0
         * shipping_btn : 0
         * return_btn : 0
         * goods_list : [{"goods_id":"244","goods_name":"皮革","goods_sn":"TP0000463","goods_num":"101","goods_price":"30.00","spec_key_name":"颜色:白 尺寸:100","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg"}]
         * goods_all_num : 101
         * store_name : sorry
         */

        private String order_id;
        private String store_id;
        private String shipping_price;
        private String total_amount;
        private String order_status_code;
        private String order_status_desc;
        private String master_order_sn;
        private int pay_btn;
        private int cancel_btn;
        private int receive_btn;
        private int comment_btn;
        private int shipping_btn;
        private int return_btn;
        private int goods_all_num;
        private String store_name;
        private List<GoodsListBean> goods_list;
        private int del_btn;
        private int remind_btn;

        public int getRemind_btn() {
            return remind_btn;
        }

        public void setRemind_btn(int remind_btn) {
            this.remind_btn = remind_btn;
        }

        public int getDel_btn() {
            return del_btn;
        }

        public void setDel_btn(int del_btn) {
            this.del_btn = del_btn;
        }

        public String getMaster_order_sn() {
            return master_order_sn;
        }

        public void setMaster_order_sn(String master_order_sn) {
            this.master_order_sn = master_order_sn;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(String shipping_price) {
            this.shipping_price = shipping_price;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getOrder_status_code() {
            return order_status_code;
        }

        public void setOrder_status_code(String order_status_code) {
            this.order_status_code = order_status_code;
        }

        public String getOrder_status_desc() {
            return order_status_desc;
        }

        public void setOrder_status_desc(String order_status_desc) {
            this.order_status_desc = order_status_desc;
        }

        public int getPay_btn() {
            return pay_btn;
        }

        public void setPay_btn(int pay_btn) {
            this.pay_btn = pay_btn;
        }

        public int getCancel_btn() {
            return cancel_btn;
        }

        public void setCancel_btn(int cancel_btn) {
            this.cancel_btn = cancel_btn;
        }

        public int getReceive_btn() {
            return receive_btn;
        }

        public void setReceive_btn(int receive_btn) {
            this.receive_btn = receive_btn;
        }

        public int getComment_btn() {
            return comment_btn;
        }

        public void setComment_btn(int comment_btn) {
            this.comment_btn = comment_btn;
        }

        public int getShipping_btn() {
            return shipping_btn;
        }

        public void setShipping_btn(int shipping_btn) {
            this.shipping_btn = shipping_btn;
        }

        public int getReturn_btn() {
            return return_btn;
        }

        public void setReturn_btn(int return_btn) {
            this.return_btn = return_btn;
        }

        public int getGoods_all_num() {
            return goods_all_num;
        }

        public void setGoods_all_num(int goods_all_num) {
            this.goods_all_num = goods_all_num;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 244
             * goods_name : 皮革
             * goods_sn : TP0000463
             * goods_num : 101
             * goods_price : 30.00
             * spec_key_name : 颜色:白 尺寸:100
             * image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/244/goods_thumb_244_400_400.jpeg
             */

            private String goods_id;
            private String goods_name;
            private String goods_sn;
            private String goods_num;
            private String goods_price;
            private String spec_key_name;
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

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
