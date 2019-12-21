package com.tem.gettogether.bean;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/27 15:41 
 */
public class OrderCountBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"all_count":10,"waitsend":"6","waitreceive":"0","waitccomment":"4","finish":"0"}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
         * all_count : 10
         * waitsend : 6
         * waitreceive : 0
         * waitccomment : 4
         * finish : 0
         */

        private String all_count;
        private String waitsend;
        private String waitreceive;
        private String waitccomment;
        private String finish;

        public String getAll_count() {
            return all_count;
        }

        public void setAll_count(String all_count) {
            this.all_count = all_count;
        }

        public String getWaitsend() {
            return waitsend;
        }

        public void setWaitsend(String waitsend) {
            this.waitsend = waitsend;
        }

        public String getWaitreceive() {
            return waitreceive;
        }

        public void setWaitreceive(String waitreceive) {
            this.waitreceive = waitreceive;
        }

        public String getWaitccomment() {
            return waitccomment;
        }

        public void setWaitccomment(String waitccomment) {
            this.waitccomment = waitccomment;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }
    }
}
