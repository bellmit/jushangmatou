package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-25.
 */

public class YHCardBean {


    /**
     * status : 1
     * msg : 获取成功
     * result : [{"bank_id":"12","user_id":"143","bank_name":"建设","bank_number":"611122231666665566","user_name":"阿龙","id_number":"","bank_logo":"","phone_number":"","add_time":"2019-04-25 17:36:24","is_del":"0"},{"bank_id":"13","user_id":"143","bank_name":"邮政","bank_number":"611122239999999999","user_name":"阿龙","id_number":"","bank_logo":"","phone_number":"","add_time":"2019-04-25 17:37:07","is_del":"0"},{"bank_id":"14","user_id":"143","bank_name":"邮政","bank_number":"611113364599669998","user_name":"嗯哦哦","id_number":"","bank_logo":"","phone_number":"","add_time":"2019-04-25 17:40:40","is_del":"0"}]
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
         * bank_id : 12
         * user_id : 143
         * bank_name : 建设
         * bank_number : 611122231666665566
         * user_name : 阿龙
         * id_number :
         * bank_logo :
         * phone_number :
         * add_time : 2019-04-25 17:36:24
         * is_del : 0
         */

        private String bank_id;
        private String user_id;
        private String bank_name;
        private String bank_number;
        private String user_name;
        private String id_number;
        private String bank_logo;
        private String phone_number;
        private String add_time;
        private String is_del;

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_number() {
            return bank_number;
        }

        public void setBank_number(String bank_number) {
            this.bank_number = bank_number;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }
    }
}
