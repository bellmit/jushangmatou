package com.tem.gettogether.bean;

import java.util.List;

public class CompanyPersionInformationBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"nickname":"15068156432","sex":"0","mobile":"15068156432","head_pic":null,"country_name":"空","country":[{"id":"1","country_name":"沙特","country_en_name":"Saudi Arabia","country_ara_name":"المملكة العربية السعودية"},{"id":"2","country_name":"伊朗","country_en_name":"Iran","country_ara_name":"إيران"},{"id":"3","country_name":"科威特","country_en_name":"Kuwait","country_ara_name":"الكويت"},{"id":"4","country_name":"伊拉克","country_en_name":"Iraq","country_ara_name":"العراق"},{"id":"5","country_name":"阿联酋","country_en_name":"United Arab Emirates","country_ara_name":"الإمارات العربية المتحدة"},{"id":"6","country_name":"阿曼","country_en_name":"Oman","country_ara_name":"عمان"},{"id":"7","country_name":"卡塔尔","country_en_name":"Qatar","country_ara_name":"قطر"},{"id":"8","country_name":"巴林","country_en_name":"Bahrain","country_ara_name":"البحرين"},{"id":"9","country_name":"土耳其","country_en_name":"Turkey","country_ara_name":"تركيا"},{"id":"10","country_name":"以色列","country_en_name":"Israel","country_ara_name":"إسرائيل"},{"id":"11","country_name":"巴勒斯坦","country_en_name":"Palestine","country_ara_name":"فلسطين"},{"id":"12","country_name":"叙利亚","country_en_name":"Syria","country_ara_name":"سوريا"},{"id":"13","country_name":"黎巴嫩","country_en_name":"Lebanon","country_ara_name":"لبنان"},{"id":"14","country_name":"约旦","country_en_name":"Jordan","country_ara_name":"الأردن"},{"id":"15","country_name":"也门","country_en_name":"Yemen","country_ara_name":"يمني"},{"id":"16","country_name":"塞浦路斯","country_en_name":"Cyprus","country_ara_name":"قبرص"},{"id":"17","country_name":"苏丹","country_en_name":"Sudan","country_ara_name":"سودان"},{"id":"18","country_name":"埃及","country_en_name":"Egypt","country_ara_name":"مصر"},{"id":"19","country_name":"利比亚","country_en_name":"Libya","country_ara_name":"ليبيا"},{"id":"20","country_name":"突尼斯","country_en_name":"Tunisia","country_ara_name":"تونس"},{"id":"21","country_name":"阿尔及利亚","country_en_name":"Algeria","country_ara_name":"الجزائر"},{"id":"22","country_name":"摩洛哥","country_en_name":"Morocco","country_ara_name":"المغرب"},{"id":"23","country_name":"马德拉群岛","country_en_name":"Madeira Islands","country_ara_name":"جزر ماديرا"},{"id":"24","country_name":"亚速尔群岛","country_en_name":"Azores","country_ara_name":"الأزور"},{"id":"25","country_name":"中国","country_en_name":"China","country_ara_name":"الصين"}],"store_state":"1","sc_name":"母婴/食品","company_content":null,"company_name":"哈哈哈","company_province":"内蒙古自治区","company_city":"包头市","company_district":"昆都仑区","company_address":"就是计算机"}
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
         * nickname : 15068156432
         * sex : 0
         * mobile : 15068156432
         * head_pic : null
         * country_name : 空
         * country : [{"id":"1","country_name":"沙特","country_en_name":"Saudi Arabia","country_ara_name":"المملكة العربية السعودية"},{"id":"2","country_name":"伊朗","country_en_name":"Iran","country_ara_name":"إيران"},{"id":"3","country_name":"科威特","country_en_name":"Kuwait","country_ara_name":"الكويت"},{"id":"4","country_name":"伊拉克","country_en_name":"Iraq","country_ara_name":"العراق"},{"id":"5","country_name":"阿联酋","country_en_name":"United Arab Emirates","country_ara_name":"الإمارات العربية المتحدة"},{"id":"6","country_name":"阿曼","country_en_name":"Oman","country_ara_name":"عمان"},{"id":"7","country_name":"卡塔尔","country_en_name":"Qatar","country_ara_name":"قطر"},{"id":"8","country_name":"巴林","country_en_name":"Bahrain","country_ara_name":"البحرين"},{"id":"9","country_name":"土耳其","country_en_name":"Turkey","country_ara_name":"تركيا"},{"id":"10","country_name":"以色列","country_en_name":"Israel","country_ara_name":"إسرائيل"},{"id":"11","country_name":"巴勒斯坦","country_en_name":"Palestine","country_ara_name":"فلسطين"},{"id":"12","country_name":"叙利亚","country_en_name":"Syria","country_ara_name":"سوريا"},{"id":"13","country_name":"黎巴嫩","country_en_name":"Lebanon","country_ara_name":"لبنان"},{"id":"14","country_name":"约旦","country_en_name":"Jordan","country_ara_name":"الأردن"},{"id":"15","country_name":"也门","country_en_name":"Yemen","country_ara_name":"يمني"},{"id":"16","country_name":"塞浦路斯","country_en_name":"Cyprus","country_ara_name":"قبرص"},{"id":"17","country_name":"苏丹","country_en_name":"Sudan","country_ara_name":"سودان"},{"id":"18","country_name":"埃及","country_en_name":"Egypt","country_ara_name":"مصر"},{"id":"19","country_name":"利比亚","country_en_name":"Libya","country_ara_name":"ليبيا"},{"id":"20","country_name":"突尼斯","country_en_name":"Tunisia","country_ara_name":"تونس"},{"id":"21","country_name":"阿尔及利亚","country_en_name":"Algeria","country_ara_name":"الجزائر"},{"id":"22","country_name":"摩洛哥","country_en_name":"Morocco","country_ara_name":"المغرب"},{"id":"23","country_name":"马德拉群岛","country_en_name":"Madeira Islands","country_ara_name":"جزر ماديرا"},{"id":"24","country_name":"亚速尔群岛","country_en_name":"Azores","country_ara_name":"الأزور"},{"id":"25","country_name":"中国","country_en_name":"China","country_ara_name":"الصين"}]
         * store_state : 1
         * sc_name : 母婴/食品
         * company_content : null
         * company_name : 哈哈哈
         * company_province : 内蒙古自治区
         * company_city : 包头市
         * company_district : 昆都仑区
         * company_address : 就是计算机
         */

        private String nickname;
        private String sex;
        private String mobile;
        private Object head_pic;
        private String country_name;
        private String store_state;
        private String sc_name;
        private Object company_content;
        private String company_name;
        private String company_province;
        private String company_city;
        private String company_district;
        private String company_address;
        private List<CountryBean> country;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(Object head_pic) {
            this.head_pic = head_pic;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getStore_state() {
            return store_state;
        }

        public void setStore_state(String store_state) {
            this.store_state = store_state;
        }

        public String getSc_name() {
            return sc_name;
        }

        public void setSc_name(String sc_name) {
            this.sc_name = sc_name;
        }

        public Object getCompany_content() {
            return company_content;
        }

        public void setCompany_content(Object company_content) {
            this.company_content = company_content;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_province() {
            return company_province;
        }

        public void setCompany_province(String company_province) {
            this.company_province = company_province;
        }

        public String getCompany_city() {
            return company_city;
        }

        public void setCompany_city(String company_city) {
            this.company_city = company_city;
        }

        public String getCompany_district() {
            return company_district;
        }

        public void setCompany_district(String company_district) {
            this.company_district = company_district;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public List<CountryBean> getCountry() {
            return country;
        }

        public void setCountry(List<CountryBean> country) {
            this.country = country;
        }

        public static class CountryBean {
            /**
             * id : 1
             * country_name : 沙特
             * country_en_name : Saudi Arabia
             * country_ara_name : المملكة العربية السعودية
             */

            private String id;
            private String country_name;
            private String country_en_name;
            private String country_ara_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            public String getCountry_en_name() {
                return country_en_name;
            }

            public void setCountry_en_name(String country_en_name) {
                this.country_en_name = country_en_name;
            }

            public String getCountry_ara_name() {
                return country_ara_name;
            }

            public void setCountry_ara_name(String country_ara_name) {
                this.country_ara_name = country_ara_name;
            }
        }
    }
}
