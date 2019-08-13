package com.tem.gettogether.bean;

import java.util.List;

public class LianMengDetailBean {

    /**
     * msg : 获取成功
     * result : [{"qq":"","company_images":"","createtime":"1564039722","company_images2":["http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d396de91b7a3.jpg","http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d396df7163e8.jpg"],"address":"义乌市东方大厦21--8号","maindeal":"承办海运、陆运、空运进出口货物的国际运输代理业务，包括：揽货、托运、订舱、仓储、中转、集装箱拼装拆箱、结算运杂费、报关、报验、保险及相关运输咨询业务&quot;","contact_person":"陈涛","description":"承办海运、陆运、空运进出口货物的国际运输代理业务，包括：揽货、托运、订舱，仓储、中转、集装箱拼装拆箱、结算运杂费、报关、报验、保险及相关运输咨询业务，对于旧的依赖自然资源、资金和新产品技术的传统管理模式，以最终客户为中心、将客户服务、客户满意、客户成功作为管理出发点的供应链管理的确具有多方面的优势","telephone":"","buyer_id":null,"companyid":"15","company_logo":"http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d395a030d8f5.jpg","company_name":"浙江恒莱供应链管理有限公司","cellphone":"15267401600","email":"bgxingzheng@h-line.net.cn","status":"1"}]
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
         * qq :
         * company_images :
         * createtime : 1564039722
         * company_images2 : ["http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d396de91b7a3.jpg","http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d396df7163e8.jpg"]
         * address : 义乌市东方大厦21--8号
         * maindeal : 承办海运、陆运、空运进出口货物的国际运输代理业务，包括：揽货、托运、订舱、仓储、中转、集装箱拼装拆箱、结算运杂费、报关、报验、保险及相关运输咨询业务&quot;
         * contact_person : 陈涛
         * description : 承办海运、陆运、空运进出口货物的国际运输代理业务，包括：揽货、托运、订舱，仓储、中转、集装箱拼装拆箱、结算运杂费、报关、报验、保险及相关运输咨询业务，对于旧的依赖自然资源、资金和新产品技术的传统管理模式，以最终客户为中心、将客户服务、客户满意、客户成功作为管理出发点的供应链管理的确具有多方面的优势
         * telephone :
         * buyer_id : null
         * companyid : 15
         * company_logo : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/enterprise_union/2019/07-25/5d395a030d8f5.jpg
         * company_name : 浙江恒莱供应链管理有限公司
         * cellphone : 15267401600
         * email : bgxingzheng@h-line.net.cn
         * status : 1
         */
        private String qq;
        private String company_images;
        private String createtime;
        private List<String> company_images2;
        private String address;
        private String maindeal;
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
        private String wechat;
        private String whatsapp;
        private String facebook;
        private String website;

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setCompany_images(String company_images) {
            this.company_images = company_images;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public void setCompany_images2(List<String> company_images2) {
            this.company_images2 = company_images2;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setMaindeal(String maindeal) {
            this.maindeal = maindeal;
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

        public List<String> getCompany_images2() {
            return company_images2;
        }

        public String getAddress() {
            return address;
        }

        public String getMaindeal() {
            return maindeal;
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
