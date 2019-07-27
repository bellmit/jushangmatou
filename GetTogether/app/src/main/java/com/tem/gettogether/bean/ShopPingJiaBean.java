package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-07.
 */

public class ShopPingJiaBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"comment_list":[{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:02:33","content":"0000","comment_id":"371","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfbe0d204621.jpg"}],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:06:54","content":"222222","comment_id":"372","img":[],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:08:03","content":"222","comment_id":"373","img":[],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:09:13","content":"232","comment_id":"374","img":[],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:09:29","content":"1232","comment_id":"375","img":[],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-26, 20:16:47","content":"122","comment_id":"376","img":[],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 09:04:34","content":"6666","comment_id":"377","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfc9820e7166.jpg"}],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","content":"1111111","comment_id":"378","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","content":"1111111","comment_id":"379","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","is_anonymous":"0"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","content":"1111111","comment_id":"380","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","is_anonymous":"0"}]}
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
        private List<CommentListBean> comment_list;

        public List<CommentListBean> getComment_list() {
            return comment_list;
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public static class CommentListBean {
            /**
             * head_pic : http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg
             * nickname : 匿名用户
             * add_time : 2018-11-26, 20:02:33
             * content : 0000
             * comment_id : 371
             * img : [{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfbe0d204621.jpg"}]
             * parent_id : 00000000000
             * is_anonymous : 0
             */

            private String head_pic;
            private String nickname;
            private String add_time;
            private String content;
            private String comment_id;
            private String parent_id;
            private String is_anonymous;
            private List<ImgBean> img;

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getIs_anonymous() {
                return is_anonymous;
            }

            public void setIs_anonymous(String is_anonymous) {
                this.is_anonymous = is_anonymous;
            }

            public List<ImgBean> getImg() {
                return img;
            }

            public void setImg(List<ImgBean> img) {
                this.img = img;
            }

            public static class ImgBean {
                /**
                 * logo : http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfbe0d204621.jpg
                 */

                private String logo;

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }
            }
        }
    }
}
