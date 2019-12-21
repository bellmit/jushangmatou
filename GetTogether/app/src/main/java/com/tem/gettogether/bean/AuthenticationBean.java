package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/8 11:40 
 */
public class AuthenticationBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"business_licence_cert":["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155607_38982.jpg"],"legal_identity_cert":["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155545_68356.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155556_64317.jpg"],"user_id":"1032","contacts_name":"陈氏春","contacts_mobile":"15068156432","contacts_email":"64646@qq.com","is_linyi":"1","mobile":null,"store_name":"多喝水","sc_id":"3","store_province":"636","store_city":"1554","store_district":"1564","store_street":null,"area":"河北省邢台市桥西区","store_address":"看你那呢","apply_type":"0","review_msg":"信息不全","stype":"百货/家居/建材"}
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
         * business_licence_cert : ["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155607_38982.jpg"]
         * legal_identity_cert : ["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155545_68356.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191107/20191107155556_64317.jpg"]
         * user_id : 1032
         * contacts_name : 陈氏春
         * contacts_mobile : 15068156432
         * contacts_email : 64646@qq.com
         * is_linyi : 1
         * mobile : null
         * store_name : 多喝水
         * sc_id : 3
         * store_province : 636
         * store_city : 1554
         * store_district : 1564
         * store_street : null
         * area : 河北省邢台市桥西区
         * store_address : 看你那呢
         * apply_type : 0
         * review_msg : 信息不全
         * stype : 百货/家居/建材
         */

        private String user_id;
        private String contacts_name;
        private String contacts_mobile;
        private String contacts_email;
        private String is_linyi;
        private String mobile;
        private String store_name;
        private String sc_id;
        private String store_province;
        private String store_city;
        private String store_district;
        private String store_street;
        private String area;
        private String store_address;
        private String apply_type;
        private String review_msg;
        private String stype;
        private List<String> store_person_cert;
        private List<String> business_licence_cert;
        private List<String> legal_identity_cert;
        private List<String> factory_scene;

        public List<String> getStore_person_cert() {
            return store_person_cert;
        }

        public void setStore_person_cert(List<String> store_person_cert) {
            this.store_person_cert = store_person_cert;
        }

        public List<String> getFactory_scene() {
            return factory_scene;
        }

        public void setFactory_scene(List<String> factory_scene) {
            this.factory_scene = factory_scene;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getIs_linyi() {
            return is_linyi;
        }

        public void setIs_linyi(String is_linyi) {
            this.is_linyi = is_linyi;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getSc_id() {
            return sc_id;
        }

        public void setSc_id(String sc_id) {
            this.sc_id = sc_id;
        }

        public String getStore_province() {
            return store_province;
        }

        public void setStore_province(String store_province) {
            this.store_province = store_province;
        }

        public String getStore_city() {
            return store_city;
        }

        public void setStore_city(String store_city) {
            this.store_city = store_city;
        }

        public String getStore_district() {
            return store_district;
        }

        public void setStore_district(String store_district) {
            this.store_district = store_district;
        }

        public String getStore_street() {
            return store_street;
        }

        public void setStore_street(String store_street) {
            this.store_street = store_street;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getApply_type() {
            return apply_type;
        }

        public void setApply_type(String apply_type) {
            this.apply_type = apply_type;
        }

        public String getReview_msg() {
            return review_msg;
        }

        public void setReview_msg(String review_msg) {
            this.review_msg = review_msg;
        }

        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public List<String> getBusiness_licence_cert() {
            return business_licence_cert;
        }

        public void setBusiness_licence_cert(List<String> business_licence_cert) {
            this.business_licence_cert = business_licence_cert;
        }

        public List<String> getLegal_identity_cert() {
            return legal_identity_cert;
        }

        public void setLegal_identity_cert(List<String> legal_identity_cert) {
            this.legal_identity_cert = legal_identity_cert;
        }
    }
}
