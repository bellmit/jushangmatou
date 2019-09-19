package com.tem.gettogether.bean;

/**
 * Created by lt on 2019-03-07.
 */

public class ShopXQBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store_id":"50","province_id":"12596","city_id":"13564","district":"13678","store_name":"sorry","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2019/01-21/5c4545250fc47.jpg","store_collect":"1","store_contact_name":"gerry","store_phone":"13366954677","store_des":"","store_wx":"15958442124","store_whatsapp":"","store_facebook":"","best_number":"10.00","store_count":"74","location":"浙江省金华市义乌市"}
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
         * store_id : 50
         * province_id : 12596
         * city_id : 13564
         * district : 13678
         * store_name : sorry
         * store_logo : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2019/01-21/5c4545250fc47.jpg
         * store_collect : 1
         * store_contact_name : gerry
         * store_phone : 13366954677
         * store_des :
         * store_wx : 15958442124
         * store_whatsapp :
         * store_facebook :
         * best_number : 10.00
         * store_count : 74
         * location : 浙江省金华市义乌市
         */

        private String store_id;
        private String province_id;
        private String city_id;
        private String district;
        private String store_name;
        private String store_logo;
        private String store_collect;
        private String store_contact_name;
        private String store_phone;
        private String store_des;
        private String store_wx;
        private String store_whatsapp;
        private String store_facebook;
        private String best_number;
        private String store_count;
        private String location;
        private String store_new_count;
        private String store_comment_count;
        private String fcount;
        private String ocount;
        private String seo_description;
        private String contacts_name;
        private String contacts_mobile;
        private String level;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getContacts_mobile() {
            return contacts_mobile;
        }

        public void setContacts_mobile(String contacts_mobile) {
            this.contacts_mobile = contacts_mobile;
        }

        public String getContacts_name() {
            return contacts_name;
        }

        public void setContacts_name(String contacts_name) {
            this.contacts_name = contacts_name;
        }

        public String getSeo_description() {
            return seo_description;
        }

        public void setSeo_description(String seo_description) {
            this.seo_description = seo_description;
        }

        public String getOcount() {
            return ocount;
        }

        public void setOcount(String ocount) {
            this.ocount = ocount;
        }

        public String getFcount() {
            return fcount;
        }

        public void setFcount(String fcount) {
            this.fcount = fcount;
        }

        public String getStore_comment_count() {
            return store_comment_count;
        }

        public void setStore_comment_count(String store_comment_count) {
            this.store_comment_count = store_comment_count;
        }

        public String getStore_new_count() {
            return store_new_count;
        }

        public void setStore_new_count(String store_new_count) {
            this.store_new_count = store_new_count;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
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

        public String getStore_contact_name() {
            return store_contact_name;
        }

        public void setStore_contact_name(String store_contact_name) {
            this.store_contact_name = store_contact_name;
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

        public String getBest_number() {
            return best_number;
        }

        public void setBest_number(String best_number) {
            this.best_number = best_number;
        }

        public String getStore_count() {
            return store_count;
        }

        public void setStore_count(String store_count) {
            this.store_count = store_count;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
