package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-13.
 */

public class ShoppingPJBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"comment_list":[{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/11-26/5bfba8c632a7e.jpg","nickname":"匿名用户","add_time":"2018-11-26, 14:38:55","spec_key_name":"内存:32G 颜色:黑色","content":"非常的棒","impression":"","comment_id":"369","goods_rank":"5.0","user_id":"83","order_id":"381","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb946d496ea.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb947c86bbf.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb9488ab9b9.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb949f3b0d0.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb94bd6c9f5.jpg"}],"parent_id":"00000000000","goods_num":"90"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","spec_key_name":"内存:32G 颜色:黑色","content":"1111111","impression":"2323","comment_id":"379","goods_rank":"5.0","user_id":"82","order_id":"385","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","goods_num":null}]}
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
             * head_pic : http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/11-26/5bfba8c632a7e.jpg
             * nickname : 匿名用户
             * add_time : 2018-11-26, 14:38:55
             * spec_key_name : 内存:32G 颜色:黑色
             * content : 非常的棒
             * impression :
             * comment_id : 369
             * goods_rank : 5.0
             * user_id : 83
             * order_id : 381
             * img : [{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb946d496ea.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb947c86bbf.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb9488ab9b9.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb949f3b0d0.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb94bd6c9f5.jpg"}]
             * parent_id : 00000000000
             * goods_num : 90
             */

            private String head_pic;
            private String nickname;
            private String add_time;
            private String spec_key_name;
            private String content;
            private String impression;
            private String comment_id;
            private String goods_rank;
            private String user_id;
            private String order_id;
            private String parent_id;
            private String goods_num;
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

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImpression() {
                return impression;
            }

            public void setImpression(String impression) {
                this.impression = impression;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getGoods_rank() {
                return goods_rank;
            }

            public void setGoods_rank(String goods_rank) {
                this.goods_rank = goods_rank;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public List<ImgBean> getImg() {
                return img;
            }

            public void setImg(List<ImgBean> img) {
                this.img = img;
            }

            public static class ImgBean {
                /**
                 * logo : http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg
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
