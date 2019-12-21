package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/1 11:36 
 */
public class RefundProgressBean {

    /**
     * status : 1
     * msg : 提交成功
     * result : [{"pay_status":3,"fail_time":"2019-10-31 11:15:30","review_msg":"支付宝账号不正确"},{"pay_status":0,"ctime":"2019-10-25 15:54:05","review_msg":""}]
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
         * pay_status : 3
         * fail_time : 2019-10-31 11:15:30
         * review_msg : 支付宝账号不正确
         * ctime : 2019-10-25 15:54:05
         */

        private int pay_status;
        private String check_time;
        private String review_msg;

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }


        public String getReview_msg() {
            return review_msg;
        }

        public void setReview_msg(String review_msg) {
            this.review_msg = review_msg;
        }

    }
}
