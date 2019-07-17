package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-06.
 */

public class XITMessageLBBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"msg_id":"42","title":"你好","time":"2019-04-27 10:32:51","is_read":0,"url":"http://www.jsmtgou.com/jushangmatou/index.php?m=Home&c=Store&a=store&store_id=42"}]
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
         * msg_id : 42
         * title : 你好
         * time : 2019-04-27 10:32:51
         * is_read : 0
         * url : http://www.jsmtgou.com/jushangmatou/index.php?m=Home&c=Store&a=store&store_id=42
         */

        private String msg_id;
        private String title;
        private String time;
        private int is_read;
        private String url;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
