package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-07.
 */

public class ShopXQBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"fcount":"1","ocount":"3","level":"2","contacts_name":"公积金","contacts_mobile":"15068156432","contacts_email":"fsgsh@163.com","factory_scene":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190831/20190831112235_50298.jpg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190831/20190831112242_18211.jpg"],"seo_description":"护手霜说说","store_name":"套小店呵","user_id":"715","store_id":"286","store_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/remote/2019/09/Ekvapyw4iQjYO.jpg","store_count":"9","store_new_count":"0","location":"辽宁省"}
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
         * fcount : 1
         * ocount : 3
         * level : 2
         * contacts_name : 公积金
         * contacts_mobile : 15068156432
         * contacts_email : fsgsh@163.com
         * factory_scene : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190831/20190831112235_50298.jpg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190831/20190831112242_18211.jpg"]
         * seo_description : 护手霜说说
         * store_name : 套小店呵
         * user_id : 715
         * store_id : 286
         * store_logo : http://www.jsmtgou.com/jushangmatou/Public/upload/remote/2019/09/Ekvapyw4iQjYO.jpg
         * store_count : 9
         * store_new_count : 0
         * location : 辽宁省
         */

        private String fcount;
        private String ocount;
        private String level;
        private String contacts_name;
        private String contacts_mobile;
        private String contacts_email;
        private String seo_description;
        private String store_name;
        private String user_id;
        private String store_id;
        private String store_logo;
        private String store_count;
        private String store_new_count;
        private String location;
        private List<String> factory_scene;

        public String getFcount() {
            return fcount;
        }

        public void setFcount(String fcount) {
            this.fcount = fcount;
        }

        public String getOcount() {
            return ocount;
        }

        public void setOcount(String ocount) {
            this.ocount = ocount;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getContacts_name() {
            return contacts_name;
        }

        public void setContacts_name(String contacts_name) {
            this.contacts_name = contacts_name;
        }

        public String getContacts_mobile() {
            return contacts_mobile;
        }

        public void setContacts_mobile(String contacts_mobile) {
            this.contacts_mobile = contacts_mobile;
        }

        public String getContacts_email() {
            return contacts_email;
        }

        public void setContacts_email(String contacts_email) {
            this.contacts_email = contacts_email;
        }

        public String getSeo_description() {
            return seo_description;
        }

        public void setSeo_description(String seo_description) {
            this.seo_description = seo_description;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public String getStore_new_count() {
            return store_new_count;
        }

        public void setStore_new_count(String store_new_count) {
            this.store_new_count = store_new_count;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<String> getFactory_scene() {
            return factory_scene;
        }

        public void setFactory_scene(List<String> factory_scene) {
            this.factory_scene = factory_scene;
        }
    }
}
