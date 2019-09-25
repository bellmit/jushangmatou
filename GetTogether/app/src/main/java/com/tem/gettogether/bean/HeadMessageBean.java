package com.tem.gettogether.bean;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: HeadMessageBean
 * @Author: csc
 * @CreateDate: 2019/9/25 16:30
 * @Description:
 */
public class HeadMessageBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"role_type":"1","store_id":"286"}
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
         * role_type : 1
         * store_id : 286
         */

        private String role_type;
        private String store_id;

        public String getRole_type() {
            return role_type;
        }

        public void setRole_type(String role_type) {
            this.role_type = role_type;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }
    }
}
