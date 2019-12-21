package com.tem.gettogether.bean;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: FansDataBean
 * @Author: csc
 * @CreateDate: 2019/9/15 9:49
 * @Description:
 */
public class FansDataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"fans":[{"nickname":"娟娟","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190904/20190904093848_15434.jpeg","level":"2"}],"count":"1"}
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
         * fans : [{"nickname":"娟娟","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190904/20190904093848_15434.jpeg","level":"2"}]
         * count : 1
         */

        private String count;
        private List<FansBean> fans;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<FansBean> getFans() {
            return fans;
        }

        public void setFans(List<FansBean> fans) {
            this.fans = fans;
        }

        public static class FansBean {
            /**
             * nickname : 娟娟
             * head_pic : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190904/20190904093848_15434.jpeg
             * level : 2
             */

            private String nickname;
            private String head_pic;
            private String level;
            private String user_id;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
