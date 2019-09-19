package com.tem.gettogether.bean;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: ShopDecorationBean
 * @Author: csc
 * @CreateDate: 2019/9/17 14:00
 * @Description:
 */
public class ShopDecorationBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store_name":"hshsjsj","app_store_logo":null,"app_store_banner":null,"seo_description":null,"ad_code":[{"ad_code":"/Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg"}]}
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
         * store_name : hshsjsj
         * app_store_logo : null
         * app_store_banner : null
         * seo_description : null
         * ad_code : [{"ad_code":"/Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg"}]
         */

        private String store_name;
        private String app_store_logo;
        private String app_store_banner;
        private String seo_description;
        private List<AdCodeBean> ad_code;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getApp_store_logo() {
            return app_store_logo;
        }

        public void setApp_store_logo(String app_store_logo) {
            this.app_store_logo = app_store_logo;
        }

        public String getApp_store_banner() {
            return app_store_banner;
        }

        public void setApp_store_banner(String app_store_banner) {
            this.app_store_banner = app_store_banner;
        }

        public String getSeo_description() {
            return seo_description;
        }

        public void setSeo_description(String seo_description) {
            this.seo_description = seo_description;
        }

        public List<AdCodeBean> getAd_code() {
            return ad_code;
        }

        public void setAd_code(List<AdCodeBean> ad_code) {
            this.ad_code = ad_code;
        }

        public static class AdCodeBean {
            /**
             * ad_code : /Public/upload/ad/2018/10-25/5bd1a3f51cadb.jpg
             */

            private String ad_code;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }
    }
}
