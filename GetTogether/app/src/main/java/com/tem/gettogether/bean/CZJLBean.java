package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-14.
 */

public class CZJLBean {

    /**
     * status : 1
     * all_page : 1
     * page : 1
     * msg : 成功
     * data : [{"log_id":"297","status":"2","change_time":"2018-11-26 15:51:39","pay_money":"20"}]
     */

    private String status;
    private String all_page;
    private String page;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAll_page() {
        return all_page;
    }

    public void setAll_page(String all_page) {
        this.all_page = all_page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * log_id : 297
         * status : 2
         * change_time : 2018-11-26 15:51:39
         * pay_money : 20
         */

        private String log_id;
        private String status;
        private String change_time;
        private String pay_money;

        public String getLog_id() {
            return log_id;
        }

        public void setLog_id(String log_id) {
            this.log_id = log_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getChange_time() {
            return change_time;
        }

        public void setChange_time(String change_time) {
            this.change_time = change_time;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }
    }
}
