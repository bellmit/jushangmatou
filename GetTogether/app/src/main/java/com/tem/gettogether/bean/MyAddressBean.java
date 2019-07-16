package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-11.
 */

public class MyAddressBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"province":"天津市","city":"市辖区","district":"河东区","twon":"大直沽街道","address":"么得KKK咯弄","consignee":"莫得模OK镜","address_id":"87","email":"","mobile":"18339965555","zipcode":"","is_default":"0"},{"province":"天津市","city":"市辖区","district":"河东区","twon":"大直沽街道","address":"么得KKK咯弄","consignee":"莫得模OK镜","address_id":"88","email":"","mobile":"18339965555","zipcode":"","is_default":"0"},{"province":"天津市","city":"市辖区","district":"河东区","twon":"大直沽街道","address":"么得KKK咯弄","consignee":"嗯嗯呢","address_id":"89","email":"","mobile":"18339965555","zipcode":"","is_default":"1"}]
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
         * province : 天津市
         * city : 市辖区
         * district : 河东区
         * twon : 大直沽街道
         * address : 么得KKK咯弄
         * consignee : 莫得模OK镜
         * address_id : 87
         * email :
         * mobile : 18339965555
         * zipcode :
         * is_default : 0
         */

        private String province;
        private String city;
        private String district;
        private String twon;
        private String address;
        private String consignee;
        private String address_id;
        private String email;
        private String mobile;
        private String zipcode;
        private String is_default;

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
    }
}
