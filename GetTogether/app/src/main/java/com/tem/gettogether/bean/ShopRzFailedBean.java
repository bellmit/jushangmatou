package com.tem.gettogether.bean;

public class ShopRzFailedBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : 你的信息不真实！！
     */

    private int status;
    private String msg;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
