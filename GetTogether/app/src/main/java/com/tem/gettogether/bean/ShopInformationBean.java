package com.tem.gettogether.bean;

import java.util.List;

public class ShopInformationBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"store_province":null,"store_city":null,"store_district":null,"store_address":"店铺地址","legal_identity_cert":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160429_24817.jpeg"],"factory_scene":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160436_13899.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160443_82031.jpeg"],"business_licence_cert":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160410_73826.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160422_66923.jpeg"],"sc_name":"百货/家居/文体","store_name":"豆豆","seller_name":"dianpuzhanghao","store_person_identity":null,"business_licence_number":null,"legal_identity":"330782199102262512","apply_type":"2"}]
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
         * store_province : null
         * store_city : null
         * store_district : null
         * store_address : 店铺地址
         * legal_identity_cert : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160429_24817.jpeg"]
         * factory_scene : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160436_13899.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160443_82031.jpeg"]
         * business_licence_cert : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160410_73826.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806160422_66923.jpeg"]
         * sc_name : 百货/家居/文体
         * store_name : 豆豆
         * seller_name : dianpuzhanghao
         * store_person_identity : null
         * business_licence_number : null
         * legal_identity : 330782199102262512
         * apply_type : 2
         */

        private String store_province;
        private String store_city;
        private String store_district;
        private String store_address;
        private String sc_name;
        private String store_name;
        private String seller_name;
        private String store_person_identity;
        private String business_licence_number;
        private String legal_identity;
        private String apply_type;
        private List<String> legal_identity_cert;
        private List<String> factory_scene;
        private List<String> business_licence_cert;
        private List<String> store_person_cert;

        public List<String> getStore_person_cert() {
            return store_person_cert;
        }

        public void setStore_person_cert(List<String> store_person_cert) {
            this.store_person_cert = store_person_cert;
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

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public String getSc_name() {
            return sc_name;
        }

        public void setSc_name(String sc_name) {
            this.sc_name = sc_name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getSeller_name() {
            return seller_name;
        }

        public void setSeller_name(String seller_name) {
            this.seller_name = seller_name;
        }

        public String getStore_person_identity() {
            return store_person_identity;
        }

        public void setStore_person_identity(String store_person_identity) {
            this.store_person_identity = store_person_identity;
        }

        public String getBusiness_licence_number() {
            return business_licence_number;
        }

        public void setBusiness_licence_number(String business_licence_number) {
            this.business_licence_number = business_licence_number;
        }

        public String getLegal_identity() {
            return legal_identity;
        }

        public void setLegal_identity(String legal_identity) {
            this.legal_identity = legal_identity;
        }

        public String getApply_type() {
            return apply_type;
        }

        public void setApply_type(String apply_type) {
            this.apply_type = apply_type;
        }

        public List<String> getLegal_identity_cert() {
            return legal_identity_cert;
        }

        public void setLegal_identity_cert(List<String> legal_identity_cert) {
            this.legal_identity_cert = legal_identity_cert;
        }

        public List<String> getFactory_scene() {
            return factory_scene;
        }

        public void setFactory_scene(List<String> factory_scene) {
            this.factory_scene = factory_scene;
        }

        public List<String> getBusiness_licence_cert() {
            return business_licence_cert;
        }

        public void setBusiness_licence_cert(List<String> business_licence_cert) {
            this.business_licence_cert = business_licence_cert;
        }
    }
}
