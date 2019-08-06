package com.tem.gettogether.bean;

public class MemberInformationBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"seniormfee":"未缴纳","regularmdeposit":"未缴纳","paystatus":0,"ustart_time":"0","uexpire_time":"0","pay_name":null}
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
         * seniormfee : 未缴纳
         * regularmdeposit : 未缴纳
         * paystatus : 0
         * ustart_time : 0
         * uexpire_time : 0
         * pay_name : null
         */

        private String seniormfee;
        private String regularmdeposit;
        private int paystatus;
        private String ustart_time;
        private String uexpire_time;
        private String pay_name;

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

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public String getUstart_time() {
            return ustart_time;
        }

        public void setUstart_time(String ustart_time) {
            this.ustart_time = ustart_time;
        }

        public String getUexpire_time() {
            return uexpire_time;
        }

        public void setUexpire_time(String uexpire_time) {
            this.uexpire_time = uexpire_time;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }
    }
}
