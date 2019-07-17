package com.jsmt.developer.bean;

/**
 * Created by lt on 2019-05-06.
 */

public class MyShopDataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store_id":"191","store_name":"瑟莉梅内衣专卖店","app_store_logo":null,"app_store_banner":null,"store_contact_name":null,"store_telephone":null,"store_fax":null,"store_email":"627568542@qq.com","store_qq":"627568542","store_phone":"15267339054","store_des":null,"store_wx":"15267339054","store_whatsapp":"","store_facebook":""}
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
         * store_id : 191
         * store_name : 瑟莉梅内衣专卖店
         * app_store_logo : null
         * app_store_banner : null
         * store_contact_name : null
         * store_telephone : null
         * store_fax : null
         * store_email : 627568542@qq.com
         * store_qq : 627568542
         * store_phone : 15267339054
         * store_des : null
         * store_wx : 15267339054
         * store_whatsapp :
         * store_facebook :
         */

        private String store_id;
        private String store_name;
        private String app_store_logo;
        private String app_store_banner;
        private String store_contact_name;
        private String store_telephone;
        private String store_fax;
        private String store_email;
        private String store_qq;
        private String store_phone;
        private String store_des;
        private String store_wx;
        private String store_whatsapp;
        private String store_facebook;

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

        public String  getStore_contact_name() {
            return store_contact_name;
        }

        public void setStore_contact_name(String store_contact_name) {
            this.store_contact_name = store_contact_name;
        }

        public String getStore_telephone() {
            return store_telephone;
        }

        public void setStore_telephone(String store_telephone) {
            this.store_telephone = store_telephone;
        }

        public String getStore_fax() {
            return store_fax;
        }

        public void setStore_fax(String store_fax) {
            this.store_fax = store_fax;
        }

        public String getStore_email() {
            return store_email;
        }

        public void setStore_email(String store_email) {
            this.store_email = store_email;
        }

        public String getStore_qq() {
            return store_qq;
        }

        public void setStore_qq(String store_qq) {
            this.store_qq = store_qq;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public String getStore_des() {
            return store_des;
        }

        public void setStore_des(String store_des) {
            this.store_des = store_des;
        }

        public String getStore_wx() {
            return store_wx;
        }

        public void setStore_wx(String store_wx) {
            this.store_wx = store_wx;
        }

        public String getStore_whatsapp() {
            return store_whatsapp;
        }

        public void setStore_whatsapp(String store_whatsapp) {
            this.store_whatsapp = store_whatsapp;
        }

        public String getStore_facebook() {
            return store_facebook;
        }

        public void setStore_facebook(String store_facebook) {
            this.store_facebook = store_facebook;
        }
    }
}
