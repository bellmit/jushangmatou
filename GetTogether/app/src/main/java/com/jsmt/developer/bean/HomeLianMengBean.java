package com.jsmt.developer.bean;

import java.util.List;

public class HomeLianMengBean {

    /**
     * msg : 获取成功
     * result : [{"qq":null,"company_images":null,"createtime":"1563005942","address":"浙江省义乌市稠州北路601号10栋7号201室","contact_person":"王伟亚","description":null,"telephone":null,"buyer_id":null,"companyid":"10","company_logo":null,"company_name":"义乌市赛瑞斯国际贸易有限公司","cellphone":"15381750152","email":null,"status":"1"},{"qq":null,"company_images":null,"createtime":"1563005836","address":null,"contact_person":"张荣刚","description":null,"telephone":null,"buyer_id":null,"companyid":"9","company_logo":null,"company_name":"美国5CGROUP(五義资产管理咨询）有限公司","cellphone":"17316932965","email":null,"status":"1"},{"qq":null,"company_images":null,"createtime":"1563005783","address":"浙江省义乌市国际大厦2栋3单元408室","contact_person":"马举富","description":null,"telephone":null,"buyer_id":null,"companyid":"8","company_logo":null,"company_name":"赫步国际贸易有限公司","cellphone":"15825775846","email":null,"status":"1"},{"qq":null,"company_images":null,"createtime":"1563005692","address":"浙江省义乌市义乌港A座2021室","contact_person":"姜凤","description":null,"telephone":null,"buyer_id":null,"companyid":"7","company_logo":"","company_name":"尚迪国际贸易有限公司","cellphone":"15372956109","email":null,"status":"1"},{"qq":null,"company_images":null,"createtime":"1562921195","address":"浙江省义乌市五爱路97号502室","contact_person":"徐 磊","description":null,"telephone":null,"buyer_id":null,"companyid":"6","company_logo":null,"company_name":"万尼斯国际贸易有限公司","cellphone":"15588032188","email":null,"status":"1"},{"qq":null,"company_images":null,"createtime":"1562921114","address":"浙江省义乌市东方大厦西座18-7室","contact_person":"苏亮","description":null,"telephone":null,"buyer_id":null,"companyid":"5","company_logo":null,"company_name":"淼迈进出口有限公司","cellphone":"15988529905","email":null,"status":"1"}]
     * status : 1
     */
    private String msg;
    private List<ResultEntity> result;
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public class ResultEntity {
        /**
         * qq : null
         * company_images : null
         * createtime : 1563005942
         * address : 浙江省义乌市稠州北路601号10栋7号201室
         * contact_person : 王伟亚
         * description : null
         * telephone : null
         * buyer_id : null
         * companyid : 10
         * company_logo : null
         * company_name : 义乌市赛瑞斯国际贸易有限公司
         * cellphone : 15381750152
         * email : null
         * status : 1
         */
        private String qq;
        private String company_images;
        private String createtime;
        private String address;
        private String contact_person;
        private String description;
        private String telephone;
        private String buyer_id;
        private String companyid;
        private String company_logo;
        private String company_name;
        private String cellphone;
        private String email;
        private String status;

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setCompany_images(String company_images) {
            this.company_images = company_images;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public void setCompanyid(String companyid) {
            this.companyid = companyid;
        }

        public void setCompany_logo(String company_logo) {
            this.company_logo = company_logo;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getQq() {
            return qq;
        }

        public String getCompany_images() {
            return company_images;
        }

        public String getCreatetime() {
            return createtime;
        }

        public String getAddress() {
            return address;
        }

        public String getContact_person() {
            return contact_person;
        }

        public String getDescription() {
            return description;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public String getCompanyid() {
            return companyid;
        }

        public String getCompany_logo() {
            return company_logo;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getCellphone() {
            return cellphone;
        }

        public String getEmail() {
            return email;
        }

        public String getStatus() {
            return status;
        }
    }
}
