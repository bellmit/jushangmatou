package com.tem.gettogether.bean;

/**
 * Created by lt on 2019-03-11.
 */

public class AddressXQBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"province":"338","city":"339","district":"347","twon":"349","province_name":"天津市","city_name":"市辖区","district_name":"河东区","twon_name":"大直沽街道","address":"么得KKK咯弄","consignee":"莫得模OK镜","address_id":"88","email":"","mobile":"18339965555","zipcode":"","is_default":"0","type":"0"}
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
         * province : 338
         * city : 339
         * district : 347
         * twon : 349
         * province_name : 天津市
         * city_name : 市辖区
         * district_name : 河东区
         * twon_name : 大直沽街道
         * address : 么得KKK咯弄
         * consignee : 莫得模OK镜
         * address_id : 88
         * email :
         * mobile : 18339965555
         * zipcode :
         * is_default : 0
         * type : 0
         */

        private String province;
        private String city;
        private String district;
        private String twon;
        private String province_name;
        private String city_name;
        private String district_name;
        private String twon_name;
        private String address;
        private String consignee;
        private String address_id;
        private String email;
        private String mobile;
        private String zipcode;
        private String is_default;
        private String type;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getTwon() {
            return twon;
        }

        public void setTwon(String twon) {
            this.twon = twon;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getTwon_name() {
            return twon_name;
        }

        public void setTwon_name(String twon_name) {
            this.twon_name = twon_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
