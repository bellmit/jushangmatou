package com.tem.gettogether.bean;

import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: VisitorBean
 * @Author: csc
 * @CreateDate: 2019/9/15 16:09
 * @Description:
 */
public class VisitorBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"vs":[{"time":"2019-09-14","visiters":[{"nickname":"义乌良哥贸易15858917698","head_pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/ickkuZyeF70jqCV1seEeMndg2o9m9sK3zXKibpX9NLJT3u1hTTAX5I8kcsSIAvdne6NlxZXicTcwHfI1SB8Dicrc3g/132","user_id":"824"}]},{"time":"2019-09-09","visiters":[{"nickname":"码头哥","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190907/20190907144808_51092.jpg","user_id":"809"}]},{"time":"2019-09-03","visiters":[{"nickname":"娟娟","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190904/20190904093848_15434.jpeg","user_id":"715"}]}],"count":3}
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
         * vs : [{"time":"2019-09-14","visiters":[{"nickname":"义乌良哥贸易15858917698","head_pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/ickkuZyeF70jqCV1seEeMndg2o9m9sK3zXKibpX9NLJT3u1hTTAX5I8kcsSIAvdne6NlxZXicTcwHfI1SB8Dicrc3g/132","user_id":"824"}]},{"time":"2019-09-09","visiters":[{"nickname":"码头哥","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190907/20190907144808_51092.jpg","user_id":"809"}]},{"time":"2019-09-03","visiters":[{"nickname":"娟娟","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190904/20190904093848_15434.jpeg","user_id":"715"}]}]
         * count : 3
         */

        private String count;
        private List<VsBean> vs;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<VsBean> getVs() {
            return vs;
        }

        public void setVs(List<VsBean> vs) {
            this.vs = vs;
        }

        public static class VsBean {
            /**
             * time : 2019-09-14
             * visiters : [{"nickname":"义乌良哥贸易15858917698","head_pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/ickkuZyeF70jqCV1seEeMndg2o9m9sK3zXKibpX9NLJT3u1hTTAX5I8kcsSIAvdne6NlxZXicTcwHfI1SB8Dicrc3g/132","user_id":"824"}]
             */

            private String time;
            private List<VisitersBean> visiters;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<VisitersBean> getVisiters() {
                return visiters;
            }

            public void setVisiters(List<VisitersBean> visiters) {
                this.visiters = visiters;
            }

            public static class VisitersBean {
                /**
                 * nickname : 义乌良哥贸易15858917698
                 * head_pic : http://thirdwx.qlogo.cn/mmopen/vi_32/ickkuZyeF70jqCV1seEeMndg2o9m9sK3zXKibpX9NLJT3u1hTTAX5I8kcsSIAvdne6NlxZXicTcwHfI1SB8Dicrc3g/132
                 * user_id : 824
                 */

                private String nickname;
                private String head_pic;
                private String user_id;
                private String mobile;

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
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

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }
            }
        }
    }
}
