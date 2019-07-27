package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-07.
 */

public class ShopDongtaiBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"images":["http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/12-01/5c0245980637b.png","http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/12-01/5c0245980637b.png"],"title":"11111","add_time":"2019-03-04 16:37:28","goods_id":"244","goods_name":"皮革","batch_number":"1"}]
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
         * images : ["http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/12-01/5c0245980637b.png","http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/12-01/5c0245980637b.png"]
         * title : 11111
         * add_time : 2019-03-04 16:37:28
         * goods_id : 244
         * goods_name : 皮革
         * batch_number : 1
         */

        private String title;
        private String add_time;
        private String goods_id;
        private String goods_name;
        private String batch_number;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getBatch_number() {
            return batch_number;
        }

        public void setBatch_number(String batch_number) {
            this.batch_number = batch_number;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
