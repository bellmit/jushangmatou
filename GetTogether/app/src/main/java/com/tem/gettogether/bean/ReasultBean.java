package com.tem.gettogether.bean;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: ReasultBean
 * @Author: csc
 * @CreateDate: 2019/9/7 15:19
 * @Description:
 */
public class ReasultBean {

    /**
     * status : -1
     * msg : 请输入商品名称
     * result : 参数错误
     */

    private String status;
    private String msg;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
