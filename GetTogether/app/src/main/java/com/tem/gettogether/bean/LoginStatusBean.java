package com.tem.gettogether.bean;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/11 16:20 
 */
public class LoginStatusBean {

    /**
     * status : 1
     * msg : facebook已登录过
     * result : {"facebook_status":1,"weixin_status":-1,"role_type":"0"}
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
         * facebook_status : 1
         * weixin_status : -1
         * role_type : 0
         */

        private String facebook_status;
        private String weixin_status;
        private String role_type;

        public String getFacebook_status() {
            return facebook_status;
        }

        public void setFacebook_status(String facebook_status) {
            this.facebook_status = facebook_status;
        }

        public String getWeixin_status() {
            return weixin_status;
        }

        public void setWeixin_status(String weixin_status) {
            this.weixin_status = weixin_status;
        }

        public String getRole_type() {
            return role_type;
        }

        public void setRole_type(String role_type) {
            this.role_type = role_type;
        }
    }
}
