package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-04.
 */

public class ShopTopBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store_id":"1","store_name":"聚商码头旗舰店","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-23/5bf7fe8487d89.png","store_collect":"6","is_collect":"0","store_banner":[{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg","ad_link":"javascript:;"},{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-26/5bd273fb251cc.jpg","ad_link":"javascript:;"},{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-26/5bd2742b71afb.jpg","ad_link":"javascript:;"}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * store_id : 1
         * store_name : 聚商码头旗舰店
         * store_logo : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-23/5bf7fe8487d89.png
         * store_collect : 6
         * is_collect : 0
         * store_banner : [{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg","ad_link":"javascript:;"},{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-26/5bd273fb251cc.jpg","ad_link":"javascript:;"},{"ad_code":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-26/5bd2742b71afb.jpg","ad_link":"javascript:;"}]
         */

        private String store_id;
        private String store_name;
        private String store_logo;
        private String store_collect;
        private String is_collect;
        private List<StoreBannerBean> store_banner;
        private String store_user_id;
        private String store_background;

        public String getStore_background() {
            return store_background;
        }

        public void setStore_background(String store_background) {
            this.store_background = store_background;
        }

        public String getStore_user_id() {
            return store_user_id;
        }

        public void setStore_user_id(String store_user_id) {
            this.store_user_id = store_user_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_collect() {
            return store_collect;
        }

        public void setStore_collect(String store_collect) {
            this.store_collect = store_collect;
        }

        public String getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }

        public List<StoreBannerBean> getStore_banner() {
            return store_banner;
        }

        public void setStore_banner(List<StoreBannerBean> store_banner) {
            this.store_banner = store_banner;
        }

        public static class StoreBannerBean {
            /**
             * ad_code : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg
             * ad_link : javascript:;
             */

            private String ad_code;
            private String ad_link;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }
        }
    }
}
