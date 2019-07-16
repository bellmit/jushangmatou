package com.tem.gettogether.bean;

/**
 * Created by lt on 2019-05-09.
 */

public class FuwuXieYBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"protocol_id":"1","title":"聚商码头服务协议","content":"ize: 1","add_time":"2016-05-28 12:43:03"}
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
         * protocol_id : 1
         * title : 聚商码头服务协议
         * content : ize: 1
         * add_time : 2016-05-28 12:43:03
         */

        private String protocol_id;
        private String title;
        private String content;
        private String add_time;

        public String getProtocol_id() {
            return protocol_id;
        }

        public void setProtocol_id(String protocol_id) {
            this.protocol_id = protocol_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
