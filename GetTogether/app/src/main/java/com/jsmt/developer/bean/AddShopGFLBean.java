package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-06.
 */

public class AddShopGFLBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"1","pavilion_name":"义乌馆","son":[{"id":"1","name":"国际商贸城一区","pavilion_id":"1"},{"id":"2","name":"国际商贸城一区东","pavilion_id":"1"},{"id":"3","name":"国际商贸城二区","pavilion_id":"1"},{"id":"20","name":"国际商贸城三区","pavilion_id":"1"},{"id":"21","name":"国际商贸城四区","pavilion_id":"1"},{"id":"22","name":"国际商贸城五区","pavilion_id":"1"},{"id":"23","name":"篁园服装市场","pavilion_id":"1"},{"id":"24","name":"国际生产资料市场","pavilion_id":"1"},{"id":"25","name":"副食品市场","pavilion_id":"1"},{"id":"39","name":"梅湖库存","pavilion_id":"1"}]}]
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
         * id : 1
         * pavilion_name : 义乌馆
         * son : [{"id":"1","name":"国际商贸城一区","pavilion_id":"1"},{"id":"2","name":"国际商贸城一区东","pavilion_id":"1"},{"id":"3","name":"国际商贸城二区","pavilion_id":"1"},{"id":"20","name":"国际商贸城三区","pavilion_id":"1"},{"id":"21","name":"国际商贸城四区","pavilion_id":"1"},{"id":"22","name":"国际商贸城五区","pavilion_id":"1"},{"id":"23","name":"篁园服装市场","pavilion_id":"1"},{"id":"24","name":"国际生产资料市场","pavilion_id":"1"},{"id":"25","name":"副食品市场","pavilion_id":"1"},{"id":"39","name":"梅湖库存","pavilion_id":"1"}]
         */

        private String id;
        private String pavilion_name;
        private String sku_str;
        private List<SonBean> son;

        public String getSku_str() {
            return sku_str;
        }

        public void setSku_str(String sku_str) {
            this.sku_str = sku_str;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPavilion_name() {
            return pavilion_name;
        }

        public void setPavilion_name(String pavilion_name) {
            this.pavilion_name = pavilion_name;
        }

        public List<SonBean> getSon() {
            return son;
        }

        public void setSon(List<SonBean> son) {
            this.son = son;
        }

        public static class SonBean {
            /**
             * id : 1
             * name : 国际商贸城一区
             * pavilion_id : 1
             */

            private String id;
            private String name;
            private String pavilion_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPavilion_id() {
                return pavilion_id;
            }

            public void setPavilion_id(String pavilion_id) {
                this.pavilion_id = pavilion_id;
            }
        }
    }
}
