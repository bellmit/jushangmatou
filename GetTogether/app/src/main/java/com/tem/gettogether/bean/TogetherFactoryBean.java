package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/18 17:12 
 */
public class TogetherFactoryBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"3","user_id":null,"factory_name":"测试工厂1","en_factory_name":"","ara_factory_name":"","factory_logo":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd356918c751.jpg","contact_person":"阿公","en_contact_person":"","ara_contact_person":"","maindeal":"主营成衣生产","en_maindeal":"","ara_maindeal":"","cellphone":"1389098765","telephone":"","qq":"","wechat":"","whatsup":null,"facebook":"","email":"","zipcode":"321898","address":"浙江省金华市永康市瑶石路2号","en_address":"","ara_address":"","description":"主营成衣生产","en_description":"","ara_description":"","createtime":"2019-11-19 10:32:53","sort":null,"status":"1","factory_images2":[""],"website":"www.xxx.com","fm":[{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3542cda11d.jpg","fimages_title":"厂区1"},{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3544530126.jpg","fimages_title":"厂区2"},{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3544d6570d.jpg","fimages_title":"厂区3"}]},{"id":"1","user_id":null,"factory_name":"测试工厂","en_factory_name":"","ara_factory_name":"","factory_logo":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-15/5dce058709d4b.png","contact_person":"阿宝","en_contact_person":"","ara_contact_person":"","maindeal":"主营外贸成衣","en_maindeal":"","ara_maindeal":"","cellphone":"13397675632","telephone":"","qq":"","wechat":"","whatsup":null,"facebook":"","email":"","zipcode":"","address":"浙江省金华市义乌市宾王路223号","en_address":"","ara_address":"","description":"","en_description":"","ara_description":"","createtime":"2019-11-15 09:56:48","sort":null,"status":"1","factory_images2":["","http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-15/5dce05b176f79.png","http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-15/5dce05bfd75b1.png","http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-15/5dce05dc6d7fb.jpg"],"website":"www.xxx.com","fm":[{"fimages":"","fimages_title":null},{"fimages":null,"fimages_title":null},{"fimages":null,"fimages_title":null}]}]
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
         * id : 3
         * user_id : null
         * factory_name : 测试工厂1
         * en_factory_name :
         * ara_factory_name :
         * factory_logo : http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd356918c751.jpg
         * contact_person : 阿公
         * en_contact_person :
         * ara_contact_person :
         * maindeal : 主营成衣生产
         * en_maindeal :
         * ara_maindeal :
         * cellphone : 1389098765
         * telephone :
         * qq :
         * wechat :
         * whatsup : null
         * facebook :
         * email :
         * zipcode : 321898
         * address : 浙江省金华市永康市瑶石路2号
         * en_address :
         * ara_address :
         * description : 主营成衣生产
         * en_description :
         * ara_description :
         * createtime : 2019-11-19 10:32:53
         * sort : null
         * status : 1
         * factory_images2 : [""]
         * website : www.xxx.com
         * fm : [{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3542cda11d.jpg","fimages_title":"厂区1"},{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3544530126.jpg","fimages_title":"厂区2"},{"fimages":"http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3544d6570d.jpg","fimages_title":"厂区3"}]
         */

        private String id;
        private Object user_id;
        private String factory_name;
        private String en_factory_name;
        private String ara_factory_name;
        private String factory_logo;
        private String contact_person;
        private String en_contact_person;
        private String ara_contact_person;
        private String maindeal;
        private String en_maindeal;
        private String ara_maindeal;
        private String cellphone;
        private String telephone;
        private String qq;
        private String wechat;
        private Object whatsup;
        private String facebook;
        private String email;
        private String zipcode;
        private String address;
        private String en_address;
        private String ara_address;
        private String description;
        private String en_description;
        private String ara_description;
        private String createtime;
        private Object sort;
        private String status;
        private String website;
        private List<String> factory_images2;
        private List<FmBean> fm;
        private String distance;

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public String getFactory_name() {
            return factory_name;
        }

        public void setFactory_name(String factory_name) {
            this.factory_name = factory_name;
        }

        public String getEn_factory_name() {
            return en_factory_name;
        }

        public void setEn_factory_name(String en_factory_name) {
            this.en_factory_name = en_factory_name;
        }

        public String getAra_factory_name() {
            return ara_factory_name;
        }

        public void setAra_factory_name(String ara_factory_name) {
            this.ara_factory_name = ara_factory_name;
        }

        public String getFactory_logo() {
            return factory_logo;
        }

        public void setFactory_logo(String factory_logo) {
            this.factory_logo = factory_logo;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public String getEn_contact_person() {
            return en_contact_person;
        }

        public void setEn_contact_person(String en_contact_person) {
            this.en_contact_person = en_contact_person;
        }

        public String getAra_contact_person() {
            return ara_contact_person;
        }

        public void setAra_contact_person(String ara_contact_person) {
            this.ara_contact_person = ara_contact_person;
        }

        public String getMaindeal() {
            return maindeal;
        }

        public void setMaindeal(String maindeal) {
            this.maindeal = maindeal;
        }

        public String getEn_maindeal() {
            return en_maindeal;
        }

        public void setEn_maindeal(String en_maindeal) {
            this.en_maindeal = en_maindeal;
        }

        public String getAra_maindeal() {
            return ara_maindeal;
        }

        public void setAra_maindeal(String ara_maindeal) {
            this.ara_maindeal = ara_maindeal;
        }

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public Object getWhatsup() {
            return whatsup;
        }

        public void setWhatsup(Object whatsup) {
            this.whatsup = whatsup;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEn_address() {
            return en_address;
        }

        public void setEn_address(String en_address) {
            this.en_address = en_address;
        }

        public String getAra_address() {
            return ara_address;
        }

        public void setAra_address(String ara_address) {
            this.ara_address = ara_address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEn_description() {
            return en_description;
        }

        public void setEn_description(String en_description) {
            this.en_description = en_description;
        }

        public String getAra_description() {
            return ara_description;
        }

        public void setAra_description(String ara_description) {
            this.ara_description = ara_description;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public List<String> getFactory_images2() {
            return factory_images2;
        }

        public void setFactory_images2(List<String> factory_images2) {
            this.factory_images2 = factory_images2;
        }

        public List<FmBean> getFm() {
            return fm;
        }

        public void setFm(List<FmBean> fm) {
            this.fm = fm;
        }

        public static class FmBean {
            /**
             * fimages : http://m.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/outside_factory/2019/11-19/5dd3542cda11d.jpg
             * fimages_title : 厂区1
             */

            private String fimages;
            private String fimages_title;

            public String getFimages() {
                return fimages;
            }

            public void setFimages(String fimages) {
                this.fimages = fimages;
            }

            public String getFimages_title() {
                return fimages_title;
            }

            public void setFimages_title(String fimages_title) {
                this.fimages_title = fimages_title;
            }
        }
    }
}
