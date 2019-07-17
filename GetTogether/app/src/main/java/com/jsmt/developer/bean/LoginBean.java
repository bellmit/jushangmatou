package com.jsmt.developer.bean;

/**
 * Created by lt on 2019-02-28.
 */

public class LoginBean {

    /**
     * status : 1
     * msg : 登陆成功
     * result : {"user_id":"137","email":"","nickname":"17682318029","mobile":"17682318029","token":"848fde0dbfb69be96c2489f9280db473","last_login":"2019-02-28 14:42:55","chat_id":"wyoyNEG73+E+hQj4rFhwOUZ8L3aSSjufLUuGrJ+EzBDz+yiRI2HrVabm4w0uokCxUBAZo+TLz7wz2TkcJPmqYQ=="}
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
         * user_id : 137
         * email :
         * nickname : 17682318029
         * mobile : 17682318029
         * token : 848fde0dbfb69be96c2489f9280db473
         * last_login : 2019-02-28 14:42:55
         * chat_id : wyoyNEG73+E+hQj4rFhwOUZ8L3aSSjufLUuGrJ+EzBDz+yiRI2HrVabm4w0uokCxUBAZo+TLz7wz2TkcJPmqYQ==
         */

        private String user_id;
        private String email;
        private String nickname;
        private String mobile;
        private String token;
        private String last_login;
        private String chat_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getChat_id() {
            return chat_id;
        }

        public void setChat_id(String chat_id) {
            this.chat_id = chat_id;
        }
    }
}
