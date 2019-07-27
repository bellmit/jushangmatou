package com.tem.gettogether.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 18339 on 2017/12/14.
 */

public class WechatDataBean {


    /**
     * msg : 获取成功
     * status : 1
     * result : {"appid":null,"noncestr":null,"package":"Sign=WXPay","partnerid":null,"prepayid":null,"timestamp":1554260570,"sign":"B67B470D16F78C4C7F57C0CFF79F93D5"}
     */

    private String msg;
    private int status;
    private ResultBean result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * appid : null
         * noncestr : null
         * package : Sign=WXPay
         * partnerid : null
         * prepayid : null
         * timestamp : 1554260570
         * sign : B67B470D16F78C4C7F57C0CFF79F93D5
         */

        private String  appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
