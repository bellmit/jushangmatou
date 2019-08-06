package com.tem.gettogether.bean;

public class MemberAmountBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"seniormfee":"998.00","regularmdeposit":"2000.00"}
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
         * seniormfee : 998.00
         * regularmdeposit : 2000.00
         * level: "7"
         */

        private String seniormfee;
        private String regularmdeposit;
        private String level;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSeniormfee() {
            return seniormfee;
        }

        public void setSeniormfee(String seniormfee) {
            this.seniormfee = seniormfee;
        }

        public String getRegularmdeposit() {
            return regularmdeposit;
        }

        public void setRegularmdeposit(String regularmdeposit) {
            this.regularmdeposit = regularmdeposit;
        }
    }
}
