package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-01.
 */

public class SouSuoLSBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"297","user_id":"131","session_id":null,"content":"测试","add_time":"2019-02-28 09:26:02","store_id":"0"}]
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
         * id : 297
         * user_id : 131
         * session_id : null
         * content : 测试
         * add_time : 2019-02-28 09:26:02
         * store_id : 0
         */

        private String id;
        private String user_id;
        private Object session_id;
        private String content;
        private String add_time;
        private String store_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public Object getSession_id() {
            return session_id;
        }

        public void setSession_id(Object session_id) {
            this.session_id = session_id;
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

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }
    }
}
