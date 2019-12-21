package com.tem.gettogether.bean;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/25 10:10 
 */
public class RefundAmountBean {

    /**
     * status : 1
     * msg : 提交成功
     * result : {"level_id":"1","account":"2000.00","saccount":"998.00","upgrade_account":"1002.00"}
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
         * level_id : 1
         * account : 2000.00
         * saccount : 998.00
         * upgrade_account : 1002.00
         */

        private String level_id;
        private String account;
        private String saccount;
        private String upgrade_account;

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getSaccount() {
            return saccount;
        }

        public void setSaccount(String saccount) {
            this.saccount = saccount;
        }

        public String getUpgrade_account() {
            return upgrade_account;
        }

        public void setUpgrade_account(String upgrade_account) {
            this.upgrade_account = upgrade_account;
        }
    }
}
